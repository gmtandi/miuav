package com.facebook.imagepipeline.webp;

import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.imagepipeline.animated.base.AnimatedImageFrame;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class WebPFrame implements AnimatedImageFrame {
    @DoNotStrip
    private long mNativeContext;

    @DoNotStrip
    WebPFrame(long j) {
        this.mNativeContext = j;
    }

    private native void nativeDispose();

    private native void nativeFinalize();

    private native int nativeGetDurationMs();

    private native int nativeGetHeight();

    private native int nativeGetWidth();

    private native int nativeGetXOffset();

    private native int nativeGetYOffset();

    private native void nativeRenderFrame(int i, int i2, Bitmap bitmap);

    private native boolean nativeShouldBlendWithPreviousFrame();

    private native boolean nativeShouldDisposeToBackgroundColor();

    public void dispose() {
        nativeDispose();
    }

    protected void finalize() {
        nativeFinalize();
    }

    public int getDurationMs() {
        return nativeGetDurationMs();
    }

    public int getHeight() {
        return nativeGetHeight();
    }

    public int getWidth() {
        return nativeGetWidth();
    }

    public int getXOffset() {
        return nativeGetXOffset();
    }

    public int getYOffset() {
        return nativeGetYOffset();
    }

    public void renderFrame(int i, int i2, Bitmap bitmap) {
        nativeRenderFrame(i, i2, bitmap);
    }

    public boolean shouldBlendWithPreviousFrame() {
        return nativeShouldBlendWithPreviousFrame();
    }

    public boolean shouldDisposeToBackgroundColor() {
        return nativeShouldDisposeToBackgroundColor();
    }
}
