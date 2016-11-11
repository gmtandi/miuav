package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.AssistActivity;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.p128a.C2186a;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.open.SocialConstants;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.p134b.C2341d;
import com.tencent.open.utils.AsynLoadImg;
import com.tencent.open.utils.AsynLoadImgBack;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.TemporaryStorage;
import com.tencent.open.utils.Util;
import com.tencent.tauth.AuthActivity;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.File;
import java.util.ArrayList;
import org.p122a.p123a.C2915a;

public class QQShare extends BaseApi {
    public static final int QQ_SHARE_SUMMARY_MAX_LENGTH = 60;
    public static final int QQ_SHARE_TITLE_MAX_LENGTH = 45;
    public static final String SHARE_TO_QQ_APP_NAME = "appName";
    public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
    public static final String SHARE_TO_QQ_EXT_INT = "cflag";
    public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
    public static final int SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN = 1;
    public static final int SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE = 2;
    public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
    public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
    public static final String SHARE_TO_QQ_KEY_TYPE = "req_type";
    public static final String SHARE_TO_QQ_SITE = "site";
    public static final String SHARE_TO_QQ_SUMMARY = "summary";
    public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
    public static final String SHARE_TO_QQ_TITLE = "title";
    public static final int SHARE_TO_QQ_TYPE_APP = 6;
    public static final int SHARE_TO_QQ_TYPE_AUDIO = 2;
    public static final int SHARE_TO_QQ_TYPE_DEFAULT = 1;
    public static final int SHARE_TO_QQ_TYPE_IMAGE = 5;
    private static final String f11507a;
    public String mViaShareQQType;

    /* renamed from: com.tencent.connect.share.QQShare.1 */
    class C22151 implements AsynLoadImgBack {
        final /* synthetic */ Bundle f11495a;
        final /* synthetic */ String f11496b;
        final /* synthetic */ String f11497c;
        final /* synthetic */ IUiListener f11498d;
        final /* synthetic */ Activity f11499e;
        final /* synthetic */ QQShare f11500f;

        C22151(QQShare qQShare, Bundle bundle, String str, String str2, IUiListener iUiListener, Activity activity) {
            this.f11500f = qQShare;
            this.f11495a = bundle;
            this.f11496b = str;
            this.f11497c = str2;
            this.f11498d = iUiListener;
            this.f11499e = activity;
        }

        public void batchSaved(int i, ArrayList<String> arrayList) {
        }

        public void saved(int i, String str) {
            if (i == 0) {
                this.f11495a.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, str);
            } else if (TextUtils.isEmpty(this.f11496b) && TextUtils.isEmpty(this.f11497c)) {
                if (this.f11498d != null) {
                    this.f11498d.onError(new UiError(-6, Constants.MSG_SHARE_GETIMG_ERROR, null));
                    C2333f.m13759e(QQShare.f11507a, "shareToMobileQQ -- error: \u83b7\u53d6\u5206\u4eab\u56fe\u7247\u5931\u8d25!");
                }
                C2341d.m13784a().m13785a(QQShare.SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f11500f.mToken.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, QQShare.SHARE_TO_QQ_TYPE_DEFAULT, Constants.MSG_SHARE_GETIMG_ERROR);
                return;
            }
            this.f11500f.m13333b(this.f11499e, this.f11495a, this.f11498d);
        }
    }

    /* renamed from: com.tencent.connect.share.QQShare.2 */
    class C22162 implements AsynLoadImgBack {
        final /* synthetic */ Bundle f11501a;
        final /* synthetic */ String f11502b;
        final /* synthetic */ String f11503c;
        final /* synthetic */ IUiListener f11504d;
        final /* synthetic */ Activity f11505e;
        final /* synthetic */ QQShare f11506f;

        C22162(QQShare qQShare, Bundle bundle, String str, String str2, IUiListener iUiListener, Activity activity) {
            this.f11506f = qQShare;
            this.f11501a = bundle;
            this.f11502b = str;
            this.f11503c = str2;
            this.f11504d = iUiListener;
            this.f11505e = activity;
        }

        public void batchSaved(int i, ArrayList<String> arrayList) {
        }

        public void saved(int i, String str) {
            if (i == 0) {
                this.f11501a.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, str);
            } else if (TextUtils.isEmpty(this.f11502b) && TextUtils.isEmpty(this.f11503c)) {
                if (this.f11504d != null) {
                    this.f11504d.onError(new UiError(-6, Constants.MSG_SHARE_GETIMG_ERROR, null));
                    C2333f.m13759e(QQShare.f11507a, "shareToMobileQQ -- error: \u83b7\u53d6\u5206\u4eab\u56fe\u7247\u5931\u8d25!");
                }
                C2341d.m13784a().m13785a(QQShare.SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f11506f.mToken.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, QQShare.SHARE_TO_QQ_TYPE_DEFAULT, Constants.MSG_SHARE_GETIMG_ERROR);
                return;
            }
            this.f11506f.m13333b(this.f11505e, this.f11501a, this.f11504d);
        }
    }

    static {
        f11507a = C2333f.f12014d + ".QQShare";
    }

    public QQShare(Context context, QQToken qQToken) {
        super(qQToken);
        this.mViaShareQQType = C2915a.f14760f;
    }

    private StringBuffer m13329a(StringBuffer stringBuffer, Bundle bundle) {
        C2333f.m13757c(C2333f.f12014d, "fillShareToQQParams() --start");
        String str = "...";
        bundle.putString(AuthActivity.ACTION_KEY, SystemUtils.QQ_SHARE_CALLBACK_ACTION);
        bundle.putString("appId", this.mToken.getAppId());
        bundle.putString("sdkp", "a");
        bundle.putString("sdkv", Constants.SDK_VERSION);
        bundle.putString("status_os", VERSION.RELEASE);
        bundle.putString("status_machine", Build.MODEL);
        String str2 = RMsgInfo.COL_CONTENT;
        if (bundle.containsKey(str2) && bundle.getString(str2).length() > 40) {
            bundle.putString(str2, bundle.getString(str2).substring(0, 40) + str);
        }
        str2 = SHARE_TO_QQ_SUMMARY;
        if (bundle.containsKey(str2) && bundle.getString(str2).length() > 80) {
            bundle.putString(str2, bundle.getString(str2).substring(0, 80) + str);
        }
        stringBuffer.append("&" + Util.encodeUrl(bundle).replaceAll("\\+", "%20"));
        C2333f.m13757c(C2333f.f12014d, "fillShareToQQParams() --end");
        return stringBuffer;
    }

    private void m13330a(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2333f.m13757c(C2333f.f12014d, "shareToMobileQQ() -- start.");
        String string = bundle.getString(SHARE_TO_QQ_IMAGE_URL);
        String string2 = bundle.getString(SHARE_TO_QQ_TITLE);
        String string3 = bundle.getString(SHARE_TO_QQ_SUMMARY);
        C2333f.m13754b(f11507a, "shareToMobileQQ -- imageUrl: " + string);
        if (TextUtils.isEmpty(string)) {
            m13333b(activity, bundle, iUiListener);
        } else if (!Util.isValidUrl(string)) {
            bundle.putString(SHARE_TO_QQ_IMAGE_URL, null);
            if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_3_0) < 0) {
                C2333f.m13754b(f11507a, "shareToMobileQQ -- QQ Version is < 4.3.0 ");
                m13333b(activity, bundle, iUiListener);
            } else {
                C2333f.m13754b(f11507a, "shareToMobileQQ -- QQ Version is > 4.3.0 ");
                C2222a.m13343a((Context) activity, string, new C22162(this, bundle, string2, string3, iUiListener, activity));
            }
        } else if (TextUtils.isEmpty(string2) && TextUtils.isEmpty(string3)) {
            if (iUiListener != null) {
                iUiListener.onError(new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, null));
                C2333f.m13759e(f11507a, Constants.MSG_SHARE_NOSD_ERROR);
            }
            C2341d.m13784a().m13785a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, Constants.MSG_SHARE_NOSD_ERROR);
            return;
        } else if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_3_0) >= 0) {
            m13333b(activity, bundle, iUiListener);
        } else {
            new AsynLoadImg(activity).save(string, new C22151(this, bundle, string2, string3, iUiListener, activity));
        }
        C2333f.m13757c(C2333f.f12014d, "shareToMobileQQ() -- end");
    }

    private void m13333b(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2333f.m13757c(C2333f.f12014d, "doShareToQQ() -- start");
        StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_fri?src_type=app&version=1&file_type=news");
        Object string = bundle.getString(SHARE_TO_QQ_IMAGE_URL);
        Object string2 = bundle.getString(SHARE_TO_QQ_TITLE);
        Object string3 = bundle.getString(SHARE_TO_QQ_SUMMARY);
        Object string4 = bundle.getString(SHARE_TO_QQ_TARGET_URL);
        Object string5 = bundle.getString(SHARE_TO_QQ_AUDIO_URL);
        int i = bundle.getInt(SHARE_TO_QQ_KEY_TYPE, SHARE_TO_QQ_TYPE_DEFAULT);
        int i2 = bundle.getInt(SHARE_TO_QQ_EXT_INT, 0);
        Object string6 = bundle.getString(SHARE_TO_QQ_EXT_STR);
        String applicationLable = Util.getApplicationLable(activity);
        if (applicationLable == null) {
            applicationLable = bundle.getString(SHARE_TO_QQ_APP_NAME);
        }
        Object string7 = bundle.getString(SHARE_TO_QQ_IMAGE_LOCAL_URL);
        Object appId = this.mToken.getAppId();
        String openId = this.mToken.getOpenId();
        C2333f.m13751a(f11507a, "doShareToQQ -- openid: " + openId);
        if (!TextUtils.isEmpty(string)) {
            stringBuffer.append("&image_url=" + Base64.encodeToString(Util.getBytesUTF8(string), SHARE_TO_QQ_TYPE_AUDIO));
        }
        if (!TextUtils.isEmpty(string7)) {
            stringBuffer.append("&file_data=" + Base64.encodeToString(Util.getBytesUTF8(string7), SHARE_TO_QQ_TYPE_AUDIO));
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append("&title=" + Base64.encodeToString(Util.getBytesUTF8(string2), SHARE_TO_QQ_TYPE_AUDIO));
        }
        if (!TextUtils.isEmpty(string3)) {
            stringBuffer.append("&description=" + Base64.encodeToString(Util.getBytesUTF8(string3), SHARE_TO_QQ_TYPE_AUDIO));
        }
        if (!TextUtils.isEmpty(appId)) {
            stringBuffer.append("&share_id=" + appId);
        }
        if (!TextUtils.isEmpty(string4)) {
            stringBuffer.append("&url=" + Base64.encodeToString(Util.getBytesUTF8(string4), SHARE_TO_QQ_TYPE_AUDIO));
        }
        if (!TextUtils.isEmpty(applicationLable)) {
            if (applicationLable.length() > 20) {
                applicationLable = applicationLable.substring(0, 20) + "...";
            }
            stringBuffer.append("&app_name=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), SHARE_TO_QQ_TYPE_AUDIO));
        }
        if (!TextUtils.isEmpty(openId)) {
            stringBuffer.append("&open_id=" + Base64.encodeToString(Util.getBytesUTF8(openId), SHARE_TO_QQ_TYPE_AUDIO));
        }
        if (!TextUtils.isEmpty(string5)) {
            stringBuffer.append("&audioUrl=" + Base64.encodeToString(Util.getBytesUTF8(string5), SHARE_TO_QQ_TYPE_AUDIO));
        }
        stringBuffer.append("&req_type=" + Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i)), SHARE_TO_QQ_TYPE_AUDIO));
        if (!TextUtils.isEmpty(string6)) {
            stringBuffer.append("&share_qq_ext_str=" + Base64.encodeToString(Util.getBytesUTF8(string6), SHARE_TO_QQ_TYPE_AUDIO));
        }
        stringBuffer.append("&cflag=" + Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i2)), SHARE_TO_QQ_TYPE_AUDIO));
        C2333f.m13751a(f11507a, "doShareToQQ -- url: " + stringBuffer.toString());
        String[] strArr = new String[SHARE_TO_QQ_TYPE_DEFAULT];
        strArr[0] = "shareToNativeQQ";
        C2186a.m13215a(Global.getContext(), this.mToken, "requireApi", strArr);
        this.mActivityIntent = new Intent("android.intent.action.VIEW");
        this.mActivityIntent.setData(Uri.parse(stringBuffer.toString()));
        this.mActivityIntent.putExtra("pkg_name", activity.getPackageName());
        if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_6_0) < 0) {
            C2333f.m13757c(f11507a, "doShareToQQ, qqver below 4.6.");
            if (hasActivityForIntent()) {
                startAssitActivity(activity, iUiListener);
            }
        } else {
            Object obj = TemporaryStorage.set(SystemUtils.QQ_SHARE_CALLBACK_ACTION, iUiListener);
            if (obj != null) {
                ((IUiListener) obj).onCancel();
                C2333f.m13757c(f11507a, "doShareToQQ, last listener is not null, cancel it.");
            }
            if (hasActivityForIntent()) {
                AssistActivity.isQQMobileShare = true;
                startAssistActivity(activity, Constants.REQUEST_QQ_SHARE);
            }
        }
        if (hasActivityForIntent()) {
            C2341d.m13784a().m13788a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_QQ, Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, Constants.VIA_TO_TYPE_QQ_DISCUSS_GROUP, Constants.VIA_RESULT_SUCCESS, this.mViaShareQQType, Constants.VIA_RESULT_SUCCESS, Constants.VIA_TO_TYPE_QQ_GROUP, Constants.VIA_RESULT_SUCCESS);
            C2341d.m13784a().m13785a(0, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, C2915a.f14760f);
        } else {
            C2341d.m13784a().m13788a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_QQ, Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, Constants.VIA_TO_TYPE_QQ_DISCUSS_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP, this.mViaShareQQType, Constants.VIA_RESULT_SUCCESS, Constants.VIA_TO_TYPE_QQ_GROUP, Constants.VIA_RESULT_SUCCESS);
            C2341d.m13784a().m13785a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, "hasActivityForIntent fail");
        }
        C2333f.m13757c(C2333f.f12014d, "doShareToQQ() --end");
    }

    private void m13334c(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2333f.m13757c(C2333f.f12014d, "shareToH5QQ() -- start");
        Object obj = TemporaryStorage.set(SystemUtils.QQ_SHARE_CALLBACK_ACTION, iUiListener);
        if (obj != null) {
            C2333f.m13757c(f11507a, "shareToH5QQ, last listener is not null, cancel it.");
            ((IUiListener) obj).onCancel();
        }
        StringBuffer stringBuffer = new StringBuffer("http://openmobile.qq.com/api/check?page=shareindex.html&style=9");
        if (bundle == null) {
            bundle = new Bundle();
        }
        stringBuffer = m13329a(stringBuffer, bundle);
        String[] strArr = new String[SHARE_TO_QQ_TYPE_DEFAULT];
        strArr[0] = "shareToH5QQ";
        C2186a.m13215a(Global.getContext(), this.mToken, "requireApi", strArr);
        Bundle bundle2 = new Bundle();
        bundle2.putString("callbackAction", SystemUtils.QQ_SHARE_CALLBACK_ACTION);
        bundle2.putString("viaShareType", this.mViaShareQQType);
        bundle2.putString(SocialConstants.PARAM_URL, stringBuffer.toString());
        bundle2.putString("openId", this.mToken.getOpenId());
        bundle2.putString("appId", this.mToken.getAppId());
        startAssistActivity(activity, bundle2, Constants.REQUEST_QQ_SHARE);
        C2341d.m13784a().m13785a(0, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, "shareToH5QQ");
        C2333f.m13757c(C2333f.f12014d, "shareToH5QQ() --end");
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
    }

    public void releaseResource() {
        TemporaryStorage.remove(SystemUtils.QQ_SHARE_CALLBACK_ACTION);
    }

    public void shareToQQ(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2333f.m13757c(f11507a, "shareToQQ() -- start.");
        String string = bundle.getString(SHARE_TO_QQ_IMAGE_URL);
        String string2 = bundle.getString(SHARE_TO_QQ_TITLE);
        String string3 = bundle.getString(SHARE_TO_QQ_SUMMARY);
        String string4 = bundle.getString(SHARE_TO_QQ_TARGET_URL);
        String string5 = bundle.getString(SHARE_TO_QQ_IMAGE_LOCAL_URL);
        int i = bundle.getInt(SHARE_TO_QQ_KEY_TYPE, SHARE_TO_QQ_TYPE_DEFAULT);
        C2333f.m13757c(f11507a, "shareToQQ -- type: " + i);
        switch (i) {
            case SHARE_TO_QQ_TYPE_DEFAULT /*1*/:
                this.mViaShareQQType = Constants.VIA_TO_TYPE_QQ_GROUP;
                break;
            case SHARE_TO_QQ_TYPE_AUDIO /*2*/:
                this.mViaShareQQType = Constants.VIA_TO_TYPE_QQ_DISCUSS_GROUP;
                break;
            case SHARE_TO_QQ_TYPE_IMAGE /*5*/:
                this.mViaShareQQType = Constants.VIA_SSO_LOGIN;
                break;
            case SHARE_TO_QQ_TYPE_APP /*6*/:
                this.mViaShareQQType = Constants.VIA_TO_TYPE_QZONE;
                break;
        }
        if (i == SHARE_TO_QQ_TYPE_APP) {
            if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_5_0_0) < 0) {
                iUiListener.onError(new UiError(-15, Constants.MSG_PARAM_APPSHARE_TOO_LOW, null));
                C2333f.m13759e(f11507a, "shareToQQ, app share is not support below qq5.0.");
                C2341d.m13784a().m13785a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, "shareToQQ, app share is not support below qq5.0.");
                return;
            }
            string4 = ServerSetting.APP_DETAIL_PAGE;
            Object[] objArr = new Object[SHARE_TO_QQ_TYPE_AUDIO];
            objArr[0] = this.mToken.getAppId();
            objArr[SHARE_TO_QQ_TYPE_DEFAULT] = "mqq";
            string4 = String.format(string4, objArr);
            bundle.putString(SHARE_TO_QQ_TARGET_URL, string4);
        }
        if (Util.hasSDCard() || SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_5_0) >= 0) {
            if (i == SHARE_TO_QQ_TYPE_IMAGE) {
                if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_3_0) < 0) {
                    iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_QQ_VERSION_ERROR, null));
                    C2333f.m13759e(f11507a, "shareToQQ, version below 4.3 is not support.");
                    C2341d.m13784a().m13785a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, "shareToQQ, version below 4.3 is not support.");
                    return;
                } else if (!Util.fileExists(string5)) {
                    iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, null));
                    C2333f.m13759e(f11507a, "shareToQQ -- error: \u975e\u6cd5\u7684\u56fe\u7247\u5730\u5740!");
                    C2341d.m13784a().m13785a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR);
                    return;
                }
            }
            if (i != SHARE_TO_QQ_TYPE_IMAGE) {
                if (TextUtils.isEmpty(string4) || !(string4.startsWith("http://") || string4.startsWith("https://"))) {
                    iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_ERROR, null));
                    C2333f.m13759e(f11507a, "shareToQQ, targetUrl is empty or illegal..");
                    C2341d.m13784a().m13785a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, "shareToQQ, targetUrl is empty or illegal..");
                    return;
                } else if (TextUtils.isEmpty(string2)) {
                    iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_TITLE_NULL_ERROR, null));
                    C2333f.m13759e(f11507a, "shareToQQ, title is empty.");
                    C2341d.m13784a().m13785a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, "shareToQQ, title is empty.");
                    return;
                }
            }
            if (TextUtils.isEmpty(string) || string.startsWith("http://") || string.startsWith("https://") || new File(string).exists()) {
                if (!TextUtils.isEmpty(string2) && string2.length() > QQ_SHARE_TITLE_MAX_LENGTH) {
                    bundle.putString(SHARE_TO_QQ_TITLE, Util.subString(string2, QQ_SHARE_TITLE_MAX_LENGTH, null, null));
                }
                if (!TextUtils.isEmpty(string3) && string3.length() > QQ_SHARE_SUMMARY_MAX_LENGTH) {
                    bundle.putString(SHARE_TO_QQ_SUMMARY, Util.subString(string3, QQ_SHARE_SUMMARY_MAX_LENGTH, null, null));
                }
                if (Util.isMobileQQSupportShare(activity)) {
                    m13330a(activity, bundle, iUiListener);
                } else {
                    m13334c(activity, bundle, iUiListener);
                }
                C2333f.m13757c(f11507a, "shareToQQ() -- end.");
                return;
            }
            iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, null));
            C2333f.m13759e(f11507a, " shareToQQ, image url is emprty or illegal.");
            C2341d.m13784a().m13785a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, "shareToQQ, image url is emprty or illegal.");
            return;
        }
        iUiListener.onError(new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, null));
        C2333f.m13759e(f11507a, "shareToQQ sdcard is null--end");
        C2341d.m13784a().m13785a(SHARE_TO_QQ_TYPE_DEFAULT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QQ_TYPE_DEFAULT, "shareToQQ sdcard is null");
    }
}
