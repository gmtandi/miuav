package com.mi.live.openlivesdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.util.List;

/* renamed from: com.mi.live.openlivesdk.g */
public class C2121g {
    public static final String f11141a = "com.wali.live";
    public static final int f11142b = 101020;
    public static final int f11143c = 1;
    public static final int f11144d = -2;
    public static final int f11145e = -3;

    public static int m13056a(Context context) {
        List installedPackages = context.getPackageManager().getInstalledPackages(0);
        if (installedPackages != null) {
            int i = 0;
            while (i < installedPackages.size()) {
                PackageInfo packageInfo = (PackageInfo) installedPackages.get(i);
                if (f11141a.equals(packageInfo.packageName)) {
                    return packageInfo.versionCode >= f11142b ? f11143c : f11145e;
                } else {
                    i += f11143c;
                }
            }
        }
        return f11144d;
    }
}
