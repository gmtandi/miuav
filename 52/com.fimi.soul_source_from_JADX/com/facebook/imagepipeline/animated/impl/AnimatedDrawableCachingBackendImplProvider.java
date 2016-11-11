package com.facebook.imagepipeline.animated.impl;

import com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableOptions;

public interface AnimatedDrawableCachingBackendImplProvider {
    AnimatedDrawableCachingBackendImpl get(AnimatedDrawableBackend animatedDrawableBackend, AnimatedDrawableOptions animatedDrawableOptions);
}
