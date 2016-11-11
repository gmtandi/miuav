package com.amap.api.mapcore.util;

public abstract class dp implements Runnable {
    C0379a f2427d;

    /* renamed from: com.amap.api.mapcore.util.dp.a */
    interface C0379a {
        void m4044a(dp dpVar);

        void m4045b(dp dpVar);

        void m4046c(dp dpVar);
    }

    public abstract void m4021a();

    public final void m4022e() {
        try {
            if (this.f2427d != null) {
                this.f2427d.m4046c(this);
            }
        } catch (Throwable th) {
            ce.m3829a(th, "ThreadTask", "cancelTask");
            th.printStackTrace();
        }
    }

    public final void run() {
        try {
            if (this.f2427d != null) {
                this.f2427d.m4044a(this);
            }
            if (!Thread.interrupted()) {
                m4021a();
                if (!Thread.interrupted() && this.f2427d != null) {
                    this.f2427d.m4045b(this);
                }
            }
        } catch (Throwable th) {
            ce.m3829a(th, "ThreadTask", "run");
            th.printStackTrace();
        }
    }
}
