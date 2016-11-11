package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.l */
public class C1449l extends C1437b {
    public C1465c f6858b;
    private int f6859c;
    private int f6860d;

    public C1449l(C1465c c1465c) {
        this.f6858b = c1465c;
        this.a = 9;
        m9773a(c1465c.f7001d);
    }

    public C1465c m9772a() {
        return new C1465c();
    }

    public void m9773a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6859c = c1466d.m9835f();
        this.f6860d = c1466d.m9835f();
    }

    public int m9774b() {
        return this.f6859c;
    }

    public int m9775c() {
        return this.f6860d;
    }

    public String toString() {
        return "selfError" + this.f6859c;
    }
}
