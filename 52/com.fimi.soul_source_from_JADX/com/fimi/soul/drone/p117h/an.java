package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.an */
public class an extends C1495k {
    private short f7387b;

    public an(C1433a c1433a) {
        super(c1433a);
    }

    public short m10353a() {
        return this.f7387b;
    }

    public void m10354a(short s) {
        this.f7387b = s;
        this.a.m9589a(C1584h.PauseWaypoint);
    }

    public void m10355b() {
        this.f7387b = (short) 0;
    }
}
