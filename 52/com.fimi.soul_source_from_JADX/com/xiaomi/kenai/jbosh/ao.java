package com.xiaomi.kenai.jbosh;

import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class ao {
    private static final SecureRandom f12670a;
    private static final Lock f12671b;
    private AtomicLong f12672c;

    static {
        f12670a = new SecureRandom();
        f12671b = new ReentrantLock();
    }

    ao() {
        this.f12672c = new AtomicLong();
        this.f12672c = new AtomicLong(m14361b());
    }

    private long m14361b() {
        long nextLong;
        f12671b.lock();
        while (true) {
            try {
                nextLong = f12670a.nextLong() & 9007199254740991L;
                if (nextLong <= 9007194959773696L) {
                    break;
                }
            } finally {
                f12671b.unlock();
            }
        }
        return nextLong;
    }

    public long m14362a() {
        return this.f12672c.getAndIncrement();
    }
}
