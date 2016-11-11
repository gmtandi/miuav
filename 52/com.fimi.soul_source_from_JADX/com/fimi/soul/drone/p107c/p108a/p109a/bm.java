package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.bm */
public class bm extends C1437b {
    public static final int f6793b = 202;
    public static final int f6794c = 2;
    private static final long serialVersionUID = 202;
    public byte f6795d;
    public byte f6796e;

    public bm() {
        this.a = f6793b;
    }

    public bm(C1465c c1465c) {
        this.a = f6793b;
        m9720a(c1465c.f7001d);
    }

    public C1465c m9719a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6794c;
        c1465c.f7000c = f6793b;
        c1465c.f7001d.m9828b(this.f6795d);
        c1465c.f7001d.m9828b(this.f6796e);
        return c1465c;
    }

    public void m9720a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6795d = c1466d.m9833d();
        this.f6796e = c1466d.m9833d();
    }

    public String toString() {
        return "updatedronefinishback [report=" + this.f6796e + "]";
    }
}
