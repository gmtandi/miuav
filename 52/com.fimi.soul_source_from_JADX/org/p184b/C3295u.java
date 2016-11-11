package org.p184b;

import org.p184b.p186b.C3303b;

/* renamed from: org.b.u */
public abstract class C3295u<T> extends C3276b<T> {
    private static final C3303b f15805a;
    private final Class<?> f15806b;

    static {
        f15805a = new C3303b("matchesSafely", 1, 0);
    }

    protected C3295u() {
        this(f15805a);
    }

    protected C3295u(Class<?> cls) {
        this.f15806b = cls;
    }

    protected C3295u(C3303b c3303b) {
        this.f15806b = c3303b.m18240a(getClass());
    }

    public final void m18201a(Object obj, C3300k c3300k) {
        if (obj == null) {
            super.m18109a(obj, c3300k);
        } else if (this.f15806b.isInstance(obj)) {
            m18203b(obj, c3300k);
        } else {
            c3300k.m18222a("was a ").m18222a(obj.getClass().getName()).m18222a(" (").m18221a(obj).m18222a(")");
        }
    }

    public final boolean m18202a(Object obj) {
        return obj != null && this.f15806b.isInstance(obj) && m18204b(obj);
    }

    protected void m18203b(T t, C3300k c3300k) {
        super.m18109a(t, c3300k);
    }

    protected abstract boolean m18204b(T t);
}
