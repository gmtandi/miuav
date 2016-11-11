package com.fimi.kernel.p076b.p078b;

import android.os.Message;
import java.util.ArrayList;

/* renamed from: com.fimi.kernel.b.b.e */
class C1116e extends Thread {
    Boolean f5157a;
    Boolean f5158b;
    final /* synthetic */ C1113b f5159c;

    public C1116e(C1113b c1113b) {
        this.f5159c = c1113b;
        this.f5157a = Boolean.valueOf(false);
        this.f5158b = Boolean.valueOf(true);
        this.f5157a = Boolean.valueOf(true);
    }

    public C1116e(C1113b c1113b, long j) {
        this.f5159c = c1113b;
        this.f5157a = Boolean.valueOf(false);
        this.f5158b = Boolean.valueOf(true);
        c1113b.f5134j = j;
        this.f5157a = Boolean.valueOf(false);
    }

    public void run() {
        int i = 0;
        Message message = new Message();
        message.what = 1;
        if (this.f5157a.booleanValue()) {
            this.f5158b = Boolean.valueOf(this.f5159c.m7771q());
        } else {
            this.f5158b = Boolean.valueOf(this.f5159c.m7772r());
        }
        if (this.f5158b.booleanValue()) {
            long d = this.f5159c.f5134j / ((long) 1);
            this.f5159c.f5141q = new ArrayList();
            while (i < 1) {
                this.f5159c.f5141q.add(new C1128p(i, ((long) i) * d, ((long) (i + 1)) * d, 0, this.f5159c.f5139o));
                i++;
            }
            C1131s a = C1131s.m7844a(this.f5159c.f5143s);
            a.m7850a(this.f5159c.f5141q);
            a.m7849a(this.f5159c);
            this.f5159c.f5135k = 0;
            message.arg1 = 1;
        } else {
            message.arg1 = 0;
        }
        if (this.f5159c.f5144t != null) {
            this.f5159c.f5144t.sendMessage(message);
        }
    }
}
