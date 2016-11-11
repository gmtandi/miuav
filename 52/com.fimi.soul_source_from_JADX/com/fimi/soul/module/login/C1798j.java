package com.fimi.soul.module.login;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

/* renamed from: com.fimi.soul.module.login.j */
class C1798j implements AnimatorUpdateListener {
    final /* synthetic */ LoginActivity f8854a;

    C1798j(LoginActivity loginActivity) {
        this.f8854a = loginActivity;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f8854a.f8803j.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
