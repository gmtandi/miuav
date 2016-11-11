package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.ax */
public class ax extends C1437b {
    public static final int f6692b = 146;
    public static final int f6693c = 3;
    private static final long serialVersionUID = 146;
    public short f6694d;
    public byte f6695e;

    public ax() {
        this.a = f6692b;
    }

    public ax(C1465c c1465c) {
        this.a = f6692b;
        m9684a(c1465c.f7001d);
    }

    public C1465c m9683a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6693c;
        c1465c.f7000c = f6692b;
        c1465c.f7001d.m9826a(this.f6694d);
        c1465c.f7001d.m9828b(this.f6695e);
        return c1465c;
    }

    public void m9684a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6694d = c1466d.m9834e();
        this.f6695e = c1466d.m9833d();
    }

    public String toString() {
        return "msg_Gohome [Packet_sequence=" + this.f6694d + "]";
    }
}
