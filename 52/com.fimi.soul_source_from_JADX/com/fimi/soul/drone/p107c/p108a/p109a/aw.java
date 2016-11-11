package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.aw */
public class aw extends C1437b {
    public static final int f6682b = 131;
    public static final int f6683c = 19;
    private static final long serialVersionUID = 131;
    public short f6684d;
    public float f6685e;
    public float f6686f;
    public short f6687g;
    public short f6688h;
    public short f6689i;
    public byte f6690j;
    public short f6691k;

    public aw() {
        this.a = f6682b;
    }

    public aw(C1465c c1465c) {
        this.a = f6682b;
        m9682a(c1465c.f7001d);
    }

    public C1465c m9681a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6683c;
        c1465c.f7000c = f6682b;
        c1465c.f7001d.m9826a(this.f6684d);
        c1465c.f7001d.m9823a(this.f6685e);
        c1465c.f7001d.m9823a(this.f6686f);
        c1465c.f7001d.m9826a(this.f6687g);
        c1465c.f7001d.m9826a(this.f6688h);
        c1465c.f7001d.m9826a(this.f6689i);
        c1465c.f7001d.m9828b(this.f6690j);
        c1465c.f7001d.m9826a(this.f6691k);
        return c1465c;
    }

    public void m9682a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6684d = c1466d.m9834e();
        this.f6685e = c1466d.m9838i();
        this.f6686f = c1466d.m9838i();
        this.f6687g = c1466d.m9834e();
        this.f6688h = c1466d.m9834e();
        this.f6689i = c1466d.m9834e();
        this.f6690j = c1466d.m9833d();
        this.f6691k = c1466d.m9834e();
    }

    public String toString() {
        return "msg_SettingWaypointUplink [number=" + this.f6684d + ", Longitude=" + this.f6685e + ", Latitude=" + this.f6686f + ", Altitude=" + this.f6687g + ", yaw_angle=" + this.f6688h + ", hover_time=" + this.f6689i + ", report=" + this.f6690j + ", Number_to_be_transmited=" + this.f6691k + "]";
    }
}
