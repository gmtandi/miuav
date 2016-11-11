package com.fimi.kernel;

import android.os.Handler;
import android.os.Message;
import com.fimi.kernel.p084e.ah;

/* renamed from: com.fimi.kernel.d */
public abstract class C1099d {
    private C1188e f5099a;

    public C1099d() {
        this.f5099a = new C1188e(this);
    }

    protected Handler m7685a() {
        return this.f5099a;
    }

    protected abstract void m7686a(Message message);

    protected void m7687a(Runnable runnable) {
        ah.m8075a(runnable);
    }
}
