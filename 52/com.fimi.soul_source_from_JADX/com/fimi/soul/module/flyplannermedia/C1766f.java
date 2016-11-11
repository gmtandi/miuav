package com.fimi.soul.module.flyplannermedia;

import android.view.View;
import android.view.View.OnClickListener;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.aq;
import java.util.List;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.module.flyplannermedia.f */
class C1766f implements OnClickListener {
    final /* synthetic */ DroneLocalFragment f8698a;

    C1766f(DroneLocalFragment droneLocalFragment) {
        this.f8698a = droneLocalFragment;
    }

    public void onClick(View view) {
        List d = this.f8698a.m11386b().m8495d();
        aq aqVar = new aq(this.f8698a.getActivity());
        if (this.f8698a.m11386b().m8493c() != null && this.f8698a.m11386b().m8493c().size() > 0) {
            aqVar.m12748a(String.format(this.f8698a.getString(C1205R.string.ensure_delete_image), new Object[]{this.f8698a.m11386b().m8493c().size() + C2915a.f14760f})).m12747a(this.f8698a.getActivity().getResources().getColor(C1205R.color.dialog_ensure_hot)).m12753b(this.f8698a.getString(C1205R.string.delete), new C1768h(this, d)).m12749a(this.f8698a.getString(C1205R.string.cancel), new C1767g(this)).m12746a().show();
        }
    }
}
