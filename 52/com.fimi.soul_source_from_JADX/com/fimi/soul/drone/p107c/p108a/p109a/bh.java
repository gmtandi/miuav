package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.bh */
public class bh extends C1437b {
    public static final int f6770b = 199;
    public static final int f6771c = 2;
    private static final long serialVersionUID = 199;
    public byte f6772d;
    public byte f6773e;

    public bh() {
        this.a = f6770b;
    }

    public bh(C1465c c1465c) {
        this.a = f6770b;
        m9706a(c1465c.f7001d);
    }

    public C1465c m9705a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6771c;
        c1465c.f7000c = f6770b;
        c1465c.f7001d.m9828b(this.f6772d);
        c1465c.f7001d.m9828b(this.f6773e);
        return c1465c;
    }

    public void m9706a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6772d = c1466d.m9833d();
        this.f6773e = c1466d.m9833d();
    }

    public String toString() {
        return "updateOrderbackreport [Packet_sequence=, target_ID=" + this.f6772d + ", report=" + this.f6773e + "]";
    }
}
