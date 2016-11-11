package cn.sharesdk.framework;

import cn.sharesdk.framework.authorize.AuthorizeHelper;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.C0158e;
import cn.sharesdk.framework.authorize.C0159f;
import cn.sharesdk.framework.authorize.C0161g;
import cn.sharesdk.framework.authorize.SSOListener;

/* renamed from: cn.sharesdk.framework.e */
public abstract class C0185e implements AuthorizeHelper {
    protected Platform f312a;
    private AuthorizeListener f313b;
    private SSOListener f314c;

    public C0185e(Platform platform) {
        this.f312a = platform;
    }

    protected void m624a(SSOListener sSOListener) {
        this.f314c = sSOListener;
        C0158e c0158e = new C0158e();
        c0158e.m448a(sSOListener);
        c0158e.m447a(this);
    }

    protected void m625b(AuthorizeListener authorizeListener) {
        this.f313b = authorizeListener;
        C0161g c0161g = new C0161g();
        c0161g.m456a(this.f313b);
        c0161g.m447a(this);
    }

    public int m626c() {
        return this.f312a.getPlatformId();
    }

    public AuthorizeListener getAuthorizeListener() {
        return this.f313b;
    }

    public Platform getPlatform() {
        return this.f312a;
    }

    public SSOListener getSSOListener() {
        return this.f314c;
    }

    public C0159f getSSOProcessor(C0158e c0158e) {
        return null;
    }
}
