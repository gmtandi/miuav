package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.android.volley.C0570r;
import com.android.volley.C0600v;
import com.android.volley.ag;
import java.util.HashMap;

/* renamed from: com.android.volley.toolbox.q */
public class C0590q {
    private final C0600v f3634a;
    private int f3635b;
    private final C0596w f3636c;
    private final HashMap<String, C0595v> f3637d;
    private final HashMap<String, C0595v> f3638e;
    private final Handler f3639f;
    private Runnable f3640g;

    public C0590q(C0600v c0600v, C0596w c0596w) {
        this.f3635b = 100;
        this.f3637d = new HashMap();
        this.f3638e = new HashMap();
        this.f3639f = new Handler(Looper.getMainLooper());
        this.f3634a = c0600v;
        this.f3636c = c0596w;
    }

    public static C0575y m5237a(ImageView imageView, int i, int i2) {
        return new C0591r(i2, imageView, i);
    }

    private void m5240a() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
        }
    }

    private void m5241a(String str, C0595v c0595v) {
        this.f3638e.put(str, c0595v);
        if (this.f3640g == null) {
            this.f3640g = new C0594u(this);
            this.f3639f.postDelayed(this.f3640g, (long) this.f3635b);
        }
    }

    private static String m5242b(String str, int i, int i2, ScaleType scaleType) {
        return new StringBuilder(str.length() + 12).append("#W").append(i).append("#H").append(i2).append("#S").append(scaleType.ordinal()).append(str).toString();
    }

    protected C0570r<Bitmap> m5244a(String str, int i, int i2, ScaleType scaleType, String str2) {
        return new C0598z(str, new C0592s(this, str2), i, i2, scaleType, Config.RGB_565, new C0593t(this, str2));
    }

    public C0597x m5245a(String str, C0575y c0575y) {
        return m5246a(str, c0575y, 0, 0);
    }

    public C0597x m5246a(String str, C0575y c0575y, int i, int i2) {
        return m5247a(str, c0575y, i, i2, ScaleType.CENTER_INSIDE);
    }

    public C0597x m5247a(String str, C0575y c0575y, int i, int i2, ScaleType scaleType) {
        m5240a();
        String b = C0590q.m5242b(str, i, i2, scaleType);
        Bitmap a = this.f3636c.m5265a(b);
        if (a != null) {
            C0597x c0597x = new C0597x(this, a, str, null, null);
            c0575y.m5155a(c0597x, true);
            return c0597x;
        }
        c0597x = new C0597x(this, null, str, b, c0575y);
        c0575y.m5155a(c0597x, true);
        C0595v c0595v = (C0595v) this.f3637d.get(b);
        if (c0595v != null) {
            c0595v.m5263a(c0597x);
            return c0597x;
        }
        C0570r a2 = m5244a(str, i, i2, scaleType, b);
        this.f3634a.m5279a(a2);
        this.f3637d.put(b, new C0595v(this, a2, c0597x));
        return c0597x;
    }

    public void m5248a(int i) {
        this.f3635b = i;
    }

    protected void m5249a(String str, Bitmap bitmap) {
        this.f3636c.m5266a(str, bitmap);
        C0595v c0595v = (C0595v) this.f3637d.remove(str);
        if (c0595v != null) {
            c0595v.f3651c = bitmap;
            m5241a(str, c0595v);
        }
    }

    protected void m5250a(String str, ag agVar) {
        C0595v c0595v = (C0595v) this.f3637d.remove(str);
        if (c0595v != null) {
            c0595v.m5262a(agVar);
            m5241a(str, c0595v);
        }
    }

    public boolean m5251a(String str, int i, int i2) {
        return m5252a(str, i, i2, ScaleType.CENTER_INSIDE);
    }

    public boolean m5252a(String str, int i, int i2, ScaleType scaleType) {
        m5240a();
        return this.f3636c.m5265a(C0590q.m5242b(str, i, i2, scaleType)) != null;
    }
}
