package com.tencent.stat;

import android.content.Context;
import com.tencent.stat.common.C2418k;
import com.tencent.stat.common.StatLogger;
import org.p122a.p123a.C2915a;

public class StatMid {
    private static StatLogger f12230a;
    private static DeviceInfo f12231b;

    static {
        f12230a = C2418k.m14018b();
        f12231b = null;
    }

    static synchronized DeviceInfo m13914a(Context context) {
        DeviceInfo a;
        synchronized (StatMid.class) {
            try {
                C2405a a2 = C2405a.m13962a(context);
                DeviceInfo a3 = m13917a(a2.m13969d(DeviceInfo.TAG_FLAG, null));
                f12230a.m13976d("get device info from internal storage:" + a3);
                DeviceInfo a4 = m13917a(a2.m13971f(DeviceInfo.TAG_FLAG, null));
                f12230a.m13976d("get device info from setting.system:" + a4);
                a = m13917a(a2.m13967b(DeviceInfo.TAG_FLAG, null));
                f12230a.m13976d("get device info from SharedPreference:" + a);
                f12231b = m13916a(a, a4, a3);
                if (f12231b == null) {
                    f12231b = new DeviceInfo();
                }
                a = C2434n.m14082a(context).m14106b(context);
                if (a != null) {
                    f12231b.m13894d(a.getImei());
                    f12231b.m13895e(a.getMac());
                    f12231b.m13890b(a.getUserType());
                }
            } catch (Object th) {
                f12230a.m13978e(th);
            }
            a = f12231b;
        }
        return a;
    }

    static DeviceInfo m13915a(DeviceInfo deviceInfo, DeviceInfo deviceInfo2) {
        return (deviceInfo == null || deviceInfo2 == null) ? deviceInfo == null ? deviceInfo2 != null ? deviceInfo2 : null : deviceInfo : deviceInfo.m13886a(deviceInfo2) >= 0 ? deviceInfo : deviceInfo2;
    }

    static DeviceInfo m13916a(DeviceInfo deviceInfo, DeviceInfo deviceInfo2, DeviceInfo deviceInfo3) {
        return m13915a(m13915a(deviceInfo, deviceInfo2), m13915a(deviceInfo2, deviceInfo3));
    }

    private static DeviceInfo m13917a(String str) {
        return str != null ? DeviceInfo.m13884a(C2418k.m14026d(str)) : null;
    }

    public static DeviceInfo getDeviceInfo(Context context) {
        if (context == null) {
            f12230a.error((Object) "Context for StatConfig.getDeviceInfo is null.");
            return null;
        }
        if (f12231b == null) {
            m13914a(context);
        }
        return f12231b;
    }

    public static String getMid(Context context) {
        if (f12231b == null) {
            getDeviceInfo(context);
        }
        return f12231b.getMid();
    }

    public static void updateDeviceInfo(Context context, String str) {
        try {
            getDeviceInfo(context);
            f12231b.m13893c(str);
            f12231b.m13887a(f12231b.m13885a() + 1);
            f12231b.m13888a(System.currentTimeMillis());
            String jSONObject = f12231b.m13892c().toString();
            f12230a.m13976d("save DeviceInfo:" + jSONObject);
            jSONObject = C2418k.m14023c(jSONObject).replace("\n", C2915a.f14760f);
            C2405a a = C2405a.m13962a(context);
            a.m13968c(DeviceInfo.TAG_FLAG, jSONObject);
            a.m13970e(DeviceInfo.TAG_FLAG, jSONObject);
            a.m13966a(DeviceInfo.TAG_FLAG, jSONObject);
        } catch (Object th) {
            f12230a.m13978e(th);
        }
    }
}
