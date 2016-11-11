package com.p054c.p055a.p063b;

import android.graphics.Bitmap;
import android.os.Handler;
import com.p054c.p055a.p063b.p064a.C0901g;
import com.p054c.p055a.p072c.C0958f;

/* renamed from: com.c.a.b.w */
final class C0953w implements Runnable {
    private static final String f5035a = "PostProcess image before displaying [%s]";
    private final C0944o f5036b;
    private final Bitmap f5037c;
    private final C0946q f5038d;
    private final Handler f5039e;

    public C0953w(C0944o c0944o, Bitmap bitmap, C0946q c0946q, Handler handler) {
        this.f5036b = c0944o;
        this.f5037c = bitmap;
        this.f5038d = c0946q;
        this.f5039e = handler;
    }

    public void run() {
        C0958f.m7554a(f5035a, this.f5038d.f4982b);
        C0948r.m7520a(new C0919c(this.f5038d.f4985e.m7309p().m7402a(this.f5037c), this.f5038d, this.f5036b, C0901g.MEMORY_CACHE), this.f5038d.f4985e.m7312s(), this.f5039e, this.f5036b);
    }
}
