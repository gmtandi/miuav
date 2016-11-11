package com.xiaomi.account.openauth;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tencent.connect.common.Constants;
import com.xiaomi.account.IXiaomiAuthResponse;
import com.xiaomi.account.XiaomiOAuthResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.p122a.p123a.C2915a;

public abstract class AuthorizeActivityBase extends Activity {
    private static final String AUTHORIZE_PATH;
    private static final String KEY_KEEP_COOKIES = "extra_keep_cookies ";
    private static final String KEY_MY_BUNDLE = "extra_my_bundle";
    private static final String KEY_MY_INTENT = "extra_my_intent";
    public static final String KEY_REDIRECT_URI = "redirect_uri";
    private static final String KEY_RESPONSE = "extra_response";
    private static final String KEY_RESULT_CODE = "extra_result_code";
    private static final String LOCALE_KEY_IN_URL = "_locale";
    private static final int REQUEST_CODE = 1001;
    public static int RESULT_CANCEL = 0;
    public static int RESULT_FAIL = 0;
    public static int RESULT_SUCCESS = 0;
    private static final String UTF8 = "UTF-8";
    private boolean mKeepCookies;
    private boolean mMiddleActivityMode;
    private XiaomiOAuthResponse mResponse;
    private WebSettings mSettings;
    private String mUrl;
    private WebView mWebView;

    /* renamed from: com.xiaomi.account.openauth.AuthorizeActivityBase.1 */
    class C24471 extends WebChromeClient {
        C24471() {
        }

        public void onProgressChanged(WebView webView, int i) {
            AuthorizeActivityBase.this.onUpdateProgress(i);
        }
    }

    /* renamed from: com.xiaomi.account.openauth.AuthorizeActivityBase.2 */
    class C24482 implements Runnable {
        C24482() {
        }

        public void run() {
            AuthorizeActivityBase.this.onHideErrorUI();
        }
    }

    class AuthorizeWebViewClient extends WebViewClient {
        private final String mRedirectUrlOf3rdPartyApp;

        AuthorizeWebViewClient(String str) {
            this.mRedirectUrlOf3rdPartyApp = str;
        }

        public void onPageFinished(WebView webView, String str) {
            AuthorizeActivityBase.this.onHideProgress();
            super.onPageFinished(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            AuthorizeActivityBase.this.onShowProgress();
            super.onPageStarted(webView, str, bitmap);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            AuthorizeActivityBase.this.onShowErrorUI();
            super.onReceivedError(webView, i, str, str2);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            AuthorizeActivityBase.this.onShowErrorUI();
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (this.mRedirectUrlOf3rdPartyApp != null && !str.toLowerCase().startsWith(this.mRedirectUrlOf3rdPartyApp.toLowerCase())) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            String str2 = new String(str);
            int indexOf = str2.indexOf(63);
            String substring;
            if (indexOf > 0) {
                substring = str2.substring(indexOf + 1);
                if (substring.startsWith("code=") || substring.contains("&code=")) {
                    AuthorizeActivityBase.this.setResultAndFinish(AuthorizeActivityBase.RESULT_SUCCESS, str2);
                    return true;
                } else if (substring.startsWith("error=") || substring.contains("&error=")) {
                    AuthorizeActivityBase.this.setResultAndFinish(AuthorizeActivityBase.RESULT_FAIL, str2);
                    return true;
                }
            }
            indexOf = str2.indexOf(35);
            if (indexOf > 0) {
                substring = str2.substring(indexOf + 1);
                if (substring.startsWith("access_token=") || substring.contains("&access_token=")) {
                    AuthorizeActivityBase.this.setResultAndFinish(AuthorizeActivityBase.RESULT_SUCCESS, str2.replace("#", "?"));
                    return true;
                } else if (substring.startsWith("error=") || substring.contains("&error=")) {
                    AuthorizeActivityBase.this.setResultAndFinish(AuthorizeActivityBase.RESULT_FAIL, str2.replace("#", "?"));
                    return true;
                }
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    static {
        RESULT_SUCCESS = -1;
        RESULT_FAIL = 1;
        RESULT_CANCEL = 0;
        AUTHORIZE_PATH = AuthorizeHelper.OAUTH2_HOST + "/oauth2/authorize";
    }

    public AuthorizeActivityBase() {
        this.mKeepCookies = false;
        this.mMiddleActivityMode = false;
    }

    private Bundle addLocaleIfNeeded(Bundle bundle) {
        if (!(bundle == null || bundle.containsKey(LOCALE_KEY_IN_URL))) {
            Object localeString = getLocaleString(Locale.getDefault());
            if (!TextUtils.isEmpty(localeString)) {
                bundle.putString(LOCALE_KEY_IN_URL, localeString);
            }
        }
        return bundle;
    }

    private void appendPassportUserAgent() {
        if (!TextUtils.isEmpty(this.mSettings.getUserAgentString())) {
            this.mSettings.setUserAgentString(String.format("%s Passport/OAuthSDK/%d.%d", new Object[]{this.mSettings.getUserAgentString(), Integer.valueOf(1), Integer.valueOf(4)}));
        }
    }

    public static Intent asMiddleActivity(Activity activity, int i, Bundle bundle, Class<? extends AuthorizeActivityBase> cls) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra(KEY_MY_BUNDLE, bundle);
        intent.putExtra(KEY_RESULT_CODE, i);
        return intent;
    }

    public static Intent asMiddleActivity(Activity activity, Intent intent, IXiaomiAuthResponse iXiaomiAuthResponse, Class<? extends AuthorizeActivityBase> cls) {
        Intent intent2 = new Intent(activity, cls);
        intent2.putExtra(KEY_MY_INTENT, intent);
        intent2.putExtra(KEY_RESPONSE, new XiaomiOAuthResponse(iXiaomiAuthResponse));
        return intent2;
    }

    public static Intent getIntent(Activity activity, String str, String str2, String str3, String str4, String str5, Boolean bool, boolean z, IXiaomiAuthResponse iXiaomiAuthResponse, Class<? extends AuthorizeActivityBase> cls) {
        Intent intent = new Intent(activity, cls);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.PARAM_CLIENT_ID, String.valueOf(str));
        bundle.putString(KEY_REDIRECT_URI, str2);
        bundle.putString("response_type", str3);
        bundle.putString(XiaomiOAuthConstants.EXTRA_SCOPE_2, str4);
        bundle.putString(XiaomiOAuthConstants.EXTRA_STATE_2, str5);
        if (bool != null) {
            bundle.putString("skip_confirm", String.valueOf(bool));
        }
        intent.putExtra("url_param", bundle);
        intent.putExtra(KEY_KEEP_COOKIES, z);
        intent.putExtra(KEY_RESPONSE, new XiaomiOAuthResponse(iXiaomiAuthResponse));
        return intent;
    }

    private static String getLocaleString(Locale locale) {
        if (locale == null) {
            return null;
        }
        String language = locale.getLanguage();
        if (TextUtils.isEmpty(locale.getCountry())) {
            return language;
        }
        return String.format("%s_%s", new Object[]{language, locale.getCountry()});
    }

    private String parseBundle(Bundle bundle) {
        if (bundle == null) {
            return C2915a.f14760f;
        }
        List arrayList = new ArrayList();
        for (String str : bundle.keySet()) {
            Object string = bundle.getString(str);
            if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(string))) {
                arrayList.add(new BasicNameValuePair(str, string));
            }
        }
        return URLEncodedUtils.format(arrayList, UTF8);
    }

    private Bundle parseUrl(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            try {
                for (NameValuePair nameValuePair : URLEncodedUtils.parse(new URI(str), UTF8)) {
                    if (!(TextUtils.isEmpty(nameValuePair.getName()) || TextUtils.isEmpty(nameValuePair.getValue()))) {
                        bundle.putString(nameValuePair.getName(), nameValuePair.getValue());
                    }
                }
            } catch (URISyntaxException e) {
                Log.e("openauth", e.getMessage());
            }
        }
        return bundle;
    }

    private void removeCookiesIfNeeded() {
        if (!this.mKeepCookies) {
            CookieSyncManager.createInstance(this);
            CookieManager.getInstance().removeAllCookie();
        }
    }

    protected final WebView getWebView() {
        return this.mWebView;
    }

    protected final boolean isMiddleActivityMode() {
        return this.mMiddleActivityMode;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == REQUEST_CODE) {
            setResultAndFinish(i2, intent != null ? intent.getExtras() : null);
        }
    }

    public void onBackPressed() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        } else {
            setResultAndFinish(RESULT_CANCEL, (Bundle) null);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (new ParcelableAttackGuardian().safeCheck(this)) {
            Intent intent = getIntent();
            Bundle bundleExtra = intent.getBundleExtra(KEY_MY_BUNDLE);
            if (bundleExtra != null) {
                setResultAndFinish(intent.getIntExtra(KEY_RESULT_CODE, -1), bundleExtra);
                return;
            }
            this.mResponse = (XiaomiOAuthResponse) intent.getParcelableExtra(KEY_RESPONSE);
            Intent intent2 = (Intent) intent.getParcelableExtra(KEY_MY_INTENT);
            if (intent2 != null) {
                startActivityForResult(intent2, REQUEST_CODE);
                this.mMiddleActivityMode = true;
                return;
            }
            this.mKeepCookies = intent.getBooleanExtra(KEY_KEEP_COOKIES, false);
            this.mWebView = new WebView(this);
            this.mSettings = this.mWebView.getSettings();
            this.mSettings.setJavaScriptEnabled(true);
            this.mSettings.setSavePassword(false);
            this.mSettings.setSaveFormData(false);
            bundleExtra = addLocaleIfNeeded(intent.getBundleExtra("url_param"));
            this.mUrl = AUTHORIZE_PATH + "?" + parseBundle(bundleExtra);
            removeCookiesIfNeeded();
            appendPassportUserAgent();
            refreshWebView(false);
            this.mWebView.setWebViewClient(new AuthorizeWebViewClient(bundleExtra.getString(KEY_REDIRECT_URI)));
            this.mWebView.setWebChromeClient(new C24471());
            return;
        }
        finish();
    }

    protected abstract void onHideErrorUI();

    protected abstract void onHideProgress();

    protected abstract void onShowErrorUI();

    protected abstract void onShowProgress();

    protected abstract void onUpdateProgress(int i);

    protected final void refreshWebView() {
        refreshWebView(true);
    }

    protected final void refreshWebView(boolean z) {
        this.mWebView.loadUrl(this.mUrl);
        if (z) {
            onHideErrorUI();
        } else {
            new Handler(Looper.getMainLooper()).post(new C24482());
        }
    }

    void setResultAndFinish(int i, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        setResult(i, intent);
        if (this.mResponse != null) {
            if (i == 0) {
                this.mResponse.onCancel();
            } else {
                this.mResponse.onResult(bundle);
            }
        }
        removeCookiesIfNeeded();
        finish();
    }

    void setResultAndFinish(int i, String str) {
        setResultAndFinish(i, parseUrl(str));
    }
}
