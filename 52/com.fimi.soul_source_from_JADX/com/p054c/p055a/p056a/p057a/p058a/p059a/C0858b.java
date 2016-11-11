package com.p054c.p055a.p056a.p057a.p058a.p059a;

import java.util.concurrent.Callable;

/* renamed from: com.c.a.a.a.a.a.b */
class C0858b implements Callable<Void> {
    final /* synthetic */ C0857a f4660a;

    C0858b(C0857a c0857a) {
        this.f4660a = c0857a;
    }

    public Void m6997a() {
        synchronized (this.f4660a) {
            if (this.f4660a.f4656w == null) {
            } else {
                this.f4660a.m6983o();
                this.f4660a.m6984p();
                if (this.f4660a.m6981m()) {
                    this.f4660a.m6980l();
                    this.f4660a.f4658y = 0;
                }
            }
        }
        return null;
    }

    public /* synthetic */ Object call() {
        return m6997a();
    }
}
