package com.p054c.p055a.p063b.p069e;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.p054c.p055a.p063b.p064a.C0903i;
import com.p054c.p055a.p072c.C0958f;
import java.lang.reflect.Field;

/* renamed from: com.c.a.b.e.b */
public class C0927b extends C0926d {
    public C0927b(ImageView imageView) {
        super(imageView);
    }

    public C0927b(ImageView imageView, boolean z) {
        super(imageView, z);
    }

    private static int m7331a(Object obj, String str) {
        try {
            Field declaredField = ImageView.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            int intValue = ((Integer) declaredField.get(obj)).intValue();
            if (intValue > 0 && intValue < Integer.MAX_VALUE) {
                return intValue;
            }
        } catch (Throwable e) {
            C0958f.m7555a(e);
        }
        return 0;
    }

    public int m7332a() {
        int a = super.m7321a();
        if (a <= 0) {
            Object obj = (ImageView) this.c.get();
            if (obj != null) {
                return C0927b.m7331a(obj, "mMaxWidth");
            }
        }
        return a;
    }

    protected void m7333a(Bitmap bitmap, View view) {
        ((ImageView) view).setImageBitmap(bitmap);
    }

    protected void m7334a(Drawable drawable, View view) {
        ((ImageView) view).setImageDrawable(drawable);
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).start();
        }
    }

    public int m7335b() {
        int b = super.m7326b();
        if (b <= 0) {
            Object obj = (ImageView) this.c.get();
            if (obj != null) {
                return C0927b.m7331a(obj, "mMaxHeight");
            }
        }
        return b;
    }

    public C0903i m7336c() {
        ImageView imageView = (ImageView) this.c.get();
        return imageView != null ? C0903i.m7210a(imageView) : super.m7327c();
    }

    public /* synthetic */ View m7337d() {
        return m7338g();
    }

    public ImageView m7338g() {
        return (ImageView) super.m7328d();
    }
}
