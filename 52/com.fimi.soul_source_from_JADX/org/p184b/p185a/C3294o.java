package org.p184b.p185a;

import org.p184b.C3275p;
import org.p184b.C3276b;
import org.p184b.C3300k;
import org.p184b.C3315n;

/* renamed from: org.b.a.o */
public class C3294o<T> extends C3276b<T> {
    private final T f15804a;

    public C3294o(T t) {
        this.f15804a = t;
    }

    @C3315n
    public static <T> C3275p<T> m18197b(T t) {
        return new C3294o(t);
    }

    @C3315n
    public static <T> C3275p<T> m18198c(T t) {
        return new C3294o(t);
    }

    public void m18199a(C3300k c3300k) {
        c3300k.m18222a("sameInstance(").m18221a(this.f15804a).m18222a(")");
    }

    public boolean m18200a(Object obj) {
        return obj == this.f15804a;
    }
}
