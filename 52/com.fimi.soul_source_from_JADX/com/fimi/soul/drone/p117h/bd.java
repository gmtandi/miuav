package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.bd */
public class bd extends C1495k {
    public byte f7473b;
    public byte f7474c;

    public bd(C1433a c1433a) {
        super(c1433a);
    }

    public byte m10481a() {
        return this.f7474c;
    }

    public void m10482a(byte b, byte b2) {
        this.f7474c = b;
        this.f7473b = b2;
        this.a.m9589a(C1584h.UPDATEDRONEFINISHREPORTCRC);
    }

    public byte m10483b() {
        return this.f7473b;
    }
}
