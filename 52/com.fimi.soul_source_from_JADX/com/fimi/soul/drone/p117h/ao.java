package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.ao */
public class ao extends C1495k {
    private double f7388b;
    private byte f7389c;
    private float f7390d;
    private float f7391e;
    private double f7392f;
    private double f7393g;
    private byte f7394h;

    public ao(C1433a c1433a) {
        super(c1433a);
    }

    public byte m10356a() {
        return this.f7394h;
    }

    public void m10357a(double d, byte b, float f, float f2, double d2, double d3, byte b2) {
        this.f7388b = d;
        this.f7389c = b;
        this.f7390d = f;
        this.f7391e = f2;
        this.f7393g = d3;
        this.f7394h = b2;
        this.f7392f = d2;
        this.a.m9589a(C1584h.HEARDDAY);
    }

    public double m10358b() {
        return this.f7388b;
    }

    public byte m10359c() {
        return this.f7389c;
    }

    public float m10360d() {
        return this.f7390d;
    }

    public float m10361e() {
        return this.f7391e;
    }

    public double m10362f() {
        return this.f7392f;
    }

    public double m10363g() {
        return this.f7393g;
    }

    public String toString() {
        return "Pert{flighttime=" + this.f7388b + ", SatelliteNumber=" + this.f7389c + ", CurrentLongitude=" + this.f7390d + ", CurrentLatitude=" + this.f7391e + ", Height=" + this.f7392f + ", Distance=" + this.f7393g + ", RFsignalstrength=" + this.f7394h + '}';
    }
}
