package org.p004c.p190g;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import org.p004c.p187f.p192a.C3521a;
import org.p004c.p187f.p192a.C3528l;

/* renamed from: org.c.g.e */
abstract class C3555e<T extends C3521a> {
    private static final C3551b f16107a;

    static {
        f16107a = new C3551b();
    }

    private C3555e() {
    }

    private List<Exception> m19268a(T t) {
        List<Exception> arrayList = new ArrayList();
        for (Annotation annotationType : t.m19162a()) {
            C3560k c3560k = (C3560k) annotationType.annotationType().getAnnotation(C3560k.class);
            if (c3560k != null) {
                arrayList.addAll(m19270a(f16107a.m19265a(c3560k), t));
            }
        }
        return arrayList;
    }

    abstract Iterable<T> m19269a(C3528l c3528l);

    abstract List<Exception> m19270a(C3330a c3330a, T t);

    public List<Exception> m19271b(C3528l c3528l) {
        List<Exception> arrayList = new ArrayList();
        for (C3521a a : m19269a(c3528l)) {
            arrayList.addAll(m19268a(a));
        }
        return arrayList;
    }
}
