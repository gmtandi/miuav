package com.fimi.soul.module.setting.newhand;

import android.os.Handler;
import android.os.Message;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.module.setting.newhand.q */
class C1875q extends Handler {
    final /* synthetic */ C1874p f9592a;

    C1875q(C1874p c1874p) {
        this.f9592a = c1874p;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (1 == message.what) {
            this.f9592a.m11874i();
        }
        switch (message.what) {
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f9592a.m11866b((byte) 0);
            case Type.BYTE /*3*/:
                this.f9592a.m11866b((byte) 1);
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                this.f9592a.m11877a(2, 1);
            case Type.INT /*5*/:
                this.f9592a.m11877a(2, 0);
            case Type.FLOAT /*6*/:
                this.f9592a.m11877a(4, 1);
            case Type.LONG /*7*/:
                this.f9592a.m11877a(4, 0);
            default:
        }
    }
}
