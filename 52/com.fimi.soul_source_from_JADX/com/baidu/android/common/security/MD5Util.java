package com.baidu.android.common.security;

import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import java.security.MessageDigest;
import org.p122a.p123a.C2915a;

public final class MD5Util {
    private MD5Util() {
    }

    public static String toHexString(byte[] bArr, String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & Util.MASK_8BIT);
            if (z) {
                toHexString = toHexString.toUpperCase();
            }
            if (toHexString.length() == 1) {
                stringBuilder.append(Constants.VIA_RESULT_SUCCESS);
            }
            stringBuilder.append(toHexString).append(str);
        }
        return stringBuilder.toString();
    }

    public static String toMd5(byte[] bArr, boolean z) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bArr);
            return toHexString(instance.digest(), C2915a.f14760f, z);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
