package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.ar */
public class ar extends C1437b {
    public static final int f6660b = 130;
    public static final int f6661c = 2;
    private static final long serialVersionUID = 130;
    public short f6662d;

    public ar() {
        this.a = f6660b;
    }

    public ar(C1465c c1465c) {
        this.a = f6660b;
        m9672a(c1465c.f7001d);
    }

    public C1465c m9671a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6661c;
        c1465c.f7000c = f6660b;
        c1465c.f7001d.m9826a(this.f6662d);
        return c1465c;
    }

    public void m9672a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6662d = c1466d.m9834e();
    }

    public String toString() {
        return "msg_NumberUplink [MILINK_MSG_ID_RCDATA=130+number=" + this.f6662d + "]";
    }
}
