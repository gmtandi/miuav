package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.j */
public class C1447j extends C1437b {
    public static final int f6841b = 134;
    public static final int f6842c = 7;
    private static final long serialVersionUID = 134;
    public byte f6843d;
    public byte f6844e;
    public float f6845f;
    public byte f6846g;

    public C1447j() {
        this.a = f6841b;
    }

    public C1447j(C1465c c1465c) {
        this.a = f6841b;
        m9767a(c1465c.f7001d);
    }

    public C1465c m9766a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6842c;
        c1465c.f7000c = f6841b;
        c1465c.f7001d.m9828b(this.f6843d);
        c1465c.f7001d.m9828b(this.f6844e);
        c1465c.f7001d.m9823a(this.f6845f);
        c1465c.f7001d.m9828b(this.f6846g);
        return c1465c;
    }

    public void m9767a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6843d = c1466d.m9833d();
        this.f6844e = c1466d.m9833d();
        this.f6845f = c1466d.m9838i();
        this.f6846g = c1466d.m9833d();
    }

    public String toString() {
        return "DroneRTHMessage{Packet_sequence=" + this.f6843d + ", cmdID=" + this.f6844e + ", height=" + this.f6845f + ", report=" + this.f6846g + '}';
    }
}
