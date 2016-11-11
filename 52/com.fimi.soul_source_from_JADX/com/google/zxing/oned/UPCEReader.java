package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;

public final class UPCEReader extends UPCEANReader {
    private static final int[] MIDDLE_END_PATTERN;
    private static final int[][] NUMSYS_AND_CHECK_DIGIT_PATTERNS;
    private final int[] decodeMiddleCounters;

    static {
        MIDDLE_END_PATTERN = new int[]{1, 1, 1, 1, 1, 1};
        NUMSYS_AND_CHECK_DIGIT_PATTERNS = new int[][]{new int[]{56, 52, 50, 49, 44, 38, 35, 42, 41, 37}, new int[]{7, 11, 13, 14, 19, 25, 28, 21, 22, 26}};
    }

    public UPCEReader() {
        this.decodeMiddleCounters = new int[4];
    }

    public static String convertUPCEtoUPCA(String str) {
        char[] cArr = new char[6];
        str.getChars(1, 7, cArr, 0);
        StringBuffer stringBuffer = new StringBuffer(12);
        stringBuffer.append(str.charAt(0));
        char c = cArr[5];
        switch (c) {
            case SmileConstants.TOKEN_PREFIX_KEY_SHARED_LONG /*48*/:
            case Opcodes.V1_5 /*49*/:
            case Opcodes.V1_6 /*50*/:
                stringBuffer.append(cArr, 0, 2);
                stringBuffer.append(c);
                stringBuffer.append("0000");
                stringBuffer.append(cArr, 2, 3);
                break;
            case Opcodes.V1_7 /*51*/:
                stringBuffer.append(cArr, 0, 3);
                stringBuffer.append("00000");
                stringBuffer.append(cArr, 3, 2);
                break;
            case Opcodes.CALOAD /*52*/:
                stringBuffer.append(cArr, 0, 4);
                stringBuffer.append("00000");
                stringBuffer.append(cArr[4]);
                break;
            default:
                stringBuffer.append(cArr, 0, 5);
                stringBuffer.append("0000");
                stringBuffer.append(c);
                break;
        }
        stringBuffer.append(str.charAt(7));
        return stringBuffer.toString();
    }

    private static void determineNumSysAndCheckDigit(StringBuffer stringBuffer, int i) {
        for (int i2 = 0; i2 <= 1; i2++) {
            for (int i3 = 0; i3 < 10; i3++) {
                if (i == NUMSYS_AND_CHECK_DIGIT_PATTERNS[i2][i3]) {
                    stringBuffer.insert(0, (char) (i2 + 48));
                    stringBuffer.append((char) (i3 + 48));
                    return;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected boolean checkChecksum(String str) {
        return super.checkChecksum(convertUPCEtoUPCA(str));
    }

    protected int[] decodeEnd(BitArray bitArray, int i) {
        return UPCEANReader.findGuardPattern(bitArray, i, true, MIDDLE_END_PATTERN);
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
        determineNumSysAndCheckDigit(stringBuffer, i3);
        return i;
    }

    BarcodeFormat getBarcodeFormat() {
        return BarcodeFormat.UPC_E;
    }
}
