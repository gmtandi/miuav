package com.p054c.p055a.p063b.p066b;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import com.p054c.p055a.p063b.C0924d;
import com.p054c.p055a.p063b.p064a.C0899e;
import com.p054c.p055a.p063b.p064a.C0900f;
import com.p054c.p055a.p063b.p064a.C0903i;
import com.p054c.p055a.p063b.p068d.C0920c;

/* renamed from: com.c.a.b.b.e */
public class C0910e {
    private final String f4794a;
    private final String f4795b;
    private final String f4796c;
    private final C0900f f4797d;
    private final C0899e f4798e;
    private final C0903i f4799f;
    private final C0920c f4800g;
    private final Object f4801h;
    private final boolean f4802i;
    private final Options f4803j;

    public C0910e(String str, String str2, String str3, C0900f c0900f, C0903i c0903i, C0920c c0920c, C0924d c0924d) {
        this.f4794a = str;
        this.f4795b = str2;
        this.f4796c = str3;
        this.f4797d = c0900f;
        this.f4798e = c0924d.m7303j();
        this.f4799f = c0903i;
        this.f4800g = c0920c;
        this.f4801h = c0924d.m7307n();
        this.f4802i = c0924d.m7306m();
        this.f4803j = new Options();
        m7233a(c0924d.m7304k(), this.f4803j);
    }

    private void m7233a(Options options, Options options2) {
        options2.inDensity = options.inDensity;
        options2.inDither = options.inDither;
        options2.inInputShareable = options.inInputShareable;
        options2.inJustDecodeBounds = options.inJustDecodeBounds;
        options2.inPreferredConfig = options.inPreferredConfig;
        options2.inPurgeable = options.inPurgeable;
        options2.inSampleSize = options.inSampleSize;
        options2.inScaled = options.inScaled;
        options2.inScreenDensity = options.inScreenDensity;
        options2.inTargetDensity = options.inTargetDensity;
        options2.inTempStorage = options.inTempStorage;
        if (VERSION.SDK_INT >= 10) {
            m7234b(options, options2);
        }
        if (VERSION.SDK_INT >= 11) {
            m7235c(options, options2);
        }
    }

    @TargetApi(10)
    private void m7234b(Options options, Options options2) {
        options2.inPreferQualityOverSpeed = options.inPreferQualityOverSpeed;
    }

    @TargetApi(11)
    private void m7235c(Options options, Options options2) {
        options2.inBitmap = options.inBitmap;
        options2.inMutable = options.inMutable;
    }

    public String m7236a() {
        return this.f4794a;
    }

    public String m7237b() {
        return this.f4795b;
    }

    public String m7238c() {
        return this.f4796c;
    }

    public C0900f m7239d() {
        return this.f4797d;
    }

    public C0899e m7240e() {
        return this.f4798e;
    }

    public C0903i m7241f() {
        return this.f4799f;
    }

    public C0920c m7242g() {
        return this.f4800g;
    }

    public Object m7243h() {
        return this.f4801h;
    }

    public boolean m7244i() {
        return this.f4802i;
    }

    public Options m7245j() {
        return this.f4803j;
    }
}
