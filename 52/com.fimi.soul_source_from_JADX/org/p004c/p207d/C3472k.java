package org.p004c.p207d;

import org.p004c.p005e.C3507d;
import org.p004c.p187f.p192a.C3377k;

/* renamed from: org.c.d.k */
public class C3472k extends C3377k {
    private final C3377k f15990a;

    public C3472k(C3377k c3377k, Iterable<C3460r> iterable, C3507d c3507d) {
        this.f15990a = C3472k.m18951a(c3377k, iterable, c3507d);
    }

    private static C3377k m18951a(C3377k c3377k, Iterable<C3460r> iterable, C3507d c3507d) {
        for (C3460r a : iterable) {
            c3377k = a.m18905a(c3377k, c3507d);
        }
        return c3377k;
    }

    public void m18952a() {
        this.f15990a.m18589a();
    }
}
