package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.bf */
public class bf extends C1495k {
    public byte f7478b;
    public byte f7479c;

    public bf(C1433a c1433a) {
        super(c1433a);
    }

    public byte m10488a() {
        return this.f7478b;
    }

    public void m10489a(byte b, byte b2) {
        this.f7478b = b;
        this.f7479c = b2;
        this.a.m9589a(C1584h.UPDATEDRONEORDERREPORT);
    }

    public byte m10490b() {
        return this.f7479c;
    }
}
