package com.facebook.imagepipeline.animated.base;

import android.graphics.drawable.Animatable;
import com.p017b.p018a.as;
import com.p017b.p018a.az;

public interface AnimatableDrawable extends Animatable {
    az createAnimatorUpdateListener();

    as createValueAnimator();

    as createValueAnimator(int i);
}
