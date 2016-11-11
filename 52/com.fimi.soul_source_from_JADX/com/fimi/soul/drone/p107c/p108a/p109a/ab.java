package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.ab */
public class ab extends C1437b {
    public static final int f6545b = 148;
    public static final int f6546c = 3;
    private static final long serialVersionUID = 148;
    public short f6547d;
    public byte f6548e;

    public ab() {
        this.a = f6545b;
    }

    public ab(C1465c c1465c) {
        this.a = f6545b;
        m9638a(c1465c.f7001d);
    }

    public C1465c m9637a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6546c;
        c1465c.f7000c = f6545b;
        c1465c.f7001d.m9826a(this.f6547d);
        c1465c.f7001d.m9828b(this.f6548e);
        return c1465c;
    }

    public void m9638a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6547d = c1466d.m9834e();
        this.f6548e = c1466d.m9833d();
    }

    public String toString() {
        return "msg_Gohome [Packet_sequence=" + this.f6547d + "]";
    }
}
