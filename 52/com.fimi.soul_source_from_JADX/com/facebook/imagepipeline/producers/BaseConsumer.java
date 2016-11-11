package com.facebook.imagepipeline.producers;

import com.facebook.common.logging.FLog;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class BaseConsumer<T> implements Consumer<T> {
    private boolean mIsFinished;

    public BaseConsumer() {
        this.mIsFinished = false;
    }

    public synchronized void onCancellation() {
        if (!this.mIsFinished) {
            this.mIsFinished = true;
            try {
                onCancellationImpl();
            } catch (Exception e) {
                onUnhandledException(e);
            }
        }
    }

    protected abstract void onCancellationImpl();

    public synchronized void onFailure(Throwable th) {
        if (!this.mIsFinished) {
            this.mIsFinished = true;
            try {
                onFailureImpl(th);
            } catch (Exception e) {
                onUnhandledException(e);
            }
        }
    }

    protected abstract void onFailureImpl(Throwable th);

    public synchronized void onNewResult(@Nullable T t, boolean z) {
        if (!this.mIsFinished) {
            this.mIsFinished = z;
            try {
                onNewResultImpl(t, z);
            } catch (Exception e) {
                onUnhandledException(e);
            }
        }
    }

    protected abstract void onNewResultImpl(T t, boolean z);

    public synchronized void onProgressUpdate(float f) {
        if (!this.mIsFinished) {
            try {
                onProgressUpdateImpl(f);
            } catch (Exception e) {
                onUnhandledException(e);
            }
        }
    }

    protected void onProgressUpdateImpl(float f) {
    }

    protected void onUnhandledException(Exception exception) {
        FLog.wtf(getClass(), "unhandled exception", (Throwable) exception);
    }
}
