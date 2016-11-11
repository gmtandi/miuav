package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;
import com.tencent.mm.sdk.platformtools.Util;

/* renamed from: com.fimi.soul.drone.h.ar */
public class ar extends C1495k {
    public double f7403b;
    public int f7404c;
    public float f7405d;
    public float f7406e;
    public double f7407f;
    public short f7408g;
    public int f7409h;
    public int f7410i;
    public byte f7411j;
    public byte f7412k;
    public byte f7413l;

    public ar(C1433a c1433a) {
        super(c1433a);
    }

    public byte m10374a() {
        return this.f7413l;
    }

    public void m10375a(double d, int i, float f, float f2, double d2, short s, int i2, int i3, byte b, byte b2, byte b3) {
        this.f7403b = d;
        this.f7404c = i;
        this.f7405d = f;
        this.f7406e = f2;
        this.f7407f = d2;
        this.f7408g = s;
        this.f7409h = i2 & Util.MASK_8BIT;
        this.f7410i = i3 & Util.MASK_8BIT;
        this.f7411j = b;
        this.f7412k = b2;
        this.f7413l = b3;
        this.a.m9589a(C1584h.RETURNINTERESTWAYPOIT);
    }

    public byte m10376b() {
        return this.f7412k;
    }

    public byte m10377c() {
        return this.f7411j;
    }

    public int m10378d() {
        return this.f7410i;
    }

    public int m10379e() {
        return this.f7409h;
    }

    public short m10380f() {
        return this.f7408g;
    }

    public double m10381g() {
        return this.f7407f;
    }

    public float m10382h() {
        return this.f7406e;
    }

    public float m10383i() {
        return this.f7405d;
    }

    public int m10384j() {
        return this.f7404c;
    }

    public double m10385k() {
        return this.f7403b;
    }

    public String toString() {
        return "ReceiveSettingPoint{PacketSequence=" + this.f7403b + ", Opration_Code=" + this.f7404c + ", POI_Longitude=" + this.f7405d + ", POI_Latitude=" + this.f7406e + ", POI_Altitude=" + this.f7407f + ", Radius=" + this.f7408g + ", speek=" + this.f7409h + ", Start_Point_Pole_Angle=" + this.f7410i + ", Paral=" + this.f7411j + ", yaw_mode=" + this.f7412k + ", report=" + this.f7413l + '}';
    }
}
