package com.amap.api.services.core;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ab {
    public static String m4471a(String str) {
        return str == null ? null : ae.m4504c(m4475c(str));
    }

    public static String m4472a(byte[] bArr) {
        return ae.m4504c(m4474b(bArr));
    }

    public static String m4473b(String str) {
        return ae.m4505d(m4476d(str));
    }

    private static byte[] m4474b(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            bArr2 = instance.digest();
        } catch (Throwable e) {
            ay.m4590a(e, "MD5", "getMd5Bytes");
            e.printStackTrace();
        } catch (Throwable e2) {
            ay.m4590a(e2, "MD5", "getMd5Bytes1");
            e2.printStackTrace();
        }
        return bArr2;
    }

    public static byte[] m4475c(String str) {
        try {
            return m4477e(str);
        } catch (Throwable e) {
            ay.m4590a(e, "MD5", "getMd5Bytes");
            e.printStackTrace();
            return new byte[0];
        } catch (Throwable e2) {
            ay.m4590a(e2, "MD5", "getMd5Bytes");
            e2.printStackTrace();
            return new byte[0];
        } catch (Throwable e22) {
            ay.m4590a(e22, "MD5", "getMd5Bytes");
            e22.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] m4476d(String str) {
        try {
            return m4477e(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return new byte[0];
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] m4477e(String str) {
        if (str == null) {
            return null;
        }
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(str.getBytes("utf-8"));
        return instance.digest();
    }
}
