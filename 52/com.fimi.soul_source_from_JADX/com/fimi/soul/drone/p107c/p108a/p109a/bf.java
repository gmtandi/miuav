package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.bf */
public class bf extends C1437b {
    public static final int f6760b = 200;
    public static final int f6761c = 6;
    private static final long serialVersionUID = 200;
    public byte f6762d;
    public byte f6763e;
    public int f6764f;

    public bf() {
        this.a = f6760b;
    }

    public bf(C1465c c1465c) {
        this.a = f6760b;
        m9702a(c1465c.f7001d);
    }

    public C1465c m9701a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6761c;
        c1465c.f7000c = f6760b;
        c1465c.f7001d.m9828b(this.f6762d);
        c1465c.f7001d.m9828b(this.f6763e);
        c1465c.f7001d.m9829b(this.f6764f);
        return c1465c;
    }

    public void m9702a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6762d = c1466d.m9833d();
        this.f6763e = c1466d.m9833d();
        this.f6764f = c1466d.m9835f();
    }

    public String toString() {
        return "updateDroneOrder [Packet_sequence=" + this.f6762d + ", target_ID=" + this.f6763e + ", length=" + this.f6764f + "]";
    }
}
