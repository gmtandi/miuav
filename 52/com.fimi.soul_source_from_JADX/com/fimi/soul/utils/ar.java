package com.fimi.soul.utils;

import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.entity.PlaneMsg;
import com.xiaomi.mipush.sdk.MiPushClient;

class ar implements C1330i {
    final /* synthetic */ aq f10046a;

    ar(aq aqVar) {
        this.f10046a = aqVar;
    }

    public void m12283a(PlaneMsg planeMsg) {
        this.f10046a.f10045c.f10035g = planeMsg.getErrorMessage();
        if (planeMsg.isSuccess()) {
            MiPushClient.registerPush(this.f10046a.f10045c.f10033e, C1236a.f5628z, C1236a.f5620r);
            if (this.f10046a.f10045c.f10034f != null) {
                this.f10046a.f10045c.f10034f.m11535a();
            }
        } else if (this.f10046a.f10045c.f10035g != null) {
            if (this.f10046a.f10045c.f10034f != null) {
                this.f10046a.f10045c.f10034f.m11536a(this.f10046a.f10045c.f10035g);
            }
        } else if (this.f10046a.f10045c.f10034f != null) {
            this.f10046a.f10045c.f10034f.m11536a(this.f10046a.f10045c.f10033e.getResources().getString(C1205R.string.login_result_net));
        }
    }
}
