package com.xiaomi.mistatistic.sdk.controller.p139a;

import android.database.Cursor;
import android.text.TextUtils;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.tencent.open.SocialConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mistatistic.sdk.controller.C2582e;
import com.xiaomi.mistatistic.sdk.controller.C2595i;
import com.xiaomi.mistatistic.sdk.controller.C2601o;
import com.xiaomi.mistatistic.sdk.data.C2621h;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.a.b */
public class C2584b implements C2582e {
    private long f12927a;
    private HashMap f12928b;
    private long f12929c;
    private long f12930d;
    private JSONArray f12931e;
    private JSONObject f12932f;
    private ArrayList f12933g;
    private C2585c f12934h;
    private HashMap f12935i;

    public C2584b(long j, C2585c c2585c) {
        this.f12928b = new HashMap();
        this.f12929c = System.currentTimeMillis();
        this.f12930d = 0;
        this.f12931e = new JSONArray();
        this.f12932f = null;
        this.f12933g = new ArrayList();
        this.f12935i = new HashMap();
        this.f12927a = j;
        this.f12934h = c2585c;
    }

    private void m14700a(C2621h c2621h) {
        JSONObject jSONObject = (JSONObject) this.f12928b.get("mistat_session");
        if (jSONObject == null) {
            JSONArray jSONArray = new JSONArray();
            jSONObject = new JSONObject();
            jSONObject.put("category", "mistat_session");
            jSONObject.put("values", jSONArray);
            this.f12928b.put("mistat_session", jSONObject);
            this.f12932f.getJSONArray(RMsgInfo.COL_CONTENT).put(jSONObject);
        }
        JSONObject jSONObject2 = new JSONObject();
        String[] split = c2621h.f13006e.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
        long parseLong = Long.parseLong(split[0]);
        long parseLong2 = Long.parseLong(split[1]);
        jSONObject2.put("start", parseLong);
        jSONObject2.put("end", parseLong2);
        jSONObject2.put("env", c2621h.f13007f);
        jSONObject.getJSONArray("values").put(jSONObject2);
    }

    private void m14701b(C2621h c2621h) {
        JSONObject jSONObject = (JSONObject) this.f12928b.get("mistat_pv");
        if (jSONObject == null) {
            jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONObject.put("category", "mistat_pv");
            jSONObject.put("values", jSONArray);
            this.f12928b.put("mistat_pv", jSONObject);
            this.f12932f.getJSONArray(RMsgInfo.COL_CONTENT).put(jSONObject);
        }
        String[] split = c2621h.f13006e.trim().split(MiPushClient.ACCEPT_TIME_SEPARATOR);
        String[] strArr = new String[split.length];
        for (int i = 0; i < split.length; i++) {
            int indexOf = this.f12933g.indexOf(split[i]);
            if (indexOf >= 0) {
                strArr[i] = String.valueOf(indexOf + 1);
            } else {
                strArr[i] = String.valueOf(this.f12933g.size() + 1);
                this.f12933g.add(split[i]);
            }
        }
        jSONObject.getJSONArray("values").put(TextUtils.join(MiPushClient.ACCEPT_TIME_SEPARATOR, strArr));
        jSONObject.put("index", TextUtils.join(MiPushClient.ACCEPT_TIME_SEPARATOR, this.f12933g));
    }

    private void m14702c(C2621h c2621h) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = (JSONObject) this.f12928b.get(c2621h.f13002a);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONObject2.put("category", c2621h.f13002a);
            jSONObject2.put("values", jSONArray);
            this.f12928b.put(c2621h.f13002a, jSONObject2);
            this.f12932f.getJSONArray(RMsgInfo.COL_CONTENT).put(jSONObject2);
            jSONObject = jSONObject2;
        } else {
            jSONObject = jSONObject2;
        }
        if ("event".equals(c2621h.f13005d) && TextUtils.isEmpty(c2621h.f13007f)) {
            jSONObject2 = (JSONObject) this.f12935i.get(c2621h.f13004c);
            if (jSONObject2 != null) {
                jSONObject2.put(SharedPref.VALUE, jSONObject2.getLong(SharedPref.VALUE) + Long.parseLong(c2621h.f13006e));
                return;
            }
            jSONObject2 = new JSONObject();
            jSONObject2.put(SharedPref.KEY, c2621h.f13004c);
            jSONObject2.put(SocialConstants.PARAM_TYPE, c2621h.f13005d);
            jSONObject2.put(SharedPref.VALUE, Long.parseLong(c2621h.f13006e));
            jSONObject.getJSONArray("values").put(jSONObject2);
            this.f12935i.put(c2621h.f13004c, jSONObject2);
            return;
        }
        jSONObject2 = new JSONObject();
        jSONObject2.put(SharedPref.KEY, c2621h.f13004c);
        jSONObject2.put(SocialConstants.PARAM_TYPE, c2621h.f13005d);
        if ("count".equals(c2621h.f13005d) || "numeric".equals(c2621h.f13005d)) {
            jSONObject2.put(SharedPref.VALUE, Long.parseLong(c2621h.f13006e));
        } else {
            jSONObject2.put(SharedPref.VALUE, c2621h.f13006e);
        }
        if (!TextUtils.isEmpty(c2621h.f13007f)) {
            jSONObject2.put("params", new JSONObject(c2621h.f13007f));
        }
        jSONObject.getJSONArray("values").put(jSONObject2);
    }

    public void m14703a() {
        try {
            JSONArray b = m14704b();
            if (b == null) {
                this.f12934h.m14705a(C2915a.f14760f, this.f12930d);
            } else {
                this.f12934h.m14705a(b.toString(), this.f12930d);
            }
        } catch (JSONException e) {
            this.f12934h.m14705a(C2915a.f14760f, this.f12930d);
        }
    }

    public JSONArray m14704b() {
        C2601o c2601o = new C2601o();
        C2595i c2595i = new C2595i();
        c2595i.m14751c();
        Cursor b = c2595i.m14750b();
        c2601o.m14769a("Begin to packing data from local DB");
        int i = 0;
        if (b.moveToFirst()) {
            do {
                i++;
                C2621h a = C2595i.m14744a(b);
                c2601o.m14769a("Packing " + a.toString());
                if (this.f12930d == 0) {
                    this.f12930d = a.f13003b;
                    this.f12929c = this.f12930d;
                }
                if (this.f12927a > 0 && this.f12929c - a.f13003b > this.f12927a && this.f12932f != null) {
                    this.f12932f = null;
                    this.f12928b.clear();
                    this.f12933g.clear();
                    this.f12929c = a.f13003b;
                    this.f12935i.clear();
                }
                if (this.f12932f == null) {
                    this.f12932f = new JSONObject();
                    this.f12932f.put("endTS", a.f13003b);
                    this.f12932f.put(RMsgInfo.COL_CONTENT, new JSONArray());
                    this.f12931e.put(this.f12932f);
                }
                if ("mistat_session".equals(a.f13002a)) {
                    m14700a(a);
                } else if ("mistat_pv".equals(a.f13002a)) {
                    m14701b(a);
                } else {
                    try {
                        m14702c(a);
                    } catch (Throwable th) {
                        if (b != null) {
                            b.close();
                        }
                    }
                }
                this.f12932f.put("startTS", a.f13003b);
            } while (b.moveToNext());
            c2601o.m14769a("Packing complete, total " + i + " records were packed and to be uploaded");
        } else {
            c2601o.m14769a("No data available to be packed");
            this.f12931e = null;
        }
        if (b != null) {
            b.close();
        }
        return this.f12931e;
    }
}
