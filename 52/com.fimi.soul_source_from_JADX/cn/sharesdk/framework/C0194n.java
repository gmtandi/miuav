package cn.sharesdk.framework;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Message;
import cn.sharesdk.framework.p013b.C0170a;
import cn.sharesdk.framework.utils.C0205d;
import com.facebook.common.util.UriUtil;
import com.mob.tools.SSDKHandlerThread;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.C2178R;
import com.mob.tools.utils.Hashon;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.codehaus.jackson.smile.SmileConstants;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* renamed from: cn.sharesdk.framework.n */
public class C0194n extends SSDKHandlerThread {
    private C0193a f341a;
    private Context f342b;
    private HashMap<String, HashMap<String, String>> f343c;
    private ArrayList<Platform> f344d;
    private HashMap<String, Integer> f345e;
    private HashMap<Integer, String> f346f;
    private HashMap<Integer, CustomPlatform> f347g;
    private HashMap<Integer, HashMap<String, Object>> f348h;
    private HashMap<Integer, Service> f349i;
    private String f350j;
    private boolean f351k;
    private String f352l;
    private boolean f353m;
    private boolean f354n;

    /* renamed from: cn.sharesdk.framework.n.a */
    enum C0193a {
        IDLE,
        INITIALIZING,
        READY
    }

    public C0194n(Context context, String str) {
        super("Thread-" + Math.abs(-31716));
        this.f350j = str;
        this.f341a = C0193a.IDLE;
        this.f342b = context.getApplicationContext();
        this.f343c = new HashMap();
        this.f344d = new ArrayList();
        this.f345e = new HashMap();
        this.f346f = new HashMap();
        this.f347g = new HashMap();
        this.f348h = new HashMap();
        this.f349i = new HashMap();
    }

    private boolean m676a(C0170a c0170a, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
        try {
            if (hashMap.containsKey(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                C0205d.m752a().m743i("ShareSDK parse sns config ==>>", new Hashon().fromHashMap(hashMap));
                return false;
            } else if (hashMap.containsKey(UriUtil.LOCAL_RESOURCE_SCHEME)) {
                String str = (String) hashMap.get(UriUtil.LOCAL_RESOURCE_SCHEME);
                if (str == null) {
                    return false;
                }
                hashMap2.putAll(c0170a.m516b(str));
                return true;
            } else {
                C0205d.m752a().m737d("ShareSDK platform config result ==>>", "SNS configuration is empty");
                return false;
            }
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
            return false;
        }
    }

    private void m678h() {
        synchronized (this.f343c) {
            this.f343c.clear();
            m679i();
            if (this.f343c.containsKey("ShareSDK")) {
                HashMap hashMap = (HashMap) this.f343c.remove("ShareSDK");
                if (hashMap != null) {
                    if (this.f350j == null) {
                        this.f350j = (String) hashMap.get("AppKey");
                    }
                    this.f352l = hashMap.containsKey("UseVersion") ? (String) hashMap.get("UseVersion") : "latest";
                }
            }
        }
    }

    private void m679i() {
        XmlPullParser newPullParser;
        InputStream open;
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            newPullParser = newInstance.newPullParser();
            open = this.f342b.getAssets().open("ShareSDK.xml");
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
            return;
        }
        newPullParser.setInput(open, "utf-8");
        for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
            if (eventType == 2) {
                String name = newPullParser.getName();
                HashMap hashMap = new HashMap();
                int attributeCount = newPullParser.getAttributeCount();
                for (eventType = 0; eventType < attributeCount; eventType++) {
                    hashMap.put(newPullParser.getAttributeName(eventType), newPullParser.getAttributeValue(eventType).trim());
                }
                this.f343c.put(name, hashMap);
            }
        }
        open.close();
    }

    private void m680j() {
        new C0195o(this).start();
    }

    private void m681k() {
        this.f344d.clear();
        Collection a = new C0191l(this.f342b, this.f350j).m664a();
        if (a != null) {
            this.f344d.addAll(a);
        }
        synchronized (this.f345e) {
            synchronized (this.f346f) {
                Iterator it = this.f344d.iterator();
                while (it.hasNext()) {
                    Platform platform = (Platform) it.next();
                    this.f346f.put(Integer.valueOf(platform.getPlatformId()), platform.getName());
                    this.f345e.put(platform.getName(), Integer.valueOf(platform.getPlatformId()));
                }
            }
        }
    }

    private void m682l() {
        C0191l c0191l = new C0191l(this.f342b, this.f350j);
        c0191l.m668a(this);
        c0191l.m667a(this.handler, this.f351k, m704c());
    }

    public Platform m683a(String str) {
        if (str == null) {
            return null;
        }
        Platform[] a = m696a();
        if (a == null) {
            return null;
        }
        for (Platform platform : a) {
            if (str.equals(platform.getName())) {
                return platform;
            }
        }
        return null;
    }

    public String m684a(int i, String str) {
        String a;
        synchronized (this.f348h) {
            a = new C0191l(this.f342b, this.f350j).m660a(i, str, this.f348h);
        }
        return a;
    }

    public String m685a(Bitmap bitmap) {
        return C0193a.READY != this.f341a ? null : new C0191l(this.f342b, this.f350j).m661a(bitmap);
    }

    public String m686a(String str, boolean z, int i, String str2) {
        return C0193a.READY != this.f341a ? str : new C0191l(this.f342b, this.f350j).m663a(str, z, i, str2);
    }

    public void m687a(int i) {
        NetworkHelper.connectionTimeout = i;
    }

    public void m688a(int i, int i2) {
        synchronized (this.f348h) {
            new C0191l(this.f342b, this.f350j).m665a(i, i2, this.f348h);
        }
    }

    public void m689a(int i, Platform platform) {
        new C0191l(this.f342b, this.f350j).m666a(i, platform);
    }

    public void m690a(Class<? extends Service> cls) {
        synchronized (this.f349i) {
            if (this.f349i.containsKey(Integer.valueOf(cls.hashCode()))) {
                return;
            }
            try {
                Service service = (Service) cls.newInstance();
                this.f349i.put(Integer.valueOf(cls.hashCode()), service);
                service.m398a(this.f342b);
                service.m399a(this.f350j);
                service.onBind();
            } catch (Throwable th) {
                C0205d.m752a().m750w(th);
            }
        }
    }

    public void m691a(String str, int i) {
        new C0191l(this.f342b, this.f350j).m669a(str, i);
    }

    public void m692a(String str, String str2) {
        synchronized (this.f343c) {
            this.f343c.put(str2, (HashMap) this.f343c.get(str));
        }
    }

    public void m693a(String str, HashMap<String, Object> hashMap) {
        synchronized (this.f343c) {
            HashMap hashMap2 = (HashMap) this.f343c.get(str);
            HashMap hashMap3 = hashMap2 == null ? new HashMap() : hashMap2;
            synchronized (hashMap3) {
                for (Entry entry : hashMap.entrySet()) {
                    String str2 = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value != null) {
                        hashMap3.put(str2, String.valueOf(value));
                    }
                }
            }
            this.f343c.put(str, hashMap3);
        }
    }

    public void m694a(boolean z) {
        this.f351k = z;
    }

    public boolean m695a(HashMap<String, Object> hashMap) {
        if (C0193a.READY != this.f341a) {
            C0205d.m752a().m737d("Statistics module unopened", new Object[0]);
            return false;
        }
        C0170a a = C0170a.m499a(this.f342b, this.f350j);
        boolean a2 = m676a(a, a.m521f(), hashMap);
        if (a2) {
            this.f354n = true;
            new C0196p(this, a).start();
            return a2;
        }
        try {
            HashMap g = a.m522g();
            a2 = m676a(a, g, hashMap);
            if (a2) {
                a.m513a(g);
            }
            this.f354n = true;
            return a2;
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
            this.f354n = false;
            return a2;
        }
    }

    public Platform[] m696a() {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.f344d) {
            if (this.f341a == C0193a.IDLE) {
                return null;
            }
            if (this.f341a == C0193a.INITIALIZING) {
                try {
                    this.f344d.wait();
                } catch (Throwable th) {
                    C0205d.m752a().m750w(th);
                }
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = this.f344d.iterator();
            while (it.hasNext()) {
                Platform platform = (Platform) it.next();
                if (platform != null && platform.m396b()) {
                    platform.m395a();
                    arrayList.add(platform);
                }
            }
            for (Entry value : this.f347g.entrySet()) {
                platform = (Platform) value.getValue();
                if (platform != null && platform.m396b()) {
                    arrayList.add(platform);
                }
            }
            if (arrayList.size() <= 0) {
                return null;
            }
            Platform[] platformArr = new Platform[arrayList.size()];
            for (int i = 0; i < platformArr.length; i++) {
                platformArr[i] = (Platform) arrayList.get(i);
            }
            C0205d.m752a().m743i("sort list use time: %s", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return platformArr;
        }
    }

    public int m697b(String str) {
        int intValue;
        synchronized (this.f345e) {
            if (this.f345e.containsKey(str)) {
                intValue = ((Integer) this.f345e.get(str)).intValue();
            } else {
                intValue = 0;
            }
        }
        return intValue;
    }

    public String m698b() {
        try {
            return new C0191l(this.f342b, this.f350j).m673c();
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
            return "2.7.2";
        }
    }

    public String m699b(String str, String str2) {
        String str3;
        synchronized (this.f343c) {
            HashMap hashMap = (HashMap) this.f343c.get(str);
            if (hashMap == null) {
                str3 = null;
            } else {
                str3 = (String) hashMap.get(str2);
            }
        }
        return str3;
    }

    public void m700b(int i) {
        NetworkHelper.readTimout = i;
    }

    public void m701b(Class<? extends Service> cls) {
        synchronized (this.f349i) {
            int hashCode = cls.hashCode();
            if (this.f349i.containsKey(Integer.valueOf(hashCode))) {
                ((Service) this.f349i.get(Integer.valueOf(hashCode))).onUnbind();
                this.f349i.remove(Integer.valueOf(hashCode));
            }
        }
    }

    public void m702b(boolean z) {
        this.f353m = z;
    }

    public boolean m703b(HashMap<String, Object> hashMap) {
        boolean a;
        synchronized (this.f348h) {
            this.f348h.clear();
            a = new C0191l(this.f342b, this.f350j).m671a((HashMap) hashMap, this.f348h);
        }
        return a;
    }

    public int m704c() {
        try {
            return new C0191l(this.f342b, this.f350j).m674d();
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
            return 60;
        }
    }

    public <T extends Service> T m705c(Class<T> cls) {
        synchronized (this.f349i) {
            if (this.f341a == C0193a.IDLE) {
                return null;
            }
            if (this.f341a == C0193a.INITIALIZING) {
                try {
                    this.f349i.wait();
                } catch (Throwable th) {
                    C0205d.m752a().m750w(th);
                }
            }
            try {
                Service service = (Service) cls.cast(this.f349i.get(Integer.valueOf(cls.hashCode())));
                return service;
            } catch (Throwable th2) {
                C0205d.m752a().m750w(th2);
                return null;
            }
        }
    }

    public String m706c(int i) {
        String str;
        synchronized (this.f346f) {
            str = (String) this.f346f.get(Integer.valueOf(i));
        }
        return str;
    }

    public String m707c(String str) {
        return C0193a.READY != this.f341a ? null : new C0191l(this.f342b, this.f350j).m662a(str);
    }

    public void m708d(Class<? extends CustomPlatform> cls) {
        synchronized (this.f347g) {
            if (this.f347g.containsKey(Integer.valueOf(cls.hashCode()))) {
                return;
            }
            try {
                CustomPlatform customPlatform = (CustomPlatform) cls.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{this.f342b});
                this.f347g.put(Integer.valueOf(cls.hashCode()), customPlatform);
                synchronized (this.f345e) {
                    synchronized (this.f346f) {
                        if (customPlatform != null) {
                            if (customPlatform.m396b()) {
                                this.f346f.put(Integer.valueOf(customPlatform.getPlatformId()), customPlatform.getName());
                                this.f345e.put(customPlatform.getName(), Integer.valueOf(customPlatform.getPlatformId()));
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                C0205d.m752a().m750w(th);
            }
        }
    }

    public boolean m709d() {
        return this.f353m;
    }

    public void m710e(Class<? extends CustomPlatform> cls) {
        int hashCode = cls.hashCode();
        synchronized (this.f347g) {
            this.f347g.remove(Integer.valueOf(hashCode));
        }
    }

    public boolean m711e() {
        return this.f351k;
    }

    public boolean m712f() {
        boolean z;
        synchronized (this.f348h) {
            if (this.f348h == null || this.f348h.size() <= 0) {
                z = this.f354n;
            } else {
                z = true;
            }
        }
        return z;
    }

    public void m713g() {
        try {
            C2178R.clearCache(this.f342b);
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
        }
    }

    protected void onMessage(Message message) {
        switch (message.what) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f341a = C0193a.IDLE;
                this.handler.getLooper().quit();
            default:
        }
    }

    protected void onStart(Message message) {
        synchronized (this.f349i) {
            synchronized (this.f344d) {
                try {
                    m681k();
                    m682l();
                    this.f341a = C0193a.READY;
                    this.f344d.notify();
                    this.f349i.notify();
                    m680j();
                } catch (Throwable th) {
                    C0205d.m752a().m750w(th);
                }
            }
        }
    }

    protected void onStop(Message message) {
        synchronized (this.f349i) {
            for (Entry value : this.f349i.entrySet()) {
                ((Service) value.getValue()).onUnbind();
            }
            this.f349i.clear();
        }
        synchronized (this.f347g) {
            this.f347g.clear();
        }
        try {
            new C0191l(this.f342b, this.f350j).m672b();
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
            this.handler.getLooper().quit();
            this.f341a = C0193a.IDLE;
        }
    }

    public void startThread() {
        this.f341a = C0193a.INITIALIZING;
        m678h();
        C0205d.m753a(this.f342b, 60060, this.f350j);
        super.startThread();
    }
}
