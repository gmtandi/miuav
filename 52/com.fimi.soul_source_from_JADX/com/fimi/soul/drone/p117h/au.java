package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.au */
public class au extends C1495k {
    public int f7430b;
    public int f7431c;
    public int f7432d;

    public au(C1433a c1433a) {
        super(c1433a);
    }

    public int m10404a() {
        return this.f7430b;
    }

    public void m10405a(int i, int i2, int i3) {
        this.f7430b = i;
        this.f7431c = i2;
        this.f7432d = i3;
        this.a.m9589a(C1584h.RELEASECHAIN);
    }

    public int m10406b() {
        return this.f7431c;
    }

    public int m10407c() {
        return this.f7432d;
    }

    public String toString() {
        return "ReleaseChainVariable{targetID=" + this.f7430b + ", state=" + this.f7431c + ", report=" + this.f7432d + '}';
    }
}
