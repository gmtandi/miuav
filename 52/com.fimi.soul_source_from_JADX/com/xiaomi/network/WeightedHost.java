package com.xiaomi.network;

import com.xiaomi.market.sdk.C2537j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeightedHost implements Comparable<WeightedHost> {
    public String f13042a;
    protected int f13043b;
    private final LinkedList<AccessHistory> f13044c;
    private long f13045d;

    public WeightedHost() {
        this(null, 0);
    }

    public WeightedHost(String str) {
        this(str, 0);
    }

    public WeightedHost(String str, int i) {
        this.f13044c = new LinkedList();
        this.f13045d = 0;
        this.f13042a = str;
        this.f13043b = i;
    }

    public int m14878a(WeightedHost weightedHost) {
        return weightedHost == null ? 1 : weightedHost.f13043b - this.f13043b;
    }

    public synchronized WeightedHost m14879a(JSONObject jSONObject) {
        this.f13045d = jSONObject.getLong("tt");
        this.f13043b = jSONObject.getInt("wt");
        this.f13042a = jSONObject.getString(C2537j.HOST);
        JSONArray jSONArray = jSONObject.getJSONArray("ah");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.f13044c.add(new AccessHistory().m14822a(jSONArray.getJSONObject(i)));
        }
        return this;
    }

    public synchronized ArrayList<AccessHistory> m14880a() {
        ArrayList<AccessHistory> arrayList;
        arrayList = new ArrayList();
        Iterator it = this.f13044c.iterator();
        while (it.hasNext()) {
            AccessHistory accessHistory = (AccessHistory) it.next();
            if (accessHistory.m14824c() > this.f13045d) {
                arrayList.add(accessHistory);
            }
        }
        this.f13045d = System.currentTimeMillis();
        return arrayList;
    }

    protected synchronized void m14881a(AccessHistory accessHistory) {
        if (accessHistory != null) {
            UploadHostStatHelper.m14867a().m14877b();
            this.f13044c.add(accessHistory);
            int a = accessHistory.m14821a();
            if (a > 0) {
                this.f13043b += accessHistory.m14821a();
            } else {
                int i = 0;
                int size = this.f13044c.size() - 1;
                while (size >= 0 && ((AccessHistory) this.f13044c.get(size)).m14821a() < 0) {
                    i++;
                    size--;
                }
                this.f13043b += a * i;
            }
            if (this.f13044c.size() > 30) {
                this.f13043b -= ((AccessHistory) this.f13044c.remove()).m14821a();
            }
        }
    }

    public synchronized JSONObject m14882b() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put("tt", this.f13045d);
        jSONObject.put("wt", this.f13043b);
        jSONObject.put(C2537j.HOST, this.f13042a);
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.f13044c.iterator();
        while (it.hasNext()) {
            jSONArray.put(((AccessHistory) it.next()).m14827f());
        }
        jSONObject.put("ah", jSONArray);
        return jSONObject;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m14878a((WeightedHost) obj);
    }

    public String toString() {
        return this.f13042a + ":" + this.f13043b;
    }
}
