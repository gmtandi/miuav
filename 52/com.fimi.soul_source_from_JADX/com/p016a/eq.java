package com.p016a;

import android.os.Handler;
import android.os.Message;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.a.eq */
final class eq extends Handler {
    private /* synthetic */ ep f1115a;

    eq(ep epVar) {
        this.f1115a = epVar;
    }

    public final void handleMessage(Message message) {
        try {
            switch (message.what) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (this.f1115a.f1114a.f1002A != null) {
                        this.f1115a.f1114a.f1002A.m1748a((String) message.obj);
                    }
                default:
            }
        } catch (Throwable th) {
        }
    }
}
