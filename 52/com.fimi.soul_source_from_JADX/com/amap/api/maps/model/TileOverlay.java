package com.amap.api.maps.model;

import com.amap.api.mapcore.ap;

public final class TileOverlay {
    private ap f2796a;

    public TileOverlay(ap apVar) {
        this.f2796a = apVar;
    }

    public void clearTileCache() {
        this.f2796a.m2713b();
    }

    public boolean equals(Object obj) {
        return !(obj instanceof TileOverlay) ? false : this.f2796a.m2712a(((TileOverlay) obj).f2796a);
    }

    public String getId() {
        return this.f2796a.m2715c();
    }

    public float getZIndex() {
        return this.f2796a.m2717d();
    }

    public int hashCode() {
        return this.f2796a.m2719f();
    }

    public boolean isVisible() {
        return this.f2796a.m2718e();
    }

    public void remove() {
        this.f2796a.m2708a();
    }

    public void setVisible(boolean z) {
        this.f2796a.m2711a(z);
    }

    public void setZIndex(float f) {
        this.f2796a.m2709a(f);
    }
}
