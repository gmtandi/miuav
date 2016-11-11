package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.ac */
public class ac extends C1437b {
    public static final int f6549b = 151;
    public static final int f6550c = 3;
    private static final long serialVersionUID = 151;
    public short f6551d;
    public byte f6552e;

    public ac() {
        this.a = f6549b;
    }

    public ac(C1465c c1465c) {
        this.a = f6549b;
        m9640a(c1465c.f7001d);
    }

    public C1465c m9639a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6550c;
        c1465c.f7000c = f6549b;
        c1465c.f7001d.m9826a(this.f6551d);
        c1465c.f7001d.m9828b(this.f6552e);
        return c1465c;
    }

    public void m9640a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6551d = c1466d.m9834e();
        this.f6552e = c1466d.m9833d();
    }

    public String toString() {
        return "msg_Gohome [Packet_sequence=" + this.f6551d + "]";
    }
}
