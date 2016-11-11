package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.bj */
public class bj extends C1437b {
    public static final int f6779b = 201;
    public static final int f6780c = 4;
    private static final long serialVersionUID = 201;
    public byte f6781d;
    public short f6782e;
    public byte f6783f;

    public bj() {
        this.a = f6779b;
    }

    public bj(C1465c c1465c) {
        this.a = f6779b;
        m9714a(c1465c.f7001d);
    }

    public C1465c m9713a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6780c;
        c1465c.f7000c = f6779b;
        c1465c.f7001d.m9828b(this.f6781d);
        c1465c.f7001d.m9826a(this.f6782e);
        c1465c.f7001d.m9828b(this.f6783f);
        return c1465c;
    }

    public void m9714a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6781d = c1466d.m9833d();
        this.f6782e = c1466d.m9834e();
        this.f6783f = c1466d.m9833d();
    }

    public String toString() {
        return "updatedronebackreport [Packet_sequence=" + this.f6782e + ", report=" + this.f6783f + "]";
    }
}
