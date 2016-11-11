package com.xiaomi.mistatistic.sdk.controller;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.fimi.kernel.p076b.p080d.C1142e;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.g */
public class C2593g {
    private static String f12963a;

    public static String m14736a(Context context) {
        String str = null;
        if (null != null) {
            return null;
        }
        String string;
        String b = C2593g.m14739b(context);
        try {
            string = Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable th) {
            string = null;
        }
        if (VERSION.SDK_INT > 8) {
            str = Build.SERIAL;
        }
        return C2593g.m14740b(b + string + str);
    }

    public static String m14739b(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String deviceId = telephonyManager.getDeviceId();
            int i = 10;
            while (deviceId == null) {
                int i2 = i - 1;
                if (i <= 0) {
                    break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                deviceId = telephonyManager.getDeviceId();
                i = i2;
            }
            return deviceId;
        } catch (Throwable th) {
            return null;
        }
    }

    private static String m14740b(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            instance.update(C2593g.m14741c(str));
            BigInteger bigInteger = new BigInteger(1, instance.digest());
            return String.format("%1$032X", new Object[]{bigInteger});
        } catch (NoSuchAlgorithmException e) {
            return str;
        }
    }

    private static byte[] m14741c(String str) {
        try {
            return str.getBytes(C1142e.f5201a);
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }

    public String m14742a() {
        if (f12963a != null) {
            return f12963a;
        }
        C2589b.m14731a().m14734a(new C2594h(C2588a.m14708a()));
        return null;
    }
}
