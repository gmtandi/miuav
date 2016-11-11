package com.fimi.soul.module.droneFragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ai implements OnClickListener {
    final /* synthetic */ ShowDroneControlFragment f8177a;

    ai(ShowDroneControlFragment showDroneControlFragment) {
        this.f8177a = showDroneControlFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f8177a.f8000l.m10826c();
        dialogInterface.dismiss();
    }
}
