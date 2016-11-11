package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int[][] CODE_PATTERNS;
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final int MAX_AVG_VARIANCE = 64;
    private static final int MAX_INDIVIDUAL_VARIANCE = 179;

    static {
        int[][] iArr = new int[Opcodes.DMUL][];
        iArr[0] = new int[]{2, 1, 2, 2, 2, 2};
        iArr[1] = new int[]{2, 2, 2, 1, 2, 2};
        iArr[2] = new int[]{2, 2, 2, 2, 2, 1};
        iArr[3] = new int[]{1, 2, 1, 2, 2, 3};
        iArr[4] = new int[]{1, 2, 1, 3, 2, 2};
        iArr[5] = new int[]{1, 3, 1, 2, 2, 2};
        iArr[6] = new int[]{1, 2, 2, 2, 1, 3};
        iArr[7] = new int[]{1, 2, 2, 3, 1, 2};
        iArr[8] = new int[]{1, 3, 2, 2, 1, 2};
        iArr[9] = new int[]{2, 2, 1, 2, 1, 3};
        iArr[10] = new int[]{2, 2, 1, 3, 1, 2};
        iArr[11] = new int[]{2, 3, 1, 2, 1, 2};
        iArr[12] = new int[]{1, 1, 2, 2, 3, 2};
        iArr[13] = new int[]{1, 2, 2, 1, 3, 2};
        iArr[14] = new int[]{1, 2, 2, 2, 3, 1};
        iArr[15] = new int[]{1, 1, 3, 2, 2, 2};
        iArr[16] = new int[]{1, 2, 3, 1, 2, 2};
        iArr[17] = new int[]{1, 2, 3, 2, 2, 1};
        iArr[18] = new int[]{2, 2, 3, 2, 1, 1};
        iArr[19] = new int[]{2, 2, 1, 1, 3, 2};
        iArr[20] = new int[]{2, 2, 1, 2, 3, 1};
        iArr[21] = new int[]{2, 1, 3, 2, 1, 2};
        iArr[22] = new int[]{2, 2, 3, 1, 1, 2};
        iArr[23] = new int[]{3, 1, 2, 1, 3, 1};
        iArr[24] = new int[]{3, 1, 1, 2, 2, 2};
        iArr[25] = new int[]{3, 2, 1, 1, 2, 2};
        iArr[26] = new int[]{3, 2, 1, 2, 2, 1};
        iArr[27] = new int[]{3, 1, 2, 2, 1, 2};
        iArr[28] = new int[]{3, 2, 2, 1, 1, 2};
        iArr[29] = new int[]{3, 2, 2, 2, 1, 1};
        iArr[30] = new int[]{2, 1, 2, 1, 2, 3};
        iArr[31] = new int[]{2, 1, 2, 3, 2, 1};
        iArr[32] = new int[]{2, 3, 2, 1, 2, 1};
        iArr[33] = new int[]{1, 1, 1, 3, 2, 3};
        iArr[34] = new int[]{1, 3, 1, 1, 2, 3};
        iArr[35] = new int[]{1, 3, 1, 3, 2, 1};
        iArr[36] = new int[]{1, 1, 2, 3, 1, 3};
        iArr[37] = new int[]{1, 3, 2, 1, 1, 3};
        iArr[38] = new int[]{1, 3, 2, 3, 1, 1};
        iArr[39] = new int[]{2, 1, 1, 3, 1, 3};
        iArr[40] = new int[]{2, 3, 1, 1, 1, 3};
        iArr[41] = new int[]{2, 3, 1, 3, 1, 1};
        iArr[42] = new int[]{1, 1, 2, 1, 3, 3};
        iArr[43] = new int[]{1, 1, 2, 3, 3, 1};
        iArr[44] = new int[]{1, 3, 2, 1, 3, 1};
        iArr[45] = new int[]{1, 1, 3, 1, 2, 3};
        iArr[46] = new int[]{1, 1, 3, 3, 2, 1};
        iArr[47] = new int[]{1, 3, 3, 1, 2, 1};
        iArr[48] = new int[]{3, 1, 3, 1, 2, 1};
        iArr[49] = new int[]{2, 1, 1, 3, 3, 1};
        iArr[50] = new int[]{2, 3, 1, 1, 3, 1};
        iArr[51] = new int[]{2, 1, 3, 1, 1, 3};
        iArr[52] = new int[]{2, 1, 3, 3, 1, 1};
        iArr[53] = new int[]{2, 1, 3, 1, 3, 1};
        iArr[54] = new int[]{3, 1, 1, 1, 2, 3};
        iArr[55] = new int[]{3, 1, 1, 3, 2, 1};
        iArr[56] = new int[]{3, 3, 1, 1, 2, 1};
        iArr[57] = new int[]{3, 1, 2, 1, 1, 3};
        iArr[58] = new int[]{3, 1, 2, 3, 1, 1};
        iArr[59] = new int[]{3, 3, 2, 1, 1, 1};
        iArr[60] = new int[]{3, 1, 4, 1, 1, 1};
        iArr[61] = new int[]{2, 2, 1, 4, 1, 1};
        iArr[62] = new int[]{4, 3, 1, 1, 1, 1};
        iArr[63] = new int[]{1, 1, 1, 2, 2, 4};
        iArr[MAX_AVG_VARIANCE] = new int[]{1, 1, 1, 4, 2, 2};
        iArr[65] = new int[]{1, 2, 1, 1, 2, 4};
        iArr[66] = new int[]{1, 2, 1, 4, 2, 1};
        iArr[67] = new int[]{1, 4, 1, 1, 2, 2};
        iArr[68] = new int[]{1, 4, 1, 2, 2, 1};
        iArr[69] = new int[]{1, 1, 2, 2, 1, 4};
        iArr[70] = new int[]{1, 1, 2, 4, 1, 2};
        iArr[71] = new int[]{1, 2, 2, 1, 1, 4};
        iArr[72] = new int[]{1, 2, 2, 4, 1, 1};
        iArr[73] = new int[]{1, 4, 2, 1, 1, 2};
        iArr[74] = new int[]{1, 4, 2, 2, 1, 1};
        iArr[75] = new int[]{2, 4, 1, 2, 1, 1};
        iArr[76] = new int[]{2, 2, 1, 1, 1, 4};
        iArr[77] = new int[]{4, 1, 3, 1, 1, 1};
        iArr[78] = new int[]{2, 4, 1, 1, 1, 2};
        iArr[79] = new int[]{1, 3, 4, 1, 1, 1};
        iArr[80] = new int[]{1, 1, 1, 2, 4, 2};
        iArr[81] = new int[]{1, 2, 1, 1, 4, 2};
        iArr[82] = new int[]{1, 2, 1, 2, 4, 1};
        iArr[83] = new int[]{1, 1, 4, 2, 1, 2};
        iArr[84] = new int[]{1, 2, 4, 1, 1, 2};
        iArr[85] = new int[]{1, 2, 4, 2, 1, 1};
        iArr[86] = new int[]{4, 1, 1, 2, 1, 2};
        iArr[87] = new int[]{4, 2, 1, 1, 1, 2};
        iArr[88] = new int[]{4, 2, 1, 2, 1, 1};
        iArr[89] = new int[]{2, 1, 2, 1, 4, 1};
        iArr[90] = new int[]{2, 1, 4, 1, 2, 1};
        iArr[91] = new int[]{4, 1, 2, 1, 2, 1};
        iArr[92] = new int[]{1, 1, 1, 1, 4, 3};
        iArr[93] = new int[]{1, 1, 1, 3, 4, 1};
        iArr[94] = new int[]{1, 3, 1, 1, 4, 1};
        iArr[95] = new int[]{1, 1, 4, 1, 1, 3};
        iArr[CODE_FNC_3] = new int[]{1, 1, 4, 3, 1, 1};
        iArr[CODE_FNC_2] = new int[]{4, 1, 1, 1, 1, 3};
        iArr[CODE_SHIFT] = new int[]{4, 1, 1, 3, 1, 1};
        iArr[CODE_CODE_C] = new int[]{1, 1, 3, 1, 4, 1};
        iArr[CODE_FNC_4_B] = new int[]{1, 1, 4, 1, 3, 1};
        iArr[CODE_FNC_4_A] = new int[]{3, 1, 1, 1, 4, 1};
        iArr[CODE_FNC_1] = new int[]{4, 1, 1, 1, 3, 1};
        iArr[CODE_START_A] = new int[]{2, 1, 1, 4, 1, 2};
        iArr[CODE_START_B] = new int[]{2, 1, 1, 2, 1, 4};
        iArr[CODE_START_C] = new int[]{2, 1, 1, 2, 3, 2};
        iArr[CODE_STOP] = new int[]{2, 3, 3, 1, 1, 1, 2};
        CODE_PATTERNS = iArr;
    }

    private static int decodeCode(BitArray bitArray, int[] iArr, int i) {
        OneDReader.recordPattern(bitArray, i, iArr);
        int i2 = MAX_AVG_VARIANCE;
        int i3 = -1;
        for (int i4 = 0; i4 < CODE_PATTERNS.length; i4++) {
            int patternMatchVariance = OneDReader.patternMatchVariance(iArr, CODE_PATTERNS[i4], MAX_INDIVIDUAL_VARIANCE);
            if (patternMatchVariance < i2) {
                i3 = i4;
                i2 = patternMatchVariance;
            }
        }
        if (i3 >= 0) {
            return i3;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int[] findStartPattern(BitArray bitArray) {
        int size = bitArray.getSize();
        int i = 0;
        while (i < size && !bitArray.get(i)) {
            i++;
        }
        int[] iArr = new int[6];
        int length = iArr.length;
        int i2 = i;
        int i3 = 0;
        int i4 = i;
        int i5 = 0;
        while (i2 < size) {
            int i6;
            int i7;
            if ((bitArray.get(i2) ^ i3) != 0) {
                iArr[i5] = iArr[i5] + 1;
                i6 = i3;
                i7 = i5;
            } else {
                if (i5 == length - 1) {
                    int i8 = MAX_AVG_VARIANCE;
                    i = -1;
                    i7 = CODE_START_A;
                    while (i7 <= CODE_START_C) {
                        i6 = OneDReader.patternMatchVariance(iArr, CODE_PATTERNS[i7], MAX_INDIVIDUAL_VARIANCE);
                        if (i6 < i8) {
                            i = i7;
                        } else {
                            i6 = i8;
                        }
                        i7++;
                        i8 = i6;
                    }
                    if (i < 0 || !bitArray.isRange(Math.max(0, i4 - ((i2 - i4) / 2)), i4, false)) {
                        i = (iArr[0] + iArr[1]) + i4;
                        for (i7 = 2; i7 < length; i7++) {
                            iArr[i7 - 2] = iArr[i7];
                        }
                        iArr[length - 2] = 0;
                        iArr[length - 1] = 0;
                        i7 = i5 - 1;
                    } else {
                        return new int[]{i4, i2, i};
                    }
                }
                i7 = i5 + 1;
                i = i4;
                iArr[i7] = 1;
                if (i3 == 0) {
                    i6 = 1;
                } else {
                    boolean z = false;
                }
                i4 = i;
            }
            i2++;
            i3 = i6;
            i5 = i7;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public Result decodeRow(int i, BitArray bitArray, Hashtable hashtable) {
        int i2;
        int[] findStartPattern = findStartPattern(bitArray);
        int i3 = findStartPattern[2];
        switch (i3) {
            case CODE_START_A /*103*/:
                i2 = CODE_FNC_4_A;
                break;
            case CODE_START_B /*104*/:
                i2 = CODE_FNC_4_B;
                break;
            case CODE_START_C /*105*/:
                i2 = CODE_CODE_C;
                break;
            default:
                throw FormatException.getFormatInstance();
        }
        StringBuffer stringBuffer = new StringBuffer(20);
        int i4 = findStartPattern[0];
        int i5 = findStartPattern[1];
        int[] iArr = new int[6];
        Object obj = 1;
        Object obj2 = null;
        Object obj3 = null;
        int i6 = i2;
        i2 = 0;
        int i7 = i3;
        i3 = 0;
        int i8 = 0;
        int i9 = i4;
        while (obj3 == null) {
            Object obj4 = null;
            int decodeCode = decodeCode(bitArray, iArr, i5);
            if (decodeCode != CODE_STOP) {
                obj = 1;
            }
            if (decodeCode != CODE_STOP) {
                i2++;
                i7 += i2 * decodeCode;
            }
            i3 = 0;
            i9 = i5;
            while (true) {
                int length = iArr.length;
                if (i3 < r0) {
                    i9 += iArr[i3];
                    i3++;
                } else {
                    switch (decodeCode) {
                        case CODE_START_A /*103*/:
                        case CODE_START_B /*104*/:
                        case CODE_START_C /*105*/:
                            throw FormatException.getFormatInstance();
                        default:
                            Object obj5;
                            Object obj6;
                            Object obj7;
                            switch (i6) {
                                case CODE_CODE_C /*99*/:
                                    if (decodeCode >= CODE_FNC_4_B) {
                                        obj6 = decodeCode != CODE_STOP ? null : obj;
                                        switch (decodeCode) {
                                            case CODE_FNC_4_B /*100*/:
                                                obj7 = obj6;
                                                i3 = CODE_FNC_4_B;
                                                obj5 = obj3;
                                                obj3 = null;
                                                obj4 = obj7;
                                                break;
                                            case CODE_FNC_4_A /*101*/:
                                                obj7 = obj6;
                                                i3 = CODE_FNC_4_A;
                                                obj5 = obj3;
                                                obj3 = null;
                                                obj4 = obj7;
                                                break;
                                            case CODE_FNC_1 /*102*/:
                                                obj7 = obj6;
                                                i3 = i6;
                                                obj5 = obj3;
                                                obj3 = null;
                                                obj4 = obj7;
                                                break;
                                            case CODE_STOP /*106*/:
                                                obj7 = obj6;
                                                i3 = i6;
                                                i6 = 1;
                                                obj3 = null;
                                                obj4 = obj7;
                                                break;
                                            default:
                                                obj7 = obj6;
                                                i3 = i6;
                                                obj5 = obj3;
                                                obj3 = null;
                                                obj4 = obj7;
                                                break;
                                        }
                                    }
                                    if (decodeCode < 10) {
                                        stringBuffer.append('0');
                                    }
                                    stringBuffer.append(decodeCode);
                                    i3 = i6;
                                    obj5 = obj3;
                                    obj3 = null;
                                    obj4 = obj;
                                    break;
                                case CODE_FNC_4_B /*100*/:
                                    if (decodeCode >= CODE_FNC_3) {
                                        obj6 = decodeCode != CODE_STOP ? null : obj;
                                        switch (decodeCode) {
                                            case CODE_SHIFT /*98*/:
                                                obj4 = 1;
                                                i6 = CODE_CODE_C;
                                                break;
                                            case CODE_CODE_C /*99*/:
                                                i6 = CODE_CODE_C;
                                                break;
                                            case CODE_FNC_4_A /*101*/:
                                                i6 = CODE_FNC_4_A;
                                                break;
                                            case CODE_STOP /*106*/:
                                                obj3 = 1;
                                                break;
                                        }
                                        obj7 = obj6;
                                        i3 = i6;
                                        obj5 = obj3;
                                        obj3 = obj4;
                                        obj4 = obj7;
                                        break;
                                    }
                                    stringBuffer.append((char) (decodeCode + 32));
                                    i3 = i6;
                                    obj5 = obj3;
                                    obj3 = null;
                                    obj4 = obj;
                                    break;
                                case CODE_FNC_4_A /*101*/:
                                    if (decodeCode >= MAX_AVG_VARIANCE) {
                                        if (decodeCode >= CODE_FNC_3) {
                                            int i10;
                                            if (decodeCode != CODE_STOP) {
                                                obj = null;
                                            }
                                            switch (decodeCode) {
                                                case CODE_FNC_3 /*96*/:
                                                case CODE_FNC_2 /*97*/:
                                                case CODE_FNC_4_A /*101*/:
                                                case CODE_FNC_1 /*102*/:
                                                    obj6 = null;
                                                    obj4 = obj3;
                                                    i10 = i6;
                                                    break;
                                                case CODE_SHIFT /*98*/:
                                                    obj6 = 1;
                                                    obj7 = obj3;
                                                    i10 = CODE_FNC_4_B;
                                                    obj4 = obj7;
                                                    break;
                                                case CODE_CODE_C /*99*/:
                                                    obj4 = obj3;
                                                    i10 = CODE_CODE_C;
                                                    obj6 = null;
                                                    break;
                                                case CODE_FNC_4_B /*100*/:
                                                    obj4 = obj3;
                                                    i10 = CODE_FNC_4_B;
                                                    obj6 = null;
                                                    break;
                                                case CODE_STOP /*106*/:
                                                    i10 = i6;
                                                    obj6 = null;
                                                    i4 = 1;
                                                    break;
                                                default:
                                                    obj6 = null;
                                                    obj4 = obj3;
                                                    i10 = i6;
                                                    break;
                                            }
                                            obj5 = obj4;
                                            obj4 = obj;
                                            obj7 = obj6;
                                            i3 = i10;
                                            obj3 = obj7;
                                            break;
                                        }
                                        stringBuffer.append((char) (decodeCode - 64));
                                        i3 = i6;
                                        obj5 = obj3;
                                        obj3 = null;
                                        obj4 = obj;
                                        break;
                                    }
                                    stringBuffer.append((char) (decodeCode + 32));
                                    i3 = i6;
                                    obj5 = obj3;
                                    obj3 = null;
                                    obj4 = obj;
                                    break;
                                default:
                                    i3 = i6;
                                    obj5 = obj3;
                                    obj3 = null;
                                    obj4 = obj;
                                    break;
                            }
                            if (obj2 != null) {
                                switch (i3) {
                                    case CODE_CODE_C /*99*/:
                                        i3 = CODE_FNC_4_B;
                                        break;
                                    case CODE_FNC_4_B /*100*/:
                                        i3 = CODE_FNC_4_A;
                                        break;
                                    case CODE_FNC_4_A /*101*/:
                                        i3 = CODE_CODE_C;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            obj = obj4;
                            obj2 = obj3;
                            obj3 = obj5;
                            i6 = i3;
                            i3 = i8;
                            i8 = decodeCode;
                            int i11 = i5;
                            i5 = i9;
                            i9 = i11;
                    }
                }
            }
        }
        i4 = bitArray.getSize();
        while (i5 < i4 && bitArray.get(i5)) {
            i5++;
        }
        if (!bitArray.isRange(i5, Math.min(i4, ((i5 - i9) / 2) + i5), false)) {
            throw NotFoundException.getNotFoundInstance();
        } else if ((i7 - (i2 * i3)) % CODE_START_A != i3) {
            throw ChecksumException.getChecksumInstance();
        } else {
            i2 = stringBuffer.length();
            if (i2 > 0 && r9 != null) {
                if (i6 == CODE_CODE_C) {
                    stringBuffer.delete(i2 - 2, i2);
                } else {
                    stringBuffer.delete(i2 - 1, i2);
                }
            }
            String stringBuffer2 = stringBuffer.toString();
            if (stringBuffer2.length() == 0) {
                throw FormatException.getFormatInstance();
            }
            float f = ((float) (i5 + i9)) / 2.0f;
            return new Result(stringBuffer2, null, new ResultPoint[]{new ResultPoint(((float) (findStartPattern[1] + findStartPattern[0])) / 2.0f, (float) i), new ResultPoint(f, (float) i)}, BarcodeFormat.CODE_128);
        }
    }
}
