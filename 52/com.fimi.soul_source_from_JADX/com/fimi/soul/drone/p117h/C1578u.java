package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.u */
public class C1578u extends C1495k {
    private byte f7610b;
    private byte f7611c;
    private byte f7612d;
    private byte f7613e;
    private byte f7614f;

    public C1578u(C1433a c1433a) {
        super(c1433a);
    }

    public byte m10598a() {
        return this.f7610b;
    }

    public void m10599a(byte b) {
        this.f7610b = b;
    }

    public void m10600a(byte b, byte b2, byte b3, byte b4, byte b5) {
        this.f7610b = b;
        this.f7611c = b2;
        this.f7612d = b3;
        this.f7613e = b4;
        this.f7614f = b5;
        this.a.m9589a(C1584h.CaliCompass);
    }

    public byte m10601b() {
        return this.f7614f;
    }

    public void m10602b(byte b) {
        this.f7614f = b;
    }

    public byte m10603c() {
        return this.f7613e;
    }

    public void m10604c(byte b) {
        this.f7613e = b;
    }

    public byte m10605d() {
        return this.f7612d;
    }

    public void m10606d(byte b) {
        this.f7612d = b;
    }

    public byte m10607e() {
        return this.f7611c;
    }

    public void m10608e(byte b) {
        this.f7611c = b;
    }

    public String toString() {
        return "Compass{packetSeq=" + this.f7610b + ", caliType=" + this.f7611c + ", caliStep=" + this.f7612d + ", caliStepStatus=" + this.f7613e + ", report=" + this.f7614f + '}';
    }
}
