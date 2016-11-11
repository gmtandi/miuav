package com.fimi.soul.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class bo implements AnimatorUpdateListener {
    final /* synthetic */ TranslationView f10734a;

    bo(TranslationView translationView) {
        this.f10734a = translationView;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f10734a.f10505f = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.f10734a.f10508i.setAlpha(this.f10734a.f10505f / 2);
        this.f10734a.f10506g.setAlpha(255 - this.f10734a.f10505f);
    }
}
