package com.fimi.soul.module.droneui;

import android.content.res.Resources;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;

/* renamed from: com.fimi.soul.module.droneui.t */
public class C1758t {
    private static final int f8641b = -1;
    private static final int f8642c = 2;
    public Polyline f8643a;
    private MapView f8644d;
    private float f8645e;
    private int f8646f;

    private C1758t(MapView mapView, int i, float f, Resources resources) {
        this.f8644d = mapView;
        this.f8646f = i;
        this.f8645e = (float) m11365a((double) f, resources);
    }

    public C1758t(MapView mapView, int i, Resources resources) {
        this(mapView, i, 2.0f, resources);
    }

    public C1758t(MapView mapView, Resources resources) {
        this(mapView, f8641b, resources);
    }

    private int m11365a(double d, Resources resources) {
        return (int) Math.round(((double) resources.getDisplayMetrics().density) * d);
    }

    private void m11366a() {
        if (this.f8643a == null) {
            new PolylineOptions().color(this.f8646f).width(this.f8645e);
        }
    }

    public void m11367a(C1759u c1759u) {
    }
}
