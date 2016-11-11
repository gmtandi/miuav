package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.tencent.mm.sdk.platformtools.Util;

public class GlobalHistogramBinarizer extends Binarizer {
    private static final int LUMINANCE_BITS = 5;
    private static final int LUMINANCE_BUCKETS = 32;
    private static final int LUMINANCE_SHIFT = 3;
    private int[] buckets;
    private byte[] luminances;

    public GlobalHistogramBinarizer(LuminanceSource luminanceSource) {
        super(luminanceSource);
        this.luminances = null;
        this.buckets = null;
    }

    private static int estimateBlackPoint(int[] iArr) {
        int i;
        int i2;
        int i3 = 0;
        int length = iArr.length;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (i = 0; i < length; i++) {
            if (iArr[i] > i4) {
                i4 = iArr[i];
                i5 = i;
            }
            if (iArr[i] > i6) {
                i6 = iArr[i];
            }
        }
        i = 0;
        int i7 = 0;
        while (i3 < length) {
            i4 = i3 - i5;
            i4 *= iArr[i3] * i4;
            if (i4 > i) {
                i = i3;
            } else {
                i4 = i;
                i = i7;
            }
            i3++;
            i7 = i;
            i = i4;
        }
        if (i5 > i7) {
            i2 = i7;
            i7 = i5;
        } else {
            i2 = i5;
        }
        if (i7 - i2 <= (length >> 4)) {
            throw NotFoundException.getNotFoundInstance();
        }
        i3 = i7 - 1;
        i5 = -1;
        i = i7 - 1;
        while (i > i2) {
            i4 = i - i2;
            i4 = ((i4 * i4) * (i7 - i)) * (i6 - iArr[i]);
            if (i4 > i5) {
                i5 = i;
            } else {
                i4 = i5;
                i5 = i3;
            }
            i--;
            i3 = i5;
            i5 = i4;
        }
        return i3 << LUMINANCE_SHIFT;
    }

    private void initArrays(int i) {
        if (this.luminances == null || this.luminances.length < i) {
            this.luminances = new byte[i];
        }
        if (this.buckets == null) {
            this.buckets = new int[LUMINANCE_BUCKETS];
            return;
        }
        for (int i2 = 0; i2 < LUMINANCE_BUCKETS; i2++) {
            this.buckets[i2] = 0;
        }
    }

    public Binarizer createBinarizer(LuminanceSource luminanceSource) {
        return new GlobalHistogramBinarizer(luminanceSource);
    }

    public BitMatrix getBlackMatrix() {
        int i;
        int i2;
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        int height = luminanceSource.getHeight();
        BitMatrix bitMatrix = new BitMatrix(width, height);
        initArrays(width);
        int[] iArr = this.buckets;
        for (i = 1; i < LUMINANCE_BITS; i++) {
            byte[] row = luminanceSource.getRow((height * i) / LUMINANCE_BITS, this.luminances);
            int i3 = (width << 2) / LUMINANCE_BITS;
            for (i2 = width / LUMINANCE_BITS; i2 < i3; i2++) {
                int i4 = (row[i2] & Util.MASK_8BIT) >> LUMINANCE_SHIFT;
                iArr[i4] = iArr[i4] + 1;
            }
        }
        int estimateBlackPoint = estimateBlackPoint(iArr);
        byte[] matrix = luminanceSource.getMatrix();
        for (i = 0; i < height; i++) {
            int i5 = i * width;
            for (i2 = 0; i2 < width; i2++) {
                if ((matrix[i5 + i2] & Util.MASK_8BIT) < estimateBlackPoint) {
                    bitMatrix.set(i2, i);
                }
            }
        }
        return bitMatrix;
    }

    public BitArray getBlackRow(int i, BitArray bitArray) {
        int i2;
        int i3;
        int i4 = 1;
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        if (bitArray == null || bitArray.getSize() < width) {
            bitArray = new BitArray(width);
        } else {
            bitArray.clear();
        }
        initArrays(width);
        byte[] row = luminanceSource.getRow(i, this.luminances);
        int[] iArr = this.buckets;
        for (i2 = 0; i2 < width; i2++) {
            i3 = (row[i2] & Util.MASK_8BIT) >> LUMINANCE_SHIFT;
            iArr[i3] = iArr[i3] + 1;
        }
        i3 = estimateBlackPoint(iArr);
        i2 = row[1] & Util.MASK_8BIT;
        int i5 = row[0] & Util.MASK_8BIT;
        while (i4 < width - 1) {
            int i6 = row[i4 + 1] & Util.MASK_8BIT;
            if (((((i2 << 2) - i5) - i6) >> 1) < i3) {
                bitArray.set(i4);
            }
            i4++;
            i5 = i2;
            i2 = i6;
        }
        return bitArray;
    }
}
