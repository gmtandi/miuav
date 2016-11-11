package cn.sharesdk.wechat.utils;

import android.text.TextUtils;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.utils.C0205d;
import com.mob.tools.network.KVPair;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.OAuth;
import com.tencent.open.SocialConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.util.ArrayList;

/* renamed from: cn.sharesdk.wechat.utils.h */
class C0233h extends Thread {
    final /* synthetic */ String f456a;
    final /* synthetic */ AuthorizeListener f457b;
    final /* synthetic */ C0232g f458c;

    C0233h(C0232g c0232g, String str, AuthorizeListener authorizeListener) {
        this.f458c = c0232g;
        this.f456a = str;
        this.f457b = authorizeListener;
    }

    public void run() {
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair(SocialConstants.PARAM_APP_ID, this.f458c.f451a));
            arrayList.add(new KVPair(OAuth.SECRET, this.f458c.f452b));
            arrayList.add(new KVPair(XiaomiOAuthConstants.EXTRA_CODE_2, this.f456a));
            arrayList.add(new KVPair("grant_type", "authorization_code"));
            String a = this.f458c.f453c.m417a("https://api.weixin.qq.com/sns/oauth2/access_token", arrayList, "/sns/oauth2/access_token", this.f458c.f455e);
            if (TextUtils.isEmpty(a)) {
                this.f457b.onError(new Throwable("Authorize token is empty"));
            } else if (!a.contains("errcode")) {
                this.f458c.m911a(a);
                this.f457b.onComplete(null);
            } else if (this.f457b != null) {
                this.f457b.onError(new Throwable(a));
            }
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
        }
    }
}
