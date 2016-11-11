package com.amap.api.services.core;

public abstract class ax implements Runnable {
    C0453a f3036a;

    /* renamed from: com.amap.api.services.core.ax.a */
    interface C0453a {
        void m4578a(ax axVar);

        void m4579b(ax axVar);
    }

    public abstract void m4582a();

    public final void run() {
        try {
            if (this.f3036a != null) {
                this.f3036a.m4578a(this);
            }
            if (!Thread.interrupted()) {
                m4582a();
                if (!Thread.interrupted() && this.f3036a != null) {
                    this.f3036a.m4579b(this);
                }
            }
        } catch (Throwable th) {
            ay.m4590a(th, "ThreadTask", "run");
            th.printStackTrace();
        }
    }
}
