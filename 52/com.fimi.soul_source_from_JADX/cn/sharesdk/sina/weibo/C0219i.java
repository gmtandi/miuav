package cn.sharesdk.sina.weibo;

import android.os.Bundle;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import java.util.HashMap;

/* renamed from: cn.sharesdk.sina.weibo.i */
class C0219i implements AuthorizeListener {
    final /* synthetic */ PlatformActionListener f415a;
    final /* synthetic */ ShareParams f416b;
    final /* synthetic */ C0217g f417c;

    C0219i(C0217g c0217g, PlatformActionListener platformActionListener, ShareParams shareParams) {
        this.f417c = c0217g;
        this.f415a = platformActionListener;
        this.f416b = shareParams;
    }

    public void onCancel() {
        if (this.f415a != null) {
            this.f415a.onCancel(this.f417c.a, 9);
        }
    }

    public void onComplete(Bundle bundle) {
        if (this.f415a != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("ShareParams", this.f416b);
            this.f415a.onComplete(this.f417c.a, 9, hashMap);
        }
    }

    public void onError(Throwable th) {
        if (this.f415a != null) {
            this.f415a.onError(this.f417c.a, 9, th);
        }
    }
}
