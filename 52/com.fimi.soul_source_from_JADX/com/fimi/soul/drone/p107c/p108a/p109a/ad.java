package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.ad */
public class ad extends C1437b {
    public static final int f6553b = 193;
    public static final int f6554c = 22;
    private static final long serialVersionUID = 193;
    public byte f6555d;
    public byte f6556e;
    public char f6557f;
    public short f6558g;
    public long f6559h;
    public long f6560i;
    public long f6561j;
    public long f6562k;

    public ad() {
        this.a = f6553b;
    }

    public ad(C1465c c1465c) {
        this.a = f6553b;
        m9642a(c1465c.f7001d);
    }

    public C1465c m9641a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6554c;
        c1465c.f7000c = f6553b;
        c1465c.f7001d.m9828b(this.f6555d);
        c1465c.f7001d.m9828b(this.f6556e);
        c1465c.f7001d.m9821a(this.f6557f);
        c1465c.f7001d.m9826a(this.f6558g);
        c1465c.f7001d.m9825a(this.f6559h);
        c1465c.f7001d.m9825a(this.f6560i);
        c1465c.f7001d.m9825a(this.f6561j);
        c1465c.f7001d.m9825a(this.f6562k);
        return c1465c;
    }

    public void m9642a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6555d = c1466d.m9833d();
        this.f6556e = c1466d.m9833d();
        this.f6557f = c1466d.m9841l();
        this.f6558g = c1466d.m9834e();
        this.f6559h = c1466d.m9842m();
        this.f6560i = c1466d.m9842m();
        this.f6561j = c1466d.m9842m();
        this.f6562k = c1466d.m9842m();
    }

    public String toString() {
        return "msg_FCAnswerDownlink{type=" + this.f6555d + ", Model=" + this.f6556e + ", FC_hardware_version=" + this.f6557f + ", Software_version=" + this.f6558g + ", IDA=" + this.f6559h + ", IDB=" + this.f6560i + ", IDC=" + this.f6561j + ", reserve=" + this.f6562k + '}';
    }
}
