package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.drone.c.a.a.ai */
public class ai extends C1437b {
    public static final int f6594b = 147;
    public static final int f6595c = 4;
    private static final long serialVersionUID = 147;
    public short f6596d;
    public byte f6597e;
    public byte f6598f;

    public ai() {
        this.a = f6594b;
    }

    public ai(C1465c c1465c) {
        this.a = f6594b;
        m9654a(c1465c.f7001d);
    }

    public C1465c m9653a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6595c;
        c1465c.f7000c = f6594b;
        c1465c.f7001d.m9826a(this.f6596d);
        c1465c.f7001d.m9828b(this.f6597e);
        c1465c.f7001d.m9828b(this.f6598f);
        return c1465c;
    }

    public void m9654a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6596d = c1466d.m9834e();
        this.f6597e = c1466d.m9833d();
        this.f6598f = c1466d.m9833d();
    }

    public String toString() {
        return "msg_Gohome [Packet_sequence=" + this.f6596d + "]" + C2915a.f14760f;
    }
}
