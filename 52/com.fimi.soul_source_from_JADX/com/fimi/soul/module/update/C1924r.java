package com.fimi.soul.module.update;

import com.fimi.soul.biz.update.ah;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.module.update.r */
class C1924r implements ah {
    final /* synthetic */ UpgradingActivity f9867a;

    C1924r(UpgradingActivity upgradingActivity) {
        this.f9867a = upgradingActivity;
    }

    public void m12089a(boolean z, long j, long j2, int i) {
        if (i == -1) {
            this.f9867a.m11990l();
        } else if (z) {
            this.f9867a.m11963a(Opcodes.ACC_NATIVE, i, 0);
        } else {
            this.f9867a.f9782x = i;
            this.f9867a.m11963a(Opcodes.IFEQ, (int) j, (int) j2);
        }
    }
}
