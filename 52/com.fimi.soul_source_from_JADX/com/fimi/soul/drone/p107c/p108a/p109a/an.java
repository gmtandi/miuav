package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.an */
public class an extends C1437b {
    public static final int f6626b = 129;
    public static final int f6627c = 11;
    private static final long serialVersionUID = 129;
    public byte f6628d;
    public int f6629e;
    public int f6630f;
    public short f6631g;

    public an() {
        this.a = f6626b;
    }

    public an(C1465c c1465c) {
        this.a = f6626b;
        m9664a(c1465c.f7001d);
    }

    public C1465c m9663a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6627c;
        c1465c.f7000c = f6626b;
        c1465c.f7001d.m9828b(this.f6628d);
        c1465c.f7001d.m9829b(this.f6629e);
        c1465c.f7001d.m9829b(this.f6630f);
        c1465c.f7001d.m9826a(this.f6631g);
        return c1465c;
    }

    public void m9664a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6628d = c1466d.m9833d();
        this.f6629e = c1466d.m9835f();
        this.f6630f = c1466d.m9835f();
        this.f6631g = c1466d.m9834e();
    }

    public String toString() {
        return "msg_PosionUpLink [MILINK_MSG_ID_RCDATA=129+PacketSequence=" + this.f6628d + ", HomeLongitude=" + this.f6629e + ", HomeLatitude=" + this.f6630f + ", HomeAltitude=" + this.f6631g + "]";
    }
}
