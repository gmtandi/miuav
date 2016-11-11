package com.google.zxing.oned.rss;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.OneDReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public final class RSS14Reader extends AbstractRSSReader {
    private static final int[][] FINDER_PATTERNS;
    private static final int[] INSIDE_GSUM;
    private static final int[] INSIDE_ODD_TOTAL_SUBSET;
    private static final int[] INSIDE_ODD_WIDEST;
    private static final int[] OUTSIDE_EVEN_TOTAL_SUBSET;
    private static final int[] OUTSIDE_GSUM;
    private static final int[] OUTSIDE_ODD_WIDEST;
    private final Vector possibleLeftPairs;
    private final Vector possibleRightPairs;

    static {
        OUTSIDE_EVEN_TOTAL_SUBSET = new int[]{1, 10, 34, 70, Opcodes.IAND};
        INSIDE_ODD_TOTAL_SUBSET = new int[]{4, 20, 48, 81};
        OUTSIDE_GSUM = new int[]{0, Opcodes.IF_ICMPLT, 961, 2015, 2715};
        INSIDE_GSUM = new int[]{0, 336, 1036, 1516};
        OUTSIDE_ODD_WIDEST = new int[]{8, 6, 4, 3, 1};
        INSIDE_ODD_WIDEST = new int[]{2, 4, 6, 8};
        FINDER_PATTERNS = new int[][]{new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};
    }

    public RSS14Reader() {
        this.possibleLeftPairs = new Vector();
        this.possibleRightPairs = new Vector();
    }

    private static void addOrTally(Vector vector, Pair pair) {
        if (pair != null) {
            Object obj;
            Enumeration elements = vector.elements();
            while (elements.hasMoreElements()) {
                Pair pair2 = (Pair) elements.nextElement();
                if (pair2.getValue() == pair.getValue()) {
                    pair2.incrementCount();
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj == null) {
                vector.addElement(pair);
            }
        }
    }

    private void adjustOddEvenCounts(boolean z, int i) {
        Object obj;
        Object obj2;
        Object obj3;
        int i2;
        Object obj4 = null;
        Object obj5 = 1;
        int count = AbstractRSSReader.count(this.oddCounts);
        int count2 = AbstractRSSReader.count(this.evenCounts);
        int i3 = (count + count2) - i;
        Object obj6 = (count & 1) == (z ? 1 : 0) ? 1 : null;
        Object obj7 = (count2 & 1) == 1 ? 1 : null;
        int i4;
        if (z) {
            if (count > 12) {
                obj = 1;
                obj2 = null;
            } else if (count < 4) {
                obj = null;
                i4 = 1;
            } else {
                obj = null;
                obj2 = null;
            }
            if (count2 > 12) {
                obj3 = null;
                obj4 = 1;
            } else {
                if (count2 < 4) {
                    i2 = 1;
                }
                obj3 = null;
            }
        } else {
            if (count > 11) {
                obj = 1;
                obj2 = null;
            } else if (count < 5) {
                obj = null;
                i4 = 1;
            } else {
                obj = null;
                obj2 = null;
            }
            if (count2 > 10) {
                obj3 = null;
                int i5 = 1;
            } else {
                if (count2 < 4) {
                    i2 = 1;
                }
                obj3 = null;
            }
        }
        if (i3 == 1) {
            if (obj6 != null) {
                if (obj7 != null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                obj = obj2;
                obj5 = obj3;
                obj3 = 1;
            } else if (obj7 == null) {
                throw NotFoundException.getNotFoundInstance();
            } else {
                i5 = 1;
                obj5 = obj3;
                obj3 = obj;
                obj = obj2;
            }
        } else if (i3 == -1) {
            if (obj6 != null) {
                if (obj7 != null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                r12 = obj3;
                obj3 = obj;
                r3 = 1;
                obj5 = r12;
            } else if (obj7 == null) {
                throw NotFoundException.getNotFoundInstance();
            } else {
                obj3 = obj;
                obj = obj2;
            }
        } else if (i3 != 0) {
            throw NotFoundException.getNotFoundInstance();
        } else if (obj6 != null) {
            if (obj7 == null) {
                throw NotFoundException.getNotFoundInstance();
            } else if (count < count2) {
                i5 = 1;
                r12 = obj3;
                obj3 = obj;
                r3 = 1;
                obj5 = r12;
            } else {
                i2 = 1;
                obj = obj2;
            }
        } else if (obj7 != null) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            obj5 = obj3;
            obj3 = obj;
            obj = obj2;
        }
        if (obj != null) {
            if (obj3 != null) {
                throw NotFoundException.getNotFoundInstance();
            }
            AbstractRSSReader.increment(this.oddCounts, this.oddRoundingErrors);
        }
        if (obj3 != null) {
            AbstractRSSReader.decrement(this.oddCounts, this.oddRoundingErrors);
        }
        if (obj5 != null) {
            if (obj4 != null) {
                throw NotFoundException.getNotFoundInstance();
            }
            AbstractRSSReader.increment(this.evenCounts, this.oddRoundingErrors);
        }
        if (obj4 != null) {
            AbstractRSSReader.decrement(this.evenCounts, this.evenRoundingErrors);
        }
    }

    private static boolean checkChecksum(Pair pair, Pair pair2) {
        int value = pair.getFinderPattern().getValue();
        int value2 = pair2.getFinderPattern().getValue();
        if (!(value == 0 && value2 == 8) && value == 8 && value2 == 0) {
            value2 = (pair.getChecksumPortion() + (pair2.getChecksumPortion() * 16)) % 79;
            value = (pair.getFinderPattern().getValue() * 9) + pair2.getFinderPattern().getValue();
        } else {
            value2 = (pair.getChecksumPortion() + (pair2.getChecksumPortion() * 16)) % 79;
            value = (pair.getFinderPattern().getValue() * 9) + pair2.getFinderPattern().getValue();
        }
        if (value > 72) {
            value--;
        }
        if (value > 8) {
            value--;
        }
        return value2 == value;
    }

    private static Result constructResult(Pair pair, Pair pair2) {
        int length;
        String valueOf = String.valueOf((4537077 * ((long) pair.getValue())) + ((long) pair2.getValue()));
        StringBuffer stringBuffer = new StringBuffer(14);
        for (length = 13 - valueOf.length(); length > 0; length--) {
            stringBuffer.append('0');
        }
        stringBuffer.append(valueOf);
        int i = 0;
        for (int i2 = 0; i2 < 13; i2++) {
            length = stringBuffer.charAt(i2) - 48;
            if ((i2 & 1) == 0) {
                length *= 3;
            }
            i += length;
        }
        length = 10 - (i % 10);
        if (length == 10) {
            length = 0;
        }
        stringBuffer.append(length);
        ResultPoint[] resultPoints = pair.getFinderPattern().getResultPoints();
        ResultPoint[] resultPoints2 = pair2.getFinderPattern().getResultPoints();
        return new Result(String.valueOf(stringBuffer.toString()), null, new ResultPoint[]{resultPoints[0], resultPoints[1], resultPoints2[0], resultPoints2[1]}, BarcodeFormat.RSS14);
    }

    private DataCharacter decodeDataCharacter(BitArray bitArray, FinderPattern finderPattern, boolean z) {
        int i;
        int length;
        int i2;
        int[] iArr = this.dataCharacterCounters;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
        iArr[6] = 0;
        iArr[7] = 0;
        if (z) {
            OneDReader.recordPatternInReverse(bitArray, finderPattern.getStartEnd()[0], iArr);
        } else {
            OneDReader.recordPattern(bitArray, finderPattern.getStartEnd()[1] + 1, iArr);
            i = 0;
            for (length = iArr.length - 1; i < length; length--) {
                i2 = iArr[i];
                iArr[i] = iArr[length];
                iArr[length] = i2;
                i++;
            }
        }
        length = z ? 16 : 15;
        float count = ((float) AbstractRSSReader.count(iArr)) / ((float) length);
        int[] iArr2 = this.oddCounts;
        int[] iArr3 = this.evenCounts;
        float[] fArr = this.oddRoundingErrors;
        float[] fArr2 = this.evenRoundingErrors;
        for (i = 0; i < iArr.length; i++) {
            float f = ((float) iArr[i]) / count;
            i2 = (int) (0.5f + f);
            if (i2 < 1) {
                i2 = 1;
            } else if (i2 > 8) {
                i2 = 8;
            }
            int i3 = i >> 1;
            if ((i & 1) == 0) {
                iArr2[i3] = i2;
                fArr[i3] = f - ((float) i2);
            } else {
                iArr3[i3] = i2;
                fArr2[i3] = f - ((float) i2);
            }
        }
        adjustOddEvenCounts(z, length);
        length = iArr2.length - 1;
        int i4 = 0;
        int i5 = 0;
        while (length >= 0) {
            i = (i4 * 9) + iArr2[length];
            length--;
            i4 = i;
            i5 = iArr2[length] + i5;
        }
        i2 = 0;
        i = 0;
        for (length = iArr3.length - 1; length >= 0; length--) {
            i2 = (i2 * 9) + iArr3[length];
            i += iArr3[length];
        }
        i2 = i4 + (i2 * 3);
        if (z) {
            if ((i5 & 1) != 0 || i5 > 12 || i5 < 4) {
                throw NotFoundException.getNotFoundInstance();
            }
            length = (12 - i5) / 2;
            i = OUTSIDE_ODD_WIDEST[length];
            i4 = 9 - i;
            return new DataCharacter(((RSSUtils.getRSSvalue(iArr2, i, false) * OUTSIDE_EVEN_TOTAL_SUBSET[length]) + RSSUtils.getRSSvalue(iArr3, i4, true)) + OUTSIDE_GSUM[length], i2);
        } else if ((i & 1) != 0 || i > 10 || i < 4) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            length = (10 - i) / 2;
            i = INSIDE_ODD_WIDEST[length];
            return new DataCharacter((RSSUtils.getRSSvalue(iArr2, i, true) + (RSSUtils.getRSSvalue(iArr3, 9 - i, false) * INSIDE_ODD_TOTAL_SUBSET[length])) + INSIDE_GSUM[length], i2);
        }
    }

    private Pair decodePair(BitArray bitArray, boolean z, int i, Hashtable hashtable) {
        try {
            int[] findFinderPattern = findFinderPattern(bitArray, 0, z);
            FinderPattern parseFoundFinderPattern = parseFoundFinderPattern(bitArray, i, z, findFinderPattern);
            ResultPointCallback resultPointCallback = hashtable == null ? null : (ResultPointCallback) hashtable.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
            if (resultPointCallback != null) {
                float f = ((float) (findFinderPattern[0] + findFinderPattern[1])) / 2.0f;
                if (z) {
                    f = ((float) (bitArray.getSize() - 1)) - f;
                }
                resultPointCallback.foundPossibleResultPoint(new ResultPoint(f, (float) i));
            }
            DataCharacter decodeDataCharacter = decodeDataCharacter(bitArray, parseFoundFinderPattern, true);
            DataCharacter decodeDataCharacter2 = decodeDataCharacter(bitArray, parseFoundFinderPattern, false);
            return new Pair((decodeDataCharacter.getValue() * 1597) + decodeDataCharacter2.getValue(), decodeDataCharacter.getChecksumPortion() + (decodeDataCharacter2.getChecksumPortion() * 4), parseFoundFinderPattern);
        } catch (NotFoundException e) {
            return null;
        }
    }

    private int[] findFinderPattern(BitArray bitArray, int i, boolean z) {
        int[] iArr = this.decodeFinderCounters;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        int size = bitArray.getSize();
        boolean z2 = false;
        int i2 = i;
        while (i2 < size) {
            z2 = !bitArray.get(i2);
            if (z == z2) {
                break;
            }
            i2++;
        }
        int i3 = z2;
        int i4 = i2;
        i2 = 0;
        for (int i5 = i2; i5 < size; i5++) {
            if ((bitArray.get(i5) ^ i3) != 0) {
                iArr[i2] = iArr[i2] + 1;
            } else {
                if (i2 != 3) {
                    i2++;
                } else if (AbstractRSSReader.isFinderPattern(iArr)) {
                    return new int[]{i4, i5};
                } else {
                    i4 += iArr[0] + iArr[1];
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = 0;
                    iArr[3] = 0;
                    i2--;
                }
                iArr[i2] = 1;
                i3 = i3 == 0 ? 1 : 0;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitArray, int i, boolean z, int[] iArr) {
        int size;
        boolean z2 = bitArray.get(iArr[0]);
        int i2 = iArr[0] - 1;
        while (i2 >= 0 && (bitArray.get(i2) ^ z2) != 0) {
            i2--;
        }
        int i3 = i2 + 1;
        int i4 = iArr[0] - i3;
        int[] iArr2 = this.decodeFinderCounters;
        for (i2 = iArr2.length - 1; i2 > 0; i2--) {
            iArr2[i2] = iArr2[i2 - 1];
        }
        iArr2[0] = i4;
        i4 = AbstractRSSReader.parseFinderValue(iArr2, FINDER_PATTERNS);
        int i5 = iArr[1];
        if (z) {
            size = (bitArray.getSize() - 1) - i3;
            i5 = (bitArray.getSize() - 1) - i5;
        } else {
            size = i3;
        }
        return new FinderPattern(i4, new int[]{i3, iArr[1]}, size, i5, i);
    }

    public Result decodeRow(int i, BitArray bitArray, Hashtable hashtable) {
        addOrTally(this.possibleLeftPairs, decodePair(bitArray, false, i, hashtable));
        bitArray.reverse();
        addOrTally(this.possibleRightPairs, decodePair(bitArray, true, i, hashtable));
        bitArray.reverse();
        int size = this.possibleLeftPairs.size();
        int size2 = this.possibleRightPairs.size();
        for (int i2 = 0; i2 < size; i2++) {
            Pair pair = (Pair) this.possibleLeftPairs.elementAt(i2);
            if (pair.getCount() > 1) {
                for (int i3 = 0; i3 < size2; i3++) {
                    Pair pair2 = (Pair) this.possibleRightPairs.elementAt(i3);
                    if (pair2.getCount() > 1 && checkChecksum(pair, pair2)) {
                        return constructResult(pair, pair2);
                    }
                }
                continue;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public void reset() {
        this.possibleLeftPairs.setSize(0);
        this.possibleRightPairs.setSize(0);
    }
}
