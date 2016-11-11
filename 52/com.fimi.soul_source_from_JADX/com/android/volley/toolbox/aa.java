package com.android.volley.toolbox;

import com.android.volley.C0566n;
import com.android.volley.C0568p;
import com.android.volley.C0604z;
import com.android.volley.ab;
import org.json.JSONArray;
import org.json.JSONObject;

public class aa extends ac<JSONArray> {
    public aa(int i, String str, ab<JSONArray> abVar, com.android.volley.aa aaVar) {
        super(i, str, null, abVar, aaVar);
    }

    public aa(int i, String str, String str2, ab<JSONArray> abVar, com.android.volley.aa aaVar) {
        super(i, str, str2, abVar, aaVar);
    }

    public aa(int i, String str, JSONArray jSONArray, ab<JSONArray> abVar, com.android.volley.aa aaVar) {
        super(i, str, jSONArray == null ? null : jSONArray.toString(), abVar, aaVar);
    }

    public aa(int i, String str, JSONObject jSONObject, ab<JSONArray> abVar, com.android.volley.aa aaVar) {
        super(i, str, jSONObject == null ? null : jSONObject.toString(), abVar, aaVar);
    }

    public aa(String str, ab<JSONArray> abVar, com.android.volley.aa aaVar) {
        super(0, str, null, abVar, aaVar);
    }

    public aa(String str, JSONArray jSONArray, ab<JSONArray> abVar, com.android.volley.aa aaVar) {
        this(jSONArray == null ? 0 : 1, str, jSONArray, (ab) abVar, aaVar);
    }

    public aa(String str, JSONObject jSONObject, ab<JSONArray> abVar, com.android.volley.aa aaVar) {
        this(jSONObject == null ? 0 : 1, str, jSONObject, (ab) abVar, aaVar);
    }

    protected C0604z<JSONArray> m5153a(C0566n c0566n) {
        try {
            return C0604z.m5293a(new JSONArray(new String(c0566n.f3542b, C0587m.m5228a(c0566n.f3543c, "utf-8"))), C0587m.m5226a(c0566n));
        } catch (Throwable e) {
            return C0604z.m5292a(new C0568p(e));
        } catch (Throwable e2) {
            return C0604z.m5292a(new C0568p(e2));
        }
    }
}
