package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.ag */
public class ag extends C1437b {
    public static final int f6573b = 192;
    public static final int f6574c = 2;
    private static final long serialVersionUID = 192;
    public byte f6575d;
    public byte f6576e;

    public ag() {
        this.a = f6573b;
    }

    public ag(C1465c c1465c) {
        this.a = f6573b;
        m9650a(c1465c.f7001d);
    }

    public C1465c m9649a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6574c;
        c1465c.f7000c = f6573b;
        c1465c.f7001d.m9828b(this.f6575d);
        c1465c.f7001d.m9828b(this.f6576e);
        return c1465c;
    }

    public void m9650a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6575d = c1466d.m9833d();
        this.f6576e = c1466d.m9833d();
    }

    public String toString() {
        return "msg_GoupOrderUplink [MILINK_MSG_ID_RCDATA=192+Packet_sequence=" + this.f6575d + ", target_ID=" + this.f6576e + "]";
    }
}