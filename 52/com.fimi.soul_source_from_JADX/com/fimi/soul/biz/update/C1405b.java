package com.fimi.soul.biz.update;

import android.util.Log;
import com.fimi.kernel.p076b.C1153f;
import com.fimi.soul.entity.ServerResult;
import com.fimi.soul.entity.UpdateVersonBean;

/* renamed from: com.fimi.soul.biz.update.b */
class C1405b implements C1153f<String> {
    final /* synthetic */ aj f6348a;
    final /* synthetic */ C1404a f6349b;

    C1405b(C1404a c1404a, aj ajVar) {
        this.f6349b = c1404a;
        this.f6348a = ajVar;
    }

    public void m9442a(String str) {
        Log.d("Good", str);
        if (str != null && str.length() > 0) {
            try {
                ServerResult serverResult = (ServerResult) this.f6349b.m9388f().fromJson(str, new C1406c(this).getType());
                if (serverResult != null) {
                    this.f6349b.m9385b((UpdateVersonBean) serverResult.getData());
                    this.f6349b.m9386c(this.f6349b.m9390h());
                    this.f6348a.m9421a(serverResult.getData());
                    return;
                }
            } catch (Exception e) {
            }
            this.f6348a.m9421a(this.f6349b.m9391i());
        }
    }

    public void m9444b(String str) {
        this.f6348a.m9421a(this.f6349b.m9391i());
    }
}
