package com.fimi.soul.module.insurance;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/* renamed from: com.fimi.soul.module.insurance.a */
class C1786a extends WebViewClient {
    final /* synthetic */ ReceiveInsuranceActivity f8722a;

    C1786a(ReceiveInsuranceActivity receiveInsuranceActivity) {
        this.f8722a = receiveInsuranceActivity;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        webView.loadUrl(str);
        return true;
    }
}
