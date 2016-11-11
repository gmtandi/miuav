package p000a;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/* renamed from: a.ai */
class ai extends WebViewClient {
    final /* synthetic */ ah f32a;
    private boolean f33b;

    ai(ah ahVar) {
        this.f32a = ahVar;
        this.f33b = false;
    }

    private void m25a(WebView webView) {
        if (!this.f33b) {
            this.f33b = true;
            webView.loadUrl("javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())");
        }
    }

    public void onLoadResource(WebView webView, String str) {
        super.onLoadResource(webView, str);
        m25a(webView);
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        m25a(webView);
    }
}
