package cn.sharesdk.sina.weibo;

import android.os.Bundle;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.utils.C0205d;
import com.mob.tools.utils.Hashon;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.util.HashMap;

/* renamed from: cn.sharesdk.sina.weibo.e */
class C0215e extends Thread {
    final /* synthetic */ Platform f399a;
    final /* synthetic */ String f400b;
    final /* synthetic */ C0214d f401c;

    C0215e(C0214d c0214d, Platform platform, String str) {
        this.f401c = c0214d;
        this.f399a = platform;
        this.f400b = str;
    }

    public void run() {
        try {
            String a = C0217g.m803a(this.f399a).m810a(this.f399a.getContext(), this.f400b);
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
            return;
        }
        if (a == null) {
            this.f401c.c.onError(new Throwable("Authorize token is empty"));
            return;
        }
        HashMap fromJson = new Hashon().fromJson(a);
        Bundle bundle = new Bundle();
        bundle.putString("uid", String.valueOf(fromJson.get("uid")));
        bundle.putString("remind_in", String.valueOf(fromJson.get("remind_in")));
        bundle.putString(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2, String.valueOf(fromJson.get(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2)));
        bundle.putString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, String.valueOf(fromJson.get(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2)));
        this.f401c.c.onComplete(bundle);
    }
}
