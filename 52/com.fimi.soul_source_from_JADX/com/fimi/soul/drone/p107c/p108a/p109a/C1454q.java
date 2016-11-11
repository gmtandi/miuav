package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.q */
public class C1454q extends C1437b {
    public static final int f6898b = 11;
    public static final int f6899c = 3;
    private static final long serialVersionUID = 11;
    public byte f6900d;
    public byte f6901e;
    public byte f6902f;
    public byte f6903g;
    public byte f6904h;
    public byte f6905i;
    public byte f6906j;

    public C1454q() {
        this.a = f6898b;
    }

    public C1454q(C1465c c1465c) {
        this.a = f6898b;
        m9787a(c1465c.f7001d);
    }

    public C1465c m9786a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6899c;
        c1465c.f7000c = f6898b;
        return c1465c;
    }

    public void m9787a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6900d = c1466d.m9833d();
        this.f6901e = c1466d.m9833d();
        this.f6902f = c1466d.m9833d();
        this.f6903g = c1466d.m9833d();
        this.f6904h = c1466d.m9833d();
        this.f6905i = c1466d.m9833d();
        this.f6906j = c1466d.m9833d();
    }

    public String toString() {
        return "NewDroneModel{CtrlType=" + this.f6900d + ", CtrlMode=" + this.f6901e + ", WP_SPA=" + this.f6902f + '}';
    }
}
