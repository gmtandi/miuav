package com.tencent.connect.auth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.connect.common.AssistActivity;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.ApiTask;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.connect.common.Constants;
import com.tencent.connect.p128a.C2186a;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.open.SocialConstants;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.p134b.C2341d;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.ThreadManager;
import com.tencent.open.yyb.TitleBar;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.xiaomi.account.openauth.AuthorizeActivityBase;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import it.p074a.p075a.C2799f;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p152c.p156c.C2951i;
import org.p122a.p123a.p152c.p156c.C2955m;

public class AuthAgent extends BaseApi {
    public static final String SECURE_LIB_FILE_NAME = "libwbsafeedit";
    public static final String SECURE_LIB_NAME = "libwbsafeedit.so";
    private IUiListener f11381a;
    private String f11382b;
    private Activity f11383c;

    /* renamed from: com.tencent.connect.auth.AuthAgent.1 */
    class C21881 implements Runnable {
        final /* synthetic */ String f11355a;
        final /* synthetic */ IUiListener f11356b;
        final /* synthetic */ AuthAgent f11357c;

        /* renamed from: com.tencent.connect.auth.AuthAgent.1.1 */
        class C21871 implements Runnable {
            final /* synthetic */ C21881 f11354a;

            C21871(C21881 c21881) {
                this.f11354a = c21881;
            }

            public void run() {
                new AuthDialog(this.f11354a.f11357c.f11383c, SystemUtils.ACTION_LOGIN, this.f11354a.f11355a, this.f11354a.f11356b, this.f11354a.f11357c.mToken).show();
            }
        }

        C21881(AuthAgent authAgent, String str, IUiListener iUiListener) {
            this.f11357c = authAgent;
            this.f11355a = str;
            this.f11356b = iUiListener;
        }

        public void run() {
            SystemUtils.extractSecureLib(AuthAgent.SECURE_LIB_FILE_NAME, AuthAgent.SECURE_LIB_NAME, 2);
            this.f11357c.f11383c.runOnUiThread(new C21871(this));
        }
    }

    /* renamed from: com.tencent.connect.auth.AuthAgent.2 */
    class C21892 implements Runnable {
        final /* synthetic */ AuthAgent f11358a;

        C21892(AuthAgent authAgent) {
            this.f11358a = authAgent;
        }

        public void run() {
            Global.saveVersionCode();
        }
    }

    class CheckLoginListener implements IUiListener {
        IUiListener f11359a;
        final /* synthetic */ AuthAgent f11360b;

        public CheckLoginListener(AuthAgent authAgent, IUiListener iUiListener) {
            this.f11360b = authAgent;
            this.f11359a = iUiListener;
        }

        public void onCancel() {
            if (this.f11359a != null) {
                this.f11359a.onCancel();
            }
        }

        public void onComplete(Object obj) {
            if (obj == null) {
                C2333f.m13759e("CheckLoginListener", "response data is null");
                return;
            }
            JSONObject jSONObject = (JSONObject) obj;
            try {
                int i = jSONObject.getInt("ret");
                Object string = i == 0 ? "success" : jSONObject.getString(SocialConstants.PARAM_SEND_MSG);
                if (this.f11359a != null) {
                    this.f11359a.onComplete(new JSONObject().put("ret", i).put(SocialConstants.PARAM_SEND_MSG, string));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                C2333f.m13759e("CheckLoginListener", "response data format error");
            }
        }

        public void onError(UiError uiError) {
            if (this.f11359a != null) {
                this.f11359a.onError(uiError);
            }
        }
    }

    class FeedConfirmListener implements IUiListener {
        IUiListener f11372a;
        final /* synthetic */ AuthAgent f11373b;
        private final String f11374c;
        private final String f11375d;
        private final String f11376e;

        abstract class ButtonListener implements OnClickListener {
            Dialog f11361d;
            final /* synthetic */ FeedConfirmListener f11362e;

            ButtonListener(FeedConfirmListener feedConfirmListener, Dialog dialog) {
                this.f11362e = feedConfirmListener;
                this.f11361d = dialog;
            }
        }

        /* renamed from: com.tencent.connect.auth.AuthAgent.FeedConfirmListener.1 */
        class C21901 extends ButtonListener {
            final /* synthetic */ IUiListener f11363a;
            final /* synthetic */ Object f11364b;
            final /* synthetic */ FeedConfirmListener f11365c;

            C21901(FeedConfirmListener feedConfirmListener, Dialog dialog, IUiListener iUiListener, Object obj) {
                this.f11365c = feedConfirmListener;
                this.f11363a = iUiListener;
                this.f11364b = obj;
                super(feedConfirmListener, dialog);
            }

            public void onClick(View view) {
                this.f11365c.m13223a();
                if (this.d != null && this.d.isShowing()) {
                    this.d.dismiss();
                }
                if (this.f11363a != null) {
                    this.f11363a.onComplete(this.f11364b);
                }
            }
        }

        /* renamed from: com.tencent.connect.auth.AuthAgent.FeedConfirmListener.2 */
        class C21912 extends ButtonListener {
            final /* synthetic */ IUiListener f11366a;
            final /* synthetic */ Object f11367b;
            final /* synthetic */ FeedConfirmListener f11368c;

            C21912(FeedConfirmListener feedConfirmListener, Dialog dialog, IUiListener iUiListener, Object obj) {
                this.f11368c = feedConfirmListener;
                this.f11366a = iUiListener;
                this.f11367b = obj;
                super(feedConfirmListener, dialog);
            }

            public void onClick(View view) {
                if (this.d != null && this.d.isShowing()) {
                    this.d.dismiss();
                }
                if (this.f11366a != null) {
                    this.f11366a.onComplete(this.f11367b);
                }
            }
        }

        /* renamed from: com.tencent.connect.auth.AuthAgent.FeedConfirmListener.3 */
        class C21923 implements OnCancelListener {
            final /* synthetic */ IUiListener f11369a;
            final /* synthetic */ Object f11370b;
            final /* synthetic */ FeedConfirmListener f11371c;

            C21923(FeedConfirmListener feedConfirmListener, IUiListener iUiListener, Object obj) {
                this.f11371c = feedConfirmListener;
                this.f11369a = iUiListener;
                this.f11370b = obj;
            }

            public void onCancel(DialogInterface dialogInterface) {
                if (this.f11369a != null) {
                    this.f11369a.onComplete(this.f11370b);
                }
            }
        }

        public FeedConfirmListener(AuthAgent authAgent, IUiListener iUiListener) {
            this.f11373b = authAgent;
            this.f11374c = "sendinstall";
            this.f11375d = "installwording";
            this.f11376e = "http://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi";
            this.f11372a = iUiListener;
        }

        private Drawable m13220a(String str, Context context) {
            Drawable createFromStream;
            IOException e;
            try {
                InputStream open = context.getApplicationContext().getAssets().open(str);
                if (open == null) {
                    return null;
                }
                if (str.endsWith(".9.png")) {
                    Bitmap decodeStream = BitmapFactory.decodeStream(open);
                    if (decodeStream == null) {
                        return null;
                    }
                    byte[] ninePatchChunk = decodeStream.getNinePatchChunk();
                    NinePatch.isNinePatchChunk(ninePatchChunk);
                    return new NinePatchDrawable(decodeStream, ninePatchChunk, new Rect(), null);
                }
                createFromStream = Drawable.createFromStream(open, str);
                try {
                    open.close();
                    return createFromStream;
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    return createFromStream;
                }
            } catch (IOException e3) {
                IOException iOException = e3;
                createFromStream = null;
                e = iOException;
                e.printStackTrace();
                return createFromStream;
            }
        }

        private View m13221a(Context context, Drawable drawable, String str, OnClickListener onClickListener, OnClickListener onClickListener2) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            View relativeLayout = new RelativeLayout(context);
            View imageView = new ImageView(context);
            imageView.setImageDrawable(drawable);
            imageView.setScaleType(ScaleType.FIT_XY);
            imageView.setId(1);
            int i = (int) (14.0f * f);
            i = (int) (18.0f * f);
            int i2 = (int) (6.0f * f);
            int i3 = (int) (18.0f * f);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (BitmapDescriptorFactory.HUE_YELLOW * f), (int) (BitmapDescriptorFactory.HUE_YELLOW * f));
            layoutParams.addRule(9);
            layoutParams.setMargins(0, i, i2, i3);
            relativeLayout.addView(imageView, layoutParams);
            imageView = new TextView(context);
            imageView.setText(str);
            imageView.setTextSize(14.0f);
            imageView.setGravity(3);
            imageView.setIncludeFontPadding(false);
            imageView.setPadding(0, 0, 0, 0);
            imageView.setLines(2);
            imageView.setId(5);
            imageView.setMinWidth((int) (185.0f * f));
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(1, 1);
            layoutParams2.addRule(6, 1);
            int i4 = (int) (TitleBar.SHAREBTN_RIGHT_MARGIN * f);
            layoutParams2.setMargins(0, 0, (int) (5.0f * f), 0);
            relativeLayout.addView(imageView, layoutParams2);
            imageView = new View(context);
            imageView.setBackgroundColor(Color.rgb(C2799f.f14287y, C2799f.f14287y, C2799f.f14287y));
            imageView.setId(3);
            layoutParams2 = new RelativeLayout.LayoutParams(-2, 2);
            layoutParams2.addRule(3, 1);
            layoutParams2.addRule(5, 1);
            layoutParams2.addRule(7, 5);
            layoutParams2.setMargins(0, 0, 0, (int) (12.0f * f));
            relativeLayout.addView(imageView, layoutParams2);
            imageView = new LinearLayout(context);
            layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(5, 1);
            layoutParams2.addRule(7, 5);
            layoutParams2.addRule(3, 3);
            View button = new Button(context);
            button.setText("\u8df3\u8fc7");
            button.setBackgroundDrawable(m13220a("buttonNegt.png", context));
            button.setTextColor(Color.rgb(36, 97, Opcodes.LXOR));
            button.setTextSize(TitleBar.BACKBTN_LEFT_MARGIN);
            button.setOnClickListener(onClickListener2);
            button.setId(4);
            LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, (int) (45.0f * f));
            layoutParams3.rightMargin = (int) (14.0f * f);
            layoutParams3.leftMargin = (int) (4.0f * f);
            layoutParams3.weight = C2020f.f10933c;
            imageView.addView(button, layoutParams3);
            button = new Button(context);
            button.setText("\u786e\u5b9a");
            button.setTextSize(TitleBar.BACKBTN_LEFT_MARGIN);
            button.setTextColor(Color.rgb(Util.MASK_8BIT, Util.MASK_8BIT, Util.MASK_8BIT));
            button.setBackgroundDrawable(m13220a("buttonPost.png", context));
            button.setOnClickListener(onClickListener);
            layoutParams3 = new LinearLayout.LayoutParams(0, (int) (45.0f * f));
            layoutParams3.weight = C2020f.f10933c;
            layoutParams3.rightMargin = (int) (4.0f * f);
            imageView.addView(button, layoutParams3);
            relativeLayout.addView(imageView, layoutParams2);
            LayoutParams layoutParams4 = new FrameLayout.LayoutParams((int) (279.0f * f), (int) (163.0f * f));
            relativeLayout.setPadding((int) (14.0f * f), 0, (int) (12.0f * f), (int) (12.0f * f));
            relativeLayout.setLayoutParams(layoutParams4);
            relativeLayout.setBackgroundColor(Color.rgb(247, 251, 247));
            Drawable paintDrawable = new PaintDrawable(Color.rgb(247, 251, 247));
            paintDrawable.setCornerRadius(f * 5.0f);
            relativeLayout.setBackgroundDrawable(paintDrawable);
            return relativeLayout;
        }

        private void m13222a(String str, IUiListener iUiListener, Object obj) {
            PackageInfo packageInfo;
            Drawable drawable = null;
            Dialog dialog = new Dialog(this.f11373b.f11383c);
            dialog.requestWindowFeature(1);
            PackageManager packageManager = this.f11373b.f11383c.getPackageManager();
            try {
                packageInfo = packageManager.getPackageInfo(this.f11373b.f11383c.getPackageName(), 0);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
                packageInfo = null;
            }
            if (packageInfo != null) {
                drawable = packageInfo.applicationInfo.loadIcon(packageManager);
            }
            OnClickListener c21901 = new C21901(this, dialog, iUiListener, obj);
            OnClickListener c21912 = new C21912(this, dialog, iUiListener, obj);
            Drawable colorDrawable = new ColorDrawable();
            colorDrawable.setAlpha(0);
            dialog.getWindow().setBackgroundDrawable(colorDrawable);
            dialog.setContentView(m13221a(this.f11373b.f11383c, drawable, str, c21901, c21912));
            dialog.setOnCancelListener(new C21923(this, iUiListener, obj));
            if (this.f11373b.f11383c != null && !this.f11373b.f11383c.isFinishing()) {
                dialog.show();
            }
        }

        protected void m13223a() {
            HttpUtils.requestAsync(this.f11373b.mToken, this.f11373b.f11383c, "http://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi", this.f11373b.composeActivityParams(), C2955m.f14864a, null);
        }

        public void onCancel() {
            if (this.f11372a != null) {
                this.f11372a.onCancel();
            }
        }

        public void onComplete(Object obj) {
            Object obj2;
            Object obj3 = null;
            if (obj != null) {
                JSONObject jSONObject = (JSONObject) obj;
                if (jSONObject != null) {
                    String string;
                    Object obj4;
                    String str = C2915a.f14760f;
                    try {
                        if (jSONObject.getInt("sendinstall") == 1) {
                            obj3 = 1;
                        }
                        string = jSONObject.getString("installwording");
                        obj4 = obj3;
                    } catch (JSONException e) {
                        obj2 = null;
                        C2333f.m13758d("FeedConfirm", "There is no value for sendinstall.");
                        String str2 = str;
                        obj4 = obj2;
                        string = str2;
                    }
                    obj2 = URLDecoder.decode(string);
                    C2333f.m13754b("TAG", " WORDING = " + obj2 + "xx");
                    if (obj4 != null && !TextUtils.isEmpty(obj2)) {
                        m13222a(obj2, this.f11372a, obj);
                    } else if (this.f11372a != null) {
                        this.f11372a.onComplete(obj);
                    }
                }
            }
        }

        public void onError(UiError uiError) {
            if (this.f11372a != null) {
                this.f11372a.onError(uiError);
            }
        }
    }

    class TokenListener implements IUiListener {
        final /* synthetic */ AuthAgent f11377a;
        private final IUiListener f11378b;
        private final boolean f11379c;
        private final Context f11380d;

        public TokenListener(AuthAgent authAgent, Context context, IUiListener iUiListener, boolean z, boolean z2) {
            this.f11377a = authAgent;
            this.f11380d = context;
            this.f11378b = iUiListener;
            this.f11379c = z;
            C2333f.m13754b(C2333f.f12014d, "OpenUi, TokenListener()");
        }

        public void onCancel() {
            C2333f.m13754b(C2333f.f12014d, "OpenUi, TokenListener() onCancel");
            this.f11378b.onCancel();
            C2333f.m13753b();
        }

        public void onComplete(Object obj) {
            C2333f.m13754b(C2333f.f12014d, "OpenUi, TokenListener() onComplete");
            JSONObject jSONObject = (JSONObject) obj;
            try {
                String string = jSONObject.getString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2);
                String string2 = jSONObject.getString(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2);
                String string3 = jSONObject.getString(SocialConstants.PARAM_OPEN_ID);
                if (!(string == null || this.f11377a.mToken == null || string3 == null)) {
                    this.f11377a.mToken.setAccessToken(string, string2);
                    this.f11377a.mToken.setOpenId(string3);
                    C2186a.m13219d(this.f11380d, this.f11377a.mToken);
                }
                string = jSONObject.getString(Constants.PARAM_PLATFORM_ID);
                if (string != null) {
                    try {
                        this.f11380d.getSharedPreferences(Constants.PREFERENCE_PF, 0).edit().putString(Constants.PARAM_PLATFORM_ID, string).commit();
                    } catch (Throwable e) {
                        e.printStackTrace();
                        C2333f.m13755b(C2333f.f12014d, "OpenUi, TokenListener() onComplete error", e);
                    }
                }
                if (this.f11379c) {
                    CookieSyncManager.getInstance().sync();
                }
            } catch (Throwable e2) {
                e2.printStackTrace();
                C2333f.m13755b(C2333f.f12014d, "OpenUi, TokenListener() onComplete error", e2);
            }
            this.f11378b.onComplete(jSONObject);
            this.f11377a.releaseResource();
            C2333f.m13753b();
        }

        public void onError(UiError uiError) {
            C2333f.m13754b(C2333f.f12014d, "OpenUi, TokenListener() onError");
            this.f11378b.onError(uiError);
            C2333f.m13753b();
        }
    }

    public AuthAgent(QQToken qQToken) {
        super(qQToken);
    }

    private int m13224a(boolean z, IUiListener iUiListener) {
        C2333f.m13757c(C2333f.f12014d, "OpenUi, showDialog -- start");
        CookieSyncManager.createInstance(Global.getContext());
        Bundle composeCGIParams = composeCGIParams();
        if (z) {
            composeCGIParams.putString("isadd", Constants.VIA_TO_TYPE_QQ_GROUP);
        }
        composeCGIParams.putString(XiaomiOAuthConstants.EXTRA_SCOPE_2, this.f11382b);
        composeCGIParams.putString(Constants.PARAM_CLIENT_ID, this.mToken.getAppId());
        if (isOEM) {
            composeCGIParams.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + installChannel + "-" + "android" + "-" + registerChannel + "-" + businessId);
        } else {
            composeCGIParams.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        String str = (System.currentTimeMillis() / 1000) + C2915a.f14760f;
        composeCGIParams.putString("sign", SystemUtils.getAppSignatureMD5(Global.getContext(), str));
        composeCGIParams.putString("time", str);
        composeCGIParams.putString("display", "mobile");
        composeCGIParams.putString("response_type", "token");
        composeCGIParams.putString(AuthorizeActivityBase.KEY_REDIRECT_URI, ServerSetting.DEFAULT_REDIRECT_URI);
        composeCGIParams.putString("cancel_display", Constants.VIA_TO_TYPE_QQ_GROUP);
        composeCGIParams.putString("switch", Constants.VIA_TO_TYPE_QQ_GROUP);
        composeCGIParams.putString("status_userip", com.tencent.open.utils.Util.getUserIp());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_CGI_AUTHORIZE));
        stringBuilder.append(com.tencent.open.utils.Util.encodeUrl(composeCGIParams));
        String stringBuilder2 = stringBuilder.toString();
        IUiListener tokenListener = new TokenListener(this, Global.getContext(), iUiListener, true, false);
        C2333f.m13754b(C2333f.f12014d, "OpenUi, showDialog TDialog");
        ThreadManager.executeOnSubThread(new C21881(this, stringBuilder2, tokenListener));
        C2333f.m13757c(C2333f.f12014d, "OpenUi, showDialog -- end");
        return 2;
    }

    private void m13226a(String str) {
        try {
            JSONObject parseJson = com.tencent.open.utils.Util.parseJson(str);
            Object string = parseJson.getString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2);
            Object string2 = parseJson.getString(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2);
            Object string3 = parseJson.getString(SocialConstants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                this.mToken.setAccessToken(string, string2);
                this.mToken.setOpenId(string3);
            }
        } catch (Exception e) {
        }
    }

    private boolean m13227a(Activity activity, Fragment fragment, boolean z) {
        C2333f.m13757c(C2333f.f12014d, "startActionActivity() -- start");
        Intent targetActivityIntent = getTargetActivityIntent("com.tencent.open.agent.AgentActivity");
        if (targetActivityIntent != null) {
            Bundle composeCGIParams = composeCGIParams();
            if (z) {
                composeCGIParams.putString("isadd", Constants.VIA_TO_TYPE_QQ_GROUP);
            }
            composeCGIParams.putString(XiaomiOAuthConstants.EXTRA_SCOPE_2, this.f11382b);
            composeCGIParams.putString(Constants.PARAM_CLIENT_ID, this.mToken.getAppId());
            if (isOEM) {
                composeCGIParams.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + installChannel + "-" + "android" + "-" + registerChannel + "-" + businessId);
            } else {
                composeCGIParams.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
            }
            composeCGIParams.putString("need_pay", Constants.VIA_TO_TYPE_QQ_GROUP);
            composeCGIParams.putString(Constants.KEY_APP_NAME, SystemUtils.getAppName(Global.getContext()));
            targetActivityIntent.putExtra(Constants.KEY_ACTION, SystemUtils.ACTION_LOGIN);
            targetActivityIntent.putExtra(Constants.KEY_PARAMS, composeCGIParams);
            this.mActivityIntent = targetActivityIntent;
            if (hasActivityForIntent()) {
                this.f11381a = new FeedConfirmListener(this, this.f11381a);
                if (fragment != null) {
                    C2333f.m13754b("AuthAgent", "startAssitActivity fragment");
                    startAssitActivity(fragment, this.f11381a);
                } else {
                    C2333f.m13754b("AuthAgent", "startAssitActivity activity");
                    startAssitActivity(activity, this.f11381a);
                }
                C2333f.m13757c(C2333f.f12014d, "startActionActivity() -- end");
                C2341d.m13784a().m13785a(0, "LOGIN_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), C2915a.f14760f, Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, C2915a.f14760f);
                return true;
            }
        }
        C2341d.m13784a().m13785a(1, "LOGIN_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), C2915a.f14760f, Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "startActionActivity fail");
        C2333f.m13757c(C2333f.f12014d, "startActionActivity() -- end");
        return false;
    }

    protected void m13235a(IUiListener iUiListener) {
        C2333f.m13757c(C2333f.f12014d, "reportDAU() -- start");
        String str = "tencent&sdk&qazxc***14969%%";
        String str2 = "qzone3.4";
        Object accessToken = this.mToken.getAccessToken();
        Object openId = this.mToken.getOpenId();
        Object appId = this.mToken.getAppId();
        Object obj = C2915a.f14760f;
        if (!(TextUtils.isEmpty(accessToken) || TextUtils.isEmpty(openId) || TextUtils.isEmpty(appId))) {
            obj = com.tencent.open.utils.Util.encrypt(str + accessToken + appId + openId + str2);
        }
        if (TextUtils.isEmpty(obj)) {
            C2333f.m13759e(C2333f.f12014d, "reportDAU -- encrytoken is null");
            return;
        }
        Bundle composeCGIParams = composeCGIParams();
        composeCGIParams.putString("encrytoken", obj);
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "https://openmobile.qq.com/user/user_login_statis", composeCGIParams, C2955m.f14864a, null);
        C2333f.m13757c(C2333f.f12014d, "reportDAU() -- end");
    }

    protected void m13236b(IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        composeCGIParams.putString("reqType", "checkLogin");
        IRequestListener tempRequestListener = new TempRequestListener(new CheckLoginListener(this, iUiListener));
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "https://openmobile.qq.com/v3/user/get_info", composeCGIParams, C2951i.f14860a, tempRequestListener);
    }

    public int doLogin(Activity activity, String str, IUiListener iUiListener) {
        return doLogin(activity, str, iUiListener, false, null);
    }

    public int doLogin(Activity activity, String str, IUiListener iUiListener, boolean z, Fragment fragment) {
        this.f11382b = str;
        this.f11383c = activity;
        this.f11381a = iUiListener;
        if (m13227a(activity, fragment, z)) {
            C2333f.m13757c(C2333f.f12014d, "OpenUi, showUi, return Constants.UI_ACTIVITY");
            C2341d.m13784a().m13787a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SSO_LOGIN, Constants.VIA_TO_TYPE_QQ_GROUP, Constants.VIA_SHARE_TYPE_TEXT, Constants.VIA_RESULT_SUCCESS, Constants.VIA_RESULT_SUCCESS, Constants.VIA_RESULT_SUCCESS);
            return 1;
        }
        C2341d.m13784a().m13787a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SSO_LOGIN, Constants.VIA_TO_TYPE_QQ_GROUP, Constants.VIA_SHARE_TYPE_TEXT, Constants.VIA_TO_TYPE_QQ_GROUP, Constants.VIA_RESULT_SUCCESS, Constants.VIA_RESULT_SUCCESS);
        C2333f.m13758d(C2333f.f12014d, "startActivity fail show dialog.");
        this.f11381a = new FeedConfirmListener(this, this.f11381a);
        return m13224a(z, this.f11381a);
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        IUiListener iUiListener;
        ThreadManager.executeOnSubThread(new C21892(this));
        for (ApiTask apiTask : this.mTaskList) {
            if (apiTask.mRequestCode == i) {
                IUiListener iUiListener2 = apiTask.mListener;
                this.mTaskList.remove(apiTask);
                iUiListener = iUiListener2;
                break;
            }
        }
        iUiListener = null;
        if (intent != null) {
            m13226a(intent.getStringExtra(Constants.KEY_RESPONSE));
            if (iUiListener == null) {
                AssistActivity.setResultDataForLogin(activity, intent);
                return;
            }
            if (i2 == -1) {
                BaseApi.handleDataToListener(intent, iUiListener);
            } else {
                C2333f.m13754b(C2333f.f12014d, "OpenUi, onActivityResult, Constants.ACTIVITY_CANCEL");
                iUiListener.onCancel();
            }
            releaseResource();
            C2333f.m13753b();
        } else if (iUiListener != null) {
            iUiListener.onCancel();
        }
    }

    public void releaseResource() {
        this.f11383c = null;
        this.f11381a = null;
    }
}
