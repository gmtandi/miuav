package com.facebook.drawee.drawable;

import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

public class DrawableProperties {
    private static final int UNSET = -1;
    private int mAlpha;
    private ColorFilter mColorFilter;
    private int mDither;
    private int mFilterBitmap;
    private boolean mIsSetColorFilter;

    public DrawableProperties() {
        this.mAlpha = UNSET;
        this.mIsSetColorFilter = false;
        this.mColorFilter = null;
        this.mDither = UNSET;
        this.mFilterBitmap = UNSET;
    }

    public void applyTo(Drawable drawable) {
        boolean z = true;
        if (drawable != null) {
            if (this.mAlpha != UNSET) {
                drawable.setAlpha(this.mAlpha);
            }
            if (this.mIsSetColorFilter) {
                drawable.setColorFilter(this.mColorFilter);
            }
            if (this.mDither != UNSET) {
                drawable.setDither(this.mDither != 0);
            }
            if (this.mFilterBitmap != UNSET) {
                if (this.mFilterBitmap == 0) {
                    z = false;
                }
                drawable.setFilterBitmap(z);
            }
        }
    }

    public void setAlpha(int i) {
        this.mAlpha = i;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mColorFilter = colorFilter;
        this.mIsSetColorFilter = true;
    }

    public void setDither(boolean z) {
        this.mDither = z ? 1 : 0;
    }

    public void setFilterBitmap(boolean z) {
        this.mFilterBitmap = z ? 1 : 0;
    }
}
