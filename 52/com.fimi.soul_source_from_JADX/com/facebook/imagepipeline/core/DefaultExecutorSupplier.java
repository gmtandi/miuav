package com.facebook.imagepipeline.core;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DefaultExecutorSupplier implements ExecutorSupplier {
    private static final int NUM_IO_BOUND_THREADS = 2;
    private static final int NUM_LIGHTWEIGHT_BACKGROUND_THREADS = 1;
    private final Executor mBackgroundExecutor;
    private final Executor mDecodeExecutor;
    private final Executor mIoBoundExecutor;
    private final Executor mLightWeightBackgroundExecutor;

    public DefaultExecutorSupplier(int i) {
        ThreadFactory priorityThreadFactory = new PriorityThreadFactory(10);
        this.mIoBoundExecutor = Executors.newFixedThreadPool(NUM_IO_BOUND_THREADS);
        this.mDecodeExecutor = Executors.newFixedThreadPool(i, priorityThreadFactory);
        this.mBackgroundExecutor = Executors.newFixedThreadPool(i, priorityThreadFactory);
        this.mLightWeightBackgroundExecutor = Executors.newFixedThreadPool(NUM_LIGHTWEIGHT_BACKGROUND_THREADS, priorityThreadFactory);
    }

    public Executor forBackgroundTasks() {
        return this.mBackgroundExecutor;
    }

    public Executor forDecode() {
        return this.mDecodeExecutor;
    }

    public Executor forLightweightBackgroundTasks() {
        return this.mLightWeightBackgroundExecutor;
    }

    public Executor forLocalStorageRead() {
        return this.mIoBoundExecutor;
    }

    public Executor forLocalStorageWrite() {
        return this.mIoBoundExecutor;
    }
}
