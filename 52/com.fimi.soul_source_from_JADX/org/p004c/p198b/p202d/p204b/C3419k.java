package org.p004c.p198b.p202d.p204b;

import java.lang.annotation.Annotation;
import java.util.List;
import org.p004c.p187f.p192a.C3522c;

/* renamed from: org.c.b.d.b.k */
final class C3419k implements C3411l {
    private C3419k() {
    }

    public void m18704a(C3522c<?> c3522c, Class<? extends Annotation> cls, List<Throwable> list) {
        if (!C3408a.m18686e(c3522c)) {
            list.add(new C3420m(c3522c, cls, "must return an implementation of TestRule."));
        }
    }
}
