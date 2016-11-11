package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.bl */
public class bl extends C1437b {
    public static final int f6789b = 203;
    public static final int f6790c = 2;
    private static final long serialVersionUID = 203;
    public byte f6791d;
    public byte f6792e;

    public bl() {
        this.a = f6789b;
    }

    public bl(C1465c c1465c) {
        this.a = f6789b;
        m9718a(c1465c.f7001d);
    }

    public C1465c m9717a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6790c;
        c1465c.f7000c = f6789b;
        c1465c.f7001d.m9828b(this.f6791d);
        c1465c.f7001d.m9828b(this.f6792e);
        return c1465c;
    }

    public void m9718a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6791d = c1466d.m9833d();
        this.f6792e = c1466d.m9833d();
    }

    public String toString() {
        return "updatedronefinishCRC [target_ID=" + this.f6791d + ", report=" + this.f6792e + "]";
    }
}
