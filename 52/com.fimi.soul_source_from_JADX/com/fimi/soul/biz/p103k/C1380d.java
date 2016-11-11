package com.fimi.soul.biz.p103k;

import android.os.Message;
import com.fimi.soul.entity.BroadcastMode;

/* renamed from: com.fimi.soul.biz.k.d */
class C1380d implements Runnable {
    final /* synthetic */ C1379c f6194a;
    private int f6195b;
    private BroadcastMode f6196c;
    private String f6197d;
    private int f6198e;

    public C1380d(C1379c c1379c, int i, String str, int i2, BroadcastMode broadcastMode) {
        this.f6194a = c1379c;
        this.f6195b = i;
        this.f6196c = broadcastMode;
        this.f6197d = str;
        this.f6198e = i2;
    }

    public void run() {
        Message obtainMessage = this.f6194a.f6191n.obtainMessage();
        Object obj = null;
        if (this.f6195b == 0) {
            obj = this.f6194a.f6190m.m9018a(this.f6194a.f6192o);
        } else if (this.f6195b == 1) {
            obj = this.f6194a.f6190m.m9030a(this.f6196c, this.f6194a.f6192o);
        } else if (this.f6195b == 3) {
            obj = this.f6194a.f6190m.m9028a(this.f6194a.f6192o, this.f6197d, this.f6198e);
        } else if (this.f6195b == 6) {
            obj = this.f6194a.f6190m.m9026a(this.f6194a.f6192o, this.f6198e);
        } else if (this.f6195b == 5) {
            obj = this.f6194a.f6190m.m9031b(this.f6194a.f6192o, this.f6198e);
        } else if (this.f6195b == 7) {
            obj = this.f6194a.f6190m.m9035c(this.f6194a.f6192o, this.f6197d, this.f6198e);
        } else if (this.f6195b == 8) {
            obj = this.f6194a.f6190m.m9034b(this.f6196c, this.f6194a.f6192o);
        } else if (this.f6195b == 9) {
            obj = this.f6194a.f6190m.m9036c(this.f6196c, this.f6194a.f6192o);
        } else if (this.f6195b == 10) {
            obj = this.f6194a.f6190m.m9037c(this.f6196c.getBroadcastID(), this.f6194a.f6192o);
        }
        obtainMessage.what = this.f6195b;
        obtainMessage.obj = obj;
        this.f6194a.f6191n.sendMessage(obtainMessage);
    }
}
