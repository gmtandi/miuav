package com.xiaomi.account.openauth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.account.IXiaomiAuthResponse;
import com.xiaomi.account.IXiaomiAuthResponse.Stub;
import com.xiaomi.account.IXiaomiAuthService;
import com.xiaomi.auth.AuthConstants;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.p124f.p125c.C3022o;
import p010c.p011a.C0147a;

public class XiaomiOAuthorize {
    private static final String ACCOUNT_TYPE = "com.xiaomi";
    private static final Class<? extends AuthorizeActivityBase> DEFAULT_AUTHORIZE_ACTIVITY_CLASS;
    private static final String TAG = "XiaomiOAuthorize";
    private static final String TYPE_CODE = "code";
    private static final String TYPE_TOKEN = "token";
    private Long mAppId;
    private Class<? extends AuthorizeActivityBase> mAuthorizeActivityClazz;
    private boolean mKeepCookies;
    private boolean mNotUseMiui;
    private String mRedirectUrl;
    private int[] mScopes;
    private Boolean mSkipConfirm;
    private String mState;

    /* renamed from: com.xiaomi.account.openauth.XiaomiOAuthorize.1 */
    class C24511 extends XiaomiOAuthRunnable<String> {
        final /* synthetic */ String val$accessToken;
        final /* synthetic */ long val$appId;
        final /* synthetic */ Context val$context;
        final /* synthetic */ String val$macAlgorithm;
        final /* synthetic */ String val$macKey;
        final /* synthetic */ String val$path;

        C24511(Context context, String str, long j, String str2, String str3, String str4) {
            this.val$context = context;
            this.val$path = str;
            this.val$appId = j;
            this.val$accessToken = str2;
            this.val$macKey = str3;
            this.val$macAlgorithm = str4;
        }

        public void doRun() {
            try {
                this.mFuture.set(AuthorizeApi.doHttpGet(this.val$context, this.val$path, this.val$appId, this.val$accessToken, this.val$macKey, this.val$macAlgorithm));
            } catch (Throwable e) {
                this.mFuture.setException(e);
            }
        }
    }

    /* renamed from: com.xiaomi.account.openauth.XiaomiOAuthorize.2 */
    class C24522 extends XiaomiOAuthRunnable<XiaomiOAuthResults> {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ String val$responseType;

        C24522(Activity activity, String str) {
            this.val$activity = activity;
            this.val$responseType = str;
        }

        private Bundle makeOptions() {
            Bundle bundle = new Bundle();
            bundle.putString(AuthConstants.EXTRA_CLIENT_ID, String.valueOf(XiaomiOAuthorize.this.mAppId));
            bundle.putString(AuthConstants.EXTRA_REDIRECT_URI, XiaomiOAuthorize.this.mRedirectUrl);
            bundle.putString(AuthConstants.EXTRA_RESPONSE_TYPE, this.val$responseType);
            if (XiaomiOAuthorize.this.mSkipConfirm != null) {
                bundle.putBoolean(AuthConstants.EXTRA_SKIP_CONFIRM, XiaomiOAuthorize.this.mSkipConfirm.booleanValue());
            }
            if (!TextUtils.isEmpty(XiaomiOAuthorize.this.mState)) {
                bundle.putString(AuthConstants.EXTRA_STATE, XiaomiOAuthorize.this.mState);
            }
            Object access$1100 = XiaomiOAuthorize.makeScopeString(XiaomiOAuthorize.this.mScopes);
            if (!TextUtils.isEmpty(access$1100)) {
                bundle.putString(AuthConstants.EXTRA_SCOPE, access$1100);
            }
            return bundle;
        }

        private void run(XiaomiTokenFuture xiaomiTokenFuture) {
            OAuthStage oAuthStage = OAuthStage.INIT;
            while (true) {
                switch (C24588.f12412x6d96e761[oAuthStage.ordinal()]) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        if (!XiaomiOAuthorize.this.mNotUseMiui && XiaomiOAuthorize.isMiui(this.val$activity)) {
                            if (!XiaomiOAuthorize.this.supportResponseWayWithMiui(this.val$activity)) {
                                if (XiaomiOAuthorize.this.getXiaomiAccount(this.val$activity) == null) {
                                    oAuthStage = OAuthStage.ADD_SYSTEM_ACCOUNT;
                                    break;
                                } else {
                                    oAuthStage = OAuthStage.OAUTH_FROM_MIUI;
                                    break;
                                }
                            }
                            oAuthStage = OAuthStage.OAUTH_FROM_MIUI_WITH_RESPONSE;
                            break;
                        }
                        oAuthStage = OAuthStage.OAUTH_FROM_3RD_PARTY;
                        break;
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        try {
                            Bundle bundle = (Bundle) AccountManager.get(this.val$activity).addAccount(XiaomiOAuthorize.ACCOUNT_TYPE, null, null, null, this.val$activity, null, null).getResult();
                            if (bundle != null && bundle.containsKey("authAccount")) {
                                oAuthStage = OAuthStage.OAUTH_FROM_MIUI;
                                break;
                            } else {
                                xiaomiTokenFuture.setException(new Exception("fail to add account"));
                                return;
                            }
                        } catch (SecurityException e) {
                            oAuthStage = OAuthStage.OAUTH_FROM_3RD_PARTY;
                            break;
                        } catch (AuthenticatorException e2) {
                            oAuthStage = OAuthStage.OAUTH_FROM_3RD_PARTY;
                            break;
                        }
                    case Type.BYTE /*3*/:
                        xiaomiTokenFuture.set(XiaomiOAuthorize.getAccessTokenFromMiui(this.val$activity, XiaomiOAuthorize.this.getXiaomiAccount(this.val$activity), makeOptions()));
                        return;
                    case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                        XiaomiOAuthorize.getAccessTokenFromMiuiInResponseWay(this.val$activity, makeOptions(), xiaomiTokenFuture.wrapWithinResponse());
                        return;
                    case Type.INT /*5*/:
                        xiaomiTokenFuture.handleIntentResult(AuthorizeActivityBase.getIntent(this.val$activity, String.valueOf(XiaomiOAuthorize.this.mAppId), XiaomiOAuthorize.this.mRedirectUrl, this.val$responseType, XiaomiOAuthorize.makeScopeString(XiaomiOAuthorize.this.mScopes), XiaomiOAuthorize.this.mState, XiaomiOAuthorize.this.mSkipConfirm, XiaomiOAuthorize.this.mKeepCookies, xiaomiTokenFuture.wrapWithinResponse(), XiaomiOAuthorize.this.mAuthorizeActivityClazz));
                        return;
                    default:
                        break;
                }
            }
        }

        public void doRun() {
            try {
                run(new XiaomiTokenFuture(this.val$activity, this.mFuture, XiaomiOAuthorize.this.mAuthorizeActivityClazz));
            } catch (ExecutionException e) {
                this.mFuture.setException(e.getCause());
            } catch (Throwable e2) {
                this.mFuture.setException(e2);
            } catch (Throwable e22) {
                this.mFuture.setException(e22);
            } catch (Throwable e222) {
                this.mFuture.setException(e222);
            }
        }
    }

    /* renamed from: com.xiaomi.account.openauth.XiaomiOAuthorize.3 */
    final class C24533 extends MiuiAuthServiceRunnable<Bundle> {
        final /* synthetic */ IXiaomiAuthResponse val$resp;

        C24533(Context context, Account account, Bundle bundle, IXiaomiAuthResponse iXiaomiAuthResponse) {
            this.val$resp = iXiaomiAuthResponse;
            super(context, account, bundle);
        }

        protected Bundle talkWithMiuiV5(C0147a c0147a) {
            throw new IllegalStateException("should not be here");
        }

        protected Bundle talkWithMiuiV6(IXiaomiAuthService iXiaomiAuthService) {
            iXiaomiAuthService.getAccessTokenInResponse(this.val$resp, this.options, 1, 4);
            return null;
        }
    }

    /* renamed from: com.xiaomi.account.openauth.XiaomiOAuthorize.4 */
    final class C24544 extends MiuiAuthServiceRunnable<Bundle> {
        C24544(Context context, Account account, Bundle bundle) {
            super(context, account, bundle);
        }

        protected Bundle talkWithMiuiV5(C0147a c0147a) {
            c0147a.m388d(this.account, this.options);
            return c0147a.m386b(this.account, this.options);
        }

        protected Bundle talkWithMiuiV6(IXiaomiAuthService iXiaomiAuthService) {
            return iXiaomiAuthService.getMiCloudAccessToken(this.account, this.options);
        }
    }

    /* renamed from: com.xiaomi.account.openauth.XiaomiOAuthorize.5 */
    final class C24555 extends MiuiAuthServiceRunnable<Boolean> {
        C24555(Context context, Account account, Bundle bundle) {
            super(context, account, bundle);
        }

        protected Boolean talkWithMiuiV5(C0147a c0147a) {
            return Boolean.valueOf(true);
        }

        protected Boolean talkWithMiuiV6(IXiaomiAuthService iXiaomiAuthService) {
            return Boolean.valueOf(true);
        }
    }

    /* renamed from: com.xiaomi.account.openauth.XiaomiOAuthorize.6 */
    class C24566 extends MiuiAuthServiceRunnable<Boolean> {
        C24566(Context context, Account account, Bundle bundle) {
            super(context, account, bundle);
        }

        protected Boolean talkWithMiuiV5(C0147a c0147a) {
            return Boolean.valueOf(false);
        }

        protected Boolean talkWithMiuiV6(IXiaomiAuthService iXiaomiAuthService) {
            return Boolean.valueOf(iXiaomiAuthService.supportResponseWay());
        }
    }

    /* renamed from: com.xiaomi.account.openauth.XiaomiOAuthorize.7 */
    final class C24577 extends AsyncTask<Void, Void, XiaomiOAuthResults> {
        Exception f12411e;
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ int val$requestCode;
        final /* synthetic */ String val$typeCode;
        final /* synthetic */ XiaomiOAuthFuture val$y;

        C24577(XiaomiOAuthFuture xiaomiOAuthFuture, String str, Activity activity, int i) {
            this.val$y = xiaomiOAuthFuture;
            this.val$typeCode = str;
            this.val$activity = activity;
            this.val$requestCode = i;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected com.xiaomi.account.openauth.XiaomiOAuthResults doInBackground(java.lang.Void... r2) {
            /*
            r1 = this;
            r0 = r1.val$y;	 Catch:{ OperationCanceledException -> 0x0009, IOException -> 0x000f, XMAuthericationException -> 0x0013 }
            r0 = r0.getResult();	 Catch:{ OperationCanceledException -> 0x0009, IOException -> 0x000f, XMAuthericationException -> 0x0013 }
            r0 = (com.xiaomi.account.openauth.XiaomiOAuthResults) r0;	 Catch:{ OperationCanceledException -> 0x0009, IOException -> 0x000f, XMAuthericationException -> 0x0013 }
        L_0x0008:
            return r0;
        L_0x0009:
            r0 = move-exception;
            r0.printStackTrace();
        L_0x000d:
            r0 = 0;
            goto L_0x0008;
        L_0x000f:
            r0 = move-exception;
            r1.f12411e = r0;
            goto L_0x000d;
        L_0x0013:
            r0 = move-exception;
            r1.f12411e = r0;
            goto L_0x000d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.account.openauth.XiaomiOAuthorize.7.doInBackground(java.lang.Void[]):com.xiaomi.account.openauth.XiaomiOAuthResults");
        }

        protected void onPostExecute(XiaomiOAuthResults xiaomiOAuthResults) {
            int i;
            Bundle bundle = new Bundle();
            if (xiaomiOAuthResults == null) {
                if (this.f12411e == null) {
                    i = AuthorizeActivityBase.RESULT_CANCEL;
                    bundle.putInt(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, AuthorizeActivityBase.RESULT_CANCEL);
                    bundle.putString(XiaomiOAuthConstants.EXTRA_ERROR_DESCRIPTION_2, "canceled");
                } else {
                    i = AuthorizeActivityBase.RESULT_FAIL;
                    bundle.putInt(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, AuthorizeActivityBase.RESULT_FAIL);
                    bundle.putString(XiaomiOAuthConstants.EXTRA_ERROR_DESCRIPTION_2, this.f12411e.getMessage());
                }
            } else if (xiaomiOAuthResults.hasError()) {
                i = AuthorizeActivityBase.RESULT_FAIL;
                bundle.putInt(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, xiaomiOAuthResults.getErrorCode());
                bundle.putString(XiaomiOAuthConstants.EXTRA_ERROR_DESCRIPTION_2, xiaomiOAuthResults.getErrorMessage());
            } else {
                i = AuthorizeActivityBase.RESULT_SUCCESS;
                if (XiaomiOAuthorize.TYPE_CODE.equalsIgnoreCase(this.val$typeCode)) {
                    bundle.putString(XiaomiOAuthorize.TYPE_CODE, xiaomiOAuthResults.getCode());
                    bundle.putString(XiaomiOAuthConstants.EXTRA_STATE_2, xiaomiOAuthResults.getState());
                    bundle.putString(XiaomiOAuthConstants.EXTRA_TOKEN_TYPE_2, xiaomiOAuthResults.getTokenType());
                    bundle.putString(XiaomiOAuthConstants.EXTRA_MAC_KEY_2, xiaomiOAuthResults.getMacKey());
                    bundle.putString(XiaomiOAuthConstants.EXTRA_MAC_ALGORITHM_2, xiaomiOAuthResults.getMacAlgorithm());
                } else {
                    bundle.putString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, xiaomiOAuthResults.getAccessToken());
                    bundle.putString(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2, xiaomiOAuthResults.getExpiresIn());
                    bundle.putString(XiaomiOAuthConstants.EXTRA_SCOPE_2, xiaomiOAuthResults.getScopes());
                    bundle.putString(XiaomiOAuthConstants.EXTRA_STATE_2, xiaomiOAuthResults.getState());
                    bundle.putString(XiaomiOAuthConstants.EXTRA_TOKEN_TYPE_2, xiaomiOAuthResults.getTokenType());
                    bundle.putString(XiaomiOAuthConstants.EXTRA_MAC_KEY_2, xiaomiOAuthResults.getMacKey());
                    bundle.putString(XiaomiOAuthConstants.EXTRA_MAC_ALGORITHM_2, xiaomiOAuthResults.getMacAlgorithm());
                }
            }
            this.val$activity.startActivityForResult(AuthorizeActivityBase.asMiddleActivity(this.val$activity, i, bundle, XiaomiOAuthorize.DEFAULT_AUTHORIZE_ACTIVITY_CLASS), this.val$requestCode);
        }
    }

    /* renamed from: com.xiaomi.account.openauth.XiaomiOAuthorize.8 */
    /* synthetic */ class C24588 {
        static final /* synthetic */ int[] f12412x6d96e761;

        static {
            f12412x6d96e761 = new int[OAuthStage.values().length];
            try {
                f12412x6d96e761[OAuthStage.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f12412x6d96e761[OAuthStage.ADD_SYSTEM_ACCOUNT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f12412x6d96e761[OAuthStage.OAUTH_FROM_MIUI.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f12412x6d96e761[OAuthStage.OAUTH_FROM_MIUI_WITH_RESPONSE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f12412x6d96e761[OAuthStage.OAUTH_FROM_3RD_PARTY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    enum OAuthStage {
        INIT,
        ADD_SYSTEM_ACCOUNT,
        OAUTH_FROM_MIUI,
        OAUTH_FROM_MIUI_WITH_RESPONSE,
        OAUTH_FROM_3RD_PARTY
    }

    class XiaomiTokenFuture extends FutureTask<Bundle> {
        private final Activity mActivity;
        private final Class<? extends AuthorizeActivityBase> mAuthorizeActivityClazz;
        private final XiaomiOAuthFutureImpl<XiaomiOAuthResults> mRealFuture;

        /* renamed from: com.xiaomi.account.openauth.XiaomiOAuthorize.XiaomiTokenFuture.1 */
        class C24591 implements Callable<Bundle> {
            C24591() {
            }

            public Bundle call() {
                throw new IllegalStateException("this should never be called");
            }
        }

        /* renamed from: com.xiaomi.account.openauth.XiaomiOAuthorize.XiaomiTokenFuture.2 */
        class C24602 extends Stub {
            C24602() {
            }

            public void onCancel() {
                XiaomiTokenFuture.this.setException(new OperationCanceledException());
            }

            public void onResult(Bundle bundle) {
                XiaomiTokenFuture.this.set(bundle);
            }
        }

        public XiaomiTokenFuture(Activity activity, XiaomiOAuthFutureImpl<XiaomiOAuthResults> xiaomiOAuthFutureImpl, Class<? extends AuthorizeActivityBase> cls) {
            super(new C24591());
            this.mActivity = activity;
            this.mRealFuture = xiaomiOAuthFutureImpl;
            this.mAuthorizeActivityClazz = cls;
        }

        private IXiaomiAuthResponse wrapWithinResponse() {
            return new C24602();
        }

        public Bundle get() {
            throw new IllegalStateException("this should never be called");
        }

        public Bundle get(long j, TimeUnit timeUnit) {
            throw new IllegalStateException("this should never be called");
        }

        public boolean handleIntentResult(Intent intent) {
            if (intent == null) {
                return false;
            }
            Bundle extras = intent.getExtras();
            extras.setClassLoader(getClass().getClassLoader());
            if (!extras.containsKey(XiaomiOAuthConstants.EXTRA_RESPONSE)) {
                intent = AuthorizeActivityBase.asMiddleActivity(this.mActivity, intent, wrapWithinResponse(), this.mAuthorizeActivityClazz);
            }
            this.mActivity.startActivity(intent);
            return true;
        }

        public void set(Bundle bundle) {
            if (bundle == null || !bundle.containsKey(AuthConstants.EXTRA_INTENT)) {
                this.mRealFuture.set(XiaomiOAuthResults.parseBundle(bundle));
                return;
            }
            handleIntentResult((Intent) bundle.getParcelable(AuthConstants.EXTRA_INTENT));
        }

        protected void setException(Throwable th) {
            this.mRealFuture.setException(th);
        }
    }

    static {
        DEFAULT_AUTHORIZE_ACTIVITY_CLASS = AuthorizeActivity.class;
    }

    public XiaomiOAuthorize() {
        this.mNotUseMiui = false;
        this.mScopes = null;
        this.mAppId = null;
        this.mRedirectUrl = null;
        this.mSkipConfirm = null;
        this.mState = null;
        this.mKeepCookies = false;
        this.mAuthorizeActivityClazz = DEFAULT_AUTHORIZE_ACTIVITY_CLASS;
    }

    private static Bundle getAccessTokenFromMiui(Context context, Account account, Bundle bundle) {
        return (Bundle) new C24544(context, account, bundle).start().get();
    }

    private static Bundle getAccessTokenFromMiuiInResponseWay(Context context, Bundle bundle, IXiaomiAuthResponse iXiaomiAuthResponse) {
        return (Bundle) new C24533(context, null, bundle, iXiaomiAuthResponse).start().get();
    }

    private Account getXiaomiAccount(Context context) {
        Account[] accountsByType = AccountManager.get(context).getAccountsByType(ACCOUNT_TYPE);
        return accountsByType.length == 0 ? null : accountsByType[0];
    }

    private static boolean isMiui(Context context) {
        try {
            return ((Boolean) new C24555(context, null, null).start().get()).booleanValue();
        } catch (InterruptedException e) {
            return false;
        } catch (ExecutionException e2) {
            return false;
        }
    }

    private static String makeScopeString(int[] iArr) {
        int i = 0;
        if (iArr == null || iArr.length <= 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int length = iArr.length;
        int i2 = 0;
        while (i < length) {
            int i3 = iArr[i];
            int i4 = i2 + 1;
            if (i2 > 0) {
                stringBuilder.append(C3022o.f15055c);
            }
            stringBuilder.append(i3);
            i++;
            i2 = i4;
        }
        return stringBuilder.toString();
    }

    private XiaomiOAuthFutureImpl<XiaomiOAuthResults> oauth(Activity activity, String str) {
        if (activity == null || activity.isFinishing()) {
            throw new IllegalArgumentException("activity should not be null and should be running");
        } else if (this.mAppId == null || this.mAppId.longValue() <= 0) {
            throw new IllegalArgumentException("client id is error!!!");
        } else if (TextUtils.isEmpty(this.mRedirectUrl)) {
            throw new IllegalArgumentException("redirect url is empty!!!");
        } else if (!TextUtils.isEmpty(str)) {
            return new C24522(activity, str).start();
        } else {
            throw new IllegalArgumentException("responseType is empty!!!");
        }
    }

    @Deprecated
    private static int[] scopeStringToIntArray(String str) {
        int i;
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            for (String valueOf : str.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
                try {
                    arrayList.add(Integer.valueOf(valueOf));
                } catch (NumberFormatException e) {
                }
            }
        }
        int[] iArr = new int[arrayList.size()];
        for (i = 0; i < iArr.length; i++) {
            iArr[i] = ((Integer) arrayList.get(i)).intValue();
        }
        return iArr;
    }

    @Deprecated
    public static void startGetAccessToken(Activity activity, long j, String str, Bundle bundle, int i) {
        Log.w(TAG, "you are calling startGetAccessToken(). Is still works but it is deprecated. Instead please use \n                XiaomiOAuthFuture<XiaomiOAuthResults> future = new XiaomiOAuthorize()\n                        .setAppId(appId)\n                        .setRedirectUrl(redirectUri)\n                        .setScope(scope)\n                        .setAllowSwitchAccount(true)\n                        .startGetAccessToken(acitivity);\n                XiaomiOAuthResults results = future.getResult();//call on background thread.\nIt provides better user experience! Checkout the Demo codes!");
        startGetOAuthorize(activity, j, str, TYPE_TOKEN, bundle, i);
    }

    @Deprecated
    public static void startGetOAuthCode(Activity activity, long j, String str, Bundle bundle, int i) {
        Log.w(TAG, "you are calling startGetOAuthCode(). Is still works but it is deprecated. Instead please use \n                XiaomiOAuthFuture<XiaomiOAuthResults> future = new XiaomiOAuthorize()\n                        .setAppId(appId)\n                        .setRedirectUrl(redirectUri)\n                        .setScope(scope)\n                        .setAllowSwitchAccount(true)\n                        .startGetOAuthCode(acitivity);\n                XiaomiOAuthResults results = future.getResult();//call on background thread.\nIt provides better user experience! Checkout the Demo codes!");
        startGetOAuthorize(activity, j, str, TYPE_CODE, bundle, i);
    }

    @Deprecated
    private static void startGetOAuthorize(Activity activity, long j, String str, String str2, Bundle bundle, int i) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        XiaomiOAuthorize state = new XiaomiOAuthorize().setAppId(j).setRedirectUrl(str).setScope(scopeStringToIntArray(bundle.getString(AuthConstants.EXTRA_SCOPE))).setState(bundle.getString(AuthConstants.EXTRA_STATE));
        if (bundle.containsKey(AuthConstants.EXTRA_SKIP_CONFIRM)) {
            state.setSkipConfirm(bundle.getBoolean(AuthConstants.EXTRA_SKIP_CONFIRM));
        }
        new C24577(TYPE_CODE.equalsIgnoreCase(str2) ? state.startGetOAuthCode(activity) : state.startGetAccessToken(activity), str2, activity, i).execute(new Void[0]);
    }

    private boolean supportResponseWayWithMiui(Context context) {
        try {
            return ((Boolean) new C24566(context, null, null).start().get()).booleanValue();
        } catch (InterruptedException e) {
            return false;
        } catch (ExecutionException e2) {
            return false;
        }
    }

    public XiaomiOAuthFuture<String> callOpenApi(Context context, long j, String str, String str2, String str3, String str4) {
        return new C24511(context, str, j, str2, str3, str4).start();
    }

    public XiaomiOAuthorize setAppId(long j) {
        this.mAppId = Long.valueOf(j);
        return this;
    }

    public XiaomiOAuthorize setCustomizedAuthorizeActivityClass(Class<? extends AuthorizeActivityBase> cls) {
        this.mAuthorizeActivityClazz = cls;
        return this;
    }

    public XiaomiOAuthorize setKeepCookies(boolean z) {
        this.mKeepCookies = z;
        return this;
    }

    public XiaomiOAuthorize setNoMiui(boolean z) {
        this.mNotUseMiui = z;
        return this;
    }

    public XiaomiOAuthorize setRedirectUrl(String str) {
        this.mRedirectUrl = str;
        return this;
    }

    public XiaomiOAuthorize setScope(int[] iArr) {
        this.mScopes = iArr;
        return this;
    }

    public XiaomiOAuthorize setSkipConfirm(boolean z) {
        this.mSkipConfirm = Boolean.valueOf(z);
        return this;
    }

    public XiaomiOAuthorize setState(String str) {
        this.mState = str;
        return this;
    }

    public XiaomiOAuthFuture<XiaomiOAuthResults> startGetAccessToken(Activity activity) {
        return oauth(activity, TYPE_TOKEN);
    }

    public XiaomiOAuthFuture<XiaomiOAuthResults> startGetOAuthCode(Activity activity) {
        return oauth(activity, TYPE_CODE);
    }
}
