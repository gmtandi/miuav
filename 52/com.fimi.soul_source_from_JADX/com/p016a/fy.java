package com.p016a;

import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.a.fy */
public class fy {
    private static final char[] f1247a;
    private static final byte[] f1248b;

    static {
        int i;
        f1247a = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', SignatureVisitor.EXTENDS, '/'};
        f1248b = new byte[SmileConstants.TOKEN_PREFIX_TINY_UNICODE];
        for (i = 0; i < SmileConstants.TOKEN_PREFIX_TINY_UNICODE; i++) {
            f1248b[i] = (byte) -1;
        }
        for (i = 65; i <= 90; i++) {
            f1248b[i] = (byte) (i - 65);
        }
        for (i = 97; i <= Opcodes.ISHR; i++) {
            f1248b[i] = (byte) ((i - 97) + 26);
        }
        for (i = 48; i <= 57; i++) {
            f1248b[i] = (byte) ((i - 48) + 52);
        }
        f1248b[43] = (byte) 62;
        f1248b[47] = (byte) 63;
    }

    public static String m1900a(String str) {
        try {
            return new String(fy.m1905b(str), C1142e.f5201a);
        } catch (UnsupportedEncodingException e) {
            return new String(fy.m1905b(str));
        }
    }

    public static String m1901a(byte[] bArr) {
        try {
            return fy.m1907c(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    static byte[] m1902a(byte[] bArr, Key key) {
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(1, key);
        return instance.doFinal(bArr);
    }

    static byte[] m1903a(byte[] bArr, byte[] bArr2) {
        try {
            return fy.m1906b(bArr, bArr2);
        } catch (Throwable e) {
            C0247g.m1917a(e, "Encrypt", "aesEncrypt");
            return null;
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "Encrypt", "aesEncrypt");
            return null;
        } catch (Throwable e22) {
            C0247g.m1917a(e22, "Encrypt", "aesEncrypt");
            return null;
        } catch (Throwable e222) {
            C0247g.m1917a(e222, "Encrypt", "aesEncrypt");
            return null;
        } catch (Throwable e2222) {
            C0247g.m1917a(e2222, "Encrypt", "aesEncrypt");
            return null;
        } catch (Throwable e22222) {
            C0247g.m1917a(e22222, "Encrypt", "aesEncrypt");
            return null;
        }
    }

    public static String m1904b(byte[] bArr) {
        try {
            return fy.m1907c(bArr);
        } catch (Throwable th) {
            C0247g.m1917a(th, "Encrypt", "encodeBase64");
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] m1905b(java.lang.String r9) {
        /*
        r8 = 61;
        r1 = 0;
        r7 = -1;
        if (r9 != 0) goto L_0x0009;
    L_0x0006:
        r0 = new byte[r1];
    L_0x0008:
        return r0;
    L_0x0009:
        r0 = "UTF-8";
        r0 = r9.getBytes(r0);	 Catch:{ UnsupportedEncodingException -> 0x002a }
    L_0x000f:
        r3 = r0.length;
        r4 = new java.io.ByteArrayOutputStream;
        r4.<init>(r3);
    L_0x0015:
        if (r1 >= r3) goto L_0x0025;
    L_0x0017:
        r5 = f1248b;
        r2 = r1 + 1;
        r1 = r0[r1];
        r5 = r5[r1];
        if (r2 >= r3) goto L_0x0023;
    L_0x0021:
        if (r5 == r7) goto L_0x008c;
    L_0x0023:
        if (r5 != r7) goto L_0x0031;
    L_0x0025:
        r0 = r4.toByteArray();
        goto L_0x0008;
    L_0x002a:
        r0 = move-exception;
        r0 = r9.getBytes();
        goto L_0x000f;
    L_0x0030:
        r2 = r1;
    L_0x0031:
        r6 = f1248b;
        r1 = r2 + 1;
        r2 = r0[r2];
        r6 = r6[r2];
        if (r1 >= r3) goto L_0x003d;
    L_0x003b:
        if (r6 == r7) goto L_0x0030;
    L_0x003d:
        if (r6 == r7) goto L_0x0025;
    L_0x003f:
        r2 = r5 << 2;
        r5 = r6 & 48;
        r5 = r5 >>> 4;
        r2 = r2 | r5;
        r4.write(r2);
    L_0x0049:
        r2 = r1 + 1;
        r1 = r0[r1];
        if (r1 != r8) goto L_0x0054;
    L_0x004f:
        r0 = r4.toByteArray();
        goto L_0x0008;
    L_0x0054:
        r5 = f1248b;
        r5 = r5[r1];
        if (r2 >= r3) goto L_0x005c;
    L_0x005a:
        if (r5 == r7) goto L_0x008a;
    L_0x005c:
        if (r5 == r7) goto L_0x0025;
    L_0x005e:
        r1 = r6 & 15;
        r1 = r1 << 4;
        r6 = r5 & 60;
        r6 = r6 >>> 2;
        r1 = r1 | r6;
        r4.write(r1);
    L_0x006a:
        r1 = r2 + 1;
        r2 = r0[r2];
        if (r2 != r8) goto L_0x0075;
    L_0x0070:
        r0 = r4.toByteArray();
        goto L_0x0008;
    L_0x0075:
        r6 = f1248b;
        r2 = r6[r2];
        if (r1 >= r3) goto L_0x007d;
    L_0x007b:
        if (r2 == r7) goto L_0x0088;
    L_0x007d:
        if (r2 == r7) goto L_0x0025;
    L_0x007f:
        r5 = r5 & 3;
        r5 = r5 << 6;
        r2 = r2 | r5;
        r4.write(r2);
        goto L_0x0015;
    L_0x0088:
        r2 = r1;
        goto L_0x006a;
    L_0x008a:
        r1 = r2;
        goto L_0x0049;
    L_0x008c:
        r1 = r2;
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.fy.b(java.lang.String):byte[]");
    }

    private static byte[] m1906b(byte[] bArr, byte[] bArr2) {
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(gf.m1961a());
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        try {
            instance.init(1, secretKeySpec, ivParameterSpec);
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return instance.doFinal(bArr2);
    }

    private static String m1907c(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            int i3 = bArr[i] & Util.MASK_8BIT;
            if (i2 == length) {
                stringBuffer.append(f1247a[i3 >>> 2]);
                stringBuffer.append(f1247a[(i3 & 3) << 4]);
                stringBuffer.append("==");
                break;
            }
            int i4 = i2 + 1;
            i2 = bArr[i2] & Util.MASK_8BIT;
            if (i4 == length) {
                stringBuffer.append(f1247a[i3 >>> 2]);
                stringBuffer.append(f1247a[((i3 & 3) << 4) | ((i2 & 240) >>> 4)]);
                stringBuffer.append(f1247a[(i2 & 15) << 2]);
                stringBuffer.append("=");
                break;
            }
            i = i4 + 1;
            i4 = bArr[i4] & Util.MASK_8BIT;
            stringBuffer.append(f1247a[i3 >>> 2]);
            stringBuffer.append(f1247a[((i3 & 3) << 4) | ((i2 & 240) >>> 4)]);
            stringBuffer.append(f1247a[((i2 & 15) << 2) | ((i4 & SmileConstants.TOKEN_PREFIX_SMALL_INT) >>> 6)]);
            stringBuffer.append(f1247a[i4 & 63]);
        }
        return stringBuffer.toString();
    }
}
