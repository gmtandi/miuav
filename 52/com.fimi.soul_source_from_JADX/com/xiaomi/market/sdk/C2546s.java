package com.xiaomi.market.sdk;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import com.amap.api.maps.model.WeightedLatLng;
import java.io.File;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.market.sdk.s */
public class C2546s {
    static boolean DEBUG = false;
    static final String TAG = "MarketSdkUtils";
    static boolean bb = false;
    protected static final int bc = 1000000;
    protected static final int bd = 1000;

    static {
        boolean z = false;
        DEBUG = new File("/sdcard/com.xiaomi.market.sdk/sdk_debug").exists();
        if (new File("/sdcard/com.xiaomi.market.sdk/sdk_staging").exists()) {
            z = true;
        }
        bb = z;
    }

    public static String m14547a(long j, Context context) {
        if (j < 0) {
            return C2915a.f14760f;
        }
        String format;
        int b;
        if (j > 1000000) {
            format = String.format("%.1f", new Object[]{Double.valueOf((((double) j) * WeightedLatLng.DEFAULT_INTENSITY) / 1000000.0d)});
            b = C2546s.m14548b(context.getPackageName(), "string", "xiaomi_megabytes_unit");
        } else if (j > 1000) {
            format = String.format("%.1f", new Object[]{Double.valueOf((((double) j) * WeightedLatLng.DEFAULT_INTENSITY) / 1000.0d)});
            b = C2546s.m14548b(context.getPackageName(), "string", "xiaomi_kilobytes_unit");
        } else {
            format = String.valueOf(j);
            b = C2546s.m14548b(context.getPackageName(), "string", "xiaomi_bytes_unit");
        }
        return context.getString(b, new Object[]{format});
    }

    static int m14548b(String str, String str2, String str3) {
        int i = 0;
        try {
            Class[] classes = Class.forName(new StringBuilder(String.valueOf(str)).append(".R").toString()).getClasses();
            Class cls = null;
            for (int i2 = 0; i2 < classes.length; i2++) {
                if (classes[i2].getName().split("\\$")[1].equals(str2)) {
                    cls = classes[i2];
                    break;
                }
            }
            if (cls == null) {
                if (DEBUG) {
                    Log.d(TAG, "[get resource id] : return id from R$class");
                }
                cls = Class.forName(new StringBuilder(String.valueOf(str)).append(".R$").append(str2).toString());
            }
            if (cls != null) {
                i = cls.getField(str3).getInt(cls);
            }
        } catch (Exception e) {
            Log.e(TAG, "[get resource id] : id = " + 0 + "\nerror: " + e.toString());
        }
        if (DEBUG) {
            Log.d(TAG, "[get resource id] : id = " + i);
        }
        return i;
    }

    static boolean m14549f(boolean z) {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) ? true : "mounted_ro".equals(externalStorageState) && !z;
    }

    static boolean m14550k(Context context) {
        try {
            return (context.getPackageManager().getPackageInfo("com.miui.cloudservice", 0).applicationInfo.flags & 1) != 0;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    static boolean m14551l(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.xiaomi.market", 1);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    static boolean m14552m(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    static boolean m14553n(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }

    static boolean m14554o(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
    }
}
