package com.fimi.soul.module.flyplannermedia;

import android.view.View;
import android.view.View.OnClickListener;
import com.fimi.soul.C1205R;

/* renamed from: com.fimi.soul.module.flyplannermedia.e */
class C1765e implements OnClickListener {
    final /* synthetic */ DroneBaseMediaFragment f8697a;

    C1765e(DroneBaseMediaFragment droneBaseMediaFragment) {
        this.f8697a = droneBaseMediaFragment;
    }

    public void onClick(View view) {
        if (this.f8697a.f8653g) {
            this.f8697a.m11386b().m8498g();
            this.f8697a.m11394f().setText(C1205R.string.select_all);
        } else {
            this.f8697a.m11394f().setText(C1205R.string.undo_select_all);
            this.f8697a.m11386b().m8497f();
        }
        this.f8697a.f8653g = !this.f8697a.f8653g;
    }
}
