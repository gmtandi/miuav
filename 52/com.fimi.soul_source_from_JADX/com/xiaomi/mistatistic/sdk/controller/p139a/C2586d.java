package com.xiaomi.mistatistic.sdk.controller.p139a;

import android.text.TextUtils;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.xiaomi.market.sdk.C2537j;
import com.xiaomi.mistatistic.sdk.BuildSetting;
import com.xiaomi.mistatistic.sdk.controller.C2582e;
import com.xiaomi.mistatistic.sdk.controller.C2588a;
import com.xiaomi.mistatistic.sdk.controller.C2593g;
import com.xiaomi.mistatistic.sdk.controller.C2601o;
import com.xiaomi.mistatistic.sdk.controller.C2603q;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.a.d */
public class C2586d implements C2582e {
    private C2587e f12936a;
    private String f12937b;

    public C2586d(String str, C2587e c2587e) {
        this.f12936a = c2587e;
        this.f12937b = str;
    }

    public void m14706a() {
        boolean z = false;
        C2601o c2601o = new C2601o();
        try {
            List arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("app_id", C2588a.m14710b()));
            arrayList.add(new BasicNameValuePair("app_key", C2588a.m14711c()));
            arrayList.add(new BasicNameValuePair("device_id", new C2593g().m14742a()));
            arrayList.add(new BasicNameValuePair("channel", C2588a.m14712d()));
            Object e = C2588a.m14713e();
            if (!TextUtils.isEmpty(e)) {
                arrayList.add(new BasicNameValuePair(C2537j.aq, e));
            }
            arrayList.add(new BasicNameValuePair("stat_value", this.f12937b));
            e = C2603q.m14771a(C2588a.m14708a(), BuildSetting.isTest() ? "http://10.99.168.145:8097/mistats" : "https://data.mistat.xiaomi.com/mistats", arrayList);
            c2601o.m14769a("Upload MiStat data complete, result=" + e);
            if (!TextUtils.isEmpty(e)) {
                if ("ok".equals(new JSONObject(e).getString(RMsgInfo.COL_STATUS))) {
                    z = true;
                }
            }
        } catch (Throwable e2) {
            c2601o.m14770a("Upload MiStat data failed", e2);
        } catch (Throwable e22) {
            c2601o.m14770a("Result parse failed", e22);
        }
        this.f12936a.m14707a(z);
    }
}
