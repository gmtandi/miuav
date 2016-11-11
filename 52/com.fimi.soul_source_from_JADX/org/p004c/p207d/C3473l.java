package org.p004c.p207d;

import java.util.concurrent.TimeUnit;
import org.p004c.C3520e;
import org.p004c.p005e.C3507d;
import org.p004c.p187f.p192a.C3377k;

/* renamed from: org.c.d.l */
public abstract class C3473l implements C3460r {
    private final C3475n f15991a;
    private volatile long f15992b;
    private volatile long f15993c;

    public C3473l() {
        this(new C3475n());
    }

    C3473l(C3475n c3475n) {
        this.f15991a = c3475n;
    }

    private long m18953a() {
        if (this.f15992b == 0) {
            throw new IllegalStateException("Test has not started");
        }
        long j = this.f15993c;
        if (j == 0) {
            j = this.f15991a.m18965a();
        }
        return j - this.f15992b;
    }

    private void m18956b() {
        this.f15992b = this.f15991a.m18965a();
        this.f15993c = 0;
    }

    private void m18957c() {
        this.f15993c = this.f15991a.m18965a();
    }

    public long m18959a(TimeUnit timeUnit) {
        return timeUnit.convert(m18953a(), TimeUnit.NANOSECONDS);
    }

    public final C3377k m18960a(C3377k c3377k, C3507d c3507d) {
        return new C3477o().m18976a(c3377k, c3507d);
    }

    protected void m18961a(long j, Throwable th, C3507d c3507d) {
    }

    protected void m18962a(long j, C3507d c3507d) {
    }

    protected void m18963a(long j, C3520e c3520e, C3507d c3507d) {
    }

    protected void m18964b(long j, C3507d c3507d) {
    }
}
