package com.facebook.imagepipeline.animated.factory;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.base.AnimatedImage;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendProvider;
import com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor;
import com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.Callback;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.gif.GifImage;
import com.facebook.imagepipeline.image.CloseableAnimatedImage;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.webp.WebPImage;
import java.util.ArrayList;
import java.util.List;

public class AnimatedImageFactory {
    private final AnimatedDrawableBackendProvider mAnimatedDrawableBackendProvider;
    private final PlatformBitmapFactory mBitmapFactory;

    /* renamed from: com.facebook.imagepipeline.animated.factory.AnimatedImageFactory.1 */
    class C09961 implements Callback {
        C09961() {
        }

        public CloseableReference<Bitmap> getCachedBitmap(int i) {
            return null;
        }

        public void onIntermediateResult(int i, Bitmap bitmap) {
        }
    }

    /* renamed from: com.facebook.imagepipeline.animated.factory.AnimatedImageFactory.2 */
    class C09972 implements Callback {
        final /* synthetic */ List val$bitmaps;

        C09972(List list) {
            this.val$bitmaps = list;
        }

        public CloseableReference<Bitmap> getCachedBitmap(int i) {
            return CloseableReference.cloneOrNull((CloseableReference) this.val$bitmaps.get(i));
        }

        public void onIntermediateResult(int i, Bitmap bitmap) {
        }
    }

    public AnimatedImageFactory(AnimatedDrawableBackendProvider animatedDrawableBackendProvider, PlatformBitmapFactory platformBitmapFactory) {
        this.mAnimatedDrawableBackendProvider = animatedDrawableBackendProvider;
        this.mBitmapFactory = platformBitmapFactory;
    }

    @SuppressLint({"NewApi"})
    private CloseableReference<Bitmap> createBitmap(int i, int i2, Config config) {
        CloseableReference<Bitmap> createBitmap = this.mBitmapFactory.createBitmap(i, i2, config);
        ((Bitmap) createBitmap.get()).eraseColor(0);
        if (VERSION.SDK_INT >= 12) {
            ((Bitmap) createBitmap.get()).setHasAlpha(true);
        }
        return createBitmap;
    }

    private CloseableReference<Bitmap> createPreviewBitmap(AnimatedImage animatedImage, Config config, int i) {
        CloseableReference<Bitmap> createBitmap = createBitmap(animatedImage.getWidth(), animatedImage.getHeight(), config);
        new AnimatedImageCompositor(this.mAnimatedDrawableBackendProvider.get(AnimatedImageResult.forAnimatedImage(animatedImage), null), new C09961()).renderFrame(i, (Bitmap) createBitmap.get());
        return createBitmap;
    }

    private List<CloseableReference<Bitmap>> decodeAllFrames(AnimatedImage animatedImage, Config config) {
        List<CloseableReference<Bitmap>> arrayList = new ArrayList();
        AnimatedDrawableBackend animatedDrawableBackend = this.mAnimatedDrawableBackendProvider.get(AnimatedImageResult.forAnimatedImage(animatedImage), null);
        AnimatedImageCompositor animatedImageCompositor = new AnimatedImageCompositor(animatedDrawableBackend, new C09972(arrayList));
        for (int i = 0; i < animatedDrawableBackend.getFrameCount(); i++) {
            CloseableReference createBitmap = createBitmap(animatedDrawableBackend.getWidth(), animatedDrawableBackend.getHeight(), config);
            animatedImageCompositor.renderFrame(i, (Bitmap) createBitmap.get());
            arrayList.add(createBitmap);
        }
        return arrayList;
    }

    private CloseableAnimatedImage getCloseableImage(ImageDecodeOptions imageDecodeOptions, AnimatedImage animatedImage, Config config) {
        Throwable th;
        CloseableReference closeableReference = null;
        Iterable decodeAllFrames;
        try {
            int frameCount = imageDecodeOptions.useLastFrameForPreview ? animatedImage.getFrameCount() - 1 : 0;
            if (imageDecodeOptions.decodeAllFrames) {
                decodeAllFrames = decodeAllFrames(animatedImage, config);
                try {
                    closeableReference = CloseableReference.cloneOrNull((CloseableReference) decodeAllFrames.get(frameCount));
                } catch (Throwable th2) {
                    th = th2;
                    CloseableReference.closeSafely(null);
                    CloseableReference.closeSafely(decodeAllFrames);
                    throw th;
                }
            }
            decodeAllFrames = null;
            if (imageDecodeOptions.decodePreviewFrame && r1 == null) {
                closeableReference = createPreviewBitmap(animatedImage, config, frameCount);
            }
            CloseableAnimatedImage closeableAnimatedImage = new CloseableAnimatedImage(AnimatedImageResult.newBuilder(animatedImage).setPreviewBitmap(closeableReference).setFrameForPreview(frameCount).setDecodedFrames(decodeAllFrames).build());
            CloseableReference.closeSafely(closeableReference);
            CloseableReference.closeSafely(decodeAllFrames);
            return closeableAnimatedImage;
        } catch (Throwable th3) {
            th = th3;
            decodeAllFrames = null;
            CloseableReference.closeSafely(null);
            CloseableReference.closeSafely(decodeAllFrames);
            throw th;
        }
    }

    public CloseableImage decodeGif(EncodedImage encodedImage, ImageDecodeOptions imageDecodeOptions, Config config) {
        CloseableReference byteBufferRef = encodedImage.getByteBufferRef();
        Preconditions.checkNotNull(byteBufferRef);
        try {
            Preconditions.checkState(!imageDecodeOptions.forceOldAnimationCode);
            PooledByteBuffer pooledByteBuffer = (PooledByteBuffer) byteBufferRef.get();
            CloseableImage closeableImage = getCloseableImage(imageDecodeOptions, GifImage.create(pooledByteBuffer.getNativePtr(), pooledByteBuffer.size()), config);
            return closeableImage;
        } finally {
            CloseableReference.closeSafely(byteBufferRef);
        }
    }

    public CloseableImage decodeWebP(EncodedImage encodedImage, ImageDecodeOptions imageDecodeOptions, Config config) {
        CloseableReference byteBufferRef = encodedImage.getByteBufferRef();
        Preconditions.checkNotNull(byteBufferRef);
        try {
            Preconditions.checkArgument(!imageDecodeOptions.forceOldAnimationCode);
            PooledByteBuffer pooledByteBuffer = (PooledByteBuffer) byteBufferRef.get();
            CloseableImage closeableImage = getCloseableImage(imageDecodeOptions, WebPImage.create(pooledByteBuffer.getNativePtr(), pooledByteBuffer.size()), config);
            return closeableImage;
        } finally {
            CloseableReference.closeSafely(byteBufferRef);
        }
    }
}
