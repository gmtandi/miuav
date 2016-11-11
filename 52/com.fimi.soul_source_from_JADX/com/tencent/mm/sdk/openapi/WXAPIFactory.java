package com.tencent.mm.sdk.openapi;

import android.content.Context;

public class WXAPIFactory {
    private static WXAPIFactory f11795Q;

    static {
        f11795Q = null;
    }

    private WXAPIFactory() {
    }

    public static IWXAPI createWXAPI(Context context, String str) {
        if (f11795Q == null) {
            f11795Q = new WXAPIFactory();
        }
        return new WXApiImplV10(context, str);
    }

    public static IWXAPI createWXAPI(Context context, String str, boolean z) {
        if (f11795Q == null) {
            f11795Q = new WXAPIFactory();
        }
        return new WXApiImplV10(context, str, z);
    }
}
