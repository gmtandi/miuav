package org.p004c.p187f;

import java.lang.reflect.Method;
import java.util.Comparator;
import org.p004c.p198b.C3451h;

/* renamed from: org.c.f.e */
public enum C3540e {
    NAME_ASCENDING(C3451h.f15970b),
    JVM(null),
    DEFAULT(C3451h.f15969a);
    
    private final Comparator<Method> f16093d;

    private C3540e(Comparator<Method> comparator) {
        this.f16093d = comparator;
    }

    public Comparator<Method> m19248a() {
        return this.f16093d;
    }
}
