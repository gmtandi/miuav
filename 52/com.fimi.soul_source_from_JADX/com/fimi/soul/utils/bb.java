package com.fimi.soul.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

public class bb {
    public static final int f10062a = -1728053248;
    private static boolean f10063i;
    private final bd f10064b;
    private boolean f10065c;
    private boolean f10066d;
    private boolean f10067e;
    private boolean f10068f;
    private View f10069g;
    private View f10070h;

    static {
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            f10063i = "V6".equals((String) cls.getDeclaredMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{"ro.miui.ui.version.name"}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TargetApi(19)
    public bb(Activity activity) {
        Window window = activity.getWindow();
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        if (VERSION.SDK_INT >= 19) {
            TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(new int[]{16843759, 16843760});
            try {
                this.f10065c = obtainStyledAttributes.getBoolean(0, false);
                this.f10066d = obtainStyledAttributes.getBoolean(1, false);
                LayoutParams attributes = window.getAttributes();
                if ((67108864 & attributes.flags) != 0) {
                    this.f10065c = true;
                }
                if ((attributes.flags & 134217728) != 0) {
                    this.f10066d = true;
                }
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        this.f10064b = new bd(this.f10065c, this.f10066d, null);
        if (!this.f10064b.m12344d()) {
            this.f10066d = false;
        }
        if (this.f10065c) {
            m12315a((Context) activity, viewGroup);
        }
        if (this.f10066d) {
            m12316b(activity, viewGroup);
        }
    }

    private void m12315a(Context context, ViewGroup viewGroup) {
        this.f10069g = new View(context);
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, this.f10064b.m12342b());
        layoutParams.gravity = 48;
        if (this.f10066d && !this.f10064b.m12341a()) {
            layoutParams.rightMargin = this.f10064b.m12346f();
        }
        this.f10069g.setLayoutParams(layoutParams);
        this.f10069g.setBackgroundColor(f10062a);
        this.f10069g.setVisibility(8);
        viewGroup.addView(this.f10069g);
    }

    private void m12316b(Context context, ViewGroup viewGroup) {
        ViewGroup.LayoutParams layoutParams;
        this.f10070h = new View(context);
        if (this.f10064b.m12341a()) {
            layoutParams = new FrameLayout.LayoutParams(-1, this.f10064b.m12345e());
            layoutParams.gravity = 80;
        } else {
            layoutParams = new FrameLayout.LayoutParams(this.f10064b.m12346f(), -1);
            layoutParams.gravity = 5;
        }
        this.f10070h.setLayoutParams(layoutParams);
        this.f10070h.setBackgroundColor(f10062a);
        this.f10070h.setVisibility(8);
        viewGroup.addView(this.f10070h);
    }

    public bd m12317a() {
        return this.f10064b;
    }

    public void m12318a(float f) {
        m12323b(f);
        m12328c(f);
    }

    public void m12319a(int i) {
        m12329c(i);
        m12333e(i);
    }

    public void m12320a(Drawable drawable) {
        m12325b(drawable);
        m12330c(drawable);
    }

    public void m12321a(boolean z) {
        this.f10067e = z;
        if (this.f10065c) {
            this.f10069g.setVisibility(z ? 0 : 8);
        }
    }

    public void m12322a(boolean z, Activity activity) {
        int i = 0;
        if (f10063i) {
            Class cls = activity.getWindow().getClass();
            try {
                Class cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i2 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                Method method = cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
                Window window = activity.getWindow();
                Object[] objArr = new Object[2];
                if (z) {
                    i = i2;
                }
                objArr[0] = Integer.valueOf(i);
                objArr[1] = Integer.valueOf(i2);
                method.invoke(window, objArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @TargetApi(11)
    public void m12323b(float f) {
        if (this.f10065c && VERSION.SDK_INT >= 11) {
            this.f10069g.setAlpha(f);
        }
    }

    public void m12324b(int i) {
        m12332d(i);
        m12334f(i);
    }

    public void m12325b(Drawable drawable) {
        if (this.f10065c) {
            this.f10069g.setBackgroundDrawable(drawable);
        }
    }

    public void m12326b(boolean z) {
        this.f10068f = z;
        if (this.f10066d) {
            this.f10070h.setVisibility(z ? 0 : 8);
        }
    }

    public boolean m12327b() {
        return this.f10067e;
    }

    @TargetApi(11)
    public void m12328c(float f) {
        if (this.f10066d && VERSION.SDK_INT >= 11) {
            this.f10070h.setAlpha(f);
        }
    }

    public void m12329c(int i) {
        if (this.f10065c) {
            this.f10069g.setBackgroundColor(i);
        }
    }

    public void m12330c(Drawable drawable) {
        if (this.f10066d) {
            this.f10070h.setBackgroundDrawable(drawable);
        }
    }

    public boolean m12331c() {
        return this.f10068f;
    }

    public void m12332d(int i) {
        if (this.f10065c) {
            this.f10069g.setBackgroundResource(i);
        }
    }

    public void m12333e(int i) {
        if (this.f10066d) {
            this.f10070h.setBackgroundColor(i);
        }
    }

    public void m12334f(int i) {
        if (this.f10066d) {
            this.f10070h.setBackgroundResource(i);
        }
    }
}
