package com.fimi.kernel.p084e;

import com.fimi.kernel.C1189f;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.tencent.connect.common.Constants;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.regex.Pattern;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.kernel.e.w */
public class C1184w {
    public static int m8271a(String str, int i) {
        String str2 = "[\u0391-\uffe5]";
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            i2 = str.substring(i3, i3 + 1).matches(str2) ? i2 + 2 : i2 + 1;
            if (i2 >= i) {
                return i3;
            }
        }
        return 0;
    }

    public static int m8272a(String str, String str2) {
        int i = 0;
        if (!(str == null || str.length() == 0)) {
            try {
                i = str.getBytes(str2).length;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static String m8273a(long j) {
        String str = "B";
        if (j >= FimiMediaMeta.AV_CH_SIDE_RIGHT) {
            str = "K";
            j >>= 10;
            if (j >= FimiMediaMeta.AV_CH_SIDE_RIGHT) {
                str = "M";
                j >>= 10;
                if (j >= FimiMediaMeta.AV_CH_SIDE_RIGHT) {
                    str = "G";
                    j >>= 10;
                }
            }
        }
        return j + str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m8274a(java.io.InputStream r4) {
        /*
        r0 = new java.io.BufferedReader;
        r1 = new java.io.InputStreamReader;
        r1.<init>(r4);
        r0.<init>(r1);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
    L_0x000f:
        r2 = r0.readLine();	 Catch:{ IOException -> 0x002c }
        if (r2 == 0) goto L_0x0038;
    L_0x0015:
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x002c }
        r3.<init>();	 Catch:{ IOException -> 0x002c }
        r2 = r3.append(r2);	 Catch:{ IOException -> 0x002c }
        r3 = "\n";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x002c }
        r2 = r2.toString();	 Catch:{ IOException -> 0x002c }
        r1.append(r2);	 Catch:{ IOException -> 0x002c }
        goto L_0x000f;
    L_0x002c:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x006e }
        r4.close();	 Catch:{ IOException -> 0x0069 }
    L_0x0033:
        r0 = r1.toString();
        return r0;
    L_0x0038:
        r0 = "\n";
        r0 = r1.indexOf(r0);	 Catch:{ IOException -> 0x002c }
        r2 = -1;
        if (r0 == r2) goto L_0x0060;
    L_0x0041:
        r0 = "\n";
        r0 = r1.lastIndexOf(r0);	 Catch:{ IOException -> 0x002c }
        r2 = r1.length();	 Catch:{ IOException -> 0x002c }
        r2 = r2 + -1;
        if (r0 != r2) goto L_0x0060;
    L_0x004f:
        r0 = "\n";
        r0 = r1.lastIndexOf(r0);	 Catch:{ IOException -> 0x002c }
        r2 = "\n";
        r2 = r1.lastIndexOf(r2);	 Catch:{ IOException -> 0x002c }
        r2 = r2 + 1;
        r1.delete(r0, r2);	 Catch:{ IOException -> 0x002c }
    L_0x0060:
        r4.close();	 Catch:{ IOException -> 0x0064 }
        goto L_0x0033;
    L_0x0064:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0033;
    L_0x0069:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0033;
    L_0x006e:
        r0 = move-exception;
        r4.close();	 Catch:{ IOException -> 0x0073 }
    L_0x0072:
        throw r0;
    L_0x0073:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0072;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fimi.kernel.e.w.a(java.io.InputStream):java.lang.String");
    }

    public static String m8275a(String str) {
        if (str == null || "null".equals(str.trim())) {
            str = C2915a.f14760f;
        }
        return str.trim();
    }

    public static String m8276a(String str, int i, String str2) {
        int i2 = 0;
        if (C1184w.m8272a(str, "GBK") <= i) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        for (char c : str.toCharArray()) {
            stringBuffer.append(c);
            i2 = c > '\u0100' ? i2 + 2 : i2 + 1;
            if (i2 >= i) {
                if (str2 != null) {
                    stringBuffer.append(str2);
                }
                return stringBuffer.toString();
            }
        }
        return stringBuffer.toString();
    }

    public static String m8277a(String str, String str2, int i) {
        if (C1184w.m8281b(str)) {
            return C2915a.f14760f;
        }
        int indexOf = str.indexOf(str2);
        return (indexOf == -1 || str.length() <= indexOf + i) ? C2915a.f14760f : str.substring(indexOf + i);
    }

    public static void m8278a(String[] strArr) {
        System.out.println(C1184w.m8290k("2012-3-2 12:2:20"));
    }

    public static String m8279b(String str, int i) {
        return C1184w.m8276a(str, i, C2915a.f14760f);
    }

    public static String m8280b(String str, String str2) {
        return C1184w.m8281b(str) ? C2915a.f14760f : str.substring(0, str.indexOf(str2));
    }

    public static boolean m8281b(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static int m8282c(String str) {
        int i = 0;
        String str2 = "[\u0391-\uffe5]";
        if (C1184w.m8281b(str)) {
            return 0;
        }
        int i2 = 0;
        while (i < str.length()) {
            if (str.substring(i, i + 1).matches(str2)) {
                i2 += 2;
            }
            i++;
        }
        return i2;
    }

    public static int m8283d(String str) {
        int i = 0;
        String str2 = "[\u0391-\uffe5]";
        if (C1184w.m8281b(str)) {
            return 0;
        }
        int i2 = 0;
        while (i < str.length()) {
            i2 = str.substring(i, i + 1).matches(str2) ? i2 + 2 : i2 + 1;
            i++;
        }
        return i2;
    }

    public static Boolean m8284e(String str) {
        Boolean valueOf = Boolean.valueOf(false);
        try {
            valueOf = Boolean.valueOf(Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$").matcher(str).matches());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valueOf;
    }

    public static Boolean m8285f(String str) {
        return str.matches("^[A-Za-z0-9]+$") ? Boolean.valueOf(true) : Boolean.valueOf(false);
    }

    public static Boolean m8286g(String str) {
        return str.matches("^[0-9]+$") ? Boolean.valueOf(true) : Boolean.valueOf(false);
    }

    public static Boolean m8287h(String str) {
        return str.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$") ? Boolean.valueOf(true) : Boolean.valueOf(false);
    }

    public static Boolean m8288i(String str) {
        Boolean valueOf = Boolean.valueOf(true);
        String str2 = "[\u0391-\uffe5]";
        if (C1184w.m8281b(str)) {
            return valueOf;
        }
        Boolean bool = valueOf;
        for (int i = 0; i < str.length(); i++) {
            if (!str.substring(i, i + 1).matches(str2)) {
                bool = Boolean.valueOf(false);
            }
        }
        return bool;
    }

    public static Boolean m8289j(String str) {
        int i = 0;
        Boolean valueOf = Boolean.valueOf(false);
        String str2 = "[\u0391-\uffe5]";
        if (!C1184w.m8281b(str)) {
            while (i < str.length()) {
                if (str.substring(i, i + 1).matches(str2)) {
                    valueOf = Boolean.valueOf(true);
                }
                i++;
            }
        }
        return valueOf;
    }

    public static String m8290k(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (C1184w.m8281b(str)) {
                return null;
            }
            String[] split = str.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            if (split.length > 0) {
                for (String str2 : split) {
                    String[] split2;
                    int i;
                    if (str2.indexOf("-") != -1) {
                        split2 = str2.split("-");
                        for (i = 0; i < split2.length; i++) {
                            stringBuilder.append(C1184w.m8291l(split2[i]));
                            if (i < split2.length - 1) {
                                stringBuilder.append("-");
                            }
                        }
                    } else if (str2.indexOf(":") != -1) {
                        stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                        split2 = str2.split(":");
                        for (i = 0; i < split2.length; i++) {
                            stringBuilder.append(C1184w.m8291l(split2[i]));
                            if (i < split2.length - 1) {
                                stringBuilder.append(":");
                            }
                        }
                    }
                }
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String m8291l(String str) {
        try {
            if (str.length() <= 1) {
                str = Constants.VIA_RESULT_SUCCESS + str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static long m8292m(String str) {
        String[] split = str.replace(".", MiPushClient.ACCEPT_TIME_SEPARATOR).split(MiPushClient.ACCEPT_TIME_SEPARATOR);
        return Long.valueOf(split[3]).longValue() | (((Long.valueOf(split[0]).longValue() << 24) | (Long.valueOf(split[1]).longValue() << 16)) | (Long.valueOf(split[2]).longValue() << 8));
    }

    public static int m8293n(String str) {
        int i = -1;
        if (str != null) {
            try {
                if (str != C2915a.f14760f) {
                    int lastIndexOf = str.toLowerCase().lastIndexOf("sp");
                    i = Integer.valueOf(str.substring(lastIndexOf - 2, lastIndexOf) + str.substring(lastIndexOf + 2, lastIndexOf + 4)).intValue();
                }
            } catch (Exception e) {
                ak.m8088b(C1189f.m8327a(), "\u89e3\u6790\u903b\u8f91\u7248\u672c\u51fa\u9519!");
            }
        }
        return i;
    }
}
