package com.fimi.soul.module.droneFragment;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

/* renamed from: com.fimi.soul.module.droneFragment.y */
class C1711y implements AnimatorUpdateListener {
    final /* synthetic */ SectorProgressBar f8352a;

    C1711y(SectorProgressBar sectorProgressBar) {
        this.f8352a = sectorProgressBar;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f8352a.f7983q = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.f8352a.postInvalidate();
    }
}
