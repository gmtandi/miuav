package com.p054c.p055a.p063b.p069e;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import com.p054c.p055a.p063b.p064a.C0900f;
import com.p054c.p055a.p063b.p064a.C0903i;

/* renamed from: com.c.a.b.e.c */
public class C0928c implements C0925a {
    protected final String f4876a;
    protected final C0900f f4877b;
    protected final C0903i f4878c;

    public C0928c(C0900f c0900f, C0903i c0903i) {
        this(null, c0900f, c0903i);
    }

    public C0928c(String str, C0900f c0900f, C0903i c0903i) {
        if (c0900f == null) {
            throw new IllegalArgumentException("imageSize must not be null");
        } else if (c0903i == null) {
            throw new IllegalArgumentException("scaleType must not be null");
        } else {
            this.f4876a = str;
            this.f4877b = c0900f;
            this.f4878c = c0903i;
        }
    }

    public int m7339a() {
        return this.f4877b.m7206a();
    }

    public boolean m7340a(Bitmap bitmap) {
        return true;
    }

    public boolean m7341a(Drawable drawable) {
        return true;
    }

    public int m7342b() {
        return this.f4877b.m7209b();
    }

    public C0903i m7343c() {
        return this.f4878c;
    }

    public View m7344d() {
        return null;
    }

    public boolean m7345e() {
        return false;
    }

    public int m7346f() {
        return TextUtils.isEmpty(this.f4876a) ? super.hashCode() : this.f4876a.hashCode();
    }
}
