package com.p054c.p055a.p063b.p067c;

import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.p054c.p055a.p063b.p064a.C0901g;
import com.p054c.p055a.p063b.p069e.C0925a;

/* renamed from: com.c.a.b.c.b */
public class C0913b implements C0912a {
    private final int f4809a;
    private final boolean f4810b;
    private final boolean f4811c;
    private final boolean f4812d;

    public C0913b(int i) {
        this(i, true, true, true);
    }

    public C0913b(int i, boolean z, boolean z2, boolean z3) {
        this.f4809a = i;
        this.f4810b = z;
        this.f4811c = z2;
        this.f4812d = z3;
    }

    public static void m7247a(View view, int i) {
        if (view != null) {
            Animation alphaAnimation = new AlphaAnimation(0.0f, C2020f.f10933c);
            alphaAnimation.setDuration((long) i);
            alphaAnimation.setInterpolator(new DecelerateInterpolator());
            view.startAnimation(alphaAnimation);
        }
    }

    public void m7248a(Bitmap bitmap, C0925a c0925a, C0901g c0901g) {
        c0925a.m7314a(bitmap);
        if ((this.f4810b && c0901g == C0901g.NETWORK) || ((this.f4811c && c0901g == C0901g.DISC_CACHE) || (this.f4812d && c0901g == C0901g.MEMORY_CACHE))) {
            C0913b.m7247a(c0925a.m7318d(), this.f4809a);
        }
    }
}
