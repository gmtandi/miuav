package com.fimi.soul.biz.p103k;

import android.view.View;
import android.view.View.OnClickListener;
import cn.sharesdk.wechat.friends.Wechat;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;

/* renamed from: com.fimi.soul.biz.k.ah */
class ah implements OnClickListener {
    final /* synthetic */ ag f6050a;

    ah(ag agVar) {
        this.f6050a = agVar;
    }

    public void onClick(View view) {
        if (this.f6050a.f6048g) {
            this.f6050a.f6046e.m9199a(this.f6050a.f6046e.f6036i, Wechat.NAME);
            this.f6050a.f6046e.f6037j.m9203a(ac.f6028a);
            this.f6050a.dismiss();
            return;
        }
        ak.m8085a(this.f6050a.f6047f, this.f6050a.f6047f.getString(C1205R.string.no_install_apk), 0);
    }
}
