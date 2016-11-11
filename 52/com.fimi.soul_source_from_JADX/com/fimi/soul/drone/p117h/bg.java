package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.bg */
public class bg extends C1495k {
    public byte f7480b;
    public short f7481c;
    public byte f7482d;

    public bg(C1433a c1433a) {
        super(c1433a);
    }

    public short m10491a() {
        return this.f7481c;
    }

    public void m10492a(byte b, short s, byte b2) {
        this.f7480b = b;
        this.f7481c = s;
        this.f7482d = b2;
        this.a.m9589a(C1584h.UPDATEDRONEREPORT);
    }

    public byte m10493b() {
        return this.f7482d;
    }

    public byte m10494c() {
        return this.f7480b;
    }

    public String toString() {
        return "UpdateDronerepory{target_ID=" + this.f7480b + ", Packet_sequence=" + this.f7481c + ", report=" + this.f7482d + '}';
    }
}
