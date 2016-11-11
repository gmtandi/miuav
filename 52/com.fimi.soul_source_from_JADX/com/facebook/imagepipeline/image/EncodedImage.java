package com.facebook.imagepipeline.image;

import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.SharedReference;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.memory.PooledByteBufferInputStream;
import com.facebook.imageutils.BitmapUtil;
import com.facebook.imageutils.JfifUtil;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class EncodedImage implements Closeable {
    public static final int DEFAULT_SAMPLE_SIZE = 1;
    public static final int UNKNOWN_HEIGHT = -1;
    public static final int UNKNOWN_ROTATION_ANGLE = -1;
    public static final int UNKNOWN_STREAM_SIZE = -1;
    public static final int UNKNOWN_WIDTH = -1;
    private int mHeight;
    private ImageFormat mImageFormat;
    @Nullable
    private final Supplier<FileInputStream> mInputStreamSupplier;
    @Nullable
    private final CloseableReference<PooledByteBuffer> mPooledByteBufferRef;
    private int mRotationAngle;
    private int mSampleSize;
    private int mStreamSize;
    private int mWidth;

    public EncodedImage(Supplier<FileInputStream> supplier) {
        this.mImageFormat = ImageFormat.UNKNOWN;
        this.mRotationAngle = UNKNOWN_WIDTH;
        this.mWidth = UNKNOWN_WIDTH;
        this.mHeight = UNKNOWN_WIDTH;
        this.mSampleSize = DEFAULT_SAMPLE_SIZE;
        this.mStreamSize = UNKNOWN_WIDTH;
        Preconditions.checkNotNull(supplier);
        this.mPooledByteBufferRef = null;
        this.mInputStreamSupplier = supplier;
    }

    public EncodedImage(Supplier<FileInputStream> supplier, int i) {
        this((Supplier) supplier);
        this.mStreamSize = i;
    }

    public EncodedImage(CloseableReference<PooledByteBuffer> closeableReference) {
        this.mImageFormat = ImageFormat.UNKNOWN;
        this.mRotationAngle = UNKNOWN_WIDTH;
        this.mWidth = UNKNOWN_WIDTH;
        this.mHeight = UNKNOWN_WIDTH;
        this.mSampleSize = DEFAULT_SAMPLE_SIZE;
        this.mStreamSize = UNKNOWN_WIDTH;
        Preconditions.checkArgument(CloseableReference.isValid(closeableReference));
        this.mPooledByteBufferRef = closeableReference.clone();
        this.mInputStreamSupplier = null;
    }

    public static EncodedImage cloneOrNull(EncodedImage encodedImage) {
        return encodedImage != null ? encodedImage.cloneOrNull() : null;
    }

    public static void closeSafely(@Nullable EncodedImage encodedImage) {
        if (encodedImage != null) {
            encodedImage.close();
        }
    }

    public static boolean isMetaDataAvailable(EncodedImage encodedImage) {
        return encodedImage.mRotationAngle >= 0 && encodedImage.mWidth >= 0 && encodedImage.mHeight >= 0;
    }

    public static boolean isValid(@Nullable EncodedImage encodedImage) {
        return encodedImage != null && encodedImage.isValid();
    }

    public EncodedImage cloneOrNull() {
        EncodedImage encodedImage;
        if (this.mInputStreamSupplier != null) {
            encodedImage = new EncodedImage(this.mInputStreamSupplier, this.mStreamSize);
        } else {
            CloseableReference cloneOrNull = CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
            if (cloneOrNull == null) {
                encodedImage = null;
            } else {
                try {
                    encodedImage = new EncodedImage(cloneOrNull);
                } catch (Throwable th) {
                    CloseableReference.closeSafely(cloneOrNull);
                }
            }
            CloseableReference.closeSafely(cloneOrNull);
        }
        if (encodedImage != null) {
            encodedImage.copyMetaDataFrom(this);
        }
        return encodedImage;
    }

    public void close() {
        CloseableReference.closeSafely(this.mPooledByteBufferRef);
    }

    public void copyMetaDataFrom(EncodedImage encodedImage) {
        this.mImageFormat = encodedImage.getImageFormat();
        this.mWidth = encodedImage.getWidth();
        this.mHeight = encodedImage.getHeight();
        this.mRotationAngle = encodedImage.getRotationAngle();
        this.mSampleSize = encodedImage.getSampleSize();
        this.mStreamSize = encodedImage.getSize();
    }

    public CloseableReference<PooledByteBuffer> getByteBufferRef() {
        return CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
    }

    public int getHeight() {
        return this.mHeight;
    }

    public ImageFormat getImageFormat() {
        return this.mImageFormat;
    }

    public InputStream getInputStream() {
        if (this.mInputStreamSupplier != null) {
            return (InputStream) this.mInputStreamSupplier.get();
        }
        CloseableReference cloneOrNull = CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
        if (cloneOrNull == null) {
            return null;
        }
        try {
            InputStream pooledByteBufferInputStream = new PooledByteBufferInputStream((PooledByteBuffer) cloneOrNull.get());
            return pooledByteBufferInputStream;
        } finally {
            CloseableReference.closeSafely(cloneOrNull);
        }
    }

    public int getRotationAngle() {
        return this.mRotationAngle;
    }

    public int getSampleSize() {
        return this.mSampleSize;
    }

    public int getSize() {
        return (this.mPooledByteBufferRef == null || this.mPooledByteBufferRef.get() == null) ? this.mStreamSize : ((PooledByteBuffer) this.mPooledByteBufferRef.get()).size();
    }

    @VisibleForTesting
    public synchronized SharedReference<PooledByteBuffer> getUnderlyingReferenceTestOnly() {
        return this.mPooledByteBufferRef != null ? this.mPooledByteBufferRef.getUnderlyingReferenceTestOnly() : null;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public boolean isCompleteAt(int i) {
        if (this.mImageFormat != ImageFormat.JPEG || this.mInputStreamSupplier != null) {
            return true;
        }
        Preconditions.checkNotNull(this.mPooledByteBufferRef);
        PooledByteBuffer pooledByteBuffer = (PooledByteBuffer) this.mPooledByteBufferRef.get();
        boolean z = pooledByteBuffer.read(i + -2) == UNKNOWN_WIDTH && pooledByteBuffer.read(i + UNKNOWN_WIDTH) == -39;
        return z;
    }

    public synchronized boolean isValid() {
        boolean z;
        z = CloseableReference.isValid(this.mPooledByteBufferRef) || this.mInputStreamSupplier != null;
        return z;
    }

    public void parseMetaData() {
        ImageFormat imageFormat_WrapIOException = ImageFormatChecker.getImageFormat_WrapIOException(getInputStream());
        this.mImageFormat = imageFormat_WrapIOException;
        if (!ImageFormat.isWebpFormat(imageFormat_WrapIOException)) {
            Pair decodeDimensions = BitmapUtil.decodeDimensions(getInputStream());
            if (decodeDimensions != null) {
                this.mWidth = ((Integer) decodeDimensions.first).intValue();
                this.mHeight = ((Integer) decodeDimensions.second).intValue();
                if (imageFormat_WrapIOException != ImageFormat.JPEG) {
                    this.mRotationAngle = 0;
                } else if (this.mRotationAngle == UNKNOWN_WIDTH) {
                    this.mRotationAngle = JfifUtil.getAutoRotateAngleFromOrientation(JfifUtil.getOrientation(getInputStream()));
                }
            }
        }
    }

    public void setHeight(int i) {
        this.mHeight = i;
    }

    public void setImageFormat(ImageFormat imageFormat) {
        this.mImageFormat = imageFormat;
    }

    public void setRotationAngle(int i) {
        this.mRotationAngle = i;
    }

    public void setSampleSize(int i) {
        this.mSampleSize = i;
    }

    public void setStreamSize(int i) {
        this.mStreamSize = i;
    }

    public void setWidth(int i) {
        this.mWidth = i;
    }
}
