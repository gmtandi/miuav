package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.be */
public class be extends C1437b {
    public static final int f6755b = 200;
    public static final int f6756c = 3;
    private static final long serialVersionUID = 200;
    public byte f6757d;
    public byte f6758e;
    public byte f6759f;

    public be() {
        this.a = f6755b;
    }

    public be(C1465c c1465c) {
        this.a = f6755b;
        m9700a(c1465c.f7001d);
    }

    public C1465c m9699a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6756c;
        c1465c.f7000c = f6755b;
        c1465c.f7001d.m9828b(this.f6757d);
        c1465c.f7001d.m9828b(this.f6758e);
        c1465c.f7001d.m9828b(this.f6759f);
        return c1465c;
    }

    public void m9700a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6757d = c1466d.m9833d();
        this.f6758e = c1466d.m9833d();
        this.f6759f = c1466d.m9833d();
    }

    public String toString() {
        return "updateOrderbackreport [Packet_sequence=" + this.f6757d + ", target_ID=" + this.f6758e + ", report=" + this.f6759f + "]";
    }
}
