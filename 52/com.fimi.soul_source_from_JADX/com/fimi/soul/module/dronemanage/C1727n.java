package com.fimi.soul.module.dronemanage;

import android.view.View;
import android.view.View.OnClickListener;
import com.fimi.soul.biz.p090b.C1247f;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.module.dronemanage.n */
class C1727n implements OnClickListener {
    final /* synthetic */ FlightMapFragment f8507a;

    C1727n(FlightMapFragment flightMapFragment) {
        this.f8507a = flightMapFragment;
    }

    public void onClick(View view) {
        C1247f.m8565k().m8583l();
        this.f8507a.g.m9589a(C1584h.CLEARDATA);
    }
}
