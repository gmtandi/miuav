package com.amap.api.services.core;

import java.util.concurrent.Callable;

class bl implements Callable<Void> {
    final /* synthetic */ bk f3113a;

    bl(bk bkVar) {
        this.f3113a = bkVar;
    }

    public Void m4722a() {
        synchronized (this.f3113a) {
            if (this.f3113a.f3107k == null) {
            } else {
                this.f3113a.m4714j();
                if (this.f3113a.m4712h()) {
                    this.f3113a.m4711g();
                    this.f3113a.f3109m = 0;
                }
            }
        }
        return null;
    }

    public /* synthetic */ Object call() {
        return m4722a();
    }
}
