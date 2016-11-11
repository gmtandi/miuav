package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;

public final class Decoder {
    private static final int MAX_EC_CODEWORDS = 512;
    private static final int MAX_ERRORS = 3;

    private static int correctErrors(int[] iArr, int[] iArr2, int i) {
        if ((iArr2 != null && iArr2.length > (i / 2) + MAX_ERRORS) || i < 0 || i > MAX_EC_CODEWORDS) {
            throw FormatException.getFormatInstance();
        } else if (iArr2 == null || iArr2.length <= MAX_ERRORS) {
            return 0;
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static void verifyCodewordCount(int[] iArr, int i) {
        if (iArr.length < 4) {
            throw FormatException.getFormatInstance();
        }
        int i2 = iArr[0];
        if (i2 > iArr.length) {
            throw FormatException.getFormatInstance();
        } else if (i2 != 0) {
        } else {
            if (i < iArr.length) {
                iArr[0] = iArr.length - i;
                return;
            }
            throw FormatException.getFormatInstance();
        }
    }

    public DecoderResult decode(BitMatrix bitMatrix) {
        BitMatrixParser bitMatrixParser = new BitMatrixParser(bitMatrix);
        int[] readCodewords = bitMatrixParser.readCodewords();
        if (readCodewords == null || readCodewords.length == 0) {
            throw FormatException.getFormatInstance();
        }
        int eCLevel = 1 << (bitMatrixParser.getECLevel() + 1);
        correctErrors(readCodewords, bitMatrixParser.getErasures(), eCLevel);
        verifyCodewordCount(readCodewords, eCLevel);
        return DecodedBitStreamParser.decode(readCodewords);
    }

    public DecoderResult decode(boolean[][] zArr) {
        int length = zArr.length;
        BitMatrix bitMatrix = new BitMatrix(length);
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                if (zArr[i2][i]) {
                    bitMatrix.set(i2, i);
                }
            }
        }
        return decode(bitMatrix);
    }
}
