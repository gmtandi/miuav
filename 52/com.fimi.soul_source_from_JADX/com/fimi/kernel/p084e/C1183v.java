package com.fimi.kernel.p084e;

import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.fimi.kernel.e.v */
public class C1183v {
    protected static char[] f5351a;
    protected static MessageDigest f5352b;

    static {
        f5351a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        f5352b = null;
        try {
            f5352b = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
        }
    }

    public static String m8262a(File file) {
        f5352b.update(new FileInputStream(file).getChannel().map(MapMode.READ_ONLY, 0, file.length()));
        return C1183v.m8270b(f5352b.digest());
    }

    public static String m8263a(String str) {
        return C1183v.m8264a(str.getBytes());
    }

    public static String m8264a(byte[] bArr) {
        f5352b.update(bArr);
        return C1183v.m8270b(f5352b.digest());
    }

    private static String m8265a(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer(i2 * 2);
        int i3 = i + i2;
        while (i < i3) {
            C1183v.m8266a(bArr[i], stringBuffer);
            i++;
        }
        return stringBuffer.toString();
    }

    private static void m8266a(byte b, StringBuffer stringBuffer) {
        char c = f5351a[(b & 240) >> 4];
        char c2 = f5351a[b & 15];
        stringBuffer.append(c);
        stringBuffer.append(c2);
    }

    public static void m8267a(String[] strArr) {
        System.out.println(C1183v.m8269b("2011123456").toLowerCase());
    }

    public static boolean m8268a(String str, String str2) {
        return C1183v.m8263a(str).equals(str2);
    }

    public static final String m8269b(String str) {
        int i = 0;
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bytes);
            byte[] digest = instance.digest();
            char[] cArr2 = new char[32];
            int i2 = 0;
            while (i < 16) {
                byte b = digest[i];
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b & 15];
                i++;
            }
            return new String(cArr2).toUpperCase();
        } catch (Exception e) {
            return null;
        }
    }

    private static String m8270b(byte[] bArr) {
        return C1183v.m8265a(bArr, 0, bArr.length);
    }
}
