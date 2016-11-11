package com.fimi.soul.module.setting;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.module.setting.p */
public class C1886p {
    public static final int f9605a = 7;
    public static final int f9606b = 120;
    public static final int f9607c = 30;
    public static int f9608d;
    public static int f9609e;
    public static int f9610f;
    public static int f9611g;
    private static C1433a f9612h;
    private static C1886p f9613i;

    static {
        f9608d = 16;
        f9609e = 17;
        f9610f = 0;
        f9611g = 1880;
    }

    public static C1886p m11892a(C1433a c1433a) {
        f9612h = c1433a;
        if (f9613i == null) {
            f9613i = new C1886p();
        }
        return f9613i;
    }

    private C1465c m11893b() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f9605a;
        c1465c.f7000c = Opcodes.I2F;
        c1465c.f7001d.m9828b((byte) 0);
        c1465c.f7001d.m9828b((byte) f9609e);
        c1465c.f7001d.m9823a(0.0f);
        c1465c.f7001d.m9828b((byte) 0);
        return c1465c;
    }

    private C1465c m11894b(float f) {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = 6;
        c1465c.f7000c = Opcodes.I2F;
        c1465c.f7001d.m9828b((byte) 0);
        c1465c.f7001d.m9828b((byte) f9608d);
        c1465c.f7001d.m9823a(f);
        return c1465c;
    }

    public void m11895a() {
        f9612h.m9569P().m9993a(m11893b());
    }

    public void m11896a(float f) {
        f9612h.m9569P().m9993a(m11894b(f));
    }
}
