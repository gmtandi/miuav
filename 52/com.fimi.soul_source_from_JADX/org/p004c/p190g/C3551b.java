package org.p004c.p190g;

import java.util.concurrent.ConcurrentHashMap;

/* renamed from: org.c.g.b */
public class C3551b {
    private static final ConcurrentHashMap<C3560k, C3330a> f16105a;

    static {
        f16105a = new ConcurrentHashMap();
    }

    public C3330a m19265a(C3560k c3560k) {
        C3330a c3330a = (C3330a) f16105a.get(c3560k);
        if (c3330a == null) {
            Class a = c3560k.m19282a();
            if (a == null) {
                throw new IllegalArgumentException("Can't create validator, value is null in annotation " + c3560k.getClass().getName());
            }
            try {
                f16105a.putIfAbsent(c3560k, (C3330a) a.newInstance());
                c3330a = (C3330a) f16105a.get(c3560k);
            } catch (Throwable e) {
                throw new RuntimeException("Exception received when creating AnnotationValidator class " + a.getName(), e);
            }
        }
        return c3330a;
    }
}
