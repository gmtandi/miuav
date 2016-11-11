package org.p004c.p198b.p202d.p205c;

import java.util.concurrent.TimeUnit;
import org.p004c.p187f.p192a.C3377k;

/* renamed from: org.c.b.d.c.e */
public class C3426e {
    private boolean f15933a;
    private long f15934b;
    private TimeUnit f15935c;

    private C3426e() {
        this.f15933a = false;
        this.f15934b = 0;
        this.f15935c = TimeUnit.SECONDS;
    }

    public C3424c m18725a(C3377k c3377k) {
        if (c3377k != null) {
            return new C3424c(c3377k, null);
        }
        throw new NullPointerException("statement cannot be null");
    }

    public C3426e m18726a(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout must be non-negative");
        } else if (timeUnit == null) {
            throw new NullPointerException("TimeUnit cannot be null");
        } else {
            this.f15934b = j;
            this.f15935c = timeUnit;
            return this;
        }
    }

    public C3426e m18727a(boolean z) {
        this.f15933a = z;
        return this;
    }
}
