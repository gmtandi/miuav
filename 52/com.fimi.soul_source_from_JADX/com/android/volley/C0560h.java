package com.android.volley;

import android.os.Handler;
import java.util.concurrent.Executor;

/* renamed from: com.android.volley.h */
class C0560h implements Executor {
    final /* synthetic */ Handler f3529a;
    final /* synthetic */ C0559g f3530b;

    C0560h(C0559g c0559g, Handler handler) {
        this.f3530b = c0559g;
        this.f3529a = handler;
    }

    public void execute(Runnable runnable) {
        this.f3529a.post(runnable);
    }
}
