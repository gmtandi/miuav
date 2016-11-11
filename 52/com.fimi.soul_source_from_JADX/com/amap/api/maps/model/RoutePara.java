package com.amap.api.maps.model;

public class RoutePara {
    private int f2775a;
    private int f2776b;
    private LatLng f2777c;
    private LatLng f2778d;
    private String f2779e;
    private String f2780f;

    public RoutePara() {
        this.f2775a = 0;
        this.f2776b = 0;
    }

    public int getDrivingRouteStyle() {
        return this.f2775a;
    }

    public String getEndName() {
        return this.f2780f;
    }

    public LatLng getEndPoint() {
        return this.f2778d;
    }

    public String getStartName() {
        return this.f2779e;
    }

    public LatLng getStartPoint() {
        return this.f2777c;
    }

    public int getTransitRouteStyle() {
        return this.f2776b;
    }

    public void setDrivingRouteStyle(int i) {
        if (i >= 0 && i < 9) {
            this.f2775a = i;
        }
    }

    public void setEndName(String str) {
        this.f2780f = str;
    }

    public void setEndPoint(LatLng latLng) {
        this.f2778d = latLng;
    }

    public void setStartName(String str) {
        this.f2779e = str;
    }

    public void setStartPoint(LatLng latLng) {
        this.f2777c = latLng;
    }

    public void setTransitRouteStyle(int i) {
        if (i >= 0 && i < 6) {
            this.f2776b = i;
        }
    }
}
