package com.fimi.soul.module.droneui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.module.calibcompass.CaliCompassActivity;

/* renamed from: com.fimi.soul.module.droneui.m */
class C1751m implements OnClickListener {
    final /* synthetic */ FlightActivity f8616a;

    C1751m(FlightActivity flightActivity) {
        this.f8616a = flightActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent(this.f8616a, CaliCompassActivity.class);
        intent.putExtra(C1236a.f5594R, true);
        this.f8616a.startActivity(intent);
        dialogInterface.dismiss();
    }
}
