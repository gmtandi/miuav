package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.soul.module.setting.newhand.C1873o;

public class ProgressBarDrawable extends Drawable {
    private int mBackgroundColor;
    private int mBarWidth;
    private int mColor;
    private boolean mHideWhenZero;
    private int mLevel;
    private int mPadding;
    private final Paint mPaint;

    public ProgressBarDrawable() {
        this.mPaint = new Paint(1);
        this.mBackgroundColor = C1186y.f5353a;
        this.mColor = -2147450625;
        this.mPadding = 10;
        this.mBarWidth = 20;
        this.mLevel = 0;
        this.mHideWhenZero = false;
    }

    private void drawBar(Canvas canvas, int i, int i2) {
        Rect bounds = getBounds();
        int width = ((bounds.width() - (this.mPadding * 2)) * i) / C1873o.ak;
        int i3 = bounds.left + this.mPadding;
        int i4 = (bounds.bottom - this.mPadding) - this.mBarWidth;
        this.mPaint.setColor(i2);
        canvas.drawRect((float) i3, (float) i4, (float) (width + i3), (float) (i4 + this.mBarWidth), this.mPaint);
    }

    public void draw(Canvas canvas) {
        if (!this.mHideWhenZero || this.mLevel != 0) {
            drawBar(canvas, C1873o.ak, this.mBackgroundColor);
            drawBar(canvas, this.mLevel, this.mColor);
        }
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public int getBarWidth() {
        return this.mBarWidth;
    }

    public int getColor() {
        return this.mColor;
    }

    public boolean getHideWhenZero() {
        return this.mHideWhenZero;
    }

    public int getOpacity() {
        return DrawableUtils.getOpacityFromColor(this.mPaint.getColor());
    }

    public boolean getPadding(Rect rect) {
        rect.set(this.mPadding, this.mPadding, this.mPadding, this.mPadding);
        return this.mPadding != 0;
    }

    protected boolean onLevelChange(int i) {
        this.mLevel = i;
        invalidateSelf();
        return true;
    }

    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    public void setBackgroundColor(int i) {
        if (this.mBackgroundColor != i) {
            this.mBackgroundColor = i;
            invalidateSelf();
        }
    }

    public void setBarWidth(int i) {
        if (this.mBarWidth != i) {
            this.mBarWidth = i;
            invalidateSelf();
        }
    }

    public void setColor(int i) {
        if (this.mColor != i) {
            this.mColor = i;
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public void setHideWhenZero(boolean z) {
        this.mHideWhenZero = z;
    }

    public void setPadding(int i) {
        if (this.mPadding != i) {
            this.mPadding = i;
            invalidateSelf();
        }
    }
}
