package com.p016a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.SystemClock;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Base64;
import com.amap.api.location.AMapLocation;
import com.autonavi.aps.amapapi.model.AmapLoc;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.utils.C1972l;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Random;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.dn */
public class dn {
    private static int f909a;
    private static String[] f910b;
    private static Hashtable<String, Long> f911c;
    private static DecimalFormat f912d;
    private static SimpleDateFormat f913e;

    static {
        f909a = 0;
        f910b = null;
        f911c = new Hashtable();
        f912d = null;
        f913e = null;
    }

    private dn() {
    }

    public static float m1496a(AmapLoc amapLoc, AmapLoc amapLoc2) {
        return dn.m1497a(new double[]{amapLoc.m5339i(), amapLoc.m5337h(), amapLoc2.m5339i(), amapLoc2.m5337h()});
    }

    public static float m1497a(double[] dArr) {
        if (dArr.length != 4) {
            return 0.0f;
        }
        float[] fArr = new float[1];
        Location.distanceBetween(dArr[0], dArr[1], dArr[2], dArr[3], fArr);
        return fArr[0];
    }

    public static int m1498a(int i) {
        return (i * 2) - 113;
    }

    public static int m1499a(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    public static int m1500a(NetworkInfo networkInfo) {
        return (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) ? networkInfo.getType() : -1;
    }

    public static int m1501a(CellLocation cellLocation, Context context) {
        if (dn.m1509a(context) || cellLocation == null) {
            return 9;
        }
        if (cellLocation instanceof GsmCellLocation) {
            return 1;
        }
        try {
            Class.forName("android.telephony.cdma.CdmaCellLocation");
            return 2;
        } catch (Throwable th) {
            ev.m1777a(th, "Utils", "getCellLocT");
            return 9;
        }
    }

    public static long m1502a() {
        return System.currentTimeMillis();
    }

    public static Object m1503a(Context context, String str) {
        Object obj = null;
        if (context != null) {
            try {
                obj = context.getApplicationContext().getSystemService(str);
            } catch (Throwable th) {
                ev.m1777a(th, "Utils", "getServ");
            }
        }
        return obj;
    }

    public static synchronized String m1504a(long j, String str) {
        String format;
        synchronized (dn.class) {
            if (TextUtils.isEmpty(str)) {
                str = C1236a.f5614l;
            }
            if (f913e == null) {
                try {
                    f913e = new SimpleDateFormat(str, Locale.CHINA);
                } catch (Throwable th) {
                    ev.m1777a(th, "Utils", "formatUTC");
                }
            } else {
                f913e.applyPattern(str);
            }
            if (j <= 0) {
                j = dn.m1502a();
            }
            format = f913e == null ? "NULL" : f913e.format(Long.valueOf(j));
        }
        return format;
    }

    public static String m1505a(Object obj, String str) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
        if (f912d == null) {
            f912d = new DecimalFormat("#", decimalFormatSymbols);
        }
        f912d.applyPattern(str);
        return f912d.format(obj);
    }

    public static String m1506a(String str, int i) {
        byte[] bArr = null;
        try {
            bArr = str.getBytes(C1142e.f5201a);
        } catch (Throwable th) {
            ev.m1777a(th, "Utils", "str2Base64");
        }
        return Base64.encodeToString(bArr, i);
    }

    public static boolean m1507a(double d) {
        return d <= 180.0d && d >= -180.0d;
    }

    public static synchronized boolean m1508a(long j, long j2) {
        boolean z;
        synchronized (dn.class) {
            String str = "yyyyMMdd";
            z = false;
            if (f913e == null) {
                try {
                    f913e = new SimpleDateFormat(str, Locale.CHINA);
                } catch (Throwable th) {
                    ev.m1777a(th, "Utils", "isSameDay part1");
                }
            } else {
                f913e.applyPattern(str);
            }
            try {
                if (f913e != null) {
                    z = f913e.format(Long.valueOf(j)).equals(f913e.format(Long.valueOf(j2)));
                }
            } catch (Throwable th2) {
                ev.m1777a(th2, "Utils", "isSameDay");
            }
        }
        return z;
    }

    public static boolean m1509a(Context context) {
        boolean z = true;
        if (context == null) {
            return false;
        }
        ContentResolver contentResolver = context.getContentResolver();
        if (dn.m1526c() < 17) {
            try {
                String str = "android.provider.Settings$System";
                String str2 = ((String) dl.m1487a(str, "AIRPLANE_MODE_ON")).toString();
                return ((Integer) dl.m1488a(str, "getInt", new Object[]{contentResolver, str2}, new Class[]{ContentResolver.class, String.class})).intValue() == 1;
            } catch (Throwable th) {
                ev.m1777a(th, "Utils", "airPlaneModeOn part");
                return false;
            }
        }
        try {
            str = "android.provider.Settings$Global";
            str2 = ((String) dl.m1487a(str, "AIRPLANE_MODE_ON")).toString();
            if (((Integer) dl.m1488a(str, "getInt", new Object[]{contentResolver, str2}, new Class[]{ContentResolver.class, String.class})).intValue() != 1) {
                z = false;
            }
            return z;
        } catch (Throwable th2) {
            ev.m1777a(th2, "Utils", "airPlaneModeOn");
            return false;
        }
    }

    public static boolean m1510a(ScanResult scanResult) {
        return (scanResult == null || TextUtils.isEmpty(scanResult.BSSID) || scanResult.BSSID.equals("00:00:00:00:00:00") || scanResult.BSSID.contains(" :")) ? false : true;
    }

    public static boolean m1511a(AMapLocation aMapLocation) {
        if (aMapLocation == null || aMapLocation.getErrorCode() != 0) {
            return false;
        }
        double longitude = aMapLocation.getLongitude();
        double latitude = aMapLocation.getLatitude();
        return !(longitude == 0.0d && latitude == 0.0d && ((double) aMapLocation.getAccuracy()) == 0.0d) && longitude <= 180.0d && latitude <= 90.0d && longitude >= -180.0d && latitude >= -90.0d;
    }

    public static boolean m1512a(AmapLoc amapLoc) {
        if (amapLoc == null || amapLoc.m5347m().equals(C1972l.f10194m) || amapLoc.m5347m().equals(Constants.VIA_SHARE_TYPE_TEXT) || amapLoc.m5347m().equals(Constants.VIA_SHARE_TYPE_INFO)) {
            return false;
        }
        double h = amapLoc.m5337h();
        double i = amapLoc.m5339i();
        return !(h == 0.0d && i == 0.0d && ((double) amapLoc.m5341j()) == 0.0d) && h <= 180.0d && i <= 90.0d && h >= -180.0d && i >= -90.0d;
    }

    public static boolean m1513a(String str) {
        return (!TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str)) ? ",111,123,134,199,202,204,206,208,212,213,214,216,218,219,220,222,225,226,228,230,231,232,234,235,238,240,242,244,246,247,248,250,255,257,259,260,262,266,268,270,272,274,276,278,280,282,283,284,286,288,289,290,292,293,294,295,297,302,308,310,311,312,313,314,315,316,310,330,332,334,338,340,342,344,346,348,350,352,354,356,358,360,362,363,364,365,366,368,370,372,374,376,400,401,402,404,405,406,410,412,413,414,415,416,417,418,419,420,421,422,424,425,426,427,428,429,430,431,432,434,436,437,438,440,441,450,452,454,455,456,457,466,467,470,472,502,505,510,514,515,520,525,528,530,534,535,536,537,539,540,541,542,543,544,545,546,547,548,549,550,551,552,553,555,560,598,602,603,604,605,606,607,608,609,610,611,612,613,614,615,616,617,618,619,620,621,622,623,624,625,626,627,628,629,630,631,632,633,634,635,636,637,638,639,640,641,642,643,645,646,647,648,649,650,651,652,653,654,655,657,659,665,702,704,706,708,710,712,714,716,722,724,730,732,734,736,738,740,742,744,746,748,750,850,901,".contains(MiPushClient.ACCEPT_TIME_SEPARATOR + str + MiPushClient.ACCEPT_TIME_SEPARATOR) : false;
    }

    public static boolean m1514a(JSONObject jSONObject, String str) {
        return gf.m1960a(jSONObject, str);
    }

    public static byte[] m1515a(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) ((int) ((j >> (i * 8)) & 255));
        }
        return bArr;
    }

    public static final byte[] m1516a(File file) {
        if (file == null || !file.exists()) {
            throw new IOException("can't operate on null");
        }
        byte[] bArr = new byte[Opcodes.ACC_STRICT];
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                fileInputStream.close();
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static byte[] m1517a(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            bArr2 = gf.m1962a(bArr);
        } catch (Throwable th) {
            ev.m1777a(th, "Utils", "gz");
        }
        return bArr2;
    }

    public static String[] m1518a(TelephonyManager telephonyManager) {
        int parseInt;
        Object obj = null;
        if (telephonyManager != null) {
            obj = telephonyManager.getNetworkOperator();
        }
        String[] strArr = new String[]{Constants.VIA_RESULT_SUCCESS, Constants.VIA_RESULT_SUCCESS};
        int i = TextUtils.isEmpty(obj) ? 0 : !TextUtils.isDigitsOnly(obj) ? 0 : obj.length() <= 4 ? 0 : 1;
        if (i != 0) {
            strArr[0] = obj.substring(0, 3);
            char[] toCharArray = obj.substring(3).toCharArray();
            i = 0;
            while (i < toCharArray.length && Character.isDigit(toCharArray[i])) {
                i++;
            }
            strArr[1] = obj.substring(3, i + 3);
        }
        try {
            parseInt = Integer.parseInt(strArr[0]);
        } catch (Throwable th) {
            ev.m1777a(th, "Utils", "getMccMnc");
            parseInt = 0;
        }
        if (parseInt == 0) {
            strArr[0] = Constants.VIA_RESULT_SUCCESS;
        }
        if (strArr[0].equals(Constants.VIA_RESULT_SUCCESS) || strArr[1].equals(Constants.VIA_RESULT_SUCCESS)) {
            return (strArr[0].equals(Constants.VIA_RESULT_SUCCESS) && strArr[1].equals(Constants.VIA_RESULT_SUCCESS) && f910b != null) ? f910b : strArr;
        } else {
            f910b = strArr;
            return strArr;
        }
    }

    public static long m1519b() {
        return SystemClock.elapsedRealtime();
    }

    public static final long m1520b(byte[] bArr) {
        long j = 0;
        for (int i = 0; i < 8; i++) {
            j = (j << 8) | ((long) (bArr[i] & Util.MASK_8BIT));
        }
        return j;
    }

    public static String m1521b(Context context) {
        CharSequence charSequence = null;
        if (context == null) {
            return null;
        }
        if (!TextUtils.isEmpty(ev.f1152k)) {
            return ev.f1152k;
        }
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 64);
        } catch (Throwable th) {
            ev.m1777a(th, "Utils", "getAppName part");
            packageInfo = null;
        }
        try {
            if (TextUtils.isEmpty(ev.f1153l)) {
                ev.f1153l = null;
            }
        } catch (Throwable th2) {
            ev.m1777a(th2, "Utils", "getAppName");
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (packageInfo != null) {
            if (packageInfo.applicationInfo != null) {
                charSequence = packageInfo.applicationInfo.loadLabel(context.getPackageManager());
            }
            if (charSequence != null) {
                stringBuilder.append(charSequence.toString());
            }
            if (!TextUtils.isEmpty(packageInfo.versionName)) {
                stringBuilder.append(packageInfo.versionName);
            }
        }
        if (!TextUtils.isEmpty(ev.f1149h)) {
            stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(ev.f1149h);
        }
        if (!TextUtils.isEmpty(ev.f1153l)) {
            stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(ev.f1153l);
        }
        return stringBuilder.toString();
    }

    public static String m1522b(TelephonyManager telephonyManager) {
        int i = 0;
        if (telephonyManager != null) {
            i = telephonyManager.getNetworkType();
        }
        return (String) ev.f1159r.get(i, "UNKWN");
    }

    public static boolean m1523b(double d) {
        return d <= 90.0d && d >= -90.0d;
    }

    public static byte[] m1524b(int i) {
        byte[] bArr = new byte[2];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) ((i >> (i2 * 8)) & Util.MASK_8BIT);
        }
        return bArr;
    }

    public static byte[] m1525b(String str) {
        byte[] bArr = new byte[6];
        String[] split = str.split(":");
        for (int i = 0; i < split.length; i++) {
            bArr[i] = (byte) Integer.parseInt(split[i], 16);
        }
        return bArr;
    }

    public static int m1526c() {
        if (f909a > 0) {
            return f909a;
        }
        int i = 0;
        String str = "android.os.Build$VERSION";
        try {
            return dl.m1490b(str, "SDK_INT");
        } catch (Throwable th) {
            ev.m1777a(th, "Utils", "getSdk");
            return i;
        }
    }

    public static final int m1527c(byte[] bArr) {
        return ((bArr[0] & Util.MASK_8BIT) << 8) | (bArr[1] & Util.MASK_8BIT);
    }

    public static NetworkInfo m1528c(Context context) {
        NetworkInfo networkInfo = null;
        try {
            networkInfo = fw.m1889n(context);
        } catch (Throwable th) {
            ev.m1777a(th, "Utils", "getNetWorkInfo");
        }
        return networkInfo;
    }

    public static String m1529c(String str) {
        return dn.m1506a(str, 0);
    }

    public static byte[] m1530c(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) ((i >> (i2 * 8)) & Util.MASK_8BIT);
        }
        return bArr;
    }

    public static final int m1531d(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (i < 4) {
            i2 = (i2 << 8) | (bArr[i] & Util.MASK_8BIT);
            i++;
        }
        return i2;
    }

    public static String m1532d() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static String m1533d(String str) {
        if (str == null || str.length() == 0) {
            return C2915a.f14760f;
        }
        try {
            return new String(Base64.decode(str, 0), C1142e.f5201a);
        } catch (Throwable th) {
            ev.m1777a(th, "Utils", "base642Str");
            return null;
        }
    }

    public static boolean m1534d(Context context) {
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                    return runningAppProcessInfo.importance != 100;
                }
            }
            return false;
        } catch (Throwable th) {
            ev.m1777a(th, "Utils", "isApplicationBroughtToBackground");
            return true;
        }
    }

    public static String m1535e() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dn.m1532d()).append(File.separator);
        stringBuilder.append("amaplocationapi").append(File.separator);
        return stringBuilder.toString();
    }

    public static byte[] m1536e(String str) {
        return dn.m1524b(Integer.parseInt(str));
    }

    public static String m1537f() {
        return Build.MODEL;
    }

    public static byte[] m1538f(String str) {
        return dn.m1530c(Integer.parseInt(str));
    }

    public static String m1539g() {
        return VERSION.RELEASE;
    }

    public static boolean m1540h() {
        return dn.m1499a(0, 1) == 1;
    }

    public static void m1541i() {
        f911c.clear();
    }

    public static String m1542j() {
        String str = C2915a.f14760f;
        try {
            return fy.m1904b(ev.f1147f.getBytes(C1142e.f5201a)).substring(20);
        } catch (Exception e) {
            return C2915a.f14760f;
        }
    }

    public static boolean m1543k() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
}
