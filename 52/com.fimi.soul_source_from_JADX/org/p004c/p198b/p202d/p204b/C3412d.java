package org.p004c.p198b.p202d.p204b;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.List;
import org.p004c.p187f.p192a.C3522c;

/* renamed from: org.c.b.d.b.d */
final class C3412d implements C3411l {
    private C3412d() {
    }

    private boolean m18696a(C3522c<?> c3522c) {
        return Modifier.isPublic(c3522c.m19168f().getModifiers());
    }

    public void m18697a(C3522c<?> c3522c, Class<? extends Annotation> cls, List<Throwable> list) {
        if (!m18696a(c3522c)) {
            list.add(new C3420m(c3522c, cls, "must be declared in a public class."));
        }
    }
}
