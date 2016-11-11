package com.android.volley;

import android.os.Handler;
import java.util.concurrent.Executor;

/* renamed from: com.android.volley.g */
public class C0559g implements ac {
    private final Executor f3528a;

    public C0559g(Handler handler) {
        this.f3528a = new C0560h(this, handler);
    }

    public C0559g(Executor executor) {
        this.f3528a = executor;
    }

    public void m5081a(C0570r<?> c0570r, ag agVar) {
        c0570r.m5106a("post-error");
        this.f3528a.execute(new C0561i(this, c0570r, C0604z.m5292a(agVar), null));
    }

    public void m5082a(C0570r<?> c0570r, C0604z<?> c0604z) {
        m5083a(c0570r, c0604z, null);
    }

    public void m5083a(C0570r<?> c0570r, C0604z<?> c0604z, Runnable runnable) {
        c0570r.m5094A();
        c0570r.m5106a("post-response");
        this.f3528a.execute(new C0561i(this, c0570r, c0604z, runnable));
    }
}
