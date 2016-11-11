package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.al */
public class al extends C1437b {
    public static final int f6612b = 149;
    public static final int f6613c = 3;
    private static final long serialVersionUID = 149;
    public short f6614d;
    public byte f6615e;

    public al() {
        this.a = f6612b;
    }

    public al(C1465c c1465c) {
        this.a = f6612b;
        m9660a(c1465c.f7001d);
    }

    public C1465c m9659a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6613c;
        c1465c.f7000c = f6612b;
        c1465c.f7001d.m9826a(this.f6614d);
        c1465c.f7001d.m9828b(this.f6615e);
        return c1465c;
    }

    public void m9660a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6614d = c1466d.m9834e();
        this.f6615e = c1466d.m9833d();
    }

    public String toString() {
        return "msg_Gohome [Packet_sequence=" + this.f6614d + "]";
    }
}
