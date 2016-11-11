package com.google.zxing.multi.qrcode.detector;

import com.fimi.soul.view.photodraweeview.C2020f;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.Collections;
import com.google.zxing.common.Comparator;
import com.google.zxing.qrcode.detector.FinderPattern;
import com.google.zxing.qrcode.detector.FinderPatternFinder;
import com.google.zxing.qrcode.detector.FinderPatternInfo;
import java.util.Hashtable;
import java.util.Vector;

final class MultiFinderPatternFinder extends FinderPatternFinder {
    private static final float DIFF_MODSIZE_CUTOFF = 0.5f;
    private static final float DIFF_MODSIZE_CUTOFF_PERCENT = 0.05f;
    private static final FinderPatternInfo[] EMPTY_RESULT_ARRAY;
    private static final float MAX_MODULE_COUNT_PER_EDGE = 180.0f;
    private static final float MIN_MODULE_COUNT_PER_EDGE = 9.0f;

    /* renamed from: com.google.zxing.multi.qrcode.detector.MultiFinderPatternFinder.1 */
    class C21031 {
    }

    class ModuleSizeComparator implements Comparator {
        private ModuleSizeComparator() {
        }

        ModuleSizeComparator(C21031 c21031) {
            this();
        }

        public int compare(Object obj, Object obj2) {
            float estimatedModuleSize = ((FinderPattern) obj2).getEstimatedModuleSize() - ((FinderPattern) obj).getEstimatedModuleSize();
            return ((double) estimatedModuleSize) < 0.0d ? -1 : ((double) estimatedModuleSize) > 0.0d ? 1 : 0;
        }
    }

    static {
        EMPTY_RESULT_ARRAY = new FinderPatternInfo[0];
    }

    MultiFinderPatternFinder(BitMatrix bitMatrix) {
        super(bitMatrix);
    }

    MultiFinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        super(bitMatrix, resultPointCallback);
    }

    private FinderPattern[][] selectBestPatterns() {
        Vector possibleCenters = getPossibleCenters();
        int size = possibleCenters.size();
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        } else if (size == 3) {
            FinderPattern[][] finderPatternArr = new FinderPattern[1][];
            finderPatternArr[0] = new FinderPattern[]{(FinderPattern) possibleCenters.elementAt(0), (FinderPattern) possibleCenters.elementAt(1), (FinderPattern) possibleCenters.elementAt(2)};
            return finderPatternArr;
        } else {
            Collections.insertionSort(possibleCenters, new ModuleSizeComparator(null));
            Vector vector = new Vector();
            for (int i = 0; i < size - 2; i++) {
                FinderPattern finderPattern = (FinderPattern) possibleCenters.elementAt(i);
                if (finderPattern != null) {
                    for (int i2 = i + 1; i2 < size - 1; i2++) {
                        FinderPattern finderPattern2 = (FinderPattern) possibleCenters.elementAt(i2);
                        if (finderPattern2 != null) {
                            float estimatedModuleSize = (finderPattern.getEstimatedModuleSize() - finderPattern2.getEstimatedModuleSize()) / Math.min(finderPattern.getEstimatedModuleSize(), finderPattern2.getEstimatedModuleSize());
                            if (Math.abs(finderPattern.getEstimatedModuleSize() - finderPattern2.getEstimatedModuleSize()) > DIFF_MODSIZE_CUTOFF && estimatedModuleSize >= DIFF_MODSIZE_CUTOFF_PERCENT) {
                                break;
                            }
                            for (int i3 = i2 + 1; i3 < size; i3++) {
                                FinderPattern finderPattern3 = (FinderPattern) possibleCenters.elementAt(i3);
                                if (finderPattern3 != null) {
                                    float estimatedModuleSize2 = (finderPattern2.getEstimatedModuleSize() - finderPattern3.getEstimatedModuleSize()) / Math.min(finderPattern2.getEstimatedModuleSize(), finderPattern3.getEstimatedModuleSize());
                                    if (Math.abs(finderPattern2.getEstimatedModuleSize() - finderPattern3.getEstimatedModuleSize()) > DIFF_MODSIZE_CUTOFF && estimatedModuleSize2 >= DIFF_MODSIZE_CUTOFF_PERCENT) {
                                        break;
                                    }
                                    Object obj = new FinderPattern[]{finderPattern, finderPattern2, finderPattern3};
                                    ResultPoint.orderBestPatterns(obj);
                                    FinderPatternInfo finderPatternInfo = new FinderPatternInfo(obj);
                                    float distance = ResultPoint.distance(finderPatternInfo.getTopLeft(), finderPatternInfo.getBottomLeft());
                                    float distance2 = ResultPoint.distance(finderPatternInfo.getTopRight(), finderPatternInfo.getBottomLeft());
                                    estimatedModuleSize = ResultPoint.distance(finderPatternInfo.getTopLeft(), finderPatternInfo.getTopRight());
                                    float estimatedModuleSize3 = ((distance + estimatedModuleSize) / finderPattern.getEstimatedModuleSize()) / 2.0f;
                                    if (estimatedModuleSize3 <= MAX_MODULE_COUNT_PER_EDGE && estimatedModuleSize3 >= MIN_MODULE_COUNT_PER_EDGE && Math.abs((distance - estimatedModuleSize) / Math.min(distance, estimatedModuleSize)) < 0.1f) {
                                        estimatedModuleSize = (float) Math.sqrt((double) ((estimatedModuleSize * estimatedModuleSize) + (distance * distance)));
                                        if (Math.abs((distance2 - estimatedModuleSize) / Math.min(distance2, estimatedModuleSize)) < 0.1f) {
                                            vector.addElement(obj);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (vector.isEmpty()) {
                throw NotFoundException.getNotFoundInstance();
            }
            FinderPattern[][] finderPatternArr2 = new FinderPattern[vector.size()][];
            for (int i4 = 0; i4 < vector.size(); i4++) {
                finderPatternArr2[i4] = (FinderPattern[]) vector.elementAt(i4);
            }
            return finderPatternArr2;
        }
    }

    public FinderPatternInfo[] findMulti(Hashtable hashtable) {
        int i = 0;
        int i2 = (hashtable == null || !hashtable.containsKey(DecodeHintType.TRY_HARDER)) ? 0 : 1;
        BitMatrix image = getImage();
        int height = image.getHeight();
        int width = image.getWidth();
        int i3 = (int) ((((float) height) / 228.0f) * C2020f.f10931a);
        i2 = (i3 < 3 || i2 != 0) ? 3 : i3;
        int[] iArr = new int[5];
        for (int i4 = i2 - 1; i4 < height; i4 += i2) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            int i5 = 0;
            i3 = 0;
            while (i5 < width) {
                if (image.get(i5, i4)) {
                    if ((i3 & 1) == 1) {
                        i3++;
                    }
                    iArr[i3] = iArr[i3] + 1;
                } else if ((i3 & 1) != 0) {
                    iArr[i3] = iArr[i3] + 1;
                } else if (i3 != 4) {
                    i3++;
                    iArr[i3] = iArr[i3] + 1;
                } else if (FinderPatternFinder.foundPatternCross(iArr)) {
                    if (handlePossibleCenter(iArr, i4, i5)) {
                        i3 = i5;
                    } else {
                        do {
                            i5++;
                            if (i5 >= width) {
                                break;
                            }
                        } while (!image.get(i5, i4));
                        i3 = i5 - 1;
                    }
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    iArr[3] = 0;
                    iArr[4] = 0;
                    i5 = i3;
                    i3 = 0;
                } else {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i3 = 3;
                }
                i5++;
            }
            if (FinderPatternFinder.foundPatternCross(iArr)) {
                handlePossibleCenter(iArr, i4, width);
            }
        }
        FinderPattern[][] selectBestPatterns = selectBestPatterns();
        Vector vector = new Vector();
        for (ResultPoint[] resultPointArr : selectBestPatterns) {
            ResultPoint.orderBestPatterns(resultPointArr);
            vector.addElement(new FinderPatternInfo(resultPointArr));
        }
        if (vector.isEmpty()) {
            return EMPTY_RESULT_ARRAY;
        }
        FinderPatternInfo[] finderPatternInfoArr = new FinderPatternInfo[vector.size()];
        while (i < vector.size()) {
            finderPatternInfoArr[i] = (FinderPatternInfo) vector.elementAt(i);
            i++;
        }
        return finderPatternInfoArr;
    }
}
