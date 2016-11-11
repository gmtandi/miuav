package com.fimi.soul.drone;

import android.content.Context;
import android.text.TextUtils;
import com.fimi.kernel.C1189f;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p103k.ba;
import com.fimi.soul.entity.User;
import com.fimi.soul.module.update.p121a.C1901a;
import com.fimi.soul.utils.C1982v;
import com.fimi.soul.utils.C1984x;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.fimi.soul.drone.b */
class C1436b implements Runnable {
    final /* synthetic */ C1433a f6532a;

    C1436b(C1433a c1433a) {
        this.f6532a = c1433a;
    }

    public void run() {
        Context a = C1189f.m8327a();
        if (a != null) {
            ba a2 = ba.m9259a(a);
            this.f6532a.f6508d = C1901a.m12002a().m12003a(0).m12059i();
            User b = C1236a.m8533b(a);
            if (b != null || !TextUtils.isEmpty(b.getUserID())) {
                this.f6532a.f6509e = b.getUserID();
                this.f6532a.f6510f = C1982v.m12518a(a);
                List<C1984x> a3 = this.f6532a.f6510f.m12520a(this.f6532a.f6508d, b.getUserID());
                if (a3 != null && a3.size() > 0) {
                    JSONArray jSONArray = new JSONArray();
                    try {
                        for (C1984x c1984x : a3) {
                            a2.m9268a(this.f6532a.f6508d, c1984x.m12529c() / 1000, (long) c1984x.m12530d(), b.getUserID(), this.f6532a.f6511g);
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("planeID", this.f6532a.f6508d);
                            jSONObject.put("flyTime", c1984x.m12529c());
                            jSONObject.put("flyJourney", c1984x.m12530d());
                            jSONObject.put("userID", this.f6532a.f6509e);
                            jSONArray.put(jSONObject);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    a2.m9273b(jSONArray.toString(), this.f6532a.f6511g);
                }
            }
        }
    }
}
