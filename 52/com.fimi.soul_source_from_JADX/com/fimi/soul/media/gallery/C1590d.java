package com.fimi.soul.media.gallery;

import android.view.View;
import android.view.View.OnClickListener;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;

/* renamed from: com.fimi.soul.media.gallery.d */
class C1590d implements OnClickListener {
    final /* synthetic */ DroneImagePagerActivity f7811a;

    C1590d(DroneImagePagerActivity droneImagePagerActivity) {
        this.f7811a = droneImagePagerActivity;
    }

    public void onClick(View view) {
        if (be.m12370b(this.f7811a)) {
            this.f7811a.m10667e();
        } else {
            ak.m8083a(this.f7811a, (int) C1205R.string.login_result_net, (int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
        }
    }
}
