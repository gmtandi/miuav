package com.tencent.connect.p128a;

import android.content.Context;
import com.fimi.soul.biz.p096d.C1316b;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.utils.OpenConfig;
import java.lang.reflect.Method;

/* renamed from: com.tencent.connect.a.a */
public class C2186a {
    private static Class<?> f11347a;
    private static Class<?> f11348b;
    private static Method f11349c;
    private static Method f11350d;
    private static Method f11351e;
    private static Method f11352f;
    private static boolean f11353g;

    static {
        f11347a = null;
        f11348b = null;
        f11349c = null;
        f11350d = null;
        f11351e = null;
        f11352f = null;
        f11353g = false;
    }

    public static void m13215a(Context context, QQToken qQToken, String str, String... strArr) {
        if (f11353g) {
            C2186a.m13217b(context, qQToken);
            try {
                f11350d.invoke(f11348b, new Object[]{context, str, strArr});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean m13216a(Context context, QQToken qQToken) {
        return OpenConfig.getInstance(context, qQToken.getAppId()).getBoolean("Common_ta_enable");
    }

    public static void m13217b(Context context, QQToken qQToken) {
        try {
            if (C2186a.m13216a(context, qQToken)) {
                f11352f.invoke(f11347a, new Object[]{Boolean.valueOf(true)});
                return;
            }
            f11352f.invoke(f11347a, new Object[]{Boolean.valueOf(false)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void m13218c(Context context, QQToken qQToken) {
        String str = "Aqc" + qQToken.getAppId();
        try {
            f11347a = Class.forName("com.tencent.stat.StatConfig");
            f11348b = Class.forName("com.tencent.stat.StatService");
            f11349c = f11348b.getMethod("reportQQ", new Class[0]);
            f11350d = f11348b.getMethod("trackCustomEvent", new Class[0]);
            f11351e = f11348b.getMethod("commitEvents", new Class[0]);
            f11352f = f11347a.getMethod("setEnableStatService", new Class[0]);
            C2186a.m13217b(context, qQToken);
            f11347a.getMethod("setAutoExceptionCaught", new Class[0]).invoke(f11347a, new Object[]{Boolean.valueOf(false)});
            f11347a.getMethod("setEnableSmartReporting", new Class[0]).invoke(f11347a, new Object[]{Boolean.valueOf(true)});
            f11347a.getMethod("setSendPeriodMinutes", new Class[0]).invoke(f11347a, new Object[]{Integer.valueOf(1440)});
            f11347a.getMethod("setStatSendStrategy", new Class[0]).invoke(f11347a, new Object[]{Integer.valueOf(Class.forName("com.tencent.stat.StatReportStrategy").getField("PERIOD").getInt(null))});
            f11347a.getMethod("setStatReportUrl", new Class[0]).invoke(f11347a, new Object[]{"http://cgi.connect.qq.com/qqconnectutil/sdk"});
            f11348b.getMethod("startStatService", new Class[0]).invoke(f11348b, new Object[]{context, str, Integer.valueOf(Class.forName("com.tencent.stat.common.StatConstants").getField(C1316b.f5910e).getInt(null))});
            f11353g = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void m13219d(Context context, QQToken qQToken) {
        if (f11353g) {
            C2186a.m13217b(context, qQToken);
            if (qQToken.getOpenId() != null) {
                try {
                    f11349c.invoke(f11348b, new Object[]{context, qQToken.getOpenId()});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
