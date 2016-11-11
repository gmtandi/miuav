package com.fimi.soul.media.gallery;

import android.view.View;
import com.fimi.soul.view.photodraweeview.C1587g;

/* renamed from: com.fimi.soul.media.gallery.k */
class C1597k implements C1587g {
    final /* synthetic */ DroneImagePagerActivity f7818a;

    C1597k(DroneImagePagerActivity droneImagePagerActivity) {
        this.f7818a = droneImagePagerActivity;
    }

    public void m10776a(View view, float f, float f2) {
        this.f7818a.f7696A = this.f7818a.f7724z.m7803a(this.f7818a, this.f7818a.m10672h());
        if (this.f7818a.f7696A == null) {
            this.f7818a.f7716r = !this.f7818a.f7716r;
            this.f7818a.m10694c(this.f7818a.f7716r);
            this.f7818a.m10693b(this.f7818a.f7716r);
        }
    }
}
