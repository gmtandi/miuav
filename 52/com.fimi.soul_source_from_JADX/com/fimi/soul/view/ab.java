package com.fimi.soul.view;

class ab implements Runnable {
    final /* synthetic */ C2045z f10654a;

    ab(C2045z c2045z) {
        this.f10654a = c2045z;
    }

    public void run() {
        this.f10654a.f11103f = this.f10654a.f11103f - 1;
        if (this.f10654a.f11103f >= 0) {
            this.f10654a.f11098a.postDelayed(this.f10654a.f11099b, 1000);
        } else if (this.f10654a.isShowing()) {
            this.f10654a.dismiss();
        }
    }
}
