package com.xiaomi.push.service;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.channel.commonutils.misc.C2464a;
import com.xiaomi.channel.commonutils.string.C2476d;
import com.xiaomi.market.sdk.C2537j;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class ac {
    private static ac f13104a;
    private static String f13105d;
    private Context f13106b;
    private boolean f13107c;
    private int f13108e;

    static {
        f13105d = C2476d.m14165a(6);
    }

    private ac(Context context) {
        this.f13107c = false;
        this.f13108e = 0;
        this.f13106b = context.getApplicationContext();
        if (m14976d()) {
            C2463b.m14126b("use miui push service");
            this.f13107c = true;
        }
    }

    public static ac m14975a(Context context) {
        if (f13104a == null) {
            f13104a = new ac(context);
        }
        return f13104a;
    }

    private boolean m14976d() {
        if (C2464a.f12424e) {
            return false;
        }
        try {
            PackageInfo packageInfo = this.f13106b.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
            return packageInfo != null && packageInfo.versionCode >= Opcodes.IMUL;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean m14977a() {
        return C2537j.ap.contains("xmsf") || C2537j.ap.contains("xiaomi") || C2537j.ap.contains("miui");
    }

    public int m14978b() {
        if (this.f13108e != 0) {
            return this.f13108e;
        }
        if (VERSION.SDK_INT >= 17) {
            this.f13108e = Global.getInt(this.f13106b.getContentResolver(), "device_provisioned", 0);
            return this.f13108e;
        }
        this.f13108e = Secure.getInt(this.f13106b.getContentResolver(), "device_provisioned", 0);
        return this.f13108e;
    }

    public Uri m14979c() {
        return VERSION.SDK_INT >= 17 ? Global.getUriFor("device_provisioned") : Secure.getUriFor("device_provisioned");
    }
}
