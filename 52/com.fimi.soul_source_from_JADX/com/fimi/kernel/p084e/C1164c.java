package com.fimi.kernel.p084e;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/* renamed from: com.fimi.kernel.e.c */
class C1164c implements AnimationListener {
    final /* synthetic */ View f5310a;
    final /* synthetic */ float f5311b;
    final /* synthetic */ C1163b f5312c;

    C1164c(C1163b c1163b, View view, float f) {
        this.f5312c = c1163b;
        this.f5310a = view;
        this.f5311b = f;
    }

    public void onAnimationEnd(Animation animation) {
        this.f5312c.m8096e(this.f5310a, this.f5311b);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
