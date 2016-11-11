package com.fimi.soul.utils;

import com.tencent.connect.common.Constants;
import java.security.MessageDigest;
import org.codehaus.jackson.smile.SmileConstants;

public class br {
    public static String m12452a(String str) {
        String str2 = null;
        if (str != null) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(str.getBytes());
                str2 = String.valueOf(m12454a(instance.digest()));
            } catch (Exception e) {
            }
        }
        return str2;
    }

    public static String m12453a(String str, String str2) {
        String str3 = null;
        if (!(str == null || str2 == null)) {
            try {
                if (str2.length() >= 1 && str2.length() <= SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
                    String str4 = str + str2;
                    MessageDigest instance = MessageDigest.getInstance("MD5");
                    instance.update(str4.getBytes());
                    str3 = String.valueOf(m12454a(instance.digest()));
                }
            } catch (Exception e) {
            }
        }
        return str3;
    }

    public static String m12454a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            if (b == null) {
                stringBuffer.append("00");
            } else if (b == -1) {
                stringBuffer.append("ff");
            } else {
                String toLowerCase = Integer.toHexString(b).toLowerCase();
                if (toLowerCase.length() == 8) {
                    toLowerCase = toLowerCase.substring(6, 8);
                } else if (toLowerCase.length() < 2) {
                    toLowerCase = Constants.VIA_RESULT_SUCCESS + toLowerCase;
                }
                stringBuffer.append(toLowerCase);
            }
        }
        return stringBuffer.toString();
    }
}
