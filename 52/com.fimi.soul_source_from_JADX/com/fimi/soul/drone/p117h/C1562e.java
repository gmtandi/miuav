package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.e */
public class C1562e extends C1495k {
    public short f7510b;
    public short f7511c;
    public short f7512d;
    public short f7513e;
    public short f7514f;

    public C1562e(C1433a c1433a) {
        super(c1433a);
    }

    public short m10525a() {
        return this.f7510b;
    }

    public void m10526a(short s, short s2, short s3, short s4, short s5) {
        this.f7510b = s;
        this.f7511c = s2;
        this.f7512d = s3;
        this.f7513e = s4;
        this.f7514f = s5;
        this.a.m9589a(C1584h.BATTERYINFO);
    }

    public short m10527b() {
        return this.f7511c;
    }

    public short m10528c() {
        return this.f7512d;
    }

    public short m10529d() {
        return this.f7513e;
    }

    public short m10530e() {
        return this.f7514f;
    }
}
