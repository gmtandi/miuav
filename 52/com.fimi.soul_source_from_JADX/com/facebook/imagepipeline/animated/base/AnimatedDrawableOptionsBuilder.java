package com.facebook.imagepipeline.animated.base;

public class AnimatedDrawableOptionsBuilder {
    private boolean mAllowPrefetching;
    private boolean mEnableDebugging;
    private boolean mForceKeepAllFramesInMemory;
    private int mMaximumBytes;

    public AnimatedDrawableOptionsBuilder() {
        this.mAllowPrefetching = true;
        this.mMaximumBytes = -1;
    }

    public AnimatedDrawableOptions build() {
        return new AnimatedDrawableOptions(this);
    }

    public boolean getAllowPrefetching() {
        return this.mAllowPrefetching;
    }

    public boolean getEnableDebugging() {
        return this.mEnableDebugging;
    }

    public boolean getForceKeepAllFramesInMemory() {
        return this.mForceKeepAllFramesInMemory;
    }

    public int getMaximumBytes() {
        return this.mMaximumBytes;
    }

    public AnimatedDrawableOptionsBuilder setAllowPrefetching(boolean z) {
        this.mAllowPrefetching = z;
        return this;
    }

    public AnimatedDrawableOptionsBuilder setEnableDebugging(boolean z) {
        this.mEnableDebugging = z;
        return this;
    }

    public AnimatedDrawableOptionsBuilder setForceKeepAllFramesInMemory(boolean z) {
        this.mForceKeepAllFramesInMemory = z;
        return this;
    }

    public AnimatedDrawableOptionsBuilder setMaximumBytes(int i) {
        this.mMaximumBytes = i;
        return this;
    }
}
