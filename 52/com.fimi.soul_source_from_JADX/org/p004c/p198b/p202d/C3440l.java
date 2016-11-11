package org.p004c.p198b.p202d;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.p004c.p187f.p192a.C3532p;

/* renamed from: org.c.b.d.l */
class C3440l implements Runnable {
    final /* synthetic */ long f15959a;
    final /* synthetic */ C3439k f15960b;

    C3440l(C3439k c3439k, long j) {
        this.f15960b = c3439k;
        this.f15959a = j;
    }

    public void run() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new C3441m(this));
        newSingleThreadExecutor.shutdown();
        try {
            if (!newSingleThreadExecutor.awaitTermination(this.f15959a, TimeUnit.MILLISECONDS)) {
                newSingleThreadExecutor.shutdownNow();
            }
            submit.get(0, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            this.f15960b.m18774a(new C3532p(this.f15959a, TimeUnit.MILLISECONDS));
        } catch (Throwable e2) {
            this.f15960b.m18774a(e2);
        }
    }
}
