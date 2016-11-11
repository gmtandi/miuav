package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.codehaus.jackson.smile.SmileConstants;

public abstract class av<Params, Progress, Result> {
    private static final ThreadFactory f1843a;
    public static final Executor f1844b;
    public static final Executor f1845c;
    public static final Executor f1846d;
    private static final BlockingQueue<Runnable> f1847e;
    private static final C0346b f1848f;
    private static volatile Executor f1849g;
    private final C0342e<Params, Result> f1850h;
    private final FutureTask<Result> f1851i;
    private volatile C0349d f1852j;
    private final AtomicBoolean f1853k;
    private final AtomicBoolean f1854l;

    /* renamed from: com.amap.api.mapcore.util.av.e */
    abstract class C0342e<Params, Result> implements Callable<Result> {
        Params[] f2127b;

        private C0342e() {
        }
    }

    /* renamed from: com.amap.api.mapcore.util.av.1 */
    class C03431 extends C0342e<Params, Result> {
        final /* synthetic */ av f2128a;

        C03431(av avVar) {
            this.f2128a = avVar;
            super();
        }

        public Result call() {
            this.f2128a.f1854l.set(true);
            Process.setThreadPriority(10);
            return this.f2128a.m3131d(this.f2128a.m3135a(this.b));
        }
    }

    /* renamed from: com.amap.api.mapcore.util.av.2 */
    class C03442 extends FutureTask<Result> {
        final /* synthetic */ av f2129a;

        C03442(av avVar, Callable callable) {
            this.f2129a = avVar;
            super(callable);
        }

        protected void done() {
            try {
                this.f2129a.m3130c(this.f2129a.f1851i.get());
            } catch (Throwable e) {
                Log.w("AsyncTask", e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException e3) {
                this.f2129a.m3130c(null);
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.util.av.a */
    class C0345a<Data> {
        final av f2130a;
        final Data[] f2131b;

        C0345a(av avVar, Data... dataArr) {
            this.f2130a = avVar;
            this.f2131b = dataArr;
        }
    }

    /* renamed from: com.amap.api.mapcore.util.av.b */
    class C0346b extends Handler {
        private C0346b() {
        }

        public void handleMessage(Message message) {
            C0345a c0345a = (C0345a) message.obj;
            switch (message.what) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    c0345a.f2130a.m3132e(c0345a.f2131b[0]);
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    c0345a.f2130a.m3140b(c0345a.f2131b);
                default:
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.util.av.c */
    class C0348c implements Executor {
        final ArrayDeque<Runnable> f2134a;
        Runnable f2135b;

        /* renamed from: com.amap.api.mapcore.util.av.c.1 */
        class C03471 implements Runnable {
            final /* synthetic */ Runnable f2132a;
            final /* synthetic */ C0348c f2133b;

            C03471(C0348c c0348c, Runnable runnable) {
                this.f2133b = c0348c;
                this.f2132a = runnable;
            }

            public void run() {
                try {
                    this.f2132a.run();
                } finally {
                    this.f2133b.m3488a();
                }
            }
        }

        private C0348c() {
            this.f2134a = new ArrayDeque();
        }

        protected synchronized void m3488a() {
            Runnable runnable = (Runnable) this.f2134a.poll();
            this.f2135b = runnable;
            if (runnable != null) {
                av.f1844b.execute(this.f2135b);
            }
        }

        public synchronized void execute(Runnable runnable) {
            this.f2134a.offer(new C03471(this, runnable));
            if (this.f2135b == null) {
                m3488a();
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.util.av.d */
    public enum C0349d {
        PENDING,
        RUNNING,
        FINISHED
    }

    static {
        f1843a = new aw();
        f1847e = new LinkedBlockingQueue(10);
        f1844b = new ThreadPoolExecutor(5, SmileConstants.TOKEN_PREFIX_TINY_UNICODE, 1, TimeUnit.SECONDS, f1847e, f1843a, new DiscardOldestPolicy());
        f1845c = bj.m3639c() ? new C0348c() : Executors.newSingleThreadExecutor(f1843a);
        f1846d = Executors.newFixedThreadPool(2, f1843a);
        f1848f = new C0346b();
        f1849g = f1845c;
    }

    public av() {
        this.f1852j = C0349d.PENDING;
        this.f1853k = new AtomicBoolean();
        this.f1854l = new AtomicBoolean();
        this.f1850h = new C03431(this);
        this.f1851i = new C03442(this, this.f1850h);
    }

    private void m3130c(Result result) {
        if (!this.f1854l.get()) {
            m3131d(result);
        }
    }

    private Result m3131d(Result result) {
        f1848f.obtainMessage(1, new C0345a(this, result)).sendToTarget();
        return result;
    }

    private void m3132e(Result result) {
        if (m3143d()) {
            m3139b((Object) result);
        } else {
            m3136a((Object) result);
        }
        this.f1852j = C0349d.FINISHED;
    }

    public final C0349d m3133a() {
        return this.f1852j;
    }

    public final av<Params, Progress, Result> m3134a(Executor executor, Params... paramsArr) {
        if (this.f1852j != C0349d.PENDING) {
            switch (ax.f2141a[this.f1852j.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.f1852j = C0349d.RUNNING;
        m3138b();
        this.f1850h.f2127b = paramsArr;
        executor.execute(this.f1851i);
        return this;
    }

    protected abstract Result m3135a(Params... paramsArr);

    protected void m3136a(Result result) {
    }

    public final boolean m3137a(boolean z) {
        this.f1853k.set(true);
        return this.f1851i.cancel(z);
    }

    protected void m3138b() {
    }

    protected void m3139b(Result result) {
        m3142c();
    }

    protected void m3140b(Progress... progressArr) {
    }

    public final av<Params, Progress, Result> m3141c(Params... paramsArr) {
        return m3134a(f1849g, (Object[]) paramsArr);
    }

    protected void m3142c() {
    }

    public final boolean m3143d() {
        return this.f1853k.get();
    }
}
