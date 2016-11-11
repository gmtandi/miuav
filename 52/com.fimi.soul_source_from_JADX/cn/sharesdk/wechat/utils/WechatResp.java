package cn.sharesdk.wechat.utils;

import android.os.Bundle;

public abstract class WechatResp {
    public int f436f;
    public String f437g;
    public String f438h;

    public interface ErrCode {
        public static final int ERR_AUTH_DENIED = -4;
        public static final int ERR_COMM = -1;
        public static final int ERR_OK = 0;
        public static final int ERR_SENT_FAILED = -3;
        public static final int ERR_UNSUPPORT = -5;
        public static final int ERR_USER_CANCEL = -2;
    }

    public WechatResp(Bundle bundle) {
        m888a(bundle);
    }

    public abstract int m887a();

    public void m888a(Bundle bundle) {
        this.f436f = bundle.getInt("_wxapi_baseresp_errcode");
        this.f437g = bundle.getString("_wxapi_baseresp_errstr");
        this.f438h = bundle.getString("_wxapi_baseresp_transaction");
    }

    public void m889b(Bundle bundle) {
        bundle.putInt("_wxapi_command_type", m887a());
        bundle.putInt("_wxapi_baseresp_errcode", this.f436f);
        bundle.putString("_wxapi_baseresp_errstr", this.f437g);
        bundle.putString("_wxapi_baseresp_transaction", this.f438h);
    }
}
