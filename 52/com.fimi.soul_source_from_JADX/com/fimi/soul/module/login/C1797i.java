package com.fimi.soul.module.login;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

/* renamed from: com.fimi.soul.module.login.i */
class C1797i implements AnimatorUpdateListener {
    final /* synthetic */ LoginActivity f8853a;

    C1797i(LoginActivity loginActivity) {
        this.f8853a = loginActivity;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f8853a.f8805n.setCurrentCount(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
