package com.fimi.soul.module.setting;

import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.entity.PlaneMsg;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;

class ax implements C1330i {
    final /* synthetic */ UserFeedBackActivity f9285a;

    ax(UserFeedBackActivity userFeedBackActivity) {
        this.f9285a = userFeedBackActivity;
    }

    public void m11690a(PlaneMsg planeMsg) {
        if (planeMsg == null || !planeMsg.isSuccess()) {
            this.f9285a.m11651a((int) C1205R.string.feed_false, (int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
        } else {
            this.f9285a.m11651a((int) C1205R.string.feed_success, (int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
            this.f9285a.finish();
        }
        this.f9285a.f9195w.dismiss();
    }
}
