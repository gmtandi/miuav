package org.p004c.p188a.p189a;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.p004c.p005e.C3507d;
import org.p004c.p187f.C3321o;
import org.p004c.p187f.p192a.C3384i;
import org.p004c.p187f.p192a.C3526f;

/* renamed from: org.c.a.a.a */
public class C3322a extends C3321o {
    public C3322a(Class<?> cls, C3384i c3384i) {
        super((Class) cls, c3384i);
        try {
            m18334a(C3324b.m18375a(C3322a.m18364c((Class) cls), C3322a.m18360b((Class) cls), C3322a.m18367e(cls), C3322a.m18366d(cls)));
            C3322a.m18358a(m18344d());
        } catch (Throwable e) {
            throw new C3526f(e);
        }
    }

    private static void m18358a(C3507d c3507d) {
        if (!C3322a.m18365c(c3507d)) {
            C3322a.m18362b(c3507d);
        }
        Iterator it = c3507d.m19094b().iterator();
        while (it.hasNext()) {
            C3322a.m18358a((C3507d) it.next());
        }
    }

    private static Set<Class<?>> m18360b(Class<?> cls) {
        C3326d c3326d = (C3326d) cls.getAnnotation(C3326d.class);
        return C3322a.m18361b(c3326d == null ? null : c3326d.m18392a());
    }

    private static Set<Class<?>> m18361b(Class<?>... clsArr) {
        Object hashSet = new HashSet();
        if (clsArr != null) {
            Collections.addAll(hashSet, clsArr);
        }
        return hashSet;
    }

    private static void m18362b(C3507d c3507d) {
        Iterator it = c3507d.m19094b().iterator();
        while (it.hasNext()) {
            C3507d c3507d2 = (C3507d) it.next();
            if (c3507d2.m19093b(C3327e.class) != null) {
                throw new C3526f("Category annotations on Parameterized classes are not supported on individual methods.");
            }
            C3322a.m18362b(c3507d2);
        }
    }

    private static boolean m18363b(Set<Class<?>> set, Class<?> cls) {
        for (Class isAssignableFrom : set) {
            if (cls.isAssignableFrom(isAssignableFrom)) {
                return true;
            }
        }
        return false;
    }

    private static boolean m18364c(Class<?> cls) {
        C3326d c3326d = (C3326d) cls.getAnnotation(C3326d.class);
        return c3326d == null || c3326d.m18393b();
    }

    private static boolean m18365c(C3507d c3507d) {
        Iterator it = c3507d.m19094b().iterator();
        while (it.hasNext()) {
            if (((C3507d) it.next()).m19101i() == null) {
                return false;
            }
        }
        return true;
    }

    private static Set<Class<?>> m18366d(Class<?> cls) {
        C3325c c3325c = (C3325c) cls.getAnnotation(C3325c.class);
        return C3322a.m18361b(c3325c == null ? null : c3325c.m18390a());
    }

    private static boolean m18367e(Class<?> cls) {
        C3325c c3325c = (C3325c) cls.getAnnotation(C3325c.class);
        return c3325c == null || c3325c.m18391b();
    }
}
