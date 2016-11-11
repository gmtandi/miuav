package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.n */
public class C1451n extends C1437b {
    public static final int f6870b = 133;
    public static final int f6871c = 16;
    private static final long serialVersionUID = 133;
    public short f6872d;
    public float f6873e;
    public float f6874f;
    public short f6875g;
    public byte f6876h;
    public byte f6877i;
    public byte f6878j;
    public byte f6879k;

    public C1451n() {
        this.a = f6870b;
    }

    public C1451n(C1465c c1465c) {
        this.a = f6870b;
        m9779a(c1465c.f7001d);
    }

    public C1465c m9778a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6871c;
        c1465c.f7000c = f6870b;
        c1465c.f7001d.m9826a(this.f6872d);
        c1465c.f7001d.m9823a(this.f6873e);
        c1465c.f7001d.m9823a(this.f6874f);
        c1465c.f7001d.m9826a(this.f6875g);
        c1465c.f7001d.m9828b(this.f6876h);
        c1465c.f7001d.m9828b(this.f6877i);
        c1465c.f7001d.m9828b(this.f6878j);
        c1465c.f7001d.m9828b(this.f6879k);
        return c1465c;
    }

    public void m9779a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6872d = c1466d.m9834e();
        this.f6873e = c1466d.m9838i();
        this.f6874f = c1466d.m9838i();
        this.f6875g = c1466d.m9834e();
        this.f6876h = c1466d.m9833d();
        this.f6877i = c1466d.m9833d();
    }
}
