package com.facebook.drawee.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.facebook.drawee.C0980R;
import com.facebook.drawee.drawable.AutoRotateDrawable;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.AspectRatioMeasure.Spec;
import com.fimi.soul.module.setting.newhand.ae;
import javax.annotation.Nullable;

public class GenericDraweeView extends DraweeView<GenericDraweeHierarchy> {
    private float mAspectRatio;
    private final Spec mMeasureSpec;

    public GenericDraweeView(Context context) {
        super(context);
        this.mAspectRatio = 0.0f;
        this.mMeasureSpec = new Spec();
        inflateHierarchy(context, null);
    }

    public GenericDraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAspectRatio = 0.0f;
        this.mMeasureSpec = new Spec();
        inflateHierarchy(context, attributeSet);
    }

    public GenericDraweeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAspectRatio = 0.0f;
        this.mMeasureSpec = new Spec();
        inflateHierarchy(context, attributeSet);
    }

    @TargetApi(21)
    public GenericDraweeView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mAspectRatio = 0.0f;
        this.mMeasureSpec = new Spec();
        inflateHierarchy(context, attributeSet);
    }

    public GenericDraweeView(Context context, GenericDraweeHierarchy genericDraweeHierarchy) {
        super(context);
        this.mAspectRatio = 0.0f;
        this.mMeasureSpec = new Spec();
        setHierarchy(genericDraweeHierarchy);
    }

    private static ScaleType getScaleTypeFromXml(TypedArray typedArray, int i, ScaleType scaleType) {
        int i2 = typedArray.getInt(i, -1);
        return i2 < 0 ? scaleType : ScaleType.values()[i2];
    }

    private void inflateHierarchy(Context context, @Nullable AttributeSet attributeSet) {
        Resources resources = context.getResources();
        int i = ae.f9482j;
        int i2 = 0;
        ScaleType scaleType = GenericDraweeHierarchyBuilder.DEFAULT_SCALE_TYPE;
        int i3 = 0;
        ScaleType scaleType2 = GenericDraweeHierarchyBuilder.DEFAULT_SCALE_TYPE;
        int i4 = 0;
        ScaleType scaleType3 = GenericDraweeHierarchyBuilder.DEFAULT_SCALE_TYPE;
        int i5 = 0;
        ScaleType scaleType4 = GenericDraweeHierarchyBuilder.DEFAULT_SCALE_TYPE;
        ScaleType scaleType5 = GenericDraweeHierarchyBuilder.DEFAULT_ACTUAL_IMAGE_SCALE_TYPE;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        boolean z = false;
        int i9 = 0;
        boolean z2 = true;
        boolean z3 = true;
        boolean z4 = true;
        boolean z5 = true;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0980R.styleable.GenericDraweeView);
            try {
                i = obtainStyledAttributes.getInt(C0980R.styleable.GenericDraweeView_fadeDuration, ae.f9482j);
                this.mAspectRatio = obtainStyledAttributes.getFloat(C0980R.styleable.GenericDraweeView_viewAspectRatio, this.mAspectRatio);
                i2 = obtainStyledAttributes.getResourceId(C0980R.styleable.GenericDraweeView_placeholderImage, 0);
                scaleType = getScaleTypeFromXml(obtainStyledAttributes, C0980R.styleable.GenericDraweeView_placeholderImageScaleType, scaleType);
                i3 = obtainStyledAttributes.getResourceId(C0980R.styleable.GenericDraweeView_retryImage, 0);
                scaleType2 = getScaleTypeFromXml(obtainStyledAttributes, C0980R.styleable.GenericDraweeView_retryImageScaleType, scaleType2);
                i4 = obtainStyledAttributes.getResourceId(C0980R.styleable.GenericDraweeView_failureImage, 0);
                scaleType3 = getScaleTypeFromXml(obtainStyledAttributes, C0980R.styleable.GenericDraweeView_failureImageScaleType, scaleType3);
                i5 = obtainStyledAttributes.getResourceId(C0980R.styleable.GenericDraweeView_progressBarImage, 0);
                scaleType4 = getScaleTypeFromXml(obtainStyledAttributes, C0980R.styleable.GenericDraweeView_progressBarImageScaleType, scaleType4);
                i13 = obtainStyledAttributes.getInteger(C0980R.styleable.GenericDraweeView_progressBarAutoRotateInterval, 0);
                scaleType5 = getScaleTypeFromXml(obtainStyledAttributes, C0980R.styleable.GenericDraweeView_actualImageScaleType, scaleType5);
                i6 = obtainStyledAttributes.getResourceId(C0980R.styleable.GenericDraweeView_backgroundImage, 0);
                i7 = obtainStyledAttributes.getResourceId(C0980R.styleable.GenericDraweeView_overlayImage, 0);
                i8 = obtainStyledAttributes.getResourceId(C0980R.styleable.GenericDraweeView_pressedStateOverlayImage, 0);
                z = obtainStyledAttributes.getBoolean(C0980R.styleable.GenericDraweeView_roundAsCircle, false);
                i9 = obtainStyledAttributes.getDimensionPixelSize(C0980R.styleable.GenericDraweeView_roundedCornerRadius, 0);
                z2 = obtainStyledAttributes.getBoolean(C0980R.styleable.GenericDraweeView_roundTopLeft, true);
                z3 = obtainStyledAttributes.getBoolean(C0980R.styleable.GenericDraweeView_roundTopRight, true);
                z4 = obtainStyledAttributes.getBoolean(C0980R.styleable.GenericDraweeView_roundBottomRight, true);
                z5 = obtainStyledAttributes.getBoolean(C0980R.styleable.GenericDraweeView_roundBottomLeft, true);
                i10 = obtainStyledAttributes.getColor(C0980R.styleable.GenericDraweeView_roundWithOverlayColor, 0);
                i11 = obtainStyledAttributes.getDimensionPixelSize(C0980R.styleable.GenericDraweeView_roundingBorderWidth, 0);
                i12 = obtainStyledAttributes.getColor(C0980R.styleable.GenericDraweeView_roundingBorderColor, i12);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder = new GenericDraweeHierarchyBuilder(resources);
        genericDraweeHierarchyBuilder.setFadeDuration(i);
        if (i2 > 0) {
            genericDraweeHierarchyBuilder.setPlaceholderImage(resources.getDrawable(i2), scaleType);
        }
        if (i3 > 0) {
            genericDraweeHierarchyBuilder.setRetryImage(resources.getDrawable(i3), scaleType2);
        }
        if (i4 > 0) {
            genericDraweeHierarchyBuilder.setFailureImage(resources.getDrawable(i4), scaleType3);
        }
        if (i5 > 0) {
            Drawable drawable = resources.getDrawable(i5);
            genericDraweeHierarchyBuilder.setProgressBarImage(i13 > 0 ? new AutoRotateDrawable(drawable, i13) : drawable, scaleType4);
        }
        if (i6 > 0) {
            genericDraweeHierarchyBuilder.setBackground(resources.getDrawable(i6));
        }
        if (i7 > 0) {
            genericDraweeHierarchyBuilder.setOverlay(resources.getDrawable(i7));
        }
        if (i8 > 0) {
            genericDraweeHierarchyBuilder.setPressedStateOverlay(getResources().getDrawable(i8));
        }
        genericDraweeHierarchyBuilder.setActualImageScaleType(scaleType5);
        if (z || i9 > 0) {
            RoundingParams roundingParams = new RoundingParams();
            roundingParams.setRoundAsCircle(z);
            if (i9 > 0) {
                roundingParams.setCornersRadii(z2 ? (float) i9 : 0.0f, z3 ? (float) i9 : 0.0f, z4 ? (float) i9 : 0.0f, z5 ? (float) i9 : 0.0f);
            }
            if (i10 != 0) {
                roundingParams.setOverlayColor(i10);
            }
            if (i12 != 0 && i11 > 0) {
                roundingParams.setBorder(i12, (float) i11);
            }
            genericDraweeHierarchyBuilder.setRoundingParams(roundingParams);
        }
        setHierarchy(genericDraweeHierarchyBuilder.build());
    }

    public float getAspectRatio() {
        return this.mAspectRatio;
    }

    protected void onMeasure(int i, int i2) {
        this.mMeasureSpec.width = i;
        this.mMeasureSpec.height = i2;
        AspectRatioMeasure.updateMeasureSpec(this.mMeasureSpec, this.mAspectRatio, getLayoutParams(), getPaddingLeft() + getPaddingRight(), getPaddingTop() + getPaddingBottom());
        super.onMeasure(this.mMeasureSpec.width, this.mMeasureSpec.height);
    }

    public void setAspectRatio(float f) {
        if (f != this.mAspectRatio) {
            this.mAspectRatio = f;
            requestLayout();
        }
    }
}
