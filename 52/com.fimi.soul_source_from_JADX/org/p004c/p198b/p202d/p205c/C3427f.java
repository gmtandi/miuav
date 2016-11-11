package org.p004c.p198b.p202d.p205c;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/* renamed from: org.c.b.d.c.f */
class C3427f implements Callable<Throwable> {
    final /* synthetic */ C3424c f15936a;
    private final CountDownLatch f15937b;

    private C3427f(C3424c c3424c) {
        this.f15936a = c3424c;
        this.f15937b = new CountDownLatch(1);
    }

    public Throwable m18728a() {
        try {
            this.f15937b.countDown();
            this.f15936a.f15928a.m18589a();
            return null;
        } catch (Exception e) {
            throw e;
        } catch (Throwable th) {
            return th;
        }
    }

    public void m18729b() {
        this.f15937b.await();
    }

    public /* synthetic */ Object call() {
        return m18728a();
    }
}
