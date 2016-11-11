package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.z */
public class C1463z extends C1437b {
    public static final int f6984b = 130;
    public static final int f6985c = 19;
    private static final long serialVersionUID = 130;
    public short f6986d;
    public float f6987e;
    public float f6988f;
    public short f6989g;
    public short f6990h;
    public short f6991i;
    public byte f6992j;
    public short f6993k;
    private volatile C1465c f6994l;

    public C1463z() {
        this.a = f6984b;
    }

    public C1463z(C1465c c1465c) {
        this.f6994l = c1465c;
        this.a = f6984b;
        m9805a(c1465c.f7001d);
    }

    public C1465c m9804a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6985c;
        c1465c.f7000c = f6984b;
        c1465c.f7001d.m9826a(this.f6986d);
        c1465c.f7001d.m9823a(this.f6987e);
        c1465c.f7001d.m9823a(this.f6988f);
        c1465c.f7001d.m9826a(this.f6989g);
        c1465c.f7001d.m9826a(this.f6990h);
        c1465c.f7001d.m9826a(this.f6991i);
        c1465c.f7001d.m9828b(this.f6992j);
        c1465c.f7001d.m9826a(this.f6993k);
        return c1465c;
    }

    public void m9805a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6986d = c1466d.m9834e();
        this.f6987e = c1466d.m9838i();
        this.f6988f = c1466d.m9838i();
        this.f6989g = c1466d.m9834e();
        this.f6990h = c1466d.m9834e();
        this.f6991i = c1466d.m9834e();
        this.f6992j = c1466d.m9833d();
        this.f6993k = c1466d.m9834e();
    }

    public C1465c m9806b() {
        return this.f6994l;
    }

    public String toString() {
        return "msg_SettingWaypointUplink [number=" + this.f6986d + ", Longitude=" + this.f6987e + ", Latitude=" + this.f6988f + ", Altitude=" + this.f6989g + ", yaw_angle=" + this.f6990h + ", hover_time=" + this.f6991i + ", speed=" + this.f6992j + ", Number_to_be_transmited=" + this.f6993k + "]";
    }
}
