package org.p004c.p198b.p202d.p204b;

import java.lang.annotation.Annotation;
import java.util.List;
import org.p004c.p187f.p192a.C3522c;

/* renamed from: org.c.b.d.b.i */
final class C3417i implements C3411l {
    private C3417i() {
    }

    public void m18702a(C3522c<?> c3522c, Class<? extends Annotation> cls, List<Throwable> list) {
        if (!c3522c.m19169g()) {
            list.add(new C3420m(c3522c, cls, "must be static."));
        }
    }
}
