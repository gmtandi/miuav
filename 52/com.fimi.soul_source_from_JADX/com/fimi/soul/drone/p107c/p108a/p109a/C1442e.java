package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.e */
public class C1442e extends C1437b {
    public static final int f6814b = 5;
    public static final int f6815c = 14;
    private static final long serialVersionUID = 5;
    public byte f6816d;
    public byte f6817e;
    public byte f6818f;
    public byte f6819g;
    public byte f6820h;
    public byte f6821i;
    public short f6822j;
    public short f6823k;
    public byte f6824l;
    public byte f6825m;
    public short f6826n;
    public C1465c f6827o;

    public C1442e() {
        this.a = f6814b;
    }

    public C1442e(C1465c c1465c) {
        this.a = f6814b;
        this.f6827o = c1465c;
        m9728a(c1465c.f7001d);
    }

    public C1465c m9727a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6815c;
        c1465c.f7000c = f6814b;
        return null;
    }

    public void m9728a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6816d = c1466d.m9833d();
        this.f6817e = c1466d.m9833d();
        this.f6818f = c1466d.m9833d();
        this.f6819g = c1466d.m9833d();
        this.f6820h = c1466d.m9833d();
        this.f6821i = c1466d.m9833d();
        this.f6822j = c1466d.m9834e();
        this.f6823k = c1466d.m9834e();
        this.f6824l = c1466d.m9833d();
        this.f6825m = c1466d.m9833d();
        this.f6826n = c1466d.m9834e();
    }

    public String toString() {
        return "CellDwonLink [Cell_1_Voltage=" + this.f6816d + ", Cell_2_Voltage=" + this.f6817e + ", Cell_3_Voltage=" + this.f6818f + ", Current_Capacity=" + this.f6822j + ", Current=" + this.f6823k + ", battery_temperature=" + this.f6824l + "]";
    }
}
