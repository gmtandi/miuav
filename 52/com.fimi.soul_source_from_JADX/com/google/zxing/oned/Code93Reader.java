package com.google.zxing.oned;

import com.fimi.soul.module.setting.newhand.ae;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
import com.tencent.mm.sdk.platformtools.Util;
import it.p074a.p075a.C2799f;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public final class Code93Reader extends OneDReader {
    private static final char[] ALPHABET;
    private static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";
    private static final int ASTERISK_ENCODING;
    private static final int[] CHARACTER_ENCODINGS;

    static {
        ALPHABET = ALPHABET_STRING.toCharArray();
        CHARACTER_ENCODINGS = new int[]{276, 328, 324, 322, 296, 292, 290, 336, TiffUtil.TIFF_TAG_ORIENTATION, 266, 424, 420, 418, 404, 402, 394, 360, 356, 354, 308, 282, 344, C2799f.f14280r, 326, ae.f9482j, 278, 436, 434, 428, 422, 406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, C2799f.f14281s};
        ASTERISK_ENCODING = CHARACTER_ENCODINGS[47];
    }

    private static void checkChecksums(StringBuffer stringBuffer) {
        int length = stringBuffer.length();
        checkOneChecksum(stringBuffer, length - 2, 20);
        checkOneChecksum(stringBuffer, length - 1, 15);
    }

    private static void checkOneChecksum(StringBuffer stringBuffer, int i, int i2) {
        int i3 = 1;
        int i4 = i - 1;
        int i5 = 0;
        while (i4 >= 0) {
            int indexOf = (ALPHABET_STRING.indexOf(stringBuffer.charAt(i4)) * i3) + i5;
            i5 = i3 + 1;
            if (i5 > i2) {
                i5 = 1;
            }
            i4--;
            i3 = i5;
            i5 = indexOf;
        }
        if (stringBuffer.charAt(i) != ALPHABET[i5 % 47]) {
            throw ChecksumException.getChecksumInstance();
        }
    }

    private static String decodeExtended(StringBuffer stringBuffer) {
        int length = stringBuffer.length();
        StringBuffer stringBuffer2 = new StringBuffer(length);
        int i = 0;
        while (i < length) {
            int i2;
            char charAt = stringBuffer.charAt(i);
            if (charAt < 'a' || charAt > 'd') {
                stringBuffer2.append(charAt);
                i2 = i;
            } else {
                char charAt2 = stringBuffer.charAt(i + 1);
                switch (charAt) {
                    case Opcodes.LADD /*97*/:
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            charAt = (char) (charAt2 - 64);
                            break;
                        }
                        throw FormatException.getFormatInstance();
                    case Opcodes.FADD /*98*/:
                        if (charAt2 < 'A' || charAt2 > 'E') {
                            if (charAt2 >= 'F' && charAt2 <= 'W') {
                                charAt = (char) (charAt2 - 11);
                                break;
                            }
                            throw FormatException.getFormatInstance();
                        }
                        charAt = (char) (charAt2 - 38);
                        break;
                        break;
                    case Opcodes.DADD /*99*/:
                        if (charAt2 >= 'A' && charAt2 <= 'O') {
                            charAt = (char) (charAt2 - 32);
                            break;
                        } else if (charAt2 == 'Z') {
                            charAt = ':';
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                        break;
                    case Opcodes.ISUB /*100*/:
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            charAt = (char) (charAt2 + 32);
                            break;
                        }
                        throw FormatException.getFormatInstance();
                    default:
                        charAt = '\u0000';
                        break;
                }
                stringBuffer2.append(charAt);
                i2 = i + 1;
            }
            i = i2 + 1;
        }
        return stringBuffer2.toString();
    }

    private static int[] findAsteriskPattern(BitArray bitArray) {
        int size = bitArray.getSize();
        int i = 0;
        while (i < size && !bitArray.get(i)) {
            i++;
        }
        int[] iArr = new int[6];
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = i; i4 < size; i4++) {
            if ((bitArray.get(i4) ^ i2) != 0) {
                iArr[i3] = iArr[i3] + 1;
            } else {
                if (i3 != length - 1) {
                    i3++;
                } else if (toPattern(iArr) == ASTERISK_ENCODING) {
                    return new int[]{i, i4};
                } else {
                    i += iArr[0] + iArr[1];
                    for (int i5 = 2; i5 < length; i5++) {
                        iArr[i5 - 2] = iArr[i5];
                    }
                    iArr[length - 2] = 0;
                    iArr[length - 1] = 0;
                    i3--;
                }
                iArr[i3] = 1;
                i2 = i2 == 0 ? 1 : 0;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static char patternToChar(int i) {
        for (int i2 = 0; i2 < CHARACTER_ENCODINGS.length; i2++) {
            if (CHARACTER_ENCODINGS[i2] == i) {
                return ALPHABET[i2];
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int toPattern(int[] iArr) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            i++;
            i2 = iArr[i] + i2;
        }
        i = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = ((iArr[i3] << 8) * 9) / i2;
            int i5 = i4 >> 8;
            int i6 = (i4 & Util.MASK_8BIT) > Opcodes.LAND ? i5 + 1 : i5;
            if (i6 < 1 || i6 > 4) {
                return -1;
            }
            if ((i3 & 1) == 0) {
                i5 = 0;
                while (i5 < i6) {
                    i5++;
                    i = (i << 1) | 1;
                }
            } else {
                i <<= i6;
            }
        }
        return i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.Result decodeRow(int r10, com.google.zxing.common.BitArray r11, java.util.Hashtable r12) {
        /*
        r9 = this;
        r3 = findAsteriskPattern(r11);
        r0 = 1;
        r0 = r3[r0];
        r4 = r11.getSize();
    L_0x000b:
        if (r0 >= r4) goto L_0x0016;
    L_0x000d:
        r1 = r11.get(r0);
        if (r1 != 0) goto L_0x0016;
    L_0x0013:
        r0 = r0 + 1;
        goto L_0x000b;
    L_0x0016:
        r5 = new java.lang.StringBuffer;
        r1 = 20;
        r5.<init>(r1);
        r1 = 6;
        r6 = new int[r1];
    L_0x0020:
        com.google.zxing.oned.OneDReader.recordPattern(r11, r0, r6);
        r1 = toPattern(r6);
        if (r1 >= 0) goto L_0x002e;
    L_0x0029:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x002e:
        r7 = patternToChar(r1);
        r5.append(r7);
        r1 = 0;
        r2 = r0;
    L_0x0037:
        r8 = r6.length;
        if (r1 >= r8) goto L_0x00b2;
    L_0x003a:
        r8 = r6[r1];
        r2 = r2 + r8;
        r1 = r1 + 1;
        goto L_0x0037;
    L_0x0040:
        if (r1 >= r4) goto L_0x004b;
    L_0x0042:
        r2 = r11.get(r1);
        if (r2 != 0) goto L_0x004b;
    L_0x0048:
        r1 = r1 + 1;
        goto L_0x0040;
    L_0x004b:
        r2 = 42;
        if (r7 != r2) goto L_0x00af;
    L_0x004f:
        r2 = r5.length();
        r2 = r2 + -1;
        r5.deleteCharAt(r2);
        if (r1 == r4) goto L_0x0060;
    L_0x005a:
        r2 = r11.get(r1);
        if (r2 != 0) goto L_0x0065;
    L_0x0060:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x0065:
        r2 = r5.length();
        r4 = 2;
        if (r2 >= r4) goto L_0x0071;
    L_0x006c:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x0071:
        checkChecksums(r5);
        r2 = r5.length();
        r2 = r2 + -2;
        r5.setLength(r2);
        r2 = decodeExtended(r5);
        r4 = 1;
        r4 = r3[r4];
        r5 = 0;
        r3 = r3[r5];
        r3 = r3 + r4;
        r3 = (float) r3;
        r4 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r3 = r3 / r4;
        r0 = r0 + r1;
        r0 = (float) r0;
        r1 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r0 = r0 / r1;
        r1 = new com.google.zxing.Result;
        r4 = 0;
        r5 = 2;
        r5 = new com.google.zxing.ResultPoint[r5];
        r6 = 0;
        r7 = new com.google.zxing.ResultPoint;
        r8 = (float) r10;
        r7.<init>(r3, r8);
        r5[r6] = r7;
        r3 = 1;
        r6 = new com.google.zxing.ResultPoint;
        r7 = (float) r10;
        r6.<init>(r0, r7);
        r5[r3] = r6;
        r0 = com.google.zxing.BarcodeFormat.CODE_93;
        r1.<init>(r2, r4, r5, r0);
        return r1;
    L_0x00af:
        r0 = r1;
        goto L_0x0020;
    L_0x00b2:
        r1 = r2;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code93Reader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Hashtable):com.google.zxing.Result");
    }
}
