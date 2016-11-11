package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.p122a.p123a.C2915a;

/* renamed from: com.amap.api.mapcore.util.i */
public class C0391i {
    public static String f2484a;
    public static boolean f2485b;
    public static String f2486d;
    private static volatile C0391i f2487j;
    CopyOnWriteArrayList<C0385g> f2488c;
    C0390b f2489e;
    public C0395m f2490f;
    C0397o f2491g;
    C0394l f2492h;
    private Context f2493i;
    private C0389a f2494k;
    private C0399r f2495l;
    private C0405x f2496m;
    private ExecutorService f2497n;
    private ExecutorService f2498o;

    /* renamed from: com.amap.api.mapcore.util.i.1 */
    class C03871 implements Runnable {
        final /* synthetic */ String f2479a;
        final /* synthetic */ C0391i f2480b;

        C03871(C0391i c0391i, String str) {
            this.f2480b = c0391i;
            this.f2479a = str;
        }

        public void run() {
            C0385g a = this.f2480b.m4113g(this.f2479a);
            try {
                if (a.m4076c().equals(a.f2470f)) {
                    String adcode = a.getAdcode();
                    if (adcode.length() > 0) {
                        adcode = this.f2480b.f2496m.m4237e(adcode);
                        if (C0391i.f2486d.length() > 0 && !adcode.equals(C0391i.f2486d)) {
                            a.m4082i();
                            this.f2480b.f2494k.m4103b(a);
                            return;
                        }
                    }
                    this.f2480b.m4117i();
                    C0392j c0392j = (C0392j) new C0393k(this.f2480b.f2493i, C0391i.f2486d).m3448d();
                    if (this.f2480b.f2494k != null) {
                        if (c0392j == null) {
                            this.f2480b.f2494k.m4103b(a);
                            return;
                        } else if (c0392j.m4137a()) {
                            this.f2480b.m4118a();
                        }
                    }
                    this.f2480b.f2494k.m4103b(a);
                }
            } catch (Exception e) {
            } finally {
                this.f2480b.f2494k.m4103b(a);
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.util.i.2 */
    class C03882 implements Runnable {
        final /* synthetic */ C0385g f2481a;
        final /* synthetic */ C0391i f2482b;

        C03882(C0391i c0391i, C0385g c0385g) {
            this.f2482b = c0391i;
            this.f2481a = c0385g;
        }

        public void run() {
            if (this.f2481a.m4076c().equals(this.f2481a.f2465a)) {
                this.f2482b.f2494k.m4104c(this.f2481a);
            } else if (this.f2481a.getState() == 7 || this.f2481a.getState() == -1) {
                this.f2482b.f2491g.m4177a(this.f2481a);
            } else {
                this.f2482b.f2491g.m4177a(this.f2481a);
                this.f2482b.f2494k.m4104c(this.f2481a);
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.util.i.a */
    public interface C0389a {
        void m4102a(C0385g c0385g);

        void m4103b(C0385g c0385g);

        void m4104c(C0385g c0385g);
    }

    /* renamed from: com.amap.api.mapcore.util.i.b */
    class C0390b extends Handler {
        final /* synthetic */ C0391i f2483a;

        public C0390b(C0391i c0391i, Looper looper) {
            this.f2483a = c0391i;
            super(looper);
        }

        public void handleMessage(Message message) {
            try {
                message.getData();
                Object obj = message.obj;
                if (obj instanceof C0385g) {
                    C0385g c0385g = (C0385g) obj;
                    af.m3415a("OfflineMapHandler handleMessage CitObj  name: " + c0385g.getCity() + " complete: " + c0385g.getcompleteCode() + " status: " + c0385g.getState());
                    if (this.f2483a.f2494k != null) {
                        this.f2483a.f2494k.m4102a(c0385g);
                        return;
                    }
                    return;
                }
                af.m3415a("Do not callback by CityObject! ");
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    static {
        f2484a = C2915a.f14760f;
        f2485b = false;
        f2486d = C2915a.f14760f;
    }

    private C0391i(Context context) {
        this.f2488c = new CopyOnWriteArrayList();
        this.f2497n = null;
        this.f2498o = null;
        this.f2489e = null;
        this.f2492h = null;
        this.f2493i = context;
        m4111f();
    }

    public static C0391i m4106a(Context context) {
        if (f2487j == null) {
            synchronized (C0391i.class) {
                if (f2487j == null && !f2485b) {
                    f2487j = new C0391i(context.getApplicationContext());
                }
            }
        }
        return f2487j;
    }

    private void m4111f() {
        this.f2496m = C0405x.m4223a(this.f2493i.getApplicationContext());
        this.f2489e = new C0390b(this, this.f2493i.getMainLooper());
        this.f2490f = new C0395m(this.f2493i, this.f2489e);
        this.f2495l = C0399r.m4186a(1);
        f2484a = bj.m3634b(this.f2493i);
        m4114g();
        this.f2492h = new C0394l(this.f2493i);
        this.f2492h.start();
        Iterator it = this.f2490f.m4156a().iterator();
        while (it.hasNext()) {
            Iterator it2 = ((OfflineMapProvince) it.next()).getCityList().iterator();
            while (it2.hasNext()) {
                this.f2488c.add(new C0385g(this.f2493i, (OfflineMapCity) it2.next()));
            }
        }
        m4116h();
    }

    private void m4112f(String str) {
        List b = af.m3418b(str);
        if (b != null && b.size() != 0) {
            this.f2490f.m4158a(b);
        }
    }

    private C0385g m4113g(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        Iterator it = this.f2488c.iterator();
        while (it.hasNext()) {
            C0385g c0385g = (C0385g) it.next();
            if (str.equals(c0385g.getCity())) {
                return c0385g;
            }
        }
        return null;
    }

    private void m4114g() {
        if (!bj.m3634b(this.f2493i).equals(C2915a.f14760f)) {
            File file = new File(bj.m3634b(this.f2493i) + "offlinemapv4.png");
            String a = !file.exists() ? af.m3414a(this.f2493i, "offlinemapv4.png") : af.m3421c(file);
            if (a != null) {
                try {
                    m4112f(a);
                } catch (Throwable e) {
                    ce.m3829a(e, "MapDownloadManager", "paseJson io");
                    e.printStackTrace();
                }
            }
        }
    }

    private C0385g m4115h(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        Iterator it = this.f2488c.iterator();
        while (it.hasNext()) {
            C0385g c0385g = (C0385g) it.next();
            if (str.equals(c0385g.getCode())) {
                return c0385g;
            }
        }
        return null;
    }

    private void m4116h() {
        Iterator it = this.f2496m.m4228a().iterator();
        while (it.hasNext()) {
            C0401s c0401s = (C0401s) it.next();
            if (!(c0401s == null || c0401s.m4197e() == null || c0401s.m4199g().length() < 1)) {
                if (!(c0401s.l == 4 || c0401s.l == 7 || c0401s.l < 0)) {
                    c0401s.l = 3;
                }
                C0385g g = m4113g(c0401s.m4197e());
                if (g != null) {
                    String f = c0401s.m4198f();
                    if (f == null || f.equals(f2486d)) {
                        g.m4067a(c0401s.l);
                        g.setCompleteCode(c0401s.m4202j());
                    } else {
                        this.f2496m.m4235c(c0401s.m4199g());
                        g.m4067a(7);
                    }
                    List<String> a = this.f2496m.m4229a(c0401s.m4199g());
                    StringBuffer stringBuffer = new StringBuffer();
                    for (String append : a) {
                        stringBuffer.append(append);
                        stringBuffer.append(";");
                    }
                    g.m4073a(stringBuffer.toString());
                    this.f2490f.m4157a(g);
                }
            }
        }
    }

    private void m4117i() {
        if (!bj.m3640c(this.f2493i)) {
            throw new AMapException(com.amap.api.services.core.AMapException.ERROR_CONNECTION);
        }
    }

    protected void m4118a() {
        C0398p c0398p = new C0398p(this.f2493i, C2915a.f14760f);
        c0398p.m4182a(this.f2493i);
        List list = (List) c0398p.m3448d();
        if (this.f2488c != null) {
            this.f2490f.m4158a(list);
        }
        Iterator it = this.f2488c.iterator();
        while (it.hasNext()) {
            C0385g c0385g = (C0385g) it.next();
            String version = c0385g.getVersion();
            if (c0385g.getState() == 4 && f2486d.length() > 0 && !version.equals(f2486d)) {
                c0385g.m4082i();
            }
        }
    }

    public void m4119a(C0385g c0385g) {
        if (this.f2491g == null) {
            this.f2491g = new C0397o(this.f2493i);
        }
        if (this.f2498o == null) {
            this.f2498o = Executors.newSingleThreadExecutor();
        }
        this.f2498o.execute(new C03882(this, c0385g));
    }

    public void m4120a(C0389a c0389a) {
        this.f2494k = c0389a;
    }

    public void m4121a(String str) {
        if (str != null) {
            if (this.f2497n == null) {
                this.f2497n = Executors.newSingleThreadExecutor();
            }
            this.f2497n.execute(new C03871(this, str));
        } else if (this.f2494k != null) {
            this.f2494k.m4103b(null);
        }
    }

    public void m4122a(ArrayList<C0401s> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C0401s c0401s = (C0401s) it.next();
            C0385g g = m4113g(c0401s.m4197e());
            if (g != null) {
                g.m4072a(c0401s);
                m4127c(g);
            }
        }
    }

    public void m4123b() {
        Iterator it = this.f2488c.iterator();
        while (it.hasNext()) {
            C0385g c0385g = (C0385g) it.next();
            if (c0385g.m4076c().equals(c0385g.f2467c) || c0385g.m4076c().equals(c0385g.f2466b)) {
                c0385g.m4079f();
            }
        }
    }

    public void m4124b(C0385g c0385g) {
        try {
            this.f2495l.m4190a(c0385g, this.f2493i, null);
        } catch (bk e) {
            e.printStackTrace();
        }
    }

    public boolean m4125b(String str) {
        return m4113g(str) != null;
    }

    public void m4126c() {
        Iterator it = this.f2488c.iterator();
        while (it.hasNext()) {
            C0385g c0385g = (C0385g) it.next();
            if (c0385g.m4076c().equals(c0385g.f2467c)) {
                c0385g.m4079f();
                return;
            }
        }
    }

    public void m4127c(C0385g c0385g) {
        this.f2490f.m4157a(c0385g);
        Message obtainMessage = this.f2489e.obtainMessage();
        obtainMessage.obj = c0385g;
        this.f2489e.sendMessage(obtainMessage);
    }

    public void m4128c(String str) {
        C0385g g = m4113g(str);
        if (g != null) {
            m4130d(g);
            m4119a(g);
        } else if (this.f2494k != null) {
            this.f2494k.m4104c(g);
        }
    }

    public void m4129d() {
        if (!(this.f2497n == null || this.f2497n.isShutdown())) {
            this.f2497n.shutdownNow();
        }
        if (this.f2492h != null) {
            if (this.f2492h.isAlive()) {
                this.f2492h.interrupt();
            }
            this.f2492h = null;
        }
        this.f2495l.m4191b();
        this.f2490f.m4167g();
        m4132e();
        f2487j = null;
        f2485b = true;
    }

    public void m4130d(C0385g c0385g) {
        this.f2495l.m4189a((C0384q) c0385g);
    }

    public void m4131d(String str) {
        C0385g g = m4113g(str);
        if (g != null) {
            g.setVersion(f2486d);
            g.m4079f();
            return;
        }
        throw new AMapException(com.amap.api.services.core.AMapException.ERROR_INVALID_PARAMETER);
    }

    public void m4132e() {
        this.f2494k = null;
    }

    public void m4133e(C0385g c0385g) {
        this.f2495l.m4192b(c0385g);
    }

    public void m4134e(String str) {
        C0385g h = m4115h(str);
        if (h != null) {
            h.m4079f();
            return;
        }
        throw new AMapException(com.amap.api.services.core.AMapException.ERROR_INVALID_PARAMETER);
    }
}
