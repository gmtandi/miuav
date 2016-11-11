package com.facebook.imagepipeline.gif;

import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.soloader.SoLoaderShim;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo.DisposalMethod;
import com.facebook.imagepipeline.animated.base.AnimatedImage;
import java.nio.ByteBuffer;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class GifImage implements AnimatedImage {
    private static volatile boolean sInitialized;
    @DoNotStrip
    private long mNativeContext;

    @DoNotStrip
    GifImage(long j) {
        this.mNativeContext = j;
    }

    public static GifImage create(long j, int i) {
        ensure();
        Preconditions.checkArgument(j != 0);
        return nativeCreateFromNativeMemory(j, i);
    }

    public static GifImage create(byte[] bArr) {
        ensure();
        Preconditions.checkNotNull(bArr);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(bArr.length);
        allocateDirect.put(bArr);
        allocateDirect.rewind();
        return nativeCreateFromDirectByteBuffer(allocateDirect);
    }

    private static synchronized void ensure() {
        synchronized (GifImage.class) {
            if (!sInitialized) {
                sInitialized = true;
                SoLoaderShim.loadLibrary("gifimage");
            }
        }
    }

    private static DisposalMethod fromGifDisposalMethod(int i) {
        return i == 0 ? DisposalMethod.DISPOSE_DO_NOT : i == 1 ? DisposalMethod.DISPOSE_DO_NOT : i == 2 ? DisposalMethod.DISPOSE_TO_BACKGROUND : i == 3 ? DisposalMethod.DISPOSE_TO_PREVIOUS : DisposalMethod.DISPOSE_DO_NOT;
    }

    private static native GifImage nativeCreateFromDirectByteBuffer(ByteBuffer byteBuffer);

    private static native GifImage nativeCreateFromNativeMemory(long j, int i);

    private native void nativeDispose();

    private native void nativeFinalize();

    private native int nativeGetDuration();

    private native GifFrame nativeGetFrame(int i);

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
        return false;
    }

    protected void finalize() {
        nativeFinalize();
    }

    public int getDuration() {
        return nativeGetDuration();
    }

    public GifFrame getFrame(int i) {
        return nativeGetFrame(i);
    }

    public int getFrameCount() {
        return nativeGetFrameCount();
    }

    public int[] getFrameDurations() {
        return nativeGetFrameDurations();
    }

    public AnimatedDrawableFrameInfo getFrameInfo(int i) {
        GifFrame frame = getFrame(i);
        try {
            AnimatedDrawableFrameInfo animatedDrawableFrameInfo = new AnimatedDrawableFrameInfo(i, frame.getXOffset(), frame.getYOffset(), frame.getWidth(), frame.getHeight(), true, fromGifDisposalMethod(frame.getDisposalMode()));
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
