package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.af */
public class af extends C1437b {
    public static final int f6569b = 144;
    public static final int f6570c = 3;
    private static final long serialVersionUID = 144;
    public short f6571d;
    public byte f6572e;

    public af() {
        this.a = f6569b;
    }

    public af(C1465c c1465c) {
        this.a = f6569b;
        m9648a(c1465c.f7001d);
    }

    public C1465c m9647a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6570c;
        c1465c.f7000c = f6569b;
        c1465c.f7001d.m9826a(this.f6571d);
        c1465c.f7001d.m9828b(this.f6572e);
        return c1465c;
    }

    public void m9648a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6571d = c1466d.m9834e();
        this.f6572e = c1466d.m9833d();
    }

    public String toString() {
        return "msg_Gohome [Packet_sequence=" + this.f6571d + "]";
    }
}
