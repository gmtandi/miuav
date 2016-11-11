package com.facebook.imagepipeline.animated.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.facebook.common.references.CloseableReference;

public abstract class DelegatingAnimatedDrawableBackend implements AnimatedDrawableBackend {
    private final AnimatedDrawableBackend mAnimatedDrawableBackend;

    public DelegatingAnimatedDrawableBackend(AnimatedDrawableBackend animatedDrawableBackend) {
        this.mAnimatedDrawableBackend = animatedDrawableBackend;
    }

    public void dropCaches() {
        this.mAnimatedDrawableBackend.dropCaches();
    }

    public AnimatedImageResult getAnimatedImageResult() {
        return this.mAnimatedDrawableBackend.getAnimatedImageResult();
    }

    protected AnimatedDrawableBackend getDelegate() {
        return this.mAnimatedDrawableBackend;
    }

    public int getDurationMs() {
        return this.mAnimatedDrawableBackend.getDurationMs();
    }

    public int getDurationMsForFrame(int i) {
        return this.mAnimatedDrawableBackend.getDurationMsForFrame(i);
    }

    public int getFrameCount() {
        return this.mAnimatedDrawableBackend.getFrameCount();
    }

    public int getFrameForPreview() {
        return this.mAnimatedDrawableBackend.getFrameForPreview();
    }

    public int getFrameForTimestampMs(int i) {
        return this.mAnimatedDrawableBackend.getFrameForTimestampMs(i);
    }

    public AnimatedDrawableFrameInfo getFrameInfo(int i) {
        return this.mAnimatedDrawableBackend.getFrameInfo(i);
    }

    public int getHeight() {
        return this.mAnimatedDrawableBackend.getHeight();
    }

    public int getLoopCount() {
        return this.mAnimatedDrawableBackend.getLoopCount();
    }

    public int getMemoryUsage() {
        return this.mAnimatedDrawableBackend.getMemoryUsage();
    }

    public CloseableReference<Bitmap> getPreDecodedFrame(int i) {
        return this.mAnimatedDrawableBackend.getPreDecodedFrame(i);
    }

    public int getRenderedHeight() {
        return this.mAnimatedDrawableBackend.getRenderedHeight();
    }

    public int getRenderedWidth() {
        return this.mAnimatedDrawableBackend.getRenderedWidth();
    }

    public int getTimestampMsForFrame(int i) {
        return this.mAnimatedDrawableBackend.getTimestampMsForFrame(i);
    }

    public int getWidth() {
        return this.mAnimatedDrawableBackend.getWidth();
    }

    public boolean hasPreDecodedFrame(int i) {
        return this.mAnimatedDrawableBackend.hasPreDecodedFrame(i);
    }

    public void renderFrame(int i, Canvas canvas) {
        this.mAnimatedDrawableBackend.renderFrame(i, canvas);
    }
}
