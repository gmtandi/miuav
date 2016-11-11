package org.p184b.p185a;

import java.lang.reflect.Array;
import org.p184b.C3275p;
import org.p184b.C3276b;
import org.p184b.C3300k;
import org.p184b.C3315n;

/* renamed from: org.b.a.k */
public class C3290k<T> extends C3276b<T> {
    private final Object f15800a;

    public C3290k(T t) {
        this.f15800a = t;
    }

    private static boolean m18174a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : (obj2 == null || !C3290k.m18177c(obj)) ? obj.equals(obj2) : C3290k.m18177c(obj2) && C3290k.m18176b(obj, obj2);
    }

    @C3315n
    public static <T> C3275p<T> m18175b(T t) {
        return new C3290k(t);
    }

    private static boolean m18176b(Object obj, Object obj2) {
        return C3290k.m18178c(obj, obj2) && C3290k.m18179d(obj, obj2);
    }

    private static boolean m18177c(Object obj) {
        return obj.getClass().isArray();
    }

    private static boolean m18178c(Object obj, Object obj2) {
        return Array.getLength(obj) == Array.getLength(obj2);
    }

    private static boolean m18179d(Object obj, Object obj2) {
        for (int i = 0; i < Array.getLength(obj); i++) {
            if (!C3290k.m18174a(Array.get(obj, i), Array.get(obj2, i))) {
                return false;
            }
        }
        return true;
    }

    public void m18180a(C3300k c3300k) {
        c3300k.m18221a(this.f15800a);
    }

    public boolean m18181a(Object obj) {
        return C3290k.m18174a(obj, this.f15800a);
    }
}
