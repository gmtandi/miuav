package com.fimi.soul.service;

import com.fimi.soul.biz.update.C1403p;
import com.fimi.soul.biz.update.C1423u;
import com.fimi.soul.biz.update.ad;
import com.fimi.soul.entity.UpdateVersonBean;
import com.fimi.soul.module.update.p121a.C1901a;
import java.util.List;

/* renamed from: com.fimi.soul.service.v */
class C1955v implements ad {
    final /* synthetic */ C1423u f9997a;
    final /* synthetic */ InitAppService f9998b;

    C1955v(InitAppService initAppService, C1423u c1423u) {
        this.f9998b = initAppService;
        this.f9997a = c1423u;
    }

    public void m12193a(List<UpdateVersonBean> list) {
        this.f9998b.m12152a((List) list);
        if (list != null && list.size() >= 1) {
            C1403p.f6310c = false;
            C1403p.f6309b = false;
            List<UpdateVersonBean> e = C1901a.m12002a().m12010e();
            if (e != null && e.size() > 0) {
                for (UpdateVersonBean a : e) {
                    this.f9998b.m12141a(this.f9997a, a);
                }
            }
        }
    }
}
