package com.mining.app.zxing.p127b;

import android.app.Activity;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: com.mining.app.zxing.b.g */
public final class C2136g {
    private static final int f11224a = 300;
    private final ScheduledExecutorService f11225b;
    private final Activity f11226c;
    private ScheduledFuture<?> f11227d;

    public C2136g(Activity activity) {
        this.f11225b = Executors.newSingleThreadScheduledExecutor(new C2138i());
        this.f11227d = null;
        this.f11226c = activity;
        m13110a();
    }

    private void m13109c() {
        if (this.f11227d != null) {
            this.f11227d.cancel(true);
            this.f11227d = null;
        }
    }

    public void m13110a() {
        m13109c();
        this.f11227d = this.f11225b.schedule(new C2135f(this.f11226c), 300, TimeUnit.SECONDS);
    }

    public void m13111b() {
        m13109c();
        this.f11225b.shutdown();
    }
}
