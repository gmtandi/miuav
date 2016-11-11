package com.xiaomi.kenai.jbosh;

import java.util.EventObject;

public final class ab extends EventObject {
    private final C2501b f12637a;

    private ab(Object obj, C2501b c2501b) {
        super(obj);
        if (c2501b == null) {
            throw new IllegalArgumentException("message body may not be null");
        }
        this.f12637a = c2501b;
    }

    static ab m14305a(C2518s c2518s, C2501b c2501b) {
        return new ab(c2518s, c2501b);
    }

    static ab m14306b(C2518s c2518s, C2501b c2501b) {
        return new ab(c2518s, c2501b);
    }

    public C2501b m14307a() {
        return this.f12637a;
    }
}
