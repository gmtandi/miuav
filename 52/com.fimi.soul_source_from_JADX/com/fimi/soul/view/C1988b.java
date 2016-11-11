package com.fimi.soul.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Paint;

/* renamed from: com.fimi.soul.view.b */
class C1988b implements AnimatorUpdateListener {
    final /* synthetic */ BreathLeapView f10720a;

    C1988b(BreathLeapView breathLeapView) {
        this.f10720a = breathLeapView;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i = 50;
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.f10720a.f10280g.setAlpha(intValue + -155 > 50 ? intValue - 155 : 50);
        this.f10720a.f10281h.setAlpha(intValue + -105 > 50 ? intValue - 100 : 50);
        this.f10720a.f10282i.setAlpha(intValue + -55 > 50 ? intValue - 50 : 50);
        Paint d = this.f10720a.f10283j;
        if (intValue > 50) {
            i = intValue;
        }
        d.setAlpha(i);
        this.f10720a.invalidate();
    }
}
