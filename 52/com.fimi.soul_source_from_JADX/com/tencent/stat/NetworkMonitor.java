package com.tencent.stat;

import com.tencent.mm.sdk.platformtools.LocaleUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

public class NetworkMonitor {
    private long f12186a;
    private int f12187b;
    private String f12188c;
    private int f12189d;
    private String f12190e;

    public NetworkMonitor() {
        this.f12186a = 0;
        this.f12187b = 0;
        this.f12188c = C2915a.f14760f;
        this.f12189d = 0;
        this.f12190e = C2915a.f14760f;
    }

    public String getDomain() {
        return this.f12188c;
    }

    public long getMillisecondsConsume() {
        return this.f12186a;
    }

    public int getPort() {
        return this.f12189d;
    }

    public String getRemoteIp() {
        return this.f12190e;
    }

    public int getStatusCode() {
        return this.f12187b;
    }

    public void setDomain(String str) {
        this.f12188c = str;
    }

    public void setMillisecondsConsume(long j) {
        this.f12186a = j;
    }

    public void setPort(int i) {
        this.f12189d = i;
    }

    public void setRemoteIp(String str) {
        this.f12190e = str;
    }

    public void setStatusCode(int i) {
        this.f12187b = i;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("tm", this.f12186a);
            jSONObject.put("st", this.f12187b);
            if (this.f12188c != null) {
                jSONObject.put("dm", this.f12188c);
            }
            jSONObject.put(LocaleUtil.PORTUGUESE, this.f12189d);
            if (this.f12190e != null) {
                jSONObject.put("rip", this.f12190e);
            }
            jSONObject.put(DeviceInfo.TAG_TIMESTAMPS, System.currentTimeMillis() / 1000);
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}
