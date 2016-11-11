package cn.sharesdk.sina.weibo;

import android.os.Bundle;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.SSOListener;
import cn.sharesdk.framework.utils.C0205d;
import com.mob.tools.utils.C2178R;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;

/* renamed from: cn.sharesdk.sina.weibo.h */
class C0218h implements SSOListener {
    final /* synthetic */ AuthorizeListener f413a;
    final /* synthetic */ C0217g f414b;

    C0218h(C0217g c0217g, AuthorizeListener authorizeListener) {
        this.f414b = c0217g;
        this.f413a = authorizeListener;
    }

    public void onCancel() {
        this.f413a.onCancel();
    }

    public void onComplete(Bundle bundle) {
        try {
            C2178R.parseLong(bundle.getString(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2));
            this.f413a.onComplete(bundle);
        } catch (Throwable th) {
            onFailed(th);
        }
    }

    public void onFailed(Throwable th) {
        C0205d.m752a().m738d(th);
        this.f414b.m625b(this.f413a);
    }
}
