package com.fimi.soul.module.dronemanage;

import android.view.View;
import android.view.View.OnClickListener;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.module.dronemanage.q */
class C1730q implements OnClickListener {
    final /* synthetic */ FlightMapFragment f8512a;

    C1730q(FlightMapFragment flightMapFragment) {
        this.f8512a = flightMapFragment;
    }

    public void onClick(View view) {
        this.f8512a.g.m9589a(C1584h.CLEARMARKERTAKEPHOTO);
    }
}
