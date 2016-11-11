package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.DecoderResult;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.p122a.p123a.p124f.p125c.C3022o;

final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int ALPHA = 0;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final String[] EXP900;
    private static final int LL = 27;
    private static final int LOWER = 1;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final int MIXED = 2;
    private static final char[] MIXED_CHARS;
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final int PUNCT = 3;
    private static final char[] PUNCT_CHARS;
    private static final int PUNCT_SHIFT = 4;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;

    static {
        PUNCT_CHARS = new char[]{';', '<', '>', '@', '[', C3022o.f15058f, '}', '_', '`', '~', '!', C3022o.f15053a, '\t', ',', ':', '\n', SignatureVisitor.SUPER, '.', '$', '/', C3022o.f15057e, '|', '*', '(', ')', '?', '{', '}', '\''};
        MIXED_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '&', C3022o.f15053a, '\t', ',', ':', '#', SignatureVisitor.SUPER, '.', '$', '/', SignatureVisitor.EXTENDS, '%', '*', SignatureVisitor.INSTANCEOF, '^'};
        EXP900 = new String[]{"000000000000000000000000000000000000000000001", "000000000000000000000000000000000000000000900", "000000000000000000000000000000000000000810000", "000000000000000000000000000000000000729000000", "000000000000000000000000000000000656100000000", "000000000000000000000000000000590490000000000", "000000000000000000000000000531441000000000000", "000000000000000000000000478296900000000000000", "000000000000000000000430467210000000000000000", "000000000000000000387420489000000000000000000", "000000000000000348678440100000000000000000000", "000000000000313810596090000000000000000000000", "000000000282429536481000000000000000000000000", "000000254186582832900000000000000000000000000", "000228767924549610000000000000000000000000000", "205891132094649000000000000000000000000000000"};
    }

    private DecodedBitStreamParser() {
    }

    private static StringBuffer add(String str, String str2) {
        int i;
        StringBuffer stringBuffer = new StringBuffer(5);
        StringBuffer stringBuffer2 = new StringBuffer(5);
        StringBuffer stringBuffer3 = new StringBuffer(str.length());
        for (i = ALPHA; i < str.length(); i += LOWER) {
            stringBuffer3.append('0');
        }
        int i2 = ALPHA;
        for (i = str.length() - 3; i > -1; i -= 3) {
            stringBuffer.setLength(ALPHA);
            stringBuffer.append(str.charAt(i));
            stringBuffer.append(str.charAt(i + LOWER));
            stringBuffer.append(str.charAt(i + MIXED));
            stringBuffer2.setLength(ALPHA);
            stringBuffer2.append(str2.charAt(i));
            stringBuffer2.append(str2.charAt(i + LOWER));
            stringBuffer2.append(str2.charAt(i + MIXED));
            int parseInt = Integer.parseInt(stringBuffer.toString());
            int parseInt2 = Integer.parseInt(stringBuffer2.toString());
            int i3 = ((parseInt + parseInt2) + i2) % XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
            i2 = (i2 + (parseInt + parseInt2)) / XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
            stringBuffer3.setCharAt(i + MIXED, (char) ((i3 % 10) + 48));
            stringBuffer3.setCharAt(i + LOWER, (char) (((i3 / 10) % 10) + 48));
            stringBuffer3.setCharAt(i, (char) ((i3 / 100) + 48));
        }
        return stringBuffer3;
    }

    private static int byteCompaction(int i, int[] iArr, int i2, StringBuffer stringBuffer) {
        int i3;
        long j;
        char[] cArr;
        Object obj;
        int i4;
        if (i == BYTE_COMPACTION_MODE_LATCH) {
            i3 = ALPHA;
            j = 0;
            cArr = new char[6];
            int[] iArr2 = new int[6];
            obj = null;
            while (i2 < iArr[ALPHA] && r0 == null) {
                i4 = i2 + LOWER;
                int i5 = iArr[i2];
                if (i5 < TEXT_COMPACTION_MODE_LATCH) {
                    iArr2[i3] = i5;
                    i3 += LOWER;
                    j = (j * 900) + ((long) i5);
                    i2 = i4;
                } else if (i5 == TEXT_COMPACTION_MODE_LATCH || i5 == BYTE_COMPACTION_MODE_LATCH || i5 == NUMERIC_COMPACTION_MODE_LATCH || i5 == BYTE_COMPACTION_MODE_LATCH_6 || i5 == BEGIN_MACRO_PDF417_CONTROL_BLOCK || i5 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || i5 == MACRO_PDF417_TERMINATOR) {
                    i2 = i4 - 1;
                    obj = LOWER;
                } else {
                    i2 = i4;
                }
                if (i3 % 5 == 0 && i3 > 0) {
                    for (i3 = ALPHA; i3 < 6; i3 += LOWER) {
                        cArr[5 - i3] = (char) ((int) (j % 256));
                        j >>= 8;
                    }
                    stringBuffer.append(cArr);
                    i3 = ALPHA;
                }
            }
            for (int i6 = (i3 / 5) * 5; i6 < i3; i6 += LOWER) {
                stringBuffer.append((char) iArr2[i6]);
            }
        } else if (i == BYTE_COMPACTION_MODE_LATCH_6) {
            i3 = ALPHA;
            j = 0;
            obj = null;
            while (i2 < iArr[ALPHA] && r0 == null) {
                i4 = i2 + LOWER;
                int i7 = iArr[i2];
                if (i7 < TEXT_COMPACTION_MODE_LATCH) {
                    i3 += LOWER;
                    j = (j * 900) + ((long) i7);
                    i2 = i4;
                } else if (i7 == TEXT_COMPACTION_MODE_LATCH || i7 == BYTE_COMPACTION_MODE_LATCH || i7 == NUMERIC_COMPACTION_MODE_LATCH || i7 == BYTE_COMPACTION_MODE_LATCH_6 || i7 == BEGIN_MACRO_PDF417_CONTROL_BLOCK || i7 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || i7 == MACRO_PDF417_TERMINATOR) {
                    i2 = i4 - 1;
                    obj = LOWER;
                } else {
                    i2 = i4;
                }
                if (i3 % 5 == 0 && i3 > 0) {
                    cArr = new char[6];
                    i4 = ALPHA;
                    while (i4 < 6) {
                        cArr[5 - i4] = (char) ((int) (255 & j));
                        i4 += LOWER;
                        j >>= 8;
                    }
                    stringBuffer.append(cArr);
                }
            }
        }
        return i2;
    }

    static DecoderResult decode(int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer(100);
        int i = MIXED;
        int i2 = iArr[LOWER];
        while (i < iArr[ALPHA]) {
            switch (i2) {
                case TEXT_COMPACTION_MODE_LATCH /*900*/:
                    i2 = textCompaction(iArr, i, stringBuffer);
                    break;
                case BYTE_COMPACTION_MODE_LATCH /*901*/:
                    i2 = byteCompaction(i2, iArr, i, stringBuffer);
                    break;
                case NUMERIC_COMPACTION_MODE_LATCH /*902*/:
                    i2 = numericCompaction(iArr, i, stringBuffer);
                    break;
                case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /*913*/:
                    i2 = byteCompaction(i2, iArr, i, stringBuffer);
                    break;
                case BYTE_COMPACTION_MODE_LATCH_6 /*924*/:
                    i2 = byteCompaction(i2, iArr, i, stringBuffer);
                    break;
                default:
                    i2 = textCompaction(iArr, i - 1, stringBuffer);
                    break;
            }
            if (i2 < iArr.length) {
                i = i2 + LOWER;
                i2 = iArr[i2];
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        return new DecoderResult(null, stringBuffer.toString(), null, null);
    }

    private static String decodeBase900toBase10(int[] iArr, int i) {
        String substring;
        int i2 = ALPHA;
        StringBuffer stringBuffer = null;
        while (i2 < i) {
            StringBuffer multiply = multiply(EXP900[(i - i2) - 1], iArr[i2]);
            if (stringBuffer != null) {
                multiply = add(stringBuffer.toString(), multiply.toString());
            }
            i2 += LOWER;
            stringBuffer = multiply;
        }
        for (int i3 = ALPHA; i3 < stringBuffer.length(); i3 += LOWER) {
            if (stringBuffer.charAt(i3) == '1') {
                substring = stringBuffer.toString().substring(i3 + LOWER);
                break;
            }
        }
        substring = null;
        return substring == null ? stringBuffer.toString() : substring;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void decodeTextCompaction(int[] r11, int[] r12, int r13, java.lang.StringBuffer r14) {
        /*
        r9 = 913; // 0x391 float:1.28E-42 double:4.51E-321;
        r8 = 28;
        r7 = 26;
        r6 = 29;
        r1 = 0;
        r4 = r1;
        r2 = r1;
        r3 = r1;
    L_0x000c:
        if (r4 >= r13) goto L_0x00c8;
    L_0x000e:
        r0 = r11[r4];
        switch(r3) {
            case 0: goto L_0x001d;
            case 1: goto L_0x0046;
            case 2: goto L_0x006d;
            case 3: goto L_0x00a0;
            case 4: goto L_0x00b9;
            default: goto L_0x0013;
        };
    L_0x0013:
        r0 = r1;
    L_0x0014:
        if (r0 == 0) goto L_0x0019;
    L_0x0016:
        r14.append(r0);
    L_0x0019:
        r0 = r4 + 1;
        r4 = r0;
        goto L_0x000c;
    L_0x001d:
        if (r0 >= r7) goto L_0x0023;
    L_0x001f:
        r0 = r0 + 65;
        r0 = (char) r0;
        goto L_0x0014;
    L_0x0023:
        if (r0 != r7) goto L_0x0028;
    L_0x0025:
        r0 = 32;
        goto L_0x0014;
    L_0x0028:
        r5 = 27;
        if (r0 != r5) goto L_0x002f;
    L_0x002c:
        r3 = 1;
        r0 = r1;
        goto L_0x0014;
    L_0x002f:
        if (r0 != r8) goto L_0x0034;
    L_0x0031:
        r3 = 2;
        r0 = r1;
        goto L_0x0014;
    L_0x0034:
        if (r0 != r6) goto L_0x003c;
    L_0x0036:
        r2 = 4;
        r0 = r1;
        r10 = r3;
        r3 = r2;
        r2 = r10;
        goto L_0x0014;
    L_0x003c:
        if (r0 != r9) goto L_0x0013;
    L_0x003e:
        r0 = r12[r4];
        r0 = (char) r0;
        r14.append(r0);
        r0 = r1;
        goto L_0x0014;
    L_0x0046:
        if (r0 >= r7) goto L_0x004c;
    L_0x0048:
        r0 = r0 + 97;
        r0 = (char) r0;
        goto L_0x0014;
    L_0x004c:
        if (r0 != r7) goto L_0x0051;
    L_0x004e:
        r0 = 32;
        goto L_0x0014;
    L_0x0051:
        if (r0 != r8) goto L_0x0056;
    L_0x0053:
        r0 = r1;
        r3 = r1;
        goto L_0x0014;
    L_0x0056:
        if (r0 != r8) goto L_0x005b;
    L_0x0058:
        r3 = 2;
        r0 = r1;
        goto L_0x0014;
    L_0x005b:
        if (r0 != r6) goto L_0x0063;
    L_0x005d:
        r2 = 4;
        r0 = r1;
        r10 = r3;
        r3 = r2;
        r2 = r10;
        goto L_0x0014;
    L_0x0063:
        if (r0 != r9) goto L_0x0013;
    L_0x0065:
        r0 = r12[r4];
        r0 = (char) r0;
        r14.append(r0);
        r0 = r1;
        goto L_0x0014;
    L_0x006d:
        r5 = 25;
        if (r0 >= r5) goto L_0x0076;
    L_0x0071:
        r5 = MIXED_CHARS;
        r0 = r5[r0];
        goto L_0x0014;
    L_0x0076:
        r5 = 25;
        if (r0 != r5) goto L_0x007d;
    L_0x007a:
        r3 = 3;
        r0 = r1;
        goto L_0x0014;
    L_0x007d:
        if (r0 != r7) goto L_0x0082;
    L_0x007f:
        r0 = 32;
        goto L_0x0014;
    L_0x0082:
        r5 = 27;
        if (r0 != r5) goto L_0x0088;
    L_0x0086:
        r0 = r1;
        goto L_0x0014;
    L_0x0088:
        if (r0 != r8) goto L_0x008d;
    L_0x008a:
        r0 = r1;
        r3 = r1;
        goto L_0x0014;
    L_0x008d:
        if (r0 != r6) goto L_0x0095;
    L_0x008f:
        r2 = 4;
        r0 = r1;
        r10 = r3;
        r3 = r2;
        r2 = r10;
        goto L_0x0014;
    L_0x0095:
        if (r0 != r9) goto L_0x0013;
    L_0x0097:
        r0 = r12[r4];
        r0 = (char) r0;
        r14.append(r0);
        r0 = r1;
        goto L_0x0014;
    L_0x00a0:
        if (r0 >= r6) goto L_0x00a8;
    L_0x00a2:
        r5 = PUNCT_CHARS;
        r0 = r5[r0];
        goto L_0x0014;
    L_0x00a8:
        if (r0 != r6) goto L_0x00ae;
    L_0x00aa:
        r0 = r1;
        r3 = r1;
        goto L_0x0014;
    L_0x00ae:
        if (r0 != r9) goto L_0x0013;
    L_0x00b0:
        r0 = r12[r4];
        r0 = (char) r0;
        r14.append(r0);
        r0 = r1;
        goto L_0x0014;
    L_0x00b9:
        if (r0 >= r6) goto L_0x00c2;
    L_0x00bb:
        r3 = PUNCT_CHARS;
        r0 = r3[r0];
        r3 = r2;
        goto L_0x0014;
    L_0x00c2:
        if (r0 != r6) goto L_0x00c9;
    L_0x00c4:
        r0 = r1;
        r3 = r1;
        goto L_0x0014;
    L_0x00c8:
        return;
    L_0x00c9:
        r0 = r1;
        r3 = r2;
        goto L_0x0014;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decodeTextCompaction(int[], int[], int, java.lang.StringBuffer):void");
    }

    private static StringBuffer multiply(String str, int i) {
        int i2 = ALPHA;
        StringBuffer stringBuffer = new StringBuffer(str.length());
        for (int i3 = ALPHA; i3 < str.length(); i3 += LOWER) {
            stringBuffer.append('0');
        }
        int i4 = i / 100;
        int i5 = (i / 10) % 10;
        int i6 = i % 10;
        StringBuffer stringBuffer2 = stringBuffer;
        int i7 = ALPHA;
        while (i7 < i6) {
            i7 += LOWER;
            stringBuffer2 = add(stringBuffer2.toString(), str);
        }
        i7 = ALPHA;
        while (i7 < i5) {
            i7 += LOWER;
            stringBuffer2 = add(stringBuffer2.toString(), new StringBuffer().append(str).append('0').toString().substring(LOWER));
        }
        while (i2 < i4) {
            stringBuffer2 = add(stringBuffer2.toString(), new StringBuffer().append(str).append("00").toString().substring(MIXED));
            i2 += LOWER;
        }
        return stringBuffer2;
    }

    private static int numericCompaction(int[] iArr, int i, StringBuffer stringBuffer) {
        int[] iArr2 = new int[MAX_NUMERIC_CODEWORDS];
        int i2 = ALPHA;
        int i3 = ALPHA;
        while (i < iArr[ALPHA] && r0 == 0) {
            int i4 = i + LOWER;
            int i5 = iArr[i];
            if (i4 == iArr[ALPHA]) {
                i2 = LOWER;
            }
            if (i5 < TEXT_COMPACTION_MODE_LATCH) {
                iArr2[i3] = i5;
                i3 += LOWER;
                i = i4;
            } else if (i5 == TEXT_COMPACTION_MODE_LATCH || i5 == BYTE_COMPACTION_MODE_LATCH || i5 == BYTE_COMPACTION_MODE_LATCH_6 || i5 == BEGIN_MACRO_PDF417_CONTROL_BLOCK || i5 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || i5 == MACRO_PDF417_TERMINATOR) {
                i = i4 - 1;
                i2 = LOWER;
            } else {
                i = i4;
            }
            if (i3 % MAX_NUMERIC_CODEWORDS == 0 || i5 == NUMERIC_COMPACTION_MODE_LATCH || r0 != 0) {
                stringBuffer.append(decodeBase900toBase10(iArr2, i3));
                i3 = ALPHA;
            }
        }
        return i;
    }

    private static int textCompaction(int[] iArr, int i, StringBuffer stringBuffer) {
        int[] iArr2 = new int[(iArr[ALPHA] << LOWER)];
        int[] iArr3 = new int[(iArr[ALPHA] << LOWER)];
        int i2 = ALPHA;
        int i3 = ALPHA;
        while (i < iArr[ALPHA] && r0 == 0) {
            int i4 = i + LOWER;
            int i5 = iArr[i];
            if (i5 >= TEXT_COMPACTION_MODE_LATCH) {
                switch (i5) {
                    case TEXT_COMPACTION_MODE_LATCH /*900*/:
                        i = i4 - 1;
                        i2 = LOWER;
                        break;
                    case BYTE_COMPACTION_MODE_LATCH /*901*/:
                        i = i4 - 1;
                        i2 = LOWER;
                        break;
                    case NUMERIC_COMPACTION_MODE_LATCH /*902*/:
                        i = i4 - 1;
                        i2 = LOWER;
                        break;
                    case MODE_SHIFT_TO_BYTE_COMPACTION_MODE /*913*/:
                        iArr2[i3] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                        iArr3[i3] = i5;
                        i3 += LOWER;
                        i = i4;
                        break;
                    case BYTE_COMPACTION_MODE_LATCH_6 /*924*/:
                        i = i4 - 1;
                        i2 = LOWER;
                        break;
                    default:
                        i = i4;
                        break;
                }
            }
            iArr2[i3] = i5 / 30;
            iArr2[i3 + LOWER] = i5 % 30;
            i3 += MIXED;
            i = i4;
        }
        decodeTextCompaction(iArr2, iArr3, i3, stringBuffer);
        return i;
    }
}
