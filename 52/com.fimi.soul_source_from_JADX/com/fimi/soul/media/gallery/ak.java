package com.fimi.soul.media.gallery;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import com.fimi.soul.C1205R;

class ak implements OnPageChangeListener {
    final /* synthetic */ ImagePagerActivity f7793a;

    ak(ImagePagerActivity imagePagerActivity) {
        this.f7793a = imagePagerActivity;
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.f7793a.f7779g.setText(this.f7793a.getString(C1205R.string.viewpager_indicator, new Object[]{Integer.valueOf(i + 1), Integer.valueOf(this.f7793a.f7777e.getAdapter().getCount())}));
    }
}
