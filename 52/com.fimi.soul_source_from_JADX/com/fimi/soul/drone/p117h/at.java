package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.at */
public class at extends C1495k {
    public int f7422b;
    public float f7423c;
    public float f7424d;
    public double f7425e;
    public double f7426f;
    public double f7427g;
    public double f7428h;
    public int f7429i;

    public at(C1433a c1433a) {
        super(c1433a);
        this.f7429i = 0;
    }

    public int m10395a() {
        return this.f7422b;
    }

    public void m10396a(int i, float f, float f2, double d, double d2, double d3, double d4, int i2) {
        this.f7422b = i;
        this.f7423c = f;
        this.f7424d = f2;
        this.f7425e = d;
        this.f7426f = d2;
        this.f7427g = d3;
        this.f7428h = d4;
        this.f7429i = i2;
        this.a.m9589a(C1584h.RECEIVERPOINTS);
    }

    public double m10397b() {
        return (double) this.f7423c;
    }

    public double m10398c() {
        return (double) this.f7424d;
    }

    public double m10399d() {
        return this.f7425e;
    }

    public double m10400e() {
        return this.f7426f;
    }

    public double m10401f() {
        return this.f7427g;
    }

    public double m10402g() {
        return this.f7428h;
    }

    public int m10403h() {
        return this.f7429i;
    }

    public String toString() {
        return "Receivepoints [number=" + this.f7422b + ", Longitude=" + this.f7423c + ", Latitude=" + this.f7424d + ", Altitude=" + this.f7425e + ", yaw_angle=" + this.f7426f + ", hover_time=" + this.f7427g + ", speed=" + this.f7428h + ", Number_to_be_transmited=" + this.f7429i + "]";
    }
}
