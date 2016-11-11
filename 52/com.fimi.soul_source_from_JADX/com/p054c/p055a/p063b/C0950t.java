package com.p054c.p055a.p063b;

import com.p054c.p055a.p063b.p064a.C0896b;
import com.p054c.p055a.p063b.p064a.C0897c;

/* renamed from: com.c.a.b.t */
class C0950t implements Runnable {
    final /* synthetic */ C0897c f5030a;
    final /* synthetic */ Throwable f5031b;
    final /* synthetic */ C0948r f5032c;

    C0950t(C0948r c0948r, C0897c c0897c, Throwable th) {
        this.f5032c = c0948r;
        this.f5030a = c0897c;
        this.f5031b = th;
    }

    public void run() {
        if (this.f5032c.f5024c.m7296c()) {
            this.f5032c.f5023b.m7315a(this.f5032c.f5024c.m7295c(this.f5032c.f5013D.f4915a));
        }
        this.f5032c.f5025d.m7349a(this.f5032c.f5022a, this.f5032c.f5023b.m7318d(), new C0896b(this.f5030a, this.f5031b));
    }
}
