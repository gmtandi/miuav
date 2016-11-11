package cn.sharesdk.wechat.friends;

import android.os.Bundle;
import cn.sharesdk.framework.authorize.AuthorizeListener;

/* renamed from: cn.sharesdk.wechat.friends.a */
class C0222a implements AuthorizeListener {
    final /* synthetic */ Wechat f427a;

    C0222a(Wechat wechat) {
        this.f427a = wechat;
    }

    public void onCancel() {
        if (this.f427a.listener != null) {
            this.f427a.listener.onCancel(this.f427a, 1);
        }
    }

    public void onComplete(Bundle bundle) {
        this.f427a.afterRegister(1, null);
    }

    public void onError(Throwable th) {
        if (this.f427a.listener != null) {
            this.f427a.listener.onError(this.f427a, 1, th);
        }
    }
}
