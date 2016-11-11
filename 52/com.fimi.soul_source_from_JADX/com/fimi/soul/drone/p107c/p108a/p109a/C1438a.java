package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.a */
public class C1438a extends C1437b {
    public static final int f6534b = 152;
    public static final int f6535c = 3;
    private static final long serialVersionUID = 152;
    public short f6536d;
    public byte f6537e;

    public C1438a() {
        this.a = f6534b;
    }

    public C1438a(C1465c c1465c) {
        this.a = f6534b;
        m9634a(c1465c.f7001d);
    }

    public C1465c m9633a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6535c;
        c1465c.f7000c = f6534b;
        c1465c.f7001d.m9826a(this.f6536d);
        c1465c.f7001d.m9828b(this.f6537e);
        return c1465c;
    }

    public void m9634a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6536d = c1466d.m9834e();
        this.f6537e = c1466d.m9833d();
    }

    public String toString() {
        return "BeginFollowme [Packet_sequence=" + this.f6536d + ", Para1=" + this.f6537e + "]";
    }
}
