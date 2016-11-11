package com.android.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import com.android.volley.C0563k;
import com.android.volley.C0600v;
import java.io.File;

public class aj {
    private static final String f3599a = "volley";

    public static C0600v m5174a(Context context) {
        return m5176a(context, null);
    }

    public static C0600v m5175a(Context context, int i) {
        return m5177a(context, null, i);
    }

    public static C0600v m5176a(Context context, C0584n c0584n) {
        return m5177a(context, c0584n, -1);
    }

    public static C0600v m5177a(Context context, C0584n c0584n, int i) {
        File file = new File(context.getCacheDir(), f3599a);
        String str = "volley/0";
        try {
            String packageName = context.getPackageName();
            str = packageName + "/" + context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (NameNotFoundException e) {
        }
        if (c0584n == null) {
            c0584n = VERSION.SDK_INT >= 9 ? new C0588o() : new C0585k(AndroidHttpClient.newInstance(str));
        }
        C0563k c0576c = new C0576c(c0584n);
        C0600v c0600v = i <= -1 ? new C0600v(new C0580g(file), c0576c) : new C0600v(new C0580g(file, i), c0576c);
        c0600v.m5280a();
        return c0600v;
    }
}
