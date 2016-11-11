package com.amap.api.services.core;

import com.tencent.mm.sdk.platformtools.Util;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.codehaus.jackson.smile.SmileConstants;

public class aa {
    private static final char[] f2965a;
    private static final byte[] f2966b;

    static {
        int i;
        f2965a = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', SignatureVisitor.EXTENDS, '/'};
        f2966b = new byte[SmileConstants.TOKEN_PREFIX_TINY_UNICODE];
        for (i = 0; i < SmileConstants.TOKEN_PREFIX_TINY_UNICODE; i++) {
            f2966b[i] = (byte) -1;
        }
        for (i = 65; i <= 90; i++) {
            f2966b[i] = (byte) (i - 65);
        }
        for (i = 97; i <= Opcodes.ISHR; i++) {
            f2966b[i] = (byte) ((i - 97) + 26);
        }
        for (i = 48; i <= 57; i++) {
            f2966b[i] = (byte) ((i - 48) + 52);
        }
        f2966b[43] = (byte) 62;
        f2966b[47] = (byte) 63;
    }

    public static String m4463a(String str) {
        return new String(m4468b(str));
    }

    public static String m4464a(byte[] bArr) {
        try {
            return m4470c(bArr);
        } catch (Throwable th) {
            ay.m4590a(th, "Encrypt", "encodeBase64");
            th.printStackTrace();
            return null;
        }
    }

    static byte[] m4465a(byte[] bArr, Key key) {
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(1, key);
        return instance.doFinal(bArr);
    }

    static byte[] m4466a(byte[] bArr, byte[] bArr2) {
        try {
            return m4469b(bArr, bArr2);
        } catch (Throwable e) {
            ay.m4590a(e, "Encrypt", "aesEncrypt");
            e.printStackTrace();
            return null;
        } catch (Throwable e2) {
            ay.m4590a(e2, "Encrypt", "aesEncrypt");
            e2.printStackTrace();
            return null;
        } catch (Throwable e22) {
            ay.m4590a(e22, "Encrypt", "aesEncrypt");
            e22.printStackTrace();
            return null;
        } catch (Throwable e222) {
            ay.m4590a(e222, "Encrypt", "aesEncrypt");
            e222.printStackTrace();
            return null;
        } catch (Throwable e2222) {
            ay.m4590a(e2222, "Encrypt", "aesEncrypt");
            e2222.printStackTrace();
            return null;
        } catch (Throwable e22222) {
            ay.m4590a(e22222, "Encrypt", "aesEncrypt");
            e22222.printStackTrace();
            return null;
        }
    }

    public static String m4467b(byte[] bArr) {
        try {
            return m4470c(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] m4468b(java.lang.String r9) {
        /*
        r8 = 61;
        r0 = 0;
        r7 = -1;
        if (r9 != 0) goto L_0x0009;
    L_0x0006:
        r0 = new byte[r0];
    L_0x0008:
        return r0;
    L_0x0009:
        r2 = r9.getBytes();
        r3 = r2.length;
        r4 = new java.io.ByteArrayOutputStream;
        r4.<init>(r3);
    L_0x0013:
        if (r0 >= r3) goto L_0x0023;
    L_0x0015:
        r5 = f2966b;
        r1 = r0 + 1;
        r0 = r2[r0];
        r5 = r5[r0];
        if (r1 >= r3) goto L_0x0021;
    L_0x001f:
        if (r5 == r7) goto L_0x0084;
    L_0x0021:
        if (r5 != r7) goto L_0x0029;
    L_0x0023:
        r0 = r4.toByteArray();
        goto L_0x0008;
    L_0x0028:
        r1 = r0;
    L_0x0029:
        r6 = f2966b;
        r0 = r1 + 1;
        r1 = r2[r1];
        r6 = r6[r1];
        if (r0 >= r3) goto L_0x0035;
    L_0x0033:
        if (r6 == r7) goto L_0x0028;
    L_0x0035:
        if (r6 == r7) goto L_0x0023;
    L_0x0037:
        r1 = r5 << 2;
        r5 = r6 & 48;
        r5 = r5 >>> 4;
        r1 = r1 | r5;
        r4.write(r1);
    L_0x0041:
        r1 = r0 + 1;
        r0 = r2[r0];
        if (r0 != r8) goto L_0x004c;
    L_0x0047:
        r0 = r4.toByteArray();
        goto L_0x0008;
    L_0x004c:
        r5 = f2966b;
        r5 = r5[r0];
        if (r1 >= r3) goto L_0x0054;
    L_0x0052:
        if (r5 == r7) goto L_0x0082;
    L_0x0054:
        if (r5 == r7) goto L_0x0023;
    L_0x0056:
        r0 = r6 & 15;
        r0 = r0 << 4;
        r6 = r5 & 60;
        r6 = r6 >>> 2;
        r0 = r0 | r6;
        r4.write(r0);
    L_0x0062:
        r0 = r1 + 1;
        r1 = r2[r1];
        if (r1 != r8) goto L_0x006d;
    L_0x0068:
        r0 = r4.toByteArray();
        goto L_0x0008;
    L_0x006d:
        r6 = f2966b;
        r1 = r6[r1];
        if (r0 >= r3) goto L_0x0075;
    L_0x0073:
        if (r1 == r7) goto L_0x0080;
    L_0x0075:
        if (r1 == r7) goto L_0x0023;
    L_0x0077:
        r5 = r5 & 3;
        r5 = r5 << 6;
        r1 = r1 | r5;
        r4.write(r1);
        goto L_0x0013;
    L_0x0080:
        r1 = r0;
        goto L_0x0062;
    L_0x0082:
        r0 = r1;
        goto L_0x0041;
    L_0x0084:
        r0 = r1;
        goto L_0x0015;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.core.aa.b(java.lang.String):byte[]");
    }

    private static byte[] m4469b(byte[] bArr, byte[] bArr2) {
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
        instance.init(1, secretKeySpec);
        return instance.doFinal(bArr2);
    }

    private static String m4470c(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            int i3 = bArr[i] & Util.MASK_8BIT;
            if (i2 == length) {
                stringBuffer.append(f2965a[i3 >>> 2]);
                stringBuffer.append(f2965a[(i3 & 3) << 4]);
                stringBuffer.append("==");
                break;
            }
            int i4 = i2 + 1;
            i2 = bArr[i2] & Util.MASK_8BIT;
            if (i4 == length) {
                stringBuffer.append(f2965a[i3 >>> 2]);
                stringBuffer.append(f2965a[((i3 & 3) << 4) | ((i2 & 240) >>> 4)]);
                stringBuffer.append(f2965a[(i2 & 15) << 2]);
                stringBuffer.append("=");
                break;
            }
            i = i4 + 1;
            i4 = bArr[i4] & Util.MASK_8BIT;
            stringBuffer.append(f2965a[i3 >>> 2]);
            stringBuffer.append(f2965a[((i3 & 3) << 4) | ((i2 & 240) >>> 4)]);
            stringBuffer.append(f2965a[((i2 & 15) << 2) | ((i4 & SmileConstants.TOKEN_PREFIX_SMALL_INT) >>> 6)]);
            stringBuffer.append(f2965a[i4 & 63]);
        }
        return stringBuffer.toString();
    }
}
