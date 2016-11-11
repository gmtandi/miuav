package com.tencent.open.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.File;
import org.p122a.p123a.C2915a;

public final class Global {
    private static Context f12085a;

    public static final Context getContext() {
        return f12085a == null ? null : f12085a;
    }

    public static final File getFilesDir() {
        return getContext() == null ? null : getContext().getFilesDir();
    }

    public static final String getPackageName() {
        return getContext() == null ? C2915a.f14760f : getContext().getPackageName();
    }

    public static final SharedPreferences getSharedPreferences(String str, int i) {
        return getContext() == null ? null : getContext().getSharedPreferences(str, i);
    }

    public static int getVersionCode() {
        return f12085a.getSharedPreferences("openSdk.pref", 0).getInt("app.vercode", 0);
    }

    public static void saveVersionCode() {
        Context context = getContext();
        if (context != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                if (packageInfo != null) {
                    Editor edit = context.getSharedPreferences("openSdk.pref", 0).edit();
                    edit.putInt("app.vercode", packageInfo.versionCode);
                    edit.commit();
                }
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static final void setContext(Context context) {
        f12085a = context;
    }
}
