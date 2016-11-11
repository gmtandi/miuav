package com.fimi.kernel.p076b.p078b;

import android.os.Handler;
import android.os.Message;

/* renamed from: com.fimi.kernel.b.b.j */
class C1121j extends Handler {
    final /* synthetic */ C1113b f5164a;

    private C1121j(C1113b c1113b) {
        this.f5164a = c1113b;
    }

    public void handleMessage(Message message) {
        if (message.what == 1) {
            C1113b c1113b = null;
            if (message.arg1 == 1) {
                c1113b = this.f5164a;
            }
            this.f5164a.f5131g.m7797a(c1113b);
        }
        if (message.what == 2) {
            if (this.f5164a.f5135k >= this.f5164a.f5134j) {
                this.f5164a.f5145u = C1115d.Completed;
            }
            if (this.f5164a.f5133i != null) {
                this.f5164a.f5133i.m7796a(this.f5164a.f5145u, this.f5164a);
            }
            if (this.f5164a.f5132h != null) {
                this.f5164a.f5132h.m7796a(this.f5164a.f5145u, this.f5164a);
            }
        }
        if (message.what == 3 && this.f5164a.f5132h != null) {
            this.f5164a.f5132h.m7796a(this.f5164a.f5145u, this.f5164a);
        }
    }
}
