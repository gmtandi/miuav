package com.fimi.soul.module.update;

import com.fimi.soul.biz.update.ah;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.module.update.y */
class C1931y implements ah {
    final /* synthetic */ UpgradingActivity f9874a;

    C1931y(UpgradingActivity upgradingActivity) {
        this.f9874a = upgradingActivity;
    }

    public void m12096a(boolean z, long j, long j2, int i) {
        if (z) {
            this.f9874a.getHandler().sendEmptyMessageDelayed(Opcodes.FSUB, 50);
        }
    }
}
