package com.fimi.soul.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class ce implements AnimatorUpdateListener {
    final /* synthetic */ UpDownSliding f10749a;

    ce(UpDownSliding upDownSliding) {
        this.f10749a = upDownSliding;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f10749a.f10636v.leftMargin = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.f10749a.f10638x.leftMargin = this.f10749a.f10626h[1] - (((Integer) valueAnimator.getAnimatedValue()).intValue() - this.f10749a.f10626h[0]);
        this.f10749a.f10637w.leftMargin = (this.f10749a.f10626h[1] - ((Integer) valueAnimator.getAnimatedValue()).intValue()) * (this.f10749a.f10621c / this.f10749a.f10622d);
    }
}
