package com.fimi.soul.module.setting;

import android.content.Context;
import android.text.TextUtils;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p082c.C1156a;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p103k.ba;
import com.fimi.soul.entity.User;
import com.fimi.soul.module.update.p121a.C1901a;

/* renamed from: com.fimi.soul.module.setting.v */
class C1892v implements Runnable {
    final /* synthetic */ FlyRecordActivity f9619a;

    C1892v(FlyRecordActivity flyRecordActivity) {
        this.f9619a = flyRecordActivity;
    }

    public void run() {
        Context a = C1189f.m8327a();
        if (a != null) {
            ba a2 = ba.m9259a(a);
            String i = C1901a.m12002a().m12003a(0).m12059i();
            User b = C1236a.m8533b(a);
            if (b != null || !TextUtils.isEmpty(b.getUserID())) {
                C1156a c = C1189f.m8333c();
                a2.m9268a(i, c.m7936c(C1236a.f5597U) / 1000, c.m7936c(C1236a.f5599W), b.getUserID(), new C1893w(this, c));
            }
        }
    }
}
