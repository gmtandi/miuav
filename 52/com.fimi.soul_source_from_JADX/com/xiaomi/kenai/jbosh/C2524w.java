package com.xiaomi.kenai.jbosh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EventObject;
import java.util.List;

/* renamed from: com.xiaomi.kenai.jbosh.w */
public final class C2524w extends EventObject {
    private final boolean f12784a;
    private final List<ai> f12785b;
    private final Throwable f12786c;

    private C2524w(C2518s c2518s, boolean z, List<ai> list, Throwable th) {
        super(c2518s);
        this.f12784a = z;
        this.f12786c = th;
        if (this.f12784a) {
            if (th != null) {
                throw new IllegalStateException("Cannot be connected and have a cause");
            } else if (list != null && list.size() > 0) {
                throw new IllegalStateException("Cannot be connected and have outstanding requests");
            }
        }
        if (list == null) {
            this.f12785b = Collections.emptyList();
        } else {
            this.f12785b = Collections.unmodifiableList(new ArrayList(list));
        }
    }

    static C2524w m14452a(C2518s c2518s) {
        return new C2524w(c2518s, true, null, null);
    }

    static C2524w m14453a(C2518s c2518s, List<ai> list, Throwable th) {
        return new C2524w(c2518s, false, list, th);
    }

    static C2524w m14454b(C2518s c2518s) {
        return new C2524w(c2518s, false, null, null);
    }

    public boolean m14455a() {
        return this.f12784a;
    }

    public Throwable m14456b() {
        return this.f12786c;
    }
}
