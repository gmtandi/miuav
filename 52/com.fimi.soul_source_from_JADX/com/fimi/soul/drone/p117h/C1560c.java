package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.c */
public class C1560c extends C1495k {
    public byte f7497b;
    public byte f7498c;
    public byte f7499d;

    public C1560c(C1433a c1433a) {
        super(c1433a);
    }

    public byte m10509a() {
        return this.f7499d;
    }

    public void m10510a(byte b, byte b2, byte b3) {
        this.f7497b = b;
        this.f7498c = b2;
        this.f7499d = b3;
        this.a.m9589a(C1584h.backControl);
    }

    public byte m10511b() {
        return this.f7497b;
    }

    public byte m10512c() {
        return this.f7498c;
    }

    public byte m10513d() {
        return this.f7499d;
    }

    public String toString() {
        return "BackControl [model=" + this.f7497b + ", status=" + this.f7498c + ", action=" + this.f7499d + "]";
    }
}
