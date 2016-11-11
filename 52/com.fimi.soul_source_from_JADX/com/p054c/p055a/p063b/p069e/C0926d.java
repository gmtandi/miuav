package com.p054c.p055a.p063b.p069e;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.p054c.p055a.p063b.p064a.C0903i;
import com.p054c.p055a.p072c.C0958f;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* renamed from: com.c.a.b.e.d */
public abstract class C0926d implements C0925a {
    public static final String f4872a = "Can't set a drawable into view. You should call ImageLoader on UI thread for it.";
    public static final String f4873b = "Can't set a bitmap into view. You should call ImageLoader on UI thread for it.";
    protected Reference<View> f4874c;
    protected boolean f4875d;

    public C0926d(View view) {
        this(view, true);
    }

    public C0926d(View view, boolean z) {
        if (view == null) {
            throw new IllegalArgumentException("view must not be null");
        }
        this.f4874c = new WeakReference(view);
        this.f4875d = z;
    }

    public int m7321a() {
        View view = (View) this.f4874c.get();
        if (view == null) {
            return 0;
        }
        LayoutParams layoutParams = view.getLayoutParams();
        int width = (!this.f4875d || layoutParams == null || layoutParams.width == -2) ? 0 : view.getWidth();
        return (width > 0 || layoutParams == null) ? width : layoutParams.width;
    }

    protected abstract void m7322a(Bitmap bitmap, View view);

    protected abstract void m7323a(Drawable drawable, View view);

    public boolean m7324a(Bitmap bitmap) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            View view = (View) this.f4874c.get();
            if (view != null) {
                m7322a(bitmap, view);
                return true;
            }
        }
        C0958f.m7561c(f4873b, new Object[0]);
        return false;
    }

    public boolean m7325a(Drawable drawable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            View view = (View) this.f4874c.get();
            if (view != null) {
                m7323a(drawable, view);
                return true;
            }
        }
        C0958f.m7561c(f4872a, new Object[0]);
        return false;
    }

    public int m7326b() {
        View view = (View) this.f4874c.get();
        if (view == null) {
            return 0;
        }
        LayoutParams layoutParams = view.getLayoutParams();
        int height = (!this.f4875d || layoutParams == null || layoutParams.height == -2) ? 0 : view.getHeight();
        return (height > 0 || layoutParams == null) ? height : layoutParams.height;
    }

    public C0903i m7327c() {
        return C0903i.CROP;
    }

    public View m7328d() {
        return (View) this.f4874c.get();
    }

    public boolean m7329e() {
        return this.f4874c.get() == null;
    }

    public int m7330f() {
        View view = (View) this.f4874c.get();
        return view == null ? super.hashCode() : view.hashCode();
    }
}
