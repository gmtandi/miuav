package com.amap.api.maps.model;

public class PoiPara {
    private LatLng f2750a;
    private String f2751b;

    public LatLng getCenter() {
        return this.f2750a;
    }

    public String getKeywords() {
        return this.f2751b;
    }

    public void setCenter(LatLng latLng) {
        this.f2750a = latLng;
    }

    public void setKeywords(String str) {
        this.f2751b = str;
    }
}
