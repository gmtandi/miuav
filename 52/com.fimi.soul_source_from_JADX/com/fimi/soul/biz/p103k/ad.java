package com.fimi.soul.biz.p103k;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import java.util.HashMap;

/* renamed from: com.fimi.soul.biz.k.ad */
class ad implements PlatformActionListener {
    final /* synthetic */ ac f6039a;

    ad(ac acVar) {
        this.f6039a = acVar;
    }

    public void onCancel(Platform platform, int i) {
        this.f6039a.f6038k.sendEmptyMessage(2);
    }

    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        this.f6039a.f6038k.sendEmptyMessage(1);
    }

    public void onError(Platform platform, int i, Throwable th) {
        this.f6039a.f6038k.sendEmptyMessage(3);
    }
}
