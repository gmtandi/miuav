package com.facebook.common.executors;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

public class CallerThreadExecutor extends AbstractExecutorService {
    private static final CallerThreadExecutor sInstance;

    static {
        sInstance = new CallerThreadExecutor();
    }

    private CallerThreadExecutor() {
    }

    public static CallerThreadExecutor getInstance() {
        return sInstance;
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) {
        return true;
    }

    public void execute(Runnable runnable) {
        runnable.run();
    }

    public boolean isShutdown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }

    public void shutdown() {
    }

    public List<Runnable> shutdownNow() {
        shutdown();
        return Collections.emptyList();
    }
}
