package cn.sharesdk.framework.authorize;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.sharesdk.framework.TitleLayout;
import cn.sharesdk.framework.authorize.ResizeLayout.OnResizeListener;
import cn.sharesdk.framework.utils.C0205d;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.mob.tools.utils.C2178R;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: cn.sharesdk.framework.authorize.g */
public class C0161g extends C0153a implements Callback, OnResizeListener {
    protected AuthorizeListener f211b;
    private AuthorizeAdapter f212c;
    private RegisterView f213d;
    private WebView f214e;

    /* renamed from: cn.sharesdk.framework.authorize.g.a */
    class C0160a implements Interpolator {
        private float[] f210a;

        private C0160a() {
            this.f210a = new float[]{0.0f, 0.02692683f, 0.053847015f, 0.080753915f, 0.10764089f, 0.13450131f, 0.16132854f, 0.18811597f, 0.21485697f, 0.24154496f, 0.26817337f, 0.2947356f, 0.3212251f, 0.34763536f, 0.37395984f, 0.40019205f, 0.42632553f, 0.4523538f, 0.47827047f, 0.50406915f, 0.52974343f, 0.555287f, 0.5806936f, 0.60595685f, 0.6310707f, 0.65602875f, 0.68082494f, 0.70545316f, 0.72990733f, 0.75418144f, 0.7782694f, 0.8021654f, 0.8258634f, 0.8493577f, 0.8726424f, 0.89571184f, 0.9185602f, 0.94118196f, 0.9635715f, 0.9857233f, 1.0076319f, 1.0292919f, 1.0506978f, 1.0718446f, 1.0927268f, 1.1133395f, 1.1336775f, 1.1537358f, 1.1735094f, 1.1929934f, 1.1893399f, 1.1728106f, 1.1565471f, 1.1405534f, 1.1248333f, 1.1093911f, 1.0942302f, 1.0793544f, 1.0647675f, 1.050473f, 1.0364745f, 1.0227754f, 1.0093791f, 0.99628896f, 0.9835081f, 0.9710398f, 0.958887f, 0.9470527f, 0.93553996f, 0.9243516f, 0.91349024f, 0.90295863f, 0.90482706f, 0.9114033f, 0.91775465f, 0.9238795f, 0.9297765f, 0.93544406f, 0.9408808f, 0.94608533f, 0.95105654f, 0.955793f, 0.9602937f, 0.9645574f, 0.96858317f, 0.9723699f, 0.97591674f, 0.97922283f, 0.9822872f, 0.9851093f, 0.98768836f, 0.9900237f, 0.9921147f, 0.993961f, 0.99556196f, 0.9969173f, 0.9980267f, 0.99888986f, 0.99950653f, 0.9998766f, C2020f.f10933c};
        }

        public float getInterpolation(float f) {
            int i = 100;
            int i2 = (int) (100.0f * f);
            if (i2 < 0) {
                i2 = 0;
            }
            if (i2 <= 100) {
                i = i2;
            }
            return this.f210a[i];
        }
    }

    private AuthorizeAdapter m455c() {
        try {
            ActivityInfo activityInfo = this.activity.getPackageManager().getActivityInfo(this.activity.getComponentName(), SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            if (activityInfo.metaData == null || activityInfo.metaData.isEmpty()) {
                return null;
            }
            String string = activityInfo.metaData.getString("AuthorizeAdapter");
            if (string == null || string.length() <= 0) {
                string = activityInfo.metaData.getString("Adapter");
                if (string == null || string.length() <= 0) {
                    return null;
                }
            }
            Object newInstance = Class.forName(string).newInstance();
            return !(newInstance instanceof AuthorizeAdapter) ? null : (AuthorizeAdapter) newInstance;
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
            return null;
        }
    }

    public void OnResize(int i, int i2, int i3, int i4) {
        if (this.f212c != null) {
            this.f212c.onResize(i, i2, i3, i4);
        }
    }

    public void m456a(AuthorizeListener authorizeListener) {
        this.f211b = authorizeListener;
    }

    protected RegisterView m457b() {
        RegisterView registerView = new RegisterView(this.activity);
        registerView.m441a().setOnClickListener(new C0162h(this));
        this.f214e = registerView.m443b();
        WebSettings settings = this.f214e.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        this.f214e.setVerticalScrollBarEnabled(false);
        this.f214e.setHorizontalScrollBarEnabled(false);
        this.f214e.setWebViewClient(this.a.getAuthorizeWebviewClient(this));
        new C0164j(this).start();
        return registerView;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                AuthorizeListener authorizeListener;
                if (message.arg1 != 1) {
                    String str = (String) message.obj;
                    if (!TextUtils.isEmpty(str)) {
                        this.f214e.loadUrl(str);
                        break;
                    }
                    finish();
                    authorizeListener = this.a.getAuthorizeListener();
                    if (authorizeListener != null) {
                        authorizeListener.onError(new Throwable("Authorize URL is empty (platform: " + this.a.getPlatform().getName() + ")"));
                        break;
                    }
                }
                authorizeListener = this.a.getAuthorizeListener();
                if (authorizeListener != null) {
                    authorizeListener.onError(new Throwable("Network error (platform: " + this.a.getPlatform().getName() + ")"));
                    break;
                }
                break;
        }
        return false;
    }

    public void onCreate() {
        if (this.f213d == null) {
            this.f213d = m457b();
            this.f213d.m437a(this);
            this.f213d.m442a(this.f212c.isNotitle());
            this.f212c.setBodyView(this.f213d.m445d());
            this.f212c.setWebView(this.f213d.m443b());
            TitleLayout c = this.f213d.m444c();
            this.f212c.setTitleView(c);
            String name = this.a.getPlatform().getName();
            this.f212c.setPlatformName(this.a.getPlatform().getName());
            try {
                c.getTvTitle().setText(C2178R.getStringRes(getContext(), "ssdk_" + name.toLowerCase()));
            } catch (Throwable th) {
                C0205d.m752a().m750w(th);
            }
        }
        this.f212c.onCreate();
        if (!(this.f212c == null || this.f212c.isPopUpAnimationDisable())) {
            Animation scaleAnimation = new ScaleAnimation(0.0f, C2020f.f10933c, 0.0f, C2020f.f10933c, 1, 0.5f, 1, 0.5f);
            scaleAnimation.setDuration(550);
            scaleAnimation.setInterpolator(new C0160a());
            this.f213d.setAnimation(scaleAnimation);
        }
        this.activity.setContentView(this.f213d);
    }

    public void onDestroy() {
        if (this.f212c != null) {
            this.f212c.onDestroy();
        }
    }

    public boolean onFinish() {
        return this.f212c != null ? this.f212c.onFinish() : super.onFinish();
    }

    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        boolean z = false;
        if (this.f212c != null) {
            z = this.f212c.onKeyEvent(i, keyEvent);
        }
        if (!z && i == 4 && keyEvent.getAction() == 0) {
            AuthorizeListener authorizeListener = this.a.getAuthorizeListener();
            if (authorizeListener != null) {
                authorizeListener.onCancel();
            }
        }
        return z ? true : super.onKeyEvent(i, keyEvent);
    }

    public void onPause() {
        if (this.f212c != null) {
            this.f212c.onPause();
        }
    }

    public void onRestart() {
        if (this.f212c != null) {
            this.f212c.onRestart();
        }
    }

    public void onResume() {
        if (this.f212c != null) {
            this.f212c.onResume();
        }
    }

    public void onStart() {
        if (this.f212c != null) {
            this.f212c.onStart();
        }
    }

    public void onStop() {
        if (this.f212c != null) {
            this.f212c.onStop();
        }
    }

    public void setActivity(Activity activity) {
        super.setActivity(activity);
        if (this.f212c == null) {
            this.f212c = m455c();
            if (this.f212c == null) {
                this.f212c = new AuthorizeAdapter();
            }
        }
        this.f212c.setActivity(activity);
    }
}
