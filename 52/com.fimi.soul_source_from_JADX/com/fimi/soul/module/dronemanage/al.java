package com.fimi.soul.module.dronemanage;

import android.os.Handler;
import android.os.Message;
import org.codehaus.jackson.smile.SmileConstants;

final class al extends Handler {
    al() {
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (ak.f8418a != null) {
                    ak.f8418a.m10134e();
                    ak.f8418a.m10135f();
                }
            default:
        }
    }
}
