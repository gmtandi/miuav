package com.baidu.tts.p030j;

import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* renamed from: com.baidu.tts.j.c */
public class C0813c<A extends C0692b<A, R>, R extends C0690a> {
    private final ConcurrentMap<A, Future<R>> f4574a;

    public C0813c() {
        this.f4574a = new ConcurrentHashMap();
    }

    private A m6798b(A a) {
        for (C0692b c0692b : this.f4574a.keySet()) {
            if (a.compareTo(c0692b) == 0) {
                return c0692b;
            }
        }
        return null;
    }

    public R m6799a(A a) {
        Future future;
        Future futureTask;
        C0692b b = m6798b(a);
        Future future2 = b != null ? (Future) this.f4574a.get(b) : null;
        if (future2 != null) {
            LoggerProxy.m6515d("Memorizer", "+ get f=" + future2);
            C0690a c0690a = (C0690a) future2.get();
            LoggerProxy.m6515d("Memorizer", "- get f=" + future2);
            if (!c0690a.m6105g()) {
                LoggerProxy.m6515d("Memorizer", "arg invalid r=" + c0690a);
                this.f4574a.remove(b);
                future = null;
                if (future == null) {
                    futureTask = new FutureTask(a);
                    future = (Future) this.f4574a.putIfAbsent(a, futureTask);
                    if (future == null) {
                        LoggerProxy.m6515d("Memorizer", "+ run f=" + futureTask);
                        futureTask.run();
                        LoggerProxy.m6515d("Memorizer", "- run f=" + futureTask);
                        return (C0690a) futureTask.get();
                    }
                }
                futureTask = future;
                return (C0690a) futureTask.get();
            }
        }
        future = future2;
        if (future == null) {
            futureTask = new FutureTask(a);
            future = (Future) this.f4574a.putIfAbsent(a, futureTask);
            if (future == null) {
                LoggerProxy.m6515d("Memorizer", "+ run f=" + futureTask);
                futureTask.run();
                LoggerProxy.m6515d("Memorizer", "- run f=" + futureTask);
                return (C0690a) futureTask.get();
            }
        }
        futureTask = future;
        try {
            return (C0690a) futureTask.get();
        } catch (ExecutionException e) {
            this.f4574a.remove(a, futureTask);
            throw ((Exception) e.getCause());
        } catch (Exception e2) {
            this.f4574a.remove(a, futureTask);
            throw e2;
        }
    }

    public void m6800a() {
        if (this.f4574a != null) {
            this.f4574a.clear();
        }
    }
}
