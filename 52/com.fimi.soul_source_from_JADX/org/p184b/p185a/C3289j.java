package org.p184b.p185a;

import java.util.ArrayList;
import org.p184b.C3275p;
import org.p184b.C3281t;
import org.p184b.C3300k;
import org.p184b.C3315n;

/* renamed from: org.b.a.j */
public class C3289j<T> extends C3281t<Iterable<? super T>> {
    private final C3275p<? super T> f15799a;

    public C3289j(C3275p<? super T> c3275p) {
        this.f15799a = c3275p;
    }

    @C3315n
    public static <T> C3275p<Iterable<? super T>> m18167a(C3275p<? super T> c3275p) {
        return new C3289j(c3275p);
    }

    @C3315n
    public static <T> C3275p<Iterable<T>> m18168a(T... tArr) {
        Iterable arrayList = new ArrayList(tArr.length);
        for (Object b : tArr) {
            arrayList.add(C3289j.m18170b(b));
        }
        return C3278a.m18113a(arrayList);
    }

    @C3315n
    public static <T> C3275p<Iterable<T>> m18169a(C3275p<? super T>... c3275pArr) {
        Iterable arrayList = new ArrayList(c3275pArr.length);
        for (C3275p c3289j : c3275pArr) {
            arrayList.add(new C3289j(c3289j));
        }
        return C3278a.m18113a(arrayList);
    }

    @C3315n
    public static <T> C3275p<Iterable<? super T>> m18170b(T t) {
        return new C3289j(C3290k.m18175b(t));
    }

    public void m18171a(C3300k c3300k) {
        c3300k.m18222a("a collection containing ").m18225a(this.f15799a);
    }

    protected boolean m18172a(Iterable<? super T> iterable, C3300k c3300k) {
        boolean z = false;
        for (Object next : iterable) {
            if (this.f15799a.m18107a(next)) {
                return true;
            }
            if (z) {
                c3300k.m18222a(", ");
            }
            this.f15799a.m18106a(next, c3300k);
            z = true;
        }
        return false;
    }

    protected /* synthetic */ boolean m18173b(Object obj, C3300k c3300k) {
        return m18172a((Iterable) obj, c3300k);
    }
}
