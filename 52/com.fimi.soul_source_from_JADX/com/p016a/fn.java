package com.p016a;

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

/* renamed from: com.a.fn */
public class fn {
    private static String f1212a;
    private static String f1213b;
    private static String f1214c;
    private static String f1215d;
    private static String f1216e;

    static {
        f1212a = C2915a.f14760f;
        f1213b = C2915a.f14760f;
        f1214c = C2915a.f14760f;
        f1215d = C2915a.f14760f;
        f1216e = null;
    }

    public static String m1834a(Context context) {
        try {
            return fn.m1843g(context);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return f1215d;
        } catch (Throwable th) {
            th.printStackTrace();
            return f1215d;
        }
    }

    public static void m1835a(String str) {
        f1213b = str;
    }

    public static String m1836b(Context context) {
        try {
            if (!C2915a.f14760f.equals(f1212a)) {
                return f1212a;
            }
            PackageManager packageManager = context.getPackageManager();
            f1212a = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
            return f1212a;
        } catch (Throwable e) {
            C0247g.m1917a(e, "AppInfo", "getApplicationName");
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "AppInfo", "getApplicationName");
        }
    }

    public static void m1837b(String str) {
        f1216e = str;
    }

    public static String m1838c(Context context) {
        try {
            if (f1213b != null && !C2915a.f14760f.equals(f1213b)) {
                return f1213b;
            }
            f1213b = context.getApplicationContext().getPackageName();
            return f1213b;
        } catch (Throwable th) {
            C0247g.m1917a(th, "AppInfo", "getPackageName");
        }
    }

    static void m1839c(String str) {
        f1215d = str;
    }

    public static String m1840d(Context context) {
        try {
            if (!C2915a.f14760f.equals(f1214c)) {
                return f1214c;
            }
            f1214c = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            return f1214c;
        } catch (Throwable e) {
            C0247g.m1917a(e, "AppInfo", "getApplicationVersion");
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "AppInfo", "getApplicationVersion");
        }
    }

    public static String m1841e(Context context) {
        try {
            if (f1216e != null && !C2915a.f14760f.equals(f1216e)) {
                return f1216e;
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
            f1216e = stringBuffer.toString();
            return f1216e;
        } catch (Throwable e) {
            C0247g.m1917a(e, "AppInfo", "getSHA1AndPackage");
            return f1216e;
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "AppInfo", "getSHA1AndPackage");
            return f1216e;
        } catch (Throwable e22) {
            C0247g.m1917a(e22, "AppInfo", "getSHA1AndPackage");
            return f1216e;
        }
    }

    public static String m1842f(Context context) {
        try {
            return fn.m1843g(context);
        } catch (Throwable e) {
            C0247g.m1917a(e, "AppInfo", "getKey");
            return f1215d;
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "AppInfo", "getKey");
            return f1215d;
        }
    }

    private static String m1843g(Context context) {
        if (f1215d == null || f1215d.equals(C2915a.f14760f)) {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            if (applicationInfo == null) {
                return f1215d;
            }
            f1215d = applicationInfo.metaData.getString("com.amap.api.v2.apikey");
        }
        return f1215d;
    }
}
