package org.p004c.p207d;

import java.util.concurrent.Callable;
import org.p004c.C3459c;
import org.p184b.C3275p;

/* renamed from: org.c.d.c */
class C3464c implements Callable<Object> {
    final /* synthetic */ String f15977a;
    final /* synthetic */ Object f15978b;
    final /* synthetic */ C3275p f15979c;
    final /* synthetic */ C3463b f15980d;

    C3464c(C3463b c3463b, String str, Object obj, C3275p c3275p) {
        this.f15980d = c3463b;
        this.f15977a = str;
        this.f15978b = obj;
        this.f15979c = c3275p;
    }

    public Object call() {
        C3459c.m18855a(this.f15977a, this.f15978b, this.f15979c);
        return this.f15978b;
    }
}
