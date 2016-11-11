package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.au */
public class au extends C1437b {
    public static final int f6674b = 193;
    public static final int f6675c = 3;
    private static final long serialVersionUID = 193;
    public byte f6676d;
    public short f6677e;

    public au() {
        this.a = f6674b;
    }

    public au(C1465c c1465c) {
        this.a = f6674b;
        m9678a(c1465c.f7001d);
    }

    public C1465c m9677a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6675c;
        c1465c.f7000c = f6674b;
        c1465c.f7001d.m9828b(this.f6676d);
        c1465c.f7001d.m9826a(this.f6677e);
        return c1465c;
    }

    public void m9678a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6676d = c1466d.m9833d();
        this.f6677e = c1466d.m9834e();
    }

    public String toString() {
        return "msg_RequestVersionUplink [Type=" + this.f6676d + ", APP_version=" + this.f6677e + "]";
    }
}
