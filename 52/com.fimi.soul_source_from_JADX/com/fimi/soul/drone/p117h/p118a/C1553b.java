package com.fimi.soul.drone.p117h.p118a;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.a.b */
public class C1553b extends C1495k {
    public byte f7274b;
    public short f7275c;
    public float f7276d;
    public float f7277e;
    public byte f7278f;
    public short f7279g;
    public byte f7280h;
    public byte f7281i;
    public byte f7282j;
    public byte f7283k;
    public byte f7284l;

    public C1553b(C1433a c1433a) {
        super(c1433a);
        this.f7280h = (byte) 10;
    }

    public byte m10148a() {
        return this.f7274b;
    }

    public void m10149a(byte b) {
        this.f7278f = b;
    }

    public void m10150a(byte b, short s, float f, float f2, byte b2, short s2, byte b3, byte b4, byte b5, byte b6, byte b7) {
        this.f7274b = b;
        this.f7275c = s;
        this.f7276d = f;
        this.f7277e = f2;
        this.f7278f = b2;
        this.f7279g = s2;
        this.f7280h = b3;
        this.f7281i = b4;
        this.f7282j = b5;
        this.f7283k = b6;
        this.f7284l = b7;
        this.a.m9589a(C1584h.NOFLYZONE);
    }

    public void m10151a(float f) {
        this.f7277e = f;
    }

    public void m10152a(short s) {
        this.f7275c = s;
    }

    public byte m10153b() {
        return this.f7284l;
    }

    public void m10154b(float f) {
        this.f7276d = f;
    }

    public void m10155b(short s) {
        this.f7279g = s;
    }

    public byte m10156c() {
        return this.f7283k;
    }

    public byte m10157d() {
        return this.f7282j;
    }

    public byte m10158e() {
        return this.f7281i;
    }

    public byte m10159f() {
        return this.f7280h;
    }

    public short m10160g() {
        return this.f7279g;
    }

    public short m10161h() {
        return this.f7275c;
    }

    public byte m10162i() {
        return this.f7278f;
    }

    public float m10163j() {
        return this.f7277e;
    }

    public float m10164k() {
        return this.f7276d;
    }

    public String toString() {
        return "FlyZone{Opration_code=" + this.f7274b + ", number=" + this.f7275c + ", Forbiden_Longitude=" + this.f7276d + ", Forbiden_Latitude=" + this.f7277e + ", type=" + this.f7278f + ", radius=" + this.f7279g + ", status=" + this.f7280h + ", reserve1=" + this.f7281i + ", reserve2=" + this.f7282j + ", reserve3=" + this.f7283k + ", report=" + this.f7284l + '}';
    }
}
