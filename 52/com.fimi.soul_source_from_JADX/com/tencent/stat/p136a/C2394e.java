package com.tencent.stat.p136a;

import android.content.Context;
import com.tencent.stat.C2434n;
import com.tencent.stat.DeviceInfo;
import com.tencent.stat.StatConfig;
import com.tencent.stat.common.C2418k;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.tencent.stat.a.e */
public abstract class C2394e {
    private static volatile boolean f12251a;
    protected String f12252b;
    protected long f12253c;
    protected int f12254d;
    protected DeviceInfo f12255e;
    protected int f12256f;
    protected String f12257g;
    protected String f12258h;
    protected String f12259i;
    protected String f12260j;
    protected Context f12261k;

    static {
        f12251a = false;
    }

    C2394e(Context context, int i) {
        this.f12252b = null;
        this.f12255e = null;
        this.f12257g = null;
        this.f12258h = null;
        this.f12259i = null;
        this.f12260j = null;
        this.f12261k = context;
        this.f12253c = System.currentTimeMillis() / 1000;
        this.f12254d = i;
        this.f12252b = StatConfig.getAppKey(context);
        this.f12257g = StatConfig.getCustomUserId(context);
        this.f12255e = C2434n.m14082a(context).m14106b(context);
        this.f12256f = C2418k.m14050w(context).intValue();
        this.f12259i = C2418k.m14041n(context);
        this.f12258h = StatConfig.getInstallChannel(context);
    }

    public abstract C2399f m13934a();

    public abstract boolean m13935a(JSONObject jSONObject);

    public long m13936b() {
        return this.f12253c;
    }

    public boolean m13937b(JSONObject jSONObject) {
        try {
            C2418k.m14014a(jSONObject, "ky", this.f12252b);
            jSONObject.put("et", m13934a().m13950a());
            if (this.f12255e != null) {
                jSONObject.put(DeviceInfo.TAG_IMEI, this.f12255e.getImei());
                C2418k.m14014a(jSONObject, DeviceInfo.TAG_MAC, this.f12255e.getMac());
                jSONObject.put("ut", this.f12255e.getUserType());
            }
            C2418k.m14014a(jSONObject, "cui", this.f12257g);
            if (m13934a() != C2399f.SESSION_ENV) {
                C2418k.m14014a(jSONObject, "av", this.f12259i);
                C2418k.m14014a(jSONObject, "ch", this.f12258h);
            }
            C2418k.m14014a(jSONObject, DeviceInfo.TAG_MID, StatConfig.getMid(this.f12261k));
            jSONObject.put("idx", this.f12256f);
            jSONObject.put("si", this.f12254d);
            jSONObject.put(DeviceInfo.TAG_TIMESTAMPS, this.f12253c);
            if (this.f12255e.getUserType() == 0 && C2418k.m14006E(this.f12261k) == 1) {
                jSONObject.put("ia", 1);
            }
            return m13935a(jSONObject);
        } catch (Throwable th) {
            return false;
        }
    }

    public Context m13938c() {
        return this.f12261k;
    }

    public String m13939d() {
        try {
            JSONObject jSONObject = new JSONObject();
            m13937b(jSONObject);
            return jSONObject.toString();
        } catch (Throwable th) {
            return C2915a.f14760f;
        }
    }
}
