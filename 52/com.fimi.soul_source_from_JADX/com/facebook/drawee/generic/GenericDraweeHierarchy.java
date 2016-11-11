package com.facebook.drawee.generic;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import com.facebook.common.internal.Preconditions;
import com.facebook.drawee.drawable.FadeDrawable;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.drawee.drawable.MatrixDrawable;
import com.facebook.drawee.drawable.Rounded;
import com.facebook.drawee.drawable.RoundedBitmapDrawable;
import com.facebook.drawee.drawable.RoundedColorDrawable;
import com.facebook.drawee.drawable.RoundedCornersDrawable;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.drawable.SettableDrawable;
import com.facebook.drawee.drawable.VisibilityAwareDrawable;
import com.facebook.drawee.drawable.VisibilityCallback;
import com.facebook.drawee.generic.RoundingParams.RoundingMethod;
import com.facebook.drawee.interfaces.SettableDraweeHierarchy;
import javax.annotation.Nullable;

public class GenericDraweeHierarchy implements SettableDraweeHierarchy {
    private final int mActualImageIndex;
    private final SettableDrawable mActualImageSettableDrawable;
    private final int mControllerOverlayIndex;
    private final Drawable mEmptyActualImageDrawable;
    private final Drawable mEmptyControllerOverlayDrawable;
    private Drawable mEmptyPlaceholderDrawable;
    private final FadeDrawable mFadeDrawable;
    private final int mFailureImageIndex;
    private final int mPlaceholderImageIndex;
    private final int mProgressBarImageIndex;
    private final Resources mResources;
    private final int mRetryImageIndex;
    private RoundingParams mRoundingParams;
    private final RootDrawable mTopLevelDrawable;

    public class RootDrawable extends ForwardingDrawable implements VisibilityAwareDrawable {
        @Nullable
        private VisibilityCallback mVisibilityCallback;

        public RootDrawable(Drawable drawable) {
            super(drawable);
        }

        @SuppressLint({"WrongCall"})
        public void draw(Canvas canvas) {
            if (isVisible()) {
                if (this.mVisibilityCallback != null) {
                    this.mVisibilityCallback.onDraw();
                }
                super.draw(canvas);
            }
        }

        public int getIntrinsicHeight() {
            return -1;
        }

        public int getIntrinsicWidth() {
            return -1;
        }

        public void setVisibilityCallback(@Nullable VisibilityCallback visibilityCallback) {
            this.mVisibilityCallback = visibilityCallback;
        }

        public boolean setVisible(boolean z, boolean z2) {
            if (this.mVisibilityCallback != null) {
                this.mVisibilityCallback.onVisibilityChange(z);
            }
            return super.setVisible(z, z2);
        }
    }

    GenericDraweeHierarchy(GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder) {
        this.mEmptyActualImageDrawable = new ColorDrawable(0);
        this.mEmptyControllerOverlayDrawable = new ColorDrawable(0);
        this.mResources = genericDraweeHierarchyBuilder.getResources();
        this.mRoundingParams = genericDraweeHierarchyBuilder.getRoundingParams();
        int size = genericDraweeHierarchyBuilder.getBackgrounds() != null ? genericDraweeHierarchyBuilder.getBackgrounds().size() : 0;
        int i = 0 + size;
        Drawable placeholderImage = genericDraweeHierarchyBuilder.getPlaceholderImage();
        if (placeholderImage == null) {
            placeholderImage = getEmptyPlaceholderDrawable();
        }
        Drawable maybeWrapWithScaleType = maybeWrapWithScaleType(maybeApplyRoundingBitmapOnly(this.mRoundingParams, this.mResources, placeholderImage), genericDraweeHierarchyBuilder.getPlaceholderImageScaleType());
        int i2 = i + 1;
        this.mPlaceholderImageIndex = i;
        this.mActualImageSettableDrawable = new SettableDrawable(this.mEmptyActualImageDrawable);
        Drawable maybeWrapWithMatrix = maybeWrapWithMatrix(maybeWrapWithScaleType(this.mActualImageSettableDrawable, genericDraweeHierarchyBuilder.getActualImageScaleType(), genericDraweeHierarchyBuilder.getActualImageFocusPoint()), genericDraweeHierarchyBuilder.getActualImageMatrix());
        maybeWrapWithMatrix.setColorFilter(genericDraweeHierarchyBuilder.getActualImageColorFilter());
        i = i2 + 1;
        this.mActualImageIndex = i2;
        placeholderImage = genericDraweeHierarchyBuilder.getProgressBarImage();
        int i3 = i + 1;
        this.mProgressBarImageIndex = i;
        if (placeholderImage != null) {
            placeholderImage = maybeWrapWithScaleType(placeholderImage, genericDraweeHierarchyBuilder.getProgressBarImageScaleType());
        }
        Drawable retryImage = genericDraweeHierarchyBuilder.getRetryImage();
        int i4 = i3 + 1;
        this.mRetryImageIndex = i3;
        if (retryImage != null) {
            retryImage = maybeWrapWithScaleType(retryImage, genericDraweeHierarchyBuilder.getRetryImageScaleType());
        }
        Drawable failureImage = genericDraweeHierarchyBuilder.getFailureImage();
        int i5 = i4 + 1;
        this.mFailureImageIndex = i4;
        if (failureImage != null) {
            failureImage = maybeWrapWithScaleType(failureImage, genericDraweeHierarchyBuilder.getFailureImageScaleType());
        }
        int size2 = (genericDraweeHierarchyBuilder.getOverlays() != null ? genericDraweeHierarchyBuilder.getOverlays().size() : 0) + (genericDraweeHierarchyBuilder.getPressedStateOverlay() != null ? 1 : 0);
        i4 = i5 + size2;
        int i6 = i4 + 1;
        this.mControllerOverlayIndex = i4;
        Drawable[] drawableArr = new Drawable[i6];
        if (size > 0) {
            i4 = 0;
            for (Drawable maybeApplyRoundingBitmapOnly : genericDraweeHierarchyBuilder.getBackgrounds()) {
                i6 = i4 + 1;
                drawableArr[i4 + 0] = maybeApplyRoundingBitmapOnly(this.mRoundingParams, this.mResources, maybeApplyRoundingBitmapOnly);
                i4 = i6;
            }
        }
        drawableArr[this.mPlaceholderImageIndex] = maybeWrapWithScaleType;
        drawableArr[this.mActualImageIndex] = maybeWrapWithMatrix;
        drawableArr[this.mProgressBarImageIndex] = placeholderImage;
        drawableArr[this.mRetryImageIndex] = retryImage;
        drawableArr[this.mFailureImageIndex] = failureImage;
        if (size2 > 0) {
            if (genericDraweeHierarchyBuilder.getOverlays() != null) {
                i2 = 0;
                for (Drawable maybeApplyRoundingBitmapOnly2 : genericDraweeHierarchyBuilder.getOverlays()) {
                    i = i2 + 1;
                    drawableArr[i2 + i5] = maybeApplyRoundingBitmapOnly2;
                    i2 = i;
                }
            } else {
                i2 = 0;
            }
            if (genericDraweeHierarchyBuilder.getPressedStateOverlay() != null) {
                size = i2 + 1;
                drawableArr[i5 + i2] = genericDraweeHierarchyBuilder.getPressedStateOverlay();
            }
        }
        if (this.mControllerOverlayIndex >= 0) {
            drawableArr[this.mControllerOverlayIndex] = this.mEmptyControllerOverlayDrawable;
        }
        this.mFadeDrawable = new FadeDrawable(drawableArr);
        this.mFadeDrawable.setTransitionDuration(genericDraweeHierarchyBuilder.getFadeDuration());
        this.mTopLevelDrawable = new RootDrawable(maybeWrapWithRoundedOverlayColor(this.mRoundingParams, this.mFadeDrawable));
        this.mTopLevelDrawable.mutate();
        resetFade();
    }

    private static Drawable applyRounding(@Nullable RoundingParams roundingParams, Resources resources, Drawable drawable) {
        Object fromBitmapDrawable;
        if (drawable instanceof BitmapDrawable) {
            fromBitmapDrawable = RoundedBitmapDrawable.fromBitmapDrawable(resources, (BitmapDrawable) drawable);
            applyRoundingParams(fromBitmapDrawable, roundingParams);
            return fromBitmapDrawable;
        } else if (!(drawable instanceof ColorDrawable) || VERSION.SDK_INT < 11) {
            return drawable;
        } else {
            fromBitmapDrawable = RoundedColorDrawable.fromColorDrawable((ColorDrawable) drawable);
            applyRoundingParams(fromBitmapDrawable, roundingParams);
            return fromBitmapDrawable;
        }
    }

    private static void applyRoundingParams(Rounded rounded, RoundingParams roundingParams) {
        rounded.setCircle(roundingParams.getRoundAsCircle());
        rounded.setRadii(roundingParams.getCornersRadii());
        rounded.setBorder(roundingParams.getBorderColor(), roundingParams.getBorderWidth());
    }

    private void fadeInLayer(int i) {
        if (i >= 0) {
            this.mFadeDrawable.fadeInLayer(i);
        }
    }

    private void fadeOutBranches() {
        fadeOutLayer(this.mPlaceholderImageIndex);
        fadeOutLayer(this.mActualImageIndex);
        fadeOutLayer(this.mProgressBarImageIndex);
        fadeOutLayer(this.mRetryImageIndex);
        fadeOutLayer(this.mFailureImageIndex);
    }

    private void fadeOutLayer(int i) {
        if (i >= 0) {
            this.mFadeDrawable.fadeOutLayer(i);
        }
    }

    @Nullable
    private ScaleTypeDrawable findLayerScaleTypeDrawable(int i) {
        Drawable drawable = this.mFadeDrawable.getDrawable(i);
        if (drawable instanceof MatrixDrawable) {
            drawable = drawable.getCurrent();
        }
        return drawable instanceof ScaleTypeDrawable ? (ScaleTypeDrawable) drawable : null;
    }

    private Drawable getEmptyPlaceholderDrawable() {
        if (this.mEmptyPlaceholderDrawable == null) {
            this.mEmptyPlaceholderDrawable = new ColorDrawable(0);
        }
        return this.mEmptyPlaceholderDrawable;
    }

    private Drawable getLayerChildDrawable(int i) {
        return getLayerDrawable(i, false);
    }

    private Drawable getLayerDrawable(int i, boolean z) {
        Drawable drawable = this.mFadeDrawable;
        Drawable drawable2 = this.mFadeDrawable.getDrawable(i);
        if (drawable2 instanceof MatrixDrawable) {
            drawable = drawable2.getCurrent();
        } else {
            Drawable drawable3 = drawable2;
            drawable2 = drawable;
            drawable = drawable3;
        }
        if (drawable instanceof ScaleTypeDrawable) {
            drawable2 = drawable.getCurrent();
        } else {
            drawable3 = drawable;
            drawable = drawable2;
            drawable2 = drawable3;
        }
        return z ? drawable : drawable2;
    }

    private static Drawable maybeApplyRoundingBitmapOnly(@Nullable RoundingParams roundingParams, Resources resources, Drawable drawable) {
        if (roundingParams == null || roundingParams.getRoundingMethod() != RoundingMethod.BITMAP_ONLY) {
            return drawable;
        }
        if ((drawable instanceof BitmapDrawable) || (drawable instanceof ColorDrawable)) {
            return applyRounding(roundingParams, resources, drawable);
        }
        Drawable current = drawable.getCurrent();
        Drawable drawable2 = drawable;
        while (current != null && drawable2 != current) {
            if ((drawable2 instanceof ForwardingDrawable) && ((current instanceof BitmapDrawable) || (current instanceof ColorDrawable))) {
                ((ForwardingDrawable) drawable2).setCurrent(applyRounding(roundingParams, resources, current));
            }
            drawable2 = current;
            current = current.getCurrent();
        }
        return drawable;
    }

    private static Drawable maybeWrapWithMatrix(Drawable drawable, @Nullable Matrix matrix) {
        Preconditions.checkNotNull(drawable);
        return matrix == null ? drawable : new MatrixDrawable(drawable, matrix);
    }

    private static Drawable maybeWrapWithRoundedOverlayColor(@Nullable RoundingParams roundingParams, Drawable drawable) {
        if (roundingParams == null || roundingParams.getRoundingMethod() != RoundingMethod.OVERLAY_COLOR) {
            return drawable;
        }
        Drawable roundedCornersDrawable = new RoundedCornersDrawable(drawable);
        applyRoundingParams(roundedCornersDrawable, roundingParams);
        roundedCornersDrawable.setOverlayColor(roundingParams.getOverlayColor());
        return roundedCornersDrawable;
    }

    private static Drawable maybeWrapWithScaleType(Drawable drawable, @Nullable ScaleType scaleType) {
        return maybeWrapWithScaleType(drawable, scaleType, null);
    }

    private static Drawable maybeWrapWithScaleType(Drawable drawable, @Nullable ScaleType scaleType, @Nullable PointF pointF) {
        Preconditions.checkNotNull(drawable);
        if (scaleType == null) {
            return drawable;
        }
        Drawable scaleTypeDrawable = new ScaleTypeDrawable(drawable, scaleType);
        if (pointF != null) {
            scaleTypeDrawable.setFocusPoint(pointF);
        }
        return scaleTypeDrawable;
    }

    private void resetActualImages() {
        if (this.mActualImageSettableDrawable != null) {
            this.mActualImageSettableDrawable.setDrawable(this.mEmptyActualImageDrawable);
        }
    }

    private void resetFade() {
        if (this.mFadeDrawable != null) {
            this.mFadeDrawable.beginBatchMode();
            this.mFadeDrawable.fadeInAllLayers();
            fadeOutBranches();
            fadeInLayer(this.mPlaceholderImageIndex);
            this.mFadeDrawable.finishTransitionImmediately();
            this.mFadeDrawable.endBatchMode();
        }
    }

    private static void resetRoundedDrawable(Rounded rounded) {
        rounded.setCircle(false);
        rounded.setRadius(0.0f);
        rounded.setBorder(0, 0.0f);
    }

    private void setDrawableAndScaleType(@Nullable Drawable drawable, @Nullable ScaleType scaleType, int i) {
        if (drawable == null) {
            this.mFadeDrawable.setDrawable(i, null);
            return;
        }
        Drawable maybeApplyRoundingBitmapOnly = maybeApplyRoundingBitmapOnly(this.mRoundingParams, this.mResources, drawable);
        if (scaleType != null) {
            ScaleTypeDrawable findLayerScaleTypeDrawable = findLayerScaleTypeDrawable(i);
            if (findLayerScaleTypeDrawable != null) {
                findLayerScaleTypeDrawable.setScaleType(scaleType);
            } else {
                maybeApplyRoundingBitmapOnly = maybeWrapWithScaleType(maybeApplyRoundingBitmapOnly, scaleType);
            }
        }
        setLayerChildDrawable(i, maybeApplyRoundingBitmapOnly);
    }

    private void setLayerChildDrawable(int i, Drawable drawable) {
        Drawable layerDrawable = getLayerDrawable(i, true);
        if (layerDrawable == this.mFadeDrawable) {
            this.mFadeDrawable.setDrawable(i, drawable);
        } else {
            ((ForwardingDrawable) layerDrawable).setCurrent(drawable);
        }
    }

    private void setProgress(float f) {
        Drawable layerChildDrawable = getLayerChildDrawable(this.mProgressBarImageIndex);
        if (layerChildDrawable != null) {
            if (f >= 0.999f) {
                if (layerChildDrawable instanceof Animatable) {
                    ((Animatable) layerChildDrawable).stop();
                }
                fadeOutLayer(this.mProgressBarImageIndex);
            } else {
                if (layerChildDrawable instanceof Animatable) {
                    ((Animatable) layerChildDrawable).start();
                }
                fadeInLayer(this.mProgressBarImageIndex);
            }
            layerChildDrawable.setLevel(Math.round(10000.0f * f));
        }
    }

    private void updateBitmapOnlyRounding() {
        int i;
        Drawable layerChildDrawable;
        if (this.mRoundingParams == null || this.mRoundingParams.getRoundingMethod() != RoundingMethod.BITMAP_ONLY) {
            for (i = 0; i < this.mFadeDrawable.getNumberOfLayers(); i++) {
                layerChildDrawable = getLayerChildDrawable(i);
                if (layerChildDrawable instanceof Rounded) {
                    resetRoundedDrawable((Rounded) layerChildDrawable);
                }
            }
            return;
        }
        for (i = 0; i < this.mFadeDrawable.getNumberOfLayers(); i++) {
            layerChildDrawable = getLayerChildDrawable(i);
            if (layerChildDrawable instanceof Rounded) {
                applyRoundingParams((Rounded) layerChildDrawable, this.mRoundingParams);
            } else if (layerChildDrawable != null) {
                setLayerChildDrawable(i, this.mEmptyActualImageDrawable);
                setLayerChildDrawable(i, maybeApplyRoundingBitmapOnly(this.mRoundingParams, this.mResources, layerChildDrawable));
            }
        }
    }

    private void updateOverlayColorRounding() {
        Drawable current = this.mTopLevelDrawable.getCurrent();
        if (this.mRoundingParams == null || this.mRoundingParams.getRoundingMethod() != RoundingMethod.OVERLAY_COLOR) {
            if (current instanceof RoundedCornersDrawable) {
                this.mTopLevelDrawable.setCurrent(((RoundedCornersDrawable) current).setCurrent(this.mEmptyActualImageDrawable));
            }
        } else if (current instanceof RoundedCornersDrawable) {
            RoundedCornersDrawable roundedCornersDrawable = (RoundedCornersDrawable) current;
            applyRoundingParams(roundedCornersDrawable, this.mRoundingParams);
            roundedCornersDrawable.setOverlayColor(this.mRoundingParams.getOverlayColor());
        } else {
            this.mTopLevelDrawable.setCurrent(maybeWrapWithRoundedOverlayColor(this.mRoundingParams, this.mTopLevelDrawable.setCurrent(this.mEmptyActualImageDrawable)));
        }
    }

    public void getActualImageBounds(RectF rectF) {
        this.mActualImageSettableDrawable.getTransformedBounds(rectF);
    }

    public RoundingParams getRoundingParams() {
        return this.mRoundingParams;
    }

    public Drawable getTopLevelDrawable() {
        return this.mTopLevelDrawable;
    }

    public void reset() {
        resetActualImages();
        resetFade();
    }

    public void setActualImageColorFilter(ColorFilter colorFilter) {
        this.mFadeDrawable.getDrawable(this.mActualImageIndex).setColorFilter(colorFilter);
    }

    public void setActualImageFocusPoint(PointF pointF) {
        Preconditions.checkNotNull(pointF);
        ScaleTypeDrawable findLayerScaleTypeDrawable = findLayerScaleTypeDrawable(this.mActualImageIndex);
        if (findLayerScaleTypeDrawable == null) {
            throw new UnsupportedOperationException("ScaleTypeDrawable not found!");
        }
        findLayerScaleTypeDrawable.setFocusPoint(pointF);
    }

    public void setActualImageScaleType(ScaleType scaleType) {
        Preconditions.checkNotNull(scaleType);
        ScaleTypeDrawable findLayerScaleTypeDrawable = findLayerScaleTypeDrawable(this.mActualImageIndex);
        if (findLayerScaleTypeDrawable == null) {
            throw new UnsupportedOperationException("ScaleTypeDrawable not found!");
        }
        findLayerScaleTypeDrawable.setScaleType(scaleType);
    }

    public void setControllerOverlay(@Nullable Drawable drawable) {
        if (drawable == null) {
            drawable = this.mEmptyControllerOverlayDrawable;
        }
        this.mFadeDrawable.setDrawable(this.mControllerOverlayIndex, drawable);
    }

    public void setFadeDuration(int i) {
        this.mFadeDrawable.setTransitionDuration(i);
    }

    public void setFailure(Throwable th) {
        this.mFadeDrawable.beginBatchMode();
        fadeOutBranches();
        if (this.mFadeDrawable.getDrawable(this.mFailureImageIndex) != null) {
            fadeInLayer(this.mFailureImageIndex);
        } else {
            fadeInLayer(this.mPlaceholderImageIndex);
        }
        this.mFadeDrawable.endBatchMode();
    }

    public void setFailureImage(Drawable drawable) {
        setFailureImage(drawable, null);
    }

    public void setFailureImage(@Nullable Drawable drawable, @Nullable ScaleType scaleType) {
        setDrawableAndScaleType(drawable, scaleType, this.mFailureImageIndex);
    }

    public void setImage(Drawable drawable, float f, boolean z) {
        Drawable maybeApplyRoundingBitmapOnly = maybeApplyRoundingBitmapOnly(this.mRoundingParams, this.mResources, drawable);
        maybeApplyRoundingBitmapOnly.mutate();
        this.mActualImageSettableDrawable.setDrawable(maybeApplyRoundingBitmapOnly);
        this.mFadeDrawable.beginBatchMode();
        fadeOutBranches();
        fadeInLayer(this.mActualImageIndex);
        setProgress(f);
        if (z) {
            this.mFadeDrawable.finishTransitionImmediately();
        }
        this.mFadeDrawable.endBatchMode();
    }

    public void setPlaceholderImage(int i) {
        setPlaceholderImage(this.mResources.getDrawable(i));
    }

    public void setPlaceholderImage(Drawable drawable) {
        setPlaceholderImage(drawable, null);
    }

    public void setPlaceholderImage(@Nullable Drawable drawable, @Nullable ScaleType scaleType) {
        if (drawable == null) {
            drawable = getEmptyPlaceholderDrawable();
        }
        setDrawableAndScaleType(drawable, scaleType, this.mPlaceholderImageIndex);
    }

    public void setPlaceholderImageFocusPoint(PointF pointF) {
        Preconditions.checkNotNull(pointF);
        ScaleTypeDrawable findLayerScaleTypeDrawable = findLayerScaleTypeDrawable(this.mPlaceholderImageIndex);
        if (findLayerScaleTypeDrawable == null) {
            throw new UnsupportedOperationException("ScaleTypeDrawable not found!");
        }
        findLayerScaleTypeDrawable.setFocusPoint(pointF);
    }

    public void setProgress(float f, boolean z) {
        this.mFadeDrawable.beginBatchMode();
        setProgress(f);
        if (z) {
            this.mFadeDrawable.finishTransitionImmediately();
        }
        this.mFadeDrawable.endBatchMode();
    }

    public void setProgressBarImage(Drawable drawable) {
        setProgressBarImage(drawable, null);
    }

    public void setProgressBarImage(@Nullable Drawable drawable, @Nullable ScaleType scaleType) {
        setDrawableAndScaleType(drawable, scaleType, this.mProgressBarImageIndex);
    }

    public void setRetry(Throwable th) {
        this.mFadeDrawable.beginBatchMode();
        fadeOutBranches();
        if (this.mFadeDrawable.getDrawable(this.mRetryImageIndex) != null) {
            fadeInLayer(this.mRetryImageIndex);
        } else {
            fadeInLayer(this.mPlaceholderImageIndex);
        }
        this.mFadeDrawable.endBatchMode();
    }

    public void setRetryImage(Drawable drawable) {
        setRetryImage(drawable, null);
    }

    public void setRetryImage(@Nullable Drawable drawable, @Nullable ScaleType scaleType) {
        setDrawableAndScaleType(drawable, scaleType, this.mRetryImageIndex);
    }

    public void setRoundingParams(RoundingParams roundingParams) {
        this.mRoundingParams = roundingParams;
        updateOverlayColorRounding();
        updateBitmapOnlyRounding();
    }
}
