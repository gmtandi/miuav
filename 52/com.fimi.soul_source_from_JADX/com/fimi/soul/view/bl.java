package com.fimi.soul.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class bl implements AnimatorUpdateListener {
    final /* synthetic */ TranslationView f10731a;

    bl(TranslationView translationView) {
        this.f10731a = translationView;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f10731a.f10502c = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.f10731a.invalidate();
    }
}
