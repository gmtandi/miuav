package com.fimi.soul.utils;

import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.utils.g */
public class C1967g {
    private static final String f10149a = "0123456789ABCDEF";

    public static String m12467a(String str, String str2) {
        try {
            return new String(C1967g.m12473b(C1967g.m12470a(str), str2));
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    private static String m12468a(byte[] bArr) {
        if (bArr == null) {
            return C2915a.f14760f;
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte a : bArr) {
            C1967g.m12469a(stringBuffer, a);
        }
        return stringBuffer.toString();
    }

    private static void m12469a(StringBuffer stringBuffer, byte b) {
        stringBuffer.append(f10149a.charAt((b >> 4) & 15)).append(f10149a.charAt(b & 15));
    }

    private static byte[] m12470a(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = Integer.valueOf(str.substring(i * 2, (i * 2) + 2), 16).byteValue();
        }
        return bArr;
    }

    private static byte[] m12471a(byte[] bArr, String str) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            Key generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes("utf-8")));
            Cipher instance = Cipher.getInstance("DES");
            instance.init(1, generateSecret, secureRandom);
            return instance.doFinal(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String m12472b(String str, String str2) {
        try {
            str = C1967g.m12468a(C1967g.m12471a(str.getBytes("utf-8"), str2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private static byte[] m12473b(byte[] bArr, String str) {
        SecureRandom secureRandom = new SecureRandom();
        Key generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes("utf-8")));
        Cipher instance = Cipher.getInstance("DES");
        instance.init(2, generateSecret, secureRandom);
        return instance.doFinal(bArr);
    }
}
