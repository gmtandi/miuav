package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.aj */
public class aj extends C1437b {
    public static final int f6599b = 145;
    public static final int f6600c = 3;
    private static final long serialVersionUID = 145;
    public short f6601d;
    public byte f6602e;

    public aj() {
        this.a = f6599b;
    }

    public aj(C1465c c1465c) {
        this.a = f6599b;
        m9656a(c1465c.f7001d);
    }

    public C1465c m9655a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6600c;
        c1465c.f7000c = f6599b;
        c1465c.f7001d.m9826a(this.f6601d);
        c1465c.f7001d.m9828b(this.f6602e);
        return c1465c;
    }

    public void m9656a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6601d = c1466d.m9834e();
        this.f6602e = c1466d.m9833d();
    }

    public String toString() {
        return "msg_Gohome [Packet_sequence=" + this.f6601d + "]";
    }
}
