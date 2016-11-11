package com.xiaomi.kenai.jbosh;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

final class ak {
    private static final Logger f12661a;
    private final C2501b f12662b;
    private final Lock f12663c;
    private final Condition f12664d;
    private al f12665e;

    static {
        f12661a = Logger.getLogger(ak.class.getName());
    }

    ak(C2501b c2501b) {
        this.f12663c = new ReentrantLock();
        this.f12664d = this.f12663c.newCondition();
        if (c2501b == null) {
            throw new IllegalArgumentException("Request body cannot be null");
        }
        this.f12662b = c2501b;
    }

    C2501b m14349a() {
        return this.f12662b;
    }

    void m14350a(al alVar) {
        this.f12663c.lock();
        try {
            if (this.f12665e != null) {
                throw new IllegalStateException("HTTPResponse was already set");
            }
            this.f12665e = alVar;
            this.f12664d.signalAll();
        } finally {
            this.f12663c.unlock();
        }
    }

    al m14351b() {
        this.f12663c.lock();
        while (this.f12665e == null) {
            try {
                this.f12664d.await();
            } catch (Throwable e) {
                f12661a.log(Level.FINEST, "Interrupted", e);
            } catch (Throwable th) {
                this.f12663c.unlock();
            }
        }
        al alVar = this.f12665e;
        this.f12663c.unlock();
        return alVar;
    }
}
