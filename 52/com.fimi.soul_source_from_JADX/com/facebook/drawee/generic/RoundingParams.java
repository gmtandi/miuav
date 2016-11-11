package com.facebook.drawee.generic;

import com.facebook.common.internal.Preconditions;
import java.util.Arrays;

public class RoundingParams {
    private int mBorderColor;
    private float mBorderWidth;
    private float[] mCornersRadii;
    private int mOverlayColor;
    private boolean mRoundAsCircle;
    private RoundingMethod mRoundingMethod;

    public enum RoundingMethod {
        OVERLAY_COLOR,
        BITMAP_ONLY
    }

    public RoundingParams() {
        this.mRoundingMethod = RoundingMethod.BITMAP_ONLY;
        this.mRoundAsCircle = false;
        this.mCornersRadii = null;
        this.mOverlayColor = 0;
        this.mBorderWidth = 0.0f;
        this.mBorderColor = 0;
    }

    public static RoundingParams asCircle() {
        return new RoundingParams().setRoundAsCircle(true);
    }

    public static RoundingParams fromCornersRadii(float f, float f2, float f3, float f4) {
        return new RoundingParams().setCornersRadii(f, f2, f3, f4);
    }

    public static RoundingParams fromCornersRadii(float[] fArr) {
        return new RoundingParams().setCornersRadii(fArr);
    }

    public static RoundingParams fromCornersRadius(float f) {
        return new RoundingParams().setCornersRadius(f);
    }

    private float[] getOrCreateRoundedCornersRadii() {
        if (this.mCornersRadii == null) {
            this.mCornersRadii = new float[8];
        }
        return this.mCornersRadii;
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    public float[] getCornersRadii() {
        return this.mCornersRadii;
    }

    public int getOverlayColor() {
        return this.mOverlayColor;
    }

    public boolean getRoundAsCircle() {
        return this.mRoundAsCircle;
    }

    public RoundingMethod getRoundingMethod() {
        return this.mRoundingMethod;
    }

    public RoundingParams setBorder(int i, float f) {
        Preconditions.checkArgument(f >= 0.0f, "the border width cannot be < 0");
        this.mBorderWidth = f;
        this.mBorderColor = i;
        return this;
    }

    public RoundingParams setCornersRadii(float f, float f2, float f3, float f4) {
        float[] orCreateRoundedCornersRadii = getOrCreateRoundedCornersRadii();
        orCreateRoundedCornersRadii[1] = f;
        orCreateRoundedCornersRadii[0] = f;
        orCreateRoundedCornersRadii[3] = f2;
        orCreateRoundedCornersRadii[2] = f2;
        orCreateRoundedCornersRadii[5] = f3;
        orCreateRoundedCornersRadii[4] = f3;
        orCreateRoundedCornersRadii[7] = f4;
        orCreateRoundedCornersRadii[6] = f4;
        return this;
    }

    public RoundingParams setCornersRadii(float[] fArr) {
        Preconditions.checkNotNull(fArr);
        Preconditions.checkArgument(fArr.length == 8, "radii should have exactly 8 values");
        System.arraycopy(fArr, 0, getOrCreateRoundedCornersRadii(), 0, 8);
        return this;
    }

    public RoundingParams setCornersRadius(float f) {
        Arrays.fill(getOrCreateRoundedCornersRadii(), f);
        return this;
    }

    public RoundingParams setOverlayColor(int i) {
        this.mOverlayColor = i;
        this.mRoundingMethod = RoundingMethod.OVERLAY_COLOR;
        return this;
    }

    public RoundingParams setRoundAsCircle(boolean z) {
        this.mRoundAsCircle = z;
        return this;
    }

    public RoundingParams setRoundingMethod(RoundingMethod roundingMethod) {
        this.mRoundingMethod = roundingMethod;
        return this;
    }
}
