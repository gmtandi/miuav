package com.fimi.soul.module.update;

import android.os.Handler;
import android.os.Message;

/* renamed from: com.fimi.soul.module.update.h */
class C1916h extends Handler {
    final /* synthetic */ FindNewFirmwareAvtivity f9825a;

    C1916h(FindNewFirmwareAvtivity findNewFirmwareAvtivity) {
        this.f9825a = findNewFirmwareAvtivity;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 0) {
            if (this.f9825a.f9712n && this.f9825a.f9710l.m8848d() && this.f9825a.drone.m9570Q()) {
                this.f9825a.f9710l.m8873r().m8745b();
            } else if (!this.f9825a.f9710l.m8848d() && this.f9825a.drone.m9570Q()) {
                this.f9825a.f9710l.m8875t().m8790b();
                this.f9825a.f9710l.m8873r().m8745b();
                this.f9825a.f9712n = true;
                this.f9825a.f9711m = false;
            } else if (!this.f9825a.drone.m9570Q()) {
                this.f9825a.f9712n = true;
                this.f9825a.f9711m = false;
            }
            this.f9825a.f9713o.sendEmptyMessageDelayed(0, 2000);
        }
    }
}
