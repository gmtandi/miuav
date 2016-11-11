package com.tencent.open.yyb;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.fimi.soul.utils.bq;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.yyb.C2391a.C2389a;
import com.xiaomi.market.sdk.C2537j;
import java.util.regex.Pattern;
import org.p122a.p123a.C2915a;

public class AppbarAgent extends BaseApi {
    public static final String TO_APPBAR_DETAIL = "siteIndex";
    public static final String TO_APPBAR_NEWS = "myMessage";
    public static final String TO_APPBAR_SEND_BLOG = "newThread";
    public static final String wx_appid = "wx8e8dc60535c9cd93";
    private Bundle f12164a;
    private String f12165b;

    public AppbarAgent(QQToken qQToken) {
        super(qQToken);
    }

    private String m13866a() {
        Bundle bundle = new Bundle();
        if (!(this.mToken == null || this.mToken.getAppId() == null || this.mToken.getAccessToken() == null || this.mToken.getOpenId() == null)) {
            bundle.putString("qOpenAppId", this.mToken.getAppId());
            bundle.putString("qOpenId", this.mToken.getOpenId());
            bundle.putString("qAccessToken", this.mToken.getAccessToken());
        }
        bundle.putString("qPackageName", Global.getContext().getPackageName());
        return "&" + m13867a(bundle);
    }

    private String m13867a(Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return C2915a.f14760f;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : bundle.keySet()) {
            stringBuilder.append(str).append("=").append(bundle.get(str)).append("&");
        }
        String str2 = stringBuilder.toString();
        if (str2.endsWith("&")) {
            str2 = str2.substring(0, str2.length() - 1);
        }
        C2333f.m13754b("AppbarAgent", "-->encodeParams, result: " + str2);
        return str2;
    }

    private void m13868a(Activity activity, String str) {
        if (this.mToken != null) {
            Intent intent = new Intent(activity, AppbarActivity.class);
            intent.putExtra(SocialConstants.PARAM_APP_ID, this.mToken.getAppId());
            if (!(this.mToken.getAccessToken() == null || this.mToken.getOpenId() == null)) {
                C2389a c2389a = new C2389a();
                c2389a.f12173b = this.mToken.getAccessToken();
                c2389a.f12174c = Long.parseLong(this.mToken.getAppId());
                c2389a.f12172a = this.mToken.getOpenId();
                C2391a.m13881a(activity, str, this.mToken.getOpenId(), this.mToken.getAccessToken(), this.mToken.getAppId());
            }
            intent.putExtra(SocialConstants.PARAM_URL, str);
            C2333f.m13754b("AppbarAgent", "-->(AppbarAgent)startAppbar H5 : url = " + str);
            try {
                activity.startActivityForResult(intent, Constants.REQUEST_APPBAR);
            } catch (Exception e) {
                C2333f.m13754b("AppbarAgent", "-->(AppbarAgent)startAppbar : activity not found, start H5");
            }
        }
    }

    private boolean m13869a(String str) {
        return TO_APPBAR_DETAIL.equals(str) || TO_APPBAR_NEWS.equals(str) || TO_APPBAR_SEND_BLOG.equals(str) || "sId".equals(str) || "toThread".equals(str);
    }

    private Bundle m13870b(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("pkgName", Global.getContext().getPackageName());
        if (!(TO_APPBAR_DETAIL.equals(str) || TO_APPBAR_SEND_BLOG.equals(str))) {
            if (TO_APPBAR_NEWS.equals(str)) {
                bundle.putString(C2537j.au, "myapp");
            } else if ("sId".equals(str)) {
                if (this.f12164a != null) {
                    bundle.putAll(this.f12164a);
                }
            } else if ("toThread".equals(str)) {
                str = String.format("sId/t/%s", new Object[]{this.f12165b});
            }
        }
        bundle.putString(bq.f10107a, str);
        return bundle;
    }

    private String m13871b() {
        try {
            PackageInfo packageInfo = Global.getContext().getPackageManager().getPackageInfo("com.tencent.android.qqdownloader", 0);
            return packageInfo == null ? null : packageInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String m13872c(String str) {
        StringBuilder stringBuilder = new StringBuilder("http://m.wsq.qq.com/direct?");
        stringBuilder.append(m13867a(m13870b(str)));
        return stringBuilder.toString();
    }

    private boolean m13873d(String str) {
        return !TextUtils.isEmpty(str) && Pattern.matches("^[1-9][0-9]*$", str);
    }

    public void startAppbar(Activity activity, String str) {
        if (m13869a(str)) {
            String c = m13872c(str);
            Object b = m13871b();
            if (TextUtils.isEmpty(b) || SystemUtils.compareVersion(b, "4.2") < 0) {
                m13868a(activity, c);
                return;
            }
            String str2 = c + m13866a();
            C2333f.m13754b("AppbarAgent", "-->(AppbarAgent)startAppbar : yybUrl = " + str2);
            try {
                Intent intent = new Intent();
                intent.setClassName("com.tencent.android.qqdownloader", "com.tencent.assistant.activity.ExportBrowserActivity");
                intent.putExtra("com.tencent.assistant.BROWSER_URL", str2);
                activity.startActivity(intent);
                activity.overridePendingTransition(17432576, 17432577);
                return;
            } catch (Exception e) {
                C2333f.m13754b("AppbarAgent", "-->(AppbarAgent)startAppbar : ExportBrowserActivity not found, start H5");
                m13868a(activity, c);
                return;
            }
        }
        Toast.makeText(activity, Constants.MSG_PARAM_ERROR, 0).show();
    }

    public void startAppbarLabel(Activity activity, String str) {
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(activity, Constants.MSG_PARAM_ERROR, 0).show();
            return;
        }
        this.f12164a = new Bundle();
        this.f12164a.putString("params", "label/" + str);
        startAppbar(activity, "sId");
    }

    public void startAppbarThread(Activity activity, String str) {
        if (m13873d(str)) {
            this.f12165b = str;
            startAppbar(activity, "toThread");
            return;
        }
        Toast.makeText(activity, Constants.MSG_PARAM_ERROR, 0).show();
    }
}
