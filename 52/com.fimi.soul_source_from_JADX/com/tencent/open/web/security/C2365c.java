package com.tencent.open.web.security;

import android.webkit.WebView;
import com.facebook.common.util.UriUtil;
import com.tencent.open.C2336a.C2318a;
import com.tencent.open.p132c.C2353c;
import com.tencent.open.p133a.C2333f;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tencent.open.web.security.c */
public class C2365c extends C2318a {
    private static final String f12134d;
    private String f12135e;

    static {
        f12134d = C2333f.f12014d + ".SL";
    }

    public C2365c(WebView webView, long j, String str, String str2) {
        super(webView, j, str);
        this.f12135e = str2;
    }

    private void m13854b(String str) {
        WebView webView = (WebView) this.a.get();
        if (webView != null) {
            StringBuffer stringBuffer = new StringBuffer("javascript:");
            stringBuffer.append("if(!!").append(this.f12135e).append("){");
            stringBuffer.append(this.f12135e);
            stringBuffer.append("(");
            stringBuffer.append(str);
            stringBuffer.append(")}");
            String stringBuffer2 = stringBuffer.toString();
            C2333f.m13754b(C2333f.f12014d, "-->callback, callback: " + stringBuffer2);
            webView.loadUrl(stringBuffer2);
        }
    }

    public void m13855a() {
        C2333f.m13754b(f12134d, "-->onNoMatchMethod...");
    }

    public void m13856a(Object obj) {
        C2333f.m13754b(f12134d, "-->onComplete, result: " + obj);
    }

    public void m13857a(String str) {
        C2333f.m13754b(f12134d, "-->onCustomCallback, js: " + str);
        JSONObject jSONObject = new JSONObject();
        int i = 0;
        if (!C2353c.f12064a) {
            i = -4;
        }
        try {
            jSONObject.put("result", i);
            jSONObject.put("sn", this.b);
            jSONObject.put(UriUtil.DATA_SCHEME, str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        m13854b(jSONObject.toString());
    }
}
