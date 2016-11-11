package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.u */
public class C1458u extends C1437b {
    public static final int f6934b = 204;
    public static final int f6935c = 2;
    private static final long serialVersionUID = 204;
    public byte f6936d;
    public byte f6937e;

    public C1458u() {
        this.a = f6934b;
    }

    public C1458u(C1465c c1465c) {
        this.a = f6934b;
        m9795a(c1465c.f7001d);
    }

    public C1465c m9794a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6935c;
        c1465c.f7000c = f6934b;
        c1465c.f7001d.m9828b(this.f6936d);
        c1465c.f7001d.m9828b(this.f6937e);
        return c1465c;
    }

    public void m9795a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6936d = c1466d.m9833d();
        this.f6937e = c1466d.m9833d();
    }
}
