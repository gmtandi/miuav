package com.fimi.soul.module.login;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import com.fimi.soul.C1205R;

public class RegisterActivity extends Activity {
    private WebView f8832a;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.activity_register);
        this.f8832a = (WebView) findViewById(C1205R.id.web_view);
        this.f8832a.getSettings().setJavaScriptEnabled(true);
        this.f8832a.setWebViewClient(new C1804q(this));
        this.f8832a.loadUrl("https://account.xiaomi.com/pass/register");
    }
}
