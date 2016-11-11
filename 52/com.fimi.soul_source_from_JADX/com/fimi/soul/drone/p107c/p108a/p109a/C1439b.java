package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.b */
public class C1439b extends C1437b {
    public static final int f6712b = 152;
    public static final int f6713c = 4;
    private static final long serialVersionUID = 152;
    public short f6714d;
    public byte f6715e;
    public byte f6716f;

    public C1439b() {
        this.a = f6712b;
    }

    public C1439b(C1465c c1465c) {
        this.a = f6712b;
        m9690a(c1465c.f7001d);
    }

    public C1465c m9689a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6713c;
        c1465c.f7000c = f6712b;
        c1465c.f7001d.m9826a(this.f6714d);
        c1465c.f7001d.m9828b(this.f6715e);
        c1465c.f7001d.m9828b(this.f6716f);
        return c1465c;
    }

    public void m9690a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6714d = c1466d.m9834e();
        this.f6715e = c1466d.m9833d();
        this.f6716f = c1466d.m9833d();
    }

    public String toString() {
        return "BeginFollowmeback{Packet_sequence=" + this.f6714d + ", cmd=" + this.f6715e + ", report=" + this.f6716f + '}';
    }
}
