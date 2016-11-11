package org.p004c.p188a.p191b;

import java.util.HashMap;
import java.util.Map;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.C3515n;
import org.p004c.p005e.p007b.C0133b;
import org.p004c.p005e.p007b.C3493a;

/* renamed from: org.c.a.b.g */
final class C3345g extends C0133b {
    final /* synthetic */ C3343e f15849a;
    private long f15850b;
    private Map<C3507d, Long> f15851c;

    private C3345g(C3343e c3343e) {
        this.f15849a = c3343e;
        this.f15850b = System.currentTimeMillis();
        this.f15851c = new HashMap();
    }

    public void m18451a(C3493a c3493a) {
        this.f15849a.m18446a(c3493a.m19046b(), this.f15850b);
    }

    public void m18452a(C3507d c3507d) {
        this.f15849a.m18448b(c3507d, System.nanoTime() - ((Long) this.f15851c.get(c3507d)).longValue());
    }

    public void m18453a(C3515n c3515n) {
        this.f15849a.m18443c();
    }

    public void m18454b(C3507d c3507d) {
        this.f15851c.put(c3507d, Long.valueOf(System.nanoTime()));
    }
}
