package com.facebook.imagepipeline.image;

import android.support.v4.view.GravityCompat;
import com.facebook.imagepipeline.memory.DefaultFlexByteArrayPoolParams;

public class ImmutableQualityInfo implements QualityInfo {
    public static final QualityInfo FULL_QUALITY;
    boolean mIsOfFullQuality;
    boolean mIsOfGoodEnoughQuality;
    int mQuality;

    static {
        FULL_QUALITY = of(Integer.MAX_VALUE, true, true);
    }

    private ImmutableQualityInfo(int i, boolean z, boolean z2) {
        this.mQuality = i;
        this.mIsOfGoodEnoughQuality = z;
        this.mIsOfFullQuality = z2;
    }

    public static QualityInfo of(int i, boolean z, boolean z2) {
        return new ImmutableQualityInfo(i, z, z2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImmutableQualityInfo)) {
            return false;
        }
        ImmutableQualityInfo immutableQualityInfo = (ImmutableQualityInfo) obj;
        return this.mQuality == immutableQualityInfo.mQuality && this.mIsOfGoodEnoughQuality == immutableQualityInfo.mIsOfGoodEnoughQuality && this.mIsOfFullQuality == immutableQualityInfo.mIsOfFullQuality;
    }

    public int getQuality() {
        return this.mQuality;
    }

    public int hashCode() {
        int i = 0;
        int i2 = (this.mIsOfGoodEnoughQuality ? DefaultFlexByteArrayPoolParams.DEFAULT_MAX_BYTE_ARRAY_SIZE : 0) ^ this.mQuality;
        if (this.mIsOfFullQuality) {
            i = GravityCompat.RELATIVE_LAYOUT_DIRECTION;
        }
        return i2 ^ i;
    }

    public boolean isOfFullQuality() {
        return this.mIsOfFullQuality;
    }

    public boolean isOfGoodEnoughQuality() {
        return this.mIsOfGoodEnoughQuality;
    }
}
