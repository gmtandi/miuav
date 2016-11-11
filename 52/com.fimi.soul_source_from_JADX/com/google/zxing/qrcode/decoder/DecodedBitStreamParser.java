package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.StringUtils;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Vector;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.p124f.p125c.C3022o;

final class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS;

    static {
        ALPHANUMERIC_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', C3022o.f15055c, '$', '%', '*', SignatureVisitor.EXTENDS, SignatureVisitor.SUPER, '.', '/', ':'};
    }

    private DecodedBitStreamParser() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.zxing.common.DecoderResult decode(byte[] r11, com.google.zxing.qrcode.decoder.Version r12, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel r13, java.util.Hashtable r14) {
        /*
        r8 = 0;
        r10 = 4;
        r7 = 1;
        r0 = new com.google.zxing.common.BitSource;
        r0.<init>(r11);
        r1 = new java.lang.StringBuffer;
        r2 = 50;
        r1.<init>(r2);
        r6 = 0;
        r4 = new java.util.Vector;
        r4.<init>(r7);
        r3 = r8;
    L_0x0016:
        r2 = r0.available();
        if (r2 >= r10) goto L_0x0050;
    L_0x001c:
        r2 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR;
        r9 = r2;
    L_0x001f:
        r2 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR;
        r2 = r9.equals(r2);
        if (r2 != 0) goto L_0x00cf;
    L_0x0027:
        r2 = com.google.zxing.qrcode.decoder.Mode.FNC1_FIRST_POSITION;
        r2 = r9.equals(r2);
        if (r2 != 0) goto L_0x0037;
    L_0x002f:
        r2 = com.google.zxing.qrcode.decoder.Mode.FNC1_SECOND_POSITION;
        r2 = r9.equals(r2);
        if (r2 == 0) goto L_0x0061;
    L_0x0037:
        r2 = r7;
    L_0x0038:
        r5 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR;
        r5 = r9.equals(r5);
        if (r5 == 0) goto L_0x00d2;
    L_0x0040:
        r0 = new com.google.zxing.common.DecoderResult;
        r1 = r1.toString();
        r2 = r4.isEmpty();
        if (r2 == 0) goto L_0x00cc;
    L_0x004c:
        r0.<init>(r11, r1, r8, r13);
        return r0;
    L_0x0050:
        r2 = 4;
        r2 = r0.readBits(r2);	 Catch:{ IllegalArgumentException -> 0x005b }
        r2 = com.google.zxing.qrcode.decoder.Mode.forBits(r2);	 Catch:{ IllegalArgumentException -> 0x005b }
        r9 = r2;
        goto L_0x001f;
    L_0x005b:
        r0 = move-exception;
        r0 = com.google.zxing.FormatException.getFormatInstance();
        throw r0;
    L_0x0061:
        r2 = com.google.zxing.qrcode.decoder.Mode.STRUCTURED_APPEND;
        r2 = r9.equals(r2);
        if (r2 == 0) goto L_0x0070;
    L_0x0069:
        r2 = 16;
        r0.readBits(r2);
        r2 = r6;
        goto L_0x0038;
    L_0x0070:
        r2 = com.google.zxing.qrcode.decoder.Mode.ECI;
        r2 = r9.equals(r2);
        if (r2 == 0) goto L_0x0089;
    L_0x0078:
        r2 = parseECIValue(r0);
        r3 = com.google.zxing.common.CharacterSetECI.getCharacterSetECIByValue(r2);
        if (r3 != 0) goto L_0x0087;
    L_0x0082:
        r0 = com.google.zxing.FormatException.getFormatInstance();
        throw r0;
    L_0x0087:
        r2 = r6;
        goto L_0x0038;
    L_0x0089:
        r2 = r9.getCharacterCountBits(r12);
        r2 = r0.readBits(r2);
        r5 = com.google.zxing.qrcode.decoder.Mode.NUMERIC;
        r5 = r9.equals(r5);
        if (r5 == 0) goto L_0x009e;
    L_0x0099:
        decodeNumericSegment(r0, r1, r2);
        r2 = r6;
        goto L_0x0038;
    L_0x009e:
        r5 = com.google.zxing.qrcode.decoder.Mode.ALPHANUMERIC;
        r5 = r9.equals(r5);
        if (r5 == 0) goto L_0x00ab;
    L_0x00a6:
        decodeAlphanumericSegment(r0, r1, r2, r6);
        r2 = r6;
        goto L_0x0038;
    L_0x00ab:
        r5 = com.google.zxing.qrcode.decoder.Mode.BYTE;
        r5 = r9.equals(r5);
        if (r5 == 0) goto L_0x00b9;
    L_0x00b3:
        r5 = r14;
        decodeByteSegment(r0, r1, r2, r3, r4, r5);
        r2 = r6;
        goto L_0x0038;
    L_0x00b9:
        r5 = com.google.zxing.qrcode.decoder.Mode.KANJI;
        r5 = r9.equals(r5);
        if (r5 == 0) goto L_0x00c7;
    L_0x00c1:
        decodeKanjiSegment(r0, r1, r2);
        r2 = r6;
        goto L_0x0038;
    L_0x00c7:
        r0 = com.google.zxing.FormatException.getFormatInstance();
        throw r0;
    L_0x00cc:
        r8 = r4;
        goto L_0x004c;
    L_0x00cf:
        r2 = r6;
        goto L_0x0038;
    L_0x00d2:
        r6 = r2;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.DecodedBitStreamParser.decode(byte[], com.google.zxing.qrcode.decoder.Version, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel, java.util.Hashtable):com.google.zxing.common.DecoderResult");
    }

    private static void decodeAlphanumericSegment(BitSource bitSource, StringBuffer stringBuffer, int i, boolean z) {
        int length = stringBuffer.length();
        while (i > 1) {
            int readBits = bitSource.readBits(11);
            stringBuffer.append(toAlphaNumericChar(readBits / 45));
            stringBuffer.append(toAlphaNumericChar(readBits % 45));
            i -= 2;
        }
        if (i == 1) {
            stringBuffer.append(toAlphaNumericChar(bitSource.readBits(6)));
        }
        if (z) {
            while (length < stringBuffer.length()) {
                if (stringBuffer.charAt(length) == '%') {
                    if (length >= stringBuffer.length() - 1 || stringBuffer.charAt(length + 1) != '%') {
                        stringBuffer.setCharAt(length, '\u001d');
                    } else {
                        stringBuffer.deleteCharAt(length + 1);
                    }
                }
                length++;
            }
        }
    }

    private static void decodeByteSegment(BitSource bitSource, StringBuffer stringBuffer, int i, CharacterSetECI characterSetECI, Vector vector, Hashtable hashtable) {
        Object obj = new byte[i];
        if ((i << 3) > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        for (int i2 = 0; i2 < i; i2++) {
            obj[i2] = (byte) bitSource.readBits(8);
        }
        try {
            stringBuffer.append(new String(obj, characterSetECI == null ? StringUtils.guessEncoding(obj, hashtable) : characterSetECI.getEncodingName()));
            vector.addElement(obj);
        } catch (UnsupportedEncodingException e) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeKanjiSegment(BitSource bitSource, StringBuffer stringBuffer, int i) {
        byte[] bArr = new byte[(i * 2)];
        int i2 = 0;
        while (i > 0) {
            int readBits = bitSource.readBits(13);
            readBits = (readBits % SmileConstants.TOKEN_PREFIX_SMALL_INT) | ((readBits / SmileConstants.TOKEN_PREFIX_SMALL_INT) << 8);
            readBits = readBits < 7936 ? readBits + 33088 : readBits + 49472;
            bArr[i2] = (byte) (readBits >> 8);
            bArr[i2 + 1] = (byte) readBits;
            i--;
            i2 += 2;
        }
        try {
            stringBuffer.append(new String(bArr, StringUtils.SHIFT_JIS));
        } catch (UnsupportedEncodingException e) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeNumericSegment(BitSource bitSource, StringBuffer stringBuffer, int i) {
        while (i >= 3) {
            int readBits = bitSource.readBits(10);
            if (readBits >= XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) {
                throw FormatException.getFormatInstance();
            }
            stringBuffer.append(toAlphaNumericChar(readBits / 100));
            stringBuffer.append(toAlphaNumericChar((readBits / 10) % 10));
            stringBuffer.append(toAlphaNumericChar(readBits % 10));
            i -= 3;
        }
        if (i == 2) {
            readBits = bitSource.readBits(7);
            if (readBits >= 100) {
                throw FormatException.getFormatInstance();
            }
            stringBuffer.append(toAlphaNumericChar(readBits / 10));
            stringBuffer.append(toAlphaNumericChar(readBits % 10));
        } else if (i == 1) {
            readBits = bitSource.readBits(4);
            if (readBits >= 10) {
                throw FormatException.getFormatInstance();
            }
            stringBuffer.append(toAlphaNumericChar(readBits));
        }
    }

    private static int parseECIValue(BitSource bitSource) {
        int readBits = bitSource.readBits(8);
        if ((readBits & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) == 0) {
            return readBits & Opcodes.LAND;
        }
        if ((readBits & SmileConstants.TOKEN_PREFIX_SMALL_INT) == SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            return ((readBits & 63) << 8) | bitSource.readBits(8);
        } else if ((readBits & SmileConstants.TOKEN_PREFIX_MISC_OTHER) == SmileConstants.TOKEN_PREFIX_SMALL_INT) {
            return ((readBits & 31) << 16) | bitSource.readBits(16);
        } else {
            throw new IllegalArgumentException(new StringBuffer().append("Bad ECI bits starting with byte ").append(readBits).toString());
        }
    }

    private static char toAlphaNumericChar(int i) {
        if (i < ALPHANUMERIC_CHARS.length) {
            return ALPHANUMERIC_CHARS[i];
        }
        throw FormatException.getFormatInstance();
    }
}
