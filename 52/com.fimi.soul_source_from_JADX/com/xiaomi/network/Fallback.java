package com.xiaomi.network;

import android.text.TextUtils;
import com.amap.api.services.district.DistrictSearchQuery;
import com.tencent.stat.DeviceInfo;
import com.xiaomi.market.sdk.C2537j;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class Fallback {
    public String f13016a;
    public String f13017b;
    public String f13018c;
    public String f13019d;
    public String f13020e;
    public String f13021f;
    public String f13022g;
    protected String f13023h;
    private long f13024i;
    private ArrayList<WeightedHost> f13025j;
    private String f13026k;
    private double f13027l;
    private String f13028m;
    private long f13029n;

    public Fallback(String str) {
        this.f13025j = new ArrayList();
        this.f13027l = 0.1d;
        this.f13028m = "s.mi1.cc";
        this.f13029n = MiStatInterface.MAX_UPLOAD_INTERVAL;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        this.f13024i = System.currentTimeMillis();
        this.f13025j.add(new WeightedHost(str, -1));
        this.f13016a = HostManager.getInstance().getActiveNetworkLabel();
        this.f13017b = str;
    }

    private synchronized void m14830d(String str) {
        Iterator it = this.f13025j.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(((WeightedHost) it.next()).f13042a, str)) {
                it.remove();
            }
        }
    }

    public synchronized Fallback m14831a(JSONObject jSONObject) {
        this.f13016a = jSONObject.optString("net");
        this.f13029n = jSONObject.getLong("ttl");
        this.f13027l = jSONObject.getDouble("pct");
        this.f13024i = jSONObject.getLong(DeviceInfo.TAG_TIMESTAMPS);
        this.f13019d = jSONObject.optString(DistrictSearchQuery.KEYWORDS_CITY);
        this.f13018c = jSONObject.optString("prv");
        this.f13022g = jSONObject.optString("cty");
        this.f13020e = jSONObject.optString("isp");
        this.f13021f = jSONObject.optString("ip");
        this.f13017b = jSONObject.optString(C2537j.HOST);
        this.f13023h = jSONObject.optString("xf");
        JSONArray jSONArray = jSONObject.getJSONArray("fbs");
        for (int i = 0; i < jSONArray.length(); i++) {
            m14835a(new WeightedHost().m14879a(jSONArray.getJSONObject(i)));
        }
        return this;
    }

    public ArrayList<String> m14832a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the url is empty.");
        }
        URL url = new URL(str);
        if (TextUtils.equals(url.getHost(), this.f13017b)) {
            ArrayList<String> arrayList = new ArrayList();
            Iterator it = m14845c().iterator();
            while (it.hasNext()) {
                arrayList.add(new URL(url.getProtocol(), (String) it.next(), url.getPort(), url.getFile()).toString());
            }
            return arrayList;
        }
        throw new IllegalArgumentException("the url is not supported by the fallback");
    }

    public void m14833a(double d) {
        this.f13027l = d;
    }

    public void m14834a(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("the duration is invalid " + j);
        }
        this.f13029n = j;
    }

    public synchronized void m14835a(WeightedHost weightedHost) {
        m14830d(weightedHost.f13042a);
        this.f13025j.add(weightedHost);
    }

    public void m14836a(String str, int i, long j, long j2, Exception exception) {
        m14839a(str, new AccessHistory(i, j, j2, exception));
    }

    public void m14837a(String str, long j, long j2) {
        m14836a(str, 0, j, j2, null);
    }

    public void m14838a(String str, long j, long j2, Exception exception) {
        m14836a(str, -1, j, j2, exception);
    }

    public synchronized void m14839a(String str, AccessHistory accessHistory) {
        Iterator it = this.f13025j.iterator();
        while (it.hasNext()) {
            WeightedHost weightedHost = (WeightedHost) it.next();
            if (TextUtils.equals(str, weightedHost.f13042a)) {
                weightedHost.m14881a(accessHistory);
                break;
            }
        }
    }

    public synchronized void m14840a(String[] strArr) {
        for (int size = this.f13025j.size() - 1; size >= 0; size--) {
            for (CharSequence equals : strArr) {
                if (TextUtils.equals(((WeightedHost) this.f13025j.get(size)).f13042a, equals)) {
                    this.f13025j.remove(size);
                    break;
                }
            }
        }
        Iterator it = this.f13025j.iterator();
        int i = 0;
        while (it.hasNext()) {
            WeightedHost weightedHost = (WeightedHost) it.next();
            i = weightedHost.f13043b > i ? weightedHost.f13043b : i;
        }
        for (int i2 = 0; i2 < strArr.length; i2++) {
            m14835a(new WeightedHost(strArr[i2], (strArr.length + i) - i2));
        }
    }

    public boolean m14841a() {
        return TextUtils.equals(this.f13016a, HostManager.getInstance().getActiveNetworkLabel());
    }

    public boolean m14842a(Fallback fallback) {
        return TextUtils.equals(this.f13016a, fallback.f13016a);
    }

    public synchronized void m14843b(String str) {
        m14835a(new WeightedHost(str));
    }

    public boolean m14844b() {
        return System.currentTimeMillis() - this.f13024i < this.f13029n;
    }

    public synchronized ArrayList<String> m14845c() {
        ArrayList<String> arrayList;
        WeightedHost[] weightedHostArr = new WeightedHost[this.f13025j.size()];
        this.f13025j.toArray(weightedHostArr);
        Arrays.sort(weightedHostArr);
        arrayList = new ArrayList();
        for (WeightedHost weightedHost : weightedHostArr) {
            arrayList.add(weightedHost.f13042a);
        }
        return arrayList;
    }

    public void m14846c(String str) {
        this.f13028m = str;
    }

    public synchronized String m14847d() {
        String str;
        if (!TextUtils.isEmpty(this.f13026k)) {
            str = this.f13026k;
        } else if (TextUtils.isEmpty(this.f13020e)) {
            str = "hardcode_isp";
        } else {
            this.f13026k = HostManager.join(new String[]{this.f13020e, this.f13018c, this.f13019d, this.f13022g, this.f13021f}, "_");
            str = this.f13026k;
        }
        return str;
    }

    public ArrayList<WeightedHost> m14848e() {
        return this.f13025j;
    }

    public double m14849f() {
        return this.f13027l < 1.0E-5d ? 0.1d : this.f13027l;
    }

    public synchronized JSONObject m14850g() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put("net", this.f13016a);
        jSONObject.put("ttl", this.f13029n);
        jSONObject.put("pct", this.f13027l);
        jSONObject.put(DeviceInfo.TAG_TIMESTAMPS, this.f13024i);
        jSONObject.put(DistrictSearchQuery.KEYWORDS_CITY, this.f13019d);
        jSONObject.put("prv", this.f13018c);
        jSONObject.put("cty", this.f13022g);
        jSONObject.put("isp", this.f13020e);
        jSONObject.put("ip", this.f13021f);
        jSONObject.put(C2537j.HOST, this.f13017b);
        jSONObject.put("xf", this.f13023h);
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.f13025j.iterator();
        while (it.hasNext()) {
            jSONArray.put(((WeightedHost) it.next()).m14882b());
        }
        jSONObject.put("fbs", jSONArray);
        return jSONObject;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f13016a);
        stringBuilder.append("\n");
        stringBuilder.append(m14847d());
        Iterator it = this.f13025j.iterator();
        while (it.hasNext()) {
            WeightedHost weightedHost = (WeightedHost) it.next();
            stringBuilder.append("\n");
            stringBuilder.append(weightedHost.toString());
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
