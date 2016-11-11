package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.as */
public class as extends C1495k {
    public volatile int f7414b;
    public float f7415c;
    public float f7416d;
    public double f7417e;
    public double f7418f;
    public double f7419g;
    public double f7420h;
    public double f7421i;

    public as(C1433a c1433a) {
        super(c1433a);
    }

    public int m10386a() {
        return this.f7414b;
    }

    public void m10387a(int i, float f, float f2, double d, double d2, double d3, double d4, double d5) {
        this.f7414b = i;
        this.f7415c = f;
        this.f7416d = f2;
        this.f7417e = d;
        this.f7418f = d2;
        this.f7419g = d3;
        this.f7420h = d4;
        this.f7421i = d5;
        this.a.m9589a(C1584h.RECEIVERWAYPOINTS);
    }

    public double m10388b() {
        return (double) this.f7415c;
    }

    public double m10389c() {
        return (double) this.f7416d;
    }

    public double m10390d() {
        return this.f7417e;
    }

    public double m10391e() {
        return this.f7418f;
    }

    public double m10392f() {
        return this.f7419g;
    }

    public double m10393g() {
        return this.f7420h;
    }

    public double m10394h() {
        return this.f7421i;
    }

    public String toString() {
        return "Receivepoint [number=" + this.f7414b + ", Longitude=" + this.f7415c + ", Latitude=" + this.f7416d + ", Altitude=" + this.f7417e + ", yaw_angle=" + this.f7418f + ", hover_time=" + this.f7419g + ", report=" + this.f7420h + ", Number_to_be_transmited=" + this.f7421i + "]";
    }
}
