package com.fimi.soul.service;

import android.content.Context;
import android.text.TextUtils;
import com.fimi.kernel.C1189f;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p103k.ba;
import com.fimi.soul.entity.User;
import com.fimi.soul.module.update.p121a.C1901a;

/* renamed from: com.fimi.soul.service.s */
class C1952s implements Runnable {
    final /* synthetic */ InitAppService f9993a;

    C1952s(InitAppService initAppService) {
        this.f9993a = initAppService;
    }

    public void run() {
        Context a = C1189f.m8327a();
        if (a != null) {
            ba a2 = ba.m9259a(a);
            String i = C1901a.m12002a().m12003a(0).m12059i();
            User b = C1236a.m8533b(a);
            if (b != null || !TextUtils.isEmpty(b.getUserID())) {
                long j = 0;
                a2.m9268a(i, 0, j, b.getUserID(), new C1953t(this, C1189f.m8333c()));
            }
        }
    }
}
