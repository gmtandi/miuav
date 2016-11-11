package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.ay */
public class ay extends C1437b {
    public static final int f6696b = 6;
    public static final int f6697c = 18;
    private static final long serialVersionUID = 6;
    public float f6698d;
    public float f6699e;
    public float f6700f;
    public short f6701g;
    public short f6702h;
    public byte f6703i;
    public short f6704j;

    public ay() {
        this.a = f6696b;
    }

    public ay(C1465c c1465c) {
        this.a = f6696b;
        m9686a(c1465c.f7001d);
    }

    public C1465c m9685a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6697c;
        c1465c.f7000c = f6696b;
        c1465c.f7001d.m9823a(this.f6698d);
        c1465c.f7001d.m9823a(this.f6699e);
        c1465c.f7001d.m9824a((int) this.f6700f);
        c1465c.f7001d.m9826a(this.f6701g);
        c1465c.f7001d.m9826a(this.f6702h);
        c1465c.f7001d.m9828b(this.f6703i);
        c1465c.f7001d.m9826a(this.f6704j);
        return c1465c;
    }

    public void m9686a(C1466d c1466d) {
        c1466d.m9831c();
    }

    public String toString() {
        return "msg_followme [Packet_Sequence=Packet_Sequence, GCSLongitude=" + this.f6698d + ", GCSLatitude=" + this.f6699e + ", HomeAltitude=" + this.f6700f + ", ground_speed=" + this.f6701g + ", ground_course=" + this.f6702h + ", SatelliteNumber=" + this.f6703i + ", accuracy=" + this.f6704j + "]";
    }
}
