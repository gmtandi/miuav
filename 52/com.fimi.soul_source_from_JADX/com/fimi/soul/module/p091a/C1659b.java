package com.fimi.soul.module.p091a;

import android.os.Handler;
import android.os.Message;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.module.a.b */
class C1659b extends Handler {
    final /* synthetic */ C1658a f7847a;

    C1659b(C1658a c1658a) {
        this.f7847a = c1658a;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (this.f7847a.f7843c != null) {
                    this.f7847a.f7843c.m10134e();
                    this.f7847a.f7843c.m10135f();
                }
                if (this.f7847a.f7846h != null && this.f7847a.f7845e != null) {
                    this.f7847a.f7846h.m10809a(this.f7847a.f7845e.f7000c);
                    this.f7847a.f7845e = null;
                }
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (this.f7847a.f7843c != null) {
                    this.f7847a.f7843c.m10134e();
                }
            default:
        }
    }
}
