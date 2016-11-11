package com.facebook.imagepipeline.animated.factory;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.SystemClock;
import com.facebook.common.time.MonotonicClock;
import com.facebook.imagepipeline.animated.base.AnimatedDrawable;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableOptions;
import com.facebook.imagepipeline.animated.base.AnimatedImage;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendProvider;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableCachingBackendImplProvider;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableDiagnosticsImpl;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableDiagnosticsNoop;
import com.facebook.imagepipeline.animated.util.AnimatedDrawableUtil;
import java.util.concurrent.ScheduledExecutorService;

public class AnimatedDrawableFactory {
    private final AnimatedDrawableBackendProvider mAnimatedDrawableBackendProvider;
    private final AnimatedDrawableCachingBackendImplProvider mAnimatedDrawableCachingBackendProvider;
    private final AnimatedDrawableUtil mAnimatedDrawableUtil;
    private final MonotonicClock mMonotonicClock;
    private final Resources mResources;
    private final ScheduledExecutorService mScheduledExecutorServiceForUiThread;

    /* renamed from: com.facebook.imagepipeline.animated.factory.AnimatedDrawableFactory.1 */
    class C09951 implements MonotonicClock {
        C09951() {
        }

        public long now() {
            return SystemClock.uptimeMillis();
        }
    }

    public AnimatedDrawableFactory(AnimatedDrawableBackendProvider animatedDrawableBackendProvider, AnimatedDrawableCachingBackendImplProvider animatedDrawableCachingBackendImplProvider, AnimatedDrawableUtil animatedDrawableUtil, ScheduledExecutorService scheduledExecutorService, Resources resources) {
        this.mAnimatedDrawableBackendProvider = animatedDrawableBackendProvider;
        this.mAnimatedDrawableCachingBackendProvider = animatedDrawableCachingBackendImplProvider;
        this.mAnimatedDrawableUtil = animatedDrawableUtil;
        this.mScheduledExecutorServiceForUiThread = scheduledExecutorService;
        this.mMonotonicClock = new C09951();
        this.mResources = resources;
    }

    private AnimatedDrawable createAnimatedDrawable(AnimatedDrawableOptions animatedDrawableOptions, AnimatedDrawableBackend animatedDrawableBackend) {
        return new AnimatedDrawable(this.mScheduledExecutorServiceForUiThread, this.mAnimatedDrawableCachingBackendProvider.get(animatedDrawableBackend, animatedDrawableOptions), animatedDrawableOptions.enableDebugging ? new AnimatedDrawableDiagnosticsImpl(this.mAnimatedDrawableUtil, this.mResources.getDisplayMetrics()) : AnimatedDrawableDiagnosticsNoop.getInstance(), this.mMonotonicClock);
    }

    public AnimatedDrawable create(AnimatedImageResult animatedImageResult) {
        return create(animatedImageResult, AnimatedDrawableOptions.DEFAULTS);
    }

    public AnimatedDrawable create(AnimatedImageResult animatedImageResult, AnimatedDrawableOptions animatedDrawableOptions) {
        AnimatedImage image = animatedImageResult.getImage();
        return createAnimatedDrawable(animatedDrawableOptions, this.mAnimatedDrawableBackendProvider.get(animatedImageResult, new Rect(0, 0, image.getWidth(), image.getHeight())));
    }
}
