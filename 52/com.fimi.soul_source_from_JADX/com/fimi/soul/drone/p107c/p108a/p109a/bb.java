package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.bb */
public class bb extends C1437b {
    public static final int f6722b = 99;
    public static final int f6723c = 5;
    private static final long serialVersionUID = 99;
    public byte f6724d;
    public byte f6725e;
    public byte f6726f;
    public byte f6727g;
    public byte f6728h;
    public byte f6729i;

    public bb() {
        this.a = f6722b;
    }

    public bb(C1465c c1465c) {
        this.a = f6722b;
        m9694a(c1465c.f7001d);
    }

    public C1465c m9693a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6723c;
        c1465c.f7000c = f6722b;
        c1465c.f7001d.m9828b(this.f6724d);
        c1465c.f7001d.m9828b(this.f6725e);
        c1465c.f7001d.m9828b(this.f6726f);
        c1465c.f7001d.m9828b(this.f6727g);
        c1465c.f7001d.m9828b(this.f6728h);
        return c1465c;
    }

    public void m9694a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6724d = c1466d.m9833d();
        this.f6725e = c1466d.m9833d();
        this.f6726f = c1466d.m9833d();
        this.f6727g = c1466d.m9833d();
        this.f6728h = c1466d.m9833d();
        this.f6729i = c1466d.m9833d();
    }

    public String toString() {
        return "msg_remotecontrol [ number1=" + this.f6724d + ", number2=" + this.f6725e + ", number3=" + this.f6726f + ", number4=" + this.f6727g + ", number5=" + this.f6728h + "]";
    }
}
