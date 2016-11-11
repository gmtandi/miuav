package com.fimi.soul.module.dronemanage;

import android.os.Handler;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p116g.C1547g;
import it.p074a.p075a.C2799f;

public class ak {
    static C1547g f8418a = null;
    static C1433a f8419b = null;
    static C1465c f8420c = null;
    static int f8421d = 0;
    static Handler f8422e = null;
    private static final int f8423f = 1;

    static {
        f8422e = new al();
    }

    public static void m11200a() {
        f8421d = 0;
        if (f8418a != null) {
            f8418a.m10132c();
            return;
        }
        f8418a = new C1547g(C2799f.f14282t, new am());
        f8418a.m10133d();
    }

    public static void m11201a(C1433a c1433a, C1465c c1465c) {
        f8419b = c1433a;
        f8420c = c1465c;
    }

    public static void m11202b() {
        f8422e.sendEmptyMessage(f8423f);
    }

    public boolean m11203c() {
        return f8418a.m10131b();
    }
}
