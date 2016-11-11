package com.fimi.soul.module.login;

import com.fimi.soul.drone.p110d.C1488o;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.module.login.e */
class C1793e implements Runnable {
    final /* synthetic */ LoginActivity f8849a;

    C1793e(LoginActivity loginActivity) {
        this.f8849a = loginActivity;
    }

    public void run() {
        boolean z = false;
        int i = (byte) 0;
        int i2 = 0;
        while (i2 < 12) {
            if (!z) {
                z = be.m12380d(C1543c.f7237l);
            }
            if (z && (!this.f8849a.drone.m9569P().m9995a() || i2 < 2)) {
                this.f8849a.m11497h();
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.f8849a.drone.m9569P().m9995a() && r0 == 0) {
                C1488o.f7054a.f6936d = (byte) 0;
                C1488o.f7054a.f6937e = (byte) 2;
                this.f8849a.drone.m9569P().m9993a(C1488o.f7054a.m9794a());
                i = 1;
            }
            if (this.f8849a.drone.m9569P().m9995a() && this.f8849a.drone.m9570Q()) {
                break;
            }
            i2++;
        }
        this.f8849a.f8790K.removeMessages(0);
        if (this.f8849a.drone.m9569P().m9995a()) {
            this.f8849a.f8790K.removeCallbacks(this.f8849a.f8792M);
            this.f8849a.f8790K.sendEmptyMessageDelayed(1, 500);
            return;
        }
        this.f8849a.f8790K.sendEmptyMessage(0);
    }
}
