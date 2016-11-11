package com.mob.commons.appcollector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Base64;
import com.fimi.kernel.C1154b;
import com.fimi.soul.entity.User;
import com.mi.live.openlivesdk.C2116b;
import com.mob.tools.MobLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.C2178R;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.LocalDB;
import com.tencent.open.SocialConstants;
import com.xiaomi.market.sdk.C2537j;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.codehaus.jackson.smile.SmileConstants;

public class PackageCollector extends BroadcastReceiver implements Callback {
    private static PackageCollector f11281a;
    private static FileLocker f11282b;
    private final String f11283c;
    private final String[] f11284d;
    private final int f11285e;
    private final int f11286f;
    private C2149a f11287g;
    private NetworkHelper f11288h;
    private PackageManager f11289i;
    private DeviceHelper f11290j;
    private IntentFilter f11291k;
    private C2146a f11292l;
    private Handler f11293m;
    private Context f11294n;
    private String f11295o;

    /* renamed from: com.mob.commons.appcollector.PackageCollector.a */
    class C2146a {
        final /* synthetic */ PackageCollector f11278a;
        private final String f11279b;
        private LocalDB f11280c;

        public C2146a(PackageCollector packageCollector, Context context) {
            this.f11278a = packageCollector;
            this.f11279b = ".db_accache";
            m13116b(context);
        }

        private String m13115a(Context context) {
            return C2178R.getCacheRoot(context) + ".db_accache";
        }

        private void m13116b(Context context) {
            if (this.f11280c == null) {
                this.f11280c = new LocalDB();
            }
            File file = new File(m13115a(context));
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (Throwable e) {
                    MobLog.getInstance().m741e(e);
                }
            }
            this.f11280c.open(m13115a(context));
        }

        public HashMap<String, HashMap<String, Object>> m13117a() {
            Object object = this.f11280c.getObject(C1154b.f5233d);
            return object == null ? new HashMap() : (HashMap) C2178R.forceCast(object);
        }

        public void m13118a(int i) {
            this.f11280c.putInt("time", Integer.valueOf(i));
        }

        public void m13119a(HashMap<String, HashMap<String, Object>> hashMap) {
            this.f11280c.putObject(C1154b.f5233d, hashMap);
        }

        public int m13120b() {
            return this.f11280c.getInt("time");
        }
    }

    static {
        f11282b = new FileLocker();
    }

    private PackageCollector(Context context, String str) {
        this.f11283c = "http://cca.mob.com:80/ca";
        this.f11284d = new String[]{"android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_CHANGED", "android.intent.action.PACKAGE_REMOVED", "android.intent.action.PACKAGE_REPLACED"};
        this.f11285e = 1;
        this.f11286f = 2;
        this.f11294n = context.getApplicationContext();
        this.f11295o = str;
        this.f11291k = new IntentFilter();
        this.f11288h = new NetworkHelper();
        this.f11292l = new C2146a(this, this.f11294n);
        this.f11287g = C2149a.m13155a(this.f11294n, this.f11295o);
        this.f11290j = DeviceHelper.getInstance(this.f11294n);
        this.f11289i = this.f11294n.getPackageManager();
        HandlerThread c2150b = new C2150b(this, "mob.pkc");
        c2150b.start();
        this.f11293m = new Handler(c2150b.getLooper(), this);
        this.f11293m.sendEmptyMessage(2);
    }

    private ArrayList<HashMap<String, Object>> m13122a(HashMap<String, HashMap<String, Object>> hashMap) {
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList();
        for (Entry value : hashMap.entrySet()) {
            arrayList.add(value.getValue());
        }
        return arrayList;
    }

    private HashMap<String, HashMap<String, Object>> m13123a() {
        HashMap<String, HashMap<String, Object>> hashMap = new HashMap();
        for (PackageInfo packageInfo : this.f11289i.getInstalledPackages(0)) {
            if (!m13124a(packageInfo.applicationInfo.flags)) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("pkg", packageInfo.packageName);
                hashMap2.put(User.FN_NAME, packageInfo.applicationInfo.loadLabel(this.f11289i).toString());
                hashMap2.put(C2537j.aq, packageInfo.versionName);
                hashMap.put(packageInfo.packageName, hashMap2);
            }
        }
        return hashMap;
    }

    private boolean m13124a(int i) {
        return ((i & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) == 0 && (i & 1) == 0) ? false : true;
    }

    private boolean m13125a(String str) {
        for (String equals : this.f11284d) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean m13126a(String str, ArrayList<HashMap<String, Object>> arrayList) {
        HashMap hashMap = new HashMap();
        hashMap.put(SocialConstants.PARAM_TYPE, str);
        hashMap.put("plat", Integer.valueOf(this.f11290j.getPlatformCode()));
        hashMap.put("device", this.f11290j.getDeviceKey());
        hashMap.put("imei", this.f11290j.getIMEI());
        hashMap.put("serialno", this.f11290j.getSerialno());
        hashMap.put("mac", this.f11290j.getMacAddress());
        hashMap.put("model", this.f11290j.getModel());
        hashMap.put("list", arrayList);
        String fromHashMap = new Hashon().fromHashMap(hashMap);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair(C2116b.f11123f, this.f11295o));
        arrayList2.add(new KVPair("m", Base64.encodeToString(Data.AES128Encode((this.f11295o + "0000000000000000").substring(0, 16), fromHashMap), 2)));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = 30000;
        try {
            fromHashMap = this.f11288h.httpPost("http://cca.mob.com:80/ca", arrayList2, null, null, networkTimeOut);
            MobLog.getInstance().m743i("> uploadApps list resp: %s", fromHashMap);
            return true;
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
            return false;
        }
    }

    private void m13128b() {
        HashMap a = m13123a();
        HashMap a2 = this.f11292l.m13117a();
        HashMap hashMap = (HashMap) C2178R.forceCast(a.clone());
        for (Entry key : a.entrySet()) {
            a2.remove(key.getKey());
        }
        if (this.f11287g.m13160d() && a2.size() > 0) {
            try {
                m13126a("UNINSTALL", m13122a(a2));
            } catch (Throwable th) {
                MobLog.getInstance().m750w(th);
            }
        }
        for (Entry key2 : this.f11292l.m13117a().entrySet()) {
            a.remove(key2.getKey());
        }
        if (this.f11287g.m13157a() && a.size() > 0) {
            try {
                m13126a("APPS_INCR", m13122a(a));
            } catch (Throwable th2) {
                MobLog.getInstance().m750w(th2);
            }
        }
        this.f11292l.m13119a(hashMap);
        m13129c();
    }

    private void m13129c() {
        int b = this.f11292l.m13120b();
        int c = this.f11287g.m13159c();
        if (b == 0 || (this.f11287g.m13158b() && System.currentTimeMillis() / 1000 > ((long) (b + c)))) {
            try {
                if (m13126a("APPS_ALL", m13122a(this.f11292l.m13117a()))) {
                    this.f11292l.m13118a((int) (System.currentTimeMillis() / 1000));
                }
            } catch (Throwable th) {
                MobLog.getInstance().m750w(th);
            }
        }
    }

    public static synchronized boolean register(Context context, String str) {
        synchronized (PackageCollector.class) {
            String str2 = C2178R.getCacheRoot(context.getApplicationContext()) + ".pkg_lock";
            File file = new File(str2);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (Throwable e) {
                    MobLog.getInstance().m750w(e);
                }
            }
            f11282b.setLockFile(str2);
            if (f11282b.lock(true)) {
                if (f11281a == null) {
                    f11281a = new PackageCollector(context, str);
                }
                unregister();
                for (String addAction : f11281a.f11284d) {
                    f11281a.f11291k.addAction(addAction);
                }
                f11281a.f11291k.addDataScheme("package");
                f11281a.f11294n.registerReceiver(f11281a, f11281a.f11291k);
            }
            f11282b.release();
        }
        return true;
    }

    public static synchronized void unregister() {
        synchronized (PackageCollector.class) {
            if (f11281a != null) {
                try {
                    f11281a.f11294n.unregisterReceiver(f11281a);
                } catch (Throwable th) {
                    MobLog.getInstance().m750w(th);
                }
            }
        }
    }

    public boolean handleMessage(Message message) {
        if (1 == message.what) {
            try {
                m13128b();
            } catch (Throwable th) {
                MobLog.getInstance().m750w(th);
            }
        } else if (2 == message.what) {
            m13129c();
            this.f11293m.sendEmptyMessageDelayed(message.what, MiStatInterface.MIN_UPLOAD_INTERVAL);
        }
        return false;
    }

    public void onReceive(Context context, Intent intent) {
        String str = null;
        if (intent != null) {
            str = intent.getAction();
        }
        if (m13125a(str)) {
            this.f11293m.removeMessages(1);
            this.f11293m.sendEmptyMessageDelayed(1, 5000);
        }
    }
}
