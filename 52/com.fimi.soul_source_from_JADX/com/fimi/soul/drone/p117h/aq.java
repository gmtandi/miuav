package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.aq */
public class aq extends C1495k {
    public double f7400b;
    public byte f7401c;
    public byte f7402d;

    public aq(C1433a c1433a) {
        super(c1433a);
    }

    public double m10370a() {
        return this.f7400b;
    }

    public void m10371a(double d, byte b, byte b2) {
        this.f7400b = d;
        this.f7401c = b;
        this.f7402d = b2;
        this.a.m9589a(C1584h.FOLLOWME);
    }

    public byte m10372b() {
        return this.f7401c;
    }

    public byte m10373c() {
        return this.f7402d;
    }
}
