package org.p004c.p198b;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import org.p004c.C3567m;

/* renamed from: org.c.b.h */
public class C3451h {
    public static final Comparator<Method> f15969a;
    public static final Comparator<Method> f15970b;

    static {
        f15969a = new C3452i();
        f15970b = new C3453j();
    }

    private C3451h() {
    }

    private static Comparator<Method> m18811a(C3567m c3567m) {
        return c3567m == null ? f15969a : c3567m.m19298a().m19248a();
    }

    public static Method[] m18812a(Class<?> cls) {
        Comparator a = C3451h.m18811a((C3567m) cls.getAnnotation(C3567m.class));
        Method[] declaredMethods = cls.getDeclaredMethods();
        if (a != null) {
            Arrays.sort(declaredMethods, a);
        }
        return declaredMethods;
    }
}
