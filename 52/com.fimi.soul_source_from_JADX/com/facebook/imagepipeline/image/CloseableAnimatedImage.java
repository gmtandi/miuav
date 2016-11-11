package com.facebook.imagepipeline.image;

import com.facebook.imagepipeline.animated.base.AnimatedImage;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;

public class CloseableAnimatedImage extends CloseableImage {
    private AnimatedImageResult mImageResult;

    public CloseableAnimatedImage(AnimatedImageResult animatedImageResult) {
        this.mImageResult = animatedImageResult;
    }

    public void close() {
        synchronized (this) {
            if (this.mImageResult == null) {
                return;
            }
            AnimatedImageResult animatedImageResult = this.mImageResult;
            this.mImageResult = null;
            animatedImageResult.dispose();
        }
    }

    public synchronized int getHeight() {
        return isClosed() ? 0 : this.mImageResult.getImage().getHeight();
    }

    public synchronized AnimatedImage getImage() {
        return isClosed() ? null : this.mImageResult.getImage();
    }

    public synchronized AnimatedImageResult getImageResult() {
        return this.mImageResult;
    }

    public synchronized int getSizeInBytes() {
        return isClosed() ? 0 : this.mImageResult.getImage().getSizeInBytes();
    }

    public synchronized int getWidth() {
        return isClosed() ? 0 : this.mImageResult.getImage().getWidth();
    }

    public synchronized boolean isClosed() {
        return this.mImageResult == null;
    }

    public boolean isStateful() {
        return true;
    }
}
