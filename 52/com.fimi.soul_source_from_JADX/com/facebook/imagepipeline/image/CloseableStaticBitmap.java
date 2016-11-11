package com.facebook.imagepipeline.image;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.imageutils.BitmapUtil;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class CloseableStaticBitmap extends CloseableBitmap {
    private volatile Bitmap mBitmap;
    @GuardedBy("this")
    private CloseableReference<Bitmap> mBitmapReference;
    private final QualityInfo mQualityInfo;
    private final int mRotationAngle;

    public CloseableStaticBitmap(Bitmap bitmap, ResourceReleaser<Bitmap> resourceReleaser, QualityInfo qualityInfo, int i) {
        this.mBitmap = (Bitmap) Preconditions.checkNotNull(bitmap);
        this.mBitmapReference = CloseableReference.of(this.mBitmap, (ResourceReleaser) Preconditions.checkNotNull(resourceReleaser));
        this.mQualityInfo = qualityInfo;
        this.mRotationAngle = i;
    }

    public CloseableStaticBitmap(CloseableReference<Bitmap> closeableReference, QualityInfo qualityInfo, int i) {
        this.mBitmapReference = (CloseableReference) Preconditions.checkNotNull(closeableReference.cloneOrNull());
        this.mBitmap = (Bitmap) this.mBitmapReference.get();
        this.mQualityInfo = qualityInfo;
        this.mRotationAngle = i;
    }

    private synchronized CloseableReference<Bitmap> detachBitmapReference() {
        CloseableReference<Bitmap> closeableReference;
        closeableReference = this.mBitmapReference;
        this.mBitmapReference = null;
        this.mBitmap = null;
        return closeableReference;
    }

    public void close() {
        CloseableReference detachBitmapReference = detachBitmapReference();
        if (detachBitmapReference != null) {
            detachBitmapReference.close();
        }
    }

    public synchronized CloseableReference<Bitmap> convertToBitmapReference() {
        Preconditions.checkNotNull(this.mBitmapReference, "Cannot convert a closed static bitmap");
        return detachBitmapReference();
    }

    public int getHeight() {
        Bitmap bitmap = this.mBitmap;
        return bitmap == null ? 0 : bitmap.getHeight();
    }

    public QualityInfo getQualityInfo() {
        return this.mQualityInfo;
    }

    public int getRotationAngle() {
        return this.mRotationAngle;
    }

    public int getSizeInBytes() {
        return BitmapUtil.getSizeInBytes(this.mBitmap);
    }

    public Bitmap getUnderlyingBitmap() {
        return this.mBitmap;
    }

    public int getWidth() {
        Bitmap bitmap = this.mBitmap;
        return bitmap == null ? 0 : bitmap.getWidth();
    }

    public synchronized boolean isClosed() {
        return this.mBitmapReference == null;
    }
}
