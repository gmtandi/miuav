package com.fimi.soul.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class bn implements AnimatorUpdateListener {
    final /* synthetic */ TranslationView f10733a;

    bn(TranslationView translationView) {
        this.f10733a = translationView;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f10733a.f10504e = ((Float) valueAnimator.getAnimatedValue()).floatValue();
    }
}
