package com.xiaomi.network;

import com.tencent.mm.sdk.platformtools.Util;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.codehaus.jackson.smile.SmileConstants;

public class Base64Coder {
    private static final String f13013a;
    private static char[] f13014b;
    private static byte[] f13015c;

    static {
        int i;
        int i2 = 0;
        f13013a = System.getProperty("line.separator");
        f13014b = new char[64];
        char c = 'A';
        int i3 = 0;
        while (c <= 'Z') {
            i = i3 + 1;
            f13014b[i3] = c;
            c = (char) (c + 1);
            i3 = i;
        }
        c = 'a';
        while (c <= 'z') {
            i = i3 + 1;
            f13014b[i3] = c;
            c = (char) (c + 1);
            i3 = i;
        }
        c = '0';
        while (c <= '9') {
            i = i3 + 1;
            f13014b[i3] = c;
            c = (char) (c + 1);
            i3 = i;
        }
        i = i3 + 1;
        f13014b[i3] = SignatureVisitor.EXTENDS;
        i3 = i + 1;
        f13014b[i] = '/';
        f13015c = new byte[SmileConstants.TOKEN_PREFIX_TINY_UNICODE];
        for (int i4 = 0; i4 < f13015c.length; i4++) {
            f13015c[i4] = (byte) -1;
        }
        while (i2 < 64) {
            f13015c[f13014b[i2]] = (byte) i2;
            i2++;
        }
    }

    private Base64Coder() {
    }

    public static char[] m14828a(byte[] bArr) {
        return m14829a(bArr, 0, bArr.length);
    }

    public static char[] m14829a(byte[] bArr, int i, int i2) {
        int i3 = ((i2 * 4) + 2) / 3;
        char[] cArr = new char[(((i2 + 2) / 3) * 4)];
        int i4 = i + i2;
        int i5 = 0;
        while (i < i4) {
            int i6;
            int i7;
            int i8 = i + 1;
            int i9 = bArr[i] & Util.MASK_8BIT;
            if (i8 < i4) {
                i6 = bArr[i8] & Util.MASK_8BIT;
                i8++;
            } else {
                i6 = 0;
            }
            if (i8 < i4) {
                i7 = i8 + 1;
                i8 = bArr[i8] & Util.MASK_8BIT;
            } else {
                i7 = i8;
                i8 = 0;
            }
            int i10 = i9 >>> 2;
            i9 = ((i9 & 3) << 4) | (i6 >>> 4);
            i6 = ((i6 & 15) << 2) | (i8 >>> 6);
            int i11 = i8 & 63;
            i8 = i5 + 1;
            cArr[i5] = f13014b[i10];
            i5 = i8 + 1;
            cArr[i8] = f13014b[i9];
            cArr[i5] = i5 < i3 ? f13014b[i6] : SignatureVisitor.INSTANCEOF;
            i6 = i5 + 1;
            cArr[i6] = i6 < i3 ? f13014b[i11] : SignatureVisitor.INSTANCEOF;
            i5 = i6 + 1;
            i = i7;
        }
        return cArr;
    }
}
