package com.fimi.soul.biz.p103k;

import android.os.Message;
import com.fimi.soul.entity.BroadcastComment;

/* renamed from: com.fimi.soul.biz.k.g */
class C1383g implements Runnable {
    final /* synthetic */ C1382f f6213a;
    private int f6214b;
    private BroadcastComment f6215c;
    private String f6216d;
    private int f6217e;

    public C1383g(C1382f c1382f, int i, String str, int i2, BroadcastComment broadcastComment) {
        this.f6213a = c1382f;
        this.f6214b = i;
        this.f6215c = broadcastComment;
        this.f6216d = str;
        this.f6217e = i2;
    }

    public void run() {
        Message obtainMessage = this.f6213a.f6210g.obtainMessage();
        Object obj = null;
        if (this.f6214b == 1) {
            obj = this.f6213a.f6209f.m9027a(this.f6213a.f6211h, this.f6215c);
        } else if (this.f6214b == 3) {
            obj = this.f6213a.f6209f.m9032b(this.f6213a.f6211h, this.f6216d, this.f6217e);
        } else if (this.f6214b == 2) {
            obj = this.f6213a.f6209f.m9029a(this.f6213a.f6211h, this.f6216d, this.f6215c.getUserID());
        } else if (this.f6214b == 4) {
            obj = this.f6213a.f6209f.m9033b(this.f6213a.f6211h, this.f6216d, this.f6215c.getUserID());
        }
        obtainMessage.what = this.f6214b;
        obtainMessage.obj = obj;
        this.f6213a.f6210g.sendMessage(obtainMessage);
    }
}
