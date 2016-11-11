package cn.sharesdk.framework.authorize;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout.LayoutParams;

/* renamed from: cn.sharesdk.framework.authorize.d */
class C0157d extends WebChromeClient {
    final /* synthetic */ int f203a;
    final /* synthetic */ RegisterView f204b;

    C0157d(RegisterView registerView, int i) {
        this.f204b = registerView;
        this.f203a = i;
    }

    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        LayoutParams layoutParams = (LayoutParams) this.f204b.f197d.getLayoutParams();
        layoutParams.width = (this.f203a * i) / 100;
        this.f204b.f197d.setLayoutParams(layoutParams);
        if (i <= 0 || i >= 100) {
            this.f204b.f197d.setVisibility(8);
        } else {
            this.f204b.f197d.setVisibility(0);
        }
    }
}
