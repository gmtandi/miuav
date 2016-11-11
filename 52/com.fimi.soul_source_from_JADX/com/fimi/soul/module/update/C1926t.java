package com.fimi.soul.module.update;

import com.fimi.soul.biz.update.ah;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.module.update.t */
class C1926t implements ah {
    final /* synthetic */ UpgradingActivity f9869a;

    C1926t(UpgradingActivity upgradingActivity) {
        this.f9869a = upgradingActivity;
    }

    public void m12092a(boolean z, long j, long j2, int i) {
        if (z) {
            this.f9869a.getHandler().sendEmptyMessageDelayed(Opcodes.FSUB, 50);
        }
    }
}
