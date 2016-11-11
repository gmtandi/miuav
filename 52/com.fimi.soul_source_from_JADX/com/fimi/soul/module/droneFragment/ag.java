package com.fimi.soul.module.droneFragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ag implements OnClickListener {
    final /* synthetic */ ShowDroneControlFragment f8175a;

    ag(ShowDroneControlFragment showDroneControlFragment) {
        this.f8175a = showDroneControlFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f8175a.f8000l.m10823b();
        dialogInterface.dismiss();
    }
}
