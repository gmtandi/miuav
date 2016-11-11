package com.fimi.soul.module.droneFragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ae implements OnClickListener {
    final /* synthetic */ ShowDroneControlFragment f8173a;

    ae(ShowDroneControlFragment showDroneControlFragment) {
        this.f8173a = showDroneControlFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f8173a.f8000l.m10815a();
        dialogInterface.dismiss();
    }
}
