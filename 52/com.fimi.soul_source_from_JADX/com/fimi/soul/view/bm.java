package com.fimi.soul.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class bm implements AnimatorUpdateListener {
    final /* synthetic */ TranslationView f10732a;

    bm(TranslationView translationView) {
        this.f10732a = translationView;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f10732a.f10503d = ((Float) valueAnimator.getAnimatedValue()).floatValue();
    }
}
