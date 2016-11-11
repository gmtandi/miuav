package com.tencent.connect.auth;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import android.widget.Toast;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.p128a.C2186a;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.utils.ApkExternalInfoTool;
import com.tencent.open.utils.Global;
import com.tencent.tauth.IUiListener;
import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class QQAuth {
    private AuthAgent f11420a;
    private QQToken f11421b;

    private QQAuth(String str, Context context) {
        C2333f.m13757c(C2333f.f12014d, "new QQAuth() --start");
        this.f11421b = new QQToken(str);
        this.f11420a = new AuthAgent(this.f11421b);
        C2186a.m13218c(context, this.f11421b);
        C2333f.m13757c(C2333f.f12014d, "new QQAuth() --end");
    }

    private int m13271a(Activity activity, Fragment fragment, String str, IUiListener iUiListener, String str2) {
        String str3;
        String packageName = activity.getApplicationContext().getPackageName();
        for (ApplicationInfo applicationInfo : activity.getPackageManager().getInstalledApplications(SmileConstants.TOKEN_PREFIX_TINY_UNICODE)) {
            if (packageName.equals(applicationInfo.packageName)) {
                str3 = applicationInfo.sourceDir;
                break;
            }
        }
        str3 = null;
        if (str3 != null) {
            try {
                String readChannelId = ApkExternalInfoTool.readChannelId(new File(str3));
                if (!TextUtils.isEmpty(readChannelId)) {
                    C2333f.m13754b(C2333f.f12014d, "-->login channelId: " + readChannelId);
                    return loginWithOEM(activity, str, iUiListener, readChannelId, readChannelId, C2915a.f14760f);
                }
            } catch (IOException e) {
                C2333f.m13754b(C2333f.f12014d, "-->login get channel id exception." + e.getMessage());
                e.printStackTrace();
            }
        }
        C2333f.m13754b(C2333f.f12014d, "-->login channelId is null ");
        BaseApi.isOEM = false;
        return this.f11420a.doLogin(activity, str, iUiListener, false, fragment);
    }

    public static QQAuth createInstance(String str, Context context) {
        Global.setContext(context.getApplicationContext());
        C2333f.m13757c(C2333f.f12014d, "QQAuth -- createInstance() --start");
        try {
            PackageManager packageManager = context.getPackageManager();
            packageManager.getActivityInfo(new ComponentName(context.getPackageName(), "com.tencent.tauth.AuthActivity"), 0);
            packageManager.getActivityInfo(new ComponentName(context.getPackageName(), "com.tencent.connect.common.AssistActivity"), 0);
            QQAuth qQAuth = new QQAuth(str, context);
            C2333f.m13757c(C2333f.f12014d, "QQAuth -- createInstance()  --end");
            return qQAuth;
        } catch (Throwable e) {
            C2333f.m13755b(C2333f.f12014d, "createInstance() error --end", e);
            Toast.makeText(context.getApplicationContext(), "\u8bf7\u53c2\u7167\u6587\u6863\u5728Androidmanifest.xml\u52a0\u4e0aAuthActivity\u548cAssitActivity\u7684\u5b9a\u4e49 ", 1).show();
            return null;
        }
    }

    public void checkLogin(IUiListener iUiListener) {
        this.f11420a.m13236b(iUiListener);
    }

    public QQToken getQQToken() {
        return this.f11421b;
    }

    public boolean isSessionValid() {
        C2333f.m13751a(C2333f.f12014d, "isSessionValid(), result = " + (this.f11421b.isSessionValid() ? "true" : "false") + C2915a.f14760f);
        return this.f11421b.isSessionValid();
    }

    public int login(Activity activity, String str, IUiListener iUiListener) {
        C2333f.m13757c(C2333f.f12014d, "login()");
        return login(activity, str, iUiListener, C2915a.f14760f);
    }

    public int login(Activity activity, String str, IUiListener iUiListener, String str2) {
        C2333f.m13757c(C2333f.f12014d, "-->login activity: " + activity);
        return m13271a(activity, null, str, iUiListener, str2);
    }

    public int login(Fragment fragment, String str, IUiListener iUiListener, String str2) {
        Activity activity = fragment.getActivity();
        C2333f.m13757c(C2333f.f12014d, "-->login activity: " + activity);
        return m13271a(activity, fragment, str, iUiListener, str2);
    }

    @Deprecated
    public int loginWithOEM(Activity activity, String str, IUiListener iUiListener, String str2, String str3, String str4) {
        C2333f.m13757c(C2333f.f12014d, "loginWithOEM");
        BaseApi.isOEM = true;
        if (str2.equals(C2915a.f14760f)) {
            str2 = "null";
        }
        if (str3.equals(C2915a.f14760f)) {
            str3 = "null";
        }
        if (str4.equals(C2915a.f14760f)) {
            str4 = "null";
        }
        BaseApi.installChannel = str3;
        BaseApi.registerChannel = str2;
        BaseApi.businessId = str4;
        return this.f11420a.doLogin(activity, str, iUiListener);
    }

    public void logout(Context context) {
        C2333f.m13757c(C2333f.f12014d, "logout() --start");
        CookieSyncManager.createInstance(context);
        setAccessToken(null, null);
        setOpenId(context, null);
        C2333f.m13757c(C2333f.f12014d, "logout() --end");
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        C2333f.m13757c(C2333f.f12014d, "onActivityResult() ,resultCode = " + i2 + C2915a.f14760f);
        return true;
    }

    public int reAuth(Activity activity, String str, IUiListener iUiListener) {
        C2333f.m13757c(C2333f.f12014d, "reAuth()");
        return this.f11420a.doLogin(activity, str, iUiListener, true, null);
    }

    public void reportDAU() {
        this.f11420a.m13235a(null);
    }

    public void setAccessToken(String str, String str2) {
        C2333f.m13751a(C2333f.f12014d, "setAccessToken(), validTimeInSecond = " + str2 + C2915a.f14760f);
        this.f11421b.setAccessToken(str, str2);
    }

    public void setOpenId(Context context, String str) {
        C2333f.m13751a(C2333f.f12014d, "setOpenId() --start");
        this.f11421b.setOpenId(str);
        C2186a.m13219d(context, this.f11421b);
        C2333f.m13751a(C2333f.f12014d, "setOpenId() --end");
    }
}
