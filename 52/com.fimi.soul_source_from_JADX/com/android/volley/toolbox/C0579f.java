package com.android.volley.toolbox;

import android.os.Handler;
import android.os.Looper;
import com.android.volley.C0554b;
import com.android.volley.C0566n;
import com.android.volley.C0570r;
import com.android.volley.C0599u;
import com.android.volley.C0604z;

/* renamed from: com.android.volley.toolbox.f */
public class C0579f extends C0570r<Object> {
    private final C0554b f3610a;
    private final Runnable f3611b;

    public C0579f(C0554b c0554b, Runnable runnable) {
        super(0, null, null);
        this.f3610a = c0554b;
        this.f3611b = runnable;
    }

    protected C0604z<Object> m5189a(C0566n c0566n) {
        return null;
    }

    protected void m5190b(Object obj) {
    }

    public boolean m5191m() {
        this.f3610a.m5070b();
        if (this.f3611b != null) {
            new Handler(Looper.getMainLooper()).postAtFrontOfQueue(this.f3611b);
        }
        return true;
    }

    public C0599u m5192x() {
        return C0599u.IMMEDIATE;
    }
}
