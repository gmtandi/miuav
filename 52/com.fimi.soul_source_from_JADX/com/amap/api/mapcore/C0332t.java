package com.amap.api.mapcore;

import com.amap.api.mapcore.util.ce;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.t */
class C0332t {
    C0331a f2076a;
    private CopyOnWriteArrayList<C0408v> f2077b;

    /* renamed from: com.amap.api.mapcore.t.a */
    class C0331a implements Serializable, Comparator<Object> {
        C0331a() {
        }

        public int compare(Object obj, Object obj2) {
            C0408v c0408v = (C0408v) obj;
            C0408v c0408v2 = (C0408v) obj2;
            if (!(c0408v == null || c0408v2 == null)) {
                try {
                    if (c0408v.getZIndex() > c0408v2.getZIndex()) {
                        return 1;
                    }
                    if (c0408v.getZIndex() < c0408v2.getZIndex()) {
                        return -1;
                    }
                } catch (Throwable th) {
                    ce.m3829a(th, "CustomGLOverlayLayer", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }

    C0332t() {
        this.f2077b = new CopyOnWriteArrayList();
        this.f2076a = new C0331a();
    }

    public void m3339a(GL10 gl10) {
        Iterator it = this.f2077b.iterator();
        while (it.hasNext()) {
            ((C0408v) it.next()).onDrawFrame(gl10);
        }
    }

    public boolean m3340a(C0408v c0408v) {
        return this.f2077b.contains(c0408v) ? this.f2077b.remove(c0408v) : false;
    }
}
