package com.fimi.soul.media.gallery;

import android.view.View;
import android.view.View.OnClickListener;
import com.fimi.kernel.C1189f;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.utils.ay;

/* renamed from: com.fimi.soul.media.gallery.o */
class C1601o implements OnClickListener {
    final /* synthetic */ DroneImagePagerActivity f7822a;

    C1601o(DroneImagePagerActivity droneImagePagerActivity) {
        this.f7822a = droneImagePagerActivity;
    }

    public void onClick(View view) {
        if (ay.m12293a(C1189f.m8327a()).getBoolean(C1236a.f5592P, false)) {
            this.f7822a.m10664d();
        } else {
            this.f7822a.m10663c();
        }
    }
}
