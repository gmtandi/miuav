package org.p122a.p123a.p171m;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p157d.C2993c;
import org.p122a.p123a.p180o.C3234a;

@C2914d
/* renamed from: org.a.a.m.k */
abstract class C3217k<T> implements Future<T> {
    private final Lock f15674a;
    private final C2993c<T> f15675b;
    private final Condition f15676c;
    private volatile boolean f15677d;
    private volatile boolean f15678e;
    private T f15679f;

    C3217k(Lock lock, C2993c<T> c2993c) {
        this.f15674a = lock;
        this.f15676c = lock.newCondition();
        this.f15675b = c2993c;
    }

    public void m17845a() {
        this.f15674a.lock();
        try {
            this.f15676c.signalAll();
        } finally {
            this.f15674a.unlock();
        }
    }

    public boolean m17846a(Date date) {
        this.f15674a.lock();
        try {
            if (this.f15677d) {
                throw new InterruptedException("Operation interrupted");
            }
            boolean awaitUntil;
            if (date != null) {
                awaitUntil = this.f15676c.awaitUntil(date);
            } else {
                this.f15676c.await();
                awaitUntil = true;
            }
            if (!this.f15677d) {
                return awaitUntil;
            }
            throw new InterruptedException("Operation interrupted");
        } finally {
            this.f15674a.unlock();
        }
    }

    protected abstract T m17847b(long j, TimeUnit timeUnit);

    public boolean cancel(boolean z) {
        this.f15674a.lock();
        try {
            if (this.f15678e) {
                return false;
            }
            this.f15678e = true;
            this.f15677d = true;
            if (this.f15675b != null) {
                this.f15675b.m17003a();
            }
            this.f15676c.signalAll();
            this.f15674a.unlock();
            return true;
        } finally {
            this.f15674a.unlock();
        }
    }

    public T get() {
        try {
            return get(0, TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            throw new ExecutionException(e);
        }
    }

    public T get(long j, TimeUnit timeUnit) {
        C3234a.m17886a((Object) timeUnit, "Time unit");
        this.f15674a.lock();
        try {
            T t;
            if (this.f15678e) {
                t = this.f15679f;
                this.f15674a.unlock();
            } else {
                this.f15679f = m17847b(j, timeUnit);
                this.f15678e = true;
                if (this.f15675b != null) {
                    this.f15675b.m17005a(this.f15679f);
                }
                t = this.f15679f;
                this.f15674a.unlock();
            }
            return t;
        } catch (Exception e) {
            this.f15678e = true;
            this.f15679f = null;
            if (this.f15675b != null) {
                this.f15675b.m17004a(e);
            }
            throw new ExecutionException(e);
        } catch (Throwable th) {
            this.f15674a.unlock();
        }
    }

    public boolean isCancelled() {
        return this.f15677d;
    }

    public boolean isDone() {
        return this.f15678e;
    }
}
