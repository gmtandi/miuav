package com.fimi.soul.module.setting;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

class ae implements OnScrollListener {
    final /* synthetic */ MapSettingFragment f9210a;

    ae(MapSettingFragment mapSettingFragment) {
        this.f9210a = mapSettingFragment;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        int i2 = 0;
        if (!this.f9210a.f9137a) {
            if (i == 0) {
                this.f9210a.f9160x = this.f9210a.f9143g.getFirstVisiblePosition();
            }
            if (this.f9210a.f9146j != null) {
                View childAt = this.f9210a.f9143g.getChildAt(0);
                MapSettingFragment mapSettingFragment = this.f9210a;
                if (childAt != null) {
                    i2 = childAt.getTop();
                }
                mapSettingFragment.f9161y = i2;
            }
        }
    }
}
