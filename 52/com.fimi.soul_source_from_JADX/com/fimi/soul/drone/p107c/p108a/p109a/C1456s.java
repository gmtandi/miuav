package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.s */
public class C1456s extends C1437b {
    public static final int f6916b = 7;
    public static final int f6917c = 11;
    private static final long serialVersionUID = 7;
    public byte f6918d;
    public short f6919e;
    public float f6920f;
    public float f6921g;
    public byte f6922h;
    public short f6923i;
    public byte f6924j;
    public byte f6925k;
    public byte f6926l;
    public byte f6927m;
    public byte f6928n;

    public C1456s() {
        this.a = f6916b;
    }

    public C1456s(C1465c c1465c) {
        this.a = f6916b;
        m9791a(c1465c.f7001d);
    }

    public C1465c m9790a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6917c;
        c1465c.f7000c = f6916b;
        c1465c.f7001d.m9826a(this.f6919e);
        c1465c.f7001d.m9823a(this.f6920f);
        c1465c.f7001d.m9823a(this.f6921g);
        c1465c.f7001d.m9828b(this.f6922h);
        return c1465c;
    }

    public void m9791a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6918d = c1466d.m9833d();
        this.f6919e = c1466d.m9834e();
        this.f6920f = c1466d.m9838i();
        this.f6921g = c1466d.m9838i();
        this.f6922h = c1466d.m9833d();
        this.f6923i = c1466d.m9834e();
        this.f6924j = c1466d.m9833d();
        this.f6925k = c1466d.m9833d();
        this.f6926l = c1466d.m9833d();
        this.f6927m = c1466d.m9833d();
        this.f6928n = c1466d.m9833d();
    }

    public String toString() {
        return "NoFlyArea{number=" + this.f6919e + ", Forbiden_Longitude=" + this.f6920f + ", Forbiden_Latitude=" + this.f6921g + ", type=" + this.f6922h + '}';
    }
}
