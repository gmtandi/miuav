package com.amap.api.services.core;

import com.amap.api.services.core.ax.C0453a;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class av {
    private static av f3031a;
    private ExecutorService f3032b;
    private ConcurrentHashMap<ax, Future<?>> f3033c;
    private C0453a f3034d;

    static {
        f3031a = null;
    }

    private av(int i) {
        this.f3033c = new ConcurrentHashMap();
        this.f3034d = new aw(this);
        try {
            this.f3032b = Executors.newFixedThreadPool(i);
        } catch (Throwable th) {
            ay.m4590a(th, "TPool", "ThreadPool");
            th.printStackTrace();
        }
    }

    public static synchronized av m4575a(int i) {
        av avVar;
        synchronized (av.class) {
            if (f3031a == null) {
                f3031a = new av(i);
            }
            avVar = f3031a;
        }
        return avVar;
    }

    private synchronized void m4577a(ax axVar, boolean z) {
        try {
            Future future = (Future) this.f3033c.remove(axVar);
            if (z && future != null) {
                future.cancel(true);
            }
        } catch (Throwable th) {
            ay.m4590a(th, "TPool", "removeQueue");
            th.printStackTrace();
        }
    }
}
