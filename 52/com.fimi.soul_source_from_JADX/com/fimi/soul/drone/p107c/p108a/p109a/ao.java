package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.ao */
public class ao extends C1437b {
    public static final int f6632b = 128;
    public static final int f6633c = 13;
    private static final long serialVersionUID = 128;
    public byte f6634d;
    public double f6635e;
    public double f6636f;
    public short f6637g;
    public byte f6638h;
    public byte f6639i;

    public ao() {
        this.a = f6632b;
    }

    public ao(C1465c c1465c) {
        this.a = f6632b;
        m9666a(c1465c.f7001d);
    }

    public C1465c m9665a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6633c;
        c1465c.f7000c = f6632b;
        c1465c.f7001d.m9828b(this.f6634d);
        c1465c.f7001d.m9822a(this.f6635e);
        c1465c.f7001d.m9822a(this.f6636f);
        c1465c.f7001d.m9826a(this.f6637g);
        c1465c.f7001d.m9828b(this.f6638h);
        c1465c.f7001d.m9828b(this.f6639i);
        return c1465c;
    }

    public void m9666a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6634d = c1466d.m9833d();
        this.f6635e = (double) c1466d.m9838i();
        this.f6636f = (double) c1466d.m9838i();
        this.f6637g = c1466d.m9834e();
        this.f6638h = c1466d.m9833d();
        this.f6639i = c1466d.m9833d();
    }

    public String toString() {
        return "msg_PosionUplink_home [MILINK_MSG_ID_RCDATA=128+PacketSequence=" + this.f6634d + ", HomeLongitude=" + this.f6635e + ", HomeLatitude=" + this.f6636f + ", HomeAltitude=" + this.f6637g + ", reserve=" + this.f6638h + ", reserve2=" + this.f6639i + "]";
    }
}
