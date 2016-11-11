package com.facebook.imagepipeline.animated.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import java.util.Arrays;
import org.codehaus.jackson.smile.SmileConstants;

public class AnimatedDrawableUtil {
    private static final int FRAME_DURATION_MS_FOR_MIN = 100;
    private static final int MIN_FRAME_DURATION_MS = 11;

    public static boolean isOutsideRange(int i, int i2, int i3) {
        boolean z = true;
        if (i == -1 || i2 == -1) {
            return true;
        }
        if (i <= i2) {
            return i3 < i || i3 > i2;
        } else {
            if (i3 >= i || i3 <= i2) {
                z = false;
            }
            return z;
        }
    }

    public void appendMemoryString(StringBuilder stringBuilder, int i) {
        if (i < SmileConstants.MAX_SHARED_STRING_VALUES) {
            stringBuilder.append(i);
            stringBuilder.append("KB");
            return;
        }
        int i2 = (i % SmileConstants.MAX_SHARED_STRING_VALUES) / FRAME_DURATION_MS_FOR_MIN;
        stringBuilder.append(i / SmileConstants.MAX_SHARED_STRING_VALUES);
        stringBuilder.append(".");
        stringBuilder.append(i2);
        stringBuilder.append("MB");
    }

    public void fixFrameDurations(int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            if (iArr[i] < MIN_FRAME_DURATION_MS) {
                iArr[i] = FRAME_DURATION_MS_FOR_MIN;
            }
        }
    }

    public int getFrameForTimestampMs(int[] iArr, int i) {
        int binarySearch = Arrays.binarySearch(iArr, i);
        return binarySearch < 0 ? ((-binarySearch) - 1) - 1 : binarySearch;
    }

    public int[] getFrameTimeStampsFromDurations(int[] iArr) {
        int i = 0;
        int[] iArr2 = new int[iArr.length];
        int i2 = 0;
        while (i < iArr.length) {
            iArr2[i] = i2;
            i2 += iArr[i];
            i++;
        }
        return iArr2;
    }

    @SuppressLint({"NewApi"})
    public int getSizeOfBitmap(Bitmap bitmap) {
        return VERSION.SDK_INT >= 19 ? bitmap.getAllocationByteCount() : VERSION.SDK_INT >= 12 ? bitmap.getByteCount() : (bitmap.getWidth() * bitmap.getHeight()) * 4;
    }

    public int getTotalDurationFromFrameDurations(int[] iArr) {
        int i = 0;
        int i2 = 0;
        while (i < iArr.length) {
            i2 += iArr[i];
            i++;
        }
        return i2;
    }
}
