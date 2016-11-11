package com.fimi.soul.drone.p117h.p118a;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.a.a */
public class C1552a extends C1495k {
    public byte f7264b;
    public byte f7265c;
    public byte f7266d;
    public short f7267e;
    public short f7268f;
    public short f7269g;
    public short f7270h;
    public float f7271i;
    public float f7272j;
    public byte f7273k;

    public C1552a(C1433a c1433a) {
        super(c1433a);
    }

    public byte m10137a() {
        return this.f7264b;
    }

    public void m10138a(byte b, byte b2, byte b3, short s, short s2, short s3, short s4, float f, float f2, byte b4) {
        this.f7264b = b;
        this.f7265c = b2;
        this.f7266d = b3;
        this.f7267e = s;
        this.f7268f = s2;
        this.f7269g = s3;
        this.f7270h = s4;
        this.f7271i = f;
        this.f7272j = f2;
        this.f7273k = b4;
        this.a.m9589a(C1584h.TAKEPHOTOBACKDATA);
    }

    public byte m10139b() {
        return this.f7273k;
    }

    public float m10140c() {
        return this.f7272j;
    }

    public short m10141d() {
        return this.f7270h;
    }

    public float m10142e() {
        return this.f7271i;
    }

    public short m10143f() {
        return this.f7268f;
    }

    public short m10144g() {
        return this.f7269g;
    }

    public short m10145h() {
        return this.f7267e;
    }

    public byte m10146i() {
        return this.f7266d;
    }

    public byte m10147j() {
        return this.f7265c;
    }

    public String toString() {
        return "DroneTakePhotoBean{packPacket_sequence=" + this.f7264b + ", CMD_ID=" + this.f7265c + ", Mode=" + this.f7266d + ", Route_Length=" + this.f7267e + ", Climb_Angle=" + this.f7268f + ", Route_Speed=" + this.f7269g + ", Start_point_Altitude=" + this.f7270h + ", Start_point_Longitude=" + this.f7271i + ", Start_point_Latitude=" + this.f7272j + ", report=" + this.f7273k + '}';
    }
}
