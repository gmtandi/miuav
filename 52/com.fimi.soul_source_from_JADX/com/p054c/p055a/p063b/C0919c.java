package com.p054c.p055a.p063b;

import android.graphics.Bitmap;
import com.p054c.p055a.p063b.p064a.C0901g;
import com.p054c.p055a.p063b.p067c.C0912a;
import com.p054c.p055a.p063b.p069e.C0925a;
import com.p054c.p055a.p063b.p070f.C0930a;
import com.p054c.p055a.p072c.C0958f;

/* renamed from: com.c.a.b.c */
final class C0919c implements Runnable {
    private static final String f4821a = "Display image in ImageAware (loaded from %1$s) [%2$s]";
    private static final String f4822b = "ImageAware is reused for another image. Task is cancelled. [%s]";
    private static final String f4823c = "ImageAware was collected by GC. Task is cancelled. [%s]";
    private final Bitmap f4824d;
    private final String f4825e;
    private final C0925a f4826f;
    private final String f4827g;
    private final C0912a f4828h;
    private final C0930a f4829i;
    private final C0944o f4830j;
    private final C0901g f4831k;

    public C0919c(Bitmap bitmap, C0946q c0946q, C0944o c0944o, C0901g c0901g) {
        this.f4824d = bitmap;
        this.f4825e = c0946q.f4981a;
        this.f4826f = c0946q.f4983c;
        this.f4827g = c0946q.f4982b;
        this.f4828h = c0946q.f4985e.m7310q();
        this.f4829i = c0946q.f4986f;
        this.f4830j = c0944o;
        this.f4831k = c0901g;
    }

    private boolean m7252a() {
        return !this.f4827g.equals(this.f4830j.m7500a(this.f4826f));
    }

    public void run() {
        if (this.f4826f.m7319e()) {
            C0958f.m7554a(f4823c, this.f4827g);
            this.f4829i.m7350b(this.f4825e, this.f4826f.m7318d());
        } else if (m7252a()) {
            C0958f.m7554a(f4822b, this.f4827g);
            this.f4829i.m7350b(this.f4825e, this.f4826f.m7318d());
        } else {
            C0958f.m7554a(f4821a, this.f4831k, this.f4827g);
            this.f4828h.m7246a(this.f4824d, this.f4826f, this.f4831k);
            this.f4830j.m7509b(this.f4826f);
            this.f4829i.m7348a(this.f4825e, this.f4826f.m7318d(), this.f4824d);
        }
    }
}
