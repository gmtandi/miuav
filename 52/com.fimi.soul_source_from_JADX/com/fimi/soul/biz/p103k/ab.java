package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.fimi.soul.utils.ay;

/* renamed from: com.fimi.soul.biz.k.ab */
public class ab {
    private static final String f6019a = "screenlight";
    private static final String f6020b = "soundmax";
    private static final String f6021c = "autoupdate";
    private static final String f6022d = "push_control";
    private static final String f6023e = "open_position";
    private static final String f6024f = "mapchangge";
    private static ab f6025g;
    private SharedPreferences f6026h;
    private Editor f6027i;

    static {
        f6025g = null;
    }

    public ab(Context context) {
        this.f6026h = ay.m12293a(context);
        this.f6027i = this.f6026h.edit();
    }

    public static ab m9180a(Context context) {
        if (f6025g == null) {
            f6025g = new ab(context);
        }
        return f6025g;
    }

    public void m9181a(boolean z) {
        this.f6027i.putBoolean(f6022d, z);
        this.f6027i.commit();
    }

    public boolean m9182a() {
        return this.f6026h.getBoolean(f6019a, false);
    }

    public void m9183b(boolean z) {
        this.f6027i.putBoolean(f6023e, z);
        this.f6027i.commit();
    }

    public boolean m9184b() {
        return this.f6026h.getBoolean(f6020b, false);
    }

    public void m9185c(boolean z) {
        this.f6027i.putBoolean(f6021c, z);
        this.f6027i.commit();
    }

    public boolean m9186c() {
        return this.f6026h.getBoolean(f6021c, false);
    }

    public void m9187d(boolean z) {
        this.f6027i.putBoolean(f6020b, z);
        this.f6027i.commit();
    }

    public boolean m9188d() {
        return this.f6026h.getBoolean(f6023e, true);
    }

    public void m9189e(boolean z) {
        this.f6027i.putBoolean(f6019a, z);
        this.f6027i.commit();
    }

    public boolean m9190e() {
        return this.f6026h.getBoolean(f6024f, false);
    }

    public void m9191f(boolean z) {
        this.f6027i.putBoolean(f6024f, z);
        this.f6027i.commit();
    }

    public boolean m9192f() {
        return this.f6026h.getBoolean(f6022d, true);
    }
}
