package com.fimi.soul.module.dronemanage;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.module.dronemanage.p */
class C1729p implements OnClickListener {
    final /* synthetic */ Button f8510a;
    final /* synthetic */ FlightMapFragment f8511b;

    C1729p(FlightMapFragment flightMapFragment, Button button) {
        this.f8511b = flightMapFragment;
        this.f8510a = button;
    }

    public void onClick(View view) {
        if (this.f8510a.getText().toString().equals(this.f8511b.getString(C1205R.string.delete_marker))) {
            this.f8511b.g.m9589a(C1584h.DELETE_WAYPOINT);
        }
    }
}
