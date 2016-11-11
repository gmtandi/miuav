package org.p184b.p185a;

import org.p184b.C3275p;
import org.p184b.C3276b;
import org.p184b.C3300k;
import org.p184b.C3315n;

/* renamed from: org.b.a.m */
public class C3292m<T> extends C3276b<T> {
    private final C3275p<T> f15803a;

    public C3292m(C3275p<T> c3275p) {
        this.f15803a = c3275p;
    }

    @C3315n
    public static <T> C3275p<T> m18187a(C3275p<T> c3275p) {
        return new C3292m(c3275p);
    }

    @C3315n
    public static <T> C3275p<T> m18188b(T t) {
        return C3292m.m18187a(C3290k.m18175b(t));
    }

    public void m18189a(C3300k c3300k) {
        c3300k.m18222a("not ").m18225a(this.f15803a);
    }

    public boolean m18190a(Object obj) {
        return !this.f15803a.m18107a(obj);
    }
}
