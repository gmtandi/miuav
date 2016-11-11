package com.tencent.map.p131b;

import android.location.Location;
import android.net.wifi.ScanResult;
import android.telephony.NeighboringCellInfo;
import com.fimi.kernel.C1154b;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.service.CameraSocketService;
import com.tencent.map.p131b.C2239d.C2237b;
import com.tencent.map.p131b.C2243e.C2241b;
import com.tencent.map.p131b.C2254g.C2253b;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.regex.Pattern;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.json.JSONObject;

/* renamed from: com.tencent.map.b.i */
public final class C2256i {
    public static String f11705a;
    public static int[] f11706b;

    static {
        f11705a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
        f11706b = new int[]{0, 49345, 49537, 320, 49921, 960, 640, 49729, 50689, 1728, 1920, 51009, C1154b.f5235f, 50625, 50305, 1088, 52225, 3264, 3456, 52545, 3840, 53185, 52865, 3648, 2560, 51905, 52097, 2880, 51457, 2496, 2176, 51265, 55297, 6336, 6528, 55617, 6912, 56257, 55937, 6720, 7680, 57025, 57217, 8000, 56577, 7616, 7296, 56385, 5120, 54465, 54657, 5440, 55041, 6080, 5760, 54849, 53761, 4800, 4992, 54081, 4352, 53697, 53377, 4160, 61441, 12480, 12672, 61761, 13056, 62401, 62081, 12864, 13824, 63169, 63361, 14144, 62721, 13760, 13440, 62529, 15360, 64705, 64897, 15680, 65281, 16320, 16000, 65089, 64001, 15040, 15232, 64321, 14592, 63937, 63617, 14400, C1142e.f5202b, 59585, 59777, 10560, 60161, 11200, 10880, 59969, 60929, 11968, 12160, 61249, 11520, 60865, 60545, 11328, 58369, 9408, 9600, 58689, 9984, 59329, 59009, 9792, 8704, 58049, 58241, 9024, 57601, 8640, 8320, 57409, 40961, 24768, 24960, 41281, 25344, 41921, 41601, 25152, CameraSocketService.f9881a, 42689, 42881, 26432, 42241, 26048, 25728, 42049, 27648, 44225, 44417, 27968, 44801, 28608, 28288, 44609, 43521, 27328, 27520, 43841, 26880, 43457, 43137, 26688, 30720, 47297, 47489, 31040, 47873, 31680, 31360, 47681, 48641, 32448, 32640, 48961, 32000, 48577, 48257, 31808, 46081, 29888, 30080, 46401, 30464, 47041, 46721, 30272, 29184, 45761, 45953, 29504, 45313, 29120, 28800, 45121, 20480, 37057, 37249, 20800, 37633, 21440, 21120, 37441, 38401, 22208, 22400, 38721, 21760, 38337, 38017, 21568, 39937, 23744, 23936, 40257, 24320, 40897, 40577, 24128, 23040, 39617, 39809, 23360, 39169, 22976, 22656, 38977, 34817, 18624, 18816, 35137, 19200, 35777, 35457, 19008, 19968, 36545, 36737, 20288, 36097, 19904, 19584, 35905, 17408, 33985, 34177, 17728, 34561, 18368, 18048, 34369, 33281, 17088, 17280, 33601, 16640, 33217, 32897, 16448};
        int[] iArr = new int[]{93629, 99879, 79843, 75029, 59699, 55667, 46867, 38039};
    }

    public static double m13482a(double d, int i) {
        double d2 = 0.0d;
        try {
            if (!Double.isNaN(d)) {
                d2 = BigDecimal.valueOf(d).setScale(i, RoundingMode.HALF_DOWN).doubleValue();
            }
        } catch (Exception e) {
        }
        return d2;
    }

    public static int m13483a(char c) {
        int i = Opcodes.ACC_NATIVE;
        if (c >= 'A' && c <= 'Z') {
            i = c - 65;
        }
        if (c >= 'a' && c <= 'z') {
            i = (c - 97) + 64;
        }
        return (c < '0' || c > '9') ? i : (c + SmileConstants.TOKEN_PREFIX_TINY_UNICODE) - 48;
    }

    private static String m13484a(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        stringBuilder.append("\"mcc\":");
        stringBuilder.append(i);
        stringBuilder.append(",\"mnc\":");
        stringBuilder.append(i2);
        stringBuilder.append(",\"lac\":");
        stringBuilder.append(i3);
        stringBuilder.append(",\"cellid\":");
        stringBuilder.append(i4);
        stringBuilder.append(",\"rss\":");
        stringBuilder.append(i5);
        if (!(i6 == Integer.MAX_VALUE || i7 == Integer.MAX_VALUE)) {
            stringBuilder.append(",\"stationLat\":");
            stringBuilder.append(String.format("%.6f", new Object[]{Float.valueOf(((float) i6) / 14400.0f)}));
            stringBuilder.append(",\"stationLng\":");
            stringBuilder.append(String.format("%.6f", new Object[]{Float.valueOf(((float) i7) / 14400.0f)}));
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static String m13485a(C2237b c2237b, List<NeighboringCellInfo> list) {
        int i = 0;
        if (c2237b == null) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        int i2 = c2237b.f11601b;
        int i3 = c2237b.f11602c;
        if (C2256i.m13489a(c2237b.f11600a, i2, i3, c2237b.f11603d, c2237b.f11604e)) {
            stringBuilder.append(C2256i.m13484a(i2, i3, c2237b.f11603d, c2237b.f11604e, c2237b.f11605f, c2237b.f11606g, c2237b.f11607h));
            i = 1;
        }
        if (list != null) {
            try {
                Method method = Class.forName("android.telephony.NeighboringCellInfo").getMethod("getLac", new Class[0]);
                for (NeighboringCellInfo neighboringCellInfo : list) {
                    int parseInt = Integer.parseInt(method.invoke(neighboringCellInfo, new Object[0]).toString());
                    if (C2256i.m13489a(c2237b.f11600a, i2, i3, parseInt, neighboringCellInfo.getCid())) {
                        if (i > 0) {
                            stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                        }
                        stringBuilder.append(C2256i.m13484a(i2, i3, parseInt, neighboringCellInfo.getCid(), (neighboringCellInfo.getRssi() << 1) - 113, Integer.MAX_VALUE, Integer.MAX_VALUE));
                        parseInt = i + 1;
                    } else {
                        parseInt = i;
                    }
                    i = parseInt;
                }
            } catch (Exception e) {
            }
            list.clear();
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static String m13486a(C2241b c2241b) {
        if (c2241b.m13393b() == null) {
            return "{}";
        }
        Location b = c2241b.m13393b();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        stringBuilder.append("\"latitude\":");
        stringBuilder.append(b.getLatitude());
        stringBuilder.append(",\"longitude\":");
        stringBuilder.append(b.getLongitude());
        stringBuilder.append(",\"additional\":");
        stringBuilder.append("\"" + b.getAltitude() + MiPushClient.ACCEPT_TIME_SEPARATOR + b.getAccuracy() + MiPushClient.ACCEPT_TIME_SEPARATOR + b.getBearing() + MiPushClient.ACCEPT_TIME_SEPARATOR + b.getSpeed() + MiPushClient.ACCEPT_TIME_SEPARATOR + b.getTime() + "\"");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static String m13487a(C2253b c2253b) {
        if (c2253b == null || c2253b.m13469a() == null) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        if (c2253b.m13469a() == null || c2253b.m13469a().size() <= 0) {
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
        List<ScanResult> a = c2253b.m13469a();
        int i = 0;
        for (ScanResult scanResult : a) {
            if ((scanResult.level < (a.size() < 6 ? -95 : -90) ? null : 1) != null) {
                if (i > 0) {
                    stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                }
                stringBuilder.append("{\"mac\":\"").append(scanResult.BSSID).append("\",");
                stringBuilder.append("\"rssi\":").append(scanResult.level).append("}");
                i++;
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static String m13488a(String str, String str2, String str3, String str4, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        stringBuilder.append("\"imei\":\"");
        stringBuilder.append(str);
        stringBuilder.append("\",\"imsi\":\"");
        stringBuilder.append(str2);
        stringBuilder.append("\",\"phonenum\":\"");
        stringBuilder.append(str3);
        stringBuilder.append("\",\"roaming\":\"");
        stringBuilder.append(z);
        stringBuilder.append("\",\"qq\":\"");
        stringBuilder.append(str4);
        stringBuilder.append("\"");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static boolean m13489a(int i, int i2, int i3, int i4, int i5) {
        return i == 2 ? i2 >= 0 && i3 >= 0 && i4 >= 0 && i4 <= Util.MASK_16BIT && i5 >= 0 && i5 <= Util.MASK_16BIT && !(i3 == 0 && i4 == 0 && i5 == 0) : (i2 < 0 || i3 < 0 || i4 <= 0 || i4 >= Util.MASK_16BIT) ? false : (i5 == 268435455 || i5 == Integer.MAX_VALUE || i5 == 50594049 || i5 == Util.MASK_16BIT || i5 == 8 || i5 == 10 || i5 == 33 || i5 <= 0) ? false : true;
    }

    public static boolean m13490a(String str) {
        return str != null && str.length() <= 32 && str.length() >= 6 && Pattern.compile("[a-zA-Z0-9_]{6,32}").matcher(str).matches();
    }

    public static boolean m13491b(String str) {
        return str != null && str.length() == 29 && Pattern.compile("([A-Z2-7]{5}){1}(-[A-Z2-7]{5}){4}").matcher(str).matches();
    }

    public static boolean m13492c(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return (str.contains("latitude")) || (jSONObject.getJSONArray("cells").length() > 0) || (jSONObject.getJSONArray("wifis").length() > 0);
        } catch (Exception e) {
            return false;
        }
    }
}
