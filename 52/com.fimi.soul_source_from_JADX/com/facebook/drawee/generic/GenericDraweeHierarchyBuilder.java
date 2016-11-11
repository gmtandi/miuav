package com.facebook.drawee.generic;

import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import com.facebook.common.internal.Preconditions;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;

public class GenericDraweeHierarchyBuilder {
    public static final ScaleType DEFAULT_ACTUAL_IMAGE_SCALE_TYPE;
    public static final int DEFAULT_FADE_DURATION = 300;
    public static final ScaleType DEFAULT_SCALE_TYPE;
    private ColorFilter mActualImageColorFilter;
    private PointF mActualImageFocusPoint;
    private Matrix mActualImageMatrix;
    private ScaleType mActualImageScaleType;
    private List<Drawable> mBackgrounds;
    private int mFadeDuration;
    private Drawable mFailureImage;
    private ScaleType mFailureImageScaleType;
    private List<Drawable> mOverlays;
    private Drawable mPlaceholderImage;
    @Nullable
    private ScaleType mPlaceholderImageScaleType;
    private Drawable mPressedStateOverlay;
    private Drawable mProgressBarImage;
    private ScaleType mProgressBarImageScaleType;
    private Resources mResources;
    private Drawable mRetryImage;
    private ScaleType mRetryImageScaleType;
    private RoundingParams mRoundingParams;

    static {
        DEFAULT_SCALE_TYPE = ScaleType.CENTER_INSIDE;
        DEFAULT_ACTUAL_IMAGE_SCALE_TYPE = ScaleType.CENTER_CROP;
    }

    public GenericDraweeHierarchyBuilder(Resources resources) {
        this.mResources = resources;
        init();
    }

    private void init() {
        this.mFadeDuration = DEFAULT_FADE_DURATION;
        this.mPlaceholderImage = null;
        this.mPlaceholderImageScaleType = null;
        this.mRetryImage = null;
        this.mRetryImageScaleType = null;
        this.mFailureImage = null;
        this.mFailureImageScaleType = null;
        this.mProgressBarImage = null;
        this.mProgressBarImageScaleType = null;
        this.mActualImageScaleType = DEFAULT_ACTUAL_IMAGE_SCALE_TYPE;
        this.mActualImageMatrix = null;
        this.mActualImageFocusPoint = null;
        this.mBackgrounds = null;
        this.mOverlays = null;
        this.mPressedStateOverlay = null;
        this.mRoundingParams = null;
        this.mActualImageColorFilter = null;
    }

    public static GenericDraweeHierarchyBuilder newInstance(Resources resources) {
        return new GenericDraweeHierarchyBuilder(resources);
    }

    private void validate() {
        if (this.mOverlays != null) {
            for (Drawable checkNotNull : this.mOverlays) {
                Preconditions.checkNotNull(checkNotNull);
            }
        }
        if (this.mBackgrounds != null) {
            for (Drawable checkNotNull2 : this.mBackgrounds) {
                Preconditions.checkNotNull(checkNotNull2);
            }
        }
    }

    public GenericDraweeHierarchy build() {
        validate();
        return new GenericDraweeHierarchy(this);
    }

    public ColorFilter getActualImageColorFilter() {
        return this.mActualImageColorFilter;
    }

    public PointF getActualImageFocusPoint() {
        return this.mActualImageFocusPoint;
    }

    public Matrix getActualImageMatrix() {
        return this.mActualImageMatrix;
    }

    public ScaleType getActualImageScaleType() {
        return this.mActualImageScaleType;
    }

    public List<Drawable> getBackgrounds() {
        return this.mBackgrounds;
    }

    public int getFadeDuration() {
        return this.mFadeDuration;
    }

    public Drawable getFailureImage() {
        return this.mFailureImage;
    }

    public ScaleType getFailureImageScaleType() {
        return this.mFailureImageScaleType;
    }

    public List<Drawable> getOverlays() {
        return this.mOverlays;
    }

    public Drawable getPlaceholderImage() {
        return this.mPlaceholderImage;
    }

    @Nullable
    public ScaleType getPlaceholderImageScaleType() {
        return this.mPlaceholderImageScaleType;
    }

    public Drawable getPressedStateOverlay() {
        return this.mPressedStateOverlay;
    }

    public Drawable getProgressBarImage() {
        return this.mProgressBarImage;
    }

    public ScaleType getProgressBarImageScaleType() {
        return this.mProgressBarImageScaleType;
    }

    public Resources getResources() {
        return this.mResources;
    }

    public Drawable getRetryImage() {
        return this.mRetryImage;
    }

    public ScaleType getRetryImageScaleType() {
        return this.mRetryImageScaleType;
    }

    public RoundingParams getRoundingParams() {
        return this.mRoundingParams;
    }

    public GenericDraweeHierarchyBuilder reset() {
        init();
        return this;
    }

    public GenericDraweeHierarchyBuilder setActualImageColorFilter(ColorFilter colorFilter) {
        this.mActualImageColorFilter = colorFilter;
        return this;
    }

    public GenericDraweeHierarchyBuilder setActualImageFocusPoint(PointF pointF) {
        this.mActualImageFocusPoint = pointF;
        return this;
    }

    @Deprecated
    public GenericDraweeHierarchyBuilder setActualImageMatrix(Matrix matrix) {
        this.mActualImageMatrix = matrix;
        this.mActualImageScaleType = null;
        return this;
    }

    public GenericDraweeHierarchyBuilder setActualImageScaleType(ScaleType scaleType) {
        this.mActualImageScaleType = scaleType;
        this.mActualImageMatrix = null;
        return this;
    }

    public GenericDraweeHierarchyBuilder setBackground(Drawable drawable) {
        this.mBackgrounds = Arrays.asList(new Drawable[]{drawable});
        return this;
    }

    public GenericDraweeHierarchyBuilder setBackgrounds(List<Drawable> list) {
        this.mBackgrounds = list;
        return this;
    }

    public GenericDraweeHierarchyBuilder setFadeDuration(int i) {
        this.mFadeDuration = i;
        return this;
    }

    public GenericDraweeHierarchyBuilder setFailureImage(Drawable drawable) {
        return setFailureImage(drawable, DEFAULT_SCALE_TYPE);
    }

    public GenericDraweeHierarchyBuilder setFailureImage(Drawable drawable, ScaleType scaleType) {
        this.mFailureImage = drawable;
        this.mFailureImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setOverlay(Drawable drawable) {
        this.mOverlays = Arrays.asList(new Drawable[]{drawable});
        return this;
    }

    public GenericDraweeHierarchyBuilder setOverlays(List<Drawable> list) {
        this.mOverlays = list;
        return this;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImage(Drawable drawable) {
        return setPlaceholderImage(drawable, DEFAULT_SCALE_TYPE);
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImage(Drawable drawable, @Nullable ScaleType scaleType) {
        this.mPlaceholderImage = drawable;
        this.mPlaceholderImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setPressedStateOverlay(Drawable drawable) {
        Drawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, drawable);
        this.mPressedStateOverlay = stateListDrawable;
        return this;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImage(Drawable drawable) {
        return setProgressBarImage(drawable, DEFAULT_SCALE_TYPE);
    }

    public GenericDraweeHierarchyBuilder setProgressBarImage(Drawable drawable, ScaleType scaleType) {
        this.mProgressBarImage = drawable;
        this.mProgressBarImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setRetryImage(Drawable drawable) {
        return setRetryImage(drawable, DEFAULT_SCALE_TYPE);
    }

    public GenericDraweeHierarchyBuilder setRetryImage(Drawable drawable, ScaleType scaleType) {
        this.mRetryImage = drawable;
        this.mRetryImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setRoundingParams(RoundingParams roundingParams) {
        this.mRoundingParams = roundingParams;
        return this;
    }
}
