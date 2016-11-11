package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.v */
public class C1459v extends C1437b {
    public static final int f6938b = 106;
    public static final int f6939c = 1;
    private static final long serialVersionUID = 106;
    public byte f6940d;

    public C1459v() {
        this.a = f6938b;
    }

    public C1459v(C1465c c1465c) {
        this.a = f6938b;
        m9797a(c1465c.f7001d);
    }

    public C1465c m9796a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6939c;
        c1465c.f7000c = f6938b;
        c1465c.f7001d.m9828b(this.f6940d);
        return c1465c;
    }

    public void m9797a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6940d = c1466d.m9833d();
    }
}
