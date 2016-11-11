package org.codehaus.jackson.io;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.tencent.mm.sdk.platformtools.Util;
import java.lang.ref.SoftReference;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.ByteArrayBuilder;
import org.codehaus.jackson.util.CharTypes;
import org.codehaus.jackson.util.TextBuffer;
import org.p122a.p123a.p124f.p125c.C3022o;

public final class JsonStringEncoder {
    private static final byte[] HEX_BYTES;
    private static final char[] HEX_CHARS;
    private static final int INT_0 = 48;
    private static final int INT_BACKSLASH = 92;
    private static final int INT_U = 117;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    protected static final ThreadLocal<SoftReference<JsonStringEncoder>> _threadEncoder;
    protected ByteArrayBuilder _byteBuilder;
    protected final char[] _quoteBuffer;
    protected TextBuffer _textBuffer;

    static {
        HEX_CHARS = CharTypes.copyHexChars();
        HEX_BYTES = CharTypes.copyHexBytes();
        _threadEncoder = new ThreadLocal();
    }

    public JsonStringEncoder() {
        this._quoteBuffer = new char[6];
        this._quoteBuffer[0] = C3022o.f15058f;
        this._quoteBuffer[2] = '0';
        this._quoteBuffer[3] = '0';
    }

    private int _appendByteEscape(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        byteArrayBuilder.setCurrentSegmentLength(i3);
        byteArrayBuilder.append(INT_BACKSLASH);
        if (i2 < 0) {
            byteArrayBuilder.append(INT_U);
            if (i > Util.MASK_8BIT) {
                int i4 = i >> 8;
                byteArrayBuilder.append(HEX_BYTES[i4 >> 4]);
                byteArrayBuilder.append(HEX_BYTES[i4 & 15]);
                i &= Util.MASK_8BIT;
            } else {
                byteArrayBuilder.append(INT_0);
                byteArrayBuilder.append(INT_0);
            }
            byteArrayBuilder.append(HEX_BYTES[i >> 4]);
            byteArrayBuilder.append(HEX_BYTES[i & 15]);
        } else {
            byteArrayBuilder.append((byte) i2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    private int _appendSingleEscape(int i, char[] cArr) {
        if (i < 0) {
            int i2 = -(i + 1);
            cArr[1] = 'u';
            cArr[4] = HEX_CHARS[i2 >> 4];
            cArr[5] = HEX_CHARS[i2 & 15];
            return 6;
        }
        cArr[1] = (char) i;
        return 2;
    }

    private int _convertSurrogate(int i, int i2) {
        if (i2 >= SURR2_FIRST && i2 <= SURR2_LAST) {
            return (AccessibilityNodeInfoCompat.ACTION_CUT + ((i - SURR1_FIRST) << 10)) + (i2 - SURR2_FIRST);
        }
        throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
    }

    private void _throwIllegalSurrogate(int i) {
        if (i > 1114111) {
            throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(i) + ") to output; max is 0x10FFFF as per RFC 4627");
        } else if (i < SURR1_FIRST) {
            throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(i) + ") to output");
        } else if (i <= SURR1_LAST) {
            throw new IllegalArgumentException("Unmatched first part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        } else {
            throw new IllegalArgumentException("Unmatched second part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        }
    }

    public static JsonStringEncoder getInstance() {
        SoftReference softReference = (SoftReference) _threadEncoder.get();
        JsonStringEncoder jsonStringEncoder = softReference == null ? null : (JsonStringEncoder) softReference.get();
        if (jsonStringEncoder != null) {
            return jsonStringEncoder;
        }
        jsonStringEncoder = new JsonStringEncoder();
        _threadEncoder.set(new SoftReference(jsonStringEncoder));
        return jsonStringEncoder;
    }

    public byte[] encodeAsUTF8(String str) {
        int i;
        ByteArrayBuilder byteArrayBuilder = this._byteBuilder;
        if (byteArrayBuilder == null) {
            byteArrayBuilder = new ByteArrayBuilder(null);
            this._byteBuilder = byteArrayBuilder;
        }
        int length = str.length();
        byte[] resetAndGetFirstSegment = byteArrayBuilder.resetAndGetFirstSegment();
        int length2 = resetAndGetFirstSegment.length;
        int i2 = 0;
        int i3 = 0;
        loop0:
        while (i3 < length) {
            int i4;
            int i5 = i3 + 1;
            i3 = str.charAt(i3);
            int i6 = length2;
            byte[] bArr = resetAndGetFirstSegment;
            int i7 = i2;
            i2 = i6;
            while (i3 <= Opcodes.LAND) {
                if (i7 >= i2) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i2 = bArr.length;
                    i7 = 0;
                }
                i4 = i7 + 1;
                bArr[i7] = (byte) i3;
                if (i5 >= length) {
                    i = i4;
                    break loop0;
                }
                i7 = i5 + 1;
                i3 = str.charAt(i5);
                i5 = i7;
                i7 = i4;
            }
            if (i7 >= i2) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                i2 = bArr.length;
                i4 = 0;
            } else {
                i4 = i7;
            }
            if (i3 < Opcodes.ACC_STRICT) {
                i7 = i4 + 1;
                bArr[i4] = (byte) ((i3 >> 6) | SmileConstants.TOKEN_PREFIX_SMALL_INT);
                i4 = i3;
                i3 = i5;
            } else if (i3 < SURR1_FIRST || i3 > SURR2_LAST) {
                i7 = i4 + 1;
                bArr[i4] = (byte) ((i3 >> 12) | SmileConstants.TOKEN_PREFIX_MISC_OTHER);
                if (i7 >= i2) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i2 = bArr.length;
                    i7 = 0;
                }
                i4 = i7 + 1;
                bArr[i7] = (byte) (((i3 >> 6) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                i7 = i4;
                i4 = i3;
                i3 = i5;
            } else {
                if (i3 > SURR1_LAST) {
                    _throwIllegalSurrogate(i3);
                }
                if (i5 >= length) {
                    _throwIllegalSurrogate(i3);
                }
                int i8 = i5 + 1;
                i3 = _convertSurrogate(i3, str.charAt(i5));
                if (i3 > 1114111) {
                    _throwIllegalSurrogate(i3);
                }
                i7 = i4 + 1;
                bArr[i4] = (byte) ((i3 >> 18) | 240);
                if (i7 >= i2) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i2 = bArr.length;
                    i7 = 0;
                }
                i4 = i7 + 1;
                bArr[i7] = (byte) (((i3 >> 12) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                if (i4 >= i2) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i2 = bArr.length;
                    i7 = 0;
                } else {
                    i7 = i4;
                }
                i4 = i7 + 1;
                bArr[i7] = (byte) (((i3 >> 6) & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
                i7 = i4;
                i4 = i3;
                i3 = i8;
            }
            if (i7 >= i2) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                i2 = bArr.length;
                i7 = 0;
            }
            i5 = i7 + 1;
            bArr[i7] = (byte) ((i4 & 63) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            resetAndGetFirstSegment = bArr;
            length2 = i2;
            i2 = i5;
        }
        i = i2;
        return this._byteBuilder.completeAndCoalesce(i);
    }

    public char[] quoteAsString(String str) {
        TextBuffer textBuffer = this._textBuffer;
        if (textBuffer == null) {
            textBuffer = new TextBuffer(null);
            this._textBuffer = textBuffer;
        }
        Object emptyAndGetCurrentSegment = textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = CharTypes.get7BitOutputEscapes();
        char length = iArr.length;
        int length2 = str.length();
        int i = 0;
        int i2 = 0;
        loop0:
        while (i2 < length2) {
            int i3;
            while (true) {
                char charAt = str.charAt(i2);
                if (charAt < length && iArr[charAt] != 0) {
                    break;
                }
                if (i >= emptyAndGetCurrentSegment.length) {
                    emptyAndGetCurrentSegment = textBuffer.finishCurrentSegment();
                    i3 = 0;
                } else {
                    i3 = i;
                }
                i = i3 + 1;
                emptyAndGetCurrentSegment[i3] = charAt;
                i2++;
                if (i2 >= length2) {
                    break loop0;
                }
            }
            i3 = i2 + 1;
            i2 = _appendSingleEscape(iArr[str.charAt(i2)], this._quoteBuffer);
            if (i + i2 > emptyAndGetCurrentSegment.length) {
                int length3 = emptyAndGetCurrentSegment.length - i;
                if (length3 > 0) {
                    System.arraycopy(this._quoteBuffer, 0, emptyAndGetCurrentSegment, i, length3);
                }
                emptyAndGetCurrentSegment = textBuffer.finishCurrentSegment();
                i2 -= length3;
                System.arraycopy(this._quoteBuffer, length3, emptyAndGetCurrentSegment, i, i2);
                i += i2;
            } else {
                System.arraycopy(this._quoteBuffer, 0, emptyAndGetCurrentSegment, i, i2);
                i += i2;
            }
            i2 = i3;
        }
        textBuffer.setCurrentLength(i);
        return textBuffer.contentsAsArray();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] quoteAsUTF8(java.lang.String r12) {
        /*
        r11 = this;
        r9 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        r5 = 0;
        r0 = r11._byteBuilder;
        if (r0 != 0) goto L_0x000f;
    L_0x0007:
        r0 = new org.codehaus.jackson.util.ByteArrayBuilder;
        r1 = 0;
        r0.<init>(r1);
        r11._byteBuilder = r0;
    L_0x000f:
        r7 = r12.length();
        r1 = r0.resetAndGetFirstSegment();
        r2 = r5;
        r3 = r5;
    L_0x0019:
        if (r3 >= r7) goto L_0x0056;
    L_0x001b:
        r6 = org.codehaus.jackson.util.CharTypes.get7BitOutputEscapes();
    L_0x001f:
        r8 = r12.charAt(r3);
        if (r8 > r9) goto L_0x0029;
    L_0x0025:
        r4 = r6[r8];
        if (r4 == 0) goto L_0x0045;
    L_0x0029:
        r4 = r1.length;
        if (r2 < r4) goto L_0x0031;
    L_0x002c:
        r1 = r0.finishCurrentSegment();
        r2 = r5;
    L_0x0031:
        r4 = r3 + 1;
        r8 = r12.charAt(r3);
        if (r8 > r9) goto L_0x005d;
    L_0x0039:
        r1 = r6[r8];
        r2 = r11._appendByteEscape(r8, r1, r0, r2);
        r1 = r0.getCurrentSegment();
        r3 = r4;
        goto L_0x0019;
    L_0x0045:
        r4 = r1.length;
        if (r2 < r4) goto L_0x010e;
    L_0x0048:
        r1 = r0.finishCurrentSegment();
        r4 = r5;
    L_0x004d:
        r2 = r4 + 1;
        r8 = (byte) r8;
        r1[r4] = r8;
        r3 = r3 + 1;
        if (r3 < r7) goto L_0x001f;
    L_0x0056:
        r0 = r11._byteBuilder;
        r0 = r0.completeAndCoalesce(r2);
        return r0;
    L_0x005d:
        r3 = 2047; // 0x7ff float:2.868E-42 double:1.0114E-320;
        if (r8 > r3) goto L_0x0082;
    L_0x0061:
        r3 = r2 + 1;
        r6 = r8 >> 6;
        r6 = r6 | 192;
        r6 = (byte) r6;
        r1[r2] = r6;
        r2 = r8 & 63;
        r2 = r2 | 128;
        r10 = r2;
        r2 = r1;
        r1 = r10;
    L_0x0071:
        r6 = r2.length;
        if (r3 < r6) goto L_0x0079;
    L_0x0074:
        r2 = r0.finishCurrentSegment();
        r3 = r5;
    L_0x0079:
        r6 = r3 + 1;
        r1 = (byte) r1;
        r2[r3] = r1;
        r1 = r2;
        r3 = r4;
        r2 = r6;
        goto L_0x0019;
    L_0x0082:
        r3 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        if (r8 < r3) goto L_0x008c;
    L_0x0087:
        r3 = 57343; // 0xdfff float:8.0355E-41 double:2.8331E-319;
        if (r8 <= r3) goto L_0x00b0;
    L_0x008c:
        r3 = r2 + 1;
        r6 = r8 >> 12;
        r6 = r6 | 224;
        r6 = (byte) r6;
        r1[r2] = r6;
        r2 = r1.length;
        if (r3 < r2) goto L_0x010c;
    L_0x0098:
        r1 = r0.finishCurrentSegment();
        r2 = r5;
    L_0x009d:
        r3 = r2 + 1;
        r6 = r8 >> 6;
        r6 = r6 & 63;
        r6 = r6 | 128;
        r6 = (byte) r6;
        r1[r2] = r6;
        r2 = r8 & 63;
        r2 = r2 | 128;
        r10 = r2;
        r2 = r1;
        r1 = r10;
        goto L_0x0071;
    L_0x00b0:
        r3 = 56319; // 0xdbff float:7.892E-41 double:2.78253E-319;
        if (r8 <= r3) goto L_0x00b8;
    L_0x00b5:
        r11._throwIllegalSurrogate(r8);
    L_0x00b8:
        if (r4 < r7) goto L_0x00bd;
    L_0x00ba:
        r11._throwIllegalSurrogate(r8);
    L_0x00bd:
        r6 = r4 + 1;
        r3 = r12.charAt(r4);
        r4 = r11._convertSurrogate(r8, r3);
        r3 = 1114111; // 0x10ffff float:1.561202E-39 double:5.50444E-318;
        if (r4 <= r3) goto L_0x00cf;
    L_0x00cc:
        r11._throwIllegalSurrogate(r4);
    L_0x00cf:
        r3 = r2 + 1;
        r8 = r4 >> 18;
        r8 = r8 | 240;
        r8 = (byte) r8;
        r1[r2] = r8;
        r2 = r1.length;
        if (r3 < r2) goto L_0x010a;
    L_0x00db:
        r1 = r0.finishCurrentSegment();
        r2 = r5;
    L_0x00e0:
        r3 = r2 + 1;
        r8 = r4 >> 12;
        r8 = r8 & 63;
        r8 = r8 | 128;
        r8 = (byte) r8;
        r1[r2] = r8;
        r2 = r1.length;
        if (r3 < r2) goto L_0x0108;
    L_0x00ee:
        r1 = r0.finishCurrentSegment();
        r2 = r5;
    L_0x00f3:
        r3 = r2 + 1;
        r8 = r4 >> 6;
        r8 = r8 & 63;
        r8 = r8 | 128;
        r8 = (byte) r8;
        r1[r2] = r8;
        r2 = r4 & 63;
        r2 = r2 | 128;
        r4 = r6;
        r10 = r1;
        r1 = r2;
        r2 = r10;
        goto L_0x0071;
    L_0x0108:
        r2 = r3;
        goto L_0x00f3;
    L_0x010a:
        r2 = r3;
        goto L_0x00e0;
    L_0x010c:
        r2 = r3;
        goto L_0x009d;
    L_0x010e:
        r4 = r2;
        goto L_0x004d;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.io.JsonStringEncoder.quoteAsUTF8(java.lang.String):byte[]");
    }
}
