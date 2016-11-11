package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.ap */
public class ap extends C1495k {
    private double f7395b;
    private double f7396c;
    private double f7397d;
    private double f7398e;
    private double f7399f;

    public ap(C1433a c1433a) {
        super(c1433a);
    }

    public double m10364a() {
        return this.f7395b;
    }

    public void m10365a(double d, double d2, double d3, double d4, double d5) {
        this.f7395b = d;
        this.f7396c = d2;
        this.f7397d = d3;
        this.f7398e = d4;
        this.f7399f = d5;
        this.a.m9589a(C1584h.RC_IN);
    }

    public double m10366b() {
        return this.f7396c;
    }

    public double m10367c() {
        return this.f7397d;
    }

    public double m10368d() {
        return this.f7398e;
    }

    public double m10369e() {
        return this.f7399f;
    }

    public String toString() {
        return "RC [rc1=" + this.f7395b + ", rc2=" + this.f7396c + ", rc3=" + this.f7397d + ", rc4=" + this.f7398e + ", rc5=" + this.f7399f + "]";
    }
}
