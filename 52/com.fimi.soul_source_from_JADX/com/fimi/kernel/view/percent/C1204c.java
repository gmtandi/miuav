package com.fimi.kernel.view.percent;

import com.amap.api.maps.model.GroundOverlayOptions;

/* renamed from: com.fimi.kernel.view.percent.c */
public class C1204c {
    public float f5433a;
    public boolean f5434b;

    public C1204c() {
        this.f5433a = GroundOverlayOptions.NO_DIMENSION;
    }

    public C1204c(float f, boolean z) {
        this.f5433a = GroundOverlayOptions.NO_DIMENSION;
        this.f5433a = f;
        this.f5434b = z;
    }

    public String toString() {
        return "PercentVal{percent=" + this.f5433a + ", isBaseWidth=" + this.f5434b + '}';
    }
}
