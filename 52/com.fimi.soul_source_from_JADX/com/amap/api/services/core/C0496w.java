package com.amap.api.services.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import java.security.MessageDigest;
import java.util.Locale;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.amap.api.services.core.w */
public class C0496w {
    private static String f3171a;
    private static String f3172b;
    private static String f3173c;
    private static String f3174d;
    private static String f3175e;

    static {
        f3171a = C2915a.f14760f;
        f3172b = null;
        f3173c = C2915a.f14760f;
        f3175e = null;
    }

    public static String m4868a(Context context) {
        try {
            if (!C2915a.f14760f.equals(f3171a)) {
                return f3171a;
            }
            PackageManager packageManager = context.getPackageManager();
            f3171a = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
            return f3171a;
        } catch (Throwable e) {
            ay.m4590a(e, "AppInfo", "getApplicationName");
            e.printStackTrace();
        } catch (Throwable e2) {
            ay.m4590a(e2, "AppInfo", "getApplicationName");
            e2.printStackTrace();
        }
    }

    static void m4869a(String str) {
        f3174d = str;
    }

    public static String m4870b(Context context) {
        try {
            if (f3172b != null && !C2915a.f14760f.equals(f3172b)) {
                return f3172b;
            }
            f3172b = context.getApplicationContext().getPackageName();
            return f3172b;
        } catch (Throwable th) {
            ay.m4590a(th, "AppInfo", "getPackageName");
            th.printStackTrace();
        }
    }

    public static String m4871c(Context context) {
        try {
            if (!C2915a.f14760f.equals(f3173c)) {
                return f3173c;
            }
            f3173c = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            return f3173c;
        } catch (Throwable e) {
            ay.m4590a(e, "AppInfo", "getApplicationVersion");
            e.printStackTrace();
        } catch (Throwable e2) {
            ay.m4590a(e2, "AppInfo", "getApplicationVersion");
            e2.printStackTrace();
        }
    }

    public static String m4872d(Context context) {
        try {
            if (f3175e != null && !C2915a.f14760f.equals(f3175e)) {
                return f3175e;
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
            f3175e = stringBuffer.toString();
            return f3175e;
        } catch (Throwable e) {
            ay.m4590a(e, "AppInfo", "getSHA1AndPackage");
            e.printStackTrace();
            return f3175e;
        } catch (Throwable e2) {
            ay.m4590a(e2, "AppInfo", "getSHA1AndPackage");
            e2.printStackTrace();
            return f3175e;
        } catch (Throwable e22) {
            ay.m4590a(e22, "AppInfo", "getSHA1AndPackage");
            e22.printStackTrace();
            return f3175e;
        }
    }

    public static String m4873e(Context context) {
        try {
            return C0496w.m4875g(context);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return f3174d;
        } catch (Throwable th) {
            th.printStackTrace();
            return f3174d;
        }
    }

    public static String m4874f(Context context) {
        try {
            return C0496w.m4875g(context);
        } catch (Throwable e) {
            ay.m4590a(e, "AppInfo", "getKey");
            e.printStackTrace();
            return f3174d;
        } catch (Throwable e2) {
            ay.m4590a(e2, "AppInfo", "getKey");
            e2.printStackTrace();
            return f3174d;
        }
    }

    private static String m4875g(Context context) {
        if (f3174d == null || f3174d.equals(C2915a.f14760f)) {
            f3174d = context.getPackageManager().getApplicationInfo(context.getPackageName(), SmileConstants.TOKEN_PREFIX_TINY_UNICODE).metaData.getString("com.amap.api.v2.apikey");
        }
        return f3174d;
    }
}
