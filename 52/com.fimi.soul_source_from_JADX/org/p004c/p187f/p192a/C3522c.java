package org.p004c.p187f.p192a;

import java.lang.reflect.Modifier;
import java.util.List;

/* renamed from: org.c.f.a.c */
public abstract class C3522c<T extends C3522c<T>> implements C3521a {
    boolean m19163a(List<T> list) {
        for (T a : list) {
            if (m19164a((C3522c) a)) {
                return true;
            }
        }
        return false;
    }

    abstract boolean m19164a(T t);

    public abstract String m19165b();

    protected abstract int m19166c();

    public abstract Class<?> m19167e();

    public abstract Class<?> m19168f();

    public boolean m19169g() {
        return Modifier.isStatic(m19166c());
    }

    public boolean m19170h() {
        return Modifier.isPublic(m19166c());
    }
}
