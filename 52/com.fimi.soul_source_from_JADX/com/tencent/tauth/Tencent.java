package com.tencent.tauth;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.avatar.QQAvatar;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.open.GameAppOperation;
import com.tencent.open.LocationApi;
import com.tencent.open.SocialApi;
import com.tencent.open.SocialConstants;
import com.tencent.open.TaskGuide;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.p134b.C2341d;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.TemporaryStorage;
import com.tencent.open.yyb.AppbarAgent;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

public class Tencent {
    private static Tencent sInstance;
    private AppbarAgent mAgent;
    private LocationApi mLocationApi;
    private final QQAuth mQQAuth;

    private Tencent(String str, Context context) {
        Global.setContext(context.getApplicationContext());
        this.mQQAuth = QQAuth.createInstance(str, context);
    }

    private static boolean checkManifestConfig(Context context, String str) {
        try {
            context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), "com.tencent.tauth.AuthActivity"), 0);
            try {
                context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), "com.tencent.connect.common.AssistActivity"), 0);
                return true;
            } catch (NameNotFoundException e) {
                StringBuilder stringBuilder = new StringBuilder();
                C2333f.m13759e("AndroidManifest.xml \u6ca1\u6709\u68c0\u6d4b\u5230com.tencent.connect.common.AssistActivity", stringBuilder.append("\u6ca1\u6709\u5728AndroidManifest.xml\u4e2d\u68c0\u6d4b\u5230com.tencent.connect.common.AssistActivity,\u8bf7\u52a0\u4e0acom.tencent.connect.common.AssistActivity,\u8be6\u7ec6\u4fe1\u606f\u8bf7\u67e5\u770b\u5b98\u7f51\u6587\u6863.").append("\n\u914d\u7f6e\u793a\u4f8b\u5982\u4e0b: \n<activity\n     android:name=\"com.tencent.connect.common.AssistActivity\"\n     android:screenOrientation=\"portrait\"\n     android:theme=\"@android:style/Theme.Translucent.NoTitleBar\"\n     android:configChanges=\"orientation|keyboardHidden\">\n</activity>").toString());
                return false;
            }
        } catch (NameNotFoundException e2) {
            String str2 = "AndroidManifest.xml \u6ca1\u6709\u68c0\u6d4b\u5230com.tencent.tauth.AuthActivity";
            C2333f.m13759e(str2, ("\u6ca1\u6709\u5728AndroidManifest.xml\u4e2d\u68c0\u6d4b\u5230com.tencent.tauth.AuthActivity,\u8bf7\u52a0\u4e0acom.tencent.open.AuthActivity,\u5e76\u914d\u7f6e<data android:scheme=\"tencent" + str + "\" />,\u8be6\u7ec6\u4fe1\u606f\u8bf7\u67e5\u770b\u5b98\u7f51\u6587\u6863.") + "\n\u914d\u7f6e\u793a\u4f8b\u5982\u4e0b: \n<activity\n     android:name=\"com.tencent.connect.util.AuthActivity\"\n     android:noHistory=\"true\"\n     android:launchMode=\"singleTask\">\n<intent-filter>\n    <action android:name=\"android.intent.action.VIEW\" />\n     <category android:name=\"android.intent.category.DEFAULT\" />\n    <category android:name=\"android.intent.category.BROWSABLE\" />\n    <data android:scheme=\"tencent" + str + "\" />\n" + "</intent-filter>\n" + "</activity>");
            return false;
        }
    }

    public static synchronized Tencent createInstance(String str, Context context) {
        Tencent tencent;
        synchronized (Tencent.class) {
            C2333f.m13751a(C2333f.f12014d, "createInstance()  -- start");
            if (sInstance == null) {
                sInstance = new Tencent(str, context);
            } else if (!str.equals(sInstance.getAppId())) {
                sInstance.logout(context);
                sInstance = new Tencent(str, context);
            }
            if (checkManifestConfig(context, str)) {
                C2333f.m13751a(C2333f.f12014d, "createInstance()  -- end");
                tencent = sInstance;
            } else {
                tencent = null;
            }
        }
        return tencent;
    }

    public static void handleResultData(Intent intent, IUiListener iUiListener) {
        BaseApi.handleDataToListener(intent, iUiListener);
    }

    public void addToQQFavorites(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new GameAppOperation(getQQToken()).addToQQFavorites(activity, bundle, iUiListener);
    }

    public int ask(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new SocialApi(this.mQQAuth.getQQToken()).ask(activity, bundle, iUiListener);
        return 0;
    }

    public void bindQQGroup(Activity activity, Bundle bundle) {
        new GameAppOperation(getQQToken()).bindQQGroup(activity, bundle);
    }

    public int brag(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new SocialApi(this.mQQAuth.getQQToken()).brag(activity, bundle, iUiListener);
        return 0;
    }

    public int challenge(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new SocialApi(this.mQQAuth.getQQToken()).challenge(activity, bundle, iUiListener);
        return 0;
    }

    public void checkLogin(IUiListener iUiListener) {
        this.mQQAuth.checkLogin(iUiListener);
    }

    public int deleteLocation(Activity activity, Bundle bundle, IUiListener iUiListener) {
        if (this.mLocationApi == null) {
            this.mLocationApi = new LocationApi(this.mQQAuth.getQQToken());
        }
        this.mLocationApi.deleteLocation(activity, bundle, iUiListener);
        return 0;
    }

    public String getAccessToken() {
        return this.mQQAuth.getQQToken().getAccessToken();
    }

    public String getAppId() {
        return this.mQQAuth.getQQToken().getAppId();
    }

    public long getExpiresIn() {
        return this.mQQAuth.getQQToken().getExpireTimeInSecond();
    }

    public String getOpenId() {
        return this.mQQAuth.getQQToken().getOpenId();
    }

    public QQToken getQQToken() {
        return this.mQQAuth.getQQToken();
    }

    public int gift(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new SocialApi(this.mQQAuth.getQQToken()).gift(activity, bundle, iUiListener);
        return 0;
    }

    public void grade(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new SocialApi(this.mQQAuth.getQQToken()).grade(activity, bundle, iUiListener);
    }

    @Deprecated
    public void handleLoginData(Intent intent, IUiListener iUiListener) {
        BaseApi.handleDataToListener(intent, iUiListener);
    }

    public int invite(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new SocialApi(this.mQQAuth.getQQToken()).invite(activity, bundle, iUiListener);
        return 0;
    }

    public boolean isReady() {
        return isSessionValid() && getOpenId() != null;
    }

    public boolean isSessionValid() {
        return this.mQQAuth.isSessionValid();
    }

    public boolean isSupportSSOLogin(Activity activity) {
        return SystemUtils.getAppVersionName(activity, Constants.PACKAGE_QQ) == null ? false : SystemUtils.checkMobileQQ(activity);
    }

    public boolean joinQQGroup(Activity activity, String str) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + str));
        try {
            activity.startActivity(intent);
            C2341d.m13784a().m13786a(this.mQQAuth.getQQToken().getOpenId(), this.mQQAuth.getQQToken().getAppId(), Constants.VIA_JOIN_GROUP, Constants.VIA_REPORT_TYPE_JOININ_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_RESULT_SUCCESS);
            return true;
        } catch (Exception e) {
            C2341d.m13784a().m13786a(this.mQQAuth.getQQToken().getOpenId(), this.mQQAuth.getQQToken().getAppId(), Constants.VIA_JOIN_GROUP, Constants.VIA_REPORT_TYPE_JOININ_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
            return false;
        }
    }

    public int login(Activity activity, String str, IUiListener iUiListener) {
        return this.mQQAuth.login(activity, str, iUiListener);
    }

    public int login(Fragment fragment, String str, IUiListener iUiListener) {
        return this.mQQAuth.login(fragment, str, iUiListener, C2915a.f14760f);
    }

    public int loginWithOEM(Activity activity, String str, IUiListener iUiListener, String str2, String str3, String str4) {
        return this.mQQAuth.loginWithOEM(activity, str, iUiListener, str2, str3, str4);
    }

    public void logout(Context context) {
        this.mQQAuth.getQQToken().setAccessToken(null, Constants.VIA_RESULT_SUCCESS);
        this.mQQAuth.getQQToken().setOpenId(null);
    }

    public void makeFriend(Activity activity, Bundle bundle) {
        new GameAppOperation(getQQToken()).makeFriend(activity, bundle);
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        return false;
    }

    public int reAuth(Activity activity, String str, IUiListener iUiListener) {
        return this.mQQAuth.reAuth(activity, str, iUiListener);
    }

    public int reactive(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new SocialApi(this.mQQAuth.getQQToken()).reactive(activity, bundle, iUiListener);
        return 0;
    }

    public void releaseResource() {
        TemporaryStorage.remove(SystemUtils.QQ_SHARE_CALLBACK_ACTION);
        TemporaryStorage.remove(SystemUtils.QZONE_SHARE_CALLBACK_ACTION);
        TemporaryStorage.remove(SystemUtils.QQDATALINE_CALLBACK_ACTION);
        TemporaryStorage.remove(SystemUtils.QQFAVORITES_CALLBACK_ACTION);
        TemporaryStorage.remove(SystemUtils.TROOPBAR_CALLBACK_ACTION);
    }

    public void reportDAU() {
        this.mQQAuth.reportDAU();
    }

    public JSONObject request(String str, Bundle bundle, String str2) {
        return HttpUtils.request(this.mQQAuth.getQQToken(), Global.getContext(), str, bundle, str2);
    }

    public void requestAsync(String str, Bundle bundle, String str2, IRequestListener iRequestListener, Object obj) {
        HttpUtils.requestAsync(this.mQQAuth.getQQToken(), Global.getContext(), str, bundle, str2, iRequestListener);
    }

    public int searchNearby(Activity activity, Bundle bundle, IUiListener iUiListener) {
        if (this.mLocationApi == null) {
            this.mLocationApi = new LocationApi(this.mQQAuth.getQQToken());
        }
        this.mLocationApi.searchNearby(activity, bundle, iUiListener);
        return 0;
    }

    public void sendToMyComputer(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new GameAppOperation(getQQToken()).sendToMyComputer(activity, bundle, iUiListener);
    }

    public void setAccessToken(String str, String str2) {
        C2333f.m13751a(C2333f.f12014d, "setAccessToken(), expiresIn = " + str2 + C2915a.f14760f);
        this.mQQAuth.setAccessToken(str, str2);
    }

    public void setAvatar(Activity activity, Bundle bundle, IUiListener iUiListener) {
        String string = bundle.getString(SocialConstants.PARAM_AVATAR_URI);
        new QQAvatar(this.mQQAuth.getQQToken()).setAvatar(activity, Uri.parse(string), iUiListener, bundle.getInt("exitAnim"));
    }

    public void setAvatar(Activity activity, Bundle bundle, IUiListener iUiListener, int i, int i2) {
        bundle.putInt("exitAnim", i2);
        activity.overridePendingTransition(i, 0);
        setAvatar(activity, bundle, iUiListener);
    }

    public void setOpenId(String str) {
        C2333f.m13751a(C2333f.f12014d, "setOpenId() --start");
        this.mQQAuth.setOpenId(Global.getContext(), str);
        C2333f.m13751a(C2333f.f12014d, "setOpenId() --end");
    }

    public void shareToQQ(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new QQShare(activity, this.mQQAuth.getQQToken()).shareToQQ(activity, bundle, iUiListener);
    }

    public void shareToQzone(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new QzoneShare(activity, this.mQQAuth.getQQToken()).shareToQzone(activity, bundle, iUiListener);
    }

    public void shareToTroopBar(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new GameAppOperation(getQQToken()).shareToTroopBar(activity, bundle, iUiListener);
    }

    public void showTaskGuideWindow(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new TaskGuide(activity, this.mQQAuth.getQQToken()).showTaskGuideWindow(activity, bundle, iUiListener);
    }

    public void startAppbar(Activity activity, String str) {
        if (this.mAgent == null) {
            this.mAgent = new AppbarAgent(this.mQQAuth.getQQToken());
        }
        this.mAgent.startAppbar(activity, str);
    }

    public void startAppbarLabel(Activity activity, String str) {
        if (this.mAgent == null) {
            this.mAgent = new AppbarAgent(this.mQQAuth.getQQToken());
        }
        this.mAgent.startAppbarLabel(activity, str);
    }

    public void startAppbarThread(Activity activity, String str) {
        if (this.mAgent == null) {
            this.mAgent = new AppbarAgent(this.mQQAuth.getQQToken());
        }
        this.mAgent.startAppbarThread(activity, str);
    }

    public int story(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new SocialApi(this.mQQAuth.getQQToken()).story(activity, bundle, iUiListener);
        return 0;
    }

    public void voice(Activity activity, Bundle bundle, IUiListener iUiListener) {
        new SocialApi(this.mQQAuth.getQQToken()).voice(activity, bundle, iUiListener);
    }
}
