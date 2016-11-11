package com.amap.api.mapcore.util;

import java.util.concurrent.Callable;

class cy implements Callable<Void> {
    final /* synthetic */ cx f2393a;

    cy(cx cxVar) {
        this.f2393a = cxVar;
    }

    public Void m3988a() {
        synchronized (this.f2393a) {
            if (this.f2393a.f2387k == null) {
            } else {
                this.f2393a.m3980j();
                if (this.f2393a.m3978h()) {
                    this.f2393a.m3977g();
                    this.f2393a.f2389m = 0;
                }
            }
        }
        return null;
    }

    public /* synthetic */ Object call() {
        return m3988a();
    }
}
