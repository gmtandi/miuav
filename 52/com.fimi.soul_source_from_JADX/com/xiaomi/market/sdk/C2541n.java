package com.xiaomi.market.sdk;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.io.File;

/* renamed from: com.xiaomi.market.sdk.n */
public class C2541n extends Handler {
    final /* synthetic */ C2540m aR;

    public C2541n(C2540m c2540m, Looper looper) {
        this.aR = c2540m;
        super(looper);
    }

    private String m14538e(String str, String str2) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.equals(C2529b.m14486a(new File(str)), str2)) {
            return null;
        }
        String absolutePath = this.aR.aN.getAbsolutePath();
        if (this.aR.aL == null || TextUtils.isEmpty(this.aR.aL.sourceDir)) {
            return null;
        }
        Patcher.m14460a(this.aR.aL.sourceDir, absolutePath, str);
        return absolutePath;
    }

    public void m14539l() {
        post(new C2542o(this));
    }

    public void m14540m() {
        post(new C2543p(this));
    }
}
