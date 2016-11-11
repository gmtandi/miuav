package p000a;

import android.webkit.JavascriptInterface;
import org.json.JSONArray;

/* renamed from: a.aj */
class aj {
    final /* synthetic */ ae f34a;
    final /* synthetic */ ah f35b;

    aj(ah ahVar, ae aeVar) {
        this.f35b = ahVar;
        this.f34a = aeVar;
    }

    @JavascriptInterface
    public void m26a(String str) {
        try {
            this.f34a.m7a(new JSONArray(str));
        } catch (Exception e) {
            this.f34a.m6a(e);
        }
    }
}
