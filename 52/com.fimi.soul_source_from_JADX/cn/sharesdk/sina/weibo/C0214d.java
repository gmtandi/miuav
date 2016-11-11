package cn.sharesdk.sina.weibo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.C0155b;
import cn.sharesdk.framework.authorize.C0161g;
import cn.sharesdk.framework.utils.C0205d;
import com.mob.tools.utils.C2178R;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import org.p122a.p123a.C2915a;

/* renamed from: cn.sharesdk.sina.weibo.d */
public class C0214d extends C0155b {
    private boolean f398d;

    public C0214d(C0161g c0161g) {
        super(c0161g);
    }

    private void m790a(Platform platform, String str) {
        new C0215e(this, platform, str).start();
    }

    private Intent m791b(String str) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
        intent.putExtra("sms_body", C2915a.f14760f);
        intent.setFlags(268435456);
        return intent;
    }

    protected void m794a(String str) {
        if (!this.f398d) {
            this.f398d = true;
            Bundle urlToBundle = C2178R.urlToBundle(str);
            String string = urlToBundle.getString(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2);
            String string2 = urlToBundle.getString("error_code");
            if (this.c == null) {
                return;
            }
            if (string == null && string2 == null) {
                Object string3 = urlToBundle.getString(XiaomiOAuthConstants.EXTRA_CODE_2);
                if (TextUtils.isEmpty(string3)) {
                    this.c.onError(new Throwable("Authorize code is empty"));
                }
                m790a(this.a.m446a().getPlatform(), string3);
            } else if (string.equals("access_denied")) {
                this.c.onCancel();
            } else {
                int i = 0;
                try {
                    i = C2178R.parseInt(string2);
                } catch (Throwable th) {
                    C0205d.m752a().m738d(th);
                }
                this.c.onError(new Throwable(string + " (" + i + ")"));
            }
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (!TextUtils.isEmpty(this.b) && str.startsWith(this.b)) {
            webView.stopLoading();
            this.a.finish();
            m794a(str);
        } else if (str.startsWith("sms:")) {
            String substring = str.substring(4);
            try {
                Intent b = m791b(substring);
                b.setPackage("com.android.mms");
                webView.getContext().startActivity(b);
            } catch (Throwable th) {
                if (this.c != null) {
                    this.c.onError(th);
                }
            }
        } else {
            super.onPageStarted(webView, str, bitmap);
        }
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!TextUtils.isEmpty(this.b) && str.startsWith(this.b)) {
            webView.stopLoading();
            this.a.finish();
            m794a(str);
            return true;
        } else if (!str.startsWith("sms:")) {
            return super.shouldOverrideUrlLoading(webView, str);
        } else {
            String substring = str.substring(4);
            try {
                Intent b = m791b(substring);
                b.setPackage("com.android.mms");
                webView.getContext().startActivity(b);
                return true;
            } catch (Throwable th) {
                if (this.c == null) {
                    return true;
                }
                this.c.onError(th);
                return true;
            }
        }
    }
}
