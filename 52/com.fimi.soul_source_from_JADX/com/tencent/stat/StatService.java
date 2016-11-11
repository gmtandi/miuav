package com.tencent.stat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.tencent.stat.common.C2408a;
import com.tencent.stat.common.C2413f;
import com.tencent.stat.common.C2418k;
import com.tencent.stat.common.C2423p;
import com.tencent.stat.common.StatConstants;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.p136a.C2394e;
import com.tencent.stat.p136a.C2395a;
import com.tencent.stat.p136a.C2396b;
import com.tencent.stat.p136a.C2397c;
import com.tencent.stat.p136a.C2398d;
import com.tencent.stat.p136a.C2400g;
import com.tencent.stat.p136a.C2401h;
import com.tencent.stat.p136a.C2403j;
import com.tencent.stat.p136a.C2404k;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

public class StatService {
    private static Handler f12240a;
    private static volatile Map<C2397c, Long> f12241b;
    private static volatile long f12242c;
    private static volatile long f12243d;
    private static volatile int f12244e;
    private static volatile String f12245f;
    private static volatile String f12246g;
    private static Map<String, Long> f12247h;
    private static StatLogger f12248i;
    private static UncaughtExceptionHandler f12249j;
    private static volatile boolean f12250k;

    static {
        f12241b = new ConcurrentHashMap();
        f12242c = 0;
        f12243d = 0;
        f12244e = 0;
        f12245f = C2915a.f14760f;
        f12246g = C2915a.f14760f;
        f12247h = new ConcurrentHashMap();
        f12248i = C2418k.m14018b();
        f12249j = null;
        f12250k = true;
    }

    static int m13922a(Context context, boolean z) {
        int i = 1;
        long currentTimeMillis = System.currentTimeMillis();
        if (!z || currentTimeMillis - f12242c < ((long) StatConfig.getSessionTimoutMillis())) {
            boolean z2 = false;
        } else {
            int i2 = 1;
        }
        f12242c = currentTimeMillis;
        if (f12243d == 0) {
            f12243d = C2418k.m14021c();
        }
        if (currentTimeMillis >= f12243d) {
            f12243d = C2418k.m14021c();
            if (C2434n.m14082a(context).m14106b(context).getUserType() != 1) {
                C2434n.m14082a(context).m14106b(context).m13890b(1);
            }
            StatConfig.m13908b(0);
            StatMid.m13914a(context);
            i2 = 1;
        }
        if (!f12250k) {
            i = i2;
        }
        if (i != 0) {
            if (StatConfig.m13913e() < StatConfig.getMaxDaySessionNumbers()) {
                C2418k.m14007F(context);
                m13932d(context);
            } else {
                f12248i.m13978e((Object) "Exceed StatConfig.getMaxDaySessionNumbers().");
            }
        }
        if (f12250k) {
            C2413f.m13995b(context);
            testSpeed(context);
            m13933e(context);
            f12250k = false;
        }
        return f12244e;
    }

    static JSONObject m13923a() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (StatConfig.f12202b.f12302d != 0) {
                jSONObject2.put("v", StatConfig.f12202b.f12302d);
            }
            jSONObject.put(Integer.toString(StatConfig.f12202b.f12299a), jSONObject2);
            jSONObject2 = new JSONObject();
            if (StatConfig.f12201a.f12302d != 0) {
                jSONObject2.put("v", StatConfig.f12201a.f12302d);
            }
            jSONObject.put(Integer.toString(StatConfig.f12201a.f12299a), jSONObject2);
        } catch (Exception e) {
            f12248i.m13977e(e);
        }
        return jSONObject;
    }

    static synchronized void m13924a(Context context) {
        synchronized (StatService.class) {
            if (context != null) {
                if (f12240a == null && m13929b(context)) {
                    if (C2413f.m13994a(context)) {
                        HandlerThread handlerThread = new HandlerThread("StatService");
                        handlerThread.start();
                        f12240a = new Handler(handlerThread.getLooper());
                        C2434n.m14082a(context);
                        C2424d.m14067a(context);
                        C2424d.m14068b();
                        StatConfig.getDeviceInfo(context);
                        f12249j = Thread.getDefaultUncaughtExceptionHandler();
                        if (StatConfig.isAutoExceptionCaught()) {
                            Thread.setDefaultUncaughtExceptionHandler(new C2427g(context.getApplicationContext()));
                        } else {
                            f12248i.warn("MTA SDK AutoExceptionCaught is disable");
                        }
                        if (StatConfig.getStatSendStrategy() == StatReportStrategy.APP_LAUNCH && C2418k.m14034h(context)) {
                            C2434n.m14082a(context).m14101a(-1);
                        }
                        f12248i.m13976d("Init MTA StatService success.");
                    } else {
                        f12248i.m13978e((Object) "ooh, Compatibility problem was found in this device!");
                        f12248i.m13978e((Object) "If you are on debug mode, please delete apk and try again.");
                        StatConfig.setEnableStatService(false);
                    }
                }
            }
        }
    }

    static void m13925a(Context context, Throwable th) {
        try {
            if (!StatConfig.isEnableStatService()) {
                return;
            }
            if (context == null) {
                f12248i.error((Object) "The Context of StatService.reportSdkSelfException() can not be null!");
                return;
            }
            C2394e c2398d = new C2398d(context, m13922a(context, false), 99, th);
            if (m13930c(context) != null) {
                m13930c(context).post(new C2431k(c2398d));
            }
        } catch (Throwable th2) {
            f12248i.m13978e("reportSdkSelfException error: " + th2);
        }
    }

    static void m13926a(Context context, Map<String, ?> map) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.sendAdditionEvent() can not be null!");
            return;
        }
        try {
            C2394e c2395a = new C2395a(context, m13922a(context, false), map);
            if (m13930c(context) != null) {
                m13930c(context).post(new C2431k(c2395a));
            }
        } catch (Throwable th) {
            f12248i.m13978e((Object) th);
            m13925a(context, th);
        }
    }

    static boolean m13927a(String str) {
        return str == null || str.length() == 0;
    }

    static boolean m13929b(Context context) {
        if (C2418k.m14017b(StatConstants.VERSION) > C2423p.m14060a(context, StatConfig.f12203c, 0)) {
            return true;
        }
        StatConfig.setEnableStatService(false);
        return false;
    }

    static Handler m13930c(Context context) {
        if (f12240a == null) {
            m13924a(context);
        }
        return f12240a;
    }

    public static void commitEvents(Context context, int i) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.commitEvents() can not be null!");
        } else if (i < -1 || i == 0) {
            f12248i.error((Object) "The maxNumber of StatService.commitEvents() should be -1 or bigger than 0.");
        } else {
            try {
                C2434n.m14082a(context).m14101a(i);
            } catch (Throwable th) {
                f12248i.m13978e((Object) th);
                m13925a(context, th);
            }
        }
    }

    static void m13932d(Context context) {
        if (m13930c(context) != null) {
            f12248i.m13976d("start new session.");
            f12244e = C2418k.m14009a();
            StatConfig.m13899a(0);
            StatConfig.m13912d();
            m13930c(context).post(new C2431k(new C2404k(context, f12244e, m13923a())));
        }
    }

    static void m13933e(Context context) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.reportNativeCrash() can not be null!");
            return;
        }
        try {
            if (m13930c(context) != null) {
                m13930c(context).post(new C2429i(context));
            }
        } catch (Throwable th) {
            f12248i.m13978e((Object) th);
            m13925a(context, th);
        }
    }

    public static void onPause(Context context) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.onPause() can not be null!");
        } else {
            trackEndPage(context, C2418k.m14038k(context));
        }
    }

    public static void onResume(Context context) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.onResume() can not be null!");
        } else {
            trackBeginPage(context, C2418k.m14038k(context));
        }
    }

    public static void reportAppMonitorStat(Context context, StatAppMonitor statAppMonitor) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.reportAppMonitorStat() can not be null!");
        } else if (statAppMonitor == null) {
            f12248i.error((Object) "The StatAppMonitor of StatService.reportAppMonitorStat() can not be null!");
        } else if (statAppMonitor.getInterfaceName() == null) {
            f12248i.error((Object) "The interfaceName of StatAppMonitor on StatService.reportAppMonitorStat() can not be null!");
        } else {
            try {
                C2394e c2401h = new C2401h(context, m13922a(context, false), statAppMonitor);
                if (m13930c(context) != null) {
                    m13930c(context).post(new C2431k(c2401h));
                }
            } catch (Throwable th) {
                f12248i.m13978e((Object) th);
                m13925a(context, th);
            }
        }
    }

    public static void reportError(Context context, String str) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.reportError() can not be null!");
        } else if (m13927a(str)) {
            f12248i.error((Object) "Error message in StatService.reportError() is empty.");
        } else {
            try {
                C2394e c2398d = new C2398d(context, m13922a(context, false), str, 0, StatConfig.getMaxReportEventLength());
                if (m13930c(context) != null) {
                    m13930c(context).post(new C2431k(c2398d));
                }
            } catch (Throwable th) {
                f12248i.m13978e((Object) th);
                m13925a(context, th);
            }
        }
    }

    public static void reportException(Context context, Throwable th) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.reportException() can not be null!");
        } else if (th == null) {
            f12248i.error((Object) "The Throwable error message of StatService.reportException() can not be null!");
        } else {
            C2394e c2398d = new C2398d(context, m13922a(context, false), 1, th);
            if (m13930c(context) != null) {
                m13930c(context).post(new C2431k(c2398d));
            }
        }
    }

    public static void reportGameUser(Context context, StatGameUser statGameUser) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.reportGameUser() can not be null!");
        } else if (statGameUser == null) {
            f12248i.error((Object) "The gameUser of StatService.reportGameUser() can not be null!");
        } else if (statGameUser.getAccount() == null || statGameUser.getAccount().length() == 0) {
            f12248i.error((Object) "The account of gameUser on StatService.reportGameUser() can not be null or empty!");
        } else {
            try {
                C2394e c2400g = new C2400g(context, m13922a(context, false), statGameUser);
                if (m13930c(context) != null) {
                    m13930c(context).post(new C2431k(c2400g));
                }
            } catch (Throwable th) {
                f12248i.m13978e((Object) th);
                m13925a(context, th);
            }
        }
    }

    public static void reportQQ(Context context, String str) {
        if (str == null) {
            str = C2915a.f14760f;
        }
        if (!StatConfig.f12204d.equals(str)) {
            StatConfig.f12204d = new String(str);
            m13926a(context, null);
        }
    }

    public static void setEnvAttributes(Context context, Map<String, String> map) {
        if (map == null || map.size() > Opcodes.ACC_INTERFACE) {
            f12248i.error((Object) "The map in setEnvAttributes can't be null or its size can't exceed 512.");
            return;
        }
        try {
            C2408a.m13983a(context, map);
        } catch (Exception e) {
            f12248i.m13977e(e);
        }
    }

    public static void startNewSession(Context context) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.startNewSession() can not be null!");
            return;
        }
        try {
            stopSession();
            m13922a(context, true);
        } catch (Throwable th) {
            f12248i.m13978e((Object) th);
            m13925a(context, th);
        }
    }

    public static boolean startStatService(Context context, String str, String str2) {
        if (StatConfig.isEnableStatService()) {
            String str3 = StatConstants.VERSION;
            f12248i.m13976d("MTA SDK version, current: " + str3 + " ,required: " + str2);
            String str4 = C2915a.f14760f;
            if (context == null || str2 == null) {
                str3 = "Context or mtaSdkVersion in StatService.startStatService() is null, please check it!";
                f12248i.error((Object) str3);
                StatConfig.setEnableStatService(false);
                throw new MtaSDkException(str3);
            } else if (C2418k.m14017b(str3) < C2418k.m14017b(str2)) {
                str3 = ("MTA SDK version conflicted, current: " + str3 + ",required: " + str2) + ". please delete the current SDK and download the latest one. official website: http://mta.qq.com/ or http://mta.oa.com/";
                f12248i.error((Object) str3);
                StatConfig.setEnableStatService(false);
                throw new MtaSDkException(str3);
            } else {
                try {
                    str3 = StatConfig.getInstallChannel(context);
                    if (str3 == null || str3.length() == 0) {
                        StatConfig.setInstallChannel("-");
                    }
                    if (str != null) {
                        StatConfig.setAppKey(context, str);
                    }
                    m13930c(context);
                    m13922a(context, false);
                    return true;
                } catch (Object th) {
                    f12248i.m13978e(th);
                    return false;
                }
            }
        }
        f12248i.error((Object) "MTA StatService is disable.");
        return false;
    }

    public static void stopSession() {
        f12242c = 0;
    }

    public static void testSpeed(Context context) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.testSpeed() can not be null!");
            return;
        }
        try {
            if (m13930c(context) != null) {
                m13930c(context).post(new C2430j(context, null));
            }
        } catch (Throwable th) {
            f12248i.m13978e((Object) th);
            m13925a(context, th);
        }
    }

    public static void testSpeed(Context context, Map<String, Integer> map) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.testSpeed() can not be null!");
        } else if (map == null || map.size() == 0) {
            f12248i.error((Object) "The domainMap of StatService.testSpeed() can not be null or empty!");
        } else {
            try {
                if (m13930c(context) != null) {
                    m13930c(context).post(new C2430j(context, map));
                }
            } catch (Throwable th) {
                f12248i.m13978e((Object) th);
                m13925a(context, th);
            }
        }
    }

    public static void trackBeginPage(Context context, String str) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null || str == null || str.length() == 0) {
            f12248i.error((Object) "The Context or pageName of StatService.trackBeginPage() can not be null or empty!");
            return;
        }
        try {
            synchronized (f12247h) {
                if (f12247h.size() >= StatConfig.getMaxParallelTimmingEvents()) {
                    f12248i.error("The number of page events exceeds the maximum value " + Integer.toString(StatConfig.getMaxParallelTimmingEvents()));
                    return;
                }
                f12245f = str;
                if (f12247h.containsKey(f12245f)) {
                    f12248i.m13978e("Duplicate PageID : " + f12245f + ", onResume() repeated?");
                    return;
                }
                f12247h.put(f12245f, Long.valueOf(System.currentTimeMillis()));
                m13922a(context, true);
            }
        } catch (Throwable th) {
            f12248i.m13978e((Object) th);
            m13925a(context, th);
        }
    }

    public static void trackCustomBeginEvent(Context context, String str, String... strArr) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.trackCustomBeginEvent() can not be null!");
        } else if (m13927a(str)) {
            f12248i.error((Object) "The event_id of StatService.trackCustomBeginEvent() can not be null or empty.");
        } else {
            try {
                C2397c c2397c = new C2397c(str, strArr, null);
                if (f12241b.containsKey(c2397c)) {
                    f12248i.error("Duplicate CustomEvent key: " + c2397c.toString() + ", trackCustomBeginEvent() repeated?");
                } else if (f12241b.size() <= StatConfig.getMaxParallelTimmingEvents()) {
                    f12241b.put(c2397c, Long.valueOf(System.currentTimeMillis()));
                } else {
                    f12248i.error("The number of timedEvent exceeds the maximum value " + Integer.toString(StatConfig.getMaxParallelTimmingEvents()));
                }
            } catch (Throwable th) {
                f12248i.m13978e((Object) th);
                m13925a(context, th);
            }
        }
    }

    public static void trackCustomBeginKVEvent(Context context, String str, Properties properties) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.trackCustomBeginEvent() can not be null!");
        } else if (m13927a(str)) {
            f12248i.error((Object) "The event_id of StatService.trackCustomBeginEvent() can not be null or empty.");
        } else {
            try {
                C2397c c2397c = new C2397c(str, null, properties);
                if (f12241b.containsKey(c2397c)) {
                    f12248i.error("Duplicate CustomEvent key: " + c2397c.toString() + ", trackCustomBeginKVEvent() repeated?");
                } else if (f12241b.size() <= StatConfig.getMaxParallelTimmingEvents()) {
                    f12241b.put(c2397c, Long.valueOf(System.currentTimeMillis()));
                } else {
                    f12248i.error("The number of timedEvent exceeds the maximum value " + Integer.toString(StatConfig.getMaxParallelTimmingEvents()));
                }
            } catch (Throwable th) {
                f12248i.m13978e((Object) th);
                m13925a(context, th);
            }
        }
    }

    public static void trackCustomEndEvent(Context context, String str, String... strArr) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.trackCustomEndEvent() can not be null!");
        } else if (m13927a(str)) {
            f12248i.error((Object) "The event_id of StatService.trackCustomEndEvent() can not be null or empty.");
        } else {
            try {
                C2397c c2397c = new C2397c(str, strArr, null);
                Long l = (Long) f12241b.remove(c2397c);
                if (l != null) {
                    C2394e c2396b = new C2396b(context, m13922a(context, false), str);
                    c2396b.m13945a(strArr);
                    l = Long.valueOf((System.currentTimeMillis() - l.longValue()) / 1000);
                    c2396b.m13943a(Long.valueOf(l.longValue() == 0 ? 1 : l.longValue()).longValue());
                    if (m13930c(context) != null) {
                        m13930c(context).post(new C2431k(c2396b));
                        return;
                    }
                    return;
                }
                f12248i.error("No start time found for custom event: " + c2397c.toString() + ", lost trackCustomBeginEvent()?");
            } catch (Throwable th) {
                f12248i.m13978e((Object) th);
                m13925a(context, th);
            }
        }
    }

    public static void trackCustomEndKVEvent(Context context, String str, Properties properties) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.trackCustomEndEvent() can not be null!");
        } else if (m13927a(str)) {
            f12248i.error((Object) "The event_id of StatService.trackCustomEndEvent() can not be null or empty.");
        } else {
            try {
                C2397c c2397c = new C2397c(str, null, properties);
                Long l = (Long) f12241b.remove(c2397c);
                if (l != null) {
                    C2394e c2396b = new C2396b(context, m13922a(context, false), str);
                    c2396b.m13944a(properties);
                    l = Long.valueOf((System.currentTimeMillis() - l.longValue()) / 1000);
                    c2396b.m13943a(Long.valueOf(l.longValue() == 0 ? 1 : l.longValue()).longValue());
                    if (m13930c(context) != null) {
                        m13930c(context).post(new C2431k(c2396b));
                        return;
                    }
                    return;
                }
                f12248i.error("No start time found for custom event: " + c2397c.toString() + ", lost trackCustomBeginKVEvent()?");
            } catch (Throwable th) {
                f12248i.m13978e((Object) th);
                m13925a(context, th);
            }
        }
    }

    public static void trackCustomEvent(Context context, String str, String... strArr) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.trackCustomEvent() can not be null!");
        } else if (m13927a(str)) {
            f12248i.error((Object) "The event_id of StatService.trackCustomEvent() can not be null or empty.");
        } else {
            try {
                C2394e c2396b = new C2396b(context, m13922a(context, false), str);
                c2396b.m13945a(strArr);
                if (m13930c(context) != null) {
                    m13930c(context).post(new C2431k(c2396b));
                }
            } catch (Throwable th) {
                f12248i.m13978e((Object) th);
                m13925a(context, th);
            }
        }
    }

    public static void trackCustomKVEvent(Context context, String str, Properties properties) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null) {
            f12248i.error((Object) "The Context of StatService.trackCustomEvent() can not be null!");
        } else if (m13927a(str)) {
            f12248i.error((Object) "The event_id of StatService.trackCustomEvent() can not be null or empty.");
        } else {
            try {
                C2394e c2396b = new C2396b(context, m13922a(context, false), str);
                c2396b.m13944a(properties);
                if (m13930c(context) != null) {
                    m13930c(context).post(new C2431k(c2396b));
                }
            } catch (Throwable th) {
                f12248i.m13978e((Object) th);
                m13925a(context, th);
            }
        }
    }

    public static void trackEndPage(Context context, String str) {
        if (!StatConfig.isEnableStatService()) {
            return;
        }
        if (context == null || str == null || str.length() == 0) {
            f12248i.error((Object) "The Context or pageName of StatService.trackEndPage() can not be null or empty!");
            return;
        }
        try {
            Long l;
            synchronized (f12247h) {
                l = (Long) f12247h.remove(str);
            }
            if (l != null) {
                Long valueOf = Long.valueOf((System.currentTimeMillis() - l.longValue()) / 1000);
                if (valueOf.longValue() <= 0) {
                    valueOf = Long.valueOf(1);
                }
                String str2 = f12246g;
                if (str2 != null && str2.equals(str)) {
                    str2 = "-";
                }
                if (m13930c(context) != null) {
                    C2394e c2403j = new C2403j(context, str2, str, m13922a(context, false), valueOf);
                    if (!str.equals(f12245f)) {
                        f12248i.warn("Invalid invocation since previous onResume on diff page.");
                    }
                    m13930c(context).post(new C2431k(c2403j));
                }
                f12246g = str;
                return;
            }
            f12248i.m13978e("Starttime for PageID:" + str + " not found, lost onResume()?");
        } catch (Throwable th) {
            f12248i.m13978e((Object) th);
            m13925a(context, th);
        }
    }
}
