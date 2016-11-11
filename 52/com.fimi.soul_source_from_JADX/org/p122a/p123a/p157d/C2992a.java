package org.p122a.p123a.p157d;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.p122a.p123a.p180o.C3234a;

/* renamed from: org.a.a.d.a */
public class C2992a<T> implements Future<T>, C2943b {
    private final C2993c<T> f14953a;
    private volatile boolean f14954b;
    private volatile boolean f14955c;
    private volatile T f14956d;
    private volatile Exception f14957e;

    public C2992a(C2993c<T> c2993c) {
        this.f14953a = c2993c;
    }

    private T m16999b() {
        if (this.f14957e == null) {
            return this.f14956d;
        }
        throw new ExecutionException(this.f14957e);
    }

    public boolean m17000a() {
        return cancel(true);
    }

    public boolean m17001a(Exception exception) {
        boolean z = true;
        synchronized (this) {
            if (this.f14954b) {
                z = false;
            } else {
                this.f14954b = true;
                this.f14957e = exception;
                notifyAll();
                if (this.f14953a != null) {
                    this.f14953a.m17004a(exception);
                }
            }
        }
        return z;
    }

    public boolean m17002a(T t) {
        boolean z = true;
        synchronized (this) {
            if (this.f14954b) {
                z = false;
            } else {
                this.f14954b = true;
                this.f14956d = t;
                notifyAll();
                if (this.f14953a != null) {
                    this.f14953a.m17005a((Object) t);
                }
            }
        }
        return z;
    }

    public boolean cancel(boolean z) {
        boolean z2 = true;
        synchronized (this) {
            if (this.f14954b) {
                z2 = false;
            } else {
                this.f14954b = true;
                this.f14955c = true;
                notifyAll();
                if (this.f14953a != null) {
                    this.f14953a.m17003a();
                }
            }
        }
        return z2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized T get() {
        /*
        r1 = this;
        monitor-enter(r1);
    L_0x0001:
        r0 = r1.f14954b;	 Catch:{ all -> 0x0009 }
        if (r0 != 0) goto L_0x000c;
    L_0x0005:
        r1.wait();	 Catch:{ all -> 0x0009 }
        goto L_0x0001;
    L_0x0009:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x000c:
        r0 = r1.m16999b();	 Catch:{ all -> 0x0009 }
        monitor-exit(r1);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.a.d.a.get():T");
    }

    public synchronized T get(long j, TimeUnit timeUnit) {
        T b;
        C3234a.m17886a((Object) timeUnit, "Time unit");
        long toMillis = timeUnit.toMillis(j);
        long currentTimeMillis = toMillis <= 0 ? 0 : System.currentTimeMillis();
        if (this.f14954b) {
            b = m16999b();
        } else if (toMillis <= 0) {
            throw new TimeoutException();
        } else {
            long j2 = toMillis;
            do {
                wait(j2);
                if (this.f14954b) {
                    b = m16999b();
                } else {
                    j2 = toMillis - (System.currentTimeMillis() - currentTimeMillis);
                }
            } while (j2 > 0);
            throw new TimeoutException();
        }
        return b;
    }

    public boolean isCancelled() {
        return this.f14955c;
    }

    public boolean isDone() {
        return this.f14954b;
    }
}
