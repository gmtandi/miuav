package com.fimi.soul.biz.p090b;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.fimi.soul.drone.p116g.C1543c;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.fimi.soul.biz.b.k */
public class C1253k {
    public static final int f5683a = 0;
    public static final int f5684b = 1;
    public static final int f5685c = 2;
    public static final int f5686d = 3;
    public static final int f5687e = 4;
    private static C1253k f5688g;
    private AtomicInteger f5689f;
    private SharedPreferences f5690h;

    public C1253k(Context context) {
        this.f5689f = new AtomicInteger(f5683a);
        this.f5690h = PreferenceManager.getDefaultSharedPreferences(context);
        m8600a(this.f5690h.getInt(C1543c.bo, f5683a));
    }

    public static C1253k m8598a(Context context) {
        if (f5688g == null) {
            f5688g = new C1253k(context);
        }
        return f5688g;
    }

    public AtomicInteger m8599a() {
        return this.f5689f;
    }

    public void m8600a(int i) {
        this.f5689f.set(i);
        this.f5690h.edit().putInt(C1543c.bo, i).commit();
    }

    public boolean m8601b() {
        return f5684b == this.f5689f.get() || f5685c == this.f5689f.get() || f5686d == this.f5689f.get();
    }
}
