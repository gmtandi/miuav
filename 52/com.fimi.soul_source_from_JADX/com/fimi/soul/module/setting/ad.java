package com.fimi.soul.module.setting;

import com.fimi.kernel.C1189f;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.entity.PlaneMsg;

class ad implements C1330i {
    final /* synthetic */ MapSettingFragment f9209a;

    ad(MapSettingFragment mapSettingFragment) {
        this.f9209a = mapSettingFragment;
    }

    public void m11675a(PlaneMsg planeMsg) {
        if (!planeMsg.isSuccess()) {
            return;
        }
        if (Integer.parseInt(planeMsg.getData().toString()) == 1) {
            C1189f.m8335e().m8028b(true);
        } else {
            C1189f.m8335e().m8028b(false);
        }
    }
}
