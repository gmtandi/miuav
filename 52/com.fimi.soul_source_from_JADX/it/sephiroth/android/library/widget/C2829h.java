package it.sephiroth.android.library.widget;

import android.view.VelocityTracker;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;

/* renamed from: it.sephiroth.android.library.widget.h */
class C2829h implements Runnable {
    final /* synthetic */ C2828g f14543a;

    C2829h(C2828g c2828g) {
        this.f14543a = c2828g;
    }

    public void run() {
        int d = this.f14543a.f14539a.bw;
        VelocityTracker e = this.f14543a.f14539a.aX;
        as a = this.f14543a.f14540b;
        if (e != null && d != -1) {
            e.computeCurrentVelocity(XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER, (float) this.f14543a.f14539a.bu);
            float f = -e.getXVelocity(d);
            if (Math.abs(f) < ((float) this.f14543a.f14539a.bt) || !a.m16322a(f, 0.0f)) {
                this.f14543a.m16378b();
                this.f14543a.f14539a.f14384Z = 3;
                this.f14543a.f14539a.m16135b(1);
                return;
            }
            this.f14543a.f14539a.postDelayed(this, 40);
        }
    }
}
