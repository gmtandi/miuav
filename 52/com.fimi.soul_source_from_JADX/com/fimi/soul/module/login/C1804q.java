package com.fimi.soul.module.login;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/* renamed from: com.fimi.soul.module.login.q */
class C1804q extends WebViewClient {
    final /* synthetic */ RegisterActivity f8860a;

    C1804q(RegisterActivity registerActivity) {
        this.f8860a = registerActivity;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        webView.loadUrl(str);
        return true;
    }
}
