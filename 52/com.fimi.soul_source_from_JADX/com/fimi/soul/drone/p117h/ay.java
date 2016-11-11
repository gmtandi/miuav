package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.ay */
public class ay extends C1495k {
    private short f7441b;
    private byte f7442c;

    public ay(C1433a c1433a) {
        super(c1433a);
    }

    public byte m10424a() {
        return this.f7442c;
    }

    public void m10425a(byte b) {
        this.f7442c = b;
    }

    public void m10426a(short s, byte b) {
        this.f7442c = b;
        this.f7441b = s;
        this.a.m9589a(C1584h.SENDHOVERWAYPOINT);
    }

    public short m10427b() {
        return this.f7441b;
    }

    public void m10428c() {
        this.f7441b = (short) 0;
    }
}
