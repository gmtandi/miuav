package com.amap.api.mapcore;

import android.os.Handler;
import android.util.Log;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.model.LatLng;
import it.p074a.p075a.C2799f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.w */
class C0410w {
    private static int f2547c;
    ab f2548a;
    C0409a f2549b;
    private CopyOnWriteArrayList<aj> f2550d;
    private CopyOnWriteArrayList<Integer> f2551e;
    private Handler f2552f;
    private Runnable f2553g;

    /* renamed from: com.amap.api.mapcore.w.a */
    class C0409a implements Serializable, Comparator<Object> {
        C0409a() {
        }

        public int compare(Object obj, Object obj2) {
            aj ajVar = (aj) obj;
            aj ajVar2 = (aj) obj2;
            if (!(ajVar == null || ajVar2 == null)) {
                try {
                    if (ajVar.m2575d() > ajVar2.m2575d()) {
                        return 1;
                    }
                    if (ajVar.m2575d() < ajVar2.m2575d()) {
                        return -1;
                    }
                } catch (Throwable th) {
                    ce.m3829a(th, "GLOverlayLayer", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }

    static {
        f2547c = 0;
    }

    public C0410w(ab abVar) {
        this.f2550d = new CopyOnWriteArrayList(new ArrayList(C2799f.f14263a));
        this.f2551e = new CopyOnWriteArrayList();
        this.f2552f = new Handler();
        this.f2553g = new C0411x(this);
        this.f2549b = new C0409a();
        this.f2548a = abVar;
    }

    static String m4243a(String str) {
        f2547c++;
        return str + f2547c;
    }

    private synchronized aj m4246d(String str) {
        aj ajVar;
        Iterator it = this.f2550d.iterator();
        while (it.hasNext()) {
            ajVar = (aj) it.next();
            if (ajVar != null && ajVar.m2574c().equals(str)) {
                break;
            }
        }
        ajVar = null;
        return ajVar;
    }

    public synchronized aj m4247a(LatLng latLng) {
        aj ajVar;
        Iterator it = this.f2550d.iterator();
        while (it.hasNext()) {
            ajVar = (aj) it.next();
            if (ajVar != null && ajVar.m2580k() && (ajVar instanceof al)) {
                if (((al) ajVar).m2679b(latLng)) {
                    break;
                }
            }
        }
        ajVar = null;
        return ajVar;
    }

    public synchronized void m4248a() {
        try {
            Iterator it = this.f2550d.iterator();
            while (it.hasNext()) {
                ((aj) it.next()).m2579j();
            }
            m4253b(null);
        } catch (Throwable th) {
            ce.m3829a(th, "GLOverlayLayer", "destory");
            th.printStackTrace();
            Log.d("amapApi", "GLOverlayLayer destory erro" + th.getMessage());
        }
    }

    public synchronized void m4249a(aj ajVar) {
        this.f2550d.add(ajVar);
        m4252b();
    }

    public void m4250a(Integer num) {
        if (num.intValue() != 0) {
            this.f2551e.add(num);
        }
    }

    public void m4251a(GL10 gl10, boolean z, int i) {
        Iterator it = this.f2551e.iterator();
        while (it.hasNext()) {
            gl10.glDeleteTextures(1, new int[]{((Integer) it.next()).intValue()}, 0);
            this.f2548a.m2298f(r0.intValue());
        }
        this.f2551e.clear();
        int size = this.f2550d.size();
        Iterator it2 = this.f2550d.iterator();
        while (it2.hasNext()) {
            aj ajVar = (aj) it2.next();
            try {
                if (ajVar.m2576e()) {
                    if (size > 20) {
                        if (ajVar.m2571a()) {
                            if (z) {
                                if (ajVar.m2575d() <= ((float) i)) {
                                    ajVar.m2569a(gl10);
                                }
                            } else if (ajVar.m2575d() > ((float) i)) {
                                ajVar.m2569a(gl10);
                            }
                        }
                    } else if (z) {
                        if (ajVar.m2575d() <= ((float) i)) {
                            ajVar.m2569a(gl10);
                        }
                    } else if (ajVar.m2575d() > ((float) i)) {
                        ajVar.m2569a(gl10);
                    }
                }
            } catch (Throwable e) {
                ce.m3829a(e, "GLOverlayLayer", "draw");
                e.printStackTrace();
            }
        }
    }

    protected synchronized void m4252b() {
        this.f2552f.removeCallbacks(this.f2553g);
        this.f2552f.postDelayed(this.f2553g, 10);
    }

    public synchronized void m4253b(String str) {
        if (str != null) {
            try {
                if (str.trim().length() != 0) {
                    Iterator it = this.f2550d.iterator();
                    while (it.hasNext()) {
                        aj ajVar = (aj) it.next();
                        if (!str.equals(ajVar.m2574c())) {
                            this.f2550d.remove(ajVar);
                        }
                    }
                }
            } catch (Throwable th) {
                ce.m3829a(th, "GLOverlayLayer", "clear");
                th.printStackTrace();
                Log.d("amapApi", "GLOverlayLayer clear erro" + th.getMessage());
            }
        }
        this.f2550d.clear();
        f2547c = 0;
    }

    public synchronized void m4254c() {
        Iterator it = this.f2550d.iterator();
        while (it.hasNext()) {
            aj ajVar = (aj) it.next();
            if (ajVar != null) {
                try {
                    ajVar.m2578g();
                } catch (Throwable e) {
                    ce.m3829a(e, "GLOverlayLayer", "calMapFPoint");
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized boolean m4255c(String str) {
        aj d;
        d = m4246d(str);
        return d != null ? this.f2550d.remove(d) : false;
    }

    public boolean m4256d() {
        Iterator it = this.f2550d.iterator();
        while (it.hasNext()) {
            aj ajVar = (aj) it.next();
            if (ajVar != null && !ajVar.m2580k()) {
                return false;
            }
        }
        return true;
    }

    public void m4257e() {
        Iterator it = this.f2550d.iterator();
        while (it.hasNext()) {
            aj ajVar = (aj) it.next();
            if (ajVar != null) {
                if (ajVar instanceof al) {
                    ((al) ajVar).m2687o();
                } else if (ajVar instanceof af) {
                    ((af) ajVar).m2594p();
                }
            }
        }
    }
}
