package com.fimi.kernel.p084e;

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

/* renamed from: com.fimi.kernel.e.ae */
public class ae {
    public static final int f5277a = -1728053248;
    private static boolean f5278i;
    private final ag f5279b;
    private boolean f5280c;
    private boolean f5281d;
    private boolean f5282e;
    private boolean f5283f;
    private View f5284g;
    private View f5285h;

    static {
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            f5278i = "V6".equals((String) cls.getDeclaredMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{"ro.miui.ui.version.name"}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TargetApi(19)
    public ae(Activity activity) {
        Window window = activity.getWindow();
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        if (VERSION.SDK_INT >= 19) {
            TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(new int[]{16843759, 16843760});
            try {
                this.f5280c = obtainStyledAttributes.getBoolean(0, false);
                this.f5281d = obtainStyledAttributes.getBoolean(1, false);
                LayoutParams attributes = window.getAttributes();
                if ((67108864 & attributes.flags) != 0) {
                    this.f5280c = true;
                }
                if ((attributes.flags & 134217728) != 0) {
                    this.f5281d = true;
                }
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        this.f5279b = new ag(this.f5280c, this.f5281d, null);
        if (!this.f5279b.m8069d()) {
            this.f5281d = false;
        }
        if (this.f5280c) {
            m8040a((Context) activity, viewGroup);
        }
        if (this.f5281d) {
            m8041b(activity, viewGroup);
        }
    }

    private void m8040a(Context context, ViewGroup viewGroup) {
        this.f5284g = new View(context);
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, this.f5279b.m8067b());
        layoutParams.gravity = 48;
        if (this.f5281d && !this.f5279b.m8066a()) {
            layoutParams.rightMargin = this.f5279b.m8071f();
        }
        this.f5284g.setLayoutParams(layoutParams);
        this.f5284g.setBackgroundColor(f5277a);
        this.f5284g.setVisibility(8);
        viewGroup.addView(this.f5284g);
    }

    private void m8041b(Context context, ViewGroup viewGroup) {
        ViewGroup.LayoutParams layoutParams;
        this.f5285h = new View(context);
        if (this.f5279b.m8066a()) {
            layoutParams = new FrameLayout.LayoutParams(-1, this.f5279b.m8070e());
            layoutParams.gravity = 80;
        } else {
            layoutParams = new FrameLayout.LayoutParams(this.f5279b.m8071f(), -1);
            layoutParams.gravity = 5;
        }
        this.f5285h.setLayoutParams(layoutParams);
        this.f5285h.setBackgroundColor(f5277a);
        this.f5285h.setVisibility(8);
        viewGroup.addView(this.f5285h);
    }

    public ag m8042a() {
        return this.f5279b;
    }

    public void m8043a(float f) {
        m8048b(f);
        m8053c(f);
    }

    public void m8044a(int i) {
        m8054c(i);
        m8058e(i);
    }

    public void m8045a(Drawable drawable) {
        m8050b(drawable);
        m8055c(drawable);
    }

    public void m8046a(boolean z) {
        this.f5282e = z;
        if (this.f5280c) {
            this.f5284g.setVisibility(z ? 0 : 8);
        }
    }

    public void m8047a(boolean z, Activity activity) {
        int i = 0;
        if (f5278i) {
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
    public void m8048b(float f) {
        if (this.f5280c && VERSION.SDK_INT >= 11) {
            this.f5284g.setAlpha(f);
        }
    }

    public void m8049b(int i) {
        m8057d(i);
        m8059f(i);
    }

    public void m8050b(Drawable drawable) {
        if (this.f5280c) {
            this.f5284g.setBackgroundDrawable(drawable);
        }
    }

    public void m8051b(boolean z) {
        this.f5283f = z;
        if (this.f5281d) {
            this.f5285h.setVisibility(z ? 0 : 8);
        }
    }

    public boolean m8052b() {
        return this.f5282e;
    }

    @TargetApi(11)
    public void m8053c(float f) {
        if (this.f5281d && VERSION.SDK_INT >= 11) {
            this.f5285h.setAlpha(f);
        }
    }

    public void m8054c(int i) {
        if (this.f5280c) {
            this.f5284g.setBackgroundColor(i);
        }
    }

    public void m8055c(Drawable drawable) {
        if (this.f5281d) {
            this.f5285h.setBackgroundDrawable(drawable);
        }
    }

    public boolean m8056c() {
        return this.f5283f;
    }

    public void m8057d(int i) {
        if (this.f5280c) {
            this.f5284g.setBackgroundResource(i);
        }
    }

    public void m8058e(int i) {
        if (this.f5281d) {
            this.f5285h.setBackgroundColor(i);
        }
    }

    public void m8059f(int i) {
        if (this.f5281d) {
            this.f5285h.setBackgroundResource(i);
        }
    }
}
