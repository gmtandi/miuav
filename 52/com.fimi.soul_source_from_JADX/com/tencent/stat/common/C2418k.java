package com.tencent.stat.common;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.stat.StatConfig;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpHost;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.tencent.stat.common.k */
public class C2418k {
    private static String f12350a;
    private static String f12351b;
    private static String f12352c;
    private static String f12353d;
    private static Random f12354e;
    private static StatLogger f12355f;
    private static String f12356g;
    private static C2419l f12357h;
    private static C2421n f12358i;
    private static String f12359j;
    private static int f12360k;

    static {
        f12350a = null;
        f12351b = null;
        f12352c = null;
        f12353d = null;
        f12354e = null;
        f12355f = null;
        f12356g = null;
        f12357h = null;
        f12358i = null;
        f12359j = "__MTA_FIRST_ACTIVATE__";
        f12360k = -1;
    }

    public static String m14002A(Context context) {
        try {
            SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            if (sensorManager != null) {
                List sensorList = sensorManager.getSensorList(-1);
                if (sensorList != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < sensorList.size(); i++) {
                        stringBuilder.append(((Sensor) sensorList.get(i)).getType());
                        if (i != sensorList.size() - 1) {
                            stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                        }
                    }
                    return stringBuilder.toString();
                }
            }
        } catch (Object th) {
            f12355f.m13978e(th);
        }
        return C2915a.f14760f;
    }

    public static WifiInfo m14003B(Context context) {
        if (C2418k.m14015a(context, "android.permission.ACCESS_WIFI_STATE")) {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            if (wifiManager != null) {
                return wifiManager.getConnectionInfo();
            }
        }
        return null;
    }

    public static String m14004C(Context context) {
        try {
            WifiInfo B = C2418k.m14003B(context);
            if (B != null) {
                return B.getBSSID();
            }
        } catch (Object th) {
            f12355f.m13978e(th);
        }
        return null;
    }

    public static String m14005D(Context context) {
        try {
            WifiInfo B = C2418k.m14003B(context);
            if (B != null) {
                return B.getSSID();
            }
        } catch (Object th) {
            f12355f.m13978e(th);
        }
        return null;
    }

    public static synchronized int m14006E(Context context) {
        int i;
        synchronized (C2418k.class) {
            if (f12360k != -1) {
                i = f12360k;
            } else {
                C2418k.m14007F(context);
                i = f12360k;
            }
        }
        return i;
    }

    public static void m14007F(Context context) {
        f12360k = C2423p.m14059a(context, f12359j, 1);
        f12355f.m13978e(Integer.valueOf(f12360k));
        if (f12360k == 1) {
            C2423p.m14063b(context, f12359j, 0);
        }
    }

    private static long m14008G(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static int m14009a() {
        return C2418k.m14033h().nextInt(Integer.MAX_VALUE);
    }

    public static Long m14010a(String str, String str2, int i, int i2, Long l) {
        if (str == null || str2 == null) {
            return l;
        }
        if (str2.equalsIgnoreCase(".") || str2.equalsIgnoreCase("|")) {
            str2 = "\\" + str2;
        }
        String[] split = str.split(str2);
        if (split.length != i2) {
            return l;
        }
        try {
            Long valueOf = Long.valueOf(0);
            int i3 = 0;
            while (i3 < split.length) {
                Long valueOf2 = Long.valueOf(((long) i) * (valueOf.longValue() + Long.valueOf(split[i3]).longValue()));
                i3++;
                valueOf = valueOf2;
            }
            return valueOf;
        } catch (NumberFormatException e) {
            return l;
        }
    }

    public static String m14011a(long j) {
        return new SimpleDateFormat("yyyyMMdd").format(new Date(j));
    }

    public static String m14012a(String str) {
        if (str == null) {
            return Constants.VIA_RESULT_SUCCESS;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i = b & Util.MASK_8BIT;
                if (i < 16) {
                    stringBuffer.append(Constants.VIA_RESULT_SUCCESS);
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (Throwable th) {
            return Constants.VIA_RESULT_SUCCESS;
        }
    }

    public static HttpHost m14013a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) != 0) {
                return null;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getTypeName() != null && activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                return null;
            }
            String extraInfo = activeNetworkInfo.getExtraInfo();
            if (extraInfo == null) {
                return null;
            }
            if (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap")) {
                return new HttpHost("10.0.0.172", 80);
            }
            if (extraInfo.equals("ctwap")) {
                return new HttpHost("10.0.0.200", 80);
            }
            return null;
        } catch (Object th) {
            f12355f.m13978e(th);
        }
    }

    public static void m14014a(JSONObject jSONObject, String str, String str2) {
        if (str2 != null) {
            try {
                if (str2.length() > 0) {
                    jSONObject.put(str, str2);
                }
            } catch (Object th) {
                f12355f.m13978e(th);
            }
        }
    }

    public static boolean m14015a(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Object th) {
            f12355f.m13978e(th);
            return false;
        }
    }

    public static byte[] m14016a(byte[] bArr) {
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
        byte[] bArr2 = new byte[Opcodes.ACC_SYNTHETIC];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length * 2);
        while (true) {
            int read = gZIPInputStream.read(bArr2);
            if (read != -1) {
                byteArrayOutputStream.write(bArr2, 0, read);
            } else {
                bArr2 = byteArrayOutputStream.toByteArray();
                byteArrayInputStream.close();
                gZIPInputStream.close();
                byteArrayOutputStream.close();
                return bArr2;
            }
        }
    }

    public static long m14017b(String str) {
        return C2418k.m14010a(str, ".", 100, 3, Long.valueOf(0)).longValue();
    }

    public static synchronized StatLogger m14018b() {
        StatLogger statLogger;
        synchronized (C2418k.class) {
            if (f12355f == null) {
                f12355f = new StatLogger("MtaSDK");
                f12355f.setDebugEnable(false);
            }
            statLogger = f12355f;
        }
        return statLogger;
    }

    public static synchronized String m14019b(Context context) {
        String str;
        synchronized (C2418k.class) {
            if (f12350a == null || f12350a.trim().length() == 0) {
                f12350a = C2418k.m14039l(context);
                if (f12350a == null || f12350a.trim().length() == 0) {
                    f12350a = Integer.toString(C2418k.m14033h().nextInt(Integer.MAX_VALUE));
                }
                str = f12350a;
            } else {
                str = f12350a;
            }
        }
        return str;
    }

    public static String m14020b(Context context, String str) {
        if (!StatConfig.isEnableConcurrentProcess()) {
            return str;
        }
        if (f12356g == null) {
            f12356g = C2418k.m14048u(context);
        }
        return f12356g != null ? str + "_" + f12356g : str;
    }

    public static long m14021c() {
        try {
            Calendar instance = Calendar.getInstance();
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            return instance.getTimeInMillis() + MiStatInterface.MAX_UPLOAD_INTERVAL;
        } catch (Object th) {
            f12355f.m13978e(th);
            return System.currentTimeMillis() + MiStatInterface.MAX_UPLOAD_INTERVAL;
        }
    }

    public static synchronized String m14022c(Context context) {
        String str;
        synchronized (C2418k.class) {
            if (f12352c == null || C2915a.f14760f == f12352c) {
                f12352c = C2418k.m14030f(context);
            }
            str = f12352c;
        }
        return str;
    }

    public static String m14023c(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(C2414g.m13998b(C2412e.m13988a(str.getBytes(C1142e.f5201a)), 0), C1142e.f5201a);
        } catch (Object th) {
            f12355f.m13978e(th);
            return str;
        }
    }

    public static int m14024d() {
        return VERSION.SDK_INT;
    }

    public static DisplayMetrics m14025d(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static String m14026d(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(C2412e.m13990b(C2414g.m13996a(str.getBytes(C1142e.f5201a), 0)), C1142e.f5201a);
        } catch (Object th) {
            f12355f.m13978e(th);
            return str;
        }
    }

    public static String m14027e() {
        long f = C2418k.m14029f() / 1000000;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return String.valueOf((((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / 1000000) + "/" + String.valueOf(f);
    }

    public static boolean m14028e(Context context) {
        try {
            if (C2418k.m14015a(context, "android.permission.ACCESS_WIFI_STATE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
                    if (allNetworkInfo != null) {
                        int i = 0;
                        while (i < allNetworkInfo.length) {
                            if (allNetworkInfo[i].getTypeName().equalsIgnoreCase("WIFI") && allNetworkInfo[i].isConnected()) {
                                return true;
                            }
                            i++;
                        }
                    }
                }
                return false;
            }
            f12355f.warn("can not get the permission of android.permission.ACCESS_WIFI_STATE");
            return false;
        } catch (Object th) {
            f12355f.m13978e(th);
        }
    }

    public static long m14029f() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
    }

    public static String m14030f(Context context) {
        if (C2418k.m14015a(context, "android.permission.ACCESS_WIFI_STATE")) {
            try {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                return wifiManager == null ? C2915a.f14760f : wifiManager.getConnectionInfo().getMacAddress();
            } catch (Exception e) {
                f12355f.m13977e(e);
                return C2915a.f14760f;
            }
        }
        f12355f.m13978e((Object) "Could not get permission of android.permission.ACCESS_WIFI_STATE");
        return C2915a.f14760f;
    }

    public static boolean m14032g(Context context) {
        try {
            if (C2418k.m14015a(context, "android.permission.INTERNET") && C2418k.m14015a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI");
                }
                return false;
            }
            f12355f.warn("can not get the permisson of android.permission.INTERNET");
            return false;
        } catch (Object th) {
            f12355f.m13978e(th);
        }
    }

    private static synchronized Random m14033h() {
        Random random;
        synchronized (C2418k.class) {
            if (f12354e == null) {
                f12354e = new Random();
            }
            random = f12354e;
        }
        return random;
    }

    public static boolean m14034h(Context context) {
        try {
            if (C2418k.m14015a(context, "android.permission.INTERNET") && C2418k.m14015a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                        return true;
                    }
                    f12355f.m13981w("Network error");
                    return false;
                }
                return false;
            }
            f12355f.warn("can not get the permisson of android.permission.INTERNET");
            return false;
        } catch (Throwable th) {
        }
    }

    private static long m14035i() {
        long j = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), Opcodes.ACC_ANNOTATION);
            j = (long) (Integer.valueOf(bufferedReader.readLine().split("\\s+")[1]).intValue() * SmileConstants.MAX_SHARED_STRING_VALUES);
            bufferedReader.close();
            return j;
        } catch (IOException e) {
            return j;
        }
    }

    public static String m14036i(Context context) {
        if (f12351b != null) {
            return f12351b;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("TA_APPKEY");
                if (string != null) {
                    f12351b = string;
                    return string;
                }
                f12355f.m13981w("Could not read APPKEY meta-data from AndroidManifest.xml");
            }
        } catch (Throwable th) {
            f12355f.m13981w("Could not read APPKEY meta-data from AndroidManifest.xml");
        }
        return null;
    }

    public static String m14037j(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            if (applicationInfo != null) {
                Object obj = applicationInfo.metaData.get("InstallChannel");
                if (obj != null) {
                    return obj.toString();
                }
                f12355f.m13981w("Could not read InstallChannel meta-data from AndroidManifest.xml");
            }
        } catch (Throwable th) {
            f12355f.m13978e((Object) "Could not read InstallChannel meta-data from AndroidManifest.xml");
        }
        return null;
    }

    public static String m14038k(Context context) {
        return context == null ? null : context.getClass().getName();
    }

    public static String m14039l(Context context) {
        try {
            if (C2418k.m14015a(context, "android.permission.READ_PHONE_STATE")) {
                String str = C2915a.f14760f;
                if (C2418k.m14042o(context)) {
                    str = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                }
                if (str != null) {
                    return str;
                }
            }
            f12355f.m13978e((Object) "Could not get permission of android.permission.READ_PHONE_STATE");
        } catch (Object th) {
            f12355f.m13978e(th);
        }
        return null;
    }

    public static String m14040m(Context context) {
        try {
            if (!C2418k.m14015a(context, "android.permission.READ_PHONE_STATE")) {
                f12355f.m13978e((Object) "Could not get permission of android.permission.READ_PHONE_STATE");
                return null;
            } else if (!C2418k.m14042o(context)) {
                return null;
            } else {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                return telephonyManager != null ? telephonyManager.getSimOperator() : null;
            }
        } catch (Object th) {
            f12355f.m13978e(th);
            return null;
        }
    }

    public static String m14041n(Context context) {
        Object th;
        String str = C2915a.f14760f;
        String str2;
        try {
            str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str2 != null) {
                return str2;
            }
            try {
                return C2915a.f14760f;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            str2 = str;
            th = th4;
            f12355f.m13978e(th);
            return str2;
        }
    }

    public static boolean m14042o(Context context) {
        return context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0;
    }

    public static String m14043p(Context context) {
        try {
            if (C2418k.m14015a(context, "android.permission.INTERNET") && C2418k.m14015a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    String typeName = activeNetworkInfo.getTypeName();
                    String extraInfo = activeNetworkInfo.getExtraInfo();
                    if (typeName != null) {
                        return typeName.equalsIgnoreCase("WIFI") ? "WIFI" : typeName.equalsIgnoreCase("MOBILE") ? extraInfo == null ? "MOBILE" : extraInfo : extraInfo == null ? typeName : extraInfo;
                    }
                }
                return null;
            }
            f12355f.m13978e((Object) "can not get the permission of android.permission.ACCESS_WIFI_STATE");
            return null;
        } catch (Object th) {
            f12355f.m13978e(th);
        }
    }

    public static Integer m14044q(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return Integer.valueOf(telephonyManager.getNetworkType());
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static String m14045r(Context context) {
        Object th;
        String str = C2915a.f14760f;
        String str2;
        try {
            str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str2 != null) {
                try {
                    if (str2.length() != 0) {
                        return str2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    f12355f.m13978e(th);
                    return str2;
                }
            }
            return FimiMediaMeta.IJKM_VAL_TYPE__UNKNOWN;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            str2 = str;
            th = th4;
            f12355f.m13978e(th);
            return str2;
        }
    }

    public static int m14046s(Context context) {
        try {
            if (C2422o.m14058a()) {
                return 1;
            }
        } catch (Object th) {
            f12355f.m13978e(th);
        }
        return 0;
    }

    public static String m14047t(Context context) {
        try {
            if (C2418k.m14015a(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                String externalStorageState = Environment.getExternalStorageState();
                if (externalStorageState == null || !externalStorageState.equals("mounted")) {
                    return null;
                }
                externalStorageState = Environment.getExternalStorageDirectory().getPath();
                if (externalStorageState == null) {
                    return null;
                }
                StatFs statFs = new StatFs(externalStorageState);
                long blockCount = (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 1000000;
                return String.valueOf((((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks())) / 1000000) + "/" + String.valueOf(blockCount);
            }
            f12355f.warn("can not get the permission of android.permission.WRITE_EXTERNAL_STORAGE");
            return null;
        } catch (Object th) {
            f12355f.m13978e(th);
            return null;
        }
    }

    static String m14048u(Context context) {
        try {
            int myPid = Process.myPid();
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static String m14049v(Context context) {
        return C2418k.m14020b(context, StatConstants.f12303a);
    }

    public static synchronized Integer m14050w(Context context) {
        Integer valueOf;
        int i = 0;
        synchronized (C2418k.class) {
            try {
                int a = C2423p.m14059a(context, "MTA_EVENT_INDEX", 0);
                if (a < 2147483646) {
                    i = a;
                }
                C2423p.m14063b(context, "MTA_EVENT_INDEX", i + 1);
            } catch (Object th) {
                f12355f.m13978e(th);
            }
            valueOf = Integer.valueOf(i + 1);
        }
        return valueOf;
    }

    public static String m14051x(Context context) {
        return String.valueOf(C2418k.m14008G(context) / 1000000) + "/" + String.valueOf(C2418k.m14035i() / 1000000);
    }

    public static synchronized C2419l m14052y(Context context) {
        C2419l c2419l;
        synchronized (C2418k.class) {
            if (f12357h == null) {
                f12357h = new C2419l();
            }
            c2419l = f12357h;
        }
        return c2419l;
    }

    public static JSONObject m14053z(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            C2418k.m14052y(context);
            int b = C2419l.m14055b();
            if (b > 0) {
                jSONObject.put("fx", b / 1000000);
            }
            C2418k.m14052y(context);
            b = C2419l.m14056c();
            if (b > 0) {
                jSONObject.put("fn", b / 1000000);
            }
            C2418k.m14052y(context);
            b = C2419l.m14054a();
            if (b > 0) {
                jSONObject.put("n", b);
            }
            C2418k.m14052y(context);
            String d = C2419l.m14057d();
            if (d != null && d.length() == 0) {
                C2418k.m14052y(context);
                jSONObject.put("na", C2419l.m14057d());
            }
        } catch (Exception e) {
            f12355f.m13977e(e);
        }
        return jSONObject;
    }
}
