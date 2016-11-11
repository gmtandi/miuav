package com.tencent.open.yyb;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import com.facebook.common.util.UriUtil;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.utils.Util;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

public class AppbarJsBridge {
    public static final int AUTHORIZE_FAIL = -5;
    public static final String BUTTON_CLICK_CALLBACK_FUNCTION_NAME = "clickCallback";
    public static final String CALLBACK_LOGIN = "loginCallback";
    public static final String CALLBACK_SHARE = "shareCallback";
    public static final int Code_Java_Exception = -3;
    public static final int Code_None = -2;
    public static final int JSBRIDGE_VERSION = 1;
    public static final String JS_BRIDGE_SCHEME = "jsb://";
    public static final String READY_CALLBACK_FUNCTION_NAME = "readyCallback";
    public static final int Result_Fail = -1;
    public static final int Result_OK = 0;
    public static final int SHARE_QQ = 1;
    public static final int SHARE_QZ = 2;
    public static final int SHARE_TIMELINE = 4;
    public static final int SHARE_WX = 3;
    private WebView f12166a;
    private Activity f12167b;

    public AppbarJsBridge(Activity activity, WebView webView) {
        this.f12167b = activity;
        this.f12166a = webView;
    }

    private void m13874a(Uri uri, String str, int i, String str2) {
        C2333f.m13754b(C2333f.f12014d, "-->callAMethod : uri = " + uri);
        if (m13875a(str)) {
            try {
                Class[] clsArr = new Class[SHARE_TIMELINE];
                clsArr[Result_OK] = Uri.class;
                clsArr[SHARE_QQ] = Integer.TYPE;
                clsArr[SHARE_QZ] = String.class;
                clsArr[SHARE_WX] = String.class;
                Method declaredMethod = AppbarJsBridge.class.getDeclaredMethod(str, clsArr);
                Object[] objArr = new Object[SHARE_TIMELINE];
                objArr[Result_OK] = uri;
                objArr[SHARE_QQ] = Integer.valueOf(i);
                objArr[SHARE_QZ] = str;
                objArr[SHARE_WX] = str2;
                declaredMethod.invoke(this, objArr);
            } catch (Exception e) {
                C2333f.m13754b(C2333f.f12014d, "-->callAMethod : Exception = " + e.getMessage());
                e.printStackTrace();
                if (!TextUtils.isEmpty(str2)) {
                    responseFail(str2, i, str, Code_Java_Exception);
                }
            }
        } else if (!TextUtils.isEmpty(str2)) {
            responseFail(str2, i, str, AUTHORIZE_FAIL);
        }
    }

    private boolean m13875a(String str) {
        return true;
    }

    public void callback(String str, String str2) {
        if (this.f12166a != null) {
            StringBuffer stringBuffer = new StringBuffer("javascript:");
            stringBuffer.append("if(!!").append(str).append("){");
            stringBuffer.append(str);
            stringBuffer.append("(");
            stringBuffer.append(str2);
            stringBuffer.append(")}");
            this.f12166a.loadUrl(stringBuffer.toString());
        }
    }

    public void clickCallback() {
        response(BUTTON_CLICK_CALLBACK_FUNCTION_NAME, Result_OK, null, C2915a.f14760f);
    }

    public void closeWebView(Uri uri, int i, String str, String str2) {
        C2333f.m13754b(C2333f.f12014d, "-->closeWebView : url = " + uri);
        this.f12167b.finish();
    }

    public void getAppInfo(Uri uri, int i, String str, String str2) {
        Object queryParameter = uri.getQueryParameter("packagenames");
        C2333f.m13754b(C2333f.f12014d, "-->getAppInfo : packageNames = " + queryParameter);
        if (!TextUtils.isEmpty(queryParameter) && !TextUtils.isEmpty(str2)) {
            String[] split = queryParameter.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            if (split != null && split.length != 0) {
                JSONObject jSONObject = new JSONObject();
                int length = split.length;
                for (int i2 = Result_OK; i2 < length; i2 += SHARE_QQ) {
                    String trim = split[i2].trim();
                    PackageInfo packageInfo = null;
                    try {
                        packageInfo = this.f12167b.getPackageManager().getPackageInfo(trim, Result_OK);
                    } catch (NameNotFoundException e) {
                        C2333f.m13754b(C2333f.f12014d, "-->getAppInfo : NameNotFoundException e1");
                    }
                    try {
                        JSONObject jSONObject2 = new JSONObject();
                        if (packageInfo != null) {
                            jSONObject2.put("install", SHARE_QQ);
                            jSONObject2.put(SocialConstants.PARAM_APPNAME, packageInfo.applicationInfo.name);
                            jSONObject2.put("verCode", packageInfo.versionCode);
                            jSONObject2.put("verName", packageInfo.versionName);
                        } else {
                            jSONObject2.put("install", Result_OK);
                        }
                        jSONObject.put(trim, jSONObject2);
                    } catch (Exception e2) {
                        responseFail(str2, i, str, Code_Java_Exception);
                    }
                }
                C2333f.m13754b(C2333f.f12014d, "-->getAppInfo : result = " + jSONObject.toString());
                response(str2, i, str, jSONObject.toString());
            }
        }
    }

    public int getVersion() {
        return SHARE_QQ;
    }

    public void invoke(String str) {
        C2333f.m13754b(C2333f.f12014d, "-->invoke : url = " + str);
        Uri parse = Uri.parse(str);
        String host = parse.getHost();
        C2333f.m13754b(C2333f.f12014d, "-->invoke : hostAsMethodName = " + host);
        if (!TextUtils.isEmpty(host)) {
            int i;
            List pathSegments = parse.getPathSegments();
            String str2 = null;
            if (pathSegments == null || pathSegments.size() <= 0) {
                i = Result_OK;
            } else {
                i = Util.parseIntValue((String) pathSegments.get(Result_OK));
                if (pathSegments.size() > SHARE_QQ) {
                    str2 = (String) pathSegments.get(SHARE_QQ);
                }
            }
            C2333f.m13754b(C2333f.f12014d, "-->invoke : seqid = " + i + " callbackName = " + str2);
            if (host.equals("callBatch")) {
                try {
                    JSONArray jSONArray = new JSONArray(parse.getQueryParameter("param"));
                    int length = jSONArray.length();
                    for (int i2 = Result_OK; i2 < length; i2 += SHARE_QQ) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i2);
                        String string = jSONObject.getString("method");
                        int i3 = jSONObject.getInt("seqid");
                        String optString = jSONObject.optString("callback");
                        JSONObject jSONObject2 = jSONObject.getJSONObject("args");
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(JS_BRIDGE_SCHEME).append(string).append("/").append(i3).append("/").append(!TextUtils.isEmpty(optString) ? optString : C2915a.f14760f).append("?");
                        if (jSONObject2 != null) {
                            Iterator keys = jSONObject2.keys();
                            while (keys.hasNext()) {
                                String str3 = (String) keys.next();
                                stringBuilder.append(str3).append("=").append(Uri.encode(Uri.decode(jSONObject2.getString(str3)))).append("&");
                            }
                        }
                        m13874a(Uri.parse(stringBuilder.toString()), string, i3, optString);
                    }
                    return;
                } catch (Exception e) {
                    if (!TextUtils.isEmpty(str2)) {
                        responseFail(str2, i, host, AUTHORIZE_FAIL);
                        return;
                    }
                    return;
                }
            }
            m13874a(parse, host, i, str2);
        }
    }

    public void openLoginActivity(Uri uri, int i, String str, String str2) {
        C2333f.m13754b(C2333f.f12014d, "-->openLoginActivity : url = " + uri);
        ((AppbarActivity) this.f12167b).login();
    }

    public void openNewWindow(Uri uri, int i, String str, String str2) {
        String queryParameter = uri.getQueryParameter(SocialConstants.PARAM_URL);
        try {
            Intent intent = new Intent(this.f12167b, AppbarActivity.class);
            intent.putExtra(SocialConstants.PARAM_URL, queryParameter);
            this.f12167b.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pageControl(Uri uri, int i, String str, String str2) {
        C2333f.m13754b(C2333f.f12014d, "-->pageControl : url = " + uri);
        int parseIntValue = Util.parseIntValue(uri.getQueryParameter(SocialConstants.PARAM_TYPE));
        if (this.f12166a != null) {
            if (parseIntValue == SHARE_QQ) {
                this.f12166a.goBack();
            } else if (parseIntValue == SHARE_QZ) {
                this.f12166a.goForward();
            } else {
                this.f12166a.reload();
            }
        }
        response(str2, i, str, C2915a.f14760f);
    }

    public void ready() {
        response(READY_CALLBACK_FUNCTION_NAME, SHARE_QQ, null, "true");
    }

    public void response(String str, int i, String str2, String str3) {
        response(str, i, str2, str3, null);
    }

    public void response(String str, int i, String str2, String str3, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("result", Result_OK);
                jSONObject.put(UriUtil.DATA_SCHEME, str3);
                if (!TextUtils.isEmpty(str2)) {
                    jSONObject.put("method", str2);
                }
                jSONObject.put("seqid", i);
                if (map != null) {
                    for (String str4 : map.keySet()) {
                        jSONObject.put(str4, map.get(str4));
                    }
                }
                callback(str, jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void responseFail(String str, int i, String str2, int i2) {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("result", Result_Fail);
                jSONObject.put(XiaomiOAuthConstants.EXTRA_CODE_2, i2);
                jSONObject.put("method", str2);
                jSONObject.put("seqid", i);
                callback(str, jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void responseShare(int i) {
        Map hashMap = new HashMap();
        hashMap.put(SocialConstants.PARAM_TYPE, i + C2915a.f14760f);
        response(CALLBACK_SHARE, Result_OK, null, Constants.VIA_RESULT_SUCCESS, hashMap);
    }

    public void responseShareFail(int i) {
        Map hashMap = new HashMap();
        hashMap.put(SocialConstants.PARAM_TYPE, i + C2915a.f14760f);
        response(CALLBACK_SHARE, Result_OK, null, Constants.VIA_TO_TYPE_QQ_GROUP, hashMap);
    }

    public void setWebView(Uri uri, int i, String str, String str2) {
        boolean z = true;
        try {
            Object queryParameter = uri.getQueryParameter(SocialConstants.PARAM_TITLE);
            int parseIntValue = Util.parseIntValue(uri.getQueryParameter("buttonVisible"), Result_OK);
            if (!TextUtils.isEmpty(queryParameter)) {
                ((AppbarActivity) this.f12167b).setAppbarTitle(queryParameter);
            }
            AppbarActivity appbarActivity = (AppbarActivity) this.f12167b;
            if (parseIntValue != SHARE_QQ) {
                z = false;
            }
            appbarActivity.setShareVisibility(z);
            C2333f.m13754b(C2333f.f12014d, "-->setWebView : url = " + uri + " -- buttonVisiable = " + parseIntValue);
            response(str2, i, str, C2915a.f14760f);
        } catch (Exception e) {
            responseFail(str2, i, str, Code_Java_Exception);
        }
    }

    public void share(Uri uri, int i, String str, String str2) {
        C2333f.m13754b(C2333f.f12014d, "-->share : url = " + uri);
        String queryParameter = uri.getQueryParameter(SocialConstants.PARAM_TITLE);
        String queryParameter2 = uri.getQueryParameter(SocialConstants.PARAM_SUMMARY);
        String queryParameter3 = uri.getQueryParameter("iconUrl");
        if (TextUtils.isEmpty(queryParameter3)) {
            queryParameter3 = "http://qzs.qq.com/open/mobile/jsbridge/demo.htm";
        }
        String queryParameter4 = uri.getQueryParameter("jumpUrl");
        C2333f.m13754b(C2333f.f12014d, "-->share : title = " + queryParameter);
        C2333f.m13754b(C2333f.f12014d, "-->share : summary = " + queryParameter2);
        C2333f.m13754b(C2333f.f12014d, "-->share : iconUrl = " + queryParameter3);
        C2333f.m13754b(C2333f.f12014d, "-->share : jumpUrl = " + queryParameter4);
        ShareModel shareModel = new ShareModel();
        shareModel.f12168a = queryParameter;
        shareModel.f12169b = queryParameter2;
        shareModel.f12170c = queryParameter3;
        shareModel.f12171d = queryParameter4;
        ((AppbarActivity) this.f12167b).setShareModel(shareModel);
        switch (Util.parseIntValue(uri.getQueryParameter(SocialConstants.PARAM_TYPE), Result_OK)) {
            case SHARE_QQ /*1*/:
                ((AppbarActivity) this.f12167b).shareToQQ();
            case SHARE_QZ /*2*/:
                ((AppbarActivity) this.f12167b).shareToQzone();
            case SHARE_WX /*3*/:
                ((AppbarActivity) this.f12167b).shareToWX();
            case SHARE_TIMELINE /*4*/:
                ((AppbarActivity) this.f12167b).shareToTimeline();
            default:
                ((AppbarActivity) this.f12167b).showFloatingDialog();
        }
    }
}
