package com.facebook.imagepipeline.animated.impl;

import android.os.SystemClock;
import com.tencent.mm.sdk.platformtools.Util;

class RollingStat {
    private static final int WINDOWS = 60;
    private final short[] mStat;

    public RollingStat() {
        this.mStat = new short[WINDOWS];
    }

    int getSum(int i) {
        int i2 = 0;
        long uptimeMillis = SystemClock.uptimeMillis() / 1000;
        int i3 = (int) ((uptimeMillis - ((long) i)) % 60);
        int i4 = (int) ((uptimeMillis / 60) & 255);
        for (int i5 = 0; i5 < i; i5++) {
            short s = this.mStat[(i3 + i5) % WINDOWS];
            int i6 = s & Util.MASK_8BIT;
            if (((s >> 8) & Util.MASK_8BIT) == i4) {
                i2 += i6;
            }
        }
        return i2;
    }

    void incrementStats(int i) {
        long uptimeMillis = SystemClock.uptimeMillis() / 1000;
        int i2 = (int) (uptimeMillis % 60);
        int i3 = (int) ((uptimeMillis / 60) & 255);
        short s = this.mStat[i2];
        int i4 = s & Util.MASK_8BIT;
        if (i3 == ((s >> 8) & Util.MASK_8BIT)) {
            i += i4;
        }
        this.mStat[i2] = (short) ((i3 << 8) | i);
    }
}
