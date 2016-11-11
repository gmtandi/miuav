package com.fimi.soul.utils;

import android.app.Activity;
import android.content.Context;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p103k.C1388l;
import com.fimi.soul.biz.p103k.ba;
import com.fimi.soul.entity.User;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.account.openauth.XiaomiOAuthFuture;
import com.xiaomi.account.openauth.XiaomiOAuthResults;
import com.xiaomi.account.openauth.XiaomiOAuthorize;
import java.util.Arrays;

public class am implements as {
    private static final String f10029a = "http://dev.xiaomi.com";
    private XiaomiOAuthResults f10030b;
    private User f10031c;
    private ba f10032d;
    private Context f10033e;
    private at f10034f;
    private String f10035g;

    public am() {
        this.f10031c = new User();
    }

    private void m12268a() {
        C1388l.m9322a(this.f10033e).m9326a(new ao(this));
        new ap(this, new XiaomiOAuthorize().callOpenApi(this.f10033e, Long.parseLong(C1236a.f5628z), XiaomiOAuthConstants.OPEN_API_PATH_PHONE, this.f10030b.getAccessToken(), this.f10030b.getMacKey(), this.f10030b.getMacAlgorithm())).execute(new Void[0]);
        new aq(this, new XiaomiOAuthorize().callOpenApi(this.f10033e, Long.parseLong(C1236a.f5628z), XiaomiOAuthConstants.OPEN_API_PATH_PROFILE, this.f10030b.getAccessToken(), this.f10030b.getMacKey(), this.f10030b.getMacAlgorithm())).execute(new Void[0]);
    }

    private <V> void m12269a(XiaomiOAuthFuture<V> xiaomiOAuthFuture) {
        new an(this, xiaomiOAuthFuture).execute(new Void[0]);
    }

    public void m12276a(Context context, at atVar) {
        this.f10033e = context;
        this.f10034f = atVar;
        this.f10032d = ba.m9259a(context);
        m12269a(new XiaomiOAuthorize().setAppId(Long.parseLong(C1236a.f5628z)).setRedirectUrl(f10029a).setScope(Arrays.copyOf(new int[4], 0)).setKeepCookies(true).setNoMiui(false).setSkipConfirm(false).startGetAccessToken((Activity) context));
    }
}
