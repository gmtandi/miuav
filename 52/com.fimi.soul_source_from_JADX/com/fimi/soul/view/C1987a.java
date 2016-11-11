package com.fimi.soul.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

/* renamed from: com.fimi.soul.view.a */
class C1987a implements AnimatorUpdateListener {
    final /* synthetic */ BreathLeapView f10652a;

    C1987a(BreathLeapView breathLeapView) {
        this.f10652a = breathLeapView;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f10652a.f10289p = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.f10652a.invalidate();
    }
}
