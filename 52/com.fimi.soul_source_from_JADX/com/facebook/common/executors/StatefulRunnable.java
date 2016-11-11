package com.facebook.common.executors;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class StatefulRunnable<T> implements Runnable {
    protected static final int STATE_CANCELLED = 2;
    protected static final int STATE_CREATED = 0;
    protected static final int STATE_FAILED = 4;
    protected static final int STATE_FINISHED = 3;
    protected static final int STATE_STARTED = 1;
    protected final AtomicInteger mState;

    public StatefulRunnable() {
        this.mState = new AtomicInteger(STATE_CREATED);
    }

    public void cancel() {
        if (this.mState.compareAndSet(STATE_CREATED, STATE_CANCELLED)) {
            onCancellation();
        }
    }

    protected void disposeResult(T t) {
    }

    protected abstract T getResult();

    protected void onCancellation() {
    }

    protected void onFailure(Exception exception) {
    }

    protected void onSuccess(T t) {
    }

    public final void run() {
        if (this.mState.compareAndSet(STATE_CREATED, STATE_STARTED)) {
            try {
                Object result = getResult();
                this.mState.set(STATE_FINISHED);
                try {
                    onSuccess(result);
                } finally {
                    disposeResult(result);
                }
            } catch (Exception e) {
                this.mState.set(STATE_FAILED);
                onFailure(e);
            }
        }
    }
}
