package com.facebook.imagepipeline.animated.base;

public class AnimatedDrawableFrameInfo {
    public final DisposalMethod disposalMethod;
    public final int frameNumber;
    public final int height;
    public final boolean shouldBlendWithPreviousFrame;
    public final int width;
    public final int xOffset;
    public final int yOffset;

    public enum DisposalMethod {
        DISPOSE_DO_NOT,
        DISPOSE_TO_BACKGROUND,
        DISPOSE_TO_PREVIOUS
    }

    public AnimatedDrawableFrameInfo(int i, int i2, int i3, int i4, int i5, boolean z, DisposalMethod disposalMethod) {
        this.frameNumber = i;
        this.xOffset = i2;
        this.yOffset = i3;
        this.width = i4;
        this.height = i5;
        this.shouldBlendWithPreviousFrame = z;
        this.disposalMethod = disposalMethod;
    }
}
