package com.google.zxing.qrcode.detector;

import com.fimi.soul.view.photodraweeview.C2020f;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.PerspectiveTransform;
import com.google.zxing.qrcode.decoder.Version;
import java.util.Hashtable;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class Detector {
    private final BitMatrix image;
    private ResultPointCallback resultPointCallback;

    public Detector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    private float calculateModuleSizeOneWay(ResultPoint resultPoint, ResultPoint resultPoint2) {
        float sizeOfBlackWhiteBlackRunBothWays = sizeOfBlackWhiteBlackRunBothWays((int) resultPoint.getX(), (int) resultPoint.getY(), (int) resultPoint2.getX(), (int) resultPoint2.getY());
        float sizeOfBlackWhiteBlackRunBothWays2 = sizeOfBlackWhiteBlackRunBothWays((int) resultPoint2.getX(), (int) resultPoint2.getY(), (int) resultPoint.getX(), (int) resultPoint.getY());
        return Float.isNaN(sizeOfBlackWhiteBlackRunBothWays) ? sizeOfBlackWhiteBlackRunBothWays2 / 7.0f : Float.isNaN(sizeOfBlackWhiteBlackRunBothWays2) ? sizeOfBlackWhiteBlackRunBothWays / 7.0f : (sizeOfBlackWhiteBlackRunBothWays + sizeOfBlackWhiteBlackRunBothWays2) / 14.0f;
    }

    protected static int computeDimension(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, float f) {
        int round = ((round(ResultPoint.distance(resultPoint, resultPoint2) / f) + round(ResultPoint.distance(resultPoint, resultPoint3) / f)) >> 1) + 7;
        switch (round & 3) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return round + 1;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return round - 1;
            case Type.BYTE /*3*/:
                throw NotFoundException.getNotFoundInstance();
            default:
                return round;
        }
    }

    private static int round(float f) {
        return (int) (0.5f + f);
    }

    private static BitMatrix sampleGrid(BitMatrix bitMatrix, PerspectiveTransform perspectiveTransform, int i) {
        return GridSampler.getInstance().sampleGrid(bitMatrix, i, perspectiveTransform);
    }

    private float sizeOfBlackWhiteBlackRun(int i, int i2, int i3, int i4) {
        Object obj = Math.abs(i4 - i2) > Math.abs(i3 - i) ? 1 : null;
        if (obj == null) {
            int i5 = i4;
            i4 = i3;
            i3 = i5;
            int i6 = i2;
            i2 = i;
            i = i6;
        }
        int abs = Math.abs(i4 - i2);
        int abs2 = Math.abs(i3 - i);
        int i7 = (-abs) >> 1;
        int i8 = i < i3 ? 1 : -1;
        int i9 = i2 < i4 ? 1 : -1;
        int i10 = 0;
        int i11 = i2;
        int i12 = i7;
        i7 = i;
        while (i11 != i4) {
            int i13 = obj != null ? i7 : i11;
            int i14 = obj != null ? i11 : i7;
            if (i10 == 1) {
                if (this.image.get(i13, i14)) {
                    i13 = i10 + 1;
                }
                i13 = i10;
            } else {
                if (!this.image.get(i13, i14)) {
                    i13 = i10 + 1;
                }
                i13 = i10;
            }
            if (i13 == 3) {
                i10 = i11 - i2;
                i7 -= i;
                i9 = i9 < 0 ? i10 + 1 : i10;
                return (float) Math.sqrt((double) ((i9 * i9) + (i7 * i7)));
            }
            i14 = i12 + abs2;
            if (i14 <= 0) {
                i10 = i7;
                i7 = i14;
            } else if (i7 == i3) {
                break;
            } else {
                i10 = i7 + i8;
                i7 = i14 - abs;
            }
            i11 += i9;
            i12 = i7;
            i7 = i10;
            i10 = i13;
        }
        i9 = i4 - i2;
        i10 = i3 - i;
        return (float) Math.sqrt((double) ((i9 * i9) + (i10 * i10)));
    }

    private float sizeOfBlackWhiteBlackRunBothWays(int i, int i2, int i3, int i4) {
        float f;
        int i5;
        int i6 = 0;
        float sizeOfBlackWhiteBlackRun = sizeOfBlackWhiteBlackRun(i, i2, i3, i4);
        int i7 = i - (i3 - i);
        if (i7 < 0) {
            f = ((float) i) / ((float) (i - i7));
            i5 = 0;
        } else if (i7 > this.image.getWidth()) {
            float width = ((float) (this.image.getWidth() - i)) / ((float) (i7 - i));
            f = width;
            i5 = this.image.getWidth();
        } else {
            i5 = i7;
            f = C2020f.f10933c;
        }
        i7 = (int) (((float) i2) - (f * ((float) (i4 - i2))));
        if (i7 < 0) {
            f = ((float) i2) / ((float) (i2 - i7));
        } else if (i7 > this.image.getHeight()) {
            f = ((float) (this.image.getHeight() - i2)) / ((float) (i7 - i2));
            i6 = this.image.getHeight();
        } else {
            i6 = i7;
            f = C2020f.f10933c;
        }
        return sizeOfBlackWhiteBlackRun(i, i2, (int) ((f * ((float) (i5 - i))) + ((float) i)), i6) + sizeOfBlackWhiteBlackRun;
    }

    protected float calculateModuleSize(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        return (calculateModuleSizeOneWay(resultPoint, resultPoint2) + calculateModuleSizeOneWay(resultPoint, resultPoint3)) / 2.0f;
    }

    public PerspectiveTransform createTransform(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i) {
        float x;
        float y;
        float f;
        float f2;
        float f3 = ((float) i) - 3.5f;
        if (resultPoint4 != null) {
            x = resultPoint4.getX();
            y = resultPoint4.getY();
            f = f3 - C2020f.f10931a;
            f2 = f;
        } else {
            x = (resultPoint2.getX() - resultPoint.getX()) + resultPoint3.getX();
            y = (resultPoint2.getY() - resultPoint.getY()) + resultPoint3.getY();
            f = f3;
            f2 = f3;
        }
        return PerspectiveTransform.quadrilateralToQuadrilateral(3.5f, 3.5f, f3, 3.5f, f2, f, 3.5f, f3, resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY(), x, y, resultPoint3.getX(), resultPoint3.getY());
    }

    public DetectorResult detect() {
        return detect(null);
    }

    public DetectorResult detect(Hashtable hashtable) {
        this.resultPointCallback = hashtable == null ? null : (ResultPointCallback) hashtable.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        return processFinderPatternInfo(new FinderPatternFinder(this.image, this.resultPointCallback).find(hashtable));
    }

    protected AlignmentPattern findAlignmentInRegion(float f, int i, int i2, float f2) {
        int i3 = (int) (f2 * f);
        int max = Math.max(0, i - i3);
        int min = Math.min(this.image.getWidth() - 1, i + i3);
        if (((float) (min - max)) < f * C2020f.f10931a) {
            throw NotFoundException.getNotFoundInstance();
        }
        int max2 = Math.max(0, i2 - i3);
        int min2 = Math.min(this.image.getHeight() - 1, i3 + i2);
        if (((float) (min2 - max2)) < f * C2020f.f10931a) {
            throw NotFoundException.getNotFoundInstance();
        }
        return new AlignmentPatternFinder(this.image, max, max2, min - max, min2 - max2, f, this.resultPointCallback).find();
    }

    protected BitMatrix getImage() {
        return this.image;
    }

    protected ResultPointCallback getResultPointCallback() {
        return this.resultPointCallback;
    }

    protected DetectorResult processFinderPatternInfo(FinderPatternInfo finderPatternInfo) {
        ResultPoint topLeft = finderPatternInfo.getTopLeft();
        ResultPoint topRight = finderPatternInfo.getTopRight();
        ResultPoint bottomLeft = finderPatternInfo.getBottomLeft();
        float calculateModuleSize = calculateModuleSize(topLeft, topRight, bottomLeft);
        if (calculateModuleSize < C2020f.f10933c) {
            throw NotFoundException.getNotFoundInstance();
        }
        int computeDimension = computeDimension(topLeft, topRight, bottomLeft, calculateModuleSize);
        Version provisionalVersionForDimension = Version.getProvisionalVersionForDimension(computeDimension);
        int dimensionForVersion = provisionalVersionForDimension.getDimensionForVersion() - 7;
        ResultPoint resultPoint = null;
        if (provisionalVersionForDimension.getAlignmentPatternCenters().length > 0) {
            float f = C2020f.f10933c - (C2020f.f10931a / ((float) dimensionForVersion));
            int x = (int) (((((topRight.getX() - topLeft.getX()) + bottomLeft.getX()) - topLeft.getX()) * f) + topLeft.getX());
            dimensionForVersion = (int) (topLeft.getY() + (f * (((topRight.getY() - topLeft.getY()) + bottomLeft.getY()) - topLeft.getY())));
            int i = 4;
            while (i <= 16) {
                try {
                    resultPoint = findAlignmentInRegion(calculateModuleSize, x, dimensionForVersion, (float) i);
                    break;
                } catch (NotFoundException e) {
                    i <<= 1;
                }
            }
        }
        return new DetectorResult(sampleGrid(this.image, createTransform(topLeft, topRight, bottomLeft, resultPoint, computeDimension), computeDimension), resultPoint == null ? new ResultPoint[]{bottomLeft, topLeft, topRight} : new ResultPoint[]{bottomLeft, topLeft, topRight, resultPoint});
    }
}
