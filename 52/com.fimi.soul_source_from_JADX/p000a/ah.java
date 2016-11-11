package p000a;

import android.net.Uri;
import android.webkit.WebView;
import org.json.JSONArray;

/* renamed from: a.ah */
class ah implements C0001q<Void, C0018s<JSONArray>> {
    final /* synthetic */ C0016p f28a;
    final /* synthetic */ Uri f29b;
    final /* synthetic */ C0016p f30c;
    final /* synthetic */ af f31d;

    ah(af afVar, C0016p c0016p, Uri uri, C0016p c0016p2) {
        this.f31d = afVar;
        this.f28a = c0016p;
        this.f29b = uri;
        this.f30c = c0016p2;
    }

    public C0018s<JSONArray> m24a(C0018s<Void> c0018s) {
        ae a = C0018s.m75a();
        WebView webView = new WebView(this.f31d.f25a);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setNetworkAvailable(false);
        webView.setWebViewClient(new ai(this));
        webView.addJavascriptInterface(new aj(this, a), "boltsWebViewAppLinkResolverResult");
        webView.loadDataWithBaseURL(this.f29b.toString(), (String) this.f30c.m69a(), this.f28a.m69a() != null ? ((String) this.f28a.m69a()).split(";")[0] : null, null, null);
        return a.m5a();
    }

    public /* synthetic */ Object then(C0018s c0018s) {
        return m24a(c0018s);
    }
}
