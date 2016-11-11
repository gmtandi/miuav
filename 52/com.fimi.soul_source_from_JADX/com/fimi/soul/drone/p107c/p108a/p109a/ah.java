package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.ah */
public class ah extends C1437b {
    public static final int f6577b = 2;
    public static final int f6578c = 19;
    private static final long serialVersionUID = 2;
    public C1465c f6579d;
    public byte f6580e;
    public byte f6581f;
    public byte f6582g;
    public byte f6583h;
    public byte f6584i;
    public short f6585j;
    public short f6586k;
    public short f6587l;
    public byte f6588m;
    public byte f6589n;
    public short f6590o;
    public short f6591p;
    public byte f6592q;
    public byte f6593r;

    public ah() {
        this.a = f6577b;
    }

    public ah(C1465c c1465c) {
        this.a = f6577b;
        this.f6579d = c1465c;
        m9652a(c1465c.f7001d);
    }

    public C1465c m9651a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6578c;
        c1465c.f7000c = f6577b;
        c1465c.f7001d.m9828b(this.f6580e);
        c1465c.f7001d.m9828b(this.f6581f);
        c1465c.f7001d.m9828b(this.f6582g);
        c1465c.f7001d.m9828b(this.f6583h);
        c1465c.f7001d.m9828b(this.f6584i);
        c1465c.f7001d.m9826a(this.f6585j);
        c1465c.f7001d.m9826a(this.f6586k);
        c1465c.f7001d.m9826a(this.f6587l);
        c1465c.f7001d.m9828b(this.f6588m);
        c1465c.f7001d.m9828b(this.f6589n);
        c1465c.f7001d.m9826a(this.f6590o);
        c1465c.f7001d.m9826a(this.f6591p);
        c1465c.f7001d.m9828b(this.f6592q);
        c1465c.f7001d.m9828b(this.f6593r);
        return c1465c;
    }

    public void m9652a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6580e = c1466d.m9833d();
        this.f6581f = c1466d.m9833d();
        this.f6582g = c1466d.m9833d();
        this.f6583h = c1466d.m9833d();
        this.f6584i = c1466d.m9833d();
        this.f6585j = c1466d.m9834e();
        this.f6586k = c1466d.m9834e();
        this.f6587l = c1466d.m9834e();
        this.f6588m = c1466d.m9833d();
        this.f6589n = c1466d.m9833d();
        this.f6590o = c1466d.m9834e();
        this.f6591p = c1466d.m9834e();
        this.f6592q = c1466d.m9833d();
        this.f6593r = c1466d.m9833d();
    }

    public String toString() {
        return "msg_Heartbeat{Heartbeat=" + this.f6580e + ", ThrottleStick=" + this.f6581f + ", RollStick=" + this.f6582g + ", PitchStick=" + this.f6583h + ", YawStick=" + this.f6584i + ", RollAngle=" + this.f6585j + ", PitchAngle=" + this.f6586k + ", HeadingAngle=" + this.f6587l + ", DisarmCount=" + this.f6588m + ", FlightMode=" + this.f6589n + ", GroundSpeed=" + this.f6590o + ", DownVelocity=" + this.f6591p + ", rcReceiver=" + this.f6592q + ", takeoffTag=" + this.f6593r + '}';
    }
}
