package com.android.volley.toolbox;

import com.android.volley.C0570r;
import com.android.volley.aa;
import com.android.volley.ab;
import com.android.volley.ag;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ah<T> implements aa, ab<T>, Future<T> {
    private C0570r<?> f3594a;
    private boolean f3595b;
    private T f3596c;
    private ag f3597d;

    private ah() {
        this.f3595b = false;
    }

    public static <E> ah<E> m5165a() {
        return new ah();
    }

    private synchronized T m5166a(Long l) {
        T t;
        if (this.f3597d != null) {
            throw new ExecutionException(this.f3597d);
        } else if (this.f3595b) {
            t = this.f3596c;
        } else {
            if (l == null) {
                wait(0);
            } else if (l.longValue() > 0) {
                wait(l.longValue());
            }
            if (this.f3597d != null) {
                throw new ExecutionException(this.f3597d);
            } else if (this.f3595b) {
                t = this.f3596c;
            } else {
                throw new TimeoutException();
            }
        }
        return t;
    }

    public synchronized void m5167a(ag agVar) {
        this.f3597d = agVar;
        notifyAll();
    }

    public void m5168a(C0570r<?> c0570r) {
        this.f3594a = c0570r;
    }

    public synchronized void m5169a(T t) {
        this.f3595b = true;
        this.f3596c = t;
        notifyAll();
    }

    public synchronized boolean cancel(boolean z) {
        boolean z2 = false;
        synchronized (this) {
            if (this.f3594a != null) {
                if (!isDone()) {
                    this.f3594a.m5121l();
                    z2 = true;
                }
            }
        }
        return z2;
    }

    public T get() {
        try {
            return m5166a(null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    public T get(long j, TimeUnit timeUnit) {
        return m5166a(Long.valueOf(TimeUnit.MILLISECONDS.convert(j, timeUnit)));
    }

    public boolean isCancelled() {
        return this.f3594a == null ? false : this.f3594a.m5122m();
    }

    public synchronized boolean isDone() {
        boolean z;
        z = this.f3595b || this.f3597d != null || isCancelled();
        return z;
    }
}
