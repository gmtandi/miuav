package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import com.mob.tools.utils.Hashon;
import com.tencent.mm.sdk.openapi.BaseResp.ErrCode;
import com.tencent.open.yyb.AppbarJsBridge;
import java.util.HashMap;
import org.codehaus.jackson.io.CharacterEscapes;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: cn.sharesdk.wechat.utils.k */
public class C0236k {
    private Platform f463a;
    private ShareParams f464b;
    private PlatformActionListener f465c;
    private AuthorizeListener f466d;
    private C0232g f467e;

    public C0236k(Platform platform) {
        this.f463a = platform;
    }

    public ShareParams m930a() {
        return this.f464b;
    }

    public void m931a(ShareParams shareParams, PlatformActionListener platformActionListener) {
        this.f464b = shareParams;
        this.f465c = platformActionListener;
    }

    public void m932a(AuthorizeListener authorizeListener) {
        this.f466d = authorizeListener;
    }

    public void m933a(WechatResp wechatResp) {
        HashMap hashMap;
        Throwable th;
        switch (wechatResp.f436f) {
            case ErrCode.ERR_AUTH_DENIED /*-4*/:
                hashMap = new HashMap();
                hashMap.put("errCode", Integer.valueOf(wechatResp.f436f));
                hashMap.put("errStr", wechatResp.f437g);
                hashMap.put("transaction", wechatResp.f438h);
                th = new Throwable(new Hashon().fromHashMap(hashMap));
                switch (wechatResp.m887a()) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        if (this.f466d != null) {
                            this.f466d.onError(th);
                        }
                    default:
                }
            case AppbarJsBridge.Code_Java_Exception /*-3*/:
                hashMap = new HashMap();
                hashMap.put("errCode", Integer.valueOf(wechatResp.f436f));
                hashMap.put("errStr", wechatResp.f437g);
                hashMap.put("transaction", wechatResp.f438h);
                th = new Throwable(new Hashon().fromHashMap(hashMap));
                switch (wechatResp.m887a()) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        if (this.f466d != null) {
                            this.f466d.onError(th);
                        }
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        if (this.f465c != null) {
                            this.f465c.onError(this.f463a, 9, th);
                        }
                    default:
                }
            case CharacterEscapes.ESCAPE_CUSTOM /*-2*/:
                switch (wechatResp.m887a()) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        if (this.f466d != null) {
                            this.f466d.onCancel();
                        }
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        if (this.f465c != null) {
                            this.f465c.onCancel(this.f463a, 9);
                        }
                    default:
                }
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                switch (wechatResp.m887a()) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        if (this.f466d != null) {
                            Bundle bundle = new Bundle();
                            wechatResp.m889b(bundle);
                            this.f467e.m916a(bundle, this.f466d);
                        }
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        if (this.f465c != null) {
                            hashMap = new HashMap();
                            hashMap.put("ShareParams", this.f464b);
                            this.f465c.onComplete(this.f463a, 9, hashMap);
                        }
                    default:
                }
            default:
                hashMap = new HashMap();
                hashMap.put("req", wechatResp.getClass().getSimpleName());
                hashMap.put("errCode", Integer.valueOf(wechatResp.f436f));
                hashMap.put("errStr", wechatResp.f437g);
                hashMap.put("transaction", wechatResp.f438h);
                new Throwable(new Hashon().fromHashMap(hashMap)).printStackTrace();
        }
    }

    public void m934a(C0232g c0232g) {
        this.f467e = c0232g;
    }

    public Platform m935b() {
        return this.f463a;
    }

    public PlatformActionListener m936c() {
        return this.f465c;
    }
}
