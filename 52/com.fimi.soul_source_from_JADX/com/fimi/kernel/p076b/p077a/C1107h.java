package com.fimi.kernel.p076b.p077a;

import android.os.Message;

/* renamed from: com.fimi.kernel.b.a.h */
class C1107h extends C1097a {
    final /* synthetic */ C1106g f5113a;

    C1107h(C1106g c1106g) {
        this.f5113a = c1106g;
    }

    public void m7720a(C1098b c1098b, int i) {
        Message message = new Message();
        message.what = C1108i.UploadFile.ordinal();
        message.arg1 = c1098b.ordinal();
        message.arg2 = i;
        this.f5113a.f5112a.m7685a().sendMessage(message);
    }
}
