package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.m */
public class C1450m extends C1437b {
    public static final int f6861b = 138;
    public static final int f6862c = 14;
    private static final long serialVersionUID = 138;
    public short f6863d;
    public short f6864e;
    public short f6865f;
    public short f6866g;
    public short f6867h;
    public short f6868i;
    public short f6869j;

    public C1450m() {
        this.a = f6861b;
    }

    public C1450m(C1465c c1465c) {
        this.a = f6861b;
        m9777a(c1465c.f7001d);
    }

    public C1465c m9776a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6862c;
        c1465c.f7000c = f6861b;
        c1465c.f7001d.m9826a(this.f6863d);
        c1465c.f7001d.m9826a(this.f6864e);
        c1465c.f7001d.m9826a(this.f6865f);
        c1465c.f7001d.m9826a(this.f6866g);
        c1465c.f7001d.m9826a(this.f6867h);
        c1465c.f7001d.m9826a(this.f6868i);
        c1465c.f7001d.m9826a(this.f6869j);
        return c1465c;
    }

    public void m9777a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6863d = c1466d.m9834e();
        this.f6864e = c1466d.m9834e();
        this.f6865f = c1466d.m9834e();
        this.f6866g = c1466d.m9834e();
        this.f6867h = c1466d.m9834e();
        this.f6868i = c1466d.m9834e();
        this.f6869j = c1466d.m9834e();
    }

    public String toString() {
        return "FlyControlParamter{roll_gain=" + this.f6863d + ", pitch_gain=" + this.f6864e + ", yaw_gain=" + this.f6865f + ", altitude_gain=" + this.f6866g + ", safety_type=" + this.f6867h + ", RTL_climb_height=" + this.f6868i + ", vehicle_type=" + this.f6869j + '}';
    }
}
