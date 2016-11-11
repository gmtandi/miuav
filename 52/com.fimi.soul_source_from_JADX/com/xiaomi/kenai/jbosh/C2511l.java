package com.xiaomi.kenai.jbosh;

import java.util.concurrent.TimeUnit;

/* renamed from: com.xiaomi.kenai.jbosh.l */
final class C2511l extends C2502c {
    private C2511l(String str) {
        super(str);
        m14373a(1);
    }

    static C2511l m14390a(String str) {
        return str == null ? null : new C2511l(str);
    }

    public int m14391c() {
        return (int) TimeUnit.MILLISECONDS.convert((long) m14374b(), TimeUnit.SECONDS);
    }
}
