package com.facebook.imagepipeline.common;

import javax.annotation.concurrent.Immutable;

@Immutable
public class ImageDecodeOptions {
    private static final ImageDecodeOptions DEFAULTS;
    public final int backgroundColor;
    public final boolean decodeAllFrames;
    public final boolean decodePreviewFrame;
    public final boolean forceOldAnimationCode;
    public final int minDecodeIntervalMs;
    public final boolean useLastFrameForPreview;

    static {
        DEFAULTS = newBuilder().build();
    }

    ImageDecodeOptions(ImageDecodeOptionsBuilder imageDecodeOptionsBuilder) {
        this.minDecodeIntervalMs = imageDecodeOptionsBuilder.getMinDecodeIntervalMs();
        this.backgroundColor = imageDecodeOptionsBuilder.getBackgroundColor();
        this.forceOldAnimationCode = imageDecodeOptionsBuilder.getForceOldAnimationCode();
        this.decodePreviewFrame = imageDecodeOptionsBuilder.getDecodePreviewFrame();
        this.useLastFrameForPreview = imageDecodeOptionsBuilder.getUseLastFrameForPreview();
        this.decodeAllFrames = imageDecodeOptionsBuilder.getDecodeAllFrames();
    }

    public static ImageDecodeOptions defaults() {
        return DEFAULTS;
    }

    public static ImageDecodeOptionsBuilder newBuilder() {
        return new ImageDecodeOptionsBuilder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ImageDecodeOptions imageDecodeOptions = (ImageDecodeOptions) obj;
        return this.backgroundColor != imageDecodeOptions.backgroundColor ? false : this.forceOldAnimationCode != imageDecodeOptions.forceOldAnimationCode ? false : this.decodePreviewFrame != imageDecodeOptions.decodePreviewFrame ? false : this.useLastFrameForPreview != imageDecodeOptions.useLastFrameForPreview ? false : this.decodeAllFrames == imageDecodeOptions.decodeAllFrames;
    }

    public int hashCode() {
        return (this.forceOldAnimationCode ? 1 : 0) + (this.backgroundColor * 31);
    }
}
