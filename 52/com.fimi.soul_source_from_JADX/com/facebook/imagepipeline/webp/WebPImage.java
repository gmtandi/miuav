package com.facebook.imagepipeline.webp;

import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.soloader.SoLoaderShim;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo.DisposalMethod;
import com.facebook.imagepipeline.animated.base.AnimatedImage;
import java.nio.ByteBuffer;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class WebPImage implements AnimatedImage {
    private static volatile boolean sInitialized;
    @DoNotStrip
    private long mNativeContext;

    @DoNotStrip
    WebPImage(long j) {
        this.mNativeContext = j;
    }

    public static WebPImage create(long j, int i) {
        ensure();
        Preconditions.checkArgument(j != 0);
        return nativeCreateFromNativeMemory(j, i);
    }

    public static WebPImage create(byte[] bArr) {
        ensure();
        Preconditions.checkNotNull(bArr);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(bArr.length);
        allocateDirect.put(bArr);
        allocateDirect.rewind();
        return nativeCreateFromDirectByteBuffer(allocateDirect);
    }

    private static synchronized void ensure() {
        synchronized (WebPImage.class) {
            if (!sInitialized) {
                sInitialized = true;
                SoLoaderShim.loadLibrary("webp");
                SoLoaderShim.loadLibrary("webpimage");
            }
        }
    }

    private static native WebPImage nativeCreateFromDirectByteBuffer(ByteBuffer byteBuffer);

    private static native WebPImage nativeCreateFromNativeMemory(long j, int i);

    private native void nativeDispose();

    private native void nativeFinalize();

    private native int nativeGetDuration();

    private native WebPFrame nativeGetFrame(int i);

    private native int nativeGetFrameCount();

    private native int[] nativeGetFrameDurations();

    private native int nativeGetHeight();

    private native int nativeGetLoopCount();

    private native int nativeGetSizeInBytes();

    private native int nativeGetWidth();

    public void dispose() {
        nativeDispose();
    }

    public boolean doesRenderSupportScaling() {
        return true;
    }

    protected void finalize() {
        nativeFinalize();
    }

    public int getDuration() {
        return nativeGetDuration();
    }

    public WebPFrame getFrame(int i) {
        return nativeGetFrame(i);
    }

    public int getFrameCount() {
        return nativeGetFrameCount();
    }

    public int[] getFrameDurations() {
        return nativeGetFrameDurations();
    }

    public AnimatedDrawableFrameInfo getFrameInfo(int i) {
        WebPFrame frame = getFrame(i);
        try {
            AnimatedDrawableFrameInfo animatedDrawableFrameInfo = new AnimatedDrawableFrameInfo(i, frame.getXOffset(), frame.getYOffset(), frame.getWidth(), frame.getHeight(), frame.shouldBlendWithPreviousFrame(), frame.shouldDisposeToBackgroundColor() ? DisposalMethod.DISPOSE_TO_BACKGROUND : DisposalMethod.DISPOSE_DO_NOT);
            return animatedDrawableFrameInfo;
        } finally {
            frame.dispose();
        }
    }

    public int getHeight() {
        return nativeGetHeight();
    }

    public int getLoopCount() {
        return nativeGetLoopCount();
    }

    public int getSizeInBytes() {
        return nativeGetSizeInBytes();
    }

    public int getWidth() {
        return nativeGetWidth();
    }
}
