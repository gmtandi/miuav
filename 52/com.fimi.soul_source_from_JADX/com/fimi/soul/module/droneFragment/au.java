package com.fimi.soul.module.droneFragment;

import android.os.Handler;
import android.os.Message;
import com.fimi.soul.media.player.FermiPlayerUtils;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;

class au extends Handler {
    final /* synthetic */ ShowDroneUiFragment f8189a;

    au(ShowDroneUiFragment showDroneUiFragment) {
        this.f8189a = showDroneUiFragment;
    }

    public void handleMessage(Message message) {
        if (message.what == 1) {
            long currentTimeMillis = System.currentTimeMillis() - this.f8189a.f8116Q;
            if (!(this.f8189a.f8150y == null || this.f8189a.getActivity() == null)) {
                r1.f8150y.m10954a(true);
                this.f8189a.f8150y.m10953a(FermiPlayerUtils.getTimelineString(currentTimeMillis));
            }
        } else if (message.what == 2) {
            this.f8189a.f8143r.m8876u().m8718h();
        } else if (message.what == 7) {
            if (!this.f8189a.f8119T && this.f8189a.m7661a().m8343b().isShowing()) {
                this.f8189a.m7661a().m8348c();
                this.f8189a.m10969b(true);
            }
            this.f8189a.f8127b.sendEmptyMessageDelayed(2, 2000);
        } else if (message.what == 3) {
            this.f8189a.m11001a(true);
            this.f8189a.f8127b.sendEmptyMessageDelayed(2, 2000);
        } else if (message.what == 8) {
            this.f8189a.f8114I = true;
            this.f8189a.f8129d.m10907a((int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER, 0);
            this.f8189a.f8131f.m10907a((int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER, 0);
            this.f8189a.f8130e.m10907a((int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER, 0);
            this.f8189a.f8128c.m10907a((int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER, 0);
            this.f8189a.f8127b.sendEmptyMessageDelayed(9, 3000);
        } else if (message.what == 9) {
            this.f8189a.f8129d.m10905a();
            this.f8189a.f8131f.m10905a();
            this.f8189a.f8130e.m10905a();
            this.f8189a.f8128c.m10905a();
        } else if (message.what == 10) {
            this.f8189a.f8125Z = false;
        } else if (message.what == 11) {
            this.f8189a.aa = false;
        } else if (message.what == 12) {
            this.f8189a.f8117R = false;
        }
        super.handleMessage(message);
    }
}
