package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.drone.c.a.a.aq */
public class aq extends C1437b {
    public static final int f6653b = 1;
    public static final int f6654c = 10;
    private static final long serialVersionUID = 1;
    public short f6655d;
    public short f6656e;
    public short f6657f;
    public short f6658g;
    public short f6659h;

    public aq() {
        this.a = f6653b;
    }

    public aq(C1465c c1465c) {
        this.a = f6653b;
        m9670a(c1465c.f7001d);
    }

    public C1465c m9669a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6654c;
        c1465c.f7000c = f6653b;
        c1465c.f7001d.m9826a(this.f6655d);
        c1465c.f7001d.m9826a(this.f6656e);
        c1465c.f7001d.m9826a(this.f6657f);
        c1465c.f7001d.m9826a(this.f6658g);
        c1465c.f7001d.m9826a(this.f6659h);
        return c1465c;
    }

    public void m9670a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6655d = c1466d.m9834e();
        this.f6656e = c1466d.m9834e();
        this.f6657f = c1466d.m9834e();
        this.f6658g = c1466d.m9834e();
        this.f6659h = c1466d.m9834e();
    }

    public String toString() {
        return "MILINK_MSG_ID_RCDATA - rc1:" + this.f6655d + " rc2:" + this.f6656e + " rc3:" + this.f6657f + " rc4:" + this.f6658g + " rc5:" + this.f6659h + C2915a.f14760f;
    }
}
