package com.facebook.common.util;

import android.util.Base64;
import com.tencent.mm.sdk.platformtools.Util;
import java.security.MessageDigest;
import org.codehaus.jackson.smile.SmileConstants;

public class SecureHashUtil {
    static final byte[] HEX_CHAR_TABLE;

    static {
        HEX_CHAR_TABLE = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, SmileConstants.TOKEN_KEY_LONG_STRING, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102};
    }

    private static String convertToHex(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length);
        for (byte b : bArr) {
            int i = b & Util.MASK_8BIT;
            stringBuilder.append((char) HEX_CHAR_TABLE[i >>> 4]);
            stringBuilder.append((char) HEX_CHAR_TABLE[i & 15]);
        }
        return stringBuilder.toString();
    }

    public static String makeMD5Hash(String str) {
        try {
            return makeMD5Hash(str.getBytes("utf-8"));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static String makeMD5Hash(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr, 0, bArr.length);
            return convertToHex(instance.digest());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }

    public static String makeSHA1Hash(String str) {
        try {
            return makeSHA1Hash(str.getBytes("utf-8"));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static String makeSHA1Hash(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(bArr, 0, bArr.length);
            return convertToHex(instance.digest());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }

    public static String makeSHA1HashBase64(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(bArr, 0, bArr.length);
            return Base64.encodeToString(instance.digest(), 11);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
