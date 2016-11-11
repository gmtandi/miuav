package org.p184b.p185a;

import org.p184b.C3275p;
import org.p184b.C3281t;
import org.p184b.C3300k;
import org.p184b.C3315n;

/* renamed from: org.b.a.g */
public class C3286g<T> extends C3281t<Iterable<T>> {
    private final C3275p<? super T> f15796a;

    public C3286g(C3275p<? super T> c3275p) {
        this.f15796a = c3275p;
    }

    @C3315n
    public static <U> C3275p<Iterable<U>> m18152a(C3275p<U> c3275p) {
        return new C3286g(c3275p);
    }

    public void m18153a(C3300k c3300k) {
        c3300k.m18222a("every item is ").m18225a(this.f15796a);
    }

    public boolean m18154a(Iterable<T> iterable, C3300k c3300k) {
        for (Object next : iterable) {
            if (!this.f15796a.m18107a(next)) {
                c3300k.m18222a("an item ");
                this.f15796a.m18106a(next, c3300k);
                return false;
            }
        }
        return true;
    }

    public /* synthetic */ boolean m18155b(Object obj, C3300k c3300k) {
        return m18154a((Iterable) obj, c3300k);
    }
}
