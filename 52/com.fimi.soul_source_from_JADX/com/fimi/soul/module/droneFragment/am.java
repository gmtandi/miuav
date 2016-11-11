package com.fimi.soul.module.droneFragment;

import java.util.TimerTask;

class am extends TimerTask {
    final /* synthetic */ ShowDroneStatusFragment f8181a;

    am(ShowDroneStatusFragment showDroneStatusFragment) {
        this.f8181a = showDroneStatusFragment;
    }

    public void run() {
        this.f8181a.f8034R.sendEmptyMessage(0);
    }
}
