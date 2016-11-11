package com.fimi.soul.view;

class bq implements Runnable {
    final /* synthetic */ boolean f10735a;
    final /* synthetic */ UIRefreshEXPView f10736b;

    bq(UIRefreshEXPView uIRefreshEXPView, boolean z) {
        this.f10736b = uIRefreshEXPView;
        this.f10735a = z;
    }

    public void run() {
        if (this.f10735a && this.f10736b.getFooterViewsCount() == 0) {
            this.f10736b.m12680i();
        } else {
            this.f10736b.m12691g();
        }
        this.f10736b.f10566w.setVisibility(this.f10735a ? 0 : 8);
        this.f10736b.f10566w.setPadding(0, this.f10735a ? 0 : -this.f10736b.f10566w.getHeight(), 0, 0);
    }
}
