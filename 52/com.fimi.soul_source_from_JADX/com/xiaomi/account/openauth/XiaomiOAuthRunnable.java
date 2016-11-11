package com.xiaomi.account.openauth;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class XiaomiOAuthRunnable<V> implements Runnable {
    private static ExecutorService sTaskExecutor;
    protected XiaomiOAuthFutureImpl<V> mFuture;

    static {
        sTaskExecutor = Executors.newCachedThreadPool();
    }

    XiaomiOAuthRunnable() {
        this.mFuture = new XiaomiOAuthFutureImpl();
    }

    protected abstract void doRun();

    public final void run() {
        doRun();
    }

    XiaomiOAuthFutureImpl<V> start() {
        sTaskExecutor.execute(this);
        return this.mFuture;
    }
}
