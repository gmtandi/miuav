package com.fimi.soul.module;

import java.util.TimerTask;

/* renamed from: com.fimi.soul.module.a */
class C1665a extends TimerTask {
    final /* synthetic */ TestActivity f7855a;

    C1665a(TestActivity testActivity) {
        this.f7855a = testActivity;
    }

    public void run() {
        this.f7855a.f7838c.sendEmptyMessage(0);
        this.f7855a.f7837b = this.f7855a.f7837b + 1;
        if (this.f7855a.f7837b == 12) {
            this.f7855a.f7837b = 0;
        }
    }
}
