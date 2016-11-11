package com.fimi.soul.biz.p103k;

import android.view.View;
import android.view.View.OnClickListener;
import cn.sharesdk.wechat.moments.WechatMoments;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;

/* renamed from: com.fimi.soul.biz.k.ai */
class ai implements OnClickListener {
    final /* synthetic */ ag f6051a;

    ai(ag agVar) {
        this.f6051a = agVar;
    }

    public void onClick(View view) {
        if (this.f6051a.f6048g) {
            this.f6051a.f6046e.m9199a(this.f6051a.f6046e.f6036i, WechatMoments.NAME);
            this.f6051a.f6046e.f6037j.m9203a(ac.f6029b);
            this.f6051a.dismiss();
            return;
        }
        ak.m8085a(this.f6051a.f6047f, this.f6051a.f6047f.getString(C1205R.string.no_install_apk), 0);
    }
}
