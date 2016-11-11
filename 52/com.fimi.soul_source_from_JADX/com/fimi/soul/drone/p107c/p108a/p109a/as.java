package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.as */
public class as extends C1437b {
    public static final int f6663b = 150;
    public static final int f6664c = 3;
    private static final long serialVersionUID = 150;
    public short f6665d;
    public byte f6666e;

    public as() {
        this.a = f6663b;
    }

    public as(C1465c c1465c) {
        this.a = f6663b;
        m9674a(c1465c.f7001d);
    }

    public C1465c m9673a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6664c;
        c1465c.f7000c = f6663b;
        c1465c.f7001d.m9826a(this.f6665d);
        c1465c.f7001d.m9828b(this.f6666e);
        return c1465c;
    }

    public void m9674a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6665d = c1466d.m9834e();
        this.f6666e = c1466d.m9833d();
    }

    public String toString() {
        return "msg_Gohome [Packet_sequence=" + this.f6665d + "]";
    }
}
