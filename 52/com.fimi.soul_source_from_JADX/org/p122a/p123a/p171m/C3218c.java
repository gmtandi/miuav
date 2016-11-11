package org.p122a.p123a.p171m;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import org.p122a.p123a.p157d.C2993c;

/* renamed from: org.a.a.m.c */
class C3218c extends C3217k<E> {
    final /* synthetic */ Object f15680a;
    final /* synthetic */ Object f15681b;
    final /* synthetic */ C3106a f15682c;

    C3218c(C3106a c3106a, Lock lock, C2993c c2993c, Object obj, Object obj2) {
        this.f15682c = c3106a;
        this.f15680a = obj;
        this.f15681b = obj2;
        super(lock, c2993c);
    }

    public E m17848a(long j, TimeUnit timeUnit) {
        C3108i a = this.f15682c.m17505a(this.f15680a, this.f15681b, j, timeUnit, this);
        this.f15682c.m17517a(a);
        return a;
    }

    public /* synthetic */ Object m17849b(long j, TimeUnit timeUnit) {
        return m17848a(j, timeUnit);
    }
}
