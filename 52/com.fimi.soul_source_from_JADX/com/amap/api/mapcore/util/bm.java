package com.amap.api.mapcore.util;

import android.content.Context;
import android.util.Log;
import com.baidu.tts.loopj.AsyncHttpClient;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.tencent.stat.DeviceInfo;
import com.xiaomi.market.sdk.C2537j;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p152c.p161f.C2989l;

public class bm {
    public static int f2214a;
    public static String f2215b;
    private static bv f2216c;
    private static String f2217d;
    private static String f2218e;

    static {
        f2214a = -1;
        f2215b = C2915a.f14760f;
        f2217d = "http://apiinit.amap.com/v3/log/init";
        f2218e = null;
    }

    private static String m3654a() {
        return f2217d;
    }

    private static Map<String, String> m3655a(Context context) {
        Map<String, String> hashMap = new HashMap();
        try {
            hashMap.put("resType", "json");
            hashMap.put("encode", C1142e.f5201a);
            String a = bn.m3661a();
            hashMap.put(DeviceInfo.TAG_TIMESTAMPS, a);
            hashMap.put(SharedPref.KEY, bl.m3652f(context));
            hashMap.put("scode", bn.m3667a(context, a, bx.m3784d("resType=json&encode=UTF-8&key=" + bl.m3652f(context))));
        } catch (Throwable th) {
            cb.m3809a(th, "Auth", "gParams");
        }
        return hashMap;
    }

    public static void m3656a(String str) {
        bl.m3647a(str);
    }

    public static synchronized boolean m3657a(Context context, bv bvVar) {
        boolean a;
        synchronized (bm.class) {
            a = m3658a(context, bvVar, true);
        }
        return a;
    }

    private static boolean m3658a(Context context, bv bvVar, boolean z) {
        boolean z2 = true;
        f2216c = bvVar;
        try {
            String a = m3654a();
            Map hashMap = new HashMap();
            hashMap.put(C3004e.f15031q, C2989l.f14939a);
            hashMap.put(C3004e.f15017c, AsyncHttpClient.ENCODING_GZIP);
            hashMap.put(C3004e.f15024j, "Keep-Alive");
            hashMap.put(C3004e.f15013Y, f2216c.f2283b);
            hashMap.put("X-INFO", bn.m3665a(context, f2216c, null, z));
            hashMap.put("logversion", "2.1");
            hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", new Object[]{f2216c.f2282a, f2216c.f2284c}));
            dd a2 = dd.m4003a();
            dj byVar = new by();
            byVar.m3433a(bt.m3735a(context));
            byVar.m3791a(hashMap);
            byVar.m3793b(m3655a(context));
            byVar.m3790a(a);
            z2 = m3659a(a2.m4006b(byVar));
        } catch (Throwable th) {
            cb.m3809a(th, "Auth", "getAuth");
        }
        return z2;
    }

    private static boolean m3659a(byte[] bArr) {
        if (bArr == null) {
            return true;
        }
        try {
            JSONObject jSONObject = new JSONObject(bx.m3772a(bArr));
            if (jSONObject.has(RMsgInfo.COL_STATUS)) {
                int i = jSONObject.getInt(RMsgInfo.COL_STATUS);
                if (i == 1) {
                    f2214a = 1;
                } else if (i == 0) {
                    f2214a = 0;
                }
            }
            if (jSONObject.has(C2537j.ag)) {
                f2215b = jSONObject.getString(C2537j.ag);
            }
            if (f2214a == 0) {
                Log.i("AuthFailure", f2215b);
            }
            return f2214a == 1;
        } catch (Throwable e) {
            cb.m3809a(e, "Auth", "lData");
            return false;
        } catch (Throwable e2) {
            cb.m3809a(e2, "Auth", "lData");
            return false;
        }
    }

    public static synchronized boolean m3660b(Context context, bv bvVar) {
        boolean a;
        synchronized (bm.class) {
            a = m3658a(context, bvVar, false);
        }
        return a;
    }
}
