package com.fimi.soul.module.setting;

import android.webkit.WebView;
import android.webkit.WebViewClient;

class au extends WebViewClient {
    final /* synthetic */ ShowTextActivity f9282a;

    au(ShowTextActivity showTextActivity) {
        this.f9282a = showTextActivity;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        webView.loadUrl(str);
        return true;
    }
}
