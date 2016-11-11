package com.fimi.soul.media.gallery;

import android.view.View;
import android.view.View.OnClickListener;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.aq;

/* renamed from: com.fimi.soul.media.gallery.e */
class C1591e implements OnClickListener {
    final /* synthetic */ DroneImagePagerActivity f7812a;

    C1591e(DroneImagePagerActivity droneImagePagerActivity) {
        this.f7812a = droneImagePagerActivity;
    }

    public void onClick(View view) {
        new aq(this.f7812a).m12748a(this.f7812a.getString(C1205R.string.ensure_delete_file)).m12747a(this.f7812a.getResources().getColor(C1205R.color.dialog_ensure_hot)).m12753b(this.f7812a.getString(C1205R.string.delete), new C1593g(this)).m12749a(this.f7812a.getString(C1205R.string.cancel), new C1592f(this)).m12746a().show();
    }
}
