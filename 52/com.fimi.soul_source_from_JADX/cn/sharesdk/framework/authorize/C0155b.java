package cn.sharesdk.framework.authorize;

import android.webkit.WebView;
import cn.sharesdk.framework.C0154i;

/* renamed from: cn.sharesdk.framework.authorize.b */
public abstract class C0155b extends C0154i {
    protected C0161g f199a;
    protected String f200b;
    protected AuthorizeListener f201c;

    public C0155b(C0161g c0161g) {
        this.f199a = c0161g;
        AuthorizeHelper a = c0161g.m446a();
        this.f200b = a.getRedirectUri();
        this.f201c = a.getAuthorizeListener();
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        webView.stopLoading();
        AuthorizeListener authorizeListener = this.f199a.m446a().getAuthorizeListener();
        this.f199a.finish();
        if (authorizeListener != null) {
            authorizeListener.onError(new Throwable(str + " (" + i + "): " + str2));
        }
    }
}
