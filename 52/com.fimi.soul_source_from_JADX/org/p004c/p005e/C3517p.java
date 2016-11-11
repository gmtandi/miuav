package org.p004c.p005e;

import org.p004c.p005e.p007b.C0133b;
import org.p004c.p005e.p007b.C3493a;
import org.p004c.p005e.p007b.C3494c;

@C3494c
/* renamed from: org.c.e.p */
class C3517p extends C0133b {
    final /* synthetic */ C3515n f16063a;

    private C3517p(C3515n c3515n) {
        this.f16063a = c3515n;
    }

    public void m19147a(C3493a c3493a) {
        this.f16063a.f16059c.add(c3493a);
    }

    public void m19148a(C3507d c3507d) {
        this.f16063a.f16057a.getAndIncrement();
    }

    public void m19149a(C3515n c3515n) {
        this.f16063a.f16060d.addAndGet(System.currentTimeMillis() - this.f16063a.f16061e.get());
    }

    public void m19150b(C3493a c3493a) {
    }

    public void m19151c(C3507d c3507d) {
        this.f16063a.f16058b.getAndIncrement();
    }

    public void m19152d(C3507d c3507d) {
        this.f16063a.f16061e.set(System.currentTimeMillis());
    }
}
