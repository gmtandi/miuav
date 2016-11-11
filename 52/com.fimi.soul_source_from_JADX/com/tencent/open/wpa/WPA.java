package com.tencent.open.wpa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.util.Base64;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.connect.common.Constants;
import com.tencent.open.p134b.C2341d;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.UnsupportedEncodingException;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p152c.p156c.C2951i;

public class WPA extends BaseApi {
    public WPA(Context context, QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
    }

    public WPA(Context context, QQToken qQToken) {
        super(qQToken);
    }

    public void getWPAUserOnlineState(String str, IUiListener iUiListener) {
        if (str == null) {
            try {
                C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_WAP_STATE, Constants.VIA_REPORT_TYPE_WPA_STATE, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
                throw new Exception("uin null");
            } catch (Exception e) {
                if (iUiListener != null) {
                    iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_ERROR, null));
                }
                C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_WAP_STATE, Constants.VIA_REPORT_TYPE_WPA_STATE, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
            }
        } else if (str.length() < 5) {
            C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_WAP_STATE, Constants.VIA_REPORT_TYPE_WPA_STATE, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
            throw new Exception("uin length < 5");
        } else {
            int i = 0;
            while (i < str.length()) {
                if (Character.isDigit(str.charAt(i))) {
                    i++;
                } else {
                    C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_WAP_STATE, Constants.VIA_REPORT_TYPE_WPA_STATE, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
                    throw new Exception("uin not digit");
                }
            }
            Bundle bundle = null;
            HttpUtils.requestAsync(this.mToken, Global.getContext(), "http://webpresence.qq.com/getonline?Type=1&" + str + ":", bundle, C2951i.f14860a, new TempRequestListener(iUiListener));
            C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_WAP_STATE, Constants.VIA_REPORT_TYPE_WPA_STATE, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_RESULT_SUCCESS);
        }
    }

    public int startWPAConversation(Activity activity, String str, String str2) {
        String str3 = "mqqwpa://im/chat?chat_type=wpa&uin=%1$s&version=1&src_type=app&attach_content=%2$s";
        Intent intent = new Intent("android.intent.action.VIEW");
        if (TextUtils.isEmpty(str)) {
            C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_START_WAP, Constants.VIA_REPORT_TYPE_START_WAP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
            return -1;
        } else if (str.length() < 5) {
            C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_START_WAP, Constants.VIA_REPORT_TYPE_START_WAP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
            return -3;
        } else {
            int i;
            int i2 = 0;
            while (i2 < str.length()) {
                if (Character.isDigit(str.charAt(i2))) {
                    i2++;
                } else {
                    C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_START_WAP, Constants.VIA_REPORT_TYPE_START_WAP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
                    return -4;
                }
            }
            str3 = C2915a.f14760f;
            if (!TextUtils.isEmpty(str2)) {
                try {
                    str3 = Base64.encodeToString(str2.getBytes(C1142e.f5201a), 2);
                } catch (UnsupportedEncodingException e) {
                }
            }
            intent.setData(Uri.parse(String.format("mqqwpa://im/chat?chat_type=wpa&uin=%1$s&version=1&src_type=app&attach_content=%2$s", new Object[]{str, str3})));
            PackageManager packageManager = Global.getContext().getPackageManager();
            if (packageManager.queryIntentActivities(intent, AccessibilityNodeInfoCompat.ACTION_CUT).size() > 0) {
                activity.startActivity(intent);
                i = 0;
            } else {
                intent.setData(Uri.parse("http://www.myapp.com/forward/a/45592?g_f=990935"));
                if (packageManager.queryIntentActivities(intent, AccessibilityNodeInfoCompat.ACTION_CUT).size() > 0) {
                    activity.startActivity(intent);
                    i = 0;
                } else {
                    C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_START_WAP, Constants.VIA_REPORT_TYPE_START_WAP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
                    i = -2;
                }
            }
            C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_START_WAP, Constants.VIA_REPORT_TYPE_START_WAP, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_RESULT_SUCCESS);
            return i;
        }
    }
}
