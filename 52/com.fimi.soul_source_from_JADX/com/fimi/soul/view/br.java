package com.fimi.soul.view;

import android.view.View;
import android.view.View.OnClickListener;

class br implements OnClickListener {
    final /* synthetic */ UIRefreshEXPView f10737a;

    br(UIRefreshEXPView uIRefreshEXPView) {
        this.f10737a = uIRefreshEXPView;
    }

    public void onClick(View view) {
        if (!this.f10737a.f10556m) {
            return;
        }
        if (this.f10737a.f10557n) {
            if (this.f10737a.f10555l != 1 && this.f10737a.f10554k != 2) {
                this.f10737a.f10555l = 1;
                this.f10737a.m12684m();
            }
        } else if (this.f10737a.f10555l != 1) {
            this.f10737a.f10555l = 1;
            this.f10737a.m12684m();
        }
    }
}
