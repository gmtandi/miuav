package com.fimi.soul.module.droneFragment;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class aa implements AnimatorUpdateListener {
    final /* synthetic */ SectorProgressBar f8163a;

    aa(SectorProgressBar sectorProgressBar) {
        this.f8163a = sectorProgressBar;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f8163a.f7983q = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.f8163a.invalidate();
    }
}
