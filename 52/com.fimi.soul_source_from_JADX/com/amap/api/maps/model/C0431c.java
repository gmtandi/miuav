package com.amap.api.maps.model;

import com.amap.api.mapcore.util.ay;
import com.autonavi.amap.mapcore.DPoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: com.amap.api.maps.model.c */
class C0431c {
    private final ay f2808a;
    private final int f2809b;
    private List<WeightedLatLng> f2810c;
    private List<C0431c> f2811d;

    private C0431c(double d, double d2, double d3, double d4, int i) {
        this(new ay(d, d2, d3, d4), i);
    }

    protected C0431c(ay ayVar) {
        this(ayVar, 0);
    }

    private C0431c(ay ayVar, int i) {
        this.f2811d = null;
        this.f2808a = ayVar;
        this.f2809b = i;
    }

    private void m4352a() {
        this.f2811d = new ArrayList(4);
        this.f2811d.add(new C0431c(this.f2808a.f2142a, this.f2808a.f2146e, this.f2808a.f2143b, this.f2808a.f2147f, this.f2809b + 1));
        this.f2811d.add(new C0431c(this.f2808a.f2146e, this.f2808a.f2144c, this.f2808a.f2143b, this.f2808a.f2147f, this.f2809b + 1));
        this.f2811d.add(new C0431c(this.f2808a.f2142a, this.f2808a.f2146e, this.f2808a.f2147f, this.f2808a.f2145d, this.f2809b + 1));
        this.f2811d.add(new C0431c(this.f2808a.f2146e, this.f2808a.f2144c, this.f2808a.f2147f, this.f2808a.f2145d, this.f2809b + 1));
        List<WeightedLatLng> list = this.f2810c;
        this.f2810c = null;
        for (WeightedLatLng weightedLatLng : list) {
            m4353a(weightedLatLng.getPoint().f3691x, weightedLatLng.getPoint().f3692y, weightedLatLng);
        }
    }

    private void m4353a(double d, double d2, WeightedLatLng weightedLatLng) {
        if (this.f2811d == null) {
            if (this.f2810c == null) {
                this.f2810c = new ArrayList();
            }
            this.f2810c.add(weightedLatLng);
            if (this.f2810c.size() > 50 && this.f2809b < 40) {
                m4352a();
            }
        } else if (d2 < this.f2808a.f2147f) {
            if (d < this.f2808a.f2146e) {
                ((C0431c) this.f2811d.get(0)).m4353a(d, d2, weightedLatLng);
            } else {
                ((C0431c) this.f2811d.get(1)).m4353a(d, d2, weightedLatLng);
            }
        } else if (d < this.f2808a.f2146e) {
            ((C0431c) this.f2811d.get(2)).m4353a(d, d2, weightedLatLng);
        } else {
            ((C0431c) this.f2811d.get(3)).m4353a(d, d2, weightedLatLng);
        }
    }

    private void m4354a(ay ayVar, Collection<WeightedLatLng> collection) {
        if (!this.f2808a.m3491a(ayVar)) {
            return;
        }
        if (this.f2811d != null) {
            for (C0431c a : this.f2811d) {
                a.m4354a(ayVar, collection);
            }
        } else if (this.f2810c == null) {
        } else {
            if (ayVar.m3493b(this.f2808a)) {
                collection.addAll(this.f2810c);
                return;
            }
            for (WeightedLatLng weightedLatLng : this.f2810c) {
                if (ayVar.m3492a(weightedLatLng.getPoint())) {
                    collection.add(weightedLatLng);
                }
            }
        }
    }

    protected Collection<WeightedLatLng> m4355a(ay ayVar) {
        Collection<WeightedLatLng> arrayList = new ArrayList();
        m4354a(ayVar, arrayList);
        return arrayList;
    }

    protected void m4356a(WeightedLatLng weightedLatLng) {
        DPoint point = weightedLatLng.getPoint();
        if (this.f2808a.m3489a(point.f3691x, point.f3692y)) {
            m4353a(point.f3691x, point.f3692y, weightedLatLng);
        }
    }
}
