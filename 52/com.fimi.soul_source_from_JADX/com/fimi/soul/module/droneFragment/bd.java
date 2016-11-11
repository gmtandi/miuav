package com.fimi.soul.module.droneFragment;

import com.fimi.soul.biz.camera.C1309p;
import java.util.TimerTask;

class bd extends TimerTask {
    final /* synthetic */ ShowDroneUiFragment f8202a;

    bd(ShowDroneUiFragment showDroneUiFragment) {
        this.f8202a = showDroneUiFragment;
    }

    public void run() {
        if (this.f8202a.f8143r.m8850f() == C1309p.Recoding) {
            this.f8202a.f8127b.sendEmptyMessage(1);
        } else if (this.f8202a.f8120U != null) {
            this.f8202a.f8120U.cancel();
            this.f8202a.f8120U = null;
        } else {
            this.f8202a.f8120U = null;
        }
    }
}
