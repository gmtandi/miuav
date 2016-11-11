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
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.p128a.C2186a;
import com.tencent.open.SocialConstants;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.p134b.C2341d;
import com.tencent.open.utils.AsynLoadImgBack;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.TemporaryStorage;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import it.p074a.p075a.C2799f;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.p122a.p123a.C2915a;

public class QzoneShare extends BaseApi {
    public static final String SHARE_TO_QQ_APP_NAME = "appName";
    public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
    public static final String SHARE_TO_QQ_EXT_INT = "cflag";
    public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
    public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
    public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
    public static final String SHARE_TO_QQ_SITE = "site";
    public static final String SHARE_TO_QQ_SUMMARY = "summary";
    public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
    public static final String SHARE_TO_QQ_TITLE = "title";
    public static final String SHARE_TO_QZONE_KEY_TYPE = "req_type";
    public static final int SHARE_TO_QZONE_TYPE_APP = 6;
    public static final int SHARE_TO_QZONE_TYPE_IMAGE = 5;
    public static final int SHARE_TO_QZONE_TYPE_IMAGE_TEXT = 1;
    public static final int SHARE_TO_QZONE_TYPE_NO_TYPE = 0;
    private boolean f11512a;
    private boolean f11513b;
    private boolean f11514c;
    private boolean f11515d;
    public String mViaShareQzoneType;

    /* renamed from: com.tencent.connect.share.QzoneShare.1 */
    class C22171 implements AsynLoadImgBack {
        final /* synthetic */ Bundle f11508a;
        final /* synthetic */ Activity f11509b;
        final /* synthetic */ IUiListener f11510c;
        final /* synthetic */ QzoneShare f11511d;

        C22171(QzoneShare qzoneShare, Bundle bundle, Activity activity, IUiListener iUiListener) {
            this.f11511d = qzoneShare;
            this.f11508a = bundle;
            this.f11509b = activity;
            this.f11510c = iUiListener;
        }

        public void batchSaved(int i, ArrayList<String> arrayList) {
            if (i == 0) {
                this.f11508a.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, arrayList);
            }
            this.f11511d.m13336a(this.f11509b, this.f11508a, this.f11510c);
        }

        public void saved(int i, String str) {
        }
    }

    public QzoneShare(Context context, QQToken qQToken) {
        super(qQToken);
        this.mViaShareQzoneType = C2915a.f14760f;
        this.f11512a = true;
        this.f11513b = false;
        this.f11514c = false;
        this.f11515d = false;
    }

    private StringBuffer m13335a(StringBuffer stringBuffer, Bundle bundle) {
        C2333f.m13757c(C2333f.f12014d, "fillShareToQQParams() --start");
        ArrayList stringArrayList = bundle.getStringArrayList(SHARE_TO_QQ_IMAGE_URL);
        Object string = bundle.getString(SHARE_TO_QQ_APP_NAME);
        int i = bundle.getInt(SHARE_TO_QZONE_KEY_TYPE, SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        String string2 = bundle.getString(SHARE_TO_QQ_TITLE);
        String string3 = bundle.getString(SHARE_TO_QQ_SUMMARY);
        bundle.putString("appId", this.mToken.getAppId());
        bundle.putString("sdkp", "a");
        bundle.putString("sdkv", Constants.SDK_VERSION);
        bundle.putString("status_os", VERSION.RELEASE);
        bundle.putString("status_machine", Build.MODEL);
        String str = "...";
        if (!Util.isEmpty(string2) && string2.length() > 40) {
            bundle.putString(SHARE_TO_QQ_TITLE, string2.substring(0, 40) + "...");
        }
        if (!Util.isEmpty(string3) && string3.length() > 80) {
            bundle.putString(SHARE_TO_QQ_SUMMARY, string3.substring(0, 80) + "...");
        }
        if (!TextUtils.isEmpty(string)) {
            bundle.putString(SHARE_TO_QQ_SITE, string);
        }
        if (stringArrayList != null) {
            int size = stringArrayList.size();
            String[] strArr = new String[size];
            for (int i2 = 0; i2 < size; i2 += SHARE_TO_QZONE_TYPE_IMAGE_TEXT) {
                strArr[i2] = (String) stringArrayList.get(i2);
            }
            bundle.putStringArray(SHARE_TO_QQ_IMAGE_URL, strArr);
        }
        bundle.putString(SocialConstants.PARAM_TYPE, String.valueOf(i));
        stringBuffer.append("&" + Util.encodeUrl(bundle).replaceAll("\\+", "%20"));
        C2333f.m13757c(C2333f.f12014d, "fillShareToQQParams() --end");
        return stringBuffer;
    }

    private void m13336a(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2333f.m13757c(C2333f.f12014d, "doshareToQzone() --start");
        StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_qzone?src_type=app&version=1&file_type=news");
        ArrayList stringArrayList = bundle.getStringArrayList(SHARE_TO_QQ_IMAGE_URL);
        Object string = bundle.getString(SHARE_TO_QQ_TITLE);
        Object string2 = bundle.getString(SHARE_TO_QQ_SUMMARY);
        Object string3 = bundle.getString(SHARE_TO_QQ_TARGET_URL);
        String string4 = bundle.getString(SHARE_TO_QQ_AUDIO_URL);
        int i = bundle.getInt(SHARE_TO_QZONE_KEY_TYPE, SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        Object string5 = bundle.getString(SHARE_TO_QQ_APP_NAME);
        int i2 = bundle.getInt(SHARE_TO_QQ_EXT_INT, 0);
        String string6 = bundle.getString(SHARE_TO_QQ_EXT_STR);
        CharSequence appId = this.mToken.getAppId();
        String openId = this.mToken.getOpenId();
        C2333f.m13754b("doshareToQzone", "openId:" + openId);
        if (stringArrayList != null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            int size = stringArrayList.size() > 9 ? 9 : stringArrayList.size();
            for (int i3 = 0; i3 < size; i3 += SHARE_TO_QZONE_TYPE_IMAGE_TEXT) {
                stringBuffer2.append(URLEncoder.encode((String) stringArrayList.get(i3)));
                if (i3 != size - 1) {
                    stringBuffer2.append(";");
                }
            }
            stringBuffer.append("&image_url=" + Base64.encodeToString(Util.getBytesUTF8(stringBuffer2.toString()), 2));
        }
        if (!TextUtils.isEmpty(string)) {
            stringBuffer.append("&title=" + Base64.encodeToString(Util.getBytesUTF8(string), 2));
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append("&description=" + Base64.encodeToString(Util.getBytesUTF8(string2), 2));
        }
        if (!TextUtils.isEmpty(appId)) {
            stringBuffer.append("&share_id=" + appId);
        }
        if (!TextUtils.isEmpty(string3)) {
            stringBuffer.append("&url=" + Base64.encodeToString(Util.getBytesUTF8(string3), 2));
        }
        if (!TextUtils.isEmpty(string5)) {
            stringBuffer.append("&app_name=" + Base64.encodeToString(Util.getBytesUTF8(string5), 2));
        }
        if (!Util.isEmpty(openId)) {
            stringBuffer.append("&open_id=" + Base64.encodeToString(Util.getBytesUTF8(openId), 2));
        }
        if (!Util.isEmpty(string4)) {
            stringBuffer.append("&audioUrl=" + Base64.encodeToString(Util.getBytesUTF8(string4), 2));
        }
        stringBuffer.append("&req_type=" + Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i)), 2));
        if (!Util.isEmpty(string6)) {
            stringBuffer.append("&share_qq_ext_str=" + Base64.encodeToString(Util.getBytesUTF8(string6), 2));
        }
        stringBuffer.append("&cflag=" + Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i2)), 2));
        C2333f.m13754b("doshareToQzone, url: ", stringBuffer.toString());
        String[] strArr = new String[SHARE_TO_QZONE_TYPE_IMAGE_TEXT];
        strArr[0] = "shareToNativeQQ";
        C2186a.m13215a(Global.getContext(), this.mToken, "requireApi", strArr);
        this.mActivityIntent = new Intent("android.intent.action.VIEW");
        this.mActivityIntent.setData(Uri.parse(stringBuffer.toString()));
        this.mActivityIntent.putExtra("pkg_name", activity.getPackageName());
        if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_6_0) < 0) {
            if (hasActivityForIntent()) {
                startAssitActivity(activity, iUiListener);
            }
            C2333f.m13757c(C2333f.f12014d, "doShareToQzone() -- QQ Version is < 4.6.0");
        } else {
            C2333f.m13757c(C2333f.f12014d, "doShareToQzone() -- QQ Version is > 4.6.0");
            Object obj = TemporaryStorage.set(SystemUtils.QZONE_SHARE_CALLBACK_ACTION, iUiListener);
            if (obj != null) {
                C2333f.m13757c(C2333f.f12014d, "doShareToQzone() -- do listener onCancel()");
                ((IUiListener) obj).onCancel();
            }
            if (hasActivityForIntent()) {
                startAssistActivity(activity, Constants.REQUEST_QZONE_SHARE);
            }
        }
        if (hasActivityForIntent()) {
            C2341d.m13784a().m13788a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, Constants.VIA_TO_TYPE_QQ_DISCUSS_GROUP, Constants.VIA_RESULT_SUCCESS, this.mViaShareQzoneType, Constants.VIA_RESULT_SUCCESS, Constants.VIA_TO_TYPE_QQ_GROUP, Constants.VIA_RESULT_SUCCESS);
            C2341d.m13784a().m13785a(0, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, C2915a.f14760f);
        } else {
            C2341d.m13784a().m13788a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, Constants.VIA_TO_TYPE_QQ_DISCUSS_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP, this.mViaShareQzoneType, Constants.VIA_RESULT_SUCCESS, Constants.VIA_TO_TYPE_QQ_GROUP, Constants.VIA_RESULT_SUCCESS);
            C2341d.m13784a().m13785a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "hasActivityForIntent fail");
        }
        C2333f.m13757c(C2333f.f12014d, "doShareToQzone() --end");
    }

    private void m13338b(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2333f.m13751a(C2333f.f12014d, "shareToH5Qzone() -- start");
        Object obj = TemporaryStorage.set(SystemUtils.QZONE_SHARE_CALLBACK_ACTION, iUiListener);
        if (obj != null) {
            C2333f.m13757c(C2333f.f12014d, "shareToH5Qzone() -- do listener onCancel()");
            ((IUiListener) obj).onCancel();
        }
        StringBuffer stringBuffer = new StringBuffer("http://openmobile.qq.com/api/check2?page=qzshare.html&loginpage=loginindex.html&logintype=qzone");
        if (bundle == null) {
            bundle = new Bundle();
        }
        stringBuffer = m13335a(stringBuffer, bundle);
        String[] strArr = new String[SHARE_TO_QZONE_TYPE_IMAGE_TEXT];
        strArr[0] = "shareToH5QQ";
        C2186a.m13215a(Global.getContext(), this.mToken, "requireApi", strArr);
        Bundle bundle2 = new Bundle();
        bundle2.putString("callbackAction", SystemUtils.QZONE_SHARE_CALLBACK_ACTION);
        bundle2.putString("viaShareType", this.mViaShareQzoneType);
        bundle2.putString(SocialConstants.PARAM_URL, stringBuffer.toString());
        bundle2.putString("openId", this.mToken.getOpenId());
        bundle2.putString("appId", this.mToken.getAppId());
        startAssistActivity(activity, bundle2, Constants.REQUEST_QZONE_SHARE);
        C2341d.m13784a().m13785a(0, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "shareToH5Qzone()");
        C2333f.m13751a(C2333f.f12014d, "shareToH5Qzone() --end");
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
    }

    public void releaseResource() {
        TemporaryStorage.remove(SystemUtils.QZONE_SHARE_CALLBACK_ACTION);
    }

    public void shareToQzone(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2333f.m13757c(C2333f.f12014d, "shareToQzone() -- start");
        if (bundle == null) {
            iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_NULL_ERROR, null));
            C2341d.m13784a().m13785a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, Constants.MSG_PARAM_NULL_ERROR);
            return;
        }
        String string = bundle.getString(SHARE_TO_QQ_TITLE);
        String string2 = bundle.getString(SHARE_TO_QQ_SUMMARY);
        String string3 = bundle.getString(SHARE_TO_QQ_TARGET_URL);
        ArrayList stringArrayList = bundle.getStringArrayList(SHARE_TO_QQ_IMAGE_URL);
        Object applicationLable = Util.getApplicationLable(activity);
        if (applicationLable == null) {
            applicationLable = bundle.getString(SHARE_TO_QQ_APP_NAME);
        } else if (applicationLable.length() > 20) {
            applicationLable = applicationLable.substring(0, 20) + "...";
        }
        int i = bundle.getInt(SHARE_TO_QZONE_KEY_TYPE);
        switch (i) {
            case SHARE_TO_QZONE_TYPE_IMAGE_TEXT /*1*/:
                this.mViaShareQzoneType = Constants.VIA_TO_TYPE_QQ_GROUP;
                break;
            case SHARE_TO_QZONE_TYPE_IMAGE /*5*/:
                this.mViaShareQzoneType = Constants.VIA_SSO_LOGIN;
                break;
            case SHARE_TO_QZONE_TYPE_APP /*6*/:
                this.mViaShareQzoneType = Constants.VIA_TO_TYPE_QZONE;
                break;
            default:
                this.mViaShareQzoneType = Constants.VIA_TO_TYPE_QQ_GROUP;
                break;
        }
        String str;
        switch (i) {
            case SHARE_TO_QZONE_TYPE_IMAGE_TEXT /*1*/:
                this.f11512a = true;
                this.f11513b = false;
                this.f11514c = true;
                this.f11515d = false;
                str = string3;
                string3 = string;
                string = str;
                break;
            case SHARE_TO_QZONE_TYPE_IMAGE /*5*/:
                iUiListener.onError(new UiError(-5, Constants.MSG_SHARE_TYPE_ERROR, null));
                C2333f.m13759e(C2333f.f12014d, "shareToQzone() error--end\u6682\u4e0d\u652f\u6301\u7eaf\u56fe\u7247\u5206\u4eab\u5230\u7a7a\u95f4\uff0c\u5efa\u8bae\u4f7f\u7528\u56fe\u6587\u5206\u4eab");
                C2341d.m13784a().m13785a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "shareToQzone() \u6682\u4e0d\u652f\u6301\u7eaf\u56fe\u7247\u5206\u4eab\u5230\u7a7a\u95f4\uff0c\u5efa\u8bae\u4f7f\u7528\u56fe\u6587\u5206\u4eab");
                return;
            case SHARE_TO_QZONE_TYPE_APP /*6*/:
                if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_5_0_0) >= 0) {
                    string3 = String.format(ServerSetting.APP_DETAIL_PAGE, new Object[]{this.mToken.getAppId(), "mqq"});
                    bundle.putString(SHARE_TO_QQ_TARGET_URL, string3);
                    str = string3;
                    string3 = string;
                    string = str;
                    break;
                }
                iUiListener.onError(new UiError(-15, Constants.MSG_PARAM_APPSHARE_TOO_LOW, null));
                C2333f.m13754b(C2333f.f12014d, "-->shareToQzone, app share is not support below qq5.0.");
                C2341d.m13784a().m13785a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "shareToQzone, app share is not support below qq5.0.");
                return;
            default:
                if (!Util.isEmpty(string) || !Util.isEmpty(string2)) {
                    this.f11512a = true;
                } else if (stringArrayList == null || stringArrayList.size() == 0) {
                    string = "\u6765\u81ea" + applicationLable + "\u7684\u5206\u4eab";
                    this.f11512a = true;
                } else {
                    this.f11512a = false;
                }
                this.f11513b = false;
                this.f11514c = true;
                this.f11515d = false;
                str = string3;
                string3 = string;
                Object obj = str;
                break;
        }
        if (Util.hasSDCard() || SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_5_0) >= 0) {
            String str2;
            if (this.f11512a) {
                if (TextUtils.isEmpty(obj)) {
                    iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_TARGETURL_NULL_ERROR, null));
                    C2333f.m13759e(C2333f.f12014d, "shareToQzone() targetUrl null error--end");
                    C2341d.m13784a().m13785a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, Constants.MSG_PARAM_TARGETURL_NULL_ERROR);
                    return;
                } else if (!Util.isValidUrl(obj)) {
                    iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_TARGETURL_ERROR, null));
                    C2333f.m13759e(C2333f.f12014d, "shareToQzone() targetUrl error--end");
                    C2341d.m13784a().m13785a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, Constants.MSG_PARAM_TARGETURL_ERROR);
                    return;
                }
            }
            if (this.f11513b) {
                string = C2915a.f14760f;
                string3 = C2915a.f14760f;
                bundle.putString(SHARE_TO_QQ_TITLE, string);
                bundle.putString(SHARE_TO_QQ_SUMMARY, string3);
            } else if (this.f11514c && Util.isEmpty(string3)) {
                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_TITLE_NULL_ERROR, null));
                C2333f.m13759e(C2333f.f12014d, "shareToQzone() title is null--end");
                C2341d.m13784a().m13785a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "shareToQzone() title is null");
                return;
            } else {
                if (!Util.isEmpty(string3) && string3.length() > C2799f.f14282t) {
                    bundle.putString(SHARE_TO_QQ_TITLE, Util.subString(string3, C2799f.f14282t, null, null));
                }
                if (!Util.isEmpty(string2) && string2.length() > 600) {
                    bundle.putString(SHARE_TO_QQ_SUMMARY, Util.subString(string2, 600, null, null));
                }
            }
            if (!TextUtils.isEmpty(applicationLable)) {
                bundle.putString(SHARE_TO_QQ_APP_NAME, applicationLable);
            }
            if (stringArrayList != null && (stringArrayList == null || stringArrayList.size() != 0)) {
                for (int i2 = 0; i2 < stringArrayList.size(); i2 += SHARE_TO_QZONE_TYPE_IMAGE_TEXT) {
                    str2 = (String) stringArrayList.get(i2);
                    if (!(Util.isValidUrl(str2) || Util.isValidPath(str2))) {
                        stringArrayList.remove(i2);
                    }
                }
                if (stringArrayList.size() == 0) {
                    iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, null));
                    C2333f.m13759e(C2333f.f12014d, "shareToQzone() MSG_PARAM_IMAGE_URL_FORMAT_ERROR--end");
                    C2341d.m13784a().m13785a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "shareToQzone() \u975e\u6cd5\u7684\u56fe\u7247\u5730\u5740!");
                    return;
                }
                bundle.putStringArrayList(SHARE_TO_QQ_IMAGE_URL, stringArrayList);
            } else if (this.f11515d) {
                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_ERROR, null));
                C2333f.m13759e(C2333f.f12014d, "shareToQzone() imageUrl is null -- end");
                C2341d.m13784a().m13785a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "shareToQzone() imageUrl is null");
                return;
            }
            if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_6_0) >= 0) {
                C2222a.m13344a((Context) activity, stringArrayList, new C22171(this, bundle, activity, iUiListener));
            } else if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_2_0) < 0 || SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_6_0) >= 0) {
                m13338b(activity, bundle, iUiListener);
            } else {
                QQShare qQShare = new QQShare(activity, this.mToken);
                if (stringArrayList != null && stringArrayList.size() > 0) {
                    str2 = (String) stringArrayList.get(0);
                    if (i != SHARE_TO_QZONE_TYPE_IMAGE || Util.fileExists(str2)) {
                        bundle.putString(SHARE_TO_QQ_IMAGE_LOCAL_URL, str2);
                    } else {
                        iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_MUST_BE_LOCAL, null));
                        C2333f.m13759e(C2333f.f12014d, "shareToQzone()\u624bQ\u7248\u672c\u8fc7\u4f4e\uff0c\u7eaf\u56fe\u5206\u4eab\u4e0d\u652f\u6301\u7f51\u8def\u56fe\u7247");
                        C2341d.m13784a().m13785a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "shareToQzone()\u624bQ\u7248\u672c\u8fc7\u4f4e\uff0c\u7eaf\u56fe\u5206\u4eab\u4e0d\u652f\u6301\u7f51\u8def\u56fe\u7247");
                        return;
                    }
                }
                if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_5_0) >= 0) {
                    bundle.putInt(SHARE_TO_QQ_EXT_INT, SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
                }
                qQShare.shareToQQ(activity, bundle, iUiListener);
            }
            C2333f.m13757c(C2333f.f12014d, "shareToQzone() --end");
            return;
        }
        iUiListener.onError(new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, null));
        C2333f.m13759e(C2333f.f12014d, "shareToQzone() sdcard is null--end");
        C2341d.m13784a().m13785a(SHARE_TO_QZONE_TYPE_IMAGE_TEXT, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, SHARE_TO_QZONE_TYPE_IMAGE_TEXT, Constants.MSG_SHARE_NOSD_ERROR);
    }
}
