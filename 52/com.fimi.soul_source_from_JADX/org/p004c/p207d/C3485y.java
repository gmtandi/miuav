package org.p004c.p207d;

import java.util.concurrent.TimeUnit;

/* renamed from: org.c.d.y */
public class C3485y {
    private boolean f16009a;
    private long f16010b;
    private TimeUnit f16011c;

    protected C3485y() {
        this.f16009a = false;
        this.f16010b = 0;
        this.f16011c = TimeUnit.SECONDS;
    }

    protected long m19019a() {
        return this.f16010b;
    }

    public C3485y m19020a(long j, TimeUnit timeUnit) {
        this.f16010b = j;
        this.f16011c = timeUnit;
        return this;
    }

    public C3485y m19021a(boolean z) {
        this.f16009a = z;
        return this;
    }

    protected TimeUnit m19022b() {
        return this.f16011c;
    }

    protected boolean m19023c() {
        return this.f16009a;
    }

    public C3483w m19024d() {
        return new C3483w(this);
    }
}
