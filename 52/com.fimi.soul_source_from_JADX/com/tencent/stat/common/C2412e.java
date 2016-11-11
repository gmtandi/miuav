package com.tencent.stat.common;

import com.tencent.mm.sdk.platformtools.Util;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.tencent.stat.common.e */
public class C2412e {
    static final byte[] f12330a;

    static {
        f12330a = "03a976511e2cbe3a7f26808fb7af3c05".getBytes();
    }

    public static byte[] m13988a(byte[] bArr) {
        return C2412e.m13989a(bArr, f12330a);
    }

    static byte[] m13989a(byte[] bArr, byte[] bArr2) {
        int i = 0;
        int[] iArr = new int[Opcodes.ACC_NATIVE];
        int[] iArr2 = new int[Opcodes.ACC_NATIVE];
        int length = bArr2.length;
        if (length < 1 || length > Opcodes.ACC_NATIVE) {
            throw new IllegalArgumentException("key must be between 1 and 256 bytes");
        }
        int i2;
        for (i2 = 0; i2 < Opcodes.ACC_NATIVE; i2++) {
            iArr[i2] = i2;
            iArr2[i2] = bArr2[i2 % length];
        }
        length = 0;
        for (i2 = 0; i2 < Opcodes.ACC_NATIVE; i2++) {
            length = ((length + iArr[i2]) + iArr2[i2]) & Util.MASK_8BIT;
            int i3 = iArr[i2];
            iArr[i2] = iArr[length];
            iArr[length] = i3;
        }
        byte[] bArr3 = new byte[bArr.length];
        i2 = 0;
        length = 0;
        while (i < bArr.length) {
            length = (length + 1) & Util.MASK_8BIT;
            i2 = (i2 + iArr[length]) & Util.MASK_8BIT;
            i3 = iArr[length];
            iArr[length] = iArr[i2];
            iArr[i2] = i3;
            bArr3[i] = (byte) (iArr[(iArr[length] + iArr[i2]) & Util.MASK_8BIT] ^ bArr[i]);
            i++;
        }
        return bArr3;
    }

    public static byte[] m13990b(byte[] bArr) {
        return C2412e.m13991b(bArr, f12330a);
    }

    static byte[] m13991b(byte[] bArr, byte[] bArr2) {
        return C2412e.m13989a(bArr, bArr2);
    }
}
