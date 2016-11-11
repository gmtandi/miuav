package com.facebook.drawee.backends.pipeline;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawable.base.DrawableWithCaches;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.drawable.OrientedDrawable;
import com.facebook.imagepipeline.animated.factory.AnimatedDrawableFactory;
import com.facebook.imagepipeline.image.CloseableAnimatedImage;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImageInfo;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class PipelineDraweeController extends AbstractDraweeController<CloseableReference<CloseableImage>, ImageInfo> {
    private static final Class<?> TAG;
    private final AnimatedDrawableFactory mAnimatedDrawableFactory;
    private Supplier<DataSource<CloseableReference<CloseableImage>>> mDataSourceSupplier;
    private final Resources mResources;

    static {
        TAG = PipelineDraweeController.class;
    }

    public PipelineDraweeController(Resources resources, DeferredReleaser deferredReleaser, AnimatedDrawableFactory animatedDrawableFactory, Executor executor, Supplier<DataSource<CloseableReference<CloseableImage>>> supplier, String str, Object obj) {
        super(deferredReleaser, executor, str, obj);
        this.mResources = resources;
        this.mAnimatedDrawableFactory = animatedDrawableFactory;
        init(supplier);
    }

    private void init(Supplier<DataSource<CloseableReference<CloseableImage>>> supplier) {
        this.mDataSourceSupplier = supplier;
    }

    protected Drawable createDrawable(CloseableReference<CloseableImage> closeableReference) {
        Preconditions.checkState(CloseableReference.isValid(closeableReference));
        CloseableImage closeableImage = (CloseableImage) closeableReference.get();
        if (closeableImage instanceof CloseableStaticBitmap) {
            CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) closeableImage;
            Drawable bitmapDrawable = new BitmapDrawable(this.mResources, closeableStaticBitmap.getUnderlyingBitmap());
            return (closeableStaticBitmap.getRotationAngle() == 0 || closeableStaticBitmap.getRotationAngle() == -1) ? bitmapDrawable : new OrientedDrawable(bitmapDrawable, closeableStaticBitmap.getRotationAngle());
        } else if (closeableImage instanceof CloseableAnimatedImage) {
            return this.mAnimatedDrawableFactory.create(((CloseableAnimatedImage) closeableImage).getImageResult());
        } else {
            throw new UnsupportedOperationException("Unrecognized image class: " + closeableImage);
        }
    }

    protected DataSource<CloseableReference<CloseableImage>> getDataSource() {
        if (FLog.isLoggable(2)) {
            FLog.m7618v(TAG, "controller %x: getDataSource", Integer.valueOf(System.identityHashCode(this)));
        }
        return (DataSource) this.mDataSourceSupplier.get();
    }

    protected int getImageHash(@Nullable CloseableReference<CloseableImage> closeableReference) {
        return closeableReference != null ? closeableReference.getValueHash() : 0;
    }

    protected ImageInfo getImageInfo(CloseableReference<CloseableImage> closeableReference) {
        Preconditions.checkState(CloseableReference.isValid(closeableReference));
        return (ImageInfo) closeableReference.get();
    }

    protected Resources getResources() {
        return this.mResources;
    }

    public void initialize(Supplier<DataSource<CloseableReference<CloseableImage>>> supplier, String str, Object obj) {
        super.initialize(str, obj);
        init(supplier);
    }

    protected void releaseDrawable(@Nullable Drawable drawable) {
        if (drawable instanceof DrawableWithCaches) {
            ((DrawableWithCaches) drawable).dropCaches();
        }
    }

    protected void releaseImage(@Nullable CloseableReference<CloseableImage> closeableReference) {
        CloseableReference.closeSafely((CloseableReference) closeableReference);
    }

    public String toString() {
        return Objects.toStringHelper((Object) this).add("super", super.toString()).add("dataSourceSupplier", this.mDataSourceSupplier).toString();
    }
}
