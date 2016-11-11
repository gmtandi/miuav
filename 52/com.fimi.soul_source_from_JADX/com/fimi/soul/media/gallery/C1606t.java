package com.fimi.soul.media.gallery;

import android.view.View;
import android.view.View.OnClickListener;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.aq;

/* renamed from: com.fimi.soul.media.gallery.t */
class C1606t implements OnClickListener {
    final /* synthetic */ DroneImagePagerActivity f7827a;

    C1606t(DroneImagePagerActivity droneImagePagerActivity) {
        this.f7827a = droneImagePagerActivity;
    }

    public void onClick(View view) {
        new aq(this.f7827a).m12748a(this.f7827a.getString(C1205R.string.ensure_delete_file)).m12747a(this.f7827a.getResources().getColor(C1205R.color.dialog_ensure_hot)).m12753b(this.f7827a.getString(C1205R.string.delete), new C1608v(this)).m12749a(this.f7827a.getString(C1205R.string.cancel), new C1607u(this)).m12746a().show();
    }
}
