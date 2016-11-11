package com.fimi.kernel.p076b.p077a;

import android.os.Message;

/* renamed from: com.fimi.kernel.b.a.f */
class C1105f extends C1097a {
    final /* synthetic */ C1104e f5111a;

    C1105f(C1104e c1104e) {
        this.f5111a = c1104e;
    }

    public void m7719a(C1098b c1098b, int i) {
        Message message = new Message();
        message.what = C1108i.DownloadFile.ordinal();
        message.arg1 = c1098b.ordinal();
        message.arg2 = i;
        this.f5111a.f5110a.m7685a().sendMessage(message);
    }
}
