package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class EAN13Reader extends UPCEANReader {
    static final int[] FIRST_DIGIT_ENCODINGS;
    private final int[] decodeMiddleCounters;

    static {
        FIRST_DIGIT_ENCODINGS = new int[]{0, 11, 13, 14, 19, 25, 28, 21, 22, 26};
    }

    public EAN13Reader() {
        this.decodeMiddleCounters = new int[4];
    }

    private static void determineFirstDigit(StringBuffer stringBuffer, int i) {
        for (int i2 = 0; i2 < 10; i2++) {
            if (i == FIRST_DIGIT_ENCODINGS[i2]) {
                stringBuffer.insert(0, (char) (i2 + 48));
                return;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected int decodeMiddle(BitArray bitArray, int[] iArr, StringBuffer stringBuffer) {
        int[] iArr2 = this.decodeMiddleCounters;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int size = bitArray.getSize();
        int i = iArr[1];
        int i2 = 0;
        int i3 = 0;
        while (i2 < 6 && i < size) {
            int decodeDigit = UPCEANReader.decodeDigit(bitArray, iArr2, i, L_AND_G_PATTERNS);
            stringBuffer.append((char) ((decodeDigit % 10) + 48));
            int i4 = i;
            for (int i5 : iArr2) {
                i4 += i5;
            }
            i = decodeDigit >= 10 ? (1 << (5 - i2)) | i3 : i3;
            i2++;
            i3 = i;
            i = i4;
        }
        determineFirstDigit(stringBuffer, i3);
        i = UPCEANReader.findGuardPattern(bitArray, i, true, MIDDLE_PATTERN)[1];
        i3 = 0;
        while (i3 < 6 && i < size) {
            stringBuffer.append((char) (UPCEANReader.decodeDigit(bitArray, iArr2, i, L_PATTERNS) + 48));
            i4 = i;
            for (int i22 : iArr2) {
                i4 += i22;
            }
            i3++;
            i = i4;
        }
        return i;
    }

    BarcodeFormat getBarcodeFormat() {
        return BarcodeFormat.EAN_13;
    }
}
