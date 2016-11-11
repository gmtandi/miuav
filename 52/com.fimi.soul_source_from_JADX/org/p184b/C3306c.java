package org.p184b;

import org.p122a.p123a.C2915a;

/* renamed from: org.b.c */
public abstract class C3306c<T> {
    public static final C3309f<Object> f15816a;

    static {
        f15816a = new C3309f();
    }

    private C3306c() {
    }

    public static <T> C3306c<T> m18245a() {
        return f15816a;
    }

    public static <T> C3306c<T> m18246a(T t, C3300k c3300k) {
        return new C3308e(t, c3300k, null);
    }

    public abstract <U> C3306c<U> m18247a(C3310g<? super T, U> c3310g);

    public final boolean m18248a(C3275p<T> c3275p) {
        return m18249a((C3275p) c3275p, C2915a.f14760f);
    }

    public abstract boolean m18249a(C3275p<T> c3275p, String str);

    public final <U> C3306c<U> m18250b(C3310g<? super T, U> c3310g) {
        return m18247a((C3310g) c3310g);
    }
}
