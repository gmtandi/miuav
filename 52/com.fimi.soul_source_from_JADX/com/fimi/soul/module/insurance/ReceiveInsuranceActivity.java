package com.fimi.soul.module.insurance;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.fimi.soul.C1205R;

public class ReceiveInsuranceActivity extends Activity {
    public static String f8720a;
    private WebView f8721b;

    static {
        f8720a = "https://api.jr.mi.com/insurance/uav.html?from=uav";
    }

    private void m11464a() {
        this.f8721b = (WebView) findViewById(C1205R.id.webView);
        String str = "http://baidu.com";
        WebSettings settings = this.f8721b.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        this.f8721b.setWebViewClient(new C1786a(this));
        this.f8721b.loadUrl("http://staging.mifi.pt.xiaomi.com/insurance/uav.html");
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.activity_receive_insurance);
        m11464a();
    }
}
