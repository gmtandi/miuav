package com.p017b.p020c;

import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.Interpolator;
import com.p017b.p018a.C0620b;
import java.util.WeakHashMap;

/* renamed from: com.b.c.c */
public abstract class C0650c {
    private static final WeakHashMap<View, C0650c> f3945a;

    static {
        f3945a = new WeakHashMap(0);
    }

    public static C0650c m5801a(View view) {
        C0650c c0650c = (C0650c) f3945a.get(view);
        if (c0650c == null) {
            int intValue = Integer.valueOf(VERSION.SDK).intValue();
            c0650c = intValue >= 14 ? new C0656i(view) : intValue >= 11 ? new C0651d(view) : new C0658k(view);
            f3945a.put(view, c0650c);
        }
        return c0650c;
    }

    public abstract long m5802a();

    public abstract C0650c m5803a(float f);

    public abstract C0650c m5804a(long j);

    public abstract C0650c m5805a(Interpolator interpolator);

    public abstract C0650c m5806a(C0620b c0620b);

    public abstract long m5807b();

    public abstract C0650c m5808b(float f);

    public abstract C0650c m5809b(long j);

    public abstract C0650c m5810c(float f);

    public abstract void m5811c();

    public abstract C0650c m5812d(float f);

    public abstract void m5813d();

    public abstract C0650c m5814e(float f);

    public abstract C0650c m5815f(float f);

    public abstract C0650c m5816g(float f);

    public abstract C0650c m5817h(float f);

    public abstract C0650c m5818i(float f);

    public abstract C0650c m5819j(float f);

    public abstract C0650c m5820k(float f);

    public abstract C0650c m5821l(float f);

    public abstract C0650c m5822m(float f);

    public abstract C0650c m5823n(float f);

    public abstract C0650c m5824o(float f);

    public abstract C0650c m5825p(float f);

    public abstract C0650c m5826q(float f);

    public abstract C0650c m5827r(float f);

    public abstract C0650c m5828s(float f);

    public abstract C0650c m5829t(float f);
}
