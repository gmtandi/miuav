package com.fimi.soul.module.login;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

/* renamed from: com.fimi.soul.module.login.l */
class C1800l implements AnimatorUpdateListener {
    final /* synthetic */ LoginActivity f8856a;

    C1800l(LoginActivity loginActivity) {
        this.f8856a = loginActivity;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f8856a.f8786F.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
