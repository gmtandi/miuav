package com.fimi.soul.module.droneui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.fimi.soul.utils.ay;

/* renamed from: com.fimi.soul.module.droneui.n */
class C1752n implements OnClickListener {
    final /* synthetic */ ay f8617a;
    final /* synthetic */ int f8618b;
    final /* synthetic */ FlightActivity f8619c;

    C1752n(FlightActivity flightActivity, ay ayVar, int i) {
        this.f8619c = flightActivity;
        this.f8617a = ayVar;
        this.f8618b = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f8617a.m12296a("insurance_ret", this.f8618b - 1);
        dialogInterface.dismiss();
    }
}
