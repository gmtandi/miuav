package com.amap.api.maps.model;

import com.amap.api.mapcore.util.bj;
import com.autonavi.amap.mapcore.DPoint;

public class WeightedLatLng {
    public static final double DEFAULT_INTENSITY = 1.0d;
    private DPoint f2807a;
    public final double intensity;
    public final LatLng latLng;

    public WeightedLatLng(LatLng latLng) {
        this(latLng, DEFAULT_INTENSITY);
    }

    public WeightedLatLng(LatLng latLng, double d) {
        if (latLng == null) {
            throw new IllegalArgumentException("latLng can not null");
        }
        this.latLng = latLng;
        this.f2807a = bj.m3613a(latLng);
        if (d >= 0.0d) {
            this.intensity = d;
        } else {
            this.intensity = DEFAULT_INTENSITY;
        }
    }

    protected DPoint getPoint() {
        return this.f2807a;
    }
}
