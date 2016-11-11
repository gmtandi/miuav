package com.fimi.soul.biz.p103k;

import android.os.Message;

/* renamed from: com.fimi.soul.biz.k.aa */
class aa implements Runnable {
    String f6015a;
    String f6016b;
    final /* synthetic */ C1401z f6017c;
    private int f6018d;

    public aa(C1401z c1401z, int i, String str, String str2) {
        this.f6017c = c1401z;
        this.f6018d = i;
        this.f6016b = str2;
        this.f6015a = str;
    }

    public void run() {
        Message obtainMessage = this.f6017c.f6306h.obtainMessage();
        Object obj = null;
        if (this.f6018d == 0) {
            obj = this.f6017c.f6305g.m9098c();
            if (obj.isSuccess()) {
                obj = this.f6017c.f6305g.m9097b();
            }
        } else if (this.f6018d == 1) {
            obj = this.f6017c.f6305g.m9094a(this.f6015a, this.f6016b);
        } else if (this.f6018d == 2) {
            obj = this.f6017c.f6305g.m9098c();
        } else if (this.f6018d == 3) {
            obj = this.f6017c.f6305g.m9099d();
        }
        obtainMessage.what = this.f6018d;
        obtainMessage.obj = obj;
        this.f6017c.f6306h.sendMessage(obtainMessage);
    }
}
