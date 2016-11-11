package org.p004c.p198b.p202d.p204b;

import java.lang.annotation.Annotation;
import org.p004c.p187f.p192a.C3522c;

/* renamed from: org.c.b.d.b.m */
class C3420m extends Exception {
    public C3420m(C3522c<?> c3522c, Class<? extends Annotation> cls, String str) {
        super(String.format("The @%s '%s' %s", new Object[]{cls.getSimpleName(), c3522c.m19165b(), str}));
    }
}
