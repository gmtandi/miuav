package com.fimi.soul.view;

import android.view.View;
import android.view.View.OnClickListener;

class bw implements OnClickListener {
    final /* synthetic */ UIRefreshListView f10740a;

    bw(UIRefreshListView uIRefreshListView) {
        this.f10740a = uIRefreshListView;
    }

    public void onClick(View view) {
        if (!this.f10740a.f10595m) {
            return;
        }
        if (this.f10740a.f10596n) {
            if (this.f10740a.f10594l != 1 && this.f10740a.f10593k != 2) {
                this.f10740a.f10594l = 1;
                this.f10740a.m12708m();
            }
        } else if (this.f10740a.f10594l != 1) {
            this.f10740a.f10594l = 1;
            this.f10740a.m12708m();
        }
    }
}
