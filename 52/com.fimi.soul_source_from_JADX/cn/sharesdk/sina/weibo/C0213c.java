package cn.sharesdk.sina.weibo;

import android.os.Bundle;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import com.mob.tools.utils.C2178R;
import com.tencent.mm.sdk.contact.RContact;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;

/* renamed from: cn.sharesdk.sina.weibo.c */
class C0213c implements AuthorizeListener {
    final /* synthetic */ C0217g f396a;
    final /* synthetic */ SinaWeibo f397b;

    C0213c(SinaWeibo sinaWeibo, C0217g c0217g) {
        this.f397b = sinaWeibo;
        this.f396a = c0217g;
    }

    public void onCancel() {
        if (this.f397b.listener != null) {
            this.f397b.listener.onCancel(this.f397b, 1);
        }
    }

    public void onComplete(Bundle bundle) {
        long parseLong;
        String string = bundle.getString("uid");
        String string2 = bundle.getString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2);
        String string3 = bundle.getString(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2);
        this.f397b.db.put(RContact.COL_NICKNAME, bundle.getString("userName"));
        this.f397b.db.put("remind_in", bundle.getString("remind_in"));
        this.f397b.db.putToken(string2);
        try {
            parseLong = C2178R.parseLong(string3);
        } catch (Throwable th) {
            parseLong = 0;
        }
        this.f397b.db.putExpiresIn(parseLong);
        this.f397b.db.putUserId(string);
        this.f396a.m821b(string2);
        this.f397b.afterRegister(1, null);
    }

    public void onError(Throwable th) {
        if (this.f397b.listener != null) {
            this.f397b.listener.onError(this.f397b, 1, th);
        }
    }
}
