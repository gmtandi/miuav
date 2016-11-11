package com.p054c.p055a.p063b.p067c;

import android.graphics.Bitmap;
import com.p054c.p055a.p063b.p064a.C0901g;
import com.p054c.p055a.p063b.p069e.C0925a;
import com.p054c.p055a.p063b.p069e.C0927b;

/* renamed from: com.c.a.b.c.c */
public class C0914c implements C0912a {
    protected final int f4813a;
    protected final int f4814b;

    public C0914c(int i) {
        this(i, 0);
    }

    public C0914c(int i, int i2) {
        this.f4813a = i;
        this.f4814b = i2;
    }

    public void m7249a(Bitmap bitmap, C0925a c0925a, C0901g c0901g) {
        if (c0925a instanceof C0927b) {
            c0925a.m7315a(new C0915d(bitmap, this.f4813a, this.f4814b));
            return;
        }
        throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
    }
}
