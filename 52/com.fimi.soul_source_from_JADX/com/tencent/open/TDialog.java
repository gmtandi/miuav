package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.connect.auth.AuthConstants;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.open.C2336a.C2292b;
import com.tencent.open.p132c.C2352b;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.p134b.C2350g;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.Util;
import com.tencent.tauth.AuthActivity;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.lang.ref.WeakReference;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

public class TDialog extends C2293b {
    static final LayoutParams f11864a;
    static Toast f11865b;
    private static WeakReference<ProgressDialog> f11866d;
    private WeakReference<Context> f11867c;
    private String f11868e;
    private OnTimeListener f11869f;
    private IUiListener f11870g;
    private FrameLayout f11871h;
    private C2352b f11872i;
    private Handler f11873j;
    private boolean f11874k;
    private QQToken f11875l;

    class FbWebViewClient extends WebViewClient {
        private FbWebViewClient() {
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            TDialog.this.f11872i.setVisibility(0);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Util.logd("TDialog", "Webview loading URL: " + str);
            super.onPageStarted(webView, str, bitmap);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            TDialog.this.f11869f.onError(new UiError(i, str, str2));
            if (!(TDialog.this.f11867c == null || TDialog.this.f11867c.get() == null)) {
                Toast.makeText((Context) TDialog.this.f11867c.get(), "\u7f51\u7edc\u8fde\u63a5\u5f02\u5e38\u6216\u7cfb\u7edf\u9519\u8bef", 0).show();
            }
            TDialog.this.dismiss();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Util.logd("TDialog", "Redirect URL: " + str);
            if (str.startsWith(ServerSetting.getInstance().getEnvUrl((Context) TDialog.this.f11867c.get(), ServerSetting.DEFAULT_REDIRECT_URI))) {
                TDialog.this.f11869f.onComplete(Util.parseUrlToJson(str));
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            } else if (str.startsWith(Constants.CANCEL_URI)) {
                TDialog.this.f11869f.onCancel();
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            } else if (str.startsWith(Constants.CLOSE_URI)) {
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            } else if (!str.startsWith(Constants.DOWNLOAD_URI)) {
                return str.startsWith(AuthConstants.PROGRESS_URI);
            } else {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(Uri.decode(str.substring(Constants.DOWNLOAD_URI.length()))));
                intent.addFlags(268435456);
                if (!(TDialog.this.f11867c == null || TDialog.this.f11867c.get() == null)) {
                    ((Context) TDialog.this.f11867c.get()).startActivity(intent);
                }
                return true;
            }
        }
    }

    class JsListener extends C2292b {
        private JsListener() {
        }

        public void onAddShare(String str) {
            C2333f.m13754b("TDialog", "onAddShare");
            onComplete(str);
        }

        public void onCancel(String str) {
            C2333f.m13754b("TDialog", "onCancel --msg = " + str);
            TDialog.this.f11873j.obtainMessage(2, str).sendToTarget();
            TDialog.this.dismiss();
        }

        public void onCancelAddShare(String str) {
            C2333f.m13754b("TDialog", "onCancelAddShare");
            onCancel("cancel");
        }

        public void onCancelInvite() {
            C2333f.m13754b("TDialog", "onCancelInvite");
            onCancel(C2915a.f14760f);
        }

        public void onCancelLogin() {
            onCancel(C2915a.f14760f);
        }

        public void onComplete(String str) {
            TDialog.this.f11873j.obtainMessage(1, str).sendToTarget();
            C2333f.m13759e("onComplete", str);
            TDialog.this.dismiss();
        }

        public void onInvite(String str) {
            onComplete(str);
        }

        public void onLoad(String str) {
            TDialog.this.f11873j.obtainMessage(4, str).sendToTarget();
        }

        public void showMsg(String str) {
            TDialog.this.f11873j.obtainMessage(3, str).sendToTarget();
        }
    }

    class OnTimeListener implements IUiListener {
        private String mAction;
        String mAppid;
        String mUrl;
        private WeakReference<Context> mWeakCtx;
        private IUiListener mWeakL;

        public OnTimeListener(Context context, String str, String str2, String str3, IUiListener iUiListener) {
            this.mWeakCtx = new WeakReference(context);
            this.mAction = str;
            this.mUrl = str2;
            this.mAppid = str3;
            this.mWeakL = iUiListener;
        }

        private void onComplete(String str) {
            try {
                onComplete(Util.parseJson(str));
            } catch (JSONException e) {
                e.printStackTrace();
                onError(new UiError(-4, Constants.MSG_JSON_ERROR, str));
            }
        }

        public void onCancel() {
            if (this.mWeakL != null) {
                this.mWeakL.onCancel();
                this.mWeakL = null;
            }
        }

        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            C2350g.m13795a().m13799a(this.mAction + "_H5", SystemClock.elapsedRealtime(), 0, 0, jSONObject.optInt("ret", -6), this.mUrl, false);
            if (this.mWeakL != null) {
                this.mWeakL.onComplete(jSONObject);
                this.mWeakL = null;
            }
        }

        public void onError(UiError uiError) {
            C2350g.m13795a().m13799a(this.mAction + "_H5", SystemClock.elapsedRealtime(), 0, 0, uiError.errorCode, uiError.errorMessage != null ? uiError.errorMessage + this.mUrl : this.mUrl, false);
            if (this.mWeakL != null) {
                this.mWeakL.onError(uiError);
                this.mWeakL = null;
            }
        }
    }

    class THandler extends Handler {
        private OnTimeListener mL;

        public THandler(OnTimeListener onTimeListener, Looper looper) {
            super(looper);
            this.mL = onTimeListener;
        }

        public void handleMessage(Message message) {
            C2333f.m13754b("TAG", "--handleMessage--msg.WHAT = " + message.what);
            switch (message.what) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    this.mL.onComplete((String) message.obj);
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    this.mL.onCancel();
                case Type.BYTE /*3*/:
                    if (TDialog.this.f11867c != null && TDialog.this.f11867c.get() != null) {
                        TDialog.m13622c((Context) TDialog.this.f11867c.get(), (String) message.obj);
                    }
                case Type.INT /*5*/:
                    if (TDialog.this.f11867c != null && TDialog.this.f11867c.get() != null) {
                        TDialog.m13624d((Context) TDialog.this.f11867c.get(), (String) message.obj);
                    }
                default:
            }
        }
    }

    static {
        f11864a = new LayoutParams(-1, -1);
        f11865b = null;
    }

    public TDialog(Context context, String str, String str2, IUiListener iUiListener, QQToken qQToken) {
        super(context, 16973840);
        this.f11874k = false;
        this.f11875l = null;
        this.f11867c = new WeakReference(context);
        this.f11868e = str2;
        this.f11869f = new OnTimeListener(context, str, str2, qQToken.getAppId(), iUiListener);
        this.f11873j = new THandler(this.f11869f, context.getMainLooper());
        this.f11870g = iUiListener;
        this.f11875l = qQToken;
    }

    private void m13616a() {
        new TextView((Context) this.f11867c.get()).setText("test");
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.f11872i = new C2352b((Context) this.f11867c.get());
        this.f11872i.setLayoutParams(layoutParams);
        this.f11871h = new FrameLayout((Context) this.f11867c.get());
        layoutParams.gravity = 17;
        this.f11871h.setLayoutParams(layoutParams);
        this.f11871h.addView(this.f11872i);
        setContentView(this.f11871h);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void m13619b() {
        this.f11872i.setVerticalScrollBarEnabled(false);
        this.f11872i.setHorizontalScrollBarEnabled(false);
        this.f11872i.setWebViewClient(new FbWebViewClient());
        this.f11872i.setWebChromeClient(this.mChromeClient);
        this.f11872i.clearFormData();
        WebSettings settings = this.f11872i.getSettings();
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setCacheMode(-1);
        settings.setNeedInitialFocus(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setJavaScriptEnabled(true);
        if (!(this.f11867c == null || this.f11867c.get() == null)) {
            settings.setDatabaseEnabled(true);
            settings.setDatabasePath(((Context) this.f11867c.get()).getApplicationContext().getDir("databases", 0).getPath());
        }
        settings.setDomStorageEnabled(true);
        this.jsBridge.m13769a(new JsListener(), "sdk_js_if");
        this.f11872i.loadUrl(this.f11868e);
        this.f11872i.setLayoutParams(f11864a);
        this.f11872i.setVisibility(4);
        this.f11872i.getSettings().setSavePassword(false);
    }

    private static void m13622c(Context context, String str) {
        try {
            JSONObject parseJson = Util.parseJson(str);
            int i = parseJson.getInt(SocialConstants.PARAM_TYPE);
            CharSequence string = parseJson.getString(SocialConstants.PARAM_SEND_MSG);
            if (i == 0) {
                if (f11865b == null) {
                    f11865b = Toast.makeText(context, string, 0);
                } else {
                    f11865b.setView(f11865b.getView());
                    f11865b.setText(string);
                    f11865b.setDuration(0);
                }
                f11865b.show();
            } else if (i == 1) {
                if (f11865b == null) {
                    f11865b = Toast.makeText(context, string, 1);
                } else {
                    f11865b.setView(f11865b.getView());
                    f11865b.setText(string);
                    f11865b.setDuration(1);
                }
                f11865b.show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void m13624d(Context context, String str) {
        if (context != null && str != null) {
            try {
                JSONObject parseJson = Util.parseJson(str);
                int i = parseJson.getInt(AuthActivity.ACTION_KEY);
                CharSequence string = parseJson.getString(SocialConstants.PARAM_SEND_MSG);
                if (i == 1) {
                    if (f11866d == null) {
                        ProgressDialog progressDialog = new ProgressDialog(context);
                        progressDialog.setMessage(string);
                        f11866d = new WeakReference(progressDialog);
                        progressDialog.show();
                        return;
                    }
                    ((ProgressDialog) f11866d.get()).setMessage(string);
                    if (!((ProgressDialog) f11866d.get()).isShowing()) {
                        ((ProgressDialog) f11866d.get()).show();
                    }
                } else if (i == 0 && f11866d != null && f11866d.get() != null && ((ProgressDialog) f11866d.get()).isShowing()) {
                    ((ProgressDialog) f11866d.get()).dismiss();
                    f11866d = null;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void onBackPressed() {
        if (this.f11869f != null) {
            this.f11869f.onCancel();
        }
        super.onBackPressed();
    }

    protected void onConsoleMessage(String str) {
        C2333f.m13754b("TDialog", "--onConsoleMessage--");
        try {
            this.jsBridge.m13771a(this.f11872i, str);
        } catch (Exception e) {
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        m13616a();
        m13619b();
    }
}
