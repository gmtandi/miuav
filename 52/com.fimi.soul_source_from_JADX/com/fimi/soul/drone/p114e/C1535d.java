package com.fimi.soul.drone.p114e;

import java.io.Serializable;

/* renamed from: com.fimi.soul.drone.e.d */
public class C1535d implements Serializable {
    private double f7190a;
    private double f7191b;

    public double m10089a() {
        return this.f7190a;
    }

    public void m10090a(double d) {
        this.f7190a = d;
    }

    public double m10091b() {
        return this.f7191b;
    }

    public void m10092b(double d) {
        this.f7191b = d;
    }

    public String toString() {
        return "GPSBean [lat=" + this.f7190a + ", lon=" + this.f7191b + "]";
    }
}
