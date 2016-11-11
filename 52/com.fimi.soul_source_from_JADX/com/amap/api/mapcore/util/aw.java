package com.amap.api.mapcore.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class aw implements ThreadFactory {
    private final AtomicInteger f2140a;

    aw() {
        this.f2140a = new AtomicInteger(1);
    }

    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, "AsyncTask #" + this.f2140a.getAndIncrement());
    }
}
