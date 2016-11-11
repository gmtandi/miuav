package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.tencent.mm.sdk.platformtools.Util;
import java.util.Arrays;

public class RoundedColorDrawable extends Drawable implements Rounded {
    private int mAlpha;
    @VisibleForTesting
    int mBorderColor;
    @VisibleForTesting
    float mBorderWidth;
    private int mColor;
    @VisibleForTesting
    boolean mIsCircle;
    @VisibleForTesting
    final Paint mPaint;
    @VisibleForTesting
    final Path mPath;
    @VisibleForTesting
    final float[] mRadii;
    private final RectF mTempRect;

    public RoundedColorDrawable(float f, int i) {
        this(i);
        setRadius(f);
    }

    public RoundedColorDrawable(int i) {
        this.mRadii = new float[8];
        this.mPaint = new Paint(1);
        this.mIsCircle = false;
        this.mBorderWidth = 0.0f;
        this.mBorderColor = 0;
        this.mPath = new Path();
        this.mColor = 0;
        this.mTempRect = new RectF();
        this.mAlpha = Util.MASK_8BIT;
        setColor(i);
    }

    public RoundedColorDrawable(float[] fArr, int i) {
        this(i);
        setRadii(fArr);
    }

    public static RoundedColorDrawable fromColorDrawable(ColorDrawable colorDrawable) {
        return new RoundedColorDrawable(colorDrawable.getColor());
    }

    private void updatePath() {
        this.mPath.reset();
        this.mTempRect.set(getBounds());
        this.mTempRect.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
        if (this.mIsCircle) {
            this.mPath.addCircle(this.mTempRect.centerX(), this.mTempRect.centerY(), Math.min(this.mTempRect.width(), this.mTempRect.height()) / 2.0f, Direction.CW);
        } else {
            this.mPath.addRoundRect(this.mTempRect, this.mRadii, Direction.CW);
        }
        this.mTempRect.inset((-this.mBorderWidth) / 2.0f, (-this.mBorderWidth) / 2.0f);
    }

    public void draw(Canvas canvas) {
        this.mPaint.setColor(DrawableUtils.multiplyColorAlpha(this.mColor, this.mAlpha));
        this.mPaint.setStyle(Style.FILL);
        canvas.drawPath(this.mPath, this.mPaint);
        if (this.mBorderWidth != 0.0f) {
            this.mPaint.setColor(DrawableUtils.multiplyColorAlpha(this.mBorderColor, this.mAlpha));
            this.mPaint.setStyle(Style.STROKE);
            this.mPaint.setStrokeWidth(this.mBorderWidth);
            canvas.drawPath(this.mPath, this.mPaint);
        }
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public int getColor() {
        return this.mColor;
    }

    public int getOpacity() {
        return DrawableUtils.getOpacityFromColor(DrawableUtils.multiplyColorAlpha(this.mColor, this.mAlpha));
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        updatePath();
    }

    public void setAlpha(int i) {
        if (i != this.mAlpha) {
            this.mAlpha = i;
            invalidateSelf();
        }
    }

    public void setBorder(int i, float f) {
        if (this.mBorderColor != i) {
            this.mBorderColor = i;
            invalidateSelf();
        }
        if (this.mBorderWidth != f) {
            this.mBorderWidth = f;
            updatePath();
            invalidateSelf();
        }
    }

    public void setCircle(boolean z) {
        this.mIsCircle = z;
        updatePath();
        invalidateSelf();
    }

    public void setColor(int i) {
        if (this.mColor != i) {
            this.mColor = i;
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void setRadii(float[] fArr) {
        if (fArr == null) {
            Arrays.fill(this.mRadii, 0.0f);
        } else {
            Preconditions.checkArgument(fArr.length == 8, "radii should have exactly 8 values");
            System.arraycopy(fArr, 0, this.mRadii, 0, 8);
        }
        updatePath();
        invalidateSelf();
    }

    public void setRadius(float f) {
        Preconditions.checkArgument(f >= 0.0f, "radius should be non negative");
        Arrays.fill(this.mRadii, f);
        updatePath();
        invalidateSelf();
    }
}
