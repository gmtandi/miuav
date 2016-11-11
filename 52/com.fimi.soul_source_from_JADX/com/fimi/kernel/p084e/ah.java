package com.fimi.kernel.p084e;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.fimi.kernel.e.ah */
public class ah {
    private static ExecutorService f5299a;

    public static synchronized ExecutorService m8074a() {
        ExecutorService executorService;
        synchronized (ah.class) {
            if (f5299a == null) {
                f5299a = Executors.newCachedThreadPool();
            }
            executorService = f5299a;
        }
        return executorService;
    }

    public static void m8075a(Runnable runnable) {
        ah.m8074a().execute(runnable);
    }

    public static void m8076b() {
        if (f5299a != null) {
            f5299a.shutdownNow();
            f5299a = null;
        }
    }

    public static void m8077b(Runnable runnable) {
        ah.m8074a().execute(runnable);
    }
}
