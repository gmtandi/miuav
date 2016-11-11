package p000a;

import a.s$a.ae;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: a.s */
public class C0018s<TResult> {
    public static final ExecutorService f88a;
    public static final Executor f89b;
    private static final Executor f90c;
    private final Object f91d;
    private boolean f92e;
    private boolean f93f;
    private TResult f94g;
    private Exception f95h;
    private List<C0001q<TResult, Void>> f96i;

    static {
        f88a = C0013m.m64a();
        f90c = C0013m.m65b();
        f89b = C0003b.m31b();
    }

    private C0018s() {
        this.f91d = new Object();
        this.f96i = new ArrayList();
    }

    public static <TResult> ae m75a() {
        C0018s c0018s = new C0018s();
        c0018s.getClass();
        return new ae(null);
    }

    public static <TResult> C0018s<TResult> m76a(Exception exception) {
        ae a = C0018s.m75a();
        a.m8b(exception);
        return a.m5a();
    }

    public static <TResult> C0018s<TResult> m77a(TResult tResult) {
        ae a = C0018s.m75a();
        a.m9b((Object) tResult);
        return a.m5a();
    }

    public static C0018s<Void> m78a(Collection<? extends C0018s<?>> collection) {
        if (collection.size() == 0) {
            return C0018s.m77a(null);
        }
        ae a = C0018s.m75a();
        ArrayList arrayList = new ArrayList();
        Object obj = new Object();
        AtomicInteger atomicInteger = new AtomicInteger(collection.size());
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        for (C0018s a2 : collection) {
            a2.m95a(new C0023x(obj, arrayList, atomicBoolean, atomicInteger, a));
        }
        return a.m5a();
    }

    public static <TResult> C0018s<TResult> m79a(Callable<TResult> callable) {
        return C0018s.m80a((Callable) callable, f88a);
    }

    public static <TResult> C0018s<TResult> m80a(Callable<TResult> callable, Executor executor) {
        ae a = C0018s.m75a();
        executor.execute(new C0022w(a, callable));
        return a.m5a();
    }

    public static <TResult> C0018s<TResult> m86b(Callable<TResult> callable) {
        return C0018s.m80a((Callable) callable, f90c);
    }

    private static <TContinuationResult, TResult> void m90c(ae aeVar, C0001q<TResult, TContinuationResult> c0001q, C0018s<TResult> c0018s, Executor executor) {
        executor.execute(new ad(c0001q, c0018s, aeVar));
    }

    private static <TContinuationResult, TResult> void m92d(ae aeVar, C0001q<TResult, C0018s<TContinuationResult>> c0001q, C0018s<TResult> c0018s, Executor executor) {
        executor.execute(new C0020u(c0001q, c0018s, aeVar));
    }

    public static <TResult> C0018s<TResult> m93h() {
        ae a = C0018s.m75a();
        a.m11c();
        return a.m5a();
    }

    private void m94k() {
        synchronized (this.f91d) {
            for (C0001q then : this.f96i) {
                try {
                    then.then(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Throwable e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.f96i = null;
        }
    }

    public <TContinuationResult> C0018s<TContinuationResult> m95a(C0001q<TResult, TContinuationResult> c0001q) {
        return m96a((C0001q) c0001q, f90c);
    }

    public <TContinuationResult> C0018s<TContinuationResult> m96a(C0001q<TResult, TContinuationResult> c0001q, Executor executor) {
        ae a = C0018s.m75a();
        synchronized (this.f91d) {
            boolean b = m101b();
            if (!b) {
                this.f96i.add(new C0025z(this, a, c0001q, executor));
            }
        }
        if (b) {
            C0018s.m90c(a, c0001q, this, executor);
        }
        return a.m5a();
    }

    public C0018s<Void> m97a(Callable<Boolean> callable, C0001q<Void, C0018s<Void>> c0001q) {
        return m98a(callable, c0001q, f90c);
    }

    public C0018s<Void> m98a(Callable<Boolean> callable, C0001q<Void, C0018s<Void>> c0001q, Executor executor) {
        C0016p c0016p = new C0016p();
        c0016p.m70a(new C0024y(this, callable, c0001q, executor, c0016p));
        return m112j().m100b((C0001q) c0016p.m69a(), executor);
    }

    public <TContinuationResult> C0018s<TContinuationResult> m99b(C0001q<TResult, C0018s<TContinuationResult>> c0001q) {
        return m100b((C0001q) c0001q, f90c);
    }

    public <TContinuationResult> C0018s<TContinuationResult> m100b(C0001q<TResult, C0018s<TContinuationResult>> c0001q, Executor executor) {
        ae a = C0018s.m75a();
        synchronized (this.f91d) {
            boolean b = m101b();
            if (!b) {
                this.f96i.add(new aa(this, a, c0001q, executor));
            }
        }
        if (b) {
            C0018s.m92d(a, c0001q, this, executor);
        }
        return a.m5a();
    }

    public boolean m101b() {
        boolean z;
        synchronized (this.f91d) {
            z = this.f92e;
        }
        return z;
    }

    public <TContinuationResult> C0018s<TContinuationResult> m102c(C0001q<TResult, TContinuationResult> c0001q) {
        return m103c(c0001q, f90c);
    }

    public <TContinuationResult> C0018s<TContinuationResult> m103c(C0001q<TResult, TContinuationResult> c0001q, Executor executor) {
        return m100b(new ab(this, c0001q), executor);
    }

    public boolean m104c() {
        boolean z;
        synchronized (this.f91d) {
            z = this.f93f;
        }
        return z;
    }

    public <TContinuationResult> C0018s<TContinuationResult> m105d(C0001q<TResult, C0018s<TContinuationResult>> c0001q) {
        return m106d(c0001q, f90c);
    }

    public <TContinuationResult> C0018s<TContinuationResult> m106d(C0001q<TResult, C0018s<TContinuationResult>> c0001q, Executor executor) {
        return m100b(new ac(this, c0001q), executor);
    }

    public boolean m107d() {
        boolean z;
        synchronized (this.f91d) {
            z = this.f95h != null;
        }
        return z;
    }

    public TResult m108e() {
        TResult tResult;
        synchronized (this.f91d) {
            tResult = this.f94g;
        }
        return tResult;
    }

    public Exception m109f() {
        Exception exception;
        synchronized (this.f91d) {
            exception = this.f95h;
        }
        return exception;
    }

    public void m110g() {
        synchronized (this.f91d) {
            if (!m101b()) {
                this.f91d.wait();
            }
        }
    }

    public <TOut> C0018s<TOut> m111i() {
        return this;
    }

    public C0018s<Void> m112j() {
        return m99b(new C0019t(this));
    }
}
