package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.drone.h.al */
public class al extends C1495k {
    private byte f7373b;
    private short f7374c;
    private short f7375d;
    private short f7376e;
    private short f7377f;
    private short f7378g;
    private short f7379h;
    private short f7380i;
    private volatile short f7381j;

    public al(C1433a c1433a) {
        super(c1433a);
    }

    public int m10322a(int i, int i2) {
        return ((1 << i2) & i) >> i2;
    }

    public void m10323a(byte b) {
        this.f7373b = b;
    }

    public void m10324a(short s) {
        this.f7381j = s;
    }

    public boolean m10325a() {
        return 1 == m10322a(this.f7381j, 4);
    }

    public short m10326b() {
        return this.f7381j;
    }

    public void m10327b(short s) {
        this.f7380i = s;
    }

    public short m10328c() {
        return this.f7380i;
    }

    public void m10329c(short s) {
        this.f7379h = s;
    }

    public short m10330d() {
        return this.f7379h;
    }

    public void m10331d(short s) {
        this.f7378g = s;
    }

    public short m10332e() {
        return this.f7378g;
    }

    public void m10333e(short s) {
        this.f7377f = s;
    }

    public short m10334f() {
        return this.f7377f;
    }

    public void m10335f(short s) {
        this.f7376e = s;
    }

    public short m10336g() {
        return this.f7376e;
    }

    public void m10337g(short s) {
        this.f7375d = s;
    }

    public short m10338h() {
        return this.f7375d;
    }

    public void m10339h(short s) {
        this.f7374c = s;
    }

    public short m10340i() {
        return this.f7374c;
    }

    public byte m10341j() {
        return this.f7373b;
    }

    public boolean m10342k() {
        return be.m12349a(this.f7381j, 4) == 0;
    }

    public String toString() {
        return "NewRemoteMode{type=" + this.f7373b + ", AD_Value0=" + this.f7374c + ", AD_Value1=" + this.f7375d + ", AD_Value2=" + this.f7376e + ", AD_Value3=" + this.f7377f + ", AD_Value4=" + this.f7378g + ", AD_Value5=" + this.f7379h + ", battery_voltage=" + this.f7380i + ", statuts=" + this.f7381j + '}';
    }
}
