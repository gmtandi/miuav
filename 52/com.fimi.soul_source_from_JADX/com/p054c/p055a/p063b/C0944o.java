package com.p054c.p055a.p063b;

import com.p054c.p055a.p063b.p069e.C0925a;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.c.a.b.o */
class C0944o {
    final C0939j f4969a;
    private Executor f4970b;
    private Executor f4971c;
    private Executor f4972d;
    private final Map<Integer, String> f4973e;
    private final Map<String, ReentrantLock> f4974f;
    private final AtomicBoolean f4975g;
    private final AtomicBoolean f4976h;
    private final AtomicBoolean f4977i;
    private final Object f4978j;

    C0944o(C0939j c0939j) {
        this.f4973e = Collections.synchronizedMap(new HashMap());
        this.f4974f = new WeakHashMap();
        this.f4975g = new AtomicBoolean(false);
        this.f4976h = new AtomicBoolean(false);
        this.f4977i = new AtomicBoolean(false);
        this.f4978j = new Object();
        this.f4969a = c0939j;
        this.f4970b = c0939j.f4921g;
        this.f4971c = c0939j.f4922h;
        this.f4972d = C0905a.m7216a();
    }

    private void m7498h() {
        if (!this.f4969a.f4923i && ((ExecutorService) this.f4970b).isShutdown()) {
            this.f4970b = m7499i();
        }
        if (!this.f4969a.f4924j && ((ExecutorService) this.f4971c).isShutdown()) {
            this.f4971c = m7499i();
        }
    }

    private Executor m7499i() {
        return C0905a.m7217a(this.f4969a.f4925k, this.f4969a.f4926l, this.f4969a.f4927m);
    }

    String m7500a(C0925a c0925a) {
        return (String) this.f4973e.get(Integer.valueOf(c0925a.m7320f()));
    }

    ReentrantLock m7501a(String str) {
        ReentrantLock reentrantLock = (ReentrantLock) this.f4974f.get(str);
        if (reentrantLock != null) {
            return reentrantLock;
        }
        reentrantLock = new ReentrantLock();
        this.f4974f.put(str, reentrantLock);
        return reentrantLock;
    }

    void m7502a() {
        this.f4975g.set(true);
    }

    void m7503a(C0925a c0925a, String str) {
        this.f4973e.put(Integer.valueOf(c0925a.m7320f()), str);
    }

    void m7504a(C0948r c0948r) {
        this.f4972d.execute(new C0945p(this, c0948r));
    }

    void m7505a(C0953w c0953w) {
        m7498h();
        this.f4971c.execute(c0953w);
    }

    void m7506a(Runnable runnable) {
        this.f4972d.execute(runnable);
    }

    void m7507a(boolean z) {
        this.f4976h.set(z);
    }

    void m7508b() {
        this.f4975g.set(false);
        synchronized (this.f4978j) {
            this.f4978j.notifyAll();
        }
    }

    void m7509b(C0925a c0925a) {
        this.f4973e.remove(Integer.valueOf(c0925a.m7320f()));
    }

    void m7510b(boolean z) {
        this.f4977i.set(z);
    }

    void m7511c() {
        if (!this.f4969a.f4923i) {
            ((ExecutorService) this.f4970b).shutdownNow();
        }
        if (!this.f4969a.f4924j) {
            ((ExecutorService) this.f4971c).shutdownNow();
        }
        this.f4973e.clear();
        this.f4974f.clear();
    }

    AtomicBoolean m7512d() {
        return this.f4975g;
    }

    Object m7513e() {
        return this.f4978j;
    }

    boolean m7514f() {
        return this.f4976h.get();
    }

    boolean m7515g() {
        return this.f4977i.get();
    }
}
