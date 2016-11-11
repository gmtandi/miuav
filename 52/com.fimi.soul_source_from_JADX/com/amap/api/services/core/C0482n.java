package com.amap.api.services.core;

import android.content.Context;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.tencent.stat.DeviceInfo;
import com.xiaomi.market.sdk.C2537j;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.json.JSONObject;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p152c.p153a.C2924b;

/* renamed from: com.amap.api.services.core.n */
class C0482n extends bt {
    private Context f3150a;
    private String f3151b;

    public C0482n(Context context) {
        this.f3150a = context;
        this.f3151b = C0496w.m4874f(context);
    }

    private C0483o m4842a(byte[] bArr) {
        String str = "loadData";
        if (bArr == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr));
            String optString = jSONObject.optString(RMsgInfo.COL_STATUS);
            if (Constants.VIA_RESULT_SUCCESS.equals(optString) || !Constants.VIA_TO_TYPE_QQ_GROUP.equals(optString)) {
                return null;
            }
            JSONObject a = m4843a(jSONObject, "result");
            return new C0483o(m4844a(m4845b(m4843a(m4843a(a, "common"), "commoninfo"), "com_isupload")), m4844a(m4845b(m4843a(m4843a(a, "exception"), "exceptinfo"), "ex_isupload")));
        } catch (Throwable e) {
            C0471d.m4762a(e, "ManifestManager", str);
            return null;
        } catch (Throwable e2) {
            C0471d.m4762a(e2, "ManifestManager", str);
            return null;
        }
    }

    private JSONObject m4843a(JSONObject jSONObject, String str) {
        return jSONObject == null ? null : jSONObject.optJSONObject(str);
    }

    private boolean m4844a(String str) {
        return str != null && str.equals(Constants.VIA_TO_TYPE_QQ_GROUP);
    }

    private String m4845b(JSONObject jSONObject, String str) {
        return jSONObject == null ? null : jSONObject.optString(str);
    }

    public C0483o m4846a() {
        String str = "feachManifest";
        try {
            bs a = bs.m4739a(false);
            m4444a(ac.m4479a(this.f3150a));
            return m4842a(a.m4749a((bt) this));
        } catch (Throwable e) {
            C0471d.m4762a(e, "ManifestManager", str);
            return null;
        }
    }

    public String m4847b() {
        return C0470c.m4755a() + "/config/resource";
    }

    public Map<String, String> c_() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(SharedPref.KEY, this.f3151b);
        hashMap.put("opertype", "common;exception");
        hashMap.put("plattype", "android");
        hashMap.put("product", "sea");
        hashMap.put(C2537j.aq, "2.5.0");
        hashMap.put("ext", C2924b.f14791c);
        hashMap.put("output", "json");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(this.f3151b);
        stringBuffer.append("&opertype=common;exception");
        stringBuffer.append("&plattype=android");
        stringBuffer.append("&product=").append("sea");
        stringBuffer.append("&version=").append("2.5.0");
        stringBuffer.append("&ext=standard");
        stringBuffer.append("&output=json");
        String a = ae.m4500a(stringBuffer.toString());
        String a2 = C0498y.m4877a();
        hashMap.put(DeviceInfo.TAG_TIMESTAMPS, a2);
        hashMap.put("scode", C0498y.m4881a(this.f3150a, a2, a));
        return hashMap;
    }

    public Map<String, String> d_() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(C3004e.f15013Y, "AMAP SDK Android Search 2.5.0");
        return hashMap;
    }

    public HttpEntity m4848e() {
        return null;
    }
}
