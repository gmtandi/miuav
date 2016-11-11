package com.fimi.soul.module.update;

import com.fimi.soul.biz.camera.entity.RelayEntity;
import com.fimi.soul.biz.p096d.C1327m;
import com.fimi.soul.biz.update.ak;
import com.fimi.soul.entity.FirmwareInfo;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.module.update.u */
class C1927u implements C1327m {
    final /* synthetic */ UpgradingActivity f9870a;

    C1927u(UpgradingActivity upgradingActivity) {
        this.f9870a = upgradingActivity;
    }

    public void m12093a(RelayEntity relayEntity) {
        int firmupg_finished = relayEntity.getFirmupg_finished();
        this.f9870a.m11963a(Opcodes.IFEQ, firmupg_finished, 100);
        if (firmupg_finished < 100) {
            this.f9870a.f9768d.m12086b();
            return;
        }
        ak.m9437d(((FirmwareInfo) this.f9870a.f9766b.get(this.f9870a.f9783y - 1)).getVersion());
        this.f9870a.m11963a(Opcodes.ACC_NATIVE, 0, 0);
    }
}
