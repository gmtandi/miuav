package com.p016a;

import android.content.Context;
import android.util.Log;
import com.baidu.tts.loopj.AsyncHttpClient;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.tencent.stat.DeviceInfo;
import com.xiaomi.market.sdk.C2537j;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p152c.p161f.C2989l;

/* renamed from: com.a.fo */
public class fo {
    public static int f1217a;
    public static String f1218b;
    private static gd f1219c;
    private static String f1220d;
    private static String f1221e;

    static {
        f1217a = -1;
        f1218b = C2915a.f14760f;
        f1220d = "http://apiinit.amap.com/v3/log/init";
        f1221e = null;
    }

    private static String m1844a() {
        return f1220d;
    }

    private static Map<String, String> m1845a(Context context) {
        Map<String, String> hashMap = new HashMap();
        try {
            hashMap.put("resType", "json");
            hashMap.put("encode", C1142e.f5201a);
            String a = fp.m1850a();
            hashMap.put(DeviceInfo.TAG_TIMESTAMPS, a);
            hashMap.put(SharedPref.KEY, fn.m1842f(context));
            hashMap.put("scode", fp.m1854a(context, a, gf.m1956a("resType=json&encode=UTF-8&key=" + fn.m1842f(context))));
        } catch (Throwable th) {
            C0247g.m1917a(th, "Auth", "gParams");
        }
        return hashMap;
    }

    public static void m1846a(String str) {
        fn.m1839c(str);
    }

    public static synchronized boolean m1847a(Context context, gd gdVar) {
        boolean a;
        synchronized (fo.class) {
            a = fo.m1848a(context, gdVar, true);
        }
        return a;
    }

    private static boolean m1848a(Context context, gd gdVar, boolean z) {
        boolean z2 = true;
        f1219c = gdVar;
        try {
            String a = fo.m1844a();
            Map hashMap = new HashMap();
            hashMap.put(C3004e.f15031q, C2989l.f14939a);
            hashMap.put(C3004e.f15017c, AsyncHttpClient.ENCODING_GZIP);
            hashMap.put(C3004e.f15024j, "Keep-Alive");
            hashMap.put(C3004e.f15013Y, f1219c.f1263b);
            hashMap.put("X-INFO", fp.m1853a(context, f1219c, null, z));
            hashMap.put("logversion", "2.1");
            hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", new Object[]{f1219c.f1262a, f1219c.f1264c}));
            bk a2 = bk.m1169a();
            bp ggVar = new gg();
            ggVar.m1035a(ga.m1922a(context));
            ggVar.m1971a(hashMap);
            ggVar.m1973b(fo.m1845a(context));
            ggVar.m1970a(a);
            z2 = fo.m1849a(a2.m1173b(ggVar));
        } catch (Throwable th) {
            C0247g.m1917a(th, "Auth", "getAuth");
        }
        return z2;
    }

    private static boolean m1849a(byte[] bArr) {
        if (bArr == null) {
            return true;
        }
        String str;
        try {
            str = new String(bArr, C1142e.f5201a);
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(RMsgInfo.COL_STATUS)) {
                    int i = jSONObject.getInt(RMsgInfo.COL_STATUS);
                    if (i == 1) {
                        f1217a = 1;
                    } else if (i == 0) {
                        f1217a = 0;
                    }
                }
                if (jSONObject.has(C2537j.ag)) {
                    f1218b = jSONObject.getString(C2537j.ag);
                }
                if (f1217a == 0) {
                    Log.i("AuthFailure", f1218b);
                }
                return f1217a == 1;
            } catch (Throwable e) {
                C0247g.m1917a(e, "Auth", "lData");
                return false;
            } catch (Throwable e2) {
                C0247g.m1917a(e2, "Auth", "lData");
                return false;
            }
        } catch (UnsupportedEncodingException e3) {
            str = new String(bArr);
        }
    }
}
