package com.fimi.soul.view;

import android.os.Handler;
import android.os.Message;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

class ca extends Handler {
    final /* synthetic */ UpDownSliding f10744a;

    ca(UpDownSliding upDownSliding) {
        this.f10744a = upDownSliding;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                this.f10744a.m12718a(ci.TakePhoto, ci.Live);
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f10744a.m12718a(ci.TakePhoto, ci.Record);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f10744a.m12725a(2, ch.DOWN, Boolean.valueOf(true));
            case Type.BYTE /*3*/:
                this.f10744a.m12725a(1, ch.UP, Boolean.valueOf(true));
            default:
        }
    }
}
