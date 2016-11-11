package com.xiaomi.account.openauth;

import android.accounts.OperationCanceledException;
import android.os.Looper;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class XiaomiOAuthFutureImpl<V> extends FutureTask<V> implements XiaomiOAuthFuture<V> {
    private static final long DEFAULT_TIMEOUT_MINUTE = 10;

    /* renamed from: com.xiaomi.account.openauth.XiaomiOAuthFutureImpl.1 */
    class C24501 implements Callable<V> {
        C24501() {
        }

        public V call() {
            throw new IllegalStateException("this should never be called");
        }
    }

    XiaomiOAuthFutureImpl() {
        super(new C24501());
    }

    private void ensureNotOnMainThread() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null && myLooper == Looper.getMainLooper()) {
            throw new IllegalStateException("calling this from your main thread can lead to deadlock");
        }
    }

    private V internalGetResult(Long l, TimeUnit timeUnit) {
        V v;
        if (!isDone()) {
            ensureNotOnMainThread();
        }
        if (l == null) {
            try {
                v = get();
                cancel(true);
            } catch (CancellationException e) {
                throw new OperationCanceledException();
            } catch (TimeoutException e2) {
                cancel(true);
                throw new OperationCanceledException();
            } catch (InterruptedException e3) {
                cancel(true);
                throw new OperationCanceledException();
            } catch (ExecutionException e4) {
                Throwable cause = e4.getCause();
                if (cause instanceof IOException) {
                    throw ((IOException) cause);
                } else if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                } else if (cause instanceof Error) {
                    throw ((Error) cause);
                } else if (cause instanceof XMAuthericationException) {
                    throw ((XMAuthericationException) cause);
                } else if (cause instanceof OperationCanceledException) {
                    throw ((OperationCanceledException) cause);
                } else {
                    throw new IllegalStateException(cause);
                }
            } catch (Throwable th) {
                cancel(true);
            }
        } else {
            v = get(l.longValue(), timeUnit);
            cancel(true);
        }
        return v;
    }

    public V getResult() {
        return internalGetResult(Long.valueOf(DEFAULT_TIMEOUT_MINUTE), TimeUnit.MINUTES);
    }

    public V getResult(long j, TimeUnit timeUnit) {
        return internalGetResult(Long.valueOf(j), timeUnit);
    }

    public void set(V v) {
        super.set(v);
    }

    public void setException(Throwable th) {
        super.setException(th);
    }
}
