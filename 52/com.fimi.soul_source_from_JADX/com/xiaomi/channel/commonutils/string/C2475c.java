package com.xiaomi.channel.commonutils.string;

import com.tencent.connect.common.Constants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.channel.commonutils.string.c */
public class C2475c {
    private static String m14162a(byte b) {
        int i = (b & Opcodes.LAND) + (b < null ? SmileConstants.TOKEN_PREFIX_TINY_UNICODE : 0);
        return (i < 16 ? Constants.VIA_RESULT_SUCCESS : C2915a.f14760f) + Integer.toHexString(i).toLowerCase();
    }

    public static String m14163a(String str) {
        int i = 0;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            StringBuffer stringBuffer = new StringBuffer();
            instance.update(str.getBytes(), 0, str.length());
            byte[] digest = instance.digest();
            while (i < digest.length) {
                stringBuffer.append(C2475c.m14162a(digest[i]));
                i++;
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String m14164b(String str) {
        return C2475c.m14163a(str).subSequence(8, 24).toString();
    }
}
