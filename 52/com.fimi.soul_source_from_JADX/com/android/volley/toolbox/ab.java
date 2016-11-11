package com.android.volley.toolbox;

import com.android.volley.C0566n;
import com.android.volley.C0568p;
import com.android.volley.C0604z;
import com.android.volley.aa;
import org.json.JSONObject;

public class ab extends ac<JSONObject> {
    public ab(int i, String str, com.android.volley.ab<JSONObject> abVar, aa aaVar) {
        super(i, str, null, abVar, aaVar);
    }

    public ab(int i, String str, String str2, com.android.volley.ab<JSONObject> abVar, aa aaVar) {
        super(i, str, str2, abVar, aaVar);
    }

    public ab(int i, String str, JSONObject jSONObject, com.android.volley.ab<JSONObject> abVar, aa aaVar) {
        super(i, str, jSONObject == null ? null : jSONObject.toString(), abVar, aaVar);
    }

    public ab(String str, com.android.volley.ab<JSONObject> abVar, aa aaVar) {
        super(0, str, null, abVar, aaVar);
    }

    public ab(String str, JSONObject jSONObject, com.android.volley.ab<JSONObject> abVar, aa aaVar) {
        this(jSONObject == null ? 0 : 1, str, jSONObject, (com.android.volley.ab) abVar, aaVar);
    }

    protected C0604z<JSONObject> m5154a(C0566n c0566n) {
        try {
            return C0604z.m5293a(new JSONObject(new String(c0566n.f3542b, C0587m.m5228a(c0566n.f3543c, "utf-8"))), C0587m.m5226a(c0566n));
        } catch (Throwable e) {
            return C0604z.m5292a(new C0568p(e));
        } catch (Throwable e2) {
            return C0604z.m5292a(new C0568p(e2));
        }
    }
}
