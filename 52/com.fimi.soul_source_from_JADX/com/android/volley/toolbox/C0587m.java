package com.android.volley.toolbox;

import com.android.volley.C0555c;
import com.android.volley.C0566n;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.p122a.p123a.C3004e;

/* renamed from: com.android.volley.toolbox.m */
public class C0587m {
    public static long m5225a(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException e) {
            return 0;
        }
    }

    public static C0555c m5226a(C0566n c0566n) {
        Object obj;
        long j;
        Object obj2;
        long currentTimeMillis = System.currentTimeMillis();
        Map map = c0566n.f3543c;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        String str = (String) map.get(C3004e.f15032r);
        if (str != null) {
            j2 = C0587m.m5225a(str);
        }
        str = (String) map.get(C3004e.f15023i);
        if (str != null) {
            String[] split = str.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            obj = null;
            j = 0;
            j4 = 0;
            for (String trim : split) {
                String trim2 = trim2.trim();
                if (trim2.equals("no-cache") || trim2.equals("no-store")) {
                    return null;
                }
                if (trim2.startsWith("max-age=")) {
                    try {
                        j4 = Long.parseLong(trim2.substring(8));
                    } catch (Exception e) {
                    }
                } else if (trim2.startsWith("stale-while-revalidate=")) {
                    try {
                        j = Long.parseLong(trim2.substring(23));
                    } catch (Exception e2) {
                    }
                } else if (trim2.equals("must-revalidate") || trim2.equals("proxy-revalidate")) {
                    obj = 1;
                }
            }
            j3 = j4;
            j4 = j;
            obj2 = 1;
        } else {
            obj = null;
            obj2 = null;
        }
        str = (String) map.get(C3004e.f15038x);
        long a = str != null ? C0587m.m5225a(str) : 0;
        str = (String) map.get(C3004e.f14995G);
        long a2 = str != null ? C0587m.m5225a(str) : 0;
        str = (String) map.get(C3004e.f15036v);
        if (obj2 != null) {
            j3 = currentTimeMillis + (1000 * j3);
            j = obj != null ? j3 : (1000 * j4) + j3;
        } else if (j2 <= 0 || a < j2) {
            j = 0;
            j3 = 0;
        } else {
            j = (a - j2) + currentTimeMillis;
            j3 = j;
        }
        C0555c c0555c = new C0555c();
        c0555c.f3506a = c0566n.f3542b;
        c0555c.f3507b = str;
        c0555c.f3511f = j3;
        c0555c.f3510e = j;
        c0555c.f3508c = j2;
        c0555c.f3509d = a2;
        c0555c.f3512g = map;
        return c0555c;
    }

    public static String m5227a(Map<String, String> map) {
        return C0587m.m5228a(map, "ISO-8859-1");
    }

    public static String m5228a(Map<String, String> map, String str) {
        String str2 = (String) map.get(C3004e.f15031q);
        if (str2 == null) {
            return str;
        }
        String[] split = str2.split(";");
        for (int i = 1; i < split.length; i++) {
            String[] split2 = split[i].trim().split("=");
            if (split2.length == 2 && split2[0].equals("charset")) {
                return split2[1];
            }
        }
        return str;
    }
}
