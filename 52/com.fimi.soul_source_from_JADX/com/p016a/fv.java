package com.p016a;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.tencent.stat.DeviceInfo;
import com.xiaomi.market.sdk.C2537j;
import java.util.HashMap;
import java.util.Map;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.C3004e;

/* renamed from: com.a.fv */
class fv extends bp {
    private Context f1238d;
    private gd f1239e;
    private String f1240f;

    fv(Context context, gd gdVar, String str) {
        this.f1240f = C2915a.f14760f;
        this.f1238d = context;
        this.f1239e = gdVar;
        this.f1240f = str;
    }

    public Map<String, String> m1868a() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(C3004e.f15013Y, this.f1239e.m1941c());
        hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", new Object[]{this.f1239e.m1940b(), this.f1239e.m1938a()}));
        hashMap.put("logversion", "2.0");
        return hashMap;
    }

    public Map<String, String> m1869b() {
        Object q = fw.m1892q(this.f1238d);
        if (!TextUtils.isEmpty(q)) {
            q = fz.m1911b(new StringBuilder(q).reverse().toString());
        }
        Map hashMap = new HashMap();
        hashMap.put(SharedPref.KEY, fn.m1842f(this.f1238d));
        hashMap.put("opertype", this.f1240f);
        hashMap.put("plattype", "android");
        hashMap.put("product", this.f1239e.m1938a());
        hashMap.put(C2537j.aq, this.f1239e.m1940b());
        hashMap.put("output", "json");
        hashMap.put("androidversion", VERSION.SDK_INT + C2915a.f14760f);
        hashMap.put(C2537j.as, q);
        hashMap.put("abitype", Build.CPU_ABI);
        hashMap.put("ext", this.f1239e.m1942d());
        String a = fp.m1850a();
        String a2 = fp.m1854a(this.f1238d, a, gf.m1963b(hashMap));
        hashMap.put(DeviceInfo.TAG_TIMESTAMPS, a);
        hashMap.put("scode", a2);
        return hashMap;
    }

    public String m1870c() {
        return "https://restapi.amap.com/v3/config/resource?";
    }
}
