package com.fimi.soul.module.remote;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.fimi.soul.module.remote.e */
class C1824e implements OnClickListener {
    final /* synthetic */ RemoteCalibration f9008a;

    C1824e(RemoteCalibration remoteCalibration) {
        this.f9008a = remoteCalibration;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.f9008a.f8951d.m10838n();
        this.f9008a.f8951d.m10837m();
        this.f9008a.finish();
    }
}
