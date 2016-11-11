package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;

public abstract class ForwardingDrawable extends Drawable implements Callback, TransformAwareDrawable, TransformCallback {
    private static final Matrix sTempTransform;
    private Drawable mCurrentDelegate;
    private final DrawableProperties mDrawableProperties;
    protected TransformCallback mTransformCallback;

    static {
        sTempTransform = new Matrix();
    }

    public ForwardingDrawable(Drawable drawable) {
        this.mDrawableProperties = new DrawableProperties();
        this.mCurrentDelegate = drawable;
        DrawableUtils.setCallbacks(this.mCurrentDelegate, this, this);
    }

    public void draw(Canvas canvas) {
        this.mCurrentDelegate.draw(canvas);
    }

    public Drawable getCurrent() {
        return this.mCurrentDelegate;
    }

    public int getIntrinsicHeight() {
        return this.mCurrentDelegate.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        return this.mCurrentDelegate.getIntrinsicWidth();
    }

    public int getOpacity() {
        return this.mCurrentDelegate.getOpacity();
    }

    public boolean getPadding(Rect rect) {
        return this.mCurrentDelegate.getPadding(rect);
    }

    protected void getParentTransform(Matrix matrix) {
        if (this.mTransformCallback != null) {
            this.mTransformCallback.getTransform(matrix);
        } else {
            matrix.reset();
        }
    }

    public void getRootBounds(RectF rectF) {
        if (this.mTransformCallback != null) {
            this.mTransformCallback.getRootBounds(rectF);
        } else {
            rectF.set(getBounds());
        }
    }

    public void getTransform(Matrix matrix) {
        getParentTransform(matrix);
    }

    public void getTransformedBounds(RectF rectF) {
        getParentTransform(sTempTransform);
        rectF.set(getBounds());
        sTempTransform.mapRect(rectF);
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public boolean isStateful() {
        return this.mCurrentDelegate.isStateful();
    }

    public Drawable mutate() {
        this.mCurrentDelegate.mutate();
        return this;
    }

    protected void onBoundsChange(Rect rect) {
        this.mCurrentDelegate.setBounds(rect);
    }

    protected boolean onLevelChange(int i) {
        return this.mCurrentDelegate.setLevel(i);
    }

    protected boolean onStateChange(int[] iArr) {
        return this.mCurrentDelegate.setState(iArr);
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void setAlpha(int i) {
        this.mDrawableProperties.setAlpha(i);
        this.mCurrentDelegate.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mDrawableProperties.setColorFilter(colorFilter);
        this.mCurrentDelegate.setColorFilter(colorFilter);
    }

    public Drawable setCurrent(Drawable drawable) {
        Drawable currentWithoutInvalidate = setCurrentWithoutInvalidate(drawable);
        invalidateSelf();
        return currentWithoutInvalidate;
    }

    protected Drawable setCurrentWithoutInvalidate(Drawable drawable) {
        Drawable drawable2 = this.mCurrentDelegate;
        DrawableUtils.setCallbacks(drawable2, null, null);
        DrawableUtils.setCallbacks(drawable, null, null);
        DrawableUtils.setDrawableProperties(drawable, this.mDrawableProperties);
        DrawableUtils.copyProperties(drawable, drawable2);
        DrawableUtils.setCallbacks(drawable, this, this);
        this.mCurrentDelegate = drawable;
        return drawable2;
    }

    public void setDither(boolean z) {
        this.mDrawableProperties.setDither(z);
        this.mCurrentDelegate.setDither(z);
    }

    public void setFilterBitmap(boolean z) {
        this.mDrawableProperties.setFilterBitmap(z);
        this.mCurrentDelegate.setFilterBitmap(z);
    }

    public void setTransformCallback(TransformCallback transformCallback) {
        this.mTransformCallback = transformCallback;
    }

    public boolean setVisible(boolean z, boolean z2) {
        super.setVisible(z, z2);
        return this.mCurrentDelegate.setVisible(z, z2);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
