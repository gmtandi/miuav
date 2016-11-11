package com.fimi.soul.drone.p110d;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import java.util.Calendar;

/* renamed from: com.fimi.soul.drone.d.u */
public class C1494u {
    public static void m9886a(C1433a c1433a) {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = 11;
        c1465c.f7000c = 18;
        Calendar instance = Calendar.getInstance();
        int i = instance.get(1);
        int i2 = instance.get(2) + 1;
        int i3 = instance.get(5);
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        int i6 = instance.get(13);
        c1465c.f7001d.m9826a((short) i);
        c1465c.f7001d.m9828b((byte) i2);
        c1465c.f7001d.m9828b((byte) i3);
        c1465c.f7001d.m9828b((byte) i4);
        c1465c.f7001d.m9828b((byte) i5);
        c1465c.f7001d.m9828b((byte) i6);
        c1465c.f7001d.m9828b((byte) 0);
        c1465c.f7001d.m9828b((byte) 0);
        c1465c.f7001d.m9828b((byte) 0);
        c1465c.f7001d.m9828b((byte) 0);
        c1433a.m9569P().m9993a(c1465c);
    }
}
