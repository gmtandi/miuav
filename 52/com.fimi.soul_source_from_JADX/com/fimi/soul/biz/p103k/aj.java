package com.fimi.soul.biz.p103k;

import android.view.View;
import android.view.View.OnClickListener;
import cn.sharesdk.sina.weibo.SinaWeibo;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;

/* renamed from: com.fimi.soul.biz.k.aj */
class aj implements OnClickListener {
    final /* synthetic */ ag f6052a;

    aj(ag agVar) {
        this.f6052a = agVar;
    }

    public void onClick(View view) {
        if (this.f6052a.f6049h) {
            this.f6052a.f6046e.m9199a(this.f6052a.f6046e.f6036i, SinaWeibo.NAME);
            this.f6052a.dismiss();
            return;
        }
        ak.m8085a(this.f6052a.f6047f, this.f6052a.f6047f.getString(C1205R.string.no_install_apk), 0);
    }
}
