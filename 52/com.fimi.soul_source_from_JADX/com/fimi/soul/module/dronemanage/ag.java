package com.fimi.soul.module.dronemanage;

import android.os.Handler;
import android.os.Message;
import org.codehaus.jackson.smile.SmileConstants;

class ag extends Handler {
    final /* synthetic */ af f8407a;

    ag(af afVar) {
        this.f8407a = afVar;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (af.f8397a != null) {
                    af.f8397a.m10134e();
                    af.f8397a.m10135f();
                }
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (af.f8397a != null) {
                    af.f8397a.m10134e();
                    af.f8397a.m10135f();
                }
                if (af.f8403g != null) {
                    af.f8403g.m11197a(1);
                    af.f8403g = null;
                }
            default:
        }
    }
}
