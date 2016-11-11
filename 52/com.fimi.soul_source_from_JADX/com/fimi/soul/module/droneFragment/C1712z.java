package com.fimi.soul.module.droneFragment;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

/* renamed from: com.fimi.soul.module.droneFragment.z */
class C1712z implements AnimatorUpdateListener {
    final /* synthetic */ SectorProgressBar f8353a;

    C1712z(SectorProgressBar sectorProgressBar) {
        this.f8353a = sectorProgressBar;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f8353a.f7983q = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.f8353a.invalidate();
    }
}
