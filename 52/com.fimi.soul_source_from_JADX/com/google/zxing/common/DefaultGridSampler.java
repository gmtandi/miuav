package com.google.zxing.common;

import com.google.zxing.NotFoundException;

public final class DefaultGridSampler extends GridSampler {
    public BitMatrix sampleGrid(BitMatrix bitMatrix, int i, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) {
        return sampleGrid(bitMatrix, i, PerspectiveTransform.quadrilateralToQuadrilateral(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16));
    }

    public BitMatrix sampleGrid(BitMatrix bitMatrix, int i, PerspectiveTransform perspectiveTransform) {
        BitMatrix bitMatrix2 = new BitMatrix(i);
        float[] fArr = new float[(i << 1)];
        for (int i2 = 0; i2 < i; i2++) {
            int i3;
            int length = fArr.length;
            float f = ((float) i2) + 0.5f;
            for (i3 = 0; i3 < length; i3 += 2) {
                fArr[i3] = ((float) (i3 >> 1)) + 0.5f;
                fArr[i3 + 1] = f;
            }
            perspectiveTransform.transformPoints(fArr);
            GridSampler.checkAndNudgePoints(bitMatrix, fArr);
            i3 = 0;
            while (i3 < length) {
                try {
                    if (bitMatrix.get((int) fArr[i3], (int) fArr[i3 + 1])) {
                        bitMatrix2.set(i3 >> 1, i2);
                    }
                    i3 += 2;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
        }
        return bitMatrix2;
    }
}
