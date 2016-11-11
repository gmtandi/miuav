package com.fimi.soul.biz.update;

import com.fimi.kernel.C1189f;
import com.fimi.soul.biz.p103k.ay;
import com.fimi.soul.entity.UpgradeResultInfo;

/* renamed from: com.fimi.soul.biz.update.y */
final class C1427y implements Runnable {
    final /* synthetic */ UpgradeResultInfo f6419a;

    C1427y(UpgradeResultInfo upgradeResultInfo) {
        this.f6419a = upgradeResultInfo;
    }

    public void run() {
        ay.m9251a(C1189f.m8327a()).m9255a(new C1428z(this), this.f6419a);
    }
}
