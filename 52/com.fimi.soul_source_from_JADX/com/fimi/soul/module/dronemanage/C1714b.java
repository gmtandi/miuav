package com.fimi.soul.module.dronemanage;

import android.os.Handler;
import android.os.Message;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.module.dronemanage.b */
class C1714b extends Handler {
    final /* synthetic */ C1713a f8424a;

    C1714b(C1713a c1713a) {
        this.f8424a = c1713a;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (this.f8424a.f8385a != null) {
                    this.f8424a.f8385a.m10134e();
                    this.f8424a.f8385a.m10135f();
                }
            default:
        }
    }
}
