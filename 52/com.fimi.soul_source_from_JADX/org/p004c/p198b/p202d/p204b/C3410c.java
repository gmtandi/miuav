package org.p004c.p198b.p202d.p204b;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/* renamed from: org.c.b.d.b.c */
class C3410c {
    private final Class<? extends Annotation> f15920a;
    private boolean f15921b;
    private final List<C3411l> f15922c;

    private C3410c(Class<? extends Annotation> cls) {
        this.f15920a = cls;
        this.f15921b = false;
        this.f15922c = new ArrayList();
    }

    C3410c m18692a() {
        this.f15921b = true;
        return this;
    }

    C3410c m18693a(C3411l c3411l) {
        this.f15922c.add(c3411l);
        return this;
    }

    C3408a m18694b() {
        return new C3408a(this);
    }
}
