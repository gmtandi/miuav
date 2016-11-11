package com.mob.commons.logcollector;

import android.content.Context;
import com.mob.tools.utils.SharePrefrenceHelper;

/* renamed from: com.mob.commons.logcollector.d */
public class C2156d {
    private static C2156d f11343a;
    private SharePrefrenceHelper f11344b;

    private C2156d(Context context) {
        this.f11344b = new SharePrefrenceHelper(context.getApplicationContext());
        this.f11344b.open("mob_sdk_exception", 1);
    }

    public static C2156d m13198a(Context context) {
        if (f11343a == null) {
            f11343a = new C2156d(context);
        }
        return f11343a;
    }

    public long m13199a() {
        return this.f11344b.getLong("service_time");
    }

    public void m13200a(long j) {
        this.f11344b.putLong("service_time", Long.valueOf(j));
    }

    public void m13201a(String str) {
        this.f11344b.putString("err_log_filter", str);
    }

    public void m13202a(boolean z) {
        this.f11344b.putInt("is_upload_err_log", Integer.valueOf(z ? 0 : 1));
    }

    public void m13203b(boolean z) {
        this.f11344b.putBoolean("is_upload_crash", Boolean.valueOf(z));
    }

    public boolean m13204b() {
        return this.f11344b.getInt("is_upload_err_log") == 0;
    }

    public void m13205c(boolean z) {
        this.f11344b.putBoolean("is_upload_sdkerr", Boolean.valueOf(z));
    }

    public boolean m13206c() {
        return this.f11344b.getBoolean("is_upload_crash");
    }

    public void m13207d(boolean z) {
        this.f11344b.putBoolean("is_upload_apperr", Boolean.valueOf(z));
    }

    public boolean m13208d() {
        return this.f11344b.getBoolean("is_upload_sdkerr");
    }

    public boolean m13209e() {
        return this.f11344b.getBoolean("is_upload_apperr");
    }

    public String m13210f() {
        return this.f11344b.getString("err_log_filter");
    }
}
