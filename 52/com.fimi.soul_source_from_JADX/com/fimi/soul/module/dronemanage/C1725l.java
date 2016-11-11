package com.fimi.soul.module.dronemanage;

import android.graphics.Point;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.fimi.soul.module.dronemanage.l */
public class C1725l {
    private AMap f8497a;
    private List<Polyline> f8498b;
    private Polyline f8499c;

    public C1725l(AMap aMap) {
        this.f8498b = new ArrayList();
        this.f8497a = aMap;
    }

    public void m11250a() {
        for (Polyline remove : this.f8498b) {
            remove.remove();
        }
    }

    public void m11251a(LatLng latLng, LatLng latLng2) {
        m11250a();
        int i = this.f8497a.getProjection().toScreenLocation(latLng).x;
        int i2 = this.f8497a.getProjection().toScreenLocation(latLng).y;
        int i3 = this.f8497a.getProjection().toScreenLocation(latLng2).x;
        int i4 = this.f8497a.getProjection().toScreenLocation(latLng2).y;
        double atan = Math.atan(10.0d / 20.0d);
        double sqrt = Math.sqrt((20.0d * 20.0d) + (10.0d * 10.0d));
        double[] a = m11252a(i3 - i, i4 - i2, atan, sqrt);
        double[] a2 = m11252a(i3 - i, i4 - i2, -atan, sqrt);
        sqrt = ((double) i3) - a2[0];
        double d = ((double) i4) - a2[1];
        Point point = new Point((int) (((double) i3) - a[0]), (int) (((double) i4) - a[1]));
        Point point2 = new Point((int) sqrt, (int) d);
        LatLng fromScreenLocation = this.f8497a.getProjection().fromScreenLocation(point);
        LatLng fromScreenLocation2 = this.f8497a.getProjection().fromScreenLocation(point2);
        this.f8499c = this.f8497a.addPolyline(new PolylineOptions().add(latLng2, fromScreenLocation).color(-256).width(5.0f));
        if (!this.f8498b.contains(this.f8499c)) {
            this.f8498b.add(this.f8499c);
        }
        this.f8499c = this.f8497a.addPolyline(new PolylineOptions().add(latLng2, fromScreenLocation2).color(-256).width(5.0f));
        if (!this.f8498b.contains(this.f8499c)) {
            this.f8498b.add(this.f8499c);
        }
    }

    public double[] m11252a(int i, int i2, double d, double d2) {
        double[] dArr = new double[2];
        double cos = (((double) i) * Math.cos(d)) - (((double) i2) * Math.sin(d));
        double sin = (((double) i) * Math.sin(d)) + (((double) i2) * Math.cos(d));
        double sqrt = Math.sqrt((cos * cos) + (sin * sin));
        sin = (sin / sqrt) * d2;
        dArr[0] = (cos / sqrt) * d2;
        dArr[1] = sin;
        return dArr;
    }
}
