package com.google.zxing.pdf417.detector;

import com.fimi.soul.view.photodraweeview.C2020f;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import java.util.Hashtable;

public final class Detector {
    private static final int MAX_AVG_VARIANCE = 107;
    private static final int MAX_INDIVIDUAL_VARIANCE = 204;
    private static final int SKEW_THRESHOLD = 2;
    private static final int[] START_PATTERN;
    private static final int[] START_PATTERN_REVERSE;
    private static final int[] STOP_PATTERN;
    private static final int[] STOP_PATTERN_REVERSE;
    private final BinaryBitmap image;

    static {
        START_PATTERN = new int[]{8, 1, 1, 1, 1, 1, 1, 3};
        START_PATTERN_REVERSE = new int[]{3, 1, 1, 1, 1, 1, 1, 8};
        STOP_PATTERN = new int[]{7, 1, 1, 3, 1, 1, 1, SKEW_THRESHOLD, 1};
        STOP_PATTERN_REVERSE = new int[]{1, SKEW_THRESHOLD, 1, 1, 1, 3, 1, 1, 7};
    }

    public Detector(BinaryBitmap binaryBitmap) {
        this.image = binaryBitmap;
    }

    private static int computeDimension(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, float f) {
        return ((((round(ResultPoint.distance(resultPoint, resultPoint2) / f) + round(ResultPoint.distance(resultPoint3, resultPoint4) / f)) >> 1) + 8) / 17) * 17;
    }

    private static float computeModuleWidth(ResultPoint[] resultPointArr) {
        return (((ResultPoint.distance(resultPointArr[0], resultPointArr[4]) + ResultPoint.distance(resultPointArr[1], resultPointArr[5])) / 34.0f) + ((ResultPoint.distance(resultPointArr[6], resultPointArr[SKEW_THRESHOLD]) + ResultPoint.distance(resultPointArr[7], resultPointArr[3])) / 36.0f)) / 2.0f;
    }

    private static void correctCodeWordVertices(ResultPoint[] resultPointArr, boolean z) {
        float x;
        float y = resultPointArr[4].getY() - resultPointArr[6].getY();
        if (z) {
            y = -y;
        }
        if (y > 2.0f) {
            x = resultPointArr[6].getX() - resultPointArr[0].getX();
            resultPointArr[4] = new ResultPoint(resultPointArr[4].getX(), (((resultPointArr[4].getX() - resultPointArr[0].getX()) * (resultPointArr[6].getY() - resultPointArr[0].getY())) / x) + resultPointArr[4].getY());
        } else if ((-y) > 2.0f) {
            x = resultPointArr[SKEW_THRESHOLD].getX() - resultPointArr[4].getX();
            resultPointArr[6] = new ResultPoint(resultPointArr[6].getX(), resultPointArr[6].getY() - (((resultPointArr[SKEW_THRESHOLD].getX() - resultPointArr[6].getX()) * (resultPointArr[SKEW_THRESHOLD].getY() - resultPointArr[4].getY())) / x));
        }
        y = resultPointArr[7].getY() - resultPointArr[5].getY();
        if (z) {
            y = -y;
        }
        if (y > 2.0f) {
            x = resultPointArr[7].getX() - resultPointArr[1].getX();
            resultPointArr[5] = new ResultPoint(resultPointArr[5].getX(), (((resultPointArr[5].getX() - resultPointArr[1].getX()) * (resultPointArr[7].getY() - resultPointArr[1].getY())) / x) + resultPointArr[5].getY());
        } else if ((-y) > 2.0f) {
            x = resultPointArr[3].getX() - resultPointArr[5].getX();
            resultPointArr[7] = new ResultPoint(resultPointArr[7].getX(), resultPointArr[7].getY() - (((resultPointArr[3].getX() - resultPointArr[7].getX()) * (resultPointArr[3].getY() - resultPointArr[5].getY())) / x));
        }
    }

    private static int[] findGuardPattern(BitMatrix bitMatrix, int i, int i2, int i3, boolean z, int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        int i4 = 0;
        int i5 = i;
        int i6 = z;
        for (int i7 = i; i7 < i + i3; i7++) {
            if ((bitMatrix.get(i7, i2) ^ i6) != 0) {
                iArr2[i4] = iArr2[i4] + 1;
            } else {
                if (i4 != length - 1) {
                    i4++;
                } else if (patternMatchVariance(iArr2, iArr, MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                    int[] iArr3 = new int[SKEW_THRESHOLD];
                    iArr3[0] = i5;
                    iArr3[1] = i7;
                    return iArr3;
                } else {
                    i5 += iArr2[0] + iArr2[1];
                    for (int i8 = SKEW_THRESHOLD; i8 < length; i8++) {
                        iArr2[i8 - 2] = iArr2[i8];
                    }
                    iArr2[length - 2] = 0;
                    iArr2[length - 1] = 0;
                    i4--;
                }
                iArr2[i4] = 1;
                i6 = i6 == 0 ? 1 : 0;
            }
        }
        return null;
    }

    private static ResultPoint[] findVertices(BitMatrix bitMatrix) {
        int i;
        int i2;
        int i3 = 0;
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        ResultPoint[] resultPointArr = new ResultPoint[8];
        for (i = 0; i < height; i++) {
            int[] findGuardPattern = findGuardPattern(bitMatrix, 0, i, width, false, START_PATTERN);
            if (findGuardPattern != null) {
                resultPointArr[0] = new ResultPoint((float) findGuardPattern[0], (float) i);
                resultPointArr[4] = new ResultPoint((float) findGuardPattern[1], (float) i);
                i2 = 1;
                break;
            }
        }
        i2 = 0;
        if (i2 != 0) {
            for (i = height - 1; i > 0; i--) {
                findGuardPattern = findGuardPattern(bitMatrix, 0, i, width, false, START_PATTERN);
                if (findGuardPattern != null) {
                    resultPointArr[1] = new ResultPoint((float) findGuardPattern[0], (float) i);
                    resultPointArr[5] = new ResultPoint((float) findGuardPattern[1], (float) i);
                    i2 = 1;
                    break;
                }
            }
            i2 = 0;
        }
        if (i2 != 0) {
            for (i = 0; i < height; i++) {
                findGuardPattern = findGuardPattern(bitMatrix, 0, i, width, false, STOP_PATTERN);
                if (findGuardPattern != null) {
                    resultPointArr[SKEW_THRESHOLD] = new ResultPoint((float) findGuardPattern[1], (float) i);
                    resultPointArr[6] = new ResultPoint((float) findGuardPattern[0], (float) i);
                    i2 = 1;
                    break;
                }
            }
            i2 = 0;
        }
        if (i2 != 0) {
            for (i = height - 1; i > 0; i--) {
                findGuardPattern = findGuardPattern(bitMatrix, 0, i, width, false, STOP_PATTERN);
                if (findGuardPattern != null) {
                    resultPointArr[3] = new ResultPoint((float) findGuardPattern[1], (float) i);
                    resultPointArr[7] = new ResultPoint((float) findGuardPattern[0], (float) i);
                    i3 = 1;
                    break;
                }
            }
        } else {
            i3 = i2;
        }
        return i3 != 0 ? resultPointArr : null;
    }

    private static ResultPoint[] findVertices180(BitMatrix bitMatrix) {
        int i;
        boolean z;
        int i2;
        boolean z2 = true;
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth() >> 1;
        ResultPoint[] resultPointArr = new ResultPoint[8];
        for (i = height - 1; i > 0; i--) {
            int[] findGuardPattern = findGuardPattern(bitMatrix, width, i, width, true, START_PATTERN_REVERSE);
            if (findGuardPattern != null) {
                resultPointArr[0] = new ResultPoint((float) findGuardPattern[1], (float) i);
                resultPointArr[4] = new ResultPoint((float) findGuardPattern[0], (float) i);
                z = true;
                break;
            }
        }
        z = false;
        if (z) {
            for (i = 0; i < height; i++) {
                findGuardPattern = findGuardPattern(bitMatrix, width, i, width, true, START_PATTERN_REVERSE);
                if (findGuardPattern != null) {
                    resultPointArr[1] = new ResultPoint((float) findGuardPattern[1], (float) i);
                    resultPointArr[5] = new ResultPoint((float) findGuardPattern[0], (float) i);
                    z = true;
                    break;
                }
            }
            z = false;
        }
        if (z) {
            for (i2 = height - 1; i2 > 0; i2--) {
                findGuardPattern = findGuardPattern(bitMatrix, 0, i2, width, false, STOP_PATTERN_REVERSE);
                if (findGuardPattern != null) {
                    resultPointArr[SKEW_THRESHOLD] = new ResultPoint((float) findGuardPattern[0], (float) i2);
                    resultPointArr[6] = new ResultPoint((float) findGuardPattern[1], (float) i2);
                    z = true;
                    break;
                }
            }
            z = false;
        }
        if (z) {
            for (i2 = 0; i2 < height; i2++) {
                findGuardPattern = findGuardPattern(bitMatrix, 0, i2, width, false, STOP_PATTERN_REVERSE);
                if (findGuardPattern != null) {
                    resultPointArr[3] = new ResultPoint((float) findGuardPattern[0], (float) i2);
                    resultPointArr[7] = new ResultPoint((float) findGuardPattern[1], (float) i2);
                    break;
                }
            }
            z2 = false;
        } else {
            z2 = z;
        }
        return z2 ? resultPointArr : null;
    }

    private static int patternMatchVariance(int[] iArr, int[] iArr2, int i) {
        int i2;
        int length = iArr.length;
        int i3 = 0;
        int i4 = 0;
        for (i2 = 0; i2 < length; i2++) {
            i4 += iArr[i2];
            i3 += iArr2[i2];
        }
        if (i4 < i3) {
            return Integer.MAX_VALUE;
        }
        int i5 = (i4 << 8) / i3;
        int i6 = (i * i5) >> 8;
        i3 = 0;
        for (i2 = 0; i2 < length; i2++) {
            int i7 = iArr[i2] << 8;
            int i8 = iArr2[i2] * i5;
            i7 = i7 > i8 ? i7 - i8 : i8 - i7;
            if (i7 > i6) {
                return Integer.MAX_VALUE;
            }
            i3 += i7;
        }
        return i3 / i4;
    }

    private static int round(float f) {
        return (int) (0.5f + f);
    }

    private static BitMatrix sampleGrid(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i) {
        return GridSampler.getInstance().sampleGrid(bitMatrix, i, 0.0f, 0.0f, (float) i, 0.0f, (float) i, (float) i, 0.0f, (float) i, resultPoint.getX(), resultPoint.getY(), resultPoint3.getX(), resultPoint3.getY(), resultPoint4.getX(), resultPoint4.getY(), resultPoint2.getX(), resultPoint2.getY());
    }

    public DetectorResult detect() {
        return detect(null);
    }

    public DetectorResult detect(Hashtable hashtable) {
        ResultPoint[] resultPointArr;
        BitMatrix blackMatrix = this.image.getBlackMatrix();
        ResultPoint[] findVertices = findVertices(blackMatrix);
        if (findVertices == null) {
            findVertices = findVertices180(blackMatrix);
            if (findVertices != null) {
                correctCodeWordVertices(findVertices, true);
                resultPointArr = findVertices;
            }
            resultPointArr = findVertices;
        } else {
            correctCodeWordVertices(findVertices, false);
            resultPointArr = findVertices;
        }
        if (resultPointArr == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        float computeModuleWidth = computeModuleWidth(resultPointArr);
        if (computeModuleWidth < C2020f.f10933c) {
            throw NotFoundException.getNotFoundInstance();
        }
        int computeDimension = computeDimension(resultPointArr[4], resultPointArr[6], resultPointArr[5], resultPointArr[7], computeModuleWidth);
        if (computeDimension < 1) {
            throw NotFoundException.getNotFoundInstance();
        }
        return new DetectorResult(sampleGrid(blackMatrix, resultPointArr[4], resultPointArr[5], resultPointArr[6], resultPointArr[7], computeDimension), new ResultPoint[]{resultPointArr[4], resultPointArr[5], resultPointArr[6], resultPointArr[7]});
    }
}
