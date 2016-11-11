package com.xiaomi.network;

import com.tencent.stat.DeviceInfo;
import org.json.JSONObject;

public class AccessHistory {
    private int f13008a;
    private long f13009b;
    private long f13010c;
    private String f13011d;
    private long f13012e;

    public AccessHistory() {
        this(0, 0, 0, null);
    }

    public AccessHistory(int i, long j, long j2, Exception exception) {
        this.f13008a = i;
        this.f13009b = j;
        this.f13012e = j2;
        this.f13010c = System.currentTimeMillis();
        if (exception != null) {
            this.f13011d = exception.getClass().getSimpleName();
        }
    }

    public int m14821a() {
        return this.f13008a;
    }

    public AccessHistory m14822a(JSONObject jSONObject) {
        this.f13009b = jSONObject.getLong("cost");
        this.f13012e = jSONObject.getLong("size");
        this.f13010c = jSONObject.getLong(DeviceInfo.TAG_TIMESTAMPS);
        this.f13008a = jSONObject.getInt("wt");
        this.f13011d = jSONObject.optString("expt");
        return this;
    }

    public long m14823b() {
        return this.f13009b;
    }

    public long m14824c() {
        return this.f13010c;
    }

    public long m14825d() {
        return this.f13012e;
    }

    public String m14826e() {
        return this.f13011d;
    }

    public JSONObject m14827f() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cost", this.f13009b);
        jSONObject.put("size", this.f13012e);
        jSONObject.put(DeviceInfo.TAG_TIMESTAMPS, this.f13010c);
        jSONObject.put("wt", this.f13008a);
        jSONObject.put("expt", this.f13011d);
        return jSONObject;
    }
}
