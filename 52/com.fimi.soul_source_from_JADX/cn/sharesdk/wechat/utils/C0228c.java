package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.wechat.utils.WXMediaMessage.C0224a;

/* renamed from: cn.sharesdk.wechat.utils.c */
public class C0228c extends WechatResp {
    public WXMediaMessage f447a;

    public C0228c(Bundle bundle) {
        super(bundle);
    }

    public int m899a() {
        return 3;
    }

    public void m900a(Bundle bundle) {
        super.m888a(bundle);
        this.f447a = C0224a.m854a(bundle);
    }

    public void m901b(Bundle bundle) {
        super.m889b(bundle);
        bundle.putAll(C0224a.m853a(this.f447a));
    }
}
