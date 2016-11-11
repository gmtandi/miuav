package com.xiaomi.market.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

/* renamed from: com.xiaomi.market.sdk.u */
class C2548u implements OnClickListener {
    C2548u() {
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (!C2546s.m14551l(XiaomiUpdateAgent.mContext) || XiaomiUpdateAgent.aK.bj == 1) {
            C2540m.m14532i(XiaomiUpdateAgent.mContext).m14534a(XiaomiUpdateAgent.aL, XiaomiUpdateAgent.aK);
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + XiaomiUpdateAgent.aL.packageName));
        intent.setClassName("com.xiaomi.market", "com.xiaomi.market.ui.AppDetailActivity");
        XiaomiUpdateAgent.mContext.startActivity(intent);
    }
}
