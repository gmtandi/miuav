package com.fimi.kernel.p084e;

import android.os.Handler;
import android.os.Message;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.kernel.e.am */
final class am extends Handler {
    am() {
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                ak.m8085a(ak.f5304d, message.getData().getString("TEXT"), (int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
            default:
        }
    }
}
