package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.ap */
public class ap extends C1437b {
    public static final int f6640b = 129;
    public static final int f6641c = 19;
    private static final long serialVersionUID = 129;
    public byte f6642d;
    public byte f6643e;
    public float f6644f;
    public float f6645g;
    public short f6646h;
    public short f6647i;
    public byte f6648j;
    public byte f6649k;
    public byte f6650l;
    public byte f6651m;
    public byte f6652n;

    public ap() {
        this.a = f6640b;
    }

    public ap(C1465c c1465c) {
        this.a = f6640b;
        m9668a(c1465c.f7001d);
    }

    public C1465c m9667a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6641c;
        c1465c.f7000c = f6640b;
        return c1465c;
    }

    public void m9668a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6642d = c1466d.m9833d();
        this.f6643e = c1466d.m9833d();
        this.f6644f = c1466d.m9838i();
        this.f6645g = c1466d.m9838i();
        this.f6646h = c1466d.m9834e();
        this.f6647i = c1466d.m9834e();
        this.f6648j = c1466d.m9833d();
        this.f6649k = c1466d.m9833d();
        this.f6650l = c1466d.m9833d();
        this.f6651m = c1466d.m9833d();
        this.f6652n = c1466d.m9833d();
    }

    public String toString() {
        return "msg_PosionUplink_poi{PacketSequence=" + this.f6642d + ", Opration_Code=" + this.f6643e + ", POI_Longitude=" + this.f6644f + ", POI_Latitude=" + this.f6645g + ", POI_Altitude=" + this.f6646h + ", Radius=" + this.f6647i + ", speek=" + this.f6648j + ", Start_Point_Pole_Angle=" + this.f6649k + ", Paral=" + this.f6650l + ", yaw_mode=" + this.f6651m + ", report=" + this.f6652n + '}';
    }
}
