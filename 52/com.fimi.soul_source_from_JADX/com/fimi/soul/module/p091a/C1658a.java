package com.fimi.soul.module.p091a;

import android.os.Handler;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p116g.C1547g;
import it.p074a.p075a.C2799f;

/* renamed from: com.fimi.soul.module.a.a */
public class C1658a {
    static int f7839a = 0;
    private static final int f7840f = 1;
    private static final int f7841g = 2;
    Handler f7842b;
    private C1547g f7843c;
    private C1433a f7844d;
    private volatile C1465c f7845e;
    private C1662d f7846h;

    public C1658a(C1662d c1662d) {
        this.f7842b = new C1659b(this);
        this.f7846h = c1662d;
    }

    public static C1658a m10799a(C1662d c1662d) {
        return new C1658a(c1662d);
    }

    public void m10803a() {
        f7839a = 0;
        if (this.f7843c == null || this.f7843c.m10131b()) {
            this.f7843c = new C1547g(C2799f.f14282t, new C1660c(this));
            this.f7843c.m10133d();
            return;
        }
        this.f7843c.m10132c();
    }

    public void m10804a(C1433a c1433a, C1465c c1465c) {
        this.f7844d = c1433a;
        this.f7845e = c1465c;
    }

    public void m10805b() {
        this.f7842b.sendEmptyMessage(f7841g);
    }

    public boolean m10806c() {
        return this.f7843c.m10131b();
    }
}
