package com.fimi.soul.module.setting.newhand;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: com.fimi.soul.module.setting.newhand.l */
class C1870l extends PagerAdapter {
    final /* synthetic */ LoopWidget f9510a;

    C1870l(LoopWidget loopWidget) {
        this.f9510a = loopWidget;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) this.f9510a.f9359c.get(i));
    }

    public int getCount() {
        return this.f9510a.f9359c.size();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        viewGroup.addView((View) this.f9510a.f9359c.get(i));
        return this.f9510a.f9359c.get(i);
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
