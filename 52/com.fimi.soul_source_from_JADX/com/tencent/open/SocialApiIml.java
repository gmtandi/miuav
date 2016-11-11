package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.C2356e.C2295a;
import com.tencent.open.p132c.C2352b;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.OpenConfig;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.market.sdk.C2537j;
import org.json.JSONException;
import org.json.JSONObject;

public class SocialApiIml extends BaseApi {
    private static final String f11862a;
    private Activity f11863b;

    /* renamed from: com.tencent.open.SocialApiIml.1 */
    class C22961 implements C2295a {
        final /* synthetic */ Bundle f11845a;
        final /* synthetic */ Activity f11846b;
        final /* synthetic */ IUiListener f11847c;
        final /* synthetic */ SocialApiIml f11848d;

        C22961(SocialApiIml socialApiIml, Bundle bundle, Activity activity, IUiListener iUiListener) {
            this.f11848d = socialApiIml;
            this.f11845a = bundle;
            this.f11846b = activity;
            this.f11847c = iUiListener;
        }

        public void m13597a(String str) {
            this.f11845a.remove(SocialConstants.PARAM_IMG_DATA);
            if (!TextUtils.isEmpty(str)) {
                this.f11845a.putString(SocialConstants.PARAM_IMG_DATA, str);
            }
            this.f11848d.m13602a(this.f11846b, this.f11845a, this.f11847c);
        }

        public void m13598b(String str) {
            this.f11845a.remove(SocialConstants.PARAM_IMG_DATA);
            this.f11847c.onError(new UiError(-5, Constants.MSG_IMAGE_ERROR, Constants.MSG_IMAGE_ERROR));
            this.f11848d.m13612b();
        }
    }

    /* renamed from: com.tencent.open.SocialApiIml.a */
    public class C2297a implements IUiListener {
        C2298b f11849a;
        final /* synthetic */ SocialApiIml f11850b;

        public C2297a(SocialApiIml socialApiIml, C2298b c2298b) {
            this.f11850b = socialApiIml;
            this.f11849a = c2298b;
        }

        public void onCancel() {
            this.f11850b.m13612b();
            C2356e.m13817a(this.f11849a.f11853c.getString(SocialConstants.PARAM_IMG_DATA));
        }

        public void onComplete(Object obj) {
            boolean z = false;
            if (obj != null) {
                try {
                    z = ((JSONObject) obj).getBoolean("check_result");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            this.f11850b.m13612b();
            if (z) {
                this.f11850b.m13600a(this.f11850b.f11863b, this.f11849a.f11851a, this.f11849a.f11852b, this.f11849a.f11853c, this.f11849a.f11855e);
                return;
            }
            C2356e.m13817a(this.f11849a.f11853c.getString(SocialConstants.PARAM_IMG_DATA));
            this.f11850b.m13604a(this.f11850b.f11863b, this.f11849a.f11852b, this.f11849a.f11853c, this.f11849a.f11854d, this.f11849a.f11855e);
        }

        public void onError(UiError uiError) {
            this.f11850b.m13612b();
            C2356e.m13817a(this.f11849a.f11853c.getString(SocialConstants.PARAM_IMG_DATA));
            this.f11850b.m13604a(this.f11850b.f11863b, this.f11849a.f11852b, this.f11849a.f11853c, this.f11849a.f11854d, this.f11849a.f11855e);
        }
    }

    /* renamed from: com.tencent.open.SocialApiIml.b */
    class C2298b {
        Intent f11851a;
        String f11852b;
        Bundle f11853c;
        String f11854d;
        IUiListener f11855e;
    }

    /* renamed from: com.tencent.open.SocialApiIml.c */
    class C2299c implements IUiListener {
        final /* synthetic */ SocialApiIml f11856a;
        private IUiListener f11857b;
        private String f11858c;
        private String f11859d;
        private Bundle f11860e;
        private Activity f11861f;

        C2299c(SocialApiIml socialApiIml, Activity activity, IUiListener iUiListener, String str, String str2, Bundle bundle) {
            this.f11856a = socialApiIml;
            this.f11857b = iUiListener;
            this.f11858c = str;
            this.f11859d = str2;
            this.f11860e = bundle;
        }

        public void onCancel() {
            this.f11857b.onCancel();
        }

        public void onComplete(Object obj) {
            CharSequence string;
            CharSequence charSequence = null;
            try {
                string = ((JSONObject) obj).getString(SocialConstants.PARAM_ENCRY_EOKEN);
            } catch (Throwable e) {
                e.printStackTrace();
                C2333f.m13755b(C2333f.f12014d, "OpenApi, EncrytokenListener() onComplete error", e);
                string = charSequence;
            }
            this.f11860e.putString("encrytoken", string);
            this.f11856a.m13605a(this.f11856a.f11863b, this.f11858c, this.f11860e, this.f11859d, this.f11857b);
            if (TextUtils.isEmpty(string)) {
                C2333f.m13754b("miles", "The token get from qq or qzone is empty. Write temp token to localstorage.");
                this.f11856a.writeEncryToken(this.f11861f);
            }
        }

        public void onError(UiError uiError) {
            C2333f.m13754b(C2333f.f12014d, "OpenApi, EncryptTokenListener() onError" + uiError.errorMessage);
            this.f11857b.onError(uiError);
        }
    }

    static {
        f11862a = SocialApiIml.class.getName();
    }

    public SocialApiIml(QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
    }

    public SocialApiIml(QQToken qQToken) {
        super(qQToken);
    }

    private C2298b m13599a(Bundle bundle, String str, String str2, IUiListener iUiListener) {
        Intent intent = new Intent();
        intent.setClassName(Constants.PACKAGE_QZONE, "com.tencent.open.agent.AgentActivity");
        C2298b c2298b = new C2298b();
        c2298b.f11851a = intent;
        c2298b.f11853c = bundle;
        c2298b.f11854d = str2;
        c2298b.f11855e = iUiListener;
        c2298b.f11852b = str;
        return c2298b;
    }

    private void m13600a(Activity activity, Intent intent, String str, Bundle bundle, IUiListener iUiListener) {
        C2333f.m13754b(C2333f.f12014d, "-->handleIntentWithAgent " + str + " params=" + bundle + " activityIntent=" + intent);
        intent.putExtra(Constants.KEY_ACTION, str);
        intent.putExtra(Constants.KEY_PARAMS, bundle);
        this.mActivityIntent = intent;
        startAssitActivity(activity, iUiListener);
    }

    private void m13601a(Activity activity, Intent intent, String str, Bundle bundle, String str2, IUiListener iUiListener, boolean z) {
        C2333f.m13754b(C2333f.f12014d, "-->handleIntent " + str + " params=" + bundle + " activityIntent=" + intent);
        if (intent != null) {
            m13600a(activity, intent, str, bundle, iUiListener);
            return;
        }
        Object obj = (z || OpenConfig.getInstance(Global.getContext(), this.mToken.getAppId()).getBoolean("C_LoginH5")) ? 1 : null;
        if (obj != null) {
            m13604a(activity, str, bundle, str2, iUiListener);
        } else {
            handleDownloadLastestQQ(activity, bundle, iUiListener);
        }
    }

    private void m13602a(Activity activity, Bundle bundle, IUiListener iUiListener) {
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_VOICE);
        String envUrl = ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_VOICE);
        if (agentIntentWithTarget == null && m13614a()) {
            if (this.mProgressDialog == null || !this.mProgressDialog.isShowing()) {
                this.mProgressDialog = new ProgressDialog(activity);
                this.mProgressDialog.setTitle("\u8bf7\u7a0d\u5019");
                this.mProgressDialog.show();
            }
            m13613a(activity, SocialConstants.ACTION_VOICE, new C2297a(this, m13599a(bundle, SocialConstants.ACTION_VOICE, envUrl, iUiListener)));
            return;
        }
        m13601a(activity, agentIntentWithTarget, SocialConstants.ACTION_VOICE, bundle, envUrl, iUiListener, true);
    }

    private void m13603a(Activity activity, String str, Bundle bundle, IUiListener iUiListener) {
        this.f11863b = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_FRIEND_CHOOSER);
        if (agentIntentWithTarget == null) {
            agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_ASK_GIFT);
        }
        bundle.putAll(composeActivityParams());
        if (SocialConstants.ACTION_ASK.equals(str)) {
            bundle.putString(SocialConstants.PARAM_TYPE, SocialConstants.TYPE_REQUEST);
        } else if (SocialConstants.ACTION_GIFT.equals(str)) {
            bundle.putString(SocialConstants.PARAM_TYPE, SocialConstants.TYPE_FREEGIFT);
        }
        m13601a(activity, agentIntentWithTarget, str, bundle, ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_GIFT), iUiListener, false);
    }

    private void m13604a(Activity activity, String str, Bundle bundle, String str2, IUiListener iUiListener) {
        C2333f.m13754b(C2333f.f12014d, "-->handleIntentWithH5 " + str + " params=" + bundle);
        Intent targetActivityIntent = getTargetActivityIntent("com.tencent.open.agent.AgentActivity");
        Object c2299c = new C2299c(this, activity, iUiListener, str, str2, bundle);
        Intent targetActivityIntent2 = getTargetActivityIntent("com.tencent.open.agent.EncryTokenActivity");
        if (targetActivityIntent2 == null || targetActivityIntent == null || targetActivityIntent.getComponent() == null || targetActivityIntent2.getComponent() == null || !targetActivityIntent.getComponent().getPackageName().equals(targetActivityIntent2.getComponent().getPackageName())) {
            String encrypt = Util.encrypt("tencent&sdk&qazxc***14969%%" + this.mToken.getAccessToken() + this.mToken.getAppId() + this.mToken.getOpenId() + "qzone3.4");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(SocialConstants.PARAM_ENCRY_EOKEN, encrypt);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            c2299c.onComplete(jSONObject);
            return;
        }
        targetActivityIntent2.putExtra(SocialConstants.PARAM_CONSUMER_KEY, this.mToken.getAppId());
        targetActivityIntent2.putExtra(SocialConstants.PARAM_OPEN_ID, this.mToken.getOpenId());
        targetActivityIntent2.putExtra(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, this.mToken.getAccessToken());
        targetActivityIntent2.putExtra(Constants.KEY_ACTION, SocialConstants.ACTION_CHECK_TOKEN);
        this.mActivityIntent = targetActivityIntent2;
        if (hasActivityForIntent()) {
            startAssitActivity(activity, (IUiListener) c2299c);
        }
    }

    private void m13605a(Context context, String str, Bundle bundle, String str2, IUiListener iUiListener) {
        C2333f.m13751a(C2333f.f12014d, "OpenUi, showDialog --start");
        CookieSyncManager.createInstance(context);
        bundle.putString(SocialConstants.PARAM_CONSUMER_KEY, this.mToken.getAppId());
        if (this.mToken.isSessionValid()) {
            bundle.putString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, this.mToken.getAccessToken());
        }
        String openId = this.mToken.getOpenId();
        if (openId != null) {
            bundle.putString(SocialConstants.PARAM_OPEN_ID, openId);
        }
        try {
            bundle.putString(Constants.PARAM_PLATFORM_ID, Global.getContext().getSharedPreferences(Constants.PREFERENCE_PF, 0).getString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF));
        } catch (Exception e) {
            e.printStackTrace();
            bundle.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append(Util.encodeUrl(bundle));
        String stringBuilder2 = stringBuilder.toString();
        C2333f.m13754b(C2333f.f12014d, "OpenUi, showDialog TDialog");
        if (SocialConstants.ACTION_CHALLENGE.equals(str) || SocialConstants.ACTION_BRAG.equals(str)) {
            C2333f.m13754b(C2333f.f12014d, "OpenUi, showDialog PKDialog");
            new PKDialog(this.f11863b, str, stringBuilder2, iUiListener, this.mToken).show();
            return;
        }
        new TDialog(this.f11863b, str, stringBuilder2, iUiListener, this.mToken).show();
    }

    private void m13612b() {
        if (!this.f11863b.isFinishing() && this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
            this.mProgressDialog = null;
        }
    }

    protected void m13613a(Activity activity, String str, IUiListener iUiListener) {
        Intent intent = new Intent();
        intent.setClassName(Constants.PACKAGE_QZONE, "com.tencent.open.agent.AgentActivity");
        intent.putExtra(Constants.KEY_ACTION, "action_check");
        Bundle bundle = new Bundle();
        bundle.putString("apiName", str);
        intent.putExtra(Constants.KEY_PARAMS, bundle);
        this.mActivityIntent = intent;
        startAssitActivity(activity, iUiListener);
    }

    protected boolean m13614a() {
        Intent intent = new Intent();
        intent.setClassName(Constants.PACKAGE_QZONE, SocialConstants.ACTIVITY_CHECK_FUNCTION);
        return SystemUtils.isActivityExist(Global.getContext(), intent);
    }

    public void ask(Activity activity, Bundle bundle, IUiListener iUiListener) {
        m13603a(activity, SocialConstants.ACTION_ASK, bundle, iUiListener);
    }

    public void brag(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f11863b = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_BRAG);
        bundle.putAll(composeActivityParams());
        Activity activity2 = activity;
        m13601a(activity2, agentIntentWithTarget, SocialConstants.ACTION_BRAG, bundle, ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_BRAG), iUiListener, false);
    }

    public void challenge(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f11863b = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_CHALLENGE);
        bundle.putAll(composeActivityParams());
        Activity activity2 = activity;
        m13601a(activity2, agentIntentWithTarget, SocialConstants.ACTION_CHALLENGE, bundle, ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_BRAG), iUiListener, false);
    }

    protected Intent getTargetActivityIntent(String str) {
        Intent intent = new Intent();
        intent.setClassName(Constants.PACKAGE_QZONE, str);
        Intent intent2 = new Intent();
        intent2.setClassName(Constants.PACKAGE_QQ, str);
        return (!SystemUtils.isActivityExist(Global.getContext(), intent2) || SystemUtils.compareQQVersion(Global.getContext(), "4.7") < 0) ? (!SystemUtils.isActivityExist(Global.getContext(), intent) || SystemUtils.compareVersion(SystemUtils.getAppVersionName(Global.getContext(), Constants.PACKAGE_QZONE), "4.2") < 0) ? null : !SystemUtils.isAppSignatureValid(Global.getContext(), intent.getComponent().getPackageName(), Constants.SIGNATRUE_QZONE) ? null : intent : intent2;
    }

    public void gift(Activity activity, Bundle bundle, IUiListener iUiListener) {
        m13603a(activity, SocialConstants.ACTION_GIFT, bundle, iUiListener);
    }

    public void grade(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f11863b = activity;
        bundle.putAll(composeActivityParams());
        bundle.putString(C2537j.aq, Util.getAppVersion(activity));
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_GRADE);
        String str = "http://qzs.qq.com/open/mobile/rate/sdk_rate.html?";
        if (agentIntentWithTarget == null && m13614a()) {
            this.mProgressDialog = new ProgressDialog(activity);
            this.mProgressDialog.setMessage("\u8bf7\u7a0d\u5019...");
            this.mProgressDialog.show();
            m13613a(activity, SocialConstants.ACTION_GRADE, new C2297a(this, m13599a(bundle, SocialConstants.ACTION_GRADE, str, iUiListener)));
            return;
        }
        m13601a(activity, agentIntentWithTarget, SocialConstants.ACTION_GRADE, bundle, str, iUiListener, true);
    }

    public void invite(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f11863b = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_FRIEND_CHOOSER);
        if (agentIntentWithTarget == null) {
            agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_INVITE);
        }
        bundle.putAll(composeActivityParams());
        Activity activity2 = activity;
        m13601a(activity2, agentIntentWithTarget, SocialConstants.ACTION_INVITE, bundle, ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_INVITE), iUiListener, false);
    }

    public void reactive(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f11863b = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_FRIEND_CHOOSER);
        if (agentIntentWithTarget == null) {
            agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_REACTIVE);
        }
        bundle.putAll(composeActivityParams());
        String envUrl = ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_REACTIVE);
        if (agentIntentWithTarget == null && m13614a()) {
            this.mProgressDialog = new ProgressDialog(activity);
            this.mProgressDialog.setMessage("\u8bf7\u7a0d\u5019...");
            this.mProgressDialog.show();
            bundle.putString(SocialConstants.PARAM_TYPE, SocialConstants.TYPE_REACTIVE);
            m13613a(activity, SocialConstants.ACTION_REACTIVE, new C2297a(this, m13599a(bundle, SocialConstants.ACTION_REACTIVE, envUrl, iUiListener)));
            return;
        }
        bundle.putString(SocialConstants.PARAM_SEND_IMG, bundle.getString(SocialConstants.PARAM_IMG_URL));
        bundle.putString(SocialConstants.PARAM_TYPE, SocialConstants.TYPE_REACTIVE);
        bundle.remove(SocialConstants.PARAM_IMG_URL);
        m13601a(activity, agentIntentWithTarget, SocialConstants.ACTION_REACTIVE, bundle, envUrl, iUiListener, false);
    }

    public void story(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f11863b = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_STORY);
        bundle.putAll(composeActivityParams());
        Activity activity2 = activity;
        m13601a(activity2, agentIntentWithTarget, SocialConstants.ACTION_STORY, bundle, ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_SEND_STORY), iUiListener, false);
    }

    public void voice(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f11863b = activity;
        bundle.putAll(composeActivityParams());
        bundle.putString(C2537j.aq, Util.getAppVersion(activity));
        if (!C2356e.m13818a()) {
            iUiListener.onError(new UiError(-12, Constants.MSG_NO_SDCARD, Constants.MSG_NO_SDCARD));
        } else if (!bundle.containsKey(SocialConstants.PARAM_IMG_DATA) || ((Bitmap) bundle.getParcelable(SocialConstants.PARAM_IMG_DATA)) == null) {
            m13602a(activity, bundle, iUiListener);
        } else {
            this.mProgressDialog = new ProgressDialog(activity);
            this.mProgressDialog.setMessage("\u8bf7\u7a0d\u5019...");
            this.mProgressDialog.show();
            new C2356e(new C22961(this, bundle, activity, iUiListener)).execute(new Bitmap[]{r0});
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void writeEncryToken(Context context) {
        String str = "tencent&sdk&qazxc***14969%%";
        String accessToken = this.mToken.getAccessToken();
        String appId = this.mToken.getAppId();
        String openId = this.mToken.getOpenId();
        str = (accessToken == null || accessToken.length() <= 0 || appId == null || appId.length() <= 0 || openId == null || openId.length() <= 0) ? null : Util.encrypt(str + accessToken + appId + openId + "qzone3.4");
        C2352b c2352b = new C2352b(context);
        WebSettings settings = c2352b.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        accessToken = "<!DOCTYPE HTML><html lang=\"en-US\"><head><meta charset=\"UTF-8\"><title>localStorage Test</title><script type=\"text/javascript\">document.domain = 'qq.com';localStorage[\"" + this.mToken.getOpenId() + "_" + this.mToken.getAppId() + "\"]=\"" + str + "\";</script></head><body></body></html>";
        str = ServerSetting.getInstance().getEnvUrl(context, ServerSetting.DEFAULT_LOCAL_STORAGE_URI);
        c2352b.loadDataWithBaseURL(str, accessToken, "text/html", "utf-8", str);
    }
}
