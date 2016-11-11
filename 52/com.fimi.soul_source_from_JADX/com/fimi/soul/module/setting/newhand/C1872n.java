package com.fimi.soul.module.setting.newhand;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;

/* renamed from: com.fimi.soul.module.setting.newhand.n */
class C1872n implements OnPageChangeListener {
    final /* synthetic */ NewHandActivity f9512a;

    C1872n(NewHandActivity newHandActivity) {
        this.f9512a = newHandActivity;
    }

    public void onPageScrollStateChanged(int i) {
        Log.i("zhej", "pager:" + i);
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
    }
}
