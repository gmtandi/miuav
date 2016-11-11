package com.tencent.connect.auth;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.connect.auth.AuthMap.Auth;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.p132c.C2353c;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.p134b.C2350g;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.Util;
import com.tencent.open.web.security.C2364b;
import com.tencent.open.web.security.JniInterface;
import com.tencent.open.web.security.SecureJsInterface;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

public class AuthDialog extends Dialog {
    private static final String f11398a;
    private String f11399b;
    private OnTimeListener f11400c;
    private IUiListener f11401d;
    private Handler f11402e;
    private FrameLayout f11403f;
    private LinearLayout f11404g;
    private FrameLayout f11405h;
    private ProgressBar f11406i;
    private String f11407j;
    private C2353c f11408k;
    private Context f11409l;
    private C2364b f11410m;
    private boolean f11411n;
    private int f11412o;
    private String f11413p;
    private String f11414q;
    private long f11415r;
    private long f11416s;
    private HashMap<String, Runnable> f11417t;

    /* renamed from: com.tencent.connect.auth.AuthDialog.1 */
    class C21931 implements OnLongClickListener {
        final /* synthetic */ AuthDialog f11384a;

        C21931(AuthDialog authDialog) {
            this.f11384a = authDialog;
        }

        public boolean onLongClick(View view) {
            return true;
        }
    }

    /* renamed from: com.tencent.connect.auth.AuthDialog.2 */
    class C21942 implements OnTouchListener {
        final /* synthetic */ AuthDialog f11385a;

        C21942(AuthDialog authDialog) {
            this.f11385a = authDialog;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (!view.hasFocus()) {
                        view.requestFocus();
                        break;
                    }
                    break;
            }
            return false;
        }
    }

    /* renamed from: com.tencent.connect.auth.AuthDialog.3 */
    class C21953 implements OnDismissListener {
        final /* synthetic */ AuthDialog f11386a;

        C21953(AuthDialog authDialog) {
            this.f11386a = authDialog;
        }

        public void onDismiss(DialogInterface dialogInterface) {
            try {
                JniInterface.clearAllPWD();
            } catch (Exception e) {
            }
        }
    }

    class LoginWebViewClient extends WebViewClient {
        final /* synthetic */ AuthDialog f11388a;

        /* renamed from: com.tencent.connect.auth.AuthDialog.LoginWebViewClient.1 */
        class C21961 implements Runnable {
            final /* synthetic */ LoginWebViewClient f11387a;

            C21961(LoginWebViewClient loginWebViewClient) {
                this.f11387a = loginWebViewClient;
            }

            public void run() {
                this.f11387a.f11388a.f11408k.loadUrl(this.f11387a.f11388a.f11413p);
            }
        }

        private LoginWebViewClient(AuthDialog authDialog) {
            this.f11388a = authDialog;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            C2333f.m13754b(AuthDialog.f11398a, "-->onPageFinished, url: " + str);
            this.f11388a.f11405h.setVisibility(8);
            if (this.f11388a.f11408k != null) {
                this.f11388a.f11408k.setVisibility(0);
            }
            if (!TextUtils.isEmpty(str)) {
                this.f11388a.f11402e.removeCallbacks((Runnable) this.f11388a.f11417t.remove(str));
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            C2333f.m13754b(AuthDialog.f11398a, "-->onPageStarted, url: " + str);
            super.onPageStarted(webView, str, bitmap);
            this.f11388a.f11405h.setVisibility(0);
            this.f11388a.f11415r = SystemClock.elapsedRealtime();
            if (!TextUtils.isEmpty(this.f11388a.f11413p)) {
                this.f11388a.f11402e.removeCallbacks((Runnable) this.f11388a.f11417t.remove(this.f11388a.f11413p));
            }
            this.f11388a.f11413p = str;
            Runnable timeOutRunable = new TimeOutRunable(this.f11388a, this.f11388a.f11413p);
            this.f11388a.f11417t.put(str, timeOutRunable);
            this.f11388a.f11402e.postDelayed(timeOutRunable, 120000);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            C2333f.m13757c(AuthDialog.f11398a, "-->onReceivedError, errorCode: " + i + " | description: " + str);
            if (!Util.checkNetWork(this.f11388a.f11409l)) {
                this.f11388a.f11400c.onError(new UiError(9001, "\u5f53\u524d\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5\uff01", str2));
                this.f11388a.dismiss();
            } else if (this.f11388a.f11413p.startsWith(ServerSetting.DOWNLOAD_QQ_URL)) {
                this.f11388a.f11400c.onError(new UiError(i, str, str2));
                this.f11388a.dismiss();
            } else {
                long elapsedRealtime = SystemClock.elapsedRealtime() - this.f11388a.f11415r;
                if (this.f11388a.f11412o >= 1 || elapsedRealtime >= this.f11388a.f11416s) {
                    this.f11388a.f11408k.loadUrl(this.f11388a.m13247b());
                    return;
                }
                this.f11388a.f11412o = this.f11388a.f11412o + 1;
                this.f11388a.f11402e.postDelayed(new C21961(this), 500);
            }
        }

        @TargetApi(8)
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.cancel();
            this.f11388a.f11400c.onError(new UiError(sslError.getPrimaryError(), "\u8bf7\u6c42\u4e0d\u5408\u6cd5\uff0c\u8bf7\u68c0\u67e5\u624b\u673a\u5b89\u5168\u8bbe\u7f6e\uff0c\u5982\u7cfb\u7edf\u65f6\u95f4\u3001\u4ee3\u7406\u7b49\u3002", "ssl error"));
            this.f11388a.dismiss();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            C2333f.m13754b(AuthDialog.f11398a, "-->Redirect URL: " + str);
            if (str.startsWith(AuthConstants.REDIRECT_BROWSER_URI)) {
                JSONObject parseUrlToJson = Util.parseUrlToJson(str);
                this.f11388a.f11411n = this.f11388a.m13259f();
                if (!this.f11388a.f11411n) {
                    if (parseUrlToJson.optString("fail_cb", null) != null) {
                        this.f11388a.callJs(parseUrlToJson.optString("fail_cb"), C2915a.f14760f);
                    } else if (parseUrlToJson.optInt("fall_to_wv") == 1) {
                        AuthDialog.m13242a(this.f11388a, this.f11388a.f11399b.indexOf("?") > -1 ? "&" : "?");
                        AuthDialog.m13242a(this.f11388a, (Object) "browser_error=1");
                        this.f11388a.f11408k.loadUrl(this.f11388a.f11399b);
                    } else {
                        String optString = parseUrlToJson.optString("redir", null);
                        if (optString != null) {
                            this.f11388a.f11408k.loadUrl(optString);
                        }
                    }
                }
                return true;
            } else if (str.startsWith(ServerSetting.DEFAULT_REDIRECT_URI)) {
                this.f11388a.f11400c.onComplete(Util.parseUrlToJson(str));
                this.f11388a.dismiss();
                return true;
            } else if (str.startsWith(Constants.CANCEL_URI)) {
                this.f11388a.f11400c.onCancel();
                this.f11388a.dismiss();
                return true;
            } else if (str.startsWith(Constants.CLOSE_URI)) {
                this.f11388a.dismiss();
                return true;
            } else if (str.startsWith(Constants.DOWNLOAD_URI)) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(Uri.decode(str.substring(Constants.DOWNLOAD_URI.length()))));
                    intent.addFlags(268435456);
                    this.f11388a.f11409l.startActivity(intent);
                } catch (Exception e) {
                    C2333f.m13754b(AuthDialog.f11398a, "-->start download activity exception, e: " + e.getMessage());
                }
                return true;
            } else if (str.startsWith(AuthConstants.PROGRESS_URI)) {
                try {
                    r0 = Uri.parse(str).getPathSegments();
                    if (r0.isEmpty()) {
                        return true;
                    }
                    int intValue = Integer.valueOf((String) r0.get(0)).intValue();
                    if (intValue == 0) {
                        this.f11388a.f11405h.setVisibility(8);
                        this.f11388a.f11408k.setVisibility(0);
                    } else if (intValue == 1) {
                        this.f11388a.f11405h.setVisibility(0);
                    }
                    return true;
                } catch (Exception e2) {
                    return true;
                }
            } else if (str.startsWith(AuthConstants.ON_LOGIN_URI)) {
                try {
                    r0 = Uri.parse(str).getPathSegments();
                    if (!r0.isEmpty()) {
                        this.f11388a.f11414q = (String) r0.get(0);
                    }
                } catch (Exception e3) {
                }
                return true;
            } else if (this.f11388a.f11410m.m13853a(this.f11388a.f11408k, str)) {
                return true;
            } else {
                C2333f.m13757c(AuthDialog.f11398a, "-->Redirect URL: return false");
                return false;
            }
        }
    }

    class OnTimeListener implements IUiListener {
        String f11389a;
        String f11390b;
        final /* synthetic */ AuthDialog f11391c;
        private String f11392d;
        private IUiListener f11393e;

        public OnTimeListener(AuthDialog authDialog, String str, String str2, String str3, IUiListener iUiListener) {
            this.f11391c = authDialog;
            this.f11392d = str;
            this.f11389a = str2;
            this.f11390b = str3;
            this.f11393e = iUiListener;
        }

        private void m13238a(String str) {
            try {
                onComplete(Util.parseJson(str));
            } catch (JSONException e) {
                e.printStackTrace();
                onError(new UiError(-4, Constants.MSG_JSON_ERROR, str));
            }
        }

        public void onCancel() {
            if (this.f11393e != null) {
                this.f11393e.onCancel();
                this.f11393e = null;
            }
        }

        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            C2350g.m13795a().m13799a(this.f11392d + "_H5", SystemClock.elapsedRealtime(), 0, 0, jSONObject.optInt("ret", -6), this.f11389a, false);
            if (this.f11393e != null) {
                this.f11393e.onComplete(jSONObject);
                this.f11393e = null;
            }
        }

        public void onError(UiError uiError) {
            String str = uiError.errorMessage != null ? uiError.errorMessage + this.f11389a : this.f11389a;
            C2350g.m13795a().m13799a(this.f11392d + "_H5", SystemClock.elapsedRealtime(), 0, 0, uiError.errorCode, str, false);
            this.f11391c.m13244a(str);
            if (this.f11393e != null) {
                this.f11393e.onError(uiError);
                this.f11393e = null;
            }
        }
    }

    class THandler extends Handler {
        final /* synthetic */ AuthDialog f11394a;
        private OnTimeListener f11395b;

        public THandler(AuthDialog authDialog, OnTimeListener onTimeListener, Looper looper) {
            this.f11394a = authDialog;
            super(looper);
            this.f11395b = onTimeListener;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    this.f11395b.m13238a((String) message.obj);
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    this.f11395b.onCancel();
                case Type.BYTE /*3*/:
                    AuthDialog.m13249b(this.f11394a.f11409l, (String) message.obj);
                default:
            }
        }
    }

    class TimeOutRunable implements Runnable {
        String f11396a;
        final /* synthetic */ AuthDialog f11397b;

        public TimeOutRunable(AuthDialog authDialog, String str) {
            this.f11397b = authDialog;
            this.f11396a = C2915a.f14760f;
            this.f11396a = str;
        }

        public void run() {
            C2333f.m13754b(AuthDialog.f11398a, "-->timeoutUrl: " + this.f11396a + " | mRetryUrl: " + this.f11397b.f11413p);
            if (this.f11396a.equals(this.f11397b.f11413p)) {
                this.f11397b.f11400c.onError(new UiError(9002, "\u8bf7\u6c42\u9875\u9762\u8d85\u65f6\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5\uff01", this.f11397b.f11413p));
                this.f11397b.dismiss();
            }
        }
    }

    static {
        f11398a = C2333f.f12014d + ".authDlg";
        try {
            Context context = Global.getContext();
            if (context == null) {
                C2333f.m13754b(f11398a, "-->load wbsafeedit lib fail, because context is null.");
            } else if (new File(context.getFilesDir().toString() + "/" + AuthAgent.SECURE_LIB_NAME).exists()) {
                System.load(context.getFilesDir().toString() + "/" + AuthAgent.SECURE_LIB_NAME);
                C2333f.m13754b(f11398a, "-->load wbsafeedit lib success.");
            } else {
                C2333f.m13754b(f11398a, "-->load wbsafeedit lib fail, because so is not exists.");
            }
        } catch (Throwable e) {
            C2333f.m13755b(f11398a, "-->load wbsafeedit lib error.", e);
        }
    }

    public AuthDialog(Context context, String str, String str2, IUiListener iUiListener, QQToken qQToken) {
        super(context, 16973840);
        this.f11411n = false;
        this.f11415r = 0;
        this.f11416s = 30000;
        this.f11409l = context;
        this.f11399b = str2;
        this.f11400c = new OnTimeListener(this, str, str2, qQToken.getAppId(), iUiListener);
        this.f11402e = new THandler(this, this.f11400c, context.getMainLooper());
        this.f11401d = iUiListener;
        this.f11407j = str;
        this.f11410m = new C2364b();
        getWindow().setSoftInputMode(32);
    }

    static /* synthetic */ String m13242a(AuthDialog authDialog, Object obj) {
        String str = authDialog.f11399b + obj;
        authDialog.f11399b = str;
        return str;
    }

    private String m13244a(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        if (!TextUtils.isEmpty(this.f11414q) && this.f11414q.length() >= 4) {
            stringBuilder.append("_u_").append(this.f11414q.substring(this.f11414q.length() - 4));
        }
        return stringBuilder.toString();
    }

    private String m13247b() {
        String str = ServerSetting.DOWNLOAD_QQ_URL + this.f11399b.substring(this.f11399b.indexOf("?") + 1);
        C2333f.m13757c(f11398a, "-->generateDownloadUrl, url: http://qzs.qq.com/open/mobile/login/qzsjump.html?");
        return str;
    }

    private static void m13249b(Context context, String str) {
        try {
            JSONObject parseJson = Util.parseJson(str);
            int i = parseJson.getInt(SocialConstants.PARAM_TYPE);
            Toast.makeText(context.getApplicationContext(), parseJson.getString(SocialConstants.PARAM_SEND_MSG), i).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void m13252c() {
        m13255d();
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.f11408k = new C2353c(this.f11409l);
        this.f11408k.setLayoutParams(layoutParams);
        this.f11403f = new FrameLayout(this.f11409l);
        layoutParams.gravity = 17;
        this.f11403f.setLayoutParams(layoutParams);
        this.f11403f.addView(this.f11408k);
        this.f11403f.addView(this.f11405h);
        setContentView(this.f11403f);
    }

    private void m13255d() {
        LayoutParams layoutParams;
        this.f11406i = new ProgressBar(this.f11409l);
        this.f11406i.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.f11404g = new LinearLayout(this.f11409l);
        View view = null;
        if (this.f11407j.equals(SystemUtils.ACTION_LOGIN)) {
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 16;
            layoutParams.leftMargin = 5;
            view = new TextView(this.f11409l);
            if (Locale.getDefault().getLanguage().equals("zh")) {
                view.setText("\u767b\u5f55\u4e2d...");
            } else {
                view.setText("Logging in...");
            }
            view.setTextColor(Color.rgb(com.tencent.mm.sdk.platformtools.Util.MASK_8BIT, com.tencent.mm.sdk.platformtools.Util.MASK_8BIT, com.tencent.mm.sdk.platformtools.Util.MASK_8BIT));
            view.setTextSize(18.0f);
            view.setLayoutParams(layoutParams);
        }
        layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.f11404g.setLayoutParams(layoutParams);
        this.f11404g.addView(this.f11406i);
        if (view != null) {
            this.f11404g.addView(view);
        }
        this.f11405h = new FrameLayout(this.f11409l);
        LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams2.leftMargin = 80;
        layoutParams2.rightMargin = 80;
        layoutParams2.topMargin = 40;
        layoutParams2.bottomMargin = 40;
        layoutParams2.gravity = 17;
        this.f11405h.setLayoutParams(layoutParams2);
        this.f11405h.setBackgroundResource(17301504);
        this.f11405h.addView(this.f11404g);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void m13257e() {
        this.f11408k.setVerticalScrollBarEnabled(false);
        this.f11408k.setHorizontalScrollBarEnabled(false);
        this.f11408k.setWebViewClient(new LoginWebViewClient());
        this.f11408k.setWebChromeClient(new WebChromeClient());
        this.f11408k.clearFormData();
        this.f11408k.clearSslPreferences();
        this.f11408k.setOnLongClickListener(new C21931(this));
        this.f11408k.setOnTouchListener(new C21942(this));
        WebSettings settings = this.f11408k.getSettings();
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setCacheMode(-1);
        settings.setNeedInitialFocus(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(this.f11409l.getDir("databases", 0).getPath());
        settings.setDomStorageEnabled(true);
        C2333f.m13754b(f11398a, "-->mUrl : " + this.f11399b);
        this.f11413p = this.f11399b;
        this.f11408k.loadUrl(this.f11399b);
        this.f11408k.setVisibility(4);
        this.f11408k.getSettings().setSavePassword(false);
        this.f11410m.m13769a(new SecureJsInterface(), "SecureJsInterface");
        SecureJsInterface.isPWDEdit = false;
        super.setOnDismissListener(new C21953(this));
    }

    private boolean m13259f() {
        AuthMap instance = AuthMap.getInstance();
        String makeKey = instance.makeKey();
        Auth auth = new Auth();
        auth.listener = this.f11401d;
        auth.dialog = this;
        auth.key = makeKey;
        String str = instance.set(auth);
        String substring = this.f11399b.substring(0, this.f11399b.indexOf("?"));
        Bundle parseUrl = Util.parseUrl(this.f11399b);
        parseUrl.putString("token_key", makeKey);
        parseUrl.putString("serial", str);
        parseUrl.putString("browser", Constants.VIA_TO_TYPE_QQ_GROUP);
        this.f11399b = substring + "?" + Util.encodeUrl(parseUrl);
        return Util.openBrowser(this.f11409l, this.f11399b);
    }

    public void callJs(String str, String str2) {
        this.f11408k.loadUrl("javascript:" + str + "(" + str2 + ");void(" + System.currentTimeMillis() + ");");
    }

    public void dismiss() {
        this.f11417t.clear();
        this.f11402e.removeCallbacksAndMessages(null);
        if (isShowing()) {
            super.dismiss();
        }
        if (this.f11408k != null) {
            this.f11408k.destroy();
            this.f11408k = null;
        }
    }

    public void onBackPressed() {
        if (!this.f11411n) {
            this.f11400c.onCancel();
        }
        super.onBackPressed();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        m13252c();
        m13257e();
        this.f11417t = new HashMap();
    }

    protected void onStop() {
        super.onStop();
    }
}
