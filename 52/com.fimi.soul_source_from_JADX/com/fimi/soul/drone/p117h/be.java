package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.be */
public class be extends C1495k {
    public byte f7475b;
    public byte f7476c;
    public byte f7477d;

    public be(C1433a c1433a) {
        super(c1433a);
    }

    public byte m10484a() {
        return this.f7475b;
    }

    public void m10485a(byte b, byte b2, byte b3) {
        this.f7475b = b;
        this.f7476c = b2;
        this.f7477d = b3;
        this.a.m9589a(C1584h.UPDATEDRONEOBJECTREPORT);
    }

    public byte m10486b() {
        return this.f7476c;
    }

    public byte m10487c() {
        return this.f7477d;
    }
}
