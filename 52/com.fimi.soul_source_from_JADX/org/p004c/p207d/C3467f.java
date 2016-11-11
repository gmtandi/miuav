package org.p004c.p207d;

import java.util.ArrayList;
import java.util.List;
import org.p004c.p206c.C3458a;
import org.p184b.C3275p;
import org.p184b.C3311h;

/* renamed from: org.c.d.f */
class C3467f {
    private final List<C3275p<?>> f15985a;

    C3467f() {
        this.f15985a = new ArrayList();
    }

    private C3275p<Throwable> m18935b(C3275p<?> c3275p) {
        return c3275p;
    }

    private C3275p<Throwable> m18936c() {
        return this.f15985a.size() == 1 ? m18935b((C3275p) this.f15985a.get(0)) : C3311h.m18259a(m18937d());
    }

    private List<C3275p<? super Throwable>> m18937d() {
        return new ArrayList(this.f15985a);
    }

    void m18938a(C3275p<?> c3275p) {
        this.f15985a.add(c3275p);
    }

    boolean m18939a() {
        return !this.f15985a.isEmpty();
    }

    C3275p<Throwable> m18940b() {
        return C3458a.m18837e(m18936c());
    }
}
