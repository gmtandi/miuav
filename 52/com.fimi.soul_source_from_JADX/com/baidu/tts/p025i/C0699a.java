package com.baidu.tts.p025i;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.baidu.tts.i.a */
public abstract class C0699a implements C0673b {
    protected final Lock f4089d;
    protected final Condition f4090e;

    /* renamed from: com.baidu.tts.i.a.a */
    public interface C0812a {
        void m6797a();
    }

    public C0699a() {
        this.f4089d = new ReentrantLock();
        this.f4090e = this.f4089d.newCondition();
    }

    public boolean m6141A() {
        try {
            m6142a(null);
        } catch (InterruptedException e) {
            m6158z();
        }
        return !m6156n();
    }

    public void m6142a(C0812a c0812a) {
        while (m6155m()) {
            m6144b(c0812a);
        }
    }

    public synchronized TtsError m6143b() {
        return m6149g();
    }

    public void m6144b(C0812a c0812a) {
        try {
            this.f4089d.lock();
            if (c0812a != null) {
                c0812a.m6797a();
            }
            LoggerProxy.m6515d("ASafeLife", "before await");
            this.f4090e.await();
            LoggerProxy.m6515d("ASafeLife", "after await");
        } finally {
            this.f4089d.unlock();
        }
    }

    public synchronized void m6145c() {
        m6151i();
        try {
            this.f4089d.lock();
            this.f4090e.signalAll();
            this.f4089d.unlock();
        } catch (Exception e) {
            e.printStackTrace();
            this.f4089d.unlock();
        } catch (Throwable th) {
            this.f4089d.unlock();
        }
    }

    public synchronized void m6146d() {
        m6152j();
    }

    public synchronized void m6147e() {
        m6153k();
    }

    public synchronized void m6148f() {
        m6154l();
    }

    protected abstract TtsError m6149g();

    protected abstract void m6150h();

    protected abstract void m6151i();

    protected abstract void m6152j();

    protected abstract void m6153k();

    protected abstract void m6154l();

    public abstract boolean m6155m();

    public abstract boolean m6156n();

    public synchronized void m6157y() {
        m6150h();
    }

    public void m6158z() {
        Thread.currentThread().interrupt();
    }
}
