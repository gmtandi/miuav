package com.baidu.tts.p037c;

import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.p042f.p043a.C0804a;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.baidu.tts.c.a */
public class C0747a extends ThreadPoolExecutor {

    /* renamed from: com.baidu.tts.c.a.a */
    public class C0746a implements RejectedExecutionHandler {
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            LoggerProxy.m6515d("LimitQueueThreadPoolExecutor", "rejectedExecution");
            if (!threadPoolExecutor.isShutdown()) {
                try {
                    threadPoolExecutor.getQueue().put(runnable);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public C0747a(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i, i2, j, timeUnit, blockingQueue, threadFactory, rejectedExecutionHandler);
    }

    public C0747a(int i, String str) {
        this(i, str, new C0746a());
    }

    public C0747a(int i, String str, RejectedExecutionHandler rejectedExecutionHandler) {
        this(i, new C0804a(str), rejectedExecutionHandler);
    }

    public C0747a(int i, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        this(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(i), threadFactory, rejectedExecutionHandler);
    }
}
