package com.mining.app.zxing.p127b;

import java.util.concurrent.ThreadFactory;

/* renamed from: com.mining.app.zxing.b.i */
final class C2138i implements ThreadFactory {
    private C2138i() {
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        return thread;
    }
}
