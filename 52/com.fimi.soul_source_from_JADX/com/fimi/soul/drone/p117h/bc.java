package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.bc */
public class bc extends C1495k {
    public byte f7471b;
    public byte f7472c;

    public bc(C1433a c1433a) {
        super(c1433a);
    }

    public byte m10479a() {
        return this.f7471b;
    }

    public void m10480a(byte b, byte b2) {
        this.f7472c = b;
        this.f7471b = b2;
        this.a.m9589a(C1584h.UPDATEDRONEFINISHREPORT);
    }
}
