package org.p004c.p188a;

import com.tencent.mm.sdk.platformtools.MAlarmHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.p004c.p187f.p192a.C3347j;

/* renamed from: org.c.a.b */
class C3348b implements C3347j {
    private final ExecutorService f15853a;

    C3348b() {
        this.f15853a = Executors.newCachedThreadPool();
    }

    public void m18459a() {
        try {
            this.f15853a.shutdown();
            this.f15853a.awaitTermination(MAlarmHandler.NEXT_FIRE_INTERVAL, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
    }

    public void m18460a(Runnable runnable) {
        this.f15853a.submit(runnable);
    }
}
