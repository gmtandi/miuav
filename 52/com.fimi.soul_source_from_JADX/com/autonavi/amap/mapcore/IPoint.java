package com.autonavi.amap.mapcore;

public class IPoint {
    public int f3714x;
    public int f3715y;

    public IPoint(int i, int i2) {
        this.f3714x = i;
        this.f3715y = i2;
    }

    public Object clone() {
        try {
            return (IPoint) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
