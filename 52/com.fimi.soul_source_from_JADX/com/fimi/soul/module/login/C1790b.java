package com.fimi.soul.module.login;

import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p103k.C1394s;
import com.fimi.soul.biz.p103k.ba;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.module.login.b */
class C1790b implements Runnable {
    final /* synthetic */ LaunchedActivity f8846a;

    C1790b(LaunchedActivity launchedActivity) {
        this.f8846a = launchedActivity;
    }

    public void run() {
        C1394s.m9340a(this.f8846a.f8765g).m9347a(this.f8846a);
        this.f8846a.f8766h = ba.m9259a(this.f8846a.f8765g);
        this.f8846a.speakTTs.m7957a();
        if (!this.f8846a.f8763c.contains("isfirstloading")) {
            this.f8846a.m11472a(true);
        } else if (be.m12370b(this.f8846a)) {
            this.f8846a.f8766h.m9262a(this.f8846a.f8765g, new C1791c(this));
        } else {
            C1236a.m8528a(this.f8846a.f8765g);
            this.f8846a.m11472a(false);
        }
    }
}
