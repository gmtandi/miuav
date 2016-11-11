package org.p004c.p198b.p202d.p204b;

import java.lang.annotation.Annotation;
import java.util.List;
import org.p004c.C3562h;
import org.p004c.p187f.p192a.C3522c;

/* renamed from: org.c.b.d.b.g */
final class C3415g implements C3411l {
    private C3415g() {
    }

    public void m18700a(C3522c<?> c3522c, Class<? extends Annotation> cls, List<Throwable> list) {
        boolean a = C3408a.m18687f(c3522c);
        Object obj = c3522c.m19161a(C3562h.class) != null ? 1 : null;
        if (!c3522c.m19169g()) {
            return;
        }
        if (a || obj == null) {
            list.add(new C3420m(c3522c, cls, C3408a.m18687f(c3522c) ? "must not be static." : "must not be static or it must be annotated with @ClassRule."));
        }
    }
}
