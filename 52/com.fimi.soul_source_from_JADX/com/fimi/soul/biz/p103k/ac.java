package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.os.Handler;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.ShareSDK;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.entity.ShareInfo;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;

/* renamed from: com.fimi.soul.biz.k.ac */
public class ac {
    public static int f6028a = 0;
    public static int f6029b = 0;
    public static final int f6030c = 1;
    public static final int f6031d = 2;
    public static final int f6032e = 3;
    public IUiListener f6033f;
    private Context f6034g;
    private IWXAPI f6035h;
    private ShareInfo f6036i;
    private al f6037j;
    private Handler f6038k;

    static {
        f6028a = 0;
        f6029b = f6030c;
    }

    public ac(Context context) {
        this.f6038k = new ae(this);
        this.f6033f = new af(this);
        this.f6034g = context;
        this.f6035h = WXAPIFactory.createWXAPI(context, C1236a.f5622t);
        this.f6035h.registerApp(C1236a.f5622t);
    }

    public void m9197a(al alVar) {
        this.f6037j = alVar;
    }

    public void m9198a(ShareInfo shareInfo) {
        this.f6036i = shareInfo;
        new ag(this, this.f6034g, null).show();
    }

    public void m9199a(ShareInfo shareInfo, String str) {
        ShareParams shareParams = new ShareParams();
        shareParams.setImagePath(shareInfo.getUrl());
        shareParams.setShareType(f6031d);
        Platform platform = ShareSDK.getPlatform(str);
        platform.setPlatformActionListener(new ad(this));
        platform.share(shareParams);
    }
}
