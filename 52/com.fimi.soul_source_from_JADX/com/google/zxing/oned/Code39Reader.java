package com.google.zxing.oned;

import com.facebook.imageutils.JfifUtil;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.codehaus.jackson.smile.SmileConstants;

public final class Code39Reader extends OneDReader {
    private static final char[] ALPHABET;
    static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
    private static final int ASTERISK_ENCODING;
    static final int[] CHARACTER_ENCODINGS;
    private final boolean extendedMode;
    private final boolean usingCheckDigit;

    static {
        ALPHABET = ALPHABET_STRING.toCharArray();
        CHARACTER_ENCODINGS = new int[]{52, 289, 97, 352, 49, 304, Opcodes.IREM, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, C1314u.f5851D, 67, 322, 19, TiffUtil.TIFF_TAG_ORIENTATION, 82, 7, 262, 70, 22, 385, Opcodes.INSTANCEOF, 448, Opcodes.I2B, 400, JfifUtil.MARKER_RST0, Opcodes.I2L, 388, SmileConstants.MIN_BUFFER_FOR_POSSIBLE_SHORT_STRING, Opcodes.LCMP, Opcodes.JSR, Opcodes.IF_ICMPGE, Opcodes.L2D, 42};
        ASTERISK_ENCODING = CHARACTER_ENCODINGS[39];
    }

    public Code39Reader() {
        this.usingCheckDigit = false;
        this.extendedMode = false;
    }

    public Code39Reader(boolean z) {
        this.usingCheckDigit = z;
        this.extendedMode = false;
    }

    public Code39Reader(boolean z, boolean z2) {
        this.usingCheckDigit = z;
        this.extendedMode = z2;
    }

    private static String decodeExtended(StringBuffer stringBuffer) {
        int length = stringBuffer.length();
        StringBuffer stringBuffer2 = new StringBuffer(length);
        int i = 0;
        while (i < length) {
            int i2;
            char charAt = stringBuffer.charAt(i);
            if (charAt == SignatureVisitor.EXTENDS || charAt == '$' || charAt == '%' || charAt == '/') {
                char charAt2 = stringBuffer.charAt(i + 1);
                switch (charAt) {
                    case SmileConstants.TOKEN_MISC_INTEGER /*36*/:
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            charAt = (char) (charAt2 - 64);
                            break;
                        }
                        throw FormatException.getFormatInstance();
                        break;
                    case '%':
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
                    case C1873o.f9553o /*43*/:
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            charAt = (char) (charAt2 + 32);
                            break;
                        }
                        throw FormatException.getFormatInstance();
                        break;
                    case Opcodes.V1_3 /*47*/:
                        if (charAt2 >= 'A' && charAt2 <= 'O') {
                            charAt = (char) (charAt2 - 32);
                            break;
                        } else if (charAt2 == 'Z') {
                            charAt = ':';
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    default:
                        charAt = '\u0000';
                        break;
                }
                stringBuffer2.append(charAt);
                i2 = i + 1;
            } else {
                stringBuffer2.append(charAt);
                i2 = i;
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
        int[] iArr = new int[9];
        int length = iArr.length;
        int i2 = i;
        int i3 = 0;
        int i4 = 0;
        while (i2 < size) {
            if ((bitArray.get(i2) ^ i3) != 0) {
                iArr[i4] = iArr[i4] + 1;
            } else {
                if (i4 != length - 1) {
                    i4++;
                } else if (toNarrowWidePattern(iArr) == ASTERISK_ENCODING && bitArray.isRange(Math.max(0, i - ((i2 - i) / 2)), i, false)) {
                    return new int[]{i, i2};
                } else {
                    i += iArr[0] + iArr[1];
                    for (int i5 = 2; i5 < length; i5++) {
                        iArr[i5 - 2] = iArr[i5];
                    }
                    iArr[length - 2] = 0;
                    iArr[length - 1] = 0;
                    i4--;
                }
                iArr[i4] = 1;
                i3 = i3 == 0 ? 1 : 0;
            }
            i2++;
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

    private static int toNarrowWidePattern(int[] iArr) {
        int i;
        int i2;
        int i3 = 0;
        while (true) {
            int i4 = Integer.MAX_VALUE;
            for (int i5 : iArr) {
                if (i5 < i4 && i5 > r0) {
                    i4 = i5;
                }
            }
            i3 = 0;
            int i52 = 0;
            i = 0;
            for (i2 = 0; i2 < r7; i2++) {
                int i6 = iArr[i2];
                if (iArr[i2] > i4) {
                    i3 |= 1 << ((r7 - 1) - i2);
                    i++;
                    i52 += i6;
                }
            }
            if (i == 3) {
                break;
            } else if (i <= 3) {
                return -1;
            } else {
                i3 = i4;
            }
        }
        int i7 = i;
        for (i = 0; i < r7 && i7 > 0; i++) {
            i2 = iArr[i];
            if (iArr[i] > i4) {
                i7--;
                if ((i2 << 1) >= i52) {
                    return -1;
                }
            }
        }
        return i3;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.Result decodeRow(int r13, com.google.zxing.common.BitArray r14, java.util.Hashtable r15) {
        /*
        r12 = this;
        r11 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r10 = 1;
        r2 = 0;
        r5 = findAsteriskPattern(r14);
        r0 = r5[r10];
        r6 = r14.getSize();
    L_0x000e:
        if (r0 >= r6) goto L_0x0019;
    L_0x0010:
        r1 = r14.get(r0);
        if (r1 != 0) goto L_0x0019;
    L_0x0016:
        r0 = r0 + 1;
        goto L_0x000e;
    L_0x0019:
        r7 = new java.lang.StringBuffer;
        r1 = 20;
        r7.<init>(r1);
        r1 = 9;
        r8 = new int[r1];
    L_0x0024:
        com.google.zxing.oned.OneDReader.recordPattern(r14, r0, r8);
        r1 = toNarrowWidePattern(r8);
        if (r1 >= 0) goto L_0x0032;
    L_0x002d:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x0032:
        r4 = patternToChar(r1);
        r7.append(r4);
        r1 = r2;
        r3 = r0;
    L_0x003b:
        r9 = r8.length;
        if (r1 >= r9) goto L_0x00e6;
    L_0x003e:
        r9 = r8[r1];
        r3 = r3 + r9;
        r1 = r1 + 1;
        goto L_0x003b;
    L_0x0044:
        if (r1 >= r6) goto L_0x004f;
    L_0x0046:
        r3 = r14.get(r1);
        if (r3 != 0) goto L_0x004f;
    L_0x004c:
        r1 = r1 + 1;
        goto L_0x0044;
    L_0x004f:
        r3 = 42;
        if (r4 != r3) goto L_0x00e3;
    L_0x0053:
        r3 = r7.length();
        r3 = r3 + -1;
        r7.deleteCharAt(r3);
        r3 = r2;
        r4 = r2;
    L_0x005e:
        r9 = r8.length;
        if (r3 >= r9) goto L_0x0067;
    L_0x0061:
        r9 = r8[r3];
        r4 = r4 + r9;
        r3 = r3 + 1;
        goto L_0x005e;
    L_0x0067:
        r3 = r1 - r0;
        r3 = r3 - r4;
        if (r1 == r6) goto L_0x0075;
    L_0x006c:
        r3 = r3 / 2;
        if (r3 >= r4) goto L_0x0075;
    L_0x0070:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x0075:
        r3 = r12.usingCheckDigit;
        if (r3 == 0) goto L_0x00a5;
    L_0x0079:
        r3 = r7.length();
        r6 = r3 + -1;
        r3 = r2;
        r4 = r2;
    L_0x0081:
        if (r3 >= r6) goto L_0x0091;
    L_0x0083:
        r8 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
        r9 = r7.charAt(r3);
        r8 = r8.indexOf(r9);
        r4 = r4 + r8;
        r3 = r3 + 1;
        goto L_0x0081;
    L_0x0091:
        r3 = r7.charAt(r6);
        r8 = ALPHABET;
        r4 = r4 % 43;
        r4 = r8[r4];
        if (r3 == r4) goto L_0x00a2;
    L_0x009d:
        r0 = com.google.zxing.ChecksumException.getChecksumInstance();
        throw r0;
    L_0x00a2:
        r7.deleteCharAt(r6);
    L_0x00a5:
        r3 = r7.length();
        if (r3 != 0) goto L_0x00b0;
    L_0x00ab:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x00b0:
        r3 = r12.extendedMode;
        if (r3 == 0) goto L_0x00de;
    L_0x00b4:
        r3 = decodeExtended(r7);
    L_0x00b8:
        r4 = r5[r10];
        r5 = r5[r2];
        r4 = r4 + r5;
        r4 = (float) r4;
        r4 = r4 / r11;
        r0 = r0 + r1;
        r0 = (float) r0;
        r0 = r0 / r11;
        r1 = new com.google.zxing.Result;
        r5 = 0;
        r6 = 2;
        r6 = new com.google.zxing.ResultPoint[r6];
        r7 = new com.google.zxing.ResultPoint;
        r8 = (float) r13;
        r7.<init>(r4, r8);
        r6[r2] = r7;
        r2 = new com.google.zxing.ResultPoint;
        r4 = (float) r13;
        r2.<init>(r0, r4);
        r6[r10] = r2;
        r0 = com.google.zxing.BarcodeFormat.CODE_39;
        r1.<init>(r3, r5, r6, r0);
        return r1;
    L_0x00de:
        r3 = r7.toString();
        goto L_0x00b8;
    L_0x00e3:
        r0 = r1;
        goto L_0x0024;
    L_0x00e6:
        r1 = r3;
        goto L_0x0044;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code39Reader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Hashtable):com.google.zxing.Result");
    }
}
