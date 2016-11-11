package com.p054c.p055a.p063b;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.c.a.b.b */
class C0911b implements ThreadFactory {
    private static final AtomicInteger f4804a;
    private final ThreadGroup f4805b;
    private final AtomicInteger f4806c;
    private final String f4807d;
    private final int f4808e;

    static {
        f4804a = new AtomicInteger(1);
    }

    C0911b(int i, String str) {
        this.f4806c = new AtomicInteger(1);
        this.f4808e = i;
        this.f4805b = Thread.currentThread().getThreadGroup();
        this.f4807d = str + f4804a.getAndIncrement() + "-thread-";
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.f4805b, runnable, this.f4807d + this.f4806c.getAndIncrement(), 0);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        thread.setPriority(this.f4808e);
        return thread;
    }
}
