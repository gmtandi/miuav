package p000a;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: a.b */
final class C0003b {
    static final int f40a;
    static final int f41b;
    static final long f42c = 1;
    private static final C0003b f43d;
    private static final int f44f;
    private final Executor f45e;

    static {
        f43d = new C0003b();
        f44f = Runtime.getRuntime().availableProcessors();
        f40a = f44f + 1;
        f41b = (f44f * 2) + 1;
    }

    private C0003b() {
        this.f45e = new C0005d();
    }

    public static ExecutorService m28a() {
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(f40a, f41b, f42c, TimeUnit.SECONDS, new LinkedBlockingQueue());
        C0003b.m30a(threadPoolExecutor, true);
        return threadPoolExecutor;
    }

    public static ExecutorService m29a(ThreadFactory threadFactory) {
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(f40a, f41b, f42c, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory);
        C0003b.m30a(threadPoolExecutor, true);
        return threadPoolExecutor;
    }

    @SuppressLint({"NewApi"})
    public static void m30a(ThreadPoolExecutor threadPoolExecutor, boolean z) {
        if (VERSION.SDK_INT >= 9) {
            threadPoolExecutor.allowCoreThreadTimeOut(z);
        }
    }

    public static Executor m31b() {
        return f43d.f45e;
    }
}
