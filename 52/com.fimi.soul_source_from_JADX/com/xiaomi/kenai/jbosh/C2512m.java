package com.xiaomi.kenai.jbosh;

import java.util.concurrent.TimeUnit;

/* renamed from: com.xiaomi.kenai.jbosh.m */
final class C2512m extends C2502c {
    private C2512m(String str) {
        super(str);
        m14373a(0);
    }

    static C2512m m14392a(String str) {
        return str == null ? null : new C2512m(str);
    }

    public int m14393c() {
        return (int) TimeUnit.MILLISECONDS.convert((long) m14374b(), TimeUnit.SECONDS);
    }
}
