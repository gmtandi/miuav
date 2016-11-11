package com.amap.api.mapcore.util;

import com.amap.api.mapcore.util.dp.C0379a;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

public final class dn {
    private static dn f2438a;
    private ExecutorService f2439b;
    private ConcurrentHashMap<dp, Future<?>> f2440c;
    private C0379a f2441d;

    static {
        f2438a = null;
    }

    private dn(int i) {
        this.f2440c = new ConcurrentHashMap();
        this.f2441d = new C0380do(this);
        try {
            this.f2439b = Executors.newFixedThreadPool(i);
        } catch (Throwable th) {
            ce.m3829a(th, "TPool", "ThreadPool");
            th.printStackTrace();
        }
    }

    public static synchronized dn m4036a(int i) {
        dn dnVar;
        synchronized (dn.class) {
            if (f2438a == null) {
                f2438a = new dn(i);
            }
            dnVar = f2438a;
        }
        return dnVar;
    }

    public static synchronized void m4037a() {
        synchronized (dn.class) {
            try {
                if (f2438a != null) {
                    f2438a.m4041b();
                    f2438a = null;
                }
            } catch (Throwable th) {
                ce.m3829a(th, "TPool", "onDestroy");
                th.printStackTrace();
            }
        }
    }

    private synchronized void m4039a(dp dpVar, Future<?> future) {
        try {
            this.f2440c.put(dpVar, future);
        } catch (Throwable th) {
            ce.m3829a(th, "TPool", "addQueue");
            th.printStackTrace();
        }
    }

    private synchronized void m4040a(dp dpVar, boolean z) {
        try {
            Future future = (Future) this.f2440c.remove(dpVar);
            if (z && future != null) {
                future.cancel(true);
            }
        } catch (Throwable th) {
            ce.m3829a(th, "TPool", "removeQueue");
            th.printStackTrace();
        }
    }

    private void m4041b() {
        try {
            for (Entry key : this.f2440c.entrySet()) {
                Future future = (Future) this.f2440c.get((dp) key.getKey());
                if (future != null) {
                    future.cancel(true);
                }
            }
            this.f2440c.clear();
            this.f2439b.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            ce.m3829a(th, "TPool", "destroy");
            th.printStackTrace();
        }
    }

    private synchronized boolean m4042b(dp dpVar) {
        boolean z;
        z = false;
        try {
            z = this.f2440c.containsKey(dpVar);
        } catch (Throwable th) {
            ce.m3829a(th, "TPool", "contain");
            th.printStackTrace();
        }
        return z;
    }

    public void m4043a(dp dpVar) {
        try {
            if (!m4042b(dpVar) && this.f2439b != null && !this.f2439b.isShutdown()) {
                dpVar.f2427d = this.f2441d;
                Future submit = this.f2439b.submit(dpVar);
                if (submit != null) {
                    m4039a(dpVar, submit);
                }
            }
        } catch (RejectedExecutionException e) {
        } catch (Throwable th) {
            th.printStackTrace();
            ce.m3829a(th, "TPool", "addTask");
            bk bkVar = new bk("thread pool has exception");
        }
    }
}
