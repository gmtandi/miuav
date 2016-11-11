package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import com.facebook.common.internal.Preconditions;
import javax.annotation.Nullable;

public class ArrayDrawable extends Drawable implements Callback, TransformAwareDrawable, TransformCallback {
    private final Rect mBounds;
    private final DrawableProperties mDrawableProperties;
    private boolean mIsMutated;
    private boolean mIsStateful;
    private boolean mIsStatefulCalculated;
    private boolean mIsVisible;
    private final Drawable[] mLayers;
    private int mLevel;
    private int[] mState;
    private final Rect mTmpRect;
    private TransformCallback mTransformCallback;

    public ArrayDrawable(Drawable[] drawableArr) {
        int i = 0;
        this.mDrawableProperties = new DrawableProperties();
        this.mTmpRect = new Rect();
        this.mIsStateful = false;
        this.mIsStatefulCalculated = false;
        this.mIsMutated = false;
        this.mBounds = new Rect();
        Preconditions.checkNotNull(drawableArr);
        this.mLayers = drawableArr;
        while (i < this.mLayers.length) {
            DrawableUtils.setCallbacks(this.mLayers[i], this, this);
            i++;
        }
    }

    public void draw(Canvas canvas) {
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }
    }

    @Nullable
    public Drawable getDrawable(int i) {
        return this.mLayers[i];
    }

    public int getIntrinsicHeight() {
        int i = -1;
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                i = Math.max(i, drawable.getIntrinsicHeight());
            }
        }
        return i > 0 ? i : -1;
    }

    public int getIntrinsicWidth() {
        int i = -1;
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                i = Math.max(i, drawable.getIntrinsicWidth());
            }
        }
        return i > 0 ? i : -1;
    }

    public int getNumberOfLayers() {
        return this.mLayers.length;
    }

    public int getOpacity() {
        if (this.mLayers.length == 0) {
            return -2;
        }
        int i = -1;
        for (int i2 = 1; i2 < this.mLayers.length; i2++) {
            Drawable drawable = this.mLayers[i2];
            if (drawable != null) {
                i = Drawable.resolveOpacity(i, drawable.getOpacity());
            }
        }
        return i;
    }

    public boolean getPadding(Rect rect) {
        int i = 0;
        rect.left = 0;
        rect.top = 0;
        rect.right = 0;
        rect.bottom = 0;
        Rect rect2 = this.mTmpRect;
        while (i < this.mLayers.length) {
            Drawable drawable = this.mLayers[i];
            if (drawable != null) {
                drawable.getPadding(rect2);
                rect.left = Math.max(rect.left, rect2.left);
                rect.top = Math.max(rect.top, rect2.top);
                rect.right = Math.max(rect.right, rect2.right);
                rect.bottom = Math.max(rect.bottom, rect2.bottom);
            }
            i++;
        }
        return true;
    }

    public void getRootBounds(RectF rectF) {
        if (this.mTransformCallback != null) {
            this.mTransformCallback.getRootBounds(rectF);
        } else {
            rectF.set(getBounds());
        }
    }

    public void getTransform(Matrix matrix) {
        if (this.mTransformCallback != null) {
            this.mTransformCallback.getTransform(matrix);
        } else {
            matrix.reset();
        }
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public boolean isStateful() {
        if (!this.mIsStatefulCalculated) {
            this.mIsStateful = false;
            for (Drawable drawable : this.mLayers) {
                boolean z = this.mIsStateful;
                int i = (drawable == null || !drawable.isStateful()) ? 0 : 1;
                this.mIsStateful = i | z;
            }
            this.mIsStatefulCalculated = true;
        }
        return this.mIsStateful;
    }

    public Drawable mutate() {
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                drawable.mutate();
            }
        }
        this.mIsMutated = true;
        return this;
    }

    protected void onBoundsChange(Rect rect) {
        this.mBounds.set(rect);
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                drawable.setBounds(rect);
            }
        }
    }

    protected boolean onLevelChange(int i) {
        int i2 = 0;
        this.mLevel = i;
        boolean z = false;
        while (i2 < this.mLayers.length) {
            Drawable drawable = this.mLayers[i2];
            if (drawable != null && drawable.setLevel(i)) {
                z = true;
            }
            i2++;
        }
        return z;
    }

    protected boolean onStateChange(int[] iArr) {
        int i = 0;
        this.mState = iArr;
        boolean z = false;
        while (i < this.mLayers.length) {
            Drawable drawable = this.mLayers[i];
            if (drawable != null && drawable.setState(iArr)) {
                z = true;
            }
            i++;
        }
        return z;
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void setAlpha(int i) {
        this.mDrawableProperties.setAlpha(i);
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                drawable.setAlpha(i);
            }
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mDrawableProperties.setColorFilter(colorFilter);
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    public void setDither(boolean z) {
        this.mDrawableProperties.setDither(z);
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                drawable.setDither(z);
            }
        }
    }

    public void setDrawable(int i, @Nullable Drawable drawable) {
        boolean z = true;
        Preconditions.checkArgument(i >= 0);
        if (i >= this.mLayers.length) {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (drawable != this.mLayers[i]) {
            if (drawable != null && this.mIsMutated) {
                drawable = drawable.mutate();
            }
            DrawableUtils.setCallbacks(this.mLayers[i], null, null);
            DrawableUtils.setCallbacks(drawable, null, null);
            DrawableUtils.setDrawableProperties(drawable, this.mDrawableProperties);
            if (drawable != null) {
                drawable.setBounds(this.mBounds);
                drawable.setLevel(this.mLevel);
                drawable.setState(this.mState);
                drawable.setVisible(this.mIsVisible, false);
            }
            DrawableUtils.setCallbacks(drawable, this, this);
            this.mIsStatefulCalculated = false;
            this.mLayers[i] = drawable;
            invalidateSelf();
        }
    }

    public void setFilterBitmap(boolean z) {
        this.mDrawableProperties.setFilterBitmap(z);
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                drawable.setFilterBitmap(z);
            }
        }
    }

    public void setTransformCallback(TransformCallback transformCallback) {
        this.mTransformCallback = transformCallback;
    }

    public boolean setVisible(boolean z, boolean z2) {
        this.mIsVisible = z;
        boolean visible = super.setVisible(z, z2);
        for (Drawable drawable : this.mLayers) {
            if (drawable != null) {
                drawable.setVisible(z, z2);
            }
        }
        return visible;
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
