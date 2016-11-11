package com.tencent.open.p134b;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.utils.Global;
import java.util.Locale;
import org.p122a.p123a.C2915a;

/* renamed from: com.tencent.open.b.c */
public class C2340c {
    static String f12025a;
    static String f12026b;
    static String f12027c;
    private static String f12028d;
    private static String f12029e;

    static {
        f12025a = null;
        f12026b = null;
        f12027c = null;
        f12029e = null;
    }

    public static String m13777a() {
        try {
            Context context = Global.getContext();
            if (context == null) {
                return C2915a.f14760f;
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null) {
                return C2915a.f14760f;
            }
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            return connectionInfo == null ? C2915a.f14760f : connectionInfo.getMacAddress();
        } catch (Throwable e) {
            C2333f.m13755b("MobileInfoUtil", "getLocalMacAddress>>>", e);
            return C2915a.f14760f;
        }
    }

    public static String m13778a(Context context) {
        if (!TextUtils.isEmpty(f12028d)) {
            return f12028d;
        }
        if (context == null) {
            return C2915a.f14760f;
        }
        f12028d = C2915a.f14760f;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            int width = windowManager.getDefaultDisplay().getWidth();
            f12028d = width + "x" + windowManager.getDefaultDisplay().getHeight();
        }
        return f12028d;
    }

    public static String m13779b() {
        return Locale.getDefault().getLanguage();
    }

    public static String m13780b(Context context) {
        if (f12025a != null && f12025a.length() > 0) {
            return f12025a;
        }
        if (context == null) {
            return C2915a.f14760f;
        }
        try {
            f12025a = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            return f12025a;
        } catch (Exception e) {
            return C2915a.f14760f;
        }
    }

    public static String m13781c(Context context) {
        if (f12026b != null && f12026b.length() > 0) {
            return f12026b;
        }
        if (context == null) {
            return C2915a.f14760f;
        }
        try {
            f12026b = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            return f12026b;
        } catch (Exception e) {
            return C2915a.f14760f;
        }
    }

    public static String m13782d(Context context) {
        if (f12027c != null && f12027c.length() > 0) {
            return f12027c;
        }
        if (context == null) {
            return C2915a.f14760f;
        }
        try {
            f12027c = Secure.getString(context.getContentResolver(), "android_id");
            return f12027c;
        } catch (Exception e) {
            return C2915a.f14760f;
        }
    }

    public static String m13783e(Context context) {
        try {
            if (f12029e == null) {
                WindowManager windowManager = (WindowManager) context.getSystemService("window");
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("imei=").append(C2340c.m13780b(context)).append('&');
                stringBuilder.append("model=").append(Build.MODEL).append('&');
                stringBuilder.append("os=").append(VERSION.RELEASE).append('&');
                stringBuilder.append("apilevel=").append(VERSION.SDK_INT).append('&');
                String b = C2338a.m13773b(context);
                if (b == null) {
                    b = C2915a.f14760f;
                }
                stringBuilder.append("network=").append(b).append('&');
                stringBuilder.append("sdcard=").append(Environment.getExternalStorageState().equals("mounted") ? 1 : 0).append('&');
                stringBuilder.append("display=").append(displayMetrics.widthPixels).append('*').append(displayMetrics.heightPixels).append('&');
                stringBuilder.append("manu=").append(Build.MANUFACTURER).append("&");
                stringBuilder.append("wifi=").append(C2338a.m13776e(context));
                f12029e = stringBuilder.toString();
            }
            return f12029e;
        } catch (Exception e) {
            return null;
        }
    }
}
