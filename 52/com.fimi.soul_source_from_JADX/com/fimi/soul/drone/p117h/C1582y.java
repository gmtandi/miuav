package com.fimi.soul.drone.p117h;

import com.amap.api.maps.model.LatLng;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.fimi.soul.drone.h.y */
public class C1582y extends C1495k {
    List<LatLng> f7623b;

    public C1582y(C1433a c1433a) {
        super(c1433a);
        this.f7623b = new ArrayList();
    }

    private void m10624b() {
        this.a.m9589a(C1584h.UPDATELINE);
    }

    public List<LatLng> m10625a() {
        return this.f7623b;
    }

    public void m10626a(LatLng latLng) {
        this.f7623b.remove(latLng);
        m10624b();
    }

    public void m10627a(List<LatLng> list) {
        for (LatLng add : list) {
            this.f7623b.add(add);
        }
        m10624b();
    }

    public void m10628b(List<LatLng> list) {
        list.removeAll(list);
    }
}
