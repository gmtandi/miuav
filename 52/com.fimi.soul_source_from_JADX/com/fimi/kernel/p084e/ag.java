package com.fimi.kernel.p084e;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewConfiguration;

/* renamed from: com.fimi.kernel.e.ag */
public class ag {
    private static final String f5286a = "status_bar_height";
    private static final String f5287b = "navigation_bar_height";
    private static final String f5288c = "navigation_bar_height_landscape";
    private static final String f5289d = "navigation_bar_width";
    private final boolean f5290e;
    private final boolean f5291f;
    private final int f5292g;
    private final int f5293h;
    private final boolean f5294i;
    private final int f5295j;
    private final int f5296k;
    private final boolean f5297l;
    private final float f5298m;

    private ag(Activity activity, boolean z, boolean z2) {
        boolean z3 = true;
        Resources resources = activity.getResources();
        this.f5297l = resources.getConfiguration().orientation == 1;
        this.f5298m = m8060a(activity);
        this.f5292g = m8062a(resources, f5286a);
        this.f5293h = m8061a((Context) activity);
        this.f5295j = m8063b(activity);
        this.f5296k = m8064c(activity);
        if (this.f5295j <= 0) {
            z3 = false;
        }
        this.f5294i = z3;
        this.f5290e = z;
        this.f5291f = z2;
    }

    @SuppressLint({"NewApi"})
    private float m8060a(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (VERSION.SDK_INT >= 16) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        } else {
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        return Math.min(((float) displayMetrics.widthPixels) / displayMetrics.density, ((float) displayMetrics.heightPixels) / displayMetrics.density);
    }

    @TargetApi(14)
    private int m8061a(Context context) {
        if (VERSION.SDK_INT < 14) {
            return 0;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843499, typedValue, true);
        return context.getResources().getDimensionPixelSize(typedValue.resourceId);
    }

    private int m8062a(Resources resources, String str) {
        int identifier = resources.getIdentifier(str, "dimen", "android");
        return identifier > 0 ? resources.getDimensionPixelSize(identifier) : 0;
    }

    @TargetApi(14)
    private int m8063b(Context context) {
        Resources resources = context.getResources();
        if (VERSION.SDK_INT < 14 || ViewConfiguration.get(context).hasPermanentMenuKey()) {
            return 0;
        }
        return m8062a(resources, this.f5297l ? f5287b : f5288c);
    }

    @TargetApi(14)
    private int m8064c(Context context) {
        return (VERSION.SDK_INT < 14 || ViewConfiguration.get(context).hasPermanentMenuKey()) ? 0 : m8062a(context.getResources(), f5289d);
    }

    public int m8065a(boolean z) {
        int i = 0;
        int i2 = this.f5290e ? this.f5292g : 0;
        if (z) {
            i = this.f5293h;
        }
        return i + i2;
    }

    public boolean m8066a() {
        return this.f5298m >= 600.0f || this.f5297l;
    }

    public int m8067b() {
        return this.f5292g;
    }

    public int m8068c() {
        return this.f5293h;
    }

    public boolean m8069d() {
        return this.f5294i;
    }

    public int m8070e() {
        return this.f5295j;
    }

    public int m8071f() {
        return this.f5296k;
    }

    public int m8072g() {
        return (this.f5291f && m8066a()) ? this.f5295j : 0;
    }

    public int m8073h() {
        return (!this.f5291f || m8066a()) ? 0 : this.f5296k;
    }
}
