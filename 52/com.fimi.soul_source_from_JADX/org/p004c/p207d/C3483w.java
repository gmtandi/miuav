package org.p004c.p207d;

import java.util.concurrent.TimeUnit;
import org.p004c.p005e.C3507d;
import org.p004c.p187f.p192a.C3377k;
import org.p004c.p198b.p202d.p205c.C3424c;

/* renamed from: org.c.d.w */
public class C3483w implements C3460r {
    private final long f16004a;
    private final TimeUnit f16005b;
    private final boolean f16006c;

    @Deprecated
    public C3483w(int i) {
        this((long) i, TimeUnit.MILLISECONDS);
    }

    public C3483w(long j, TimeUnit timeUnit) {
        this.f16004a = j;
        this.f16005b = timeUnit;
        this.f16006c = false;
    }

    protected C3483w(C3485y c3485y) {
        this.f16004a = c3485y.m19019a();
        this.f16005b = c3485y.m19022b();
        this.f16006c = c3485y.m19023c();
    }

    public static C3483w m19011a(long j) {
        return new C3483w(j, TimeUnit.MILLISECONDS);
    }

    public static C3485y m19012a() {
        return new C3485y();
    }

    public static C3483w m19013b(long j) {
        return new C3483w(j, TimeUnit.SECONDS);
    }

    protected final long m19014a(TimeUnit timeUnit) {
        return timeUnit.convert(this.f16004a, this.f16005b);
    }

    protected C3377k m19015a(C3377k c3377k) {
        return C3424c.m18717b().m18726a(this.f16004a, this.f16005b).m18727a(this.f16006c).m18725a(c3377k);
    }

    public C3377k m19016a(C3377k c3377k, C3507d c3507d) {
        try {
            return m19015a(c3377k);
        } catch (Exception e) {
            return new C3484x(this, e);
        }
    }

    protected final boolean m19017b() {
        return this.f16006c;
    }
}
