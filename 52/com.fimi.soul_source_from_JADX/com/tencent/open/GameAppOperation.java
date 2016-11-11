package com.tencent.open;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Toast;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.media.player.IMediaPlayer;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.p128a.C2186a;
import com.tencent.connect.share.QQShare;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.p134b.C2340c;
import com.tencent.open.p134b.C2341d;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.TemporaryStorage;
import com.tencent.open.utils.ThreadManager;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p152c.p156c.C2951i;

public class GameAppOperation extends BaseApi {
    public static final String GAME_FRIEND_ADD_MESSAGE = "add_msg";
    public static final String GAME_FRIEND_LABEL = "friend_label";
    public static final String GAME_FRIEND_OPENID = "fopen_id";
    public static final String GAME_SIGNATURE = "signature";
    public static final String GAME_UNION_ID = "unionid";
    public static final String GAME_UNION_NAME = "union_name";
    public static final String GAME_ZONE_ID = "zoneid";
    public static final char PIC_SYMBOLE = '\u0014';
    public static final String QQFAV_DATALINE_APPNAME = "app_name";
    public static final String QQFAV_DATALINE_AUDIOURL = "audioUrl";
    public static final String QQFAV_DATALINE_DESCRIPTION = "description";
    public static final String QQFAV_DATALINE_FILEDATA = "file_data";
    public static final String QQFAV_DATALINE_IMAGEURL = "image_url";
    public static final String QQFAV_DATALINE_OPENID = "open_id";
    public static final String QQFAV_DATALINE_REQTYPE = "req_type";
    public static final String QQFAV_DATALINE_SHAREID = "share_id";
    public static final String QQFAV_DATALINE_SRCTYPE = "src_type";
    public static final String QQFAV_DATALINE_TITLE = "title";
    public static final int QQFAV_DATALINE_TYPE_AUDIO = 2;
    public static final int QQFAV_DATALINE_TYPE_DEFAULT = 1;
    public static final int QQFAV_DATALINE_TYPE_IMAGE_TEXT = 5;
    public static final int QQFAV_DATALINE_TYPE_TEXT = 6;
    public static final String QQFAV_DATALINE_URL = "url";
    public static final String QQFAV_DATALINE_VERSION = "version";
    public static final String SHARE_PRIZE_ACTIVITY_ID = "activityid";
    public static final String SHARE_PRIZE_IMAGE_URL = "imageUrl";
    public static final String SHARE_PRIZE_SHARE_ID = "shareid";
    public static final String SHARE_PRIZE_SHARE_ID_LIST = "shareid_list";
    public static final String SHARE_PRIZE_SUMMARY = "summary";
    public static final int SHARE_PRIZE_SUMMARY_MAX_LENGTH = 60;
    public static final String SHARE_PRIZE_TARGET_URL = "targetUrl";
    public static final String SHARE_PRIZE_TITLE = "title";
    public static final int SHARE_PRIZE_TITLE_MAX_LENGTH = 45;
    public static final String TROOPBAR_ID = "troopbar_id";
    private static final String f11829a;

    /* renamed from: com.tencent.open.GameAppOperation.1 */
    class C22811 implements Runnable {
        final /* synthetic */ IUiListener f11812a;
        final /* synthetic */ String f11813b;
        final /* synthetic */ Activity f11814c;
        final /* synthetic */ Bundle f11815d;
        final /* synthetic */ GameAppOperation f11816e;

        C22811(GameAppOperation gameAppOperation, IUiListener iUiListener, String str, Activity activity, Bundle bundle) {
            this.f11816e = gameAppOperation;
            this.f11812a = iUiListener;
            this.f11813b = str;
            this.f11814c = activity;
            this.f11815d = bundle;
        }

        public void run() {
            Bundle a = this.f11816e.m13576b();
            if (a == null) {
                String str = "accesstoken or openid or appid is null, please login first!";
                C2333f.m13759e(GameAppOperation.f11829a, str);
                this.f11812a.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, str));
                return;
            }
            a.putString(GameAppOperation.SHARE_PRIZE_ACTIVITY_ID, this.f11813b);
            try {
                JSONObject request = HttpUtils.request(this.f11816e.mToken, this.f11814c.getApplicationContext(), ServerSetting.URL_PRIZE_MAKE_SHARE_URL, a, C2951i.f14860a);
                try {
                    int i = request.getInt("ret");
                    int i2 = request.getInt("subCode");
                    if (i == 0 && i2 == 0) {
                        this.f11815d.putString(GameAppOperation.SHARE_PRIZE_TARGET_URL, request.getString("share_url"));
                        new QQShare(this.f11814c.getApplicationContext(), this.f11816e.mToken).shareToQQ(this.f11814c, this.f11815d, this.f11812a);
                        return;
                    }
                    this.f11812a.onError(new UiError(i, "make_share_url error.", request.getString(SocialConstants.PARAM_SEND_MSG)));
                } catch (JSONException e) {
                    C2333f.m13759e(GameAppOperation.f11829a, "JSONException occur in make_share_url, errorMsg: " + e.getMessage());
                    this.f11812a.onError(new UiError(-4, Constants.MSG_JSON_ERROR, C2915a.f14760f));
                }
            } catch (Throwable e2) {
                C2333f.m13755b(GameAppOperation.f11829a, "Exception occur in make_share_url", e2);
                this.f11812a.onError(new UiError(-2, Constants.MSG_IO_ERROR, e2.getMessage()));
            }
        }
    }

    /* renamed from: com.tencent.open.GameAppOperation.2 */
    class C22822 implements Runnable {
        final /* synthetic */ IUiListener f11817a;
        final /* synthetic */ Bundle f11818b;
        final /* synthetic */ Context f11819c;
        final /* synthetic */ GameAppOperation f11820d;

        C22822(GameAppOperation gameAppOperation, IUiListener iUiListener, Bundle bundle, Context context) {
            this.f11820d = gameAppOperation;
            this.f11817a = iUiListener;
            this.f11818b = bundle;
            this.f11819c = context;
        }

        public void run() {
            Bundle a = this.f11820d.m13576b();
            if (a == null) {
                String str = "accesstoken or openid or appid is null, please login first!";
                C2333f.m13759e(GameAppOperation.f11829a, str);
                this.f11817a.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, str));
                return;
            }
            a.putAll(this.f11818b);
            try {
                this.f11817a.onComplete(HttpUtils.request(this.f11820d.mToken, this.f11819c, ServerSetting.URL_PRIZE_QUERY_UNEXCHANGE, a, C2951i.f14860a));
            } catch (Throwable e) {
                C2333f.m13755b(GameAppOperation.f11829a, "Exception occur in queryUnexchangePrize", e);
                this.f11817a.onError(new UiError(-2, Constants.MSG_IO_ERROR, e.getMessage()));
            }
        }
    }

    /* renamed from: com.tencent.open.GameAppOperation.3 */
    class C22833 implements Runnable {
        final /* synthetic */ IUiListener f11821a;
        final /* synthetic */ StringBuffer f11822b;
        final /* synthetic */ Context f11823c;
        final /* synthetic */ GameAppOperation f11824d;

        C22833(GameAppOperation gameAppOperation, IUiListener iUiListener, StringBuffer stringBuffer, Context context) {
            this.f11824d = gameAppOperation;
            this.f11821a = iUiListener;
            this.f11822b = stringBuffer;
            this.f11823c = context;
        }

        public void run() {
            Bundle a = this.f11824d.m13576b();
            if (a == null) {
                String str = "accesstoken or openid or appid is null, please login first!";
                C2333f.m13759e(GameAppOperation.f11829a, str);
                this.f11821a.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, str));
                return;
            }
            a.putString(GameAppOperation.SHARE_PRIZE_SHARE_ID, this.f11822b.toString());
            a.putString("imei", C2340c.m13780b(Global.getContext()));
            try {
                this.f11821a.onComplete(HttpUtils.request(this.f11824d.mToken, this.f11823c, ServerSetting.URL_PRIZE_EXCHANGE, a, C2951i.f14860a));
            } catch (Throwable e) {
                C2333f.m13755b(GameAppOperation.f11829a, "Exception occur in exchangePrize", e);
                this.f11821a.onError(new UiError(-2, Constants.MSG_IO_ERROR, e.getMessage()));
            }
        }
    }

    /* renamed from: com.tencent.open.GameAppOperation.4 */
    class C22844 implements Runnable {
        final /* synthetic */ IUiListener f11825a;
        final /* synthetic */ String f11826b;
        final /* synthetic */ Activity f11827c;
        final /* synthetic */ GameAppOperation f11828d;

        C22844(GameAppOperation gameAppOperation, IUiListener iUiListener, String str, Activity activity) {
            this.f11828d = gameAppOperation;
            this.f11825a = iUiListener;
            this.f11826b = str;
            this.f11827c = activity;
        }

        public void run() {
            Bundle a = this.f11828d.m13576b();
            if (a == null) {
                String str = "accesstoken or openid or appid is null, please login first!";
                C2333f.m13759e(GameAppOperation.f11829a, str);
                this.f11825a.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, str));
                return;
            }
            a.putString(GameAppOperation.SHARE_PRIZE_ACTIVITY_ID, this.f11826b);
            try {
                this.f11825a.onComplete(HttpUtils.request(this.f11828d.mToken, this.f11827c.getApplicationContext(), ServerSetting.URL_PRIZE_GET_ACTIVITY_STATE, a, C2951i.f14860a));
            } catch (Throwable e) {
                C2333f.m13752a(GameAppOperation.f11829a, "Exception occur in make_share_url", e);
                this.f11825a.onError(new UiError(-6, "Exception occur in make_share_url", e.getMessage()));
            }
        }
    }

    static {
        f11829a = C2333f.f12014d + ".GameAppOper";
    }

    public GameAppOperation(QQToken qQToken) {
        super(qQToken);
    }

    private void m13572a(Activity activity) {
        m13573a(activity, C2915a.f14760f);
    }

    private void m13573a(Activity activity, String str) {
        new TDialog(activity, C2915a.f14760f, getCommonDownloadQQUrl(str), null, this.mToken).show();
    }

    private void m13574a(String str, int i, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            C2333f.m13751a(C2333f.f12014d, "reportForVia() error: reportType or result is null");
            return;
        }
        String str3;
        String str4 = C2915a.f14760f;
        switch (i) {
            case QQFAV_DATALINE_TYPE_DEFAULT /*1*/:
                str3 = Constants.VIA_SHARE_TYPE_INFO;
                break;
            case QQFAV_DATALINE_TYPE_AUDIO /*2*/:
                str3 = Constants.VIA_TO_TYPE_QQ_DISCUSS_GROUP;
                break;
            case QQFAV_DATALINE_TYPE_IMAGE_TEXT /*5*/:
                str3 = Constants.VIA_TO_TYPE_QQ_GROUP;
                break;
            case QQFAV_DATALINE_TYPE_TEXT /*6*/:
                str3 = Constants.VIA_SHARE_TYPE_TEXT;
                break;
            default:
                C2333f.m13759e(C2333f.f12014d, "GameAppOperation -- reportForVia() error: unknow type " + String.valueOf(i));
                return;
        }
        C2341d.m13784a().m13788a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SSO_LOGIN, str, Constants.VIA_ACT_TYPE_TWENTY_EIGHT, str2, str3, Constants.VIA_RESULT_SUCCESS, C2915a.f14760f, C2915a.f14760f);
    }

    private boolean m13575a(Activity activity, Bundle bundle, IUiListener iUiListener) {
        if (activity == null || bundle == null || iUiListener == null) {
            C2333f.m13759e(C2333f.f12014d, "activity or params or listener is null!");
            return false;
        }
        int i = bundle.getInt(QQFAV_DATALINE_REQTYPE, QQFAV_DATALINE_TYPE_DEFAULT);
        if (TextUtils.isEmpty(bundle.getString(QQFAV_DATALINE_APPNAME))) {
            iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a: app_name", null));
            return false;
        }
        CharSequence string = bundle.getString(QQFAV_DATALINE_DESCRIPTION);
        CharSequence string2 = bundle.getString(QQFAV_DATALINE_URL);
        CharSequence string3 = bundle.getString(QQFAV_DATALINE_AUDIOURL);
        CharSequence string4 = bundle.getString(QQFAV_DATALINE_IMAGEURL);
        ArrayList stringArrayList = bundle.getStringArrayList(QQFAV_DATALINE_FILEDATA);
        switch (i) {
            case QQFAV_DATALINE_TYPE_DEFAULT /*1*/:
                if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string4)) {
                    iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a: image_url or url is null", null));
                    return false;
                }
            case QQFAV_DATALINE_TYPE_AUDIO /*2*/:
                if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string4) || TextUtils.isEmpty(string3)) {
                    iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a: image_url or url or audioUrl is null", null));
                    return false;
                }
            case QQFAV_DATALINE_TYPE_IMAGE_TEXT /*5*/:
                if (stringArrayList != null && stringArrayList.size() != 0) {
                    String str = C2915a.f14760f;
                    int size = stringArrayList.size();
                    int i2 = 0;
                    while (i2 < size) {
                        str = ((String) stringArrayList.get(i2)).trim();
                        if (!str.startsWith("/") || new File(str).exists()) {
                            i2 += QQFAV_DATALINE_TYPE_DEFAULT;
                        } else {
                            iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, null));
                            return false;
                        }
                    }
                    break;
                }
                iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a: fill_data is null", null));
                return false;
                break;
            case QQFAV_DATALINE_TYPE_TEXT /*6*/:
                if (TextUtils.isEmpty(string)) {
                    iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a: description is null", null));
                    return false;
                }
                break;
            default:
                iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u6709\u8bef!: unknow req_type", null));
                return false;
        }
        return true;
    }

    private Bundle m13576b() {
        if (this.mToken == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        CharSequence appId = this.mToken.getAppId();
        CharSequence openId = this.mToken.getOpenId();
        CharSequence accessToken = this.mToken.getAccessToken();
        if (TextUtils.isEmpty(appId) || TextUtils.isEmpty(openId) || TextUtils.isEmpty(accessToken)) {
            C2333f.m13759e(f11829a, "composeLoginStateParams fail, accesstoken or openid or appid is null");
            return null;
        }
        bundle.putString(SocialConstants.PARAM_APP_ID, this.mToken.getAppId());
        bundle.putString(SocialConstants.PARAM_OPEN_ID, this.mToken.getOpenId());
        bundle.putString("accesstoken", this.mToken.getAccessToken());
        return bundle;
    }

    public void addToQQFavorites(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2333f.m13757c(C2333f.f12014d, "addToQQFavorites() -- start");
        int i = bundle.getInt(QQFAV_DATALINE_REQTYPE, QQFAV_DATALINE_TYPE_DEFAULT);
        if (m13575a(activity, bundle, iUiListener)) {
            String string;
            StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_qqfav?src_type=app&version=1&file_type=news");
            Object string2 = bundle.getString(QQFAV_DATALINE_IMAGEURL);
            Object string3 = bundle.getString(SHARE_PRIZE_TITLE);
            Object string4 = bundle.getString(QQFAV_DATALINE_DESCRIPTION);
            Object string5 = bundle.getString(QQFAV_DATALINE_URL);
            Object string6 = bundle.getString(QQFAV_DATALINE_AUDIOURL);
            String applicationLable = Util.getApplicationLable(activity);
            if (applicationLable == null) {
                string = bundle.getString(QQFAV_DATALINE_APPNAME);
            } else {
                string = applicationLable;
            }
            ArrayList stringArrayList = bundle.getStringArrayList(QQFAV_DATALINE_FILEDATA);
            Object appId = this.mToken.getAppId();
            Object openId = this.mToken.getOpenId();
            C2333f.m13754b(SystemUtils.QQFAVORITES_CALLBACK_ACTION, "openId:" + openId);
            if (!TextUtils.isEmpty(string2)) {
                stringBuffer.append("&image_url=" + Base64.encodeToString(Util.getBytesUTF8(string2), QQFAV_DATALINE_TYPE_AUDIO));
            }
            if (stringArrayList != null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                int size = stringArrayList.size();
                for (int i2 = 0; i2 < size; i2 += QQFAV_DATALINE_TYPE_DEFAULT) {
                    try {
                        stringBuffer2.append(URLEncoder.encode(((String) stringArrayList.get(i2)).trim(), C1142e.f5201a));
                    } catch (Throwable e) {
                        e.printStackTrace();
                        C2333f.m13755b(C2333f.f12014d, "UnsupportedEncodingException", e);
                        stringBuffer2.append(URLEncoder.encode(((String) stringArrayList.get(i2)).trim()));
                    }
                    if (i2 != size - 1) {
                        stringBuffer2.append(";");
                    }
                }
                stringBuffer.append("&file_data=" + Base64.encodeToString(Util.getBytesUTF8(stringBuffer2.toString()), QQFAV_DATALINE_TYPE_AUDIO));
            }
            if (!TextUtils.isEmpty(string3)) {
                stringBuffer.append("&title=" + Base64.encodeToString(Util.getBytesUTF8(string3), QQFAV_DATALINE_TYPE_AUDIO));
            }
            if (!TextUtils.isEmpty(string4)) {
                stringBuffer.append("&description=" + Base64.encodeToString(Util.getBytesUTF8(string4), QQFAV_DATALINE_TYPE_AUDIO));
            }
            if (!TextUtils.isEmpty(appId)) {
                stringBuffer.append("&share_id=" + appId);
            }
            if (!TextUtils.isEmpty(string5)) {
                stringBuffer.append("&url=" + Base64.encodeToString(Util.getBytesUTF8(string5), QQFAV_DATALINE_TYPE_AUDIO));
            }
            if (!TextUtils.isEmpty(string)) {
                if (string.length() > 20) {
                    string = string.substring(0, 20) + "...";
                }
                stringBuffer.append("&app_name=" + Base64.encodeToString(Util.getBytesUTF8(string), QQFAV_DATALINE_TYPE_AUDIO));
            }
            if (!TextUtils.isEmpty(openId)) {
                stringBuffer.append("&open_id=" + Base64.encodeToString(Util.getBytesUTF8(openId), QQFAV_DATALINE_TYPE_AUDIO));
            }
            if (!TextUtils.isEmpty(string6)) {
                stringBuffer.append("&audioUrl=" + Base64.encodeToString(Util.getBytesUTF8(string6), QQFAV_DATALINE_TYPE_AUDIO));
            }
            stringBuffer.append("&req_type=" + Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i)), QQFAV_DATALINE_TYPE_AUDIO));
            C2333f.m13754b("addToQQFavorites url: ", stringBuffer.toString());
            String[] strArr = new String[QQFAV_DATALINE_TYPE_DEFAULT];
            strArr[0] = SystemUtils.QQFAVORITES_CALLBACK_ACTION;
            C2186a.m13215a(Global.getContext(), this.mToken, "requireApi", strArr);
            this.mActivityIntent = new Intent("android.intent.action.VIEW");
            this.mActivityIntent.setData(Uri.parse(stringBuffer.toString()));
            this.mActivityIntent.putExtra("pkg_name", activity.getPackageName());
            Object obj = TemporaryStorage.set(SystemUtils.QQFAVORITES_CALLBACK_ACTION, iUiListener);
            if (obj != null) {
                ((IUiListener) obj).onCancel();
            }
            if (hasActivityForIntent()) {
                if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_5_2_0) >= 0) {
                    try {
                        activity.startActivityForResult(this.mActivityIntent, 0);
                        m13574a(Constants.VIA_REPORT_TYPE_QQFAVORITES, i, Constants.VIA_RESULT_SUCCESS);
                    } catch (Throwable e2) {
                        C2333f.m13755b(C2333f.f12014d, "-->addToQQFavorites, start activity exception.", e2);
                        m13574a(Constants.VIA_REPORT_TYPE_QQFAVORITES, i, Constants.VIA_TO_TYPE_QQ_GROUP);
                        m13572a(activity);
                    }
                    C2333f.m13757c(C2333f.f12014d, "addToQQFavorites() --end");
                    return;
                }
            }
            C2333f.m13758d(C2333f.f12014d, "-->addToQQFavorites, there is no activity, show download page.");
            m13574a(Constants.VIA_REPORT_TYPE_QQFAVORITES, i, Constants.VIA_TO_TYPE_QQ_GROUP);
            m13572a(activity);
            C2333f.m13757c(C2333f.f12014d, "addToQQFavorites() --end");
            return;
        }
        m13574a(Constants.VIA_REPORT_TYPE_QQFAVORITES, i, Constants.VIA_TO_TYPE_QQ_GROUP);
    }

    public void bindQQGroup(Activity activity, Bundle bundle) {
        C2333f.m13757c(C2333f.f12014d, "-->bindQQGroup()  -- start");
        if (activity == null) {
            Toast.makeText(activity, "Activity\u53c2\u6570\u4e3a\u7a7a", 0).show();
            C2333f.m13759e(C2333f.f12014d, "-->bindQQGroup, activity is empty.");
            C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
        } else if (bundle == null) {
            Toast.makeText(activity, "Bundle\u53c2\u6570\u4e3a\u7a7a", 0).show();
            C2333f.m13759e(C2333f.f12014d, "-->bindQQGroup, params is empty.");
            C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
        } else {
            Object applicationLable = Util.getApplicationLable(activity);
            StringBuffer stringBuffer = new StringBuffer("mqqapi://gamesdk/bind_group?src_type=app&version=1");
            if (!TextUtils.isEmpty(applicationLable)) {
                stringBuffer.append("&app_name=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), QQFAV_DATALINE_TYPE_AUDIO));
            }
            applicationLable = bundle.getString(GAME_UNION_ID);
            if (TextUtils.isEmpty(applicationLable)) {
                Toast.makeText(activity, "\u6e38\u620f\u516c\u4f1aID\u4e3a\u7a7a", 0).show();
                C2333f.m13759e(C2333f.f12014d, "-->bindQQGroup, game union id is empty.");
                C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
                return;
            }
            stringBuffer.append("&unionid=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), QQFAV_DATALINE_TYPE_AUDIO));
            applicationLable = bundle.getString(GAME_UNION_NAME);
            if (TextUtils.isEmpty(applicationLable)) {
                Toast.makeText(activity, "\u6e38\u620f\u516c\u4f1a\u540d\u79f0\u4e3a\u7a7a", 0).show();
                C2333f.m13759e(C2333f.f12014d, "-->bindQQGroup, game union name is empty.");
                C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
                return;
            }
            stringBuffer.append("&union_name=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), QQFAV_DATALINE_TYPE_AUDIO));
            applicationLable = bundle.getString(GAME_ZONE_ID);
            if (TextUtils.isEmpty(applicationLable)) {
                Toast.makeText(activity, "\u6e38\u620f\u533a\u57dfID\u4e3a\u7a7a", 0).show();
                C2333f.m13759e(C2333f.f12014d, "-->bindQQGroup, game zone id  is empty.");
                C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
                return;
            }
            stringBuffer.append("&zoneid=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), QQFAV_DATALINE_TYPE_AUDIO));
            applicationLable = bundle.getString(GAME_SIGNATURE);
            if (TextUtils.isEmpty(applicationLable)) {
                Toast.makeText(activity, "\u6e38\u620f\u7b7e\u540d\u4e3a\u7a7a", 0).show();
                C2333f.m13759e(C2333f.f12014d, "-->bindQQGroup, game signature is empty.");
                C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
                return;
            }
            stringBuffer.append("&signature=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), QQFAV_DATALINE_TYPE_AUDIO));
            applicationLable = this.mToken.getOpenId();
            if (TextUtils.isEmpty(applicationLable)) {
                Toast.makeText(activity, "Openid\u4e3a\u7a7a", 0).show();
                C2333f.m13759e(C2333f.f12014d, "-->bindQQGroup, openid is empty.");
                C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
                return;
            }
            stringBuffer.append("&openid=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), QQFAV_DATALINE_TYPE_AUDIO));
            Bundle composeActivityParams = composeActivityParams();
            for (String str : composeActivityParams.keySet()) {
                composeActivityParams.putString(str, Base64.encodeToString(Util.getBytesUTF8(composeActivityParams.getString(str)), QQFAV_DATALINE_TYPE_AUDIO));
            }
            stringBuffer.append("&" + Util.encodeUrl(composeActivityParams));
            C2333f.m13754b(C2333f.f12014d, "-->bindQQGroup, url: " + stringBuffer.toString());
            this.mActivityIntent = new Intent("android.intent.action.VIEW");
            this.mActivityIntent.setData(Uri.parse(stringBuffer.toString()));
            if (!hasActivityForIntent() || SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_5_1_0) < 0) {
                C2333f.m13758d(C2333f.f12014d, "-->bind group, there is no activity, show download page.");
                C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
                m13572a(activity);
            } else {
                try {
                    activity.startActivityForResult(this.mActivityIntent, 0);
                    C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_RESULT_SUCCESS);
                } catch (Throwable e) {
                    C2333f.m13755b(C2333f.f12014d, "-->bind group, start activity exception.", e);
                    C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
                    m13572a(activity);
                }
            }
            C2333f.m13757c(C2333f.f12014d, "-->bindQQGroup()  -- end");
        }
    }

    public void exchangePrize(Context context, Bundle bundle, IUiListener iUiListener) {
        String str = Constants.MSG_PARAM_ERROR;
        String str2;
        if (bundle == null) {
            str2 = "exchangePrize failed, params is null.";
            C2333f.m13759e(f11829a, str2);
            iUiListener.onError(new UiError(-5, str, str2));
        } else if (this.mToken == null || !this.mToken.isSessionValid()) {
            str2 = "exchangePrize failed, auth token is illegal.";
            C2333f.m13759e(f11829a, str2);
            iUiListener.onError(new UiError(-5, str, str2));
        } else if (context == null && Global.getContext() == null) {
            str2 = "exchangePrize failed, context is null.";
            C2333f.m13759e(f11829a, str2);
            iUiListener.onError(new UiError(-5, str, str2));
        } else {
            ArrayList stringArrayList = bundle.getStringArrayList(SHARE_PRIZE_SHARE_ID_LIST);
            if (stringArrayList == null) {
                str2 = "exchangePrize failed, shareid_list is empty.";
                C2333f.m13759e(f11829a, str2);
                iUiListener.onError(new UiError(-5, str, str2));
                return;
            }
            StringBuffer stringBuffer = new StringBuffer(C2915a.f14760f);
            int size = stringArrayList.size();
            String str3 = C2915a.f14760f;
            for (int i = 0; i < size; i += QQFAV_DATALINE_TYPE_DEFAULT) {
                str3 = (String) stringArrayList.get(i);
                if (!TextUtils.isEmpty(str3)) {
                    stringBuffer.append(str3);
                    if (i < size - 1) {
                        stringBuffer.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                    }
                }
            }
            if (context == null) {
                context = Global.getContext();
            }
            ThreadManager.executeOnSubThread(new C22833(this, iUiListener, stringBuffer, context));
        }
    }

    public void isActivityAvailable(Activity activity, String str, IUiListener iUiListener) {
        String str2 = Constants.MSG_PARAM_ERROR;
        String str3;
        if (TextUtils.isEmpty(str)) {
            str3 = "isActivityAvailable failed, activityId is null.";
            C2333f.m13759e(f11829a, str3);
            iUiListener.onError(new UiError(-5, str2, str3));
        } else if (this.mToken == null || !this.mToken.isSessionValid()) {
            str3 = "exchangePrize failed, auth token is illegal.";
            C2333f.m13759e(f11829a, str3);
            iUiListener.onError(new UiError(-5, str2, str3));
        } else {
            ThreadManager.executeOnSubThread(new C22844(this, iUiListener, str, activity));
        }
    }

    public void makeFriend(Activity activity, Bundle bundle) {
        C2333f.m13757c(C2333f.f12014d, "-->makeFriend()  -- start");
        if (bundle == null) {
            C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
            return;
        }
        String string = bundle.getString(GAME_FRIEND_OPENID);
        if (TextUtils.isEmpty(string)) {
            C2333f.m13759e(C2333f.f12014d, "-->make friend, fOpenid is empty.");
            C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
            return;
        }
        Object string2 = bundle.getString(GAME_FRIEND_LABEL);
        Object string3 = bundle.getString(GAME_FRIEND_ADD_MESSAGE);
        Object applicationLable = Util.getApplicationLable(activity);
        Object openId = this.mToken.getOpenId();
        Object appId = this.mToken.getAppId();
        C2333f.m13754b(C2333f.f12014d, "-->make friend, fOpenid: " + string + " | label: " + string2 + " | message: " + string3 + " | openid: " + openId + " | appid:" + appId);
        StringBuffer stringBuffer = new StringBuffer("mqqapi://gamesdk/add_friend?src_type=app&version=1");
        stringBuffer.append("&fopen_id=" + Base64.encodeToString(Util.getBytesUTF8(string), QQFAV_DATALINE_TYPE_AUDIO));
        if (!TextUtils.isEmpty(openId)) {
            stringBuffer.append("&open_id=" + Base64.encodeToString(Util.getBytesUTF8(openId), QQFAV_DATALINE_TYPE_AUDIO));
        }
        if (!TextUtils.isEmpty(appId)) {
            stringBuffer.append("&app_id=" + appId);
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append("&friend_label=" + Base64.encodeToString(Util.getBytesUTF8(string2), QQFAV_DATALINE_TYPE_AUDIO));
        }
        if (!TextUtils.isEmpty(string3)) {
            stringBuffer.append("&add_msg=" + Base64.encodeToString(Util.getBytesUTF8(string3), QQFAV_DATALINE_TYPE_AUDIO));
        }
        if (!TextUtils.isEmpty(applicationLable)) {
            stringBuffer.append("&app_name=" + Base64.encodeToString(Util.getBytesUTF8(applicationLable), QQFAV_DATALINE_TYPE_AUDIO));
        }
        C2333f.m13754b(C2333f.f12014d, "-->make friend, url: " + stringBuffer.toString());
        this.mActivityIntent = new Intent("android.intent.action.VIEW");
        this.mActivityIntent.setData(Uri.parse(stringBuffer.toString()));
        if (!hasActivityForIntent() || SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_5_1_0) < 0) {
            C2333f.m13758d(C2333f.f12014d, "-->make friend, there is no activity.");
            m13572a(activity);
            C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
        } else {
            try {
                activity.startActivityForResult(this.mActivityIntent, 0);
                C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_RESULT_SUCCESS);
            } catch (Throwable e) {
                C2333f.m13755b(C2333f.f12014d, "-->make friend, start activity exception.", e);
                m13572a(activity);
                C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
            }
        }
        C2333f.m13757c(C2333f.f12014d, "-->makeFriend()  -- end");
    }

    public void queryUnexchangePrize(Context context, Bundle bundle, IUiListener iUiListener) {
        String str = Constants.MSG_PARAM_ERROR;
        String str2;
        if (this.mToken == null || !this.mToken.isSessionValid()) {
            str2 = "queryUnexchangePrize failed, auth token is illegal.";
            C2333f.m13759e(f11829a, str2);
            iUiListener.onError(new UiError(-5, str, str2));
        } else if (context == null && Global.getContext() == null) {
            str2 = "queryUnexchangePrize failed, context is null.";
            C2333f.m13759e(f11829a, str2);
            iUiListener.onError(new UiError(-5, str, str2));
        } else if (TextUtils.isEmpty(bundle.getString(SHARE_PRIZE_ACTIVITY_ID))) {
            str2 = "queryUnexchangePrize failed, activityId is empty.";
            C2333f.m13759e(f11829a, str2);
            iUiListener.onError(new UiError(-5, str, str2));
        } else {
            if (context == null) {
                context = Global.getContext();
            }
            ThreadManager.executeOnSubThread(new C22822(this, iUiListener, bundle, context));
        }
    }

    public void releaseResource() {
        C2333f.m13757c(C2333f.f12014d, "releaseResource() -- start");
        TemporaryStorage.remove(SystemUtils.QQDATALINE_CALLBACK_ACTION);
        TemporaryStorage.remove(SystemUtils.QQFAVORITES_CALLBACK_ACTION);
        TemporaryStorage.remove(SystemUtils.TROOPBAR_CALLBACK_ACTION);
        C2333f.m13757c(C2333f.f12014d, "releaseResource() -- end");
    }

    public void sendToMyComputer(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2333f.m13757c(C2333f.f12014d, "sendToMyComputer() --start");
        int i = bundle.getInt(QQFAV_DATALINE_REQTYPE, QQFAV_DATALINE_TYPE_DEFAULT);
        if (m13575a(activity, bundle, iUiListener)) {
            String string;
            StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_qqdataline?src_type=app&version=1&file_type=news");
            Object string2 = bundle.getString(QQFAV_DATALINE_IMAGEURL);
            Object string3 = bundle.getString(SHARE_PRIZE_TITLE);
            Object string4 = bundle.getString(QQFAV_DATALINE_DESCRIPTION);
            Object string5 = bundle.getString(QQFAV_DATALINE_URL);
            Object string6 = bundle.getString(QQFAV_DATALINE_AUDIOURL);
            String applicationLable = Util.getApplicationLable(activity);
            if (applicationLable == null) {
                string = bundle.getString(QQFAV_DATALINE_APPNAME);
            } else {
                string = applicationLable;
            }
            ArrayList stringArrayList = bundle.getStringArrayList(QQFAV_DATALINE_FILEDATA);
            Object appId = this.mToken.getAppId();
            Object openId = this.mToken.getOpenId();
            C2333f.m13754b(SystemUtils.QQDATALINE_CALLBACK_ACTION, "openId:" + openId);
            if (!TextUtils.isEmpty(string2)) {
                stringBuffer.append("&image_url=" + Base64.encodeToString(Util.getBytesUTF8(string2), QQFAV_DATALINE_TYPE_AUDIO));
            }
            if (stringArrayList != null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                int size = stringArrayList.size();
                for (int i2 = 0; i2 < size; i2 += QQFAV_DATALINE_TYPE_DEFAULT) {
                    try {
                        stringBuffer2.append(URLEncoder.encode(((String) stringArrayList.get(i2)).trim(), C1142e.f5201a));
                    } catch (Throwable e) {
                        e.printStackTrace();
                        C2333f.m13755b(C2333f.f12014d, "UnsupportedEncodingException", e);
                        stringBuffer2.append(URLEncoder.encode(((String) stringArrayList.get(i2)).trim()));
                    }
                    if (i2 != size - 1) {
                        stringBuffer2.append(";");
                    }
                }
                stringBuffer.append("&file_data=" + Base64.encodeToString(Util.getBytesUTF8(stringBuffer2.toString()), QQFAV_DATALINE_TYPE_AUDIO));
            }
            if (!TextUtils.isEmpty(string3)) {
                stringBuffer.append("&title=" + Base64.encodeToString(Util.getBytesUTF8(string3), QQFAV_DATALINE_TYPE_AUDIO));
            }
            if (!TextUtils.isEmpty(string4)) {
                stringBuffer.append("&description=" + Base64.encodeToString(Util.getBytesUTF8(string4), QQFAV_DATALINE_TYPE_AUDIO));
            }
            if (!TextUtils.isEmpty(appId)) {
                stringBuffer.append("&share_id=" + appId);
            }
            if (!TextUtils.isEmpty(string5)) {
                stringBuffer.append("&url=" + Base64.encodeToString(Util.getBytesUTF8(string5), QQFAV_DATALINE_TYPE_AUDIO));
            }
            if (!TextUtils.isEmpty(string)) {
                if (string.length() > 20) {
                    string = string.substring(0, 20) + "...";
                }
                stringBuffer.append("&app_name=" + Base64.encodeToString(Util.getBytesUTF8(string), QQFAV_DATALINE_TYPE_AUDIO));
            }
            if (!TextUtils.isEmpty(openId)) {
                stringBuffer.append("&open_id=" + Base64.encodeToString(Util.getBytesUTF8(openId), QQFAV_DATALINE_TYPE_AUDIO));
            }
            if (!TextUtils.isEmpty(string6)) {
                stringBuffer.append("&audioUrl=" + Base64.encodeToString(Util.getBytesUTF8(string6), QQFAV_DATALINE_TYPE_AUDIO));
            }
            stringBuffer.append("&req_type=" + Base64.encodeToString(Util.getBytesUTF8(String.valueOf(i)), QQFAV_DATALINE_TYPE_AUDIO));
            C2333f.m13754b("sendToMyComputer url: ", stringBuffer.toString());
            String[] strArr = new String[QQFAV_DATALINE_TYPE_DEFAULT];
            strArr[0] = SystemUtils.QQDATALINE_CALLBACK_ACTION;
            C2186a.m13215a(Global.getContext(), this.mToken, "requireApi", strArr);
            this.mActivityIntent = new Intent("android.intent.action.VIEW");
            this.mActivityIntent.setData(Uri.parse(stringBuffer.toString()));
            this.mActivityIntent.putExtra("pkg_name", activity.getPackageName());
            Object obj = TemporaryStorage.set(SystemUtils.QQDATALINE_CALLBACK_ACTION, iUiListener);
            if (obj != null) {
                ((IUiListener) obj).onCancel();
            }
            if (hasActivityForIntent()) {
                if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_5_2_0) >= 0) {
                    try {
                        startAssistActivity(activity, Constants.REQUEST_SEND_TO_MY_COMPUTER);
                        m13574a(Constants.VIA_REPORT_TYPE_DATALINE, i, Constants.VIA_RESULT_SUCCESS);
                    } catch (Throwable e2) {
                        C2333f.m13755b(C2333f.f12014d, "-->addToQQFavorites, start activity exception.", e2);
                        m13574a(Constants.VIA_REPORT_TYPE_DATALINE, i, Constants.VIA_TO_TYPE_QQ_GROUP);
                        m13572a(activity);
                    }
                    C2333f.m13757c(C2333f.f12014d, "sendToMyComputer() --end");
                    return;
                }
            }
            C2333f.m13758d(C2333f.f12014d, "-->addToQQFavorites, there is no activity, show download page.");
            m13574a(Constants.VIA_REPORT_TYPE_DATALINE, i, Constants.VIA_TO_TYPE_QQ_GROUP);
            m13572a(activity);
            C2333f.m13757c(C2333f.f12014d, "sendToMyComputer() --end");
            return;
        }
        m13574a(Constants.VIA_REPORT_TYPE_DATALINE, i, Constants.VIA_TO_TYPE_QQ_GROUP);
    }

    public void sharePrizeToQQ(Activity activity, Bundle bundle, IUiListener iUiListener) {
        String str = Constants.MSG_PARAM_ERROR;
        if (activity == null || bundle == null || iUiListener == null) {
            String str2 = "activity or params or listener is null!";
            C2333f.m13759e(f11829a, str2);
            iUiListener.onError(new UiError(-5, str, str2));
            return;
        }
        Object string = bundle.getString(SHARE_PRIZE_TITLE);
        if (TextUtils.isEmpty(string)) {
            str2 = "sharePrizeToQQ failed, title is empty.";
            C2333f.m13759e(f11829a, str2);
            iUiListener.onError(new UiError(-5, str, str2));
            return;
        }
        Object string2 = bundle.getString(SHARE_PRIZE_SUMMARY);
        if (TextUtils.isEmpty(string2)) {
            str2 = "sharePrizeToQQ failed, sumary is empty.";
            C2333f.m13759e(f11829a, str2);
            iUiListener.onError(new UiError(-5, str, str2));
            return;
        }
        String string3 = bundle.getString(SHARE_PRIZE_IMAGE_URL);
        if (TextUtils.isEmpty(string3) || !(string3.startsWith("http://") || string3.startsWith("https://"))) {
            str2 = "sharePrizeToQQ failed, imageUrl is empty or illegal.";
            C2333f.m13759e(f11829a, str2);
            iUiListener.onError(new UiError(-5, str, str2));
            return;
        }
        Object string4 = bundle.getString(SHARE_PRIZE_ACTIVITY_ID);
        if (TextUtils.isEmpty(string4)) {
            str2 = "sharePrizeToQQ failed, activityId is empty.";
            C2333f.m13759e(f11829a, str2);
            iUiListener.onError(new UiError(-5, str, str2));
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString(SHARE_PRIZE_TITLE, string);
        bundle2.putString(SHARE_PRIZE_SUMMARY, string2);
        bundle2.putString(SHARE_PRIZE_IMAGE_URL, string3);
        bundle2.putInt(QQFAV_DATALINE_REQTYPE, QQFAV_DATALINE_TYPE_DEFAULT);
        ThreadManager.executeOnSubThread(new C22811(this, iUiListener, string4, activity, bundle2));
    }

    public void shareToTroopBar(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2333f.m13757c(C2333f.f12014d, "shareToTroopBar() -- start");
        if (activity == null || bundle == null || iUiListener == null) {
            C2333f.m13759e(C2333f.f12014d, "activity or params or listener is null!");
            return;
        }
        Object string = bundle.getString(SHARE_PRIZE_TITLE);
        if (TextUtils.isEmpty(string)) {
            iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a: title is null", null));
            C2333f.m13759e(C2333f.f12014d, "shareToTroopBar() -- title is null");
        } else if (string.length() < 4 || string.length() > 25) {
            iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u6709\u8bef!: title size: 4 ~ 25", null));
            C2333f.m13759e(C2333f.f12014d, "shareToTroopBar() -- title size: 4 ~ 25");
        } else {
            Object string2 = bundle.getString(QQFAV_DATALINE_DESCRIPTION);
            if (TextUtils.isEmpty(string2)) {
                iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u4e0d\u53ef\u4ee5\u4e3a\u7a7a: description is null", null));
                C2333f.m13759e(C2333f.f12014d, "shareToTroopBar() -- description is null");
            } else if (string2.length() < 10 || string2.length() > IMediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING) {
                iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u6709\u8bef!: description size: 10 ~ 700", null));
                C2333f.m13759e(C2333f.f12014d, "shareToTroopBar() -- description size: 10 ~ 700");
            } else {
                String str;
                ArrayList stringArrayList = bundle.getStringArrayList(QQFAV_DATALINE_FILEDATA);
                Object stringBuffer = new StringBuffer();
                if (stringArrayList != null && stringArrayList.size() > 0) {
                    str = C2915a.f14760f;
                    int size = stringArrayList.size();
                    if (size > 9) {
                        iUiListener.onError(new UiError(-5, "\u4f20\u5165\u53c2\u6570\u6709\u8bef!: file_data size: 1 ~ 9", null));
                        C2333f.m13759e(C2333f.f12014d, "shareToTroopBar() -- file_data size: 1 ~ 9");
                        return;
                    }
                    int i = 0;
                    while (i < size) {
                        str = ((String) stringArrayList.get(i)).trim();
                        if (!str.startsWith("/")) {
                            iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_ERROR, "file_data\u5e94\u8be5\u4e3a\u672c\u5730\u56fe\u7247"));
                            C2333f.m13759e(C2333f.f12014d, "shareToTroopBar(): file_data\u5e94\u8be5\u4e3a\u672c\u5730\u56fe\u7247");
                            return;
                        } else if (!str.startsWith("/") || new File(str).exists()) {
                            i += QQFAV_DATALINE_TYPE_DEFAULT;
                        } else {
                            iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_ERROR, "\u56fe\u7247\u6587\u4ef6\u4e0d\u5b58\u5728"));
                            C2333f.m13759e(C2333f.f12014d, "shareToTroopBar(): \u56fe\u7247\u6587\u4ef6\u4e0d\u5b58\u5728");
                            return;
                        }
                    }
                    for (i = 0; i < size; i += QQFAV_DATALINE_TYPE_DEFAULT) {
                        try {
                            stringBuffer.append(URLEncoder.encode(((String) stringArrayList.get(i)).trim(), C1142e.f5201a));
                        } catch (Throwable e) {
                            e.printStackTrace();
                            C2333f.m13755b(C2333f.f12014d, "UnsupportedEncodingException: ", e);
                            stringBuffer.append(URLEncoder.encode(((String) stringArrayList.get(i)).trim()));
                        }
                        if (i != size - 1) {
                            stringBuffer.append(";");
                        }
                    }
                }
                Object string3 = bundle.getString(TROOPBAR_ID);
                if (TextUtils.isEmpty(string3) || Util.isNumeric(string3)) {
                    StringBuffer stringBuffer2 = new StringBuffer("mqqapi://share/to_troopbar?src_type=app&version=1&file_type=news");
                    Object appId = this.mToken.getAppId();
                    Object openId = this.mToken.getOpenId();
                    C2333f.m13754b(C2333f.f12014d, "shareToTroopBar() -- openId: " + openId);
                    str = Util.getApplicationLable(activity);
                    if (!TextUtils.isEmpty(appId)) {
                        stringBuffer2.append("&share_id=" + appId);
                    }
                    if (!TextUtils.isEmpty(openId)) {
                        stringBuffer2.append("&open_id=" + Base64.encodeToString(Util.getBytesUTF8(openId), QQFAV_DATALINE_TYPE_AUDIO));
                    }
                    if (!TextUtils.isEmpty(str)) {
                        if (str.length() > 20) {
                            str = str.substring(0, 20) + "...";
                        }
                        stringBuffer2.append("&app_name=" + Base64.encodeToString(Util.getBytesUTF8(str), QQFAV_DATALINE_TYPE_AUDIO));
                    }
                    if (!TextUtils.isEmpty(string)) {
                        stringBuffer2.append("&title=" + Base64.encodeToString(Util.getBytesUTF8(string), QQFAV_DATALINE_TYPE_AUDIO));
                    }
                    if (!TextUtils.isEmpty(string2)) {
                        stringBuffer2.append("&description=" + Base64.encodeToString(Util.getBytesUTF8(string2), QQFAV_DATALINE_TYPE_AUDIO));
                    }
                    if (!TextUtils.isEmpty(string3)) {
                        stringBuffer2.append("&troopbar_id=" + Base64.encodeToString(Util.getBytesUTF8(string3), QQFAV_DATALINE_TYPE_AUDIO));
                    }
                    if (!TextUtils.isEmpty(stringBuffer)) {
                        stringBuffer2.append("&file_data=" + Base64.encodeToString(Util.getBytesUTF8(stringBuffer.toString()), QQFAV_DATALINE_TYPE_AUDIO));
                    }
                    C2333f.m13754b("shareToTroopBar, url: ", stringBuffer2.toString());
                    String[] strArr = new String[QQFAV_DATALINE_TYPE_DEFAULT];
                    strArr[0] = SystemUtils.TROOPBAR_CALLBACK_ACTION;
                    C2186a.m13215a(Global.getContext(), this.mToken, "requireApi", strArr);
                    this.mActivityIntent = new Intent("android.intent.action.VIEW");
                    this.mActivityIntent.setData(Uri.parse(stringBuffer2.toString()));
                    Object packageName = activity.getPackageName();
                    if (!TextUtils.isEmpty(packageName)) {
                        this.mActivityIntent.putExtra("pkg_name", packageName);
                    }
                    packageName = TemporaryStorage.set(SystemUtils.TROOPBAR_CALLBACK_ACTION, iUiListener);
                    if (packageName != null) {
                        ((IUiListener) packageName).onCancel();
                    }
                    if (!hasActivityForIntent() || SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_5_3_0) < 0) {
                        C2333f.m13758d(C2333f.f12014d, "-->shareToTroopBar, there is no activity, show download page.");
                        m13573a(activity, SystemUtils.QQ_VERSION_NAME_5_3_0);
                        C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
                    } else {
                        try {
                            startAssistActivity(activity, Constants.REQUEST_SHARE_TO_TROOP_BAR);
                            C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_RESULT_SUCCESS);
                        } catch (Throwable e2) {
                            C2333f.m13755b(C2333f.f12014d, "-->shareToTroopBar, start activity exception.", e2);
                            C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
                            m13573a(activity, SystemUtils.QQ_VERSION_NAME_5_3_0);
                        }
                    }
                    C2333f.m13757c(C2333f.f12014d, "shareToTroopBar() -- end");
                    return;
                }
                iUiListener.onError(new UiError(-6, "\u4f20\u5165\u53c2\u6570\u6709\u8bef! troopbar_id \u5fc5\u987b\u4e3a\u6570\u5b57", null));
                C2333f.m13759e(C2333f.f12014d, "shareToTroopBar(): troopbar_id \u5fc5\u987b\u4e3a\u6570\u5b57");
            }
        }
    }
}
