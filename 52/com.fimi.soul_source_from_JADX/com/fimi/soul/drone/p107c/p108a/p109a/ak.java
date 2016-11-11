package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.ak */
public class ak extends C1437b {
    public static final int f6603b = 4;
    public static final int f6604c = 16;
    private static final long serialVersionUID = 4;
    public float f6605d;
    public float f6606e;
    public float f6607f;
    public byte f6608g;
    public float f6609h;
    public C1465c f6610i;
    public byte f6611j;

    public ak() {
        this.a = f6603b;
    }

    public ak(C1465c c1465c) {
        this.a = f6603b;
        this.f6610i = c1465c;
        m9658a(c1465c.f7001d);
    }

    public C1465c m9657a() {
        return null;
    }

    public void m9658a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6605d = c1466d.m9838i();
        this.f6606e = c1466d.m9838i();
        this.f6607f = c1466d.m9839j();
        this.f6608g = c1466d.m9833d();
        this.f6609h = c1466d.m9838i();
        this.f6611j = c1466d.m9833d();
    }

    public String toString() {
        return "msg_MessionState [HomeLongitude=" + this.f6605d + ", HomeLatitude=" + this.f6606e + ", HomeAltitude=" + this.f6607f + ", CurrentWaypoint=" + this.f6608g + "]";
    }
}
