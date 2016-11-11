package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.at */
public class at extends C1437b {
    public static final int f6667b = 197;
    public static final int f6668c = 6;
    private static final long serialVersionUID = 197;
    public byte f6669d;
    public byte f6670e;
    public byte f6671f;
    public byte f6672g;
    public byte f6673h;

    public at() {
        this.a = f6667b;
    }

    public at(C1465c c1465c) {
        this.a = f6667b;
        m9676a(c1465c.f7001d);
    }

    public C1465c m9675a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6668c;
        c1465c.f7000c = f6667b;
        c1465c.f7001d.m9828b(this.f6669d);
        c1465c.f7001d.m9828b(this.f6670e);
        c1465c.f7001d.m9828b(this.f6671f);
        c1465c.f7001d.m9828b(this.f6672g);
        c1465c.f7001d.m9828b(this.f6673h);
        return c1465c;
    }

    public void m9676a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6669d = c1466d.m9833d();
        this.f6670e = c1466d.m9833d();
        this.f6671f = c1466d.m9833d();
        this.f6672g = c1466d.m9833d();
        this.f6673h = c1466d.m9833d();
    }

    public String toString() {
        return "msg_ReportAnswerFlightDownlink [Battery_hardware_version=" + this.f6669d + ", Battery_software_version=" + this.f6670e + ", Gimbal_hardware_version=" + this.f6671f + ", Gimbal_software_version=" + this.f6672g + ", Other_equipment_vesion=" + this.f6673h + "]";
    }
}
