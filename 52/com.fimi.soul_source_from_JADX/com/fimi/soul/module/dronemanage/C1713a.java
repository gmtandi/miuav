package com.fimi.soul.module.dronemanage;

import android.os.Handler;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p116g.C1547g;
import it.p074a.p075a.C2799f;

/* renamed from: com.fimi.soul.module.dronemanage.a */
public class C1713a {
    private static final int f8384f = 1;
    C1547g f8385a;
    C1433a f8386b;
    C1465c f8387c;
    int f8388d;
    Handler f8389e;

    public C1713a() {
        this.f8389e = new C1714b(this);
    }

    public void m11186a() {
        this.f8388d = 0;
        if (this.f8385a != null) {
            this.f8385a.m10132c();
            return;
        }
        this.f8385a = new C1547g(C2799f.f14282t, new C1715c(this));
        this.f8385a.m10133d();
    }

    public void m11187a(C1433a c1433a, C1465c c1465c) {
        this.f8386b = c1433a;
        this.f8387c = c1465c;
    }

    public void m11188b() {
        this.f8389e.sendEmptyMessage(f8384f);
    }

    public boolean m11189c() {
        return this.f8385a.m10131b();
    }
}
