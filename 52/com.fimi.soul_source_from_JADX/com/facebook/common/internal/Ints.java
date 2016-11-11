package com.facebook.common.internal;

public class Ints {
    private Ints() {
    }

    public static int max(int... iArr) {
        int i = 1;
        Preconditions.checkArgument(iArr.length > 0);
        int i2 = iArr[0];
        while (i < iArr.length) {
            if (iArr[i] > i2) {
                i2 = iArr[i];
            }
            i++;
        }
        return i2;
    }
}
