package com.google.zxing.oned;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class CodaBarReader extends OneDReader {
    private static final char[] ALPHABET;
    private static final String ALPHABET_STRING = "0123456789-$:/.+ABCDTN";
    private static final int[] CHARACTER_ENCODINGS;
    private static final char[] STARTEND_ENCODING;
    private static final int minCharacterLength = 6;

    static {
        ALPHABET = ALPHABET_STRING.toCharArray();
        CHARACTER_ENCODINGS = new int[]{3, minCharacterLength, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 37, 81, 84, 21, 26, 41, 11, 14, 26, 41};
        STARTEND_ENCODING = new char[]{'E', '*', 'A', 'B', 'C', 'D', 'T', 'N'};
    }

    private static boolean arrayContains(char[] cArr, char c) {
        if (cArr == null) {
            return false;
        }
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    private static int[] findAsteriskPattern(BitArray bitArray) {
        int size = bitArray.getSize();
        int i = 0;
        while (i < size && !bitArray.get(i)) {
            i++;
        }
        int[] iArr = new int[7];
        int length = iArr.length;
        int i2 = i;
        int i3 = 0;
        int i4 = 0;
        while (i2 < size) {
            int i5;
            if ((bitArray.get(i2) ^ i3) != 0) {
                iArr[i4] = iArr[i4] + 1;
                i5 = i3;
                i3 = i4;
                i4 = i;
                i = i5;
            } else {
                if (i4 == length - 1) {
                    try {
                        if (arrayContains(STARTEND_ENCODING, toNarrowWidePattern(iArr)) && bitArray.isRange(Math.max(0, i - ((i2 - i) / 2)), i, false)) {
                            return new int[]{i, i2};
                        }
                    } catch (IllegalArgumentException e) {
                    }
                    i += iArr[0] + iArr[1];
                    for (int i6 = 2; i6 < length; i6++) {
                        iArr[i6 - 2] = iArr[i6];
                    }
                    iArr[length - 2] = 0;
                    iArr[length - 1] = 0;
                    i4--;
                } else {
                    i4++;
                }
                iArr[i4] = 1;
                i5 = i3 ^ 1;
                i3 = i4;
                i4 = i;
                i = i5;
            }
            i2++;
            i5 = i;
            i = i4;
            i4 = i3;
            i3 = i5;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static char toNarrowWidePattern(int[] iArr) {
        int i;
        int length = iArr.length;
        int i2 = Integer.MAX_VALUE;
        int i3 = 0;
        for (i = 0; i < length; i++) {
            if (iArr[i] < i2) {
                i2 = iArr[i];
            }
            if (iArr[i] > i3) {
                i3 = iArr[i];
            }
        }
        do {
            i = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < length; i5++) {
                if (iArr[i5] > i3) {
                    i |= 1 << ((length - 1) - i5);
                    i4++;
                }
            }
            if (i4 == 2 || i4 == 3) {
                for (i4 = 0; i4 < CHARACTER_ENCODINGS.length; i4++) {
                    if (CHARACTER_ENCODINGS[i4] == i) {
                        return ALPHABET[i4];
                    }
                }
            }
            i3--;
        } while (i3 > i2);
        return '!';
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.Result decodeRow(int r10, com.google.zxing.common.BitArray r11, java.util.Hashtable r12) {
        /*
        r9 = this;
        r4 = findAsteriskPattern(r11);
        r0 = 1;
        r1 = 0;
        r4[r0] = r1;
        r0 = 1;
        r0 = r4[r0];
        r5 = r11.getSize();
    L_0x000f:
        if (r0 >= r5) goto L_0x001a;
    L_0x0011:
        r1 = r11.get(r0);
        if (r1 != 0) goto L_0x001a;
    L_0x0017:
        r0 = r0 + 1;
        goto L_0x000f;
    L_0x001a:
        r6 = new java.lang.StringBuffer;
        r6.<init>();
    L_0x001f:
        r1 = 7;
        r7 = new int[r1];
        r7 = {0, 0, 0, 0, 0, 0, 0};
        com.google.zxing.oned.OneDReader.recordPattern(r11, r0, r7);
        r1 = toNarrowWidePattern(r7);
        r2 = 33;
        if (r1 != r2) goto L_0x0035;
    L_0x0030:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x0035:
        r6.append(r1);
        r1 = 0;
        r2 = r0;
    L_0x003a:
        r3 = r7.length;
        if (r1 >= r3) goto L_0x00fc;
    L_0x003d:
        r3 = r7[r1];
        r2 = r2 + r3;
        r1 = r1 + 1;
        goto L_0x003a;
    L_0x0043:
        if (r1 >= r5) goto L_0x004e;
    L_0x0045:
        r2 = r11.get(r1);
        if (r2 != 0) goto L_0x004e;
    L_0x004b:
        r1 = r1 + 1;
        goto L_0x0043;
    L_0x004e:
        if (r1 < r5) goto L_0x00f9;
    L_0x0050:
        r3 = 0;
        r2 = 0;
    L_0x0052:
        r8 = r7.length;
        if (r2 >= r8) goto L_0x005b;
    L_0x0055:
        r8 = r7[r2];
        r3 = r3 + r8;
        r2 = r2 + 1;
        goto L_0x0052;
    L_0x005b:
        r2 = r1 - r0;
        r2 = r2 - r3;
        if (r1 == r5) goto L_0x0069;
    L_0x0060:
        r2 = r2 / 2;
        if (r2 >= r3) goto L_0x0069;
    L_0x0064:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x0069:
        r2 = r6.length();
        r3 = 2;
        if (r2 >= r3) goto L_0x0075;
    L_0x0070:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x0075:
        r2 = 0;
        r3 = r6.charAt(r2);
        r2 = STARTEND_ENCODING;
        r2 = arrayContains(r2, r3);
        if (r2 != 0) goto L_0x0087;
    L_0x0082:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x0087:
        r2 = 1;
    L_0x0088:
        r5 = r6.length();
        if (r2 >= r5) goto L_0x00ae;
    L_0x008e:
        r5 = r6.charAt(r2);
        if (r5 != r3) goto L_0x00ab;
    L_0x0094:
        r5 = r2 + 1;
        r7 = r6.length();
        if (r5 == r7) goto L_0x00ab;
    L_0x009c:
        r2 = r2 + 1;
        r5 = r6.length();
        r5 = r5 + -1;
        r6.delete(r2, r5);
        r2 = r6.length();
    L_0x00ab:
        r2 = r2 + 1;
        goto L_0x0088;
    L_0x00ae:
        r2 = r6.length();
        r3 = 6;
        if (r2 <= r3) goto L_0x00f4;
    L_0x00b5:
        r2 = r6.length();
        r2 = r2 + -1;
        r6.deleteCharAt(r2);
        r2 = 0;
        r6.deleteCharAt(r2);
        r2 = 1;
        r2 = r4[r2];
        r3 = 0;
        r3 = r4[r3];
        r2 = r2 + r3;
        r2 = (float) r2;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 / r3;
        r0 = r0 + r1;
        r0 = (float) r0;
        r1 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r0 = r0 / r1;
        r1 = new com.google.zxing.Result;
        r3 = r6.toString();
        r4 = 0;
        r5 = 2;
        r5 = new com.google.zxing.ResultPoint[r5];
        r6 = 0;
        r7 = new com.google.zxing.ResultPoint;
        r8 = (float) r10;
        r7.<init>(r2, r8);
        r5[r6] = r7;
        r2 = 1;
        r6 = new com.google.zxing.ResultPoint;
        r7 = (float) r10;
        r6.<init>(r0, r7);
        r5[r2] = r6;
        r0 = com.google.zxing.BarcodeFormat.CODABAR;
        r1.<init>(r3, r4, r5, r0);
        return r1;
    L_0x00f4:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x00f9:
        r0 = r1;
        goto L_0x001f;
    L_0x00fc:
        r1 = r2;
        goto L_0x0043;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.CodaBarReader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Hashtable):com.google.zxing.Result");
    }
}
