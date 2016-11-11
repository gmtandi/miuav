package com.p054c.p055a.p063b.p067c;

import android.graphics.Bitmap;
import com.p054c.p055a.p063b.p064a.C0901g;
import com.p054c.p055a.p063b.p069e.C0925a;
import com.p054c.p055a.p063b.p069e.C0927b;

/* renamed from: com.c.a.b.c.e */
public class C0916e extends C0914c {
    public C0916e(int i, int i2) {
        super(i, i2);
    }

    public void m7250a(Bitmap bitmap, C0925a c0925a, C0901g c0901g) {
        if (c0925a instanceof C0927b) {
            c0925a.m7315a(new C0917f(bitmap, this.a, this.b));
            return;
        }
        throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
    }
}
