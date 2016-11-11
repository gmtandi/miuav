package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.t */
public class C1457t extends C1437b {
    public static final int f6929b = 204;
    public static final int f6930c = 3;
    private static final long serialVersionUID = 204;
    public byte f6931d;
    public byte f6932e;
    public byte f6933f;

    public C1457t() {
        this.a = f6929b;
    }

    public C1457t(C1465c c1465c) {
        this.a = f6929b;
        m9793a(c1465c.f7001d);
    }

    public C1465c m9792a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6930c;
        c1465c.f7000c = f6929b;
        c1465c.f7001d.m9828b(this.f6931d);
        c1465c.f7001d.m9828b(this.f6932e);
        c1465c.f7001d.m9828b(this.f6933f);
        return c1465c;
    }

    public void m9793a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6931d = c1466d.m9833d();
        this.f6932e = c1466d.m9833d();
        this.f6933f = c1466d.m9833d();
    }
}
