package org.p004c.p198b.p202d.p204b;

import java.lang.annotation.Annotation;
import java.util.List;
import org.p004c.p187f.p192a.C3522c;

/* renamed from: org.c.b.d.b.j */
final class C3418j implements C3411l {
    private C3418j() {
    }

    public void m18703a(C3522c<?> c3522c, Class<? extends Annotation> cls, List<Throwable> list) {
        if (!C3408a.m18685d(c3522c)) {
            list.add(new C3420m(c3522c, cls, "must return an implementation of MethodRule or TestRule."));
        }
    }
}
