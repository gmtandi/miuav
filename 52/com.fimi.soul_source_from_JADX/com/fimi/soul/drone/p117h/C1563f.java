package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.f */
public class C1563f extends C1495k {
    public byte f7515b;
    public byte f7516c;
    public byte f7517d;
    public byte f7518e;

    public C1563f(C1433a c1433a) {
        super(c1433a);
    }

    public byte m10531a() {
        return this.f7516c;
    }

    public void m10532a(byte b, byte b2, byte b3, byte b4) {
        this.f7515b = b;
        this.f7516c = b2;
        this.f7517d = b3;
        this.f7518e = b4;
        this.a.m9589a(C1584h.CALIREMOTESUSTOMBUTTON);
    }

    public byte m10533b() {
        return this.f7518e;
    }

    public byte m10534c() {
        return this.f7517d;
    }

    public byte m10535d() {
        return this.f7515b;
    }
}
