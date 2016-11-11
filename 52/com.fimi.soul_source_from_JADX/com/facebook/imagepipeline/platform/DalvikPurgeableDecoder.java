package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.common.TooManyBitmapsException;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.BitmapCounter;
import com.facebook.imagepipeline.memory.BitmapCounterProvider;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.nativecode.Bitmaps;

abstract class DalvikPurgeableDecoder implements PlatformDecoder {
    protected static final byte[] EOI;
    private final BitmapCounter mUnpooledBitmapsCounter;

    static {
        EOI = new byte[]{(byte) -1, (byte) -39};
    }

    DalvikPurgeableDecoder() {
        this.mUnpooledBitmapsCounter = BitmapCounterProvider.get();
    }

    protected static boolean endsWithEOI(CloseableReference<PooledByteBuffer> closeableReference, int i) {
        PooledByteBuffer pooledByteBuffer = (PooledByteBuffer) closeableReference.get();
        return i >= 2 && pooledByteBuffer.read(i - 2) == -1 && pooledByteBuffer.read(i - 1) == -39;
    }

    private static Options getBitmapFactoryOptions(int i, Config config) {
        Options options = new Options();
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = i;
        if (VERSION.SDK_INT >= 11) {
            options.inMutable = true;
        }
        return options;
    }

    abstract Bitmap decodeByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> closeableReference, Options options);

    public CloseableReference<Bitmap> decodeFromEncodedImage(EncodedImage encodedImage, Config config) {
        Options bitmapFactoryOptions = getBitmapFactoryOptions(encodedImage.getSampleSize(), config);
        CloseableReference byteBufferRef = encodedImage.getByteBufferRef();
        Preconditions.checkNotNull(byteBufferRef);
        try {
            CloseableReference<Bitmap> pinBitmap = pinBitmap(decodeByteArrayAsPurgeable(byteBufferRef, bitmapFactoryOptions));
            return pinBitmap;
        } finally {
            CloseableReference.closeSafely(byteBufferRef);
        }
    }

    abstract Bitmap decodeJPEGByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> closeableReference, int i, Options options);

    public CloseableReference<Bitmap> decodeJPEGFromEncodedImage(EncodedImage encodedImage, Config config, int i) {
        Options bitmapFactoryOptions = getBitmapFactoryOptions(encodedImage.getSampleSize(), config);
        CloseableReference byteBufferRef = encodedImage.getByteBufferRef();
        Preconditions.checkNotNull(byteBufferRef);
        try {
            CloseableReference<Bitmap> pinBitmap = pinBitmap(decodeJPEGByteArrayAsPurgeable(byteBufferRef, i, bitmapFactoryOptions));
            return pinBitmap;
        } finally {
            CloseableReference.closeSafely(byteBufferRef);
        }
    }

    public CloseableReference<Bitmap> pinBitmap(Bitmap bitmap) {
        try {
            Bitmaps.pinBitmap(bitmap);
            if (this.mUnpooledBitmapsCounter.increase(bitmap)) {
                return CloseableReference.of(bitmap, this.mUnpooledBitmapsCounter.getReleaser());
            }
            bitmap.recycle();
            throw new TooManyBitmapsException();
        } catch (Throwable e) {
            bitmap.recycle();
            throw Throwables.propagate(e);
        }
    }
}
