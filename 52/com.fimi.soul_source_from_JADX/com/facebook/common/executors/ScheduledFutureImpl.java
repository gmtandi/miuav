package com.facebook.common.executors;

import android.os.Handler;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class ScheduledFutureImpl<V> implements RunnableFuture<V>, ScheduledFuture<V> {
    private final Handler mHandler;
    private final FutureTask<V> mListenableFuture;

    public ScheduledFutureImpl(Handler handler, Runnable runnable, @Nullable V v) {
        this.mHandler = handler;
        this.mListenableFuture = new FutureTask(runnable, v);
    }

    public ScheduledFutureImpl(Handler handler, Callable<V> callable) {
        this.mHandler = handler;
        this.mListenableFuture = new FutureTask(callable);
    }

    public boolean cancel(boolean z) {
        return this.mListenableFuture.cancel(z);
    }

    public int compareTo(Delayed delayed) {
        throw new UnsupportedOperationException();
    }

    public V get() {
        return this.mListenableFuture.get();
    }

    public V get(long j, TimeUnit timeUnit) {
        return this.mListenableFuture.get(j, timeUnit);
    }

    public long getDelay(TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public boolean isCancelled() {
        return this.mListenableFuture.isCancelled();
    }

    public boolean isDone() {
        return this.mListenableFuture.isDone();
    }

    public void run() {
        this.mListenableFuture.run();
    }
}
