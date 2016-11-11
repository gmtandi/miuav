package com.fimi.soul.media.gallery;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.fimi.soul.media.gallery.n */
class C1600n implements OnClickListener {
    final /* synthetic */ DroneImagePagerActivity f7821a;

    C1600n(DroneImagePagerActivity droneImagePagerActivity) {
        this.f7821a = droneImagePagerActivity;
    }

    public void onClick(View view) {
        if (this.f7821a.f7696A != null) {
            this.f7821a.f7696A.m7781c();
            this.f7821a.f7700E.setVisibility(8);
            this.f7821a.f7699D.setVisibility(0);
        }
    }
}
