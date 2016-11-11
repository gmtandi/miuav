package com.fimi.soul.module.update.p121a;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.p109a.C1458u;

/* renamed from: com.fimi.soul.module.update.a.d */
public class C1904d {
    public static C1465c m12039a(int i) {
        C1465c c1465c = new C1465c();
        c1465c.f7000c = C1458u.f6934b;
        c1465c.f6999b = 2;
        c1465c.f7001d.m9828b((byte) i);
        c1465c.f7001d.m9828b((byte) 2);
        return c1465c;
    }

    public static void m12040a(C1433a c1433a) {
        C1904d.m12041a(c1433a, 0);
        C1904d.m12041a(c1433a, 9);
        C1904d.m12041a(c1433a, 10);
        C1904d.m12041a(c1433a, 6);
        C1904d.m12041a(c1433a, 3);
        C1904d.m12041a(c1433a, 1);
    }

    public static void m12041a(C1433a c1433a, int i) {
        try {
            c1433a.m9569P().m9993a(C1904d.m12039a(i));
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
