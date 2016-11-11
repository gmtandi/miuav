package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.av */
public class av extends C1437b {
    public static final int f6678b = 198;
    public static final int f6679c = 2;
    private static final long serialVersionUID = 198;
    public byte f6680d;
    public byte f6681e;

    public av() {
        this.a = f6678b;
    }

    public av(C1465c c1465c) {
        this.a = f6678b;
        m9680a(c1465c.f7001d);
    }

    public C1465c m9679a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6679c;
        c1465c.f7000c = f6678b;
        c1465c.f7001d.m9828b(this.f6680d);
        c1465c.f7001d.m9828b(this.f6681e);
        return c1465c;
    }

    public void m9680a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6680d = c1466d.m9833d();
        this.f6681e = c1466d.m9833d();
    }

    public String toString() {
        return "msg_RequestbatteryDownlink [MILINK_MSG_ID_RCDATA=198+APP_version=" + this.f6680d + ", reserve=" + this.f6681e + "]";
    }
}
