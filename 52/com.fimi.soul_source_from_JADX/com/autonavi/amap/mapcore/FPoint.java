package com.autonavi.amap.mapcore;

public class FPoint {
    public float f3693x;
    public float f3694y;

    public FPoint(float f, float f2) {
        this.f3693x = f;
        this.f3694y = f2;
    }

    public boolean equals(Object obj) {
        FPoint fPoint = (FPoint) obj;
        return fPoint != null && this.f3693x == fPoint.f3693x && this.f3694y == fPoint.f3694y;
    }
}
