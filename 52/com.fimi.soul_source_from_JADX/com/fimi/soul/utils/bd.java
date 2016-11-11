package com.fimi.soul.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewConfiguration;

public class bd {
    private static final String f10071a = "status_bar_height";
    private static final String f10072b = "navigation_bar_height";
    private static final String f10073c = "navigation_bar_height_landscape";
    private static final String f10074d = "navigation_bar_width";
    private final boolean f10075e;
    private final boolean f10076f;
    private final int f10077g;
    private final int f10078h;
    private final boolean f10079i;
    private final int f10080j;
    private final int f10081k;
    private final boolean f10082l;
    private final float f10083m;

    private bd(Activity activity, boolean z, boolean z2) {
        boolean z3 = true;
        Resources resources = activity.getResources();
        this.f10082l = resources.getConfiguration().orientation == 1;
        this.f10083m = m12335a(activity);
        this.f10077g = m12337a(resources, f10071a);
        this.f10078h = m12336a((Context) activity);
        this.f10080j = m12338b(activity);
        this.f10081k = m12339c(activity);
        if (this.f10080j <= 0) {
            z3 = false;
        }
        this.f10079i = z3;
        this.f10075e = z;
        this.f10076f = z2;
    }

    @SuppressLint({"NewApi"})
    private float m12335a(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (VERSION.SDK_INT >= 16) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        } else {
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        return Math.min(((float) displayMetrics.widthPixels) / displayMetrics.density, ((float) displayMetrics.heightPixels) / displayMetrics.density);
    }

    @TargetApi(14)
    private int m12336a(Context context) {
        if (VERSION.SDK_INT < 14) {
            return 0;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843499, typedValue, true);
        return context.getResources().getDimensionPixelSize(typedValue.resourceId);
    }

    private int m12337a(Resources resources, String str) {
        int identifier = resources.getIdentifier(str, "dimen", "android");
        return identifier > 0 ? resources.getDimensionPixelSize(identifier) : 0;
    }

    @TargetApi(14)
    private int m12338b(Context context) {
        Resources resources = context.getResources();
        if (VERSION.SDK_INT < 14 || ViewConfiguration.get(context).hasPermanentMenuKey()) {
            return 0;
        }
        return m12337a(resources, this.f10082l ? f10072b : f10073c);
    }

    @TargetApi(14)
    private int m12339c(Context context) {
        return (VERSION.SDK_INT < 14 || ViewConfiguration.get(context).hasPermanentMenuKey()) ? 0 : m12337a(context.getResources(), f10074d);
    }

    public int m12340a(boolean z) {
        int i = 0;
        int i2 = this.f10075e ? this.f10077g : 0;
        if (z) {
            i = this.f10078h;
        }
        return i + i2;
    }

    public boolean m12341a() {
        return this.f10083m >= 600.0f || this.f10082l;
    }

    public int m12342b() {
        return this.f10077g;
    }

    public int m12343c() {
        return this.f10078h;
    }

    public boolean m12344d() {
        return this.f10079i;
    }

    public int m12345e() {
        return this.f10080j;
    }

    public int m12346f() {
        return this.f10081k;
    }

    public int m12347g() {
        return (this.f10076f && m12341a()) ? this.f10080j : 0;
    }

    public int m12348h() {
        return (!this.f10076f || m12341a()) ? 0 : this.f10081k;
    }
}
