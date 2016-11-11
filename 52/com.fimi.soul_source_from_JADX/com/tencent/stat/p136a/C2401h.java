package com.tencent.stat.p136a;

import android.content.Context;
import com.tencent.stat.StatAppMonitor;
import com.tencent.stat.common.C2418k;
import org.json.JSONObject;

/* renamed from: com.tencent.stat.a.h */
public class C2401h extends C2394e {
    private static String f12282l;
    private static String f12283m;
    private StatAppMonitor f12284a;

    static {
        f12282l = null;
        f12283m = null;
    }

    public C2401h(Context context, int i, StatAppMonitor statAppMonitor) {
        super(context, i);
        this.f12284a = null;
        this.f12284a = statAppMonitor.clone();
    }

    public C2399f m13953a() {
        return C2399f.MONITOR_STAT;
    }

    public boolean m13954a(JSONObject jSONObject) {
        if (this.f12284a == null) {
            return false;
        }
        jSONObject.put("na", this.f12284a.getInterfaceName());
        jSONObject.put("rq", this.f12284a.getReqSize());
        jSONObject.put("rp", this.f12284a.getRespSize());
        jSONObject.put("rt", this.f12284a.getResultType());
        jSONObject.put("tm", this.f12284a.getMillisecondsConsume());
        jSONObject.put("rc", this.f12284a.getReturnCode());
        jSONObject.put("sp", this.f12284a.getSampling());
        if (f12283m == null) {
            f12283m = C2418k.m14045r(this.k);
        }
        C2418k.m14014a(jSONObject, "av", f12283m);
        if (f12282l == null) {
            f12282l = C2418k.m14040m(this.k);
        }
        C2418k.m14014a(jSONObject, "op", f12282l);
        jSONObject.put("cn", C2418k.m14043p(this.k));
        return true;
    }
}
