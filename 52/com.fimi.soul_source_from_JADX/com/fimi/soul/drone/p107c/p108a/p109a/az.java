package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.az */
public class az extends C1437b {
    public static final int f6705b = 132;
    public static final int f6706c = 12;
    private static final long serialVersionUID = 132;
    public short f6707d;
    public float f6708e;
    public float f6709f;
    public byte f6710g;
    public byte f6711h;

    public az() {
        this.a = f6705b;
    }

    public az(C1465c c1465c) {
        this.a = f6705b;
        m9688a(c1465c.f7001d);
    }

    public C1465c m9687a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6706c;
        c1465c.f7000c = f6705b;
        c1465c.f7001d.m9826a(this.f6707d);
        c1465c.f7001d.m9823a(this.f6708e);
        c1465c.f7001d.m9823a(this.f6709f);
        c1465c.f7001d.m9828b(this.f6710g);
        c1465c.f7001d.m9828b(this.f6711h);
        return c1465c;
    }

    public void m9688a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6707d = c1466d.m9834e();
        this.f6708e = c1466d.m9838i();
        this.f6709f = c1466d.m9838i();
        this.f6710g = c1466d.m9833d();
        this.f6711h = c1466d.m9833d();
    }

    public String toString() {
        return "msg_forbiddenpointUplink{number=" + this.f6707d + ", Longitude=" + this.f6708e + ", Latitude=" + this.f6709f + ", Altitude=" + this.f6710g + ", Operation_code=" + this.f6711h + '}';
    }
}
