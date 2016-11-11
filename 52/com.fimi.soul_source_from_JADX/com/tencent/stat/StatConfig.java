package com.tencent.stat;

import android.content.Context;
import com.tencent.stat.common.C2418k;
import com.tencent.stat.common.C2423p;
import com.tencent.stat.common.StatConstants;
import com.tencent.stat.common.StatLogger;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import java.util.Iterator;
import org.apache.http.HttpHost;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

public class StatConfig {
    private static int f12198A;
    private static boolean f12199B;
    private static String f12200C;
    static C2406b f12201a;
    static C2406b f12202b;
    static String f12203c;
    static String f12204d;
    private static StatLogger f12205e;
    private static StatReportStrategy f12206f;
    private static boolean f12207g;
    private static int f12208h;
    private static int f12209i;
    public static boolean isAutoExceptionCaught;
    private static int f12210j;
    private static int f12211k;
    private static int f12212l;
    private static String f12213m;
    private static String f12214n;
    private static String f12215o;
    private static int f12216p;
    private static int f12217q;
    private static boolean f12218r;
    private static long f12219s;
    private static long f12220t;
    private static String f12221u;
    private static int f12222v;
    private static volatile int f12223w;
    private static int f12224x;
    private static int f12225y;
    private static boolean f12226z;

    static {
        f12205e = C2418k.m14018b();
        f12201a = new C2406b(2);
        f12202b = new C2406b(1);
        f12206f = StatReportStrategy.APP_LAUNCH;
        f12207g = true;
        f12208h = 30000;
        f12209i = SmileConstants.MAX_SHARED_STRING_VALUES;
        f12210j = 30;
        f12211k = 3;
        f12212l = 30;
        f12203c = "__HIBERNATE__";
        f12213m = null;
        f12204d = C2915a.f14760f;
        f12216p = 1440;
        f12217q = SmileConstants.MAX_SHARED_STRING_VALUES;
        f12218r = true;
        f12219s = 0;
        f12220t = MiStatInterface.MIN_UPLOAD_INTERVAL;
        isAutoExceptionCaught = true;
        f12221u = "http://pingma.qq.com:80/mstat/report";
        f12222v = 0;
        f12223w = 0;
        f12224x = 20;
        f12225y = 0;
        f12226z = false;
        f12198A = Opcodes.ACC_SYNTHETIC;
        f12199B = false;
        f12200C = null;
    }

    static int m13896a() {
        return f12210j;
    }

    static String m13897a(Context context) {
        return C2418k.m14026d(C2423p.m14062a(context, "_mta_ky_tag_", null));
    }

    static String m13898a(String str, String str2) {
        try {
            String string = f12202b.f12300b.getString(str);
            return string != null ? string : str2;
        } catch (Throwable th) {
            f12205e.m13981w(th);
            return str2;
        }
    }

    static synchronized void m13899a(int i) {
        synchronized (StatConfig.class) {
            f12223w = i;
        }
    }

    static void m13900a(Context context, String str) {
        if (str != null) {
            C2423p.m14065b(context, "_mta_ky_tag_", C2418k.m14023c(str));
        }
    }

    static void m13901a(C2406b c2406b) {
        if (c2406b.f12299a == f12202b.f12299a) {
            f12202b = c2406b;
            m13909b(f12202b.f12300b);
        } else if (c2406b.f12299a == f12201a.f12299a) {
            f12201a = c2406b;
        }
    }

    static void m13902a(C2406b c2406b, JSONObject jSONObject) {
        Object obj = null;
        Object obj2;
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (str.equalsIgnoreCase("v")) {
                    int i = jSONObject.getInt(str);
                    obj2 = c2406b.f12302d != i ? 1 : obj;
                    c2406b.f12302d = i;
                } else if (str.equalsIgnoreCase("c")) {
                    str = jSONObject.getString("c");
                    if (str.length() > 0) {
                        c2406b.f12300b = new JSONObject(str);
                    }
                    obj2 = obj;
                } else {
                    if (str.equalsIgnoreCase("m")) {
                        c2406b.f12301c = jSONObject.getString("m");
                    }
                    obj2 = obj;
                }
                obj = obj2;
            }
            if (obj == 1) {
                C2434n a = C2434n.m14082a(C2424d.m14066a());
                if (a != null) {
                    a.m14103a(c2406b);
                }
                if (c2406b.f12299a == f12202b.f12299a) {
                    m13909b(c2406b.f12300b);
                    m13911c(c2406b.f12300b);
                }
            }
        } catch (Exception e) {
            f12205e.m13977e(e);
        } catch (Object obj22) {
            f12205e.m13978e(obj22);
        }
    }

    static void m13903a(JSONObject jSONObject) {
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (str.equalsIgnoreCase(Integer.toString(f12202b.f12299a))) {
                    m13902a(f12202b, jSONObject.getJSONObject(str));
                } else if (str.equalsIgnoreCase(Integer.toString(f12201a.f12299a))) {
                    m13902a(f12201a, jSONObject.getJSONObject(str));
                } else if (str.equalsIgnoreCase("rs")) {
                    StatReportStrategy statReportStrategy = StatReportStrategy.getStatReportStrategy(jSONObject.getInt(str));
                    if (statReportStrategy != null) {
                        f12206f = statReportStrategy;
                        f12205e.m13976d("Change to ReportStrategy:" + statReportStrategy.name());
                    }
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            f12205e.m13977e(e);
        }
    }

    static void m13904a(boolean z) {
        StatNativeCrashReport.setNativeCrashDebugEnable(z);
    }

    static boolean m13905a(int i, int i2, int i3) {
        return i >= i2 && i <= i3;
    }

    private static boolean m13906a(String str) {
        if (str == null) {
            return false;
        }
        if (f12214n == null) {
            f12214n = str;
            return true;
        } else if (f12214n.contains(str)) {
            return false;
        } else {
            f12214n += "|" + str;
            return true;
        }
    }

    static HttpHost m13907b() {
        if (f12213m == null || f12213m.length() <= 0) {
            return null;
        }
        String str = f12213m;
        String[] split = str.split(":");
        int i = 80;
        if (split.length == 2) {
            str = split[0];
            i = Integer.parseInt(split[1]);
        }
        return new HttpHost(str, i);
    }

    static void m13908b(int i) {
        if (i >= 0) {
            f12225y = i;
        }
    }

    static void m13909b(JSONObject jSONObject) {
        try {
            StatReportStrategy statReportStrategy = StatReportStrategy.getStatReportStrategy(jSONObject.getInt("rs"));
            if (statReportStrategy != null) {
                setStatSendStrategy(statReportStrategy);
            }
        } catch (JSONException e) {
            f12205e.m13976d("rs not found.");
        }
    }

    static synchronized void m13910c() {
        synchronized (StatConfig.class) {
            f12223w++;
        }
    }

    static void m13911c(JSONObject jSONObject) {
        try {
            String string = jSONObject.getString(f12203c);
            f12205e.m13976d("hibernateVer:" + string + ", current version:" + StatConstants.VERSION);
            long b = C2418k.m14017b(string);
            if (C2418k.m14017b(StatConstants.VERSION) <= b) {
                C2423p.m14064b(C2424d.m14066a(), f12203c, b);
                setEnableStatService(false);
                f12205e.warn("MTA has disable for SDK version of " + string + " or lower.");
            }
        } catch (JSONException e) {
            f12205e.m13976d("__HIBERNATE__ not found.");
        }
    }

    static void m13912d() {
        f12225y++;
    }

    static int m13913e() {
        return f12225y;
    }

    public static synchronized String getAppKey(Context context) {
        String str;
        synchronized (StatConfig.class) {
            if (f12214n != null) {
                str = f12214n;
            } else {
                if (context != null) {
                    if (f12214n == null) {
                        f12214n = C2418k.m14036i(context);
                    }
                }
                if (f12214n == null || f12214n.trim().length() == 0) {
                    f12205e.error((Object) "AppKey can not be null or empty, please read Developer's Guide first!");
                }
                str = f12214n;
            }
        }
        return str;
    }

    public static int getCurSessionStatReportCount() {
        return f12223w;
    }

    public static String getCustomProperty(String str) {
        try {
            return f12201a.f12300b.getString(str);
        } catch (Object th) {
            f12205e.m13978e(th);
            return null;
        }
    }

    public static String getCustomProperty(String str, String str2) {
        try {
            String string = f12201a.f12300b.getString(str);
            return string != null ? string : str2;
        } catch (Object th) {
            f12205e.m13978e(th);
            return str2;
        }
    }

    public static String getCustomUserId(Context context) {
        if (context == null) {
            f12205e.error((Object) "Context for getCustomUid is null.");
            return null;
        }
        if (f12200C == null) {
            f12200C = C2423p.m14062a(context, "MTA_CUSTOM_UID", C2915a.f14760f);
        }
        return f12200C;
    }

    public static DeviceInfo getDeviceInfo(Context context) {
        return StatMid.getDeviceInfo(context);
    }

    public static synchronized String getInstallChannel(Context context) {
        String str;
        synchronized (StatConfig.class) {
            if (f12215o != null) {
                str = f12215o;
            } else {
                f12215o = C2418k.m14037j(context);
                if (f12215o == null || f12215o.trim().length() == 0) {
                    f12205e.m13981w("installChannel can not be null or empty, please read Developer's Guide first!");
                }
                str = f12215o;
            }
        }
        return str;
    }

    public static int getMaxBatchReportCount() {
        return f12212l;
    }

    public static int getMaxDaySessionNumbers() {
        return f12224x;
    }

    public static int getMaxParallelTimmingEvents() {
        return f12217q;
    }

    public static int getMaxReportEventLength() {
        return f12198A;
    }

    public static int getMaxSendRetryCount() {
        return f12211k;
    }

    public static int getMaxSessionStatReportCount() {
        return f12222v;
    }

    public static int getMaxStoreEventCount() {
        return f12209i;
    }

    public static String getMid(Context context) {
        return StatMid.getMid(context);
    }

    public static String getQQ() {
        return f12204d;
    }

    public static int getSendPeriodMinutes() {
        return f12216p;
    }

    public static int getSessionTimoutMillis() {
        return f12208h;
    }

    public static String getStatReportUrl() {
        return f12221u;
    }

    public static StatReportStrategy getStatSendStrategy() {
        return f12206f;
    }

    public static void initNativeCrashReport(Context context, String str) {
        if (!isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12205e.error((Object) "The Context of StatConfig.initNativeCrashReport() can not be null!");
        } else {
            StatNativeCrashReport.initNativeCrash(context, str);
        }
    }

    public static boolean isAutoExceptionCaught() {
        return isAutoExceptionCaught;
    }

    public static boolean isDebugEnable() {
        return C2418k.m14018b().isDebugEnable();
    }

    public static boolean isEnableConcurrentProcess() {
        return f12199B;
    }

    public static boolean isEnableSmartReporting() {
        return f12218r;
    }

    public static boolean isEnableStatService() {
        return f12207g;
    }

    public static void setAppKey(Context context, String str) {
        if (context == null) {
            f12205e.error((Object) "ctx in StatConfig.setAppKey() is null");
        } else if (str == null || str.length() > Opcodes.ACC_NATIVE) {
            f12205e.error((Object) "appkey in StatConfig.setAppKey() is null or exceed 256 bytes");
        } else {
            if (f12214n == null) {
                f12214n = m13897a(context);
            }
            if ((m13906a(str) | m13906a(C2418k.m14036i(context))) != 0) {
                m13900a(context, f12214n);
            }
        }
    }

    public static void setAppKey(String str) {
        if (str == null) {
            f12205e.error((Object) "appkey in StatConfig.setAppKey() is null");
        } else if (str.length() > Opcodes.ACC_NATIVE) {
            f12205e.error((Object) "The length of appkey cann't exceed 256 bytes.");
        } else {
            f12214n = str;
        }
    }

    public static void setAutoExceptionCaught(boolean z) {
        isAutoExceptionCaught = z;
    }

    public static void setCustomUserId(Context context, String str) {
        if (context == null) {
            f12205e.error((Object) "Context for setCustomUid is null.");
            return;
        }
        C2423p.m14065b(context, "MTA_CUSTOM_UID", str);
        f12200C = str;
    }

    public static void setDebugEnable(boolean z) {
        C2418k.m14018b().setDebugEnable(z);
        m13904a(z);
    }

    public static void setEnableConcurrentProcess(boolean z) {
        f12199B = z;
    }

    public static void setEnableSmartReporting(boolean z) {
        f12218r = z;
    }

    public static void setEnableStatService(boolean z) {
        f12207g = z;
        if (!z) {
            f12205e.warn("!!!!!!MTA StatService has been disabled!!!!!!");
        }
    }

    public static void setInstallChannel(String str) {
        if (str.length() > SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            f12205e.error((Object) "the length of installChannel can not exceed the range of 128 bytes.");
        } else {
            f12215o = str;
        }
    }

    public static void setMaxBatchReportCount(int i) {
        if (m13905a(i, 2, XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER)) {
            f12212l = i;
        } else {
            f12205e.error((Object) "setMaxBatchReportCount can not exceed the range of [2,1000].");
        }
    }

    public static void setMaxDaySessionNumbers(int i) {
        if (i <= 0) {
            f12205e.m13978e((Object) "maxDaySessionNumbers must be greater than 0.");
        } else {
            f12224x = i;
        }
    }

    public static void setMaxParallelTimmingEvents(int i) {
        if (m13905a(i, 1, Opcodes.ACC_SYNTHETIC)) {
            f12217q = i;
        } else {
            f12205e.error((Object) "setMaxParallelTimmingEvents can not exceed the range of [1, 4096].");
        }
    }

    public static void setMaxReportEventLength(int i) {
        if (i <= 0) {
            f12205e.error((Object) "maxReportEventLength on setMaxReportEventLength() must greater than 0.");
        } else {
            f12198A = i;
        }
    }

    public static void setMaxSendRetryCount(int i) {
        if (m13905a(i, 1, XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER)) {
            f12211k = i;
        } else {
            f12205e.error((Object) "setMaxSendRetryCount can not exceed the range of [1,1000].");
        }
    }

    public static void setMaxSessionStatReportCount(int i) {
        if (i < 0) {
            f12205e.error((Object) "maxSessionStatReportCount cannot be less than 0.");
        } else {
            f12222v = i;
        }
    }

    public static void setMaxStoreEventCount(int i) {
        if (m13905a(i, 0, 500000)) {
            f12209i = i;
        } else {
            f12205e.error((Object) "setMaxStoreEventCount can not exceed the range of [0, 500000].");
        }
    }

    public static void setQQ(Context context, String str) {
        StatService.reportQQ(context, str);
    }

    public static void setSendPeriodMinutes(int i) {
        if (m13905a(i, 1, 10080)) {
            f12216p = i;
        } else {
            f12205e.error((Object) "setSendPeriodMinutes can not exceed the range of [1, 7*24*60] minutes.");
        }
    }

    public static void setSessionTimoutMillis(int i) {
        if (m13905a(i, XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER, 86400000)) {
            f12208h = i;
        } else {
            f12205e.error((Object) "setSessionTimoutMillis can not exceed the range of [1000, 24 * 60 * 60 * 1000].");
        }
    }

    public static void setStatReportUrl(String str) {
        if (str == null || str.length() == 0) {
            f12205e.error((Object) "statReportUrl cannot be null or empty.");
        } else {
            f12221u = str;
        }
    }

    public static void setStatSendStrategy(StatReportStrategy statReportStrategy) {
        f12206f = statReportStrategy;
        f12205e.m13976d("Change to statSendStrategy: " + statReportStrategy);
    }
}
