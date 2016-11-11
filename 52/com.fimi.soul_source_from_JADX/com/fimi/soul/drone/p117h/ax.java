package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.ax */
public class ax extends C1495k {
    private short f7440b;

    public ax(C1433a c1433a) {
        super(c1433a);
    }

    public short m10421a() {
        return this.f7440b;
    }

    public void m10422a(short s) {
        this.f7440b = s;
        this.a.m9589a(C1584h.ResumeWaypoint);
    }

    public void m10423b() {
        this.f7440b = (short) 0;
    }
}
