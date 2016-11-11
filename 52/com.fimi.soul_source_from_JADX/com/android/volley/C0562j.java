package com.android.volley;

import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.android.volley.j */
class C0562j {
    private static final char[] f3535a;

    static {
        f3535a = "0123456789ABCDEF".toCharArray();
    }

    C0562j() {
    }

    public static String m5084a(String str) {
        String str2 = null;
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            byte[] bytes = str.getBytes(C1142e.f5201a);
            instance.update(bytes, 0, bytes.length);
            str2 = C0562j.m5085a(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return str2;
    }

    private static String m5085a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & Util.MASK_8BIT;
            cArr[i * 2] = f3535a[i2 >>> 4];
            cArr[(i * 2) + 1] = f3535a[i2 & 15];
        }
        return new String(cArr);
    }
}
