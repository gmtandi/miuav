package com.fimi.soul.view;

class bv implements Runnable {
    final /* synthetic */ boolean f10738a;
    final /* synthetic */ UIRefreshListView f10739b;

    bv(UIRefreshListView uIRefreshListView, boolean z) {
        this.f10739b = uIRefreshListView;
        this.f10738a = z;
    }

    public void run() {
        if (this.f10738a && this.f10739b.getFooterViewsCount() == 0) {
            this.f10739b.m12704i();
        } else {
            this.f10739b.m12715g();
        }
        this.f10739b.f10605w.setVisibility(this.f10738a ? 0 : 8);
        this.f10739b.f10605w.setPadding(0, this.f10738a ? 0 : -this.f10739b.f10605w.getHeight(), 0, 0);
    }
}
