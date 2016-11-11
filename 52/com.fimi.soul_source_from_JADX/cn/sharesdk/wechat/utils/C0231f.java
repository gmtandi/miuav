package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.wechat.utils.WXMediaMessage.C0224a;

/* renamed from: cn.sharesdk.wechat.utils.f */
public class C0231f extends WechatResp {
    public WXMediaMessage f450a;

    public C0231f(Bundle bundle) {
        super(bundle);
    }

    public int m906a() {
        return 4;
    }

    public void m907a(Bundle bundle) {
        super.m888a(bundle);
        this.f450a = C0224a.m854a(bundle);
    }

    public void m908b(Bundle bundle) {
        super.m889b(bundle);
        bundle.putAll(C0224a.m853a(this.f450a));
    }
}
