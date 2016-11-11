package com.amap.api.mapcore;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.model.Marker;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import it.p074a.p075a.C2799f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

class aw extends View {
    ab f1631a;
    C0301a f1632b;
    private CopyOnWriteArrayList<ah> f1633c;
    private CopyOnWriteArrayList<be> f1634d;
    private CopyOnWriteArrayList<Integer> f1635e;
    private IPoint f1636f;
    private ah f1637g;
    private Handler f1638h;
    private Runnable f1639i;
    private final Handler f1640j;
    private final Runnable f1641k;

    /* renamed from: com.amap.api.mapcore.aw.a */
    class C0301a implements Serializable, Comparator<Object> {
        C0301a() {
        }

        public int compare(Object obj, Object obj2) {
            ah ahVar = (ah) obj;
            ah ahVar2 = (ah) obj2;
            if (!(ahVar == null || ahVar2 == null)) {
                try {
                    if (ahVar.m2085G() > ahVar2.m2085G()) {
                        return 1;
                    }
                    if (ahVar.m2085G() < ahVar2.m2085G()) {
                        return -1;
                    }
                } catch (Throwable th) {
                    ce.m3829a(th, "MapOverlayImageView", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }

    public aw(Context context) {
        super(context);
        this.f1633c = new CopyOnWriteArrayList(new ArrayList(C2799f.f14263a));
        this.f1634d = new CopyOnWriteArrayList();
        this.f1635e = new CopyOnWriteArrayList();
        this.f1632b = new C0301a();
        this.f1638h = new Handler();
        this.f1639i = new ax(this);
        this.f1640j = new Handler();
        this.f1641k = new ay(this);
    }

    public aw(Context context, AttributeSet attributeSet, ab abVar) {
        super(context, attributeSet);
        this.f1633c = new CopyOnWriteArrayList(new ArrayList(C2799f.f14263a));
        this.f1634d = new CopyOnWriteArrayList();
        this.f1635e = new CopyOnWriteArrayList();
        this.f1632b = new C0301a();
        this.f1638h = new Handler();
        this.f1639i = new ax(this);
        this.f1640j = new Handler();
        this.f1641k = new ay(this);
        this.f1631a = abVar;
    }

    private void m2803k() {
        try {
            Collection arrayList = new ArrayList(this.f1633c);
            Collections.sort(arrayList, this.f1632b);
            this.f1633c = new CopyOnWriteArrayList(arrayList);
        } catch (Throwable th) {
            ce.m3829a(th, "MapOverlayImageView", "changeOverlayIndex");
        }
    }

    protected synchronized int m2804a() {
        return this.f1633c.size();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int m2805a(com.amap.api.maps.model.BitmapDescriptor r5) {
        /*
        r4 = this;
        r1 = 0;
        monitor-enter(r4);
        if (r5 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r5.getBitmap();	 Catch:{ all -> 0x003d }
        if (r0 == 0) goto L_0x0014;
    L_0x000a:
        r0 = r5.getBitmap();	 Catch:{ all -> 0x003d }
        r0 = r0.isRecycled();	 Catch:{ all -> 0x003d }
        if (r0 == 0) goto L_0x0017;
    L_0x0014:
        r0 = r1;
    L_0x0015:
        monitor-exit(r4);
        return r0;
    L_0x0017:
        r2 = r1;
    L_0x0018:
        r0 = r4.f1634d;	 Catch:{ all -> 0x003d }
        r0 = r0.size();	 Catch:{ all -> 0x003d }
        if (r2 >= r0) goto L_0x003b;
    L_0x0020:
        r0 = r4.f1634d;	 Catch:{ all -> 0x003d }
        r0 = r0.get(r2);	 Catch:{ all -> 0x003d }
        r0 = (com.amap.api.mapcore.be) r0;	 Catch:{ all -> 0x003d }
        r3 = r0.m2944a();	 Catch:{ all -> 0x003d }
        r3 = r3.equals(r5);	 Catch:{ all -> 0x003d }
        if (r3 == 0) goto L_0x0037;
    L_0x0032:
        r0 = r0.m2945b();	 Catch:{ all -> 0x003d }
        goto L_0x0015;
    L_0x0037:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0018;
    L_0x003b:
        r0 = r1;
        goto L_0x0015;
    L_0x003d:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.aw.a(com.amap.api.maps.model.BitmapDescriptor):int");
    }

    public ah m2806a(MotionEvent motionEvent) {
        Iterator it = this.f1633c.iterator();
        while (it.hasNext()) {
            ah ahVar = (ah) it.next();
            if ((ahVar instanceof ba) && m2813a(ahVar.m2108d(), (int) motionEvent.getX(), (int) motionEvent.getY())) {
                this.f1637g = ahVar;
                return this.f1637g;
            }
        }
        return null;
    }

    public synchronized void m2807a(int i) {
        Iterator it = this.f1634d.iterator();
        while (it.hasNext()) {
            be beVar = (be) it.next();
            if (beVar.m2945b() == i) {
                this.f1634d.remove(beVar);
            }
        }
    }

    public synchronized void m2808a(ah ahVar) {
        this.f1633c.add(ahVar);
        m2825h();
    }

    public synchronized void m2809a(be beVar) {
        if (beVar != null) {
            if (beVar.m2945b() != 0) {
                this.f1634d.add(beVar);
            }
        }
    }

    public void m2810a(Integer num) {
        if (num.intValue() != 0) {
            this.f1635e.add(num);
        }
    }

    public synchronized void m2811a(String str) {
        Object obj;
        Iterator it;
        ah ahVar;
        if (str != null) {
            try {
                if (str.trim().length() != 0) {
                    obj = null;
                    this.f1637g = null;
                    this.f1636f = null;
                    if (obj == null) {
                        it = this.f1633c.iterator();
                        while (it.hasNext()) {
                            ((ah) it.next()).m2105b();
                        }
                        this.f1633c.clear();
                    } else {
                        it = this.f1633c.iterator();
                        while (it.hasNext()) {
                            ahVar = (ah) it.next();
                            if (!str.equals(ahVar.m2114h())) {
                                ahVar.m2105b();
                            }
                        }
                    }
                }
            } catch (Throwable e) {
                ce.m3829a(e, "MapOverlayImageView", "clear");
                e.printStackTrace();
            }
        }
        obj = 1;
        this.f1637g = null;
        this.f1636f = null;
        if (obj == null) {
            it = this.f1633c.iterator();
            while (it.hasNext()) {
                ahVar = (ah) it.next();
                if (!str.equals(ahVar.m2114h())) {
                    ahVar.m2105b();
                }
            }
        } else {
            it = this.f1633c.iterator();
            while (it.hasNext()) {
                ((ah) it.next()).m2105b();
            }
            this.f1633c.clear();
        }
    }

    public void m2812a(GL10 gl10) {
        Iterator it = this.f1635e.iterator();
        while (it.hasNext()) {
            gl10.glDeleteTextures(1, new int[]{((Integer) it.next()).intValue()}, 0);
            this.f1631a.m2298f(r0.intValue());
        }
        this.f1635e.clear();
        if (!(this.f1637g == null || this.f1637g.m2084F())) {
            m2827j();
        }
        it = this.f1633c.iterator();
        while (it.hasNext()) {
            ah ahVar = (ah) it.next();
            if (ahVar.m2086H() || ahVar.m2120n()) {
                ahVar.m2099a(gl10, this.f1631a);
            }
        }
    }

    public boolean m2813a(Rect rect, int i, int i2) {
        return rect.contains(i, i2);
    }

    public synchronized void m2814b() {
        Iterator it = this.f1633c.iterator();
        while (it.hasNext()) {
            ah ahVar = (ah) it.next();
            try {
                if (ahVar.m2121o()) {
                    ahVar.m2124r();
                }
            } catch (Throwable th) {
                ce.m3829a(th, "MapOverlayImageView", "calFPoint");
                th.printStackTrace();
            }
        }
    }

    public boolean m2815b(MotionEvent motionEvent) {
        Iterator it = this.f1633c.iterator();
        while (it.hasNext()) {
            ah ahVar = (ah) it.next();
            if ((ahVar instanceof ba) && ahVar.m2121o()) {
                Rect d = ahVar.m2108d();
                boolean a = m2813a(d, (int) motionEvent.getX(), (int) motionEvent.getY());
                if (a) {
                    this.f1636f = new IPoint(d.left + (d.width() / 2), d.top);
                    this.f1637g = ahVar;
                    return a;
                }
            }
        }
        return false;
    }

    public synchronized boolean m2816b(ah ahVar) {
        m2822e(ahVar);
        return this.f1633c.remove(ahVar);
    }

    public synchronized void m2817c(ah ahVar) {
        try {
            if (this.f1633c.remove(ahVar)) {
                m2803k();
                this.f1633c.add(ahVar);
            }
        } catch (Throwable th) {
            ce.m3829a(th, "MapOverlayImageView", "set2Top");
        }
    }

    public synchronized boolean m2818c() {
        boolean z;
        Iterator it = this.f1633c.iterator();
        while (it.hasNext()) {
            if (!((ah) it.next()).m2107c()) {
                z = false;
                break;
            }
        }
        z = true;
        return z;
    }

    public ah m2819d() {
        return this.f1637g;
    }

    public void m2820d(ah ahVar) {
        if (this.f1636f == null) {
            this.f1636f = new IPoint();
        }
        Rect d = ahVar.m2108d();
        this.f1636f = new IPoint(d.left + (d.width() / 2), d.top);
        this.f1637g = ahVar;
        try {
            this.f1631a.m2252a(this.f1637g);
        } catch (Throwable th) {
            ce.m3829a(th, "MapOverlayImageView", "showInfoWindow");
            th.printStackTrace();
        }
    }

    public synchronized void m2821e() {
        try {
            Iterator it = this.f1633c.iterator();
            while (it.hasNext()) {
                ah ahVar = (ah) it.next();
                if (ahVar != null) {
                    ahVar.m2122p();
                }
            }
            m2811a(null);
            it = this.f1634d.iterator();
            while (it.hasNext()) {
                ((be) it.next()).m2944a().recycle();
            }
            this.f1634d.clear();
        } catch (Throwable th) {
            ce.m3829a(th, "MapOverlayImageView", "destroy");
            th.printStackTrace();
            Log.d("amapApi", "MapOverlayImageView clear erro" + th.getMessage());
        }
    }

    public void m2822e(ah ahVar) {
        try {
            if (ahVar.m2120n()) {
                this.f1631a.m2216E();
                this.f1637g = null;
            } else if (this.f1637g != null && this.f1637g.m2114h() == ahVar.m2114h()) {
                this.f1637g = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized List<Marker> m2823f() {
        List<Marker> arrayList;
        arrayList = new ArrayList();
        try {
            Rect rect = new Rect(0, 0, this.f1631a.m2311l(), this.f1631a.m2313m());
            IPoint iPoint = new IPoint();
            Iterator it = this.f1633c.iterator();
            while (it.hasNext()) {
                ah ahVar = (ah) it.next();
                if (!(ahVar instanceof bl)) {
                    FPoint f = ahVar.m2112f();
                    if (f != null) {
                        this.f1631a.m2289c().map2Win(f.f3693x, f.f3694y, iPoint);
                        if (m2813a(rect, iPoint.f3714x, iPoint.f3715y)) {
                            arrayList.add(new Marker(ahVar));
                        }
                    }
                }
            }
        } catch (Throwable th) {
            ce.m3829a(th, "MapOverlayImageView", "getMapScreenMarkers");
            th.printStackTrace();
        }
        return arrayList;
    }

    public synchronized void m2824g() {
        Iterator it = this.f1633c.iterator();
        while (it.hasNext()) {
            ah ahVar = (ah) it.next();
            if (ahVar.m2130x()) {
                ahVar.m2131y();
            }
        }
    }

    protected synchronized void m2825h() {
        this.f1638h.removeCallbacks(this.f1639i);
        this.f1638h.postDelayed(this.f1639i, 10);
    }

    public void m2826i() {
        Iterator it = this.f1633c.iterator();
        while (it.hasNext()) {
            ah ahVar = (ah) it.next();
            if (ahVar != null) {
                ahVar.m2088J();
            }
        }
        if (this.f1634d != null) {
            this.f1634d.clear();
        }
    }

    public void m2827j() {
        this.f1640j.post(this.f1641k);
    }
}
