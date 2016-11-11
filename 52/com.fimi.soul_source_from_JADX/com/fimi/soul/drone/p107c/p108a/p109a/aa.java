package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.aa */
public class aa extends C1437b {
    public static final int f6538b = 198;
    public static final int f6539c = 10;
    private static final long serialVersionUID = 198;
    public short f6540d;
    public short f6541e;
    public short f6542f;
    public short f6543g;
    public short f6544h;

    public aa() {
        this.a = f6538b;
    }

    public aa(C1465c c1465c) {
        this.a = f6538b;
        m9636a(c1465c.f7001d);
    }

    public C1465c m9635a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6539c;
        c1465c.f7000c = f6538b;
        c1465c.f7001d.m9826a(this.f6540d);
        c1465c.f7001d.m9826a(this.f6541e);
        c1465c.f7001d.m9826a(this.f6543g);
        c1465c.f7001d.m9826a(this.f6544h);
        c1465c.f7001d.m9826a(this.f6542f);
        return c1465c;
    }

    public void m9636a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6540d = c1466d.m9834e();
        this.f6541e = c1466d.m9834e();
        this.f6543g = c1466d.m9834e();
        this.f6544h = c1466d.m9834e();
        this.f6542f = c1466d.m9834e();
    }

    public String toString() {
        return "msg_BatteryAnswerUplink [Battery_IDA=" + this.f6540d + ", Battery_IDB=" + this.f6541e + ", Cycle_count=" + this.f6542f + ", Design_capacity=" + this.f6543g + ", Full_charge_capacity=" + this.f6544h + "]";
    }
}
