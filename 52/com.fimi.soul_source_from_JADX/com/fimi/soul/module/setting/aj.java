package com.fimi.soul.module.setting;

import android.os.Handler;
import android.os.Message;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

class aj extends Handler {
    final /* synthetic */ MapSettingFragment f9215a;

    aj(MapSettingFragment mapSettingFragment) {
        this.f9215a = mapSettingFragment;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                this.f9215a.f9141e.m11825a();
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f9215a.f9141e.m11836d();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f9215a.f9141e.m11832b();
            case Type.BYTE /*3*/:
                C1886p.m11892a(this.f9215a.f9152p).m11895a();
            default:
        }
    }
}
