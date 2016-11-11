package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.p012a.C0151a;
import cn.sharesdk.framework.utils.C0205d;
import com.mob.tools.utils.C2178R;
import com.mob.tools.utils.Hashon;
import com.tencent.open.SocialConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.util.HashMap;

/* renamed from: cn.sharesdk.wechat.utils.g */
public class C0232g {
    private String f451a;
    private String f452b;
    private C0151a f453c;
    private Platform f454d;
    private int f455e;

    public C0232g(Platform platform, int i) {
        this.f454d = platform;
        this.f455e = i;
        this.f453c = C0151a.m412a();
    }

    private void m911a(String str) {
        C0205d.m752a().m737d("wechat getAuthorizeToken ==>>" + str, new Object[0]);
        HashMap fromJson = new Hashon().fromJson(str);
        String valueOf = String.valueOf(fromJson.get(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2));
        String valueOf2 = String.valueOf(fromJson.get("refresh_token"));
        String valueOf3 = String.valueOf(fromJson.get(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2));
        this.f454d.getDb().put(SocialConstants.PARAM_OPEN_ID, String.valueOf(fromJson.get(SocialConstants.PARAM_OPEN_ID)));
        this.f454d.getDb().putExpiresIn(Long.valueOf(valueOf3).longValue());
        this.f454d.getDb().putToken(valueOf);
        this.f454d.getDb().put("refresh_token", valueOf2);
    }

    public void m916a(Bundle bundle, AuthorizeListener authorizeListener) {
        String string = bundle.getString("_wxapi_sendauth_resp_url");
        if (!TextUtils.isEmpty(string)) {
            int indexOf = string.indexOf("://oauth?");
            if (indexOf >= 0) {
                string = string.substring(indexOf + 1);
            }
            try {
                m918a(C2178R.urlToBundle(string).getString(XiaomiOAuthConstants.EXTRA_CODE_2), authorizeListener);
            } catch (Throwable th) {
                C0205d.m752a().m738d(th);
                if (authorizeListener != null) {
                    authorizeListener.onError(th);
                }
            }
        } else if (authorizeListener != null) {
            authorizeListener.onError(null);
        }
    }

    public void m917a(PlatformActionListener platformActionListener) {
        new C0234i(this, platformActionListener).start();
    }

    public void m918a(String str, AuthorizeListener authorizeListener) {
        C0205d.m752a().m737d("getAuthorizeToken ==>> " + str, new Object[0]);
        new C0233h(this, str, authorizeListener).start();
    }

    public void m919a(String str, String str2) {
        this.f451a = str;
        this.f452b = str2;
    }
}
