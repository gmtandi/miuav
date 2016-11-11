package com.tencent.open.utils;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.lang.reflect.Field;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class ThreadManager {
    public static final boolean DEBUG_THREAD = false;
    public static final Executor NETWORK_EXECUTOR;
    private static Handler f12110a;
    private static HandlerThread f12111b;
    private static Handler f12112c;
    private static HandlerThread f12113d;

    static {
        NETWORK_EXECUTOR = m13846a();
    }

    private static Executor m13846a() {
        Executor threadPoolExecutor;
        if (VERSION.SDK_INT >= 11) {
            threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue());
        } else {
            Executor executor;
            try {
                Field declaredField = AsyncTask.class.getDeclaredField("sExecutor");
                declaredField.setAccessible(true);
                executor = (Executor) declaredField.get(null);
            } catch (Exception e) {
                Object threadPoolExecutor2 = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue());
            }
            threadPoolExecutor = executor;
        }
        if (threadPoolExecutor instanceof ThreadPoolExecutor) {
            ((ThreadPoolExecutor) threadPoolExecutor).setCorePoolSize(3);
        }
        return threadPoolExecutor;
    }

    public static void executeOnNetWorkThread(Runnable runnable) {
        try {
            NETWORK_EXECUTOR.execute(runnable);
        } catch (RejectedExecutionException e) {
        }
    }

    public static void executeOnSubThread(Runnable runnable) {
        getSubThreadHandler().post(runnable);
    }

    public static Handler getFileThreadHandler() {
        if (f12112c == null) {
            synchronized (ThreadManager.class) {
                f12113d = new HandlerThread("QQ_FILE_RW");
                f12113d.start();
                f12112c = new Handler(f12113d.getLooper());
            }
        }
        return f12112c;
    }

    public static Looper getFileThreadLooper() {
        return getFileThreadHandler().getLooper();
    }

    public static Thread getSubThread() {
        if (f12111b == null) {
            getSubThreadHandler();
        }
        return f12111b;
    }

    public static Handler getSubThreadHandler() {
        if (f12110a == null) {
            synchronized (ThreadManager.class) {
                f12111b = new HandlerThread("QQ_SUB");
                f12111b.start();
                f12110a = new Handler(f12111b.getLooper());
            }
        }
        return f12110a;
    }

    public static Looper getSubThreadLooper() {
        return getSubThreadHandler().getLooper();
    }

    public static void init() {
    }
}
