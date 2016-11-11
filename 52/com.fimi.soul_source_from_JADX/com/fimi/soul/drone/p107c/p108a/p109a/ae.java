package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;
import com.fimi.soul.drone.p117h.ah;

/* renamed from: com.fimi.soul.drone.c.a.a.ae */
public class ae extends C1437b {
    public static final int f6563b = 16;
    public static final int f6564c = 112;
    public static final int f6565d = 113;
    private static final int f6566g = 3;
    public C1465c f6567e;
    private ah f6568f;

    public ae(int i) {
        this.a = i;
    }

    public ae(C1465c c1465c, int i) {
        this.a = i;
        this.f6567e = c1465c;
        m9644a(c1465c.f7001d);
    }

    public C1465c m9643a() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f6566g;
        c1465c.f7000c = this.a;
        c1465c.f7001d.m9828b(this.f6568f.m10266a());
        c1465c.f7001d.m9828b((byte) this.f6568f.m10269b());
        c1465c.f7001d.m9828b(this.f6568f.m10271c());
        return c1465c;
    }

    public void m9644a(C1466d c1466d) {
        this.f6568f = new ah(null);
        c1466d.m9831c();
        c1466d.m9833d();
        this.f6568f.m10267a(c1466d.m9833d());
        this.f6568f.m10268a((short) c1466d.m9833d());
        this.f6568f.m10270b(c1466d.m9833d());
    }

    public void m9645a(ah ahVar) {
        this.f6568f = ahVar;
    }

    public ah m9646b() {
        return this.f6568f;
    }
}
