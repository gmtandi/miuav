package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.aj */
public class aj extends C1495k {
    private double f7354b;
    private double f7355c;
    private double f7356d;
    private int f7357e;
    private float f7358f;
    private byte f7359g;

    public aj(C1433a c1433a) {
        super(c1433a);
    }

    public float m10299a() {
        return this.f7358f;
    }

    public void m10300a(double d, double d2, double d3, int i, float f, byte b) {
        this.f7355c = d;
        this.f7354b = d2;
        this.f7356d = d3;
        this.f7357e = i;
        this.f7358f = f;
        this.f7359g = b;
        this.a.m9589a(C1584h.HOMEPOINT);
    }

    public double m10301b() {
        return this.f7354b;
    }

    public double m10302c() {
        return this.f7355c;
    }

    public double m10303d() {
        return this.f7356d;
    }

    public int m10304e() {
        return this.f7357e;
    }

    public boolean m10305f() {
        return (this.f7359g & 15) == 1;
    }

    public boolean m10306g() {
        return ((this.f7359g & 240) >> 4) == 1;
    }
}
