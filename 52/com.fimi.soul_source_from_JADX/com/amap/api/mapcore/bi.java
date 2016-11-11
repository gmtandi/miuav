package com.amap.api.mapcore;

import android.graphics.Point;
import android.graphics.PointF;
import com.amap.api.mapcore.util.bj;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.TileProjection;
import com.amap.api.maps.model.VisibleRegion;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;

class bi implements am {
    private ab f1783a;

    public bi(ab abVar) {
        this.f1783a = abVar;
    }

    public float m3027a(int i) {
        return i <= 0 ? 0.0f : this.f1783a.m2288c(i);
    }

    public Point m3028a(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        IPoint iPoint = new IPoint();
        this.f1783a.m2281b(latLng.latitude, latLng.longitude, iPoint);
        return new Point(iPoint.f3714x, iPoint.f3715y);
    }

    public LatLng m3029a(Point point) {
        if (point == null) {
            return null;
        }
        DPoint dPoint = new DPoint();
        this.f1783a.m2248a(point.x, point.y, dPoint);
        return new LatLng(dPoint.f3692y, dPoint.f3691x);
    }

    public LatLngBounds m3030a(LatLng latLng, float f) {
        return (this.f1783a == null || latLng == null) ? null : this.f1783a.m2237a(latLng, bj.m3602a(f));
    }

    public TileProjection m3031a(LatLngBounds latLngBounds, int i, int i2) {
        if (latLngBounds == null || i < 0 || i > 20 || i2 <= 0) {
            return null;
        }
        IPoint iPoint = new IPoint();
        IPoint iPoint2 = new IPoint();
        this.f1783a.m2243a(latLngBounds.southwest.latitude, latLngBounds.southwest.longitude, iPoint);
        this.f1783a.m2243a(latLngBounds.northeast.latitude, latLngBounds.northeast.longitude, iPoint2);
        int i3 = (iPoint.f3714x >> (20 - i)) / i2;
        int i4 = (iPoint2.f3715y >> (20 - i)) / i2;
        return new TileProjection((iPoint.f3714x - ((i3 << (20 - i)) * i2)) >> (20 - i), (iPoint2.f3715y - ((i4 << (20 - i)) * i2)) >> (20 - i), i3, (iPoint2.f3714x >> (20 - i)) / i2, i4, (iPoint.f3715y >> (20 - i)) / i2);
    }

    public VisibleRegion m3032a() {
        int l = this.f1783a.m2311l();
        int m = this.f1783a.m2313m();
        LatLng a = m3029a(new Point(0, 0));
        LatLng a2 = m3029a(new Point(l, 0));
        LatLng a3 = m3029a(new Point(0, m));
        LatLng a4 = m3029a(new Point(l, m));
        return new VisibleRegion(a3, a4, a, a2, LatLngBounds.builder().include(a3).include(a4).include(a).include(a2).build());
    }

    public PointF m3033b(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        FPoint fPoint = new FPoint();
        this.f1783a.m2242a(latLng.latitude, latLng.longitude, fPoint);
        return new PointF(fPoint.f3693x, fPoint.f3694y);
    }
}
