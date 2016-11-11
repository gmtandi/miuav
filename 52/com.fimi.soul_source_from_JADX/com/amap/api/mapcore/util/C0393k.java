package com.amap.api.mapcore.util;

import android.content.Context;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.tencent.stat.DeviceInfo;
import com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration;
import com.xiaomi.market.sdk.C2537j;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.amap.api.mapcore.util.k */
public class C0393k extends aj<String, C0392j> {
    public C0393k(Context context, String str) {
        super(context, str);
        getClass();
        m3432a((int) FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS);
        getClass();
        m3435b(FDSClientConfiguration.DEFAULT_SOCKET_TIMEOUT_MS);
    }

    protected C0392j m4138a(String str) {
        C0392j c0392j = new C0392j();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("offlinemap")) {
                jSONObject = jSONObject.getJSONObject("offlinemap");
                String optString = jSONObject.optString("update", C2915a.f14760f);
                if (optString.equals(Constants.VIA_RESULT_SUCCESS)) {
                    c0392j.m4136a(false);
                } else if (optString.equals(Constants.VIA_TO_TYPE_QQ_GROUP)) {
                    c0392j.m4136a(true);
                }
                c0392j.m4135a(jSONObject.optString(C2537j.aq, C2915a.f14760f));
            }
        } catch (Throwable th) {
            ce.m3829a(th, "OfflineInitHandler", "loadData parseJson");
        }
        return c0392j;
    }

    public String m4139a() {
        return "http://restapi.amap.com/v3/config/version";
    }

    protected /* synthetic */ Object m4140b(String str) {
        return m4138a(str);
    }

    public Map<String, String> m4141b() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("mapver", this.a);
        hashMap.put("output", "json");
        hashMap.put(SharedPref.KEY, bl.m3652f(this.d));
        hashMap.put("opertype", "offlinemap_with_province_vfour");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("mapver=").append((String) this.a);
        stringBuffer.append("&output=json");
        stringBuffer.append("&key=").append(bl.m3652f(this.d));
        stringBuffer.append("&opertype=offlinemap_with_province_vfour");
        String d = bx.m3784d(stringBuffer.toString());
        String a = bn.m3661a();
        hashMap.put(DeviceInfo.TAG_TIMESTAMPS, a);
        hashMap.put("scode", bn.m3667a(this.d, a, d));
        return hashMap;
    }
}
