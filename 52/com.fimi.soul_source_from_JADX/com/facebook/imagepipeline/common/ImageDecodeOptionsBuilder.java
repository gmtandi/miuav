package com.facebook.imagepipeline.common;

import android.support.v4.view.ViewCompat;

public class ImageDecodeOptionsBuilder {
    private int mBackgroundColor;
    private boolean mDecodeAllFrames;
    private boolean mDecodePreviewFrame;
    private boolean mForceOldAnimationCode;
    private int mMinDecodeIntervalMs;
    private boolean mUseLastFrameForPreview;

    ImageDecodeOptionsBuilder() {
        this.mMinDecodeIntervalMs = 100;
        this.mBackgroundColor = ViewCompat.MEASURED_SIZE_MASK;
    }

    public ImageDecodeOptions build() {
        return new ImageDecodeOptions(this);
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public boolean getDecodeAllFrames() {
        return this.mDecodeAllFrames;
    }

    public boolean getDecodePreviewFrame() {
        return this.mDecodePreviewFrame;
    }

    public boolean getForceOldAnimationCode() {
        return this.mForceOldAnimationCode;
    }

    public int getMinDecodeIntervalMs() {
        return this.mMinDecodeIntervalMs;
    }

    public boolean getUseLastFrameForPreview() {
        return this.mUseLastFrameForPreview;
    }

    public ImageDecodeOptionsBuilder setBackgroundColor(int i) {
        this.mBackgroundColor = i;
        return this;
    }

    public ImageDecodeOptionsBuilder setDecodeAllFrames(boolean z) {
        this.mDecodeAllFrames = z;
        return this;
    }

    public ImageDecodeOptionsBuilder setDecodePreviewFrame(boolean z) {
        this.mDecodePreviewFrame = z;
        return this;
    }

    public ImageDecodeOptionsBuilder setForceOldAnimationCode(boolean z) {
        this.mForceOldAnimationCode = z;
        return this;
    }

    public ImageDecodeOptionsBuilder setFrom(ImageDecodeOptions imageDecodeOptions) {
        this.mBackgroundColor = imageDecodeOptions.backgroundColor;
        this.mForceOldAnimationCode = imageDecodeOptions.forceOldAnimationCode;
        this.mDecodePreviewFrame = imageDecodeOptions.decodePreviewFrame;
        this.mUseLastFrameForPreview = imageDecodeOptions.useLastFrameForPreview;
        this.mDecodeAllFrames = imageDecodeOptions.decodeAllFrames;
        return this;
    }

    public ImageDecodeOptionsBuilder setMinDecodeIntervalMs(int i) {
        this.mMinDecodeIntervalMs = i;
        return this;
    }

    public ImageDecodeOptionsBuilder setUseLastFrameForPreview(boolean z) {
        this.mUseLastFrameForPreview = z;
        return this;
    }
}
