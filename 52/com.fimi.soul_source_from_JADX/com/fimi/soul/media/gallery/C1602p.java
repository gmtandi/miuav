package com.fimi.soul.media.gallery;

import android.view.View;
import android.view.View.OnClickListener;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.aq;

/* renamed from: com.fimi.soul.media.gallery.p */
class C1602p implements OnClickListener {
    final /* synthetic */ DroneImagePagerActivity f7823a;

    C1602p(DroneImagePagerActivity droneImagePagerActivity) {
        this.f7823a = droneImagePagerActivity;
    }

    public void onClick(View view) {
        new aq(this.f7823a).m12748a(this.f7823a.getString(C1205R.string.ensure_delete_file)).m12747a(this.f7823a.getResources().getColor(C1205R.color.dialog_ensure_hot)).m12753b(this.f7823a.getString(C1205R.string.delete), new C1604r(this)).m12749a(this.f7823a.getString(C1205R.string.cancel), new C1603q(this)).m12746a().show();
    }
}
