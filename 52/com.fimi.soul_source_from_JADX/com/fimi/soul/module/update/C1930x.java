package com.fimi.soul.module.update;

import com.fimi.soul.biz.update.ah;
import com.fimi.soul.biz.update.ak;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.module.update.x */
class C1930x implements ah {
    final /* synthetic */ UpgradingActivity f9873a;

    C1930x(UpgradingActivity upgradingActivity) {
        this.f9873a = upgradingActivity;
    }

    public void m12095a(boolean z, long j, long j2, int i) {
        ak.m9434c("uplaod file progress:" + ((100 * j) / j2) + "%");
        if (!this.f9873a.f9784z.m8874s().m8785g()) {
            this.f9873a.m11963a(Opcodes.IFEQ, ((int) j) / 2, (int) j2);
        }
    }
}
