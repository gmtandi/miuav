package org.p004c.p198b.p202d.p204b;

import java.lang.annotation.Annotation;
import java.util.List;
import org.p004c.p187f.p192a.C3522c;

/* renamed from: org.c.b.d.b.h */
final class C3416h implements C3411l {
    private C3416h() {
    }

    public void m18701a(C3522c<?> c3522c, Class<? extends Annotation> cls, List<Throwable> list) {
        if (!c3522c.m19170h()) {
            list.add(new C3420m(c3522c, cls, "must be public."));
        }
    }
}
