package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;
import com.tencent.mm.sdk.platformtools.Util;

/* renamed from: com.fimi.soul.drone.h.d */
public class C1561d extends C1495k {
    public byte f7500b;
    public byte f7501c;
    public byte f7502d;
    public byte f7503e;
    public byte f7504f;
    public byte f7505g;
    public short f7506h;
    public short f7507i;
    public byte f7508j;
    public short f7509k;

    public C1561d(C1433a c1433a) {
        super(c1433a);
    }

    public int m10514a() {
        return this.f7500b & Util.MASK_8BIT;
    }

    public void m10515a(byte b, byte b2, byte b3, byte b4, byte b5, byte b6, short s, short s2, byte b7, short s3) {
        this.f7500b = b;
        this.f7501c = b2;
        this.f7502d = b3;
        this.f7503e = b4;
        this.f7504f = b5;
        this.f7505g = b6;
        this.f7506h = s;
        this.f7507i = s2;
        this.f7508j = b7;
        this.f7509k = s3;
        this.a.m9589a(C1584h.BATTERY);
    }

    public int m10516b() {
        return this.f7501c & Util.MASK_8BIT;
    }

    public int m10517c() {
        return this.f7502d & Util.MASK_8BIT;
    }

    public int m10518d() {
        return this.f7503e & Util.MASK_8BIT;
    }

    public byte m10519e() {
        return this.f7504f;
    }

    public byte m10520f() {
        return this.f7505g;
    }

    public short m10521g() {
        return this.f7506h;
    }

    public short m10522h() {
        return this.f7507i;
    }

    public int m10523i() {
        return this.f7508j & Util.MASK_8BIT;
    }

    public short m10524j() {
        return this.f7509k;
    }

    public String toString() {
        return "Battery [Cell_1_Voltage=" + this.f7500b + ", Cell_2_Voltage=" + this.f7501c + ", Cell_3_Voltage=" + this.f7502d + ", Current_Capacity=" + this.f7506h + ", Current=" + this.f7507i + ", battery_temperature=" + this.f7508j + "]";
    }
}
