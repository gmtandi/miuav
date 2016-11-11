package com.amap.api.mapcore;

import android.os.Handler;
import android.os.Message;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

class bq extends Handler {
    final /* synthetic */ bp f1895a;

    bq(bp bpVar) {
        this.f1895a = bpVar;
    }

    public void handleMessage(Message message) {
        if (message != null) {
            switch (message.what) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    this.f1895a.f1883b.m2277a(this.f1895a.f1889h);
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    this.f1895a.f1883b.m2296e(this.f1895a.f1891j);
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    this.f1895a.f1883b.m2293d(this.f1895a.f1890i);
                case Type.BYTE /*3*/:
                    this.f1895a.f1883b.m2290c(this.f1895a.f1887f);
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    this.f1895a.f1883b.m2287b(this.f1895a.f1894m);
                default:
            }
        }
    }
}
