package cn.sharesdk.framework.authorize;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.sharesdk.framework.TitleLayout;
import cn.sharesdk.framework.utils.C0205d;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.mob.tools.utils.C2178R;
import java.lang.reflect.Method;

public class RegisterView extends ResizeLayout {
    private TitleLayout f194a;
    private RelativeLayout f195b;
    private WebView f196c;
    private TextView f197d;

    public RegisterView(Context context) {
        super(context);
        m439a(context);
    }

    public RegisterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m439a(context);
    }

    private void m439a(Context context) {
        int bitmapRes;
        setBackgroundColor(-1);
        setOrientation(1);
        int b = m440b(context);
        this.f194a = new TitleLayout(context);
        try {
            bitmapRes = C2178R.getBitmapRes(context, "ssdk_auth_title_back");
            if (bitmapRes > 0) {
                this.f194a.setBackgroundResource(bitmapRes);
            }
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
        }
        this.f194a.getBtnRight().setVisibility(8);
        bitmapRes = C2178R.getStringRes(getContext(), "ssdk_weibo_oauth_regiseter");
        if (bitmapRes > 0) {
            this.f194a.getTvTitle().setText(bitmapRes);
        }
        addView(this.f194a);
        View imageView = new ImageView(context);
        int bitmapRes2 = C2178R.getBitmapRes(context, "ssdk_logo");
        if (bitmapRes2 > 0) {
            imageView.setImageResource(bitmapRes2);
        }
        imageView.setScaleType(ScaleType.CENTER_INSIDE);
        imageView.setPadding(0, 0, C2178R.dipToPx(context, 10), 0);
        imageView.setLayoutParams(new LayoutParams(-2, -1));
        imageView.setOnClickListener(new C0156c(this));
        this.f194a.addView(imageView);
        this.f195b = new RelativeLayout(context);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, 0);
        layoutParams.weight = C2020f.f10933c;
        this.f195b.setLayoutParams(layoutParams);
        addView(this.f195b);
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.f195b.addView(linearLayout);
        this.f197d = new TextView(context);
        this.f197d.setLayoutParams(new LayoutParams(-1, 5));
        this.f197d.setBackgroundColor(-12929302);
        linearLayout.addView(this.f197d);
        this.f197d.setVisibility(8);
        this.f196c = new WebView(context);
        layoutParams = new LayoutParams(-1, -1);
        layoutParams.weight = C2020f.f10933c;
        this.f196c.setLayoutParams(layoutParams);
        WebChromeClient c0157d = new C0157d(this, b);
        if (VERSION.SDK_INT > 10 && VERSION.SDK_INT < 17) {
            try {
                Method method = this.f196c.getClass().getMethod("removeJavascriptInterface", new Class[]{String.class});
                method.setAccessible(true);
                method.invoke(this.f196c, new Object[]{"searchBoxJavaBridge_"});
            } catch (Throwable th2) {
                C0205d.m752a().m738d(th2);
            }
        }
        this.f196c.setWebChromeClient(c0157d);
        linearLayout.addView(this.f196c);
        this.f196c.requestFocus();
    }

    private int m440b(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (!(context instanceof Activity)) {
            return 0;
        }
        WindowManager windowManager = ((Activity) context).getWindowManager();
        if (windowManager == null) {
            return 0;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public View m441a() {
        return this.f194a.getBtnBack();
    }

    public void m442a(boolean z) {
        this.f194a.setVisibility(z ? 8 : 0);
    }

    public WebView m443b() {
        return this.f196c;
    }

    public TitleLayout m444c() {
        return this.f194a;
    }

    public RelativeLayout m445d() {
        return this.f195b;
    }
}
