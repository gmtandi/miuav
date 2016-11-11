package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.bc */
public class bc extends C1437b {
    public static final int f6730b = 100;
    public static final int f6731c = 11;
    private static final long serialVersionUID = 100;
    public byte f6732d;
    public byte f6733e;
    public byte f6734f;
    public byte f6735g;
    public byte f6736h;
    public byte f6737i;
    public byte f6738j;
    public byte f6739k;
    public byte f6740l;
    public byte f6741m;
    public byte f6742n;
    public C1465c f6743o;

    public bc() {
        this.a = f6730b;
    }

    public bc(C1465c c1465c) {
        this.f6743o = c1465c;
        this.a = f6730b;
        m9696a(c1465c.f7001d);
    }

    public C1465c m9695a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6731c;
        c1465c.f7000c = f6730b;
        c1465c.f7001d.m9828b(this.f6732d);
        c1465c.f7001d.m9828b(this.f6733e);
        c1465c.f7001d.m9828b(this.f6734f);
        c1465c.f7001d.m9828b(this.f6735g);
        c1465c.f7001d.m9828b(this.f6736h);
        c1465c.f7001d.m9828b(this.f6737i);
        c1465c.f7001d.m9828b(this.f6738j);
        c1465c.f7001d.m9828b(this.f6739k);
        c1465c.f7001d.m9828b(this.f6740l);
        c1465c.f7001d.m9828b(this.f6741m);
        c1465c.f7001d.m9828b(this.f6742n);
        return c1465c;
    }

    public void m9696a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6732d = c1466d.m9833d();
        this.f6733e = c1466d.m9833d();
        this.f6734f = c1466d.m9833d();
        this.f6735g = c1466d.m9833d();
        this.f6736h = c1466d.m9833d();
        this.f6737i = c1466d.m9833d();
        this.f6738j = c1466d.m9833d();
        this.f6739k = c1466d.m9833d();
        this.f6740l = c1466d.m9833d();
        this.f6741m = c1466d.m9833d();
        this.f6742n = c1466d.m9833d();
    }

    public String toString() {
        return "msg_simulatorINfo [ADC0=" + this.f6732d + ", ADC1=" + this.f6733e + ", ADC2=" + this.f6734f + ", ADC3=" + this.f6735g + ", ADC4=" + this.f6736h + ", ADC5=" + this.f6737i + ", battery=" + this.f6738j + ", switch1=" + this.f6739k + ", switch2=" + this.f6740l + ", left3=" + this.f6741m + ", right3=" + this.f6742n + "]";
    }
}
