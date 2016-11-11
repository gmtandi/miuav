package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.bn */
public class bn extends C1437b {
    public static final int f6797b = 203;
    public static final int f6798c = 1;
    private static final long serialVersionUID = 203;
    public byte f6799d;

    public bn() {
        this.a = f6797b;
    }

    public bn(C1465c c1465c) {
        this.a = f6797b;
        m9722a(c1465c.f7001d);
    }

    public C1465c m9721a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6798c;
        c1465c.f7000c = f6797b;
        c1465c.f7001d.m9828b(this.f6799d);
        return c1465c;
    }

    public void m9722a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6799d = c1466d.m9833d();
    }

    public String toString() {
        return "updatedronefinishbackCRC [report=" + this.f6799d + "]";
    }
}
