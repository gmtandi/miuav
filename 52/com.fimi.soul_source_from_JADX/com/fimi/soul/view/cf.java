package com.fimi.soul.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class cf implements AnimatorUpdateListener {
    final /* synthetic */ UpDownSliding f10750a;

    cf(UpDownSliding upDownSliding) {
        this.f10750a = upDownSliding;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f10750a.f10636v.topMargin = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.f10750a.f10638x.topMargin = ((Integer) valueAnimator.getAnimatedValue()).intValue() + this.f10750a.f10619a;
        this.f10750a.f10637w.topMargin = ((Integer) valueAnimator.getAnimatedValue()).intValue() + (this.f10750a.f10619a * 2);
        this.f10750a.f10628j[0].setLayoutParams(this.f10750a.f10636v);
        this.f10750a.f10628j[1].setLayoutParams(this.f10750a.f10638x);
        this.f10750a.f10628j[2].setLayoutParams(this.f10750a.f10637w);
    }
}
