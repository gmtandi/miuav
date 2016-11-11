package org.p004c.p005e.p006a;

import org.p004c.p005e.C3507d;

/* renamed from: org.c.e.a.a */
public abstract class C3323a {
    public static final C3323a f15832a;

    static {
        f15832a = new C3487b();
    }

    public static C3323a m18368b(C3507d c3507d) {
        return new C3488c(c3507d);
    }

    public abstract String m18369a();

    public C3323a m18370a(C3323a c3323a) {
        return (c3323a == this || c3323a == f15832a) ? this : new C3489d(this, this, c3323a);
    }

    public void m18371a(Object obj) {
        if (obj instanceof C0128e) {
            ((C0128e) obj).m211a(this);
        }
    }

    public abstract boolean m18372a(C3507d c3507d);
}
