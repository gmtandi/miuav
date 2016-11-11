package com.fimi.soul.module.calibcompass;

import android.os.Handler;
import android.os.Message;
import com.fimi.soul.C1205R;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.module.calibcompass.g */
class C1674g extends Handler {
    final /* synthetic */ CaliCompassActivity f7932a;

    C1674g(CaliCompassActivity caliCompassActivity) {
        this.f7932a = caliCompassActivity;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case Opcodes.FCONST_1 /*12*/:
                if (this.f7932a.f7876a != null) {
                    this.f7932a.f7891p = true;
                    this.f7932a.f7876a.m10847a(true);
                }
            case Opcodes.FCONST_2 /*13*/:
                if (!this.f7932a.drone.m9570Q() && this.f7932a.drone.m9569P().m9995a()) {
                    this.f7932a.m10857a(C1205R.string.GC_caliFail_discon_drone, XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, false, false);
                } else if (!this.f7932a.drone.m9569P().m9995a()) {
                    this.f7932a.m10857a(C1205R.string.GC_caliFail_discon, XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, false, false);
                }
            default:
        }
    }
}
