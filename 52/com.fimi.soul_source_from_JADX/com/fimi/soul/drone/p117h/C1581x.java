package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.x */
public class C1581x extends C1495k {
    public byte f7619b;
    public byte f7620c;
    public float f7621d;
    public byte f7622e;

    public C1581x(C1433a c1433a) {
        super(c1433a);
    }

    public byte m10615a() {
        return this.f7619b;
    }

    public void m10616a(byte b) {
        this.f7619b = b;
    }

    public void m10617a(byte b, byte b2, float f, byte b3) {
        this.f7619b = b;
        this.f7620c = b2;
        this.f7621d = f;
        this.f7622e = b3;
        this.a.m9589a(C1584h.RETURN_HEIGHT);
    }

    public void m10618a(float f) {
        this.f7621d = f;
    }

    public byte m10619b() {
        return this.f7620c;
    }

    public void m10620b(byte b) {
        this.f7620c = b;
    }

    public float m10621c() {
        return this.f7621d;
    }

    public void m10622c(byte b) {
        this.f7622e = b;
    }

    public byte m10623d() {
        return this.f7622e;
    }

    public String toString() {
        return "DroneRTHVariable{packPacketSequence=" + this.f7619b + ", cmdID=" + this.f7620c + ", height=" + this.f7621d + ", report=" + this.f7622e + '}';
    }
}
