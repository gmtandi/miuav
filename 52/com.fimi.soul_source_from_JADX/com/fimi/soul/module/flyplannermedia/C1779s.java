package com.fimi.soul.module.flyplannermedia;

import android.view.View;
import android.view.View.OnClickListener;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.aq;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.module.flyplannermedia.s */
class C1779s implements OnClickListener {
    final /* synthetic */ DroneOnlineFragment f8715a;

    C1779s(DroneOnlineFragment droneOnlineFragment) {
        this.f8715a = droneOnlineFragment;
    }

    public void onClick(View view) {
        this.f8715a.f8685h = this.f8715a.m11386b().m8493c();
        new aq(this.f8715a.getActivity()).m12748a(String.format(this.f8715a.getString(C1205R.string.ensure_delete_image), new Object[]{this.f8715a.f8685h.size() + C2915a.f14760f})).m12747a(this.f8715a.getActivity().getResources().getColor(C1205R.color.dialog_ensure_hot)).m12753b(this.f8715a.getString(C1205R.string.delete), new C1781u(this)).m12749a(this.f8715a.getString(C1205R.string.cancel), new C1780t(this)).m12746a().show();
    }
}
