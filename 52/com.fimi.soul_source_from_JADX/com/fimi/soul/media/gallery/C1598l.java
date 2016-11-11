package com.fimi.soul.media.gallery;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import com.fimi.soul.biz.camera.entity.X11FileInfo;

/* renamed from: com.fimi.soul.media.gallery.l */
class C1598l implements OnPageChangeListener {
    final /* synthetic */ DroneImagePagerActivity f7819a;

    C1598l(DroneImagePagerActivity droneImagePagerActivity) {
        this.f7819a = droneImagePagerActivity;
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
        Log.v("mPager", "arg0=" + i);
        if (i2 == 0 && ((double) f) == 0.0d && this.f7819a.f7723y.m10695a(i) != null) {
            this.f7819a.f7723y.m10695a(i).m10648b();
        }
    }

    public void onPageSelected(int i) {
        this.f7819a.f7716r = true;
        Object a = this.f7819a.m10656a(i);
        this.f7819a.f7712n.setText(a);
        this.f7819a.m10661b();
        if (a.endsWith(X11FileInfo.FILE_TYPE_MP4)) {
            this.f7819a.f7722x.setVisibility(0);
        } else {
            this.f7819a.f7722x.setVisibility(8);
        }
        this.f7819a.m10669f();
    }
}
