package com.fimi.soul.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class cc implements AnimatorUpdateListener {
    final /* synthetic */ UpDownSliding f10747a;

    cc(UpDownSliding upDownSliding) {
        this.f10747a = upDownSliding;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f10747a.f10636v.leftMargin = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.f10747a.f10638x.leftMargin = (((Integer) valueAnimator.getAnimatedValue()).intValue() / (this.f10747a.f10621c / this.f10747a.f10622d)) + this.f10747a.f10621c;
        this.f10747a.f10637w.leftMargin = this.f10747a.f10626h[1] - (((Integer) valueAnimator.getAnimatedValue()).intValue() / (this.f10747a.f10621c / this.f10747a.f10622d));
    }
}
