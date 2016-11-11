package org.p004c.p188a.p189a;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.p006a.C3323a;

/* renamed from: org.c.a.a.b */
public class C3324b extends C3323a {
    private final Set<Class<?>> f15833b;
    private final Set<Class<?>> f15834c;
    private final boolean f15835d;
    private final boolean f15836e;

    protected C3324b(boolean z, Set<Class<?>> set, boolean z2, Set<Class<?>> set2) {
        this.f15835d = z;
        this.f15836e = z2;
        this.f15833b = C3324b.m18373a((Set) set);
        this.f15834c = C3324b.m18373a((Set) set2);
    }

    private static Set<Class<?>> m18373a(Set<Class<?>> set) {
        Set hashSet = new HashSet();
        if (set != null) {
            hashSet.addAll(set);
        }
        hashSet.remove(null);
        return hashSet;
    }

    public static C3324b m18374a(Class<?> cls) {
        return C3324b.m18376a(true, cls);
    }

    public static C3324b m18375a(boolean z, Set<Class<?>> set, boolean z2, Set<Class<?>> set2) {
        return new C3324b(z, set, z2, set2);
    }

    public static C3324b m18376a(boolean z, Class<?>... clsArr) {
        if (!C3324b.m18384c((Class[]) clsArr)) {
            return C3324b.m18375a(z, C3322a.m18361b((Class[]) clsArr), true, null);
        }
        throw new NullPointerException("has null category");
    }

    public static C3324b m18377a(Class<?>... clsArr) {
        return C3324b.m18376a(true, (Class[]) clsArr);
    }

    private boolean m18378a(Set<Class<?>> set, Set<Class<?>> set2) {
        for (Class a : set2) {
            if (C3322a.m18363b(set, a)) {
                return true;
            }
        }
        return false;
    }

    public static C3324b m18379b(Class<?> cls) {
        return C3324b.m18380b(true, cls);
    }

    public static C3324b m18380b(boolean z, Class<?>... clsArr) {
        if (!C3324b.m18384c((Class[]) clsArr)) {
            return C3324b.m18375a(true, null, z, C3322a.m18361b((Class[]) clsArr));
        }
        throw new NullPointerException("has null category");
    }

    public static C3324b m18381b(Class<?>... clsArr) {
        return C3324b.m18380b(true, (Class[]) clsArr);
    }

    private boolean m18382b(Set<Class<?>> set, Set<Class<?>> set2) {
        for (Class a : set2) {
            if (!C3322a.m18363b(set, a)) {
                return false;
            }
        }
        return true;
    }

    private boolean m18383c(C3507d c3507d) {
        Set d = C3324b.m18385d(c3507d);
        if (d.isEmpty()) {
            return this.f15833b.isEmpty();
        }
        if (!this.f15834c.isEmpty()) {
            if (this.f15836e) {
                if (m18378a(d, this.f15834c)) {
                    return false;
                }
            } else if (m18382b(d, this.f15834c)) {
                return false;
            }
        }
        return this.f15833b.isEmpty() ? true : this.f15835d ? m18378a(d, this.f15833b) : m18382b(d, this.f15833b);
    }

    private static boolean m18384c(Class<?>... clsArr) {
        if (clsArr == null) {
            return false;
        }
        for (Class<?> cls : clsArr) {
            if (cls == null) {
                return true;
            }
        }
        return false;
    }

    private static Set<Class<?>> m18385d(C3507d c3507d) {
        Object hashSet = new HashSet();
        Collections.addAll(hashSet, C3324b.m18387f(c3507d));
        Collections.addAll(hashSet, C3324b.m18387f(C3324b.m18386e(c3507d)));
        return hashSet;
    }

    private static C3507d m18386e(C3507d c3507d) {
        Class i = c3507d.m19101i();
        return i == null ? null : C3507d.m19084a(i);
    }

    private static Class<?>[] m18387f(C3507d c3507d) {
        if (c3507d == null) {
            return new Class[0];
        }
        C3327e c3327e = (C3327e) c3507d.m19093b(C3327e.class);
        return c3327e == null ? new Class[0] : c3327e.m18394a();
    }

    public String m18388a() {
        return toString();
    }

    public boolean m18389a(C3507d c3507d) {
        if (m18383c(c3507d)) {
            return true;
        }
        Iterator it = c3507d.m19094b().iterator();
        while (it.hasNext()) {
            if (m18389a((C3507d) it.next())) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder append = new StringBuilder("categories ").append(this.f15833b.isEmpty() ? "[all]" : this.f15833b);
        if (!this.f15834c.isEmpty()) {
            append.append(" - ").append(this.f15834c);
        }
        return append.toString();
    }
}
