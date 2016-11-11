package p000a;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: a.m */
final class C0013m {
    private static final C0013m f74a;
    private final ExecutorService f75b;
    private final Executor f76c;

    static {
        f74a = new C0013m();
    }

    private C0013m() {
        this.f75b = !C0013m.m66c() ? Executors.newCachedThreadPool() : C0003b.m28a();
        this.f76c = new C0015o();
    }

    public static ExecutorService m64a() {
        return f74a.f75b;
    }

    static Executor m65b() {
        return f74a.f76c;
    }

    private static boolean m66c() {
        String property = System.getProperty("java.runtime.name");
        return property == null ? false : property.toLowerCase(Locale.US).contains("android");
    }
}
