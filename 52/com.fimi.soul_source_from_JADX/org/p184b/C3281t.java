package org.p184b;

import org.p184b.p186b.C3303b;

/* renamed from: org.b.t */
public abstract class C3281t<T> extends C3276b<T> {
    private static final C3303b f15787a;
    private final Class<?> f15788b;

    static {
        f15787a = new C3303b("matchesSafely", 2, 0);
    }

    protected C3281t() {
        this(f15787a);
    }

    protected C3281t(Class<?> cls) {
        this.f15788b = cls;
    }

    protected C3281t(C3303b c3303b) {
        this.f15788b = c3303b.m18240a(getClass());
    }

    public final void m18136a(Object obj, C3300k c3300k) {
        if (obj == null || !this.f15788b.isInstance(obj)) {
            super.m18109a(obj, c3300k);
        } else {
            m18138b(obj, c3300k);
        }
    }

    public final boolean m18137a(Object obj) {
        return obj != null && this.f15788b.isInstance(obj) && m18138b(obj, new C3314l());
    }

    protected abstract boolean m18138b(T t, C3300k c3300k);
}
