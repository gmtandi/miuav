package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.c.a.a.ba */
public class ba extends C1437b {
    public static final int f6717b = 98;
    public static final int f6718c = 3;
    private static final long serialVersionUID = 98;
    public byte f6719d;
    public byte f6720e;
    public byte f6721f;

    public ba() {
        this.a = f6717b;
    }

    public ba(C1465c c1465c) {
        this.a = f6717b;
        m9692a(c1465c.f7001d);
    }

    public C1465c m9691a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6718c;
        c1465c.f7000c = f6717b;
        c1465c.f7001d.m9828b(this.f6719d);
        c1465c.f7001d.m9828b(this.f6720e);
        c1465c.f7001d.m9828b(this.f6721f);
        return c1465c;
    }

    public void m9692a(C1466d c1466d) {
        c1466d.m9831c();
        this.f6719d = c1466d.m9833d();
        this.f6720e = c1466d.m9833d();
        this.f6721f = c1466d.m9833d();
    }

    public String toString() {
        return "msg_sendinfocontrol [model=" + this.f6719d + ", status=" + this.f6720e + ", msgid=" + this.a + ", pack()=" + m9691a() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }
}
