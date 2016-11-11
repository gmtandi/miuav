package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.am */
public class am extends C1437b {
    public static final int f6616b = 3;
    public static final int f6617c = 19;
    private static final long serialVersionUID = 3;
    public short f6618d;
    public byte f6619e;
    public float f6620f;
    public float f6621g;
    public float f6622h;
    public float f6623i;
    public byte f6624j;
    public C1465c f6625k;

    public am() {
        this.a = f6616b;
    }

    public am(C1465c c1465c) {
        this.a = f6616b;
        this.f6625k = c1465c;
        m9662a(c1465c.f7001d);
    }

    public C1465c m9661a() {
        return null;
    }

    public void m9662a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6618d = c1466d.m9834e();
        this.f6619e = c1466d.m9833d();
        this.f6620f = c1466d.m9838i();
        this.f6621g = c1466d.m9838i();
        this.f6622h = c1466d.m9839j();
        this.f6623i = c1466d.m9838i();
        this.f6624j = c1466d.m9833d();
    }

    public String toString() {
        return "msg_PosionDownlink{flightTime=" + this.f6618d + ", SatelliteNumber=" + this.f6619e + ", CurrentLongitude=" + this.f6620f + ", CurrentLatitude=" + this.f6621g + ", Height=" + this.f6622h + ", Distance=" + this.f6623i + ", RFsignalstrength=" + this.f6624j + '}';
    }
}
