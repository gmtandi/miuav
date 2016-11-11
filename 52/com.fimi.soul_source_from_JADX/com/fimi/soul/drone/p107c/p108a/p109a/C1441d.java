package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.d */
public class C1441d extends C1437b {
    public static final int f6808b = 102;
    public static final int f6809c = 4;
    private static final long serialVersionUID = 102;
    public byte f6810d;
    public byte f6811e;
    public byte f6812f;
    public byte f6813g;

    public C1441d() {
        this.a = f6808b;
    }

    public C1441d(C1465c c1465c) {
        this.a = f6808b;
        m9726a(c1465c.f7001d);
    }

    public C1465c m9725a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6809c;
        c1465c.f7000c = f6808b;
        c1465c.f7001d.m9828b(this.f6810d);
        c1465c.f7001d.m9828b(this.f6811e);
        c1465c.f7001d.m9828b(this.f6812f);
        c1465c.f7001d.m9828b(this.f6813g);
        return c1465c;
    }

    public void m9726a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6810d = c1466d.m9833d();
        this.f6811e = c1466d.m9833d();
        this.f6812f = c1466d.m9833d();
        this.f6813g = c1466d.m9833d();
    }
}
