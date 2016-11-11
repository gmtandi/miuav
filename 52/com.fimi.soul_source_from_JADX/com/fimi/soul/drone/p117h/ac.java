package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.ac */
public class ac extends C1495k {
    private short f7312b;

    public ac(C1433a c1433a) {
        super(c1433a);
    }

    public short m10235a() {
        return this.f7312b;
    }

    public void m10236a(short s) {
        this.f7312b = s;
        this.a.m9589a(C1584h.ExitWaypoint);
    }

    public void m10237b() {
        this.f7312b = (short) 0;
    }
}
