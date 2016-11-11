package com.fimi.soul.module.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.utils.be;

public class ShowTextActivity extends BaseActivity {
    public static final String f9163a = "URL_TITLE";
    public static final String f9164b = "URL_LOCAL";
    public static final String f9165c = "URL_ONLINE";
    private WebView f9166d;
    private Button f9167e;
    private TextView f9168f;

    private void m11648a(Intent intent) {
        String stringExtra = intent.getStringExtra(f9165c);
        String stringExtra2 = intent.getStringExtra(f9164b);
        int intExtra = intent.getIntExtra(f9163a, 0);
        if (intExtra != 0) {
            this.f9168f.setText(intExtra);
        }
        this.f9167e = (Button) findViewById(C1205R.id.backBtn);
        this.f9167e.setOnClickListener(new at(this));
        this.f9166d = (WebView) findViewById(C1205R.id.web_view);
        this.f9166d.getSettings().setJavaScriptEnabled(true);
        this.f9166d.setWebViewClient(new au(this));
        if (be.m12370b((Context) this)) {
            this.f9166d.loadUrl(stringExtra);
        } else {
            this.f9166d.loadUrl(stringExtra2);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.activity_show_text);
        this.f9168f = (TextView) findViewById(C1205R.id.title);
        m11648a(getIntent());
    }
}
