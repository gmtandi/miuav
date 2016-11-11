package com.p016a;

import java.util.concurrent.Callable;

/* renamed from: com.a.be */
class be implements Callable<Void> {
    final /* synthetic */ az f622a;

    be(az azVar) {
        this.f622a = azVar;
    }

    public Void m1162a() {
        synchronized (this.f622a) {
            if (this.f622a.f598k == null) {
            } else {
                this.f622a.m1133j();
                if (this.f622a.m1131h()) {
                    this.f622a.m1130g();
                    this.f622a.f600m = 0;
                }
            }
        }
        return null;
    }

    public /* synthetic */ Object call() {
        return m1162a();
    }
}
