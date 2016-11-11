package org.p184b.p185a;

import org.p184b.C3275p;
import org.p184b.C3276b;
import org.p184b.C3300k;
import org.p184b.C3315n;

/* renamed from: org.b.a.h */
public class C3287h<T> extends C3276b<T> {
    private final C3275p<T> f15797a;

    public C3287h(C3275p<T> c3275p) {
        this.f15797a = c3275p;
    }

    @C3315n
    @Deprecated
    public static <T> C3275p<T> m18156a(Class<T> cls) {
        return C3287h.m18157a(C3291l.m18182a((Class) cls));
    }

    @C3315n
    public static <T> C3275p<T> m18157a(C3275p<T> c3275p) {
        return new C3287h(c3275p);
    }

    @C3315n
    public static <T> C3275p<T> m18158b(Class<T> cls) {
        return C3287h.m18157a(C3291l.m18182a((Class) cls));
    }

    @C3315n
    public static <T> C3275p<T> m18159b(T t) {
        return C3287h.m18157a(C3290k.m18175b(t));
    }

    public void m18160a(Object obj, C3300k c3300k) {
        this.f15797a.m18106a(obj, c3300k);
    }

    public void m18161a(C3300k c3300k) {
        c3300k.m18222a("is ").m18225a(this.f15797a);
    }

    public boolean m18162a(Object obj) {
        return this.f15797a.m18107a(obj);
    }
}
