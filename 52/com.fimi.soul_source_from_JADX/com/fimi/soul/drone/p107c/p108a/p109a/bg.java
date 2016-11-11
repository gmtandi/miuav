package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.bg */
public class bg extends C1437b {
    public static final int f6765b = 199;
    public static final int f6766c = 3;
    private static final long serialVersionUID = 199;
    public byte f6767d;
    public byte f6768e;
    public byte f6769f;

    public bg() {
        this.a = f6765b;
    }

    public bg(C1465c c1465c) {
        this.a = f6765b;
        m9704a(c1465c.f7001d);
    }

    public C1465c m9703a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6766c;
        c1465c.f7000c = f6765b;
        c1465c.f7001d.m9828b(this.f6767d);
        c1465c.f7001d.m9828b(this.f6768e);
        c1465c.f7001d.m9828b(this.f6769f);
        return c1465c;
    }

    public void m9704a(C1466d c1466d) {
        c1466d.m9831c();
    }

    public String toString() {
        return "updateOrder [Packet_sequence=, target_ID=" + this.f6767d + "]";
    }
}
