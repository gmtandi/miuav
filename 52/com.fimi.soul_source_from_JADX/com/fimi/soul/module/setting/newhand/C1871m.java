package com.fimi.soul.module.setting.newhand;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;
import com.fimi.soul.C1205R;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.module.setting.newhand.m */
class C1871m implements OnPageChangeListener {
    final /* synthetic */ LoopWidget f9511a;

    C1871m(LoopWidget loopWidget) {
        this.f9511a = loopWidget;
    }

    public void onPageScrollStateChanged(int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                if (this.f9511a.f9361e.getCurrentItem() == 0) {
                    this.f9511a.f9361e.setCurrentItem(this.f9511a.f9357a, false);
                } else if (this.f9511a.f9361e.getCurrentItem() == this.f9511a.f9357a + 1) {
                    this.f9511a.f9361e.setCurrentItem(1, false);
                }
                this.f9511a.f9363g = this.f9511a.f9361e.getCurrentItem();
                this.f9511a.f9362f = true;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f9511a.f9362f = false;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f9511a.f9362f = true;
            default:
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        for (int i2 = 0; i2 < this.f9511a.f9366j.size(); i2++) {
            if (i2 == i - 1) {
                ((ImageView) this.f9511a.f9366j.get(i2)).setImageResource(C1205R.drawable.dot_focus);
            } else {
                ((ImageView) this.f9511a.f9366j.get(i2)).setImageResource(C1205R.drawable.dot_blur);
            }
        }
    }
}
