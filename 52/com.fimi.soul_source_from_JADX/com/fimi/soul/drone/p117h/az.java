package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;
import com.tencent.mm.sdk.platformtools.Util;

/* renamed from: com.fimi.soul.drone.h.az */
public class az extends C1495k {
    public double f7443b;
    public float f7444c;
    public float f7445d;
    public double f7446e;
    public byte f7447f;
    public int f7448g;
    public double f7449h;
    public double f7450i;

    public az(C1433a c1433a) {
        super(c1433a);
    }

    public double m10429a() {
        return this.f7443b;
    }

    public void m10430a(double d) {
        this.f7443b = d;
    }

    public void m10431a(double d, float f, float f2, double d2, byte b, double d3, double d4, double d5) {
        this.f7443b = d;
        this.f7444c = f;
        this.f7445d = f2;
        this.f7446e = d2;
        this.f7447f = b;
        this.f7448g = ((int) d3) & Util.MASK_8BIT;
        this.f7449h = d4;
        this.f7450i = d5;
        this.a.m9589a(C1584h.RETURNASSIGNWAYPOINT);
    }

    public void m10432a(float f) {
        this.f7445d = f;
    }

    public double m10433b() {
        return (double) this.f7444c;
    }

    public void m10434b(double d) {
        this.f7446e = d;
    }

    public void m10435b(float f) {
        this.f7444c = f;
    }

    public double m10436c() {
        return this.f7446e;
    }

    public void m10437c(double d) {
        this.f7449h = d;
    }

    public byte m10438d() {
        return this.f7447f;
    }

    public void m10439d(double d) {
        this.f7450i = d;
    }

    public int m10440e() {
        return this.f7448g;
    }

    public double m10441f() {
        return this.f7449h;
    }

    public double m10442g() {
        return this.f7450i;
    }

    public float m10443h() {
        return this.f7445d;
    }
}
