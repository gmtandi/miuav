package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.ad */
public class ad extends C1495k {
    public byte f7313b;
    public byte f7314c;
    public byte f7315d;
    public byte f7316e;
    public byte f7317f;

    public ad(C1433a c1433a) {
        super(c1433a);
    }

    public byte m10238a() {
        return this.f7313b;
    }

    public void m10239a(byte b, byte b2, byte b3, byte b4, byte b5) {
        this.f7313b = b;
        this.f7314c = b2;
        this.f7315d = b3;
        this.f7316e = b4;
        this.f7317f = b5;
        this.a.m9589a(C1584h.INFO);
    }

    public byte m10240b() {
        return this.f7314c;
    }

    public byte m10241c() {
        return this.f7315d;
    }

    public byte m10242d() {
        return this.f7316e;
    }

    public byte m10243e() {
        return this.f7317f;
    }
}
