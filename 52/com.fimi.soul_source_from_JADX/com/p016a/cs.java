package com.p016a;

import com.baidu.android.common.security.RSAUtil;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.connect.common.Constants;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.cs */
public class cs {
    private static final char[] f789a;
    private static final byte[] f790b;
    private static final IvParameterSpec f791c;

    static {
        f789a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        f790b = new byte[]{(byte) 0, (byte) 1, (byte) 1, (byte) 2, (byte) 3, (byte) 5, (byte) 8, (byte) 13, (byte) 8, (byte) 7, (byte) 6, (byte) 5, (byte) 4, (byte) 3, (byte) 2, (byte) 1};
        f791c = new IvParameterSpec(f790b);
    }

    public static String m1374a(String str) {
        String str2 = null;
        if (str != null) {
            try {
                if (str.length() != 0) {
                    str2 = cs.m1375a("MD5", cs.m1375a("SHA1", str) + str);
                }
            } catch (Throwable th) {
                ev.m1777a(th, "Encrypt", "generatorKey");
            }
        }
        return str2;
    }

    public static String m1375a(String str, String str2) {
        String str3 = null;
        if (str2 != null) {
            try {
                str3 = cs.m1380b(fz.m1910a(str2.getBytes(C1142e.f5201a), str));
            } catch (Throwable th) {
                ev.m1777a(th, "Encrypt", "encode");
            }
        }
        return str3;
    }

    public static String m1376a(byte[] bArr) {
        try {
            Object obj = new byte[16];
            Object obj2 = new byte[(bArr.length - 16)];
            System.arraycopy(bArr, 0, obj, 0, 16);
            System.arraycopy(bArr, 16, obj2, 0, bArr.length - 16);
            Key secretKeySpec = new SecretKeySpec(obj, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, new IvParameterSpec(gf.m1961a()));
            return new String(instance.doFinal(obj2), C1142e.f5201a);
        } catch (Throwable th) {
            ev.m1777a(th, "Encrypt", "decryptRsponse");
            return null;
        }
    }

    private static byte[] m1377a() {
        return gf.m1961a();
    }

    public static synchronized byte[] m1378a(byte[] bArr, String str) {
        byte[] doFinal;
        int i = 0;
        synchronized (cs.class) {
            Key generatePrivate = KeyFactory.getInstance(RSAUtil.ALGORITHM_RSA).generatePrivate(new PKCS8EncodedKeySpec(fy.m1905b(str)));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, generatePrivate);
            int length = bArr.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i2 = 0;
            while (length - i > 0) {
                doFinal = length - i > 245 ? instance.doFinal(bArr, i, 245) : instance.doFinal(bArr, i, length - i);
                byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                i = i2 + 1;
                int i3 = i;
                i *= 245;
                i2 = i3;
            }
            doFinal = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        }
        return doFinal;
    }

    public static byte[] m1379a(byte[] bArr, byte[] bArr2) {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, new SecretKeySpec(bArr, "AES"), f791c);
        return instance.doFinal(bArr2);
    }

    private static String m1380b(byte[] bArr) {
        int length = bArr.length;
        StringBuilder stringBuilder = new StringBuilder(length * 2);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(f789a[(bArr[i] >> 4) & 15]);
            stringBuilder.append(f789a[bArr[i] & 15]);
        }
        return stringBuilder.toString();
    }

    private static SecretKeySpec m1381b(String str) {
        byte[] bArr = null;
        if (str == null) {
            str = C2915a.f14760f;
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(str);
        while (stringBuffer.length() < 16) {
            stringBuffer.append(Constants.VIA_RESULT_SUCCESS);
        }
        if (stringBuffer.length() > 16) {
            stringBuffer.setLength(16);
        }
        try {
            bArr = stringBuffer.toString().getBytes(C1142e.f5201a);
        } catch (Throwable e) {
            ev.m1777a(e, "Encrypt", "createKey");
        }
        return new SecretKeySpec(bArr, "AES");
    }

    public static synchronized byte[] m1382b(byte[] bArr, String str) {
        byte[] doFinal;
        int i = 0;
        synchronized (cs.class) {
            Key generatePrivate = KeyFactory.getInstance(RSAUtil.ALGORITHM_RSA).generatePrivate(new PKCS8EncodedKeySpec(fy.m1905b(str)));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(2, generatePrivate);
            int length = bArr.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i2 = 0;
            while (length - i > 0) {
                doFinal = length - i > Opcodes.ACC_NATIVE ? instance.doFinal(bArr, i, Opcodes.ACC_NATIVE) : instance.doFinal(bArr, i, length - i);
                byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                i = i2 + 1;
                int i3 = i;
                i *= Opcodes.ACC_NATIVE;
                i2 = i3;
            }
            doFinal = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        }
        return doFinal;
    }

    public static byte[] m1383c(byte[] bArr, String str) {
        Cipher instance;
        try {
            Key b = cs.m1381b(str);
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(cs.m1377a());
            instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, b, ivParameterSpec);
        } catch (Throwable e) {
            ev.m1777a(e, "Encrypt", "aesEncrypt1");
        } catch (Throwable e2) {
            ev.m1777a(e2, "Encrypt", "aesEncrypt");
            return null;
        }
        return instance.doFinal(bArr);
    }

    public static byte[] m1384d(byte[] bArr, String str) {
        Cipher instance;
        try {
            Key b = cs.m1381b(str);
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(cs.m1377a());
            instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, b, ivParameterSpec);
        } catch (Throwable e) {
            ev.m1777a(e, "Encrypt", "aesDecrypt1");
        } catch (Throwable e2) {
            ev.m1777a(e2, "Encrypt", "aesDecrypt");
            return null;
        }
        return instance.doFinal(bArr);
    }
}
