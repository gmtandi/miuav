package com.fimi.soul.biz.update;

import android.util.Log;
import com.fimi.kernel.p076b.C1153f;
import com.fimi.soul.entity.ServerResult;
import com.fimi.soul.entity.UpdateVersonBean;

/* renamed from: com.fimi.soul.biz.update.s */
class C1421s implements C1153f<String> {
    final /* synthetic */ aj f6375a;
    final /* synthetic */ C1420r f6376b;

    C1421s(C1420r c1420r, aj ajVar) {
        this.f6376b = c1420r;
        this.f6375a = ajVar;
    }

    public void m9476a(String str) {
        Log.d("Good", str);
        if (str != null && str.length() > 0) {
            try {
                ServerResult serverResult = (ServerResult) this.f6376b.m9388f().fromJson(str, new C1422t(this).getType());
                if (serverResult != null) {
                    this.f6376b.m9385b((UpdateVersonBean) serverResult.getData());
                    this.f6376b.m9386c(this.f6376b.m9390h());
                    this.f6375a.m9421a(serverResult.getData());
                    return;
                }
            } catch (Exception e) {
            }
            this.f6375a.m9421a(this.f6376b.m9391i());
        }
    }

    public void m9478b(String str) {
        Log.d("Good", str);
        this.f6375a.m9421a(this.f6376b.m9391i());
    }
}
