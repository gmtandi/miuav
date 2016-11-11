package com.fimi.soul.module.droneFragment;

import java.util.TimerTask;

class aq extends TimerTask {
    final /* synthetic */ ShowDroneStatusLineFragment f8185a;

    aq(ShowDroneStatusLineFragment showDroneStatusLineFragment) {
        this.f8185a = showDroneStatusLineFragment;
    }

    public void run() {
        this.f8185a.f8098y.sendEmptyMessage(1);
    }
}
