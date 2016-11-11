package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

public abstract class UPCEANReader extends OneDReader {
    static final int[][] L_AND_G_PATTERNS;
    static final int[][] L_PATTERNS;
    private static final int MAX_AVG_VARIANCE = 107;
    private static final int MAX_INDIVIDUAL_VARIANCE = 179;
    static final int[] MIDDLE_PATTERN;
    static final int[] START_END_PATTERN;
    private final StringBuffer decodeRowStringBuffer;
    private final EANManufacturerOrgSupport eanManSupport;
    private final UPCEANExtensionSupport extensionReader;

    static {
        int i;
        START_END_PATTERN = new int[]{1, 1, 1};
        MIDDLE_PATTERN = new int[]{1, 1, 1, 1, 1};
        L_PATTERNS = new int[][]{new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
        L_AND_G_PATTERNS = new int[20][];
        for (i = 0; i < 10; i++) {
            L_AND_G_PATTERNS[i] = L_PATTERNS[i];
        }
        for (i = 10; i < 20; i++) {
            int[] iArr = L_PATTERNS[i - 10];
            int[] iArr2 = new int[iArr.length];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                iArr2[i2] = iArr[(iArr.length - i2) - 1];
            }
            L_AND_G_PATTERNS[i] = iArr2;
        }
    }

    protected UPCEANReader() {
        this.decodeRowStringBuffer = new StringBuffer(20);
        this.extensionReader = new UPCEANExtensionSupport();
        this.eanManSupport = new EANManufacturerOrgSupport();
    }

    private static boolean checkStandardUPCEANChecksum(String str) {
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i;
        int i2 = 0;
        for (i = length - 2; i >= 0; i -= 2) {
            int charAt = str.charAt(i) - 48;
            if (charAt < 0 || charAt > 9) {
                throw FormatException.getFormatInstance();
            }
            i2 += charAt;
        }
        i2 *= 3;
        for (i = length - 1; i >= 0; i -= 2) {
            length = str.charAt(i) - 48;
            if (length < 0 || length > 9) {
                throw FormatException.getFormatInstance();
            }
            i2 += length;
        }
        return i2 % 10 == 0;
    }

    static int decodeDigit(BitArray bitArray, int[] iArr, int i, int[][] iArr2) {
        OneDReader.recordPattern(bitArray, i, iArr);
        int i2 = MAX_AVG_VARIANCE;
        int i3 = -1;
        int length = iArr2.length;
        int i4 = 0;
        while (i4 < length) {
            int patternMatchVariance = OneDReader.patternMatchVariance(iArr, iArr2[i4], MAX_INDIVIDUAL_VARIANCE);
            if (patternMatchVariance < i2) {
                i3 = i4;
            } else {
                patternMatchVariance = i2;
            }
            i4++;
            i2 = patternMatchVariance;
        }
        if (i3 >= 0) {
            return i3;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    static int[] findGuardPattern(BitArray bitArray, int i, boolean z, int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
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
        int i3 = i2;
        i2 = 0;
        int i4 = z2;
        int i5 = i3;
        for (int i6 = i2; i6 < size; i6++) {
            if ((bitArray.get(i6) ^ i4) != 0) {
                iArr2[i2] = iArr2[i2] + 1;
            } else {
                if (i2 != length - 1) {
                    i2++;
                } else if (OneDReader.patternMatchVariance(iArr2, iArr, MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                    return new int[]{i5, i6};
                } else {
                    i5 += iArr2[0] + iArr2[1];
                    for (int i7 = 2; i7 < length; i7++) {
                        iArr2[i7 - 2] = iArr2[i7];
                    }
                    iArr2[length - 2] = 0;
                    iArr2[length - 1] = 0;
                    i2--;
                }
                iArr2[i2] = 1;
                i4 = i4 == 0 ? 1 : 0;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    static int[] findStartGuardPattern(BitArray bitArray) {
        int i = 0;
        int[] iArr = null;
        boolean z = false;
        while (!z) {
            iArr = findGuardPattern(bitArray, i, false, START_END_PATTERN);
            int i2 = iArr[0];
            i = iArr[1];
            int i3 = i2 - (i - i2);
            if (i3 >= 0) {
                z = bitArray.isRange(i3, i2, false);
            }
        }
        return iArr;
    }

    boolean checkChecksum(String str) {
        return checkStandardUPCEANChecksum(str);
    }

    int[] decodeEnd(BitArray bitArray, int i) {
        return findGuardPattern(bitArray, i, false, START_END_PATTERN);
    }

    protected abstract int decodeMiddle(BitArray bitArray, int[] iArr, StringBuffer stringBuffer);

    public Result decodeRow(int i, BitArray bitArray, Hashtable hashtable) {
        return decodeRow(i, bitArray, findStartGuardPattern(bitArray), hashtable);
    }

    public Result decodeRow(int i, BitArray bitArray, int[] iArr, Hashtable hashtable) {
        ResultPointCallback resultPointCallback = hashtable == null ? null : (ResultPointCallback) hashtable.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        if (resultPointCallback != null) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint(((float) (iArr[0] + iArr[1])) / 2.0f, (float) i));
        }
        StringBuffer stringBuffer = this.decodeRowStringBuffer;
        stringBuffer.setLength(0);
        int decodeMiddle = decodeMiddle(bitArray, iArr, stringBuffer);
        if (resultPointCallback != null) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint((float) decodeMiddle, (float) i));
        }
        int[] decodeEnd = decodeEnd(bitArray, decodeMiddle);
        if (resultPointCallback != null) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint(((float) (decodeEnd[0] + decodeEnd[1])) / 2.0f, (float) i));
        }
        int i2 = decodeEnd[1];
        int i3 = (i2 - decodeEnd[0]) + i2;
        if (i3 >= bitArray.getSize() || !bitArray.isRange(i2, i3, false)) {
            throw NotFoundException.getNotFoundInstance();
        }
        String stringBuffer2 = stringBuffer.toString();
        if (checkChecksum(stringBuffer2)) {
            float f = ((float) (iArr[1] + iArr[0])) / 2.0f;
            float f2 = ((float) (decodeEnd[1] + decodeEnd[0])) / 2.0f;
            BarcodeFormat barcodeFormat = getBarcodeFormat();
            Result result = new Result(stringBuffer2, null, new ResultPoint[]{new ResultPoint(f, (float) i), new ResultPoint(f2, (float) i)}, barcodeFormat);
            try {
                Result decodeRow = this.extensionReader.decodeRow(i, bitArray, decodeEnd[1]);
                result.putAllMetadata(decodeRow.getResultMetadata());
                result.addResultPoints(decodeRow.getResultPoints());
            } catch (ReaderException e) {
            }
            if (BarcodeFormat.EAN_13.equals(barcodeFormat) || BarcodeFormat.UPC_A.equals(barcodeFormat)) {
                stringBuffer2 = this.eanManSupport.lookupCountryIdentifier(stringBuffer2);
                if (stringBuffer2 != null) {
                    result.putMetadata(ResultMetadataType.POSSIBLE_COUNTRY, stringBuffer2);
                }
            }
            return result;
        }
        throw ChecksumException.getChecksumInstance();
    }

    abstract BarcodeFormat getBarcodeFormat();
}
