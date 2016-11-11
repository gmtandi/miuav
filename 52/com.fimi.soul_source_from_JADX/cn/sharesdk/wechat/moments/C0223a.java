package cn.sharesdk.wechat.moments;

import android.os.Bundle;
import cn.sharesdk.framework.authorize.AuthorizeListener;

/* renamed from: cn.sharesdk.wechat.moments.a */
class C0223a implements AuthorizeListener {
    final /* synthetic */ WechatMoments f431a;

    C0223a(WechatMoments wechatMoments) {
        this.f431a = wechatMoments;
    }

    public void onCancel() {
        if (this.f431a.listener != null) {
            this.f431a.listener.onCancel(this.f431a, 1);
        }
    }

    public void onComplete(Bundle bundle) {
        this.f431a.afterRegister(1, null);
    }

    public void onError(Throwable th) {
        if (this.f431a.listener != null) {
            this.f431a.listener.onError(this.f431a, 1, th);
        }
    }
}
