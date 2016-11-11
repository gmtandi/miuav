package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.ag */
public class ag extends C1495k {
    private byte f7333b;
    private byte f7334c;
    private byte f7335d;
    private byte f7336e;

    public ag(C1433a c1433a) {
        super(c1433a);
    }

    public byte m10261a() {
        return this.f7335d;
    }

    public void m10262a(byte b, byte b2, byte b3, byte b4) {
        this.f7333b = b;
        this.f7334c = b2;
        this.f7335d = b3;
        this.f7336e = b4;
        this.a.m9589a(C1584h.GimbalCalibration);
    }

    public byte m10263b() {
        return this.f7336e;
    }

    public byte m10264c() {
        return this.f7333b;
    }

    public byte m10265d() {
        return this.f7334c;
    }
}
