package com.tencent.open.yyb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ZoomButtonsController;
import com.facebook.common.util.UriUtil;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.open.GameAppOperation;
import com.tencent.open.SocialConstants;
import com.tencent.open.p132c.C2352b;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.utils.SystemUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import it.p074a.p075a.C2799f;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import org.codehaus.jackson.smile.SmileConstants;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p152c.p156c.C2951i;

public class AppbarActivity extends Activity implements OnClickListener {
    private static final int FLOATING_DIALOG_HEIGHT = 100;
    public static final String MYAPP_CACHE_PATH = "/tencent/tassistant";
    private static final String UA_PREFIX = "qqdownloader/";
    private static final String WEBVIEW_PATH = "/webview_cache";
    private static ArrayList<String> specialModel;
    private String appid;
    private AppbarJsBridge jsBridge;
    private final DownloadListener mDownloadListener;
    private MoreFloatingDialog mFloatingDialog;
    protected ProgressDialog mProgressDialog;
    private LinearLayout mRootView;
    private TitleBar mTitleBar;
    private QQToken mToken;
    private C2352b mWebView;
    private ShareModel model;
    private Tencent tencent;
    private int titlebarTop;
    private String url;

    /* renamed from: com.tencent.open.yyb.AppbarActivity.1 */
    class C23781 implements IUiListener {
        final /* synthetic */ AppbarActivity f12154a;

        C23781(AppbarActivity appbarActivity) {
            this.f12154a = appbarActivity;
        }

        public void onCancel() {
            C2333f.m13754b(C2333f.f12014d, "-->(AppbarJsBridge)openLoginActivity onCancel");
            this.f12154a.jsBridge.responseFail(AppbarJsBridge.CALLBACK_LOGIN, 0, null, -2);
        }

        public void onComplete(Object obj) {
            C2333f.m13754b(C2333f.f12014d, "-->(AppbarJsBridge)openLoginActivity onComplete");
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject.optInt("ret", -1) != 0) {
                this.f12154a.jsBridge.responseFail(AppbarJsBridge.CALLBACK_LOGIN, 0, null, -5);
                return;
            }
            try {
                String string = jSONObject.getString(SocialConstants.PARAM_OPEN_ID);
                String string2 = jSONObject.getString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2);
                jSONObject.getString(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2);
                C2391a.m13881a(this.f12154a, this.f12154a.mWebView.getUrl(), string, string2, this.f12154a.getToken().getAppId());
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("logintype", "SSO");
                    jSONObject2.put(SocialConstants.PARAM_OPEN_ID, string);
                    jSONObject2.put("accesstoken", string2);
                    this.f12154a.jsBridge.response(AppbarJsBridge.CALLBACK_LOGIN, 0, null, jSONObject2.toString());
                    Intent intent = new Intent();
                    intent.putExtra(Constants.LOGIN_INFO, jSONObject.toString());
                    this.f12154a.setResult(Constants.RESULT_LOGIN, intent);
                } catch (JSONException e) {
                    this.f12154a.jsBridge.responseFail(AppbarJsBridge.CALLBACK_LOGIN, 0, null, -5);
                    C2333f.m13754b(C2333f.f12014d, "-->(AppbarJsBridge)openLoginActivity onComplete: put keys callback failed.");
                }
            } catch (JSONException e2) {
                this.f12154a.jsBridge.responseFail(AppbarJsBridge.CALLBACK_LOGIN, 0, null, -5);
                C2333f.m13754b(C2333f.f12014d, "-->(AppbarJsBridge)openLoginActivity onComplete: get keys failed.");
            }
        }

        public void onError(UiError uiError) {
            C2333f.m13754b(C2333f.f12014d, "-->(AppbarJsBridge)openLoginActivity onError");
            this.f12154a.jsBridge.responseFail(AppbarJsBridge.CALLBACK_LOGIN, 0, null, -5);
        }
    }

    /* renamed from: com.tencent.open.yyb.AppbarActivity.2 */
    class C23792 implements IUiListener {
        final /* synthetic */ QQToken f12155a;
        final /* synthetic */ AppbarActivity f12156b;

        C23792(AppbarActivity appbarActivity, QQToken qQToken) {
            this.f12156b = appbarActivity;
            this.f12155a = qQToken;
        }

        public void onCancel() {
            C2333f.m13754b(C2333f.f12014d, "-->(AppbarActivity)shareToQQ onCancel");
            this.f12156b.jsBridge.responseShareFail(1);
        }

        public void onComplete(Object obj) {
            C2333f.m13754b(C2333f.f12014d, "-->(AppbarActivity)shareToQQ onComplete");
            this.f12156b.jsBridge.responseShare(1);
            C2391a.m13882a(this.f12155a.getAppId(), "400", "SDK.APPBAR.HOME.SHARE.QQ");
        }

        public void onError(UiError uiError) {
            C2333f.m13754b(C2333f.f12014d, "-->(AppbarActivity)shareToQQ onError");
            this.f12156b.jsBridge.responseShareFail(1);
        }
    }

    /* renamed from: com.tencent.open.yyb.AppbarActivity.3 */
    class C23803 implements IUiListener {
        final /* synthetic */ QQToken f12157a;
        final /* synthetic */ AppbarActivity f12158b;

        C23803(AppbarActivity appbarActivity, QQToken qQToken) {
            this.f12158b = appbarActivity;
            this.f12157a = qQToken;
        }

        public void onCancel() {
            C2333f.m13754b(C2333f.f12014d, "-->(AppbarActivity)shareToQzone onCancel");
            this.f12158b.jsBridge.responseShareFail(2);
        }

        public void onComplete(Object obj) {
            C2333f.m13754b(C2333f.f12014d, "-->(AppbarActivity)shareToQzone onComplete");
            this.f12158b.jsBridge.responseShare(2);
            C2391a.m13882a(this.f12157a.getAppId(), "400", "SDK.APPBAR.HOME.SHARE.QZ");
        }

        public void onError(UiError uiError) {
            C2333f.m13754b(C2333f.f12014d, "-->(AppbarActivity)shareToQzone onError");
            this.f12158b.jsBridge.responseShareFail(2);
        }
    }

    /* renamed from: com.tencent.open.yyb.AppbarActivity.a */
    interface C2381a {
        void m13862a(byte[] bArr);
    }

    /* renamed from: com.tencent.open.yyb.AppbarActivity.4 */
    class C23824 implements C2381a {
        final /* synthetic */ AppbarActivity f12159a;

        C23824(AppbarActivity appbarActivity) {
            this.f12159a = appbarActivity;
        }

        public void m13863a(byte[] bArr) {
            this.f12159a.mProgressDialog.dismiss();
        }
    }

    /* renamed from: com.tencent.open.yyb.AppbarActivity.5 */
    class C23835 implements DownloadListener {
        final /* synthetic */ AppbarActivity f12160a;

        C23835(AppbarActivity appbarActivity) {
            this.f12160a = appbarActivity;
        }

        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            C2333f.m13754b(C2333f.f12014d, "-->(AppbarActivity)onDownloadStart : url = " + str);
            try {
                this.f12160a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            } catch (Exception e) {
                C2333f.m13754b(C2333f.f12014d, "-->(AppbarActivity)onDownloadStart : activity aciton_view not found.");
            }
            QQToken access$500 = this.f12160a.getToken();
            if (access$500 != null) {
                C2391a.m13882a(access$500.getAppId(), "200", "SDK.APPBAR.HOME ACTION");
            }
        }
    }

    /* renamed from: com.tencent.open.yyb.AppbarActivity.b */
    class C2384b extends AsyncTask<String, Void, byte[]> {
        private C2381a f12161a;

        public C2384b(C2381a c2381a) {
            this.f12161a = c2381a;
        }

        protected void m13864a(byte[] bArr) {
            super.onPostExecute(bArr);
            this.f12161a.m13862a(bArr);
        }

        protected byte[] m13865a(String... strArr) {
            try {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strArr[0]).openConnection();
                    httpURLConnection.setConnectTimeout(FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS);
                    try {
                        httpURLConnection.setRequestMethod(C2951i.f14860a);
                        try {
                            InputStream inputStream = httpURLConnection.getInputStream();
                            try {
                                if (httpURLConnection.getResponseCode() == C2799f.f14282t) {
                                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                    byte[] bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
                                    while (true) {
                                        int read = inputStream.read(bArr);
                                        if (read != -1) {
                                            byteArrayOutputStream.write(bArr, 0, read);
                                        } else {
                                            byteArrayOutputStream.close();
                                            inputStream.close();
                                            return byteArrayOutputStream.toByteArray();
                                        }
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            return null;
                        }
                    } catch (ProtocolException e3) {
                        e3.printStackTrace();
                        return null;
                    }
                } catch (IOException e22) {
                    e22.printStackTrace();
                    return null;
                }
            } catch (MalformedURLException e4) {
                e4.printStackTrace();
                return null;
            }
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m13865a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m13864a((byte[]) obj);
        }
    }

    /* renamed from: com.tencent.open.yyb.AppbarActivity.c */
    class C2385c extends WebChromeClient {
        final /* synthetic */ AppbarActivity f12162a;

        private C2385c(AppbarActivity appbarActivity) {
            this.f12162a = appbarActivity;
        }

        public void onReceivedTitle(WebView webView, String str) {
            this.f12162a.mTitleBar.setTitle(str);
        }
    }

    /* renamed from: com.tencent.open.yyb.AppbarActivity.d */
    class C2386d extends WebViewClient {
        final /* synthetic */ AppbarActivity f12163a;

        private C2386d(AppbarActivity appbarActivity) {
            this.f12163a = appbarActivity;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            this.f12163a.setSupportZoom(true);
            this.f12163a.jsBridge.ready();
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.f12163a.setSupportZoom(false);
            if (!str.startsWith(UriUtil.HTTP_SCHEME) && !str.startsWith(UriUtil.HTTPS_SCHEME)) {
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            boolean z = true;
            C2333f.m13754b(C2333f.f12014d, "-->(AppbarDialog)shouldOverrideUrlLoading : url = " + str);
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            if (str.startsWith(UriUtil.HTTP_SCHEME) || str.startsWith(UriUtil.HTTPS_SCHEME)) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            if (str.startsWith(AppbarJsBridge.JS_BRIDGE_SCHEME)) {
                this.f12163a.jsBridge.invoke(str);
                return true;
            } else if (!str.equals("about:blank;") && !str.equals("about:blank")) {
                return false;
            } else {
                if (VERSION.SDK_INT >= 11) {
                    z = false;
                }
                return z;
            }
        }
    }

    static {
        specialModel = new ArrayList();
        specialModel.add("MT870");
        specialModel.add("XT910");
        specialModel.add("XT928");
        specialModel.add("MT917");
        specialModel.add("Lenovo A60");
    }

    public AppbarActivity() {
        this.mDownloadListener = new C23835(this);
    }

    private String buildTransaction(String str) {
        return str == null ? String.valueOf(System.currentTimeMillis()) : str + System.currentTimeMillis();
    }

    private void createViews() {
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.mWebView.setLayoutParams(layoutParams);
        this.mRootView = new LinearLayout(this);
        layoutParams.gravity = 17;
        this.mRootView.setLayoutParams(layoutParams);
        this.mRootView.setOrientation(1);
        this.mTitleBar = new TitleBar(this);
        this.mTitleBar.getBackBtn().setOnClickListener(this);
        this.mTitleBar.getSharBtn().setOnClickListener(this);
        this.mRootView.addView(this.mTitleBar);
        this.mRootView.addView(this.mWebView);
        setContentView(this.mRootView);
    }

    private String getCommonPath(String str) {
        String commonRootDir = getCommonRootDir();
        if (!TextUtils.isEmpty(str)) {
            commonRootDir = commonRootDir + str;
        }
        return getPath(commonRootDir, false);
    }

    private String getCommonRootDir() {
        String str;
        if (isSDCardExistAndCanWrite()) {
            str = Environment.getExternalStorageDirectory().getPath() + MYAPP_CACHE_PATH;
        } else {
            File filesDir = getFilesDir();
            if (filesDir == null) {
                return C2915a.f14760f;
            }
            str = filesDir.getAbsolutePath() + MYAPP_CACHE_PATH;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    private MoreFloatingDialog getFloatingDialg() {
        if (this.mFloatingDialog == null) {
            this.mFloatingDialog = new MoreFloatingDialog(this);
            this.mFloatingDialog.setCanceledOnTouchOutside(true);
            this.mFloatingDialog.getQQItem().setOnClickListener(this);
            this.mFloatingDialog.getQzoneItem().setOnClickListener(this);
        }
        return this.mFloatingDialog;
    }

    private String getPath(String str, boolean z) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
            if (z) {
                try {
                    new File(str + File.separator + ".nomedia").createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file.getAbsolutePath();
    }

    private Tencent getTencent() {
        if (this.tencent == null) {
            this.tencent = Tencent.createInstance(this.appid, this);
        }
        return this.tencent;
    }

    private int getTitbarTop() {
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.titlebarTop = displayMetrics.heightPixels - rect.height();
        return this.titlebarTop;
    }

    private QQToken getToken() {
        if (this.mToken == null) {
            this.mToken = getTencent().getQQToken();
        }
        return this.mToken;
    }

    private String getWebViewCacheDir() {
        return getCommonPath(WEBVIEW_PATH);
    }

    private void initViews() {
        Method method;
        WebSettings settings = this.mWebView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setUserAgentString(settings.getUserAgentString() + "/" + UA_PREFIX + this.jsBridge.getVersion() + "/sdk");
        settings.setJavaScriptEnabled(true);
        Class cls = settings.getClass();
        try {
            method = cls.getMethod("setPluginsEnabled", new Class[]{Boolean.TYPE});
            if (method != null) {
                method.invoke(settings, new Object[]{Boolean.valueOf(true)});
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            method = cls.getMethod("setDomStorageEnabled", new Class[]{Boolean.TYPE});
            if (method != null) {
                method.invoke(settings, new Object[]{Boolean.valueOf(true)});
            }
        } catch (SecurityException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
        } catch (IllegalArgumentException e4) {
        } catch (IllegalAccessException e5) {
        } catch (InvocationTargetException e6) {
        }
        settings.setAppCachePath(getWebViewCacheDir());
        settings.setDatabasePath(getWebViewCacheDir());
        settings.setDatabaseEnabled(true);
        settings.setAppCacheEnabled(true);
        if (supportWebViewFullScreen()) {
            settings.setUseWideViewPort(true);
            if (VERSION.SDK_INT >= 7) {
                try {
                    cls.getMethod("setLoadWithOverviewMode", new Class[]{Boolean.TYPE}).invoke(settings, new Object[]{Boolean.valueOf(true)});
                } catch (Exception e7) {
                }
            }
            if (SystemUtils.isSupportMultiTouch()) {
                if (SystemUtils.getAndroidSDKVersion() < 11) {
                    try {
                        Field declaredField = WebView.class.getDeclaredField("mZoomButtonsController");
                        declaredField.setAccessible(true);
                        ZoomButtonsController zoomButtonsController = new ZoomButtonsController(this.mWebView);
                        zoomButtonsController.getZoomControls().setVisibility(8);
                        declaredField.set(this.mWebView, zoomButtonsController);
                    } catch (Exception e8) {
                    }
                } else {
                    try {
                        this.mWebView.getSettings().getClass().getMethod("setDisplayZoomControls", new Class[]{Boolean.TYPE}).invoke(this.mWebView.getSettings(), new Object[]{Boolean.valueOf(false)});
                    } catch (Exception e9) {
                    }
                }
            }
        }
        this.mWebView.setWebViewClient(new C2386d());
        this.mWebView.setWebChromeClient(new C2385c());
        this.mWebView.setDownloadListener(this.mDownloadListener);
        this.mWebView.loadUrl(this.url);
    }

    private boolean isSDCardExistAndCanWrite() {
        try {
            return "mounted".equals(Environment.getExternalStorageState()) && Environment.getExternalStorageDirectory().canWrite();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setSupportZoom(boolean z) {
        if (this.mWebView != null) {
            this.mWebView.getSettings().setSupportZoom(z);
        }
    }

    private void shareToWX(boolean z) {
        C2333f.m13754b(C2333f.f12014d, "-->shareToWX : wx_appid = wx8e8dc60535c9cd93");
        if (!TextUtils.isEmpty(this.model.f12170c)) {
            showProgressDialog(this, C2915a.f14760f, C2915a.f14760f);
            new C2384b(new C23824(this)).execute(new String[]{this.model.f12170c});
        }
    }

    private boolean supportWebViewFullScreen() {
        String str = Build.MODEL;
        return (str.contains("vivo") || specialModel.contains(str)) ? false : true;
    }

    public void login() {
        C2333f.m13754b(C2333f.f12014d, "-->login : activity~~~");
        getTencent().login((Activity) this, "all", new C23781(this));
    }

    public void onBackPressed() {
        MoreFloatingDialog floatingDialg = getFloatingDialg();
        if (floatingDialg == null || !floatingDialg.isShowing()) {
            super.onBackPressed();
        } else {
            floatingDialg.dismiss();
        }
    }

    public void onClick(View view) {
        MoreFloatingDialog floatingDialg = getFloatingDialg();
        if (view == this.mTitleBar.getSharBtn()) {
            this.jsBridge.clickCallback();
        } else if (view == floatingDialg.getQQItem()) {
            shareToQQ();
        } else if (view == floatingDialg.getQzoneItem()) {
            shareToQzone();
        } else if (view == floatingDialg.getWXItem()) {
            shareToWX();
        } else if (view == floatingDialg.getTimelineItem()) {
            shareToTimeline();
        } else if (view == this.mTitleBar.getBackBtn()) {
            finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.appid = getIntent().getStringExtra(SocialConstants.PARAM_APP_ID);
        this.url = getIntent().getStringExtra(SocialConstants.PARAM_URL);
        C2333f.m13754b(C2333f.f12014d, "-->(AppbarActivity)onCreate : appid = " + this.appid + " url = " + this.url);
        this.mWebView = new C2352b(this);
        this.jsBridge = new AppbarJsBridge(this, this.mWebView);
        createViews();
        initViews();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.mWebView != null) {
            this.mWebView.removeAllViews();
            this.mWebView.setVisibility(8);
            this.mWebView.stopLoading();
            this.mWebView.clearHistory();
            this.mWebView.destroy();
        }
    }

    protected void onPause() {
        super.onPause();
        MoreFloatingDialog floatingDialg = getFloatingDialg();
        if (floatingDialg != null && floatingDialg.isShowing()) {
            floatingDialg.dismiss();
        }
    }

    protected void onResume() {
        super.onResume();
    }

    public void setAppbarTitle(String str) {
        this.mTitleBar.setTitle(str);
    }

    public void setShareModel(ShareModel shareModel) {
        this.model = shareModel;
    }

    public void setShareVisibility(boolean z) {
        this.mTitleBar.getSharBtn().setVisibility(z ? 0 : 4);
    }

    public void shareToQQ() {
        QQToken token = getToken();
        if (token != null) {
            QQShare qQShare = new QQShare(this, token);
            Bundle bundle = new Bundle();
            bundle.putString(SocialConstants.PARAM_TITLE, this.model.f12168a);
            bundle.putString(SocialConstants.PARAM_TARGET_URL, this.model.f12171d);
            bundle.putString(SocialConstants.PARAM_SUMMARY, this.model.f12169b);
            bundle.putString(SocialConstants.PARAM_IMAGE_URL, this.model.f12170c);
            C2333f.m13754b(C2333f.f12014d, "-->(AppbarActivity)shareToQQ : model.mTitle = " + this.model.f12168a);
            C2333f.m13754b(C2333f.f12014d, "-->(AppbarActivity)shareToQQ : model.mTargetUrl = " + this.model.f12171d);
            C2333f.m13754b(C2333f.f12014d, "-->(AppbarActivity)shareToQQ : model.mDescription = " + this.model.f12169b);
            C2333f.m13754b(C2333f.f12014d, "-->(AppbarActivity)shareToQQ : model.mIconUrl = " + this.model.f12170c);
            qQShare.shareToQQ(this, bundle, new C23792(this, token));
            C2391a.m13882a(token.getAppId(), "200", "SDK.APPBAR.HOME.SHARE.QQ");
        }
    }

    public void shareToQzone() {
        QQToken token = getToken();
        if (token != null) {
            QzoneShare qzoneShare = new QzoneShare(this, token);
            Bundle bundle = new Bundle();
            bundle.putInt(GameAppOperation.QQFAV_DATALINE_REQTYPE, 1);
            bundle.putString(SocialConstants.PARAM_TITLE, this.model.f12168a);
            bundle.putString(SocialConstants.PARAM_SUMMARY, this.model.f12169b);
            bundle.putString(SocialConstants.PARAM_TARGET_URL, this.model.f12171d);
            ArrayList arrayList = new ArrayList();
            C2333f.m13754b(C2333f.f12014d, "-->shareToQzone : mIconUrl = " + this.model.f12170c);
            arrayList.add(this.model.f12170c);
            bundle.putStringArrayList(SocialConstants.PARAM_IMAGE_URL, arrayList);
            qzoneShare.shareToQzone(this, bundle, new C23803(this, token));
            C2391a.m13882a(token.getAppId(), "200", "SDK.APPBAR.HOME.SHARE.QZ");
        }
    }

    public void shareToTimeline() {
        shareToWX(true);
    }

    public void shareToWX() {
        shareToWX(false);
    }

    public void showFloatingDialog() {
        MoreFloatingDialog floatingDialg = getFloatingDialg();
        floatingDialg.show();
        Window window = floatingDialg.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 48;
        attributes.y = getTitbarTop() + this.mTitleBar.getHeight();
        Display defaultDisplay = floatingDialg.getWindow().getWindowManager().getDefaultDisplay();
        attributes.height = floatingDialg.dip2px(100.0f);
        attributes.width = ((int) (((double) defaultDisplay.getWidth()) * 0.95d)) / 2;
        attributes.x = attributes.width / 2;
        C2333f.m13754b(C2333f.f12014d, "-->(AppbarDialog)showFloatingDialog : params.x = " + attributes.x);
        window.setAttributes(attributes);
    }

    protected void showProgressDialog(Context context, String str, String str2) {
        CharSequence charSequence;
        CharSequence charSequence2;
        if (TextUtils.isEmpty(str)) {
            charSequence = "\u8bf7\u7a0d\u5019";
        }
        if (TextUtils.isEmpty(str2)) {
            charSequence2 = "\u6b63\u5728\u52a0\u8f7d...";
        }
        this.mProgressDialog = ProgressDialog.show(context, charSequence, charSequence2);
        this.mProgressDialog.setCancelable(true);
    }
}
