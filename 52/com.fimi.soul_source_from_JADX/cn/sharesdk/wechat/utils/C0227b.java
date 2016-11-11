package cn.sharesdk.wechat.utils;

import android.os.Bundle;

/* renamed from: cn.sharesdk.wechat.utils.b */
public class C0227b extends WechatResp {
    public String f442a;
    public String f443b;
    public int f444c;
    public String f445d;
    public String f446e;

    public C0227b(Bundle bundle) {
        super(bundle);
    }

    public int m896a() {
        return 1;
    }

    public void m897a(Bundle bundle) {
        super.m888a(bundle);
        this.f442a = bundle.getString("_wxapi_sendauth_resp_userName");
        this.f443b = bundle.getString("_wxapi_sendauth_resp_token");
        this.f444c = bundle.getInt("_wxapi_sendauth_resp_expireDate", 0);
        this.f445d = bundle.getString("_wxapi_sendauth_resp_state");
        this.f446e = bundle.getString("_wxapi_sendauth_resp_url");
    }

    public void m898b(Bundle bundle) {
        super.m889b(bundle);
        bundle.putString("_wxapi_sendauth_resp_userName", this.f442a);
        bundle.putString("_wxapi_sendauth_resp_token", this.f443b);
        bundle.putInt("_wxapi_sendauth_resp_expireDate", this.f444c);
        bundle.putString("_wxapi_sendauth_resp_state", this.f445d);
        bundle.putString("_wxapi_sendauth_resp_url", this.f446e);
    }
}
