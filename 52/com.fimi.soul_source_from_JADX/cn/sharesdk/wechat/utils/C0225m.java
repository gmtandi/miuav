package cn.sharesdk.wechat.utils;

import android.os.Bundle;

/* renamed from: cn.sharesdk.wechat.utils.m */
public abstract class C0225m {
    public String f439c;

    public abstract int m890a();

    public void m891a(Bundle bundle) {
        bundle.putInt("_wxapi_command_type", m890a());
        bundle.putString("_wxapi_basereq_transaction", this.f439c);
    }

    public abstract boolean m892b();
}
