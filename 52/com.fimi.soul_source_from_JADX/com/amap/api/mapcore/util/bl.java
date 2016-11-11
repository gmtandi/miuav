package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import java.security.MessageDigest;
import java.util.Locale;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class bl {
    private static String f2209a;
    private static String f2210b;
    private static String f2211c;
    private static String f2212d;
    private static String f2213e;

    static {
        f2209a = C2915a.f14760f;
        f2210b = C2915a.f14760f;
        f2211c = C2915a.f14760f;
        f2212d = C2915a.f14760f;
        f2213e = null;
    }

    public static String m3646a(Context context) {
        try {
            return m3653g(context);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return f2212d;
        } catch (Throwable th) {
            th.printStackTrace();
            return f2212d;
        }
    }

    static void m3647a(String str) {
        f2212d = str;
    }

    public static String m3648b(Context context) {
        try {
            if (!C2915a.f14760f.equals(f2209a)) {
                return f2209a;
            }
            PackageManager packageManager = context.getPackageManager();
            f2209a = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
            return f2209a;
        } catch (Throwable e) {
            cb.m3809a(e, "AppInfo", "getApplicationName");
        } catch (Throwable e2) {
            cb.m3809a(e2, "AppInfo", "getApplicationName");
        }
    }

    public static String m3649c(Context context) {
        try {
            if (f2210b != null && !C2915a.f14760f.equals(f2210b)) {
                return f2210b;
            }
            f2210b = context.getApplicationContext().getPackageName();
            return f2210b;
        } catch (Throwable th) {
            cb.m3809a(th, "AppInfo", "getPackageName");
        }
    }

    public static String m3650d(Context context) {
        try {
            if (!C2915a.f14760f.equals(f2211c)) {
                return f2211c;
            }
            f2211c = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            return f2211c;
        } catch (Throwable e) {
            cb.m3809a(e, "AppInfo", "getApplicationVersion");
        } catch (Throwable e2) {
            cb.m3809a(e2, "AppInfo", "getApplicationVersion");
        }
    }

    public static String m3651e(Context context) {
        try {
            if (f2213e != null && !C2915a.f14760f.equals(f2213e)) {
                return f2213e;
            }
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            byte[] digest = MessageDigest.getInstance("SHA1").digest(packageInfo.signatures[0].toByteArray());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String toUpperCase = Integer.toHexString(b & Util.MASK_8BIT).toUpperCase(Locale.US);
                if (toUpperCase.length() == 1) {
                    stringBuffer.append(Constants.VIA_RESULT_SUCCESS);
                }
                stringBuffer.append(toUpperCase);
                stringBuffer.append(":");
            }
            stringBuffer.append(packageInfo.packageName);
            f2213e = stringBuffer.toString();
            return f2213e;
        } catch (Throwable e) {
            cb.m3809a(e, "AppInfo", "getSHA1AndPackage");
            return f2213e;
        } catch (Throwable e2) {
            cb.m3809a(e2, "AppInfo", "getSHA1AndPackage");
            return f2213e;
        } catch (Throwable e22) {
            cb.m3809a(e22, "AppInfo", "getSHA1AndPackage");
            return f2213e;
        }
    }

    public static String m3652f(Context context) {
        try {
            return m3653g(context);
        } catch (Throwable e) {
            cb.m3809a(e, "AppInfo", "getKey");
            return f2212d;
        } catch (Throwable e2) {
            cb.m3809a(e2, "AppInfo", "getKey");
            return f2212d;
        }
    }

    private static String m3653g(Context context) {
        if (f2212d == null || f2212d.equals(C2915a.f14760f)) {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            if (applicationInfo == null) {
                return f2212d;
            }
            f2212d = applicationInfo.metaData.getString("com.amap.api.v2.apikey");
        }
        return f2212d;
    }
}
