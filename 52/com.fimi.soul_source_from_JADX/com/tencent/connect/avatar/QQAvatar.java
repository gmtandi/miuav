package com.tencent.connect.avatar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.p134b.C2341d;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class QQAvatar extends BaseApi {
    private IUiListener f11463a;

    public QQAvatar(QQToken qQToken) {
        super(qQToken);
    }

    private Intent m13311a(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, ImageActivity.class);
        return intent;
    }

    private void m13312a(Activity activity, Bundle bundle) {
        m13313a(bundle);
        this.mActivityIntent.putExtra(Constants.KEY_ACTION, "action_avatar");
        this.mActivityIntent.putExtra(Constants.KEY_PARAMS, bundle);
        startAssitActivity(activity, this.f11463a);
    }

    private void m13313a(Bundle bundle) {
        if (this.mToken != null) {
            bundle.putString(SocialConstants.PARAM_APP_ID, this.mToken.getAppId());
            if (this.mToken.isSessionValid()) {
                bundle.putString(Constants.PARAM_KEY_STR, this.mToken.getAccessToken());
                bundle.putString(Constants.PARAM_KEY_TYPE, "0x80");
            }
            String openId = this.mToken.getOpenId();
            if (openId != null) {
                bundle.putString(SocialConstants.PARAM_HOPEN_ID, openId);
            }
            bundle.putString(Constants.PARAM_PLATFORM, "androidqz");
            try {
                bundle.putString(Constants.PARAM_PLATFORM_ID, Global.getContext().getSharedPreferences(Constants.PREFERENCE_PF, 0).getString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF));
            } catch (Exception e) {
                e.printStackTrace();
                bundle.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
            }
        }
        bundle.putString("sdkv", Constants.SDK_VERSION);
        bundle.putString("sdkp", "a");
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        if (i2 == -1) {
            int intExtra = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
            if (intExtra == 0) {
                String stringExtra = intent.getStringExtra(Constants.KEY_RESPONSE);
                if (stringExtra != null) {
                    try {
                        this.f11463a.onComplete(Util.parseJson(stringExtra));
                        return;
                    } catch (JSONException e) {
                        this.f11463a.onError(new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra));
                        return;
                    }
                }
                this.f11463a.onComplete(new JSONObject());
                return;
            }
            this.f11463a.onError(new UiError(intExtra, intent.getStringExtra(Constants.KEY_ERROR_MSG), intent.getStringExtra(Constants.KEY_ERROR_DETAIL)));
            return;
        }
        this.f11463a.onCancel();
    }

    public void setAvatar(Activity activity, Uri uri, IUiListener iUiListener, int i) {
        if (this.f11463a != null) {
            this.f11463a.onCancel();
        }
        this.f11463a = iUiListener;
        Bundle bundle = new Bundle();
        bundle.putString(SocialConstants.PARAM_AVATAR_URI, uri.toString());
        bundle.putInt("exitAnim", i);
        bundle.putString(SocialConstants.PARAM_APP_ID, this.mToken.getAppId());
        bundle.putString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, this.mToken.getAccessToken());
        bundle.putLong(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2, this.mToken.getExpireTimeInSecond());
        bundle.putString(SocialConstants.PARAM_OPEN_ID, this.mToken.getOpenId());
        this.mActivityIntent = m13311a(activity);
        if (hasActivityForIntent()) {
            m13312a(activity, bundle);
            C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SET_AVATAR, Constants.VIA_REPORT_TYPE_SET_AVATAR, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_RESULT_SUCCESS);
            return;
        }
        C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SET_AVATAR, Constants.VIA_REPORT_TYPE_SET_AVATAR, Constants.VIA_REPORT_TYPE_BIND_GROUP, Constants.VIA_TO_TYPE_QQ_GROUP);
    }
}
