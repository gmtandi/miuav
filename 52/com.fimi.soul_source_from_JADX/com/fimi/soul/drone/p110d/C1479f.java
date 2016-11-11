package com.fimi.soul.drone.p110d;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.p109a.au;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.module.update.p121a.C1901a;
import java.util.Map;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.drone.d.f */
public class C1479f {
    public static final au f7037a;
    public static C1465c f7038b;

    static {
        f7037a = new au();
        f7037a.a = Opcodes.INSTANCEOF;
        f7038b = f7037a.m9677a();
    }

    public static void m9860a() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m9861a(C1433a c1433a) {
        f7037a.f6676d = (byte) 0;
        c1433a.m9569P().m9993a(f7037a.m9677a());
    }

    public static void m9862b(C1433a c1433a) {
        f7037a.f6676d = (byte) 1;
        c1433a.m9569P().m9993a(f7037a.m9677a());
    }

    public static void m9863c(C1433a c1433a) {
        f7037a.f6676d = (byte) 3;
        c1433a.m9569P().m9993a(f7037a.m9677a());
    }

    public static void m9864d(C1433a c1433a) {
        f7037a.f6676d = (byte) 10;
        c1433a.m9569P().m9993a(f7037a.m9677a());
    }

    public static void m9865e(C1433a c1433a) {
        f7037a.f6676d = (byte) 9;
        c1433a.m9569P().m9993a(f7037a.m9677a());
    }

    public static void m9866f(C1433a c1433a) {
        f7037a.f6676d = (byte) 6;
        c1433a.m9569P().m9993a(f7037a.m9677a());
    }

    public static void m9867g(C1433a c1433a) {
        f7037a.f6676d = (byte) 5;
        c1433a.m9569P().m9993a(f7037a.m9677a());
    }

    public static void m9868h(C1433a c1433a) {
        C1479f.m9863c(c1433a);
        C1479f.m9860a();
        C1479f.m9862b(c1433a);
        C1479f.m9860a();
        C1479f.m9861a(c1433a);
        C1479f.m9860a();
        C1479f.m9865e(c1433a);
        C1479f.m9860a();
        C1479f.m9864d(c1433a);
        C1479f.m9860a();
        C1479f.m9866f(c1433a);
        C1479f.m9860a();
    }

    public static void m9869i(C1433a c1433a) {
        Map b = C1901a.m12002a().m12006b();
        if (!b.containsKey(Integer.valueOf(3))) {
            C1664h.m10813a(c1433a).m10825b(3);
            C1479f.m9863c(c1433a);
            C1479f.m9860a();
        }
        if (!b.containsKey(Integer.valueOf(1))) {
            C1664h.m10813a(c1433a).m10825b(1);
            C1479f.m9862b(c1433a);
            C1479f.m9860a();
        }
        if (!b.containsKey(Integer.valueOf(0))) {
            C1664h.m10813a(c1433a).m10825b(0);
            C1479f.m9861a(c1433a);
            C1479f.m9860a();
        }
        if (!b.containsKey(Integer.valueOf(9))) {
            C1664h.m10813a(c1433a).m10825b(9);
            C1479f.m9865e(c1433a);
            C1479f.m9860a();
        }
        if (!b.containsKey(Integer.valueOf(10))) {
            C1664h.m10813a(c1433a).m10825b(10);
            C1479f.m9864d(c1433a);
            C1479f.m9860a();
        }
        if (!b.containsKey(Integer.valueOf(6))) {
            C1664h.m10813a(c1433a).m10825b(6);
            C1479f.m9866f(c1433a);
            C1479f.m9860a();
        }
        if (!b.containsKey(Integer.valueOf(5))) {
            C1664h.m10813a(c1433a).m10825b(5);
            C1479f.m9867g(c1433a);
            C1479f.m9860a();
        }
    }
}
