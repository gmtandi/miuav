package com.fimi.kernel.p084e;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/* renamed from: com.fimi.kernel.e.d */
class C1165d implements AnimationListener {
    final /* synthetic */ View f5313a;
    final /* synthetic */ float f5314b;
    final /* synthetic */ C1163b f5315c;

    C1165d(C1163b c1163b, View view, float f) {
        this.f5315c = c1163b;
        this.f5313a = view;
        this.f5314b = f;
    }

    public void onAnimationEnd(Animation animation) {
        this.f5313a.postDelayed(new C1166e(this), 2000);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
