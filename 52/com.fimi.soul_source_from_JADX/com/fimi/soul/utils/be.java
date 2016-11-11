package com.fimi.soul.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.entity.WifiDistanceFile;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.fimi.soul.module.login.LoginActivity;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Pattern;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;

public class be {
    private static Typeface f10084a;
    private static Typeface f10085b;
    private static MediaPlayer f10086c;
    private static MediaPlayer f10087d;
    private static MediaPlayer f10088e;

    static {
        f10084a = null;
        f10085b = null;
        f10086c = null;
        f10087d = null;
        f10088e = null;
    }

    public static int m12349a(int i, int i2) {
        return Math.abs(((1 << i2) & i) >> i2);
    }

    public static Bitmap m12350a(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i) / ((float) width), ((float) i2) / ((float) height));
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static String m12351a() {
        return Build.MODEL;
    }

    public static String m12352a(long j) {
        return new SimpleDateFormat(C1236a.f5615m).format(new Date(j));
    }

    public static String m12353a(Context context) {
        return C2915a.f14760f + Secure.getString(context.getContentResolver(), "android_id");
    }

    public static String m12354a(Context context, Bitmap bitmap) {
        Throwable th;
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(Uri.parse(Media.insertImage(context.getContentResolver(), bitmap, null, null)), new String[]{"_data"}, null, null, null);
            if (query != null) {
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
                    query.moveToFirst();
                    String string = query.getString(columnIndexOrThrow);
                    if (query == null) {
                        return string;
                    }
                    query.close();
                    return string;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static String m12355a(Context context, String str) {
        String str2 = C2915a.f14760f;
        try {
            str2 = String.valueOf(context.getPackageManager().getPackageArchiveInfo(str, 2).versionName);
        } catch (Exception e) {
        }
        C1236a.m8532a("findAppVersionName version=" + str2, be.class);
        return str2;
    }

    public static String m12356a(String str) {
        return TextUtils.isEmpty(str) ? str : m12352a(Long.parseLong(str));
    }

    public static void m12357a(AssetManager assetManager, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getClass().equals(TextView.class)) {
                    m12359a(assetManager, childAt);
                }
                m12357a(assetManager, childAt);
            }
        }
    }

    public static void m12358a(AssetManager assetManager, ViewGroup viewGroup) {
        if (f10084a == null) {
            f10084a = Typeface.createFromAsset(assetManager, "lanting.TTF");
        }
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof Button) {
                ((Button) childAt).setTypeface(f10084a);
            } else if (childAt instanceof TextView) {
                ((TextView) childAt).setTypeface(f10084a);
            } else if (childAt instanceof EditText) {
                ((EditText) childAt).setTypeface(f10084a);
            } else if (childAt instanceof ViewGroup) {
                m12358a(assetManager, (ViewGroup) childAt);
            }
        }
    }

    public static void m12359a(AssetManager assetManager, View... viewArr) {
        if (f10084a == null) {
            f10084a = Typeface.createFromAsset(assetManager, "lanting.TTF");
        }
        for (View view : viewArr) {
            if (view instanceof Button) {
                ((Button) view).setTypeface(f10084a);
            } else if (view instanceof TextView) {
                ((TextView) view).setTypeface(f10084a);
            } else if (view instanceof EditText) {
                ((EditText) view).setTypeface(f10084a);
            }
        }
    }

    public static void m12360a(TextView textView) {
        textView.getPaint().setFakeBoldText(true);
    }

    public static boolean m12361a(Bitmap bitmap, String str) {
        try {
            OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str, false));
            bitmap.compress(CompressFormat.JPEG, 100, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean m12362a(File file) {
        if (file == null) {
            return false;
        }
        File file2 = new File(file.getParent() + File.separator + System.currentTimeMillis());
        file.renameTo(file2);
        return file2.delete();
    }

    public static boolean m12363a(String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        for (File name : listFiles) {
            if (name.getName().equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean m12364a(String str, String[] strArr) {
        for (String endsWith : strArr) {
            if (str.toLowerCase().endsWith(endsWith)) {
                return true;
            }
        }
        return false;
    }

    public static String m12365b() {
        return Build.MANUFACTURER;
    }

    public static String m12366b(long j) {
        return new SimpleDateFormat(C1236a.f5614l).format(new Date(j));
    }

    public static String m12367b(String str) {
        return TextUtils.isEmpty(str) ? str : m12366b(Long.parseLong(str));
    }

    public static void m12368b(AssetManager assetManager, View... viewArr) {
        if (f10085b == null) {
            f10085b = Typeface.createFromAsset(assetManager, "DIN Alternate Bold.ttf");
        }
        for (View view : viewArr) {
            if (view instanceof Button) {
                ((Button) view).setTypeface(f10085b);
            } else if (view instanceof TextView) {
                ((TextView) view).setTypeface(f10085b);
            } else if (view instanceof EditText) {
                ((EditText) view).setTypeface(f10085b);
            }
        }
    }

    private void m12369b(File file) {
        if (file.isFile()) {
            m12362a(file);
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                m12362a(file);
                return;
            }
            for (File b : listFiles) {
                m12369b(b);
            }
            m12362a(file);
        }
    }

    public static boolean m12370b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.isAvailable() : false;
    }

    public static boolean m12371b(Context context, String str) {
        for (RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService("activity")).getRunningServices(50)) {
            if (runningServiceInfo.service.getClassName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static WifiDistanceFile m12372c(String str) {
        if (str == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        String str2 = "/media";
        WifiDistanceFile wifiDistanceFile = new WifiDistanceFile(str2);
        wifiDistanceFile.setType(0);
        wifiDistanceFile.setParentStrDir("/");
        hashMap.put(str2, wifiDistanceFile);
        String[] split = str.split("\n");
        int i = 0;
        while (i < split.length) {
            String str3 = split[i];
            C1236a.m8532a("line=" + str3, be.class);
            String[] g = m12389g(str3);
            if (g.length <= 1 || str3.startsWith("/")) {
                str3 = "/media/:".equalsIgnoreCase(str3) ? "/media" : str3.replace(":", C2915a.f14760f);
            } else {
                WifiDistanceFile wifiDistanceFile2 = new WifiDistanceFile(g[2]);
                wifiDistanceFile2.setSize(Long.parseLong(g[1]));
                wifiDistanceFile2.setParentStrDir(str2);
                if (g[0].startsWith("d")) {
                    wifiDistanceFile.setType(0);
                    hashMap.put(str2 + "/" + g[2], wifiDistanceFile2);
                } else {
                    wifiDistanceFile.setType(0);
                }
                WifiDistanceFile wifiDistanceFile3 = (WifiDistanceFile) hashMap.get(str2);
                if (wifiDistanceFile3 != null) {
                    wifiDistanceFile3.addFile(wifiDistanceFile2);
                    wifiDistanceFile2.setParentDir(wifiDistanceFile3);
                } else {
                    wifiDistanceFile2.setParentDir(null);
                }
                str3 = str2;
            }
            i++;
            str2 = str3;
        }
        return wifiDistanceFile;
    }

    public static String m12373c() {
        return Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory().getPath() : null;
    }

    public static String m12374c(long j) {
        if (j == 0) {
            return "0.00B";
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String str = C2915a.f14760f;
        return j < FimiMediaMeta.AV_CH_SIDE_RIGHT ? decimalFormat.format((double) j) + "B" : j < 1048576 ? decimalFormat.format(((double) j) / 1024.0d) + "KB" : j < FimiMediaMeta.AV_CH_STEREO_RIGHT ? decimalFormat.format(((double) j) / 1048576.0d) + "MB" : decimalFormat.format(((double) j) / 1.073741824E9d) + "GB";
    }

    public static boolean m12375c(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI");
    }

    public static boolean m12376c(Context context, String str) {
        AssetManager assets = context.getAssets();
        if (str == null) {
            return false;
        }
        try {
            String[] list = assets.list(C2915a.f14760f);
            for (String equals : list) {
                if (equals.equals(str.trim())) {
                    System.out.println(str + "\u6587\u4ef6\u5b58\u5728\uff01\uff01\uff01\uff01");
                    return true;
                }
                System.out.println(str + "\u4e0d\u5b58\u5728\u5566\uff01\uff01\uff01\uff01");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\u4e0d\u5b58\u5728\uff01");
        return false;
    }

    public static String m12377d() {
        return Locale.getDefault().getLanguage();
    }

    public static String m12378d(Context context) {
        String str = C2915a.f14760f;
        try {
            str = String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
        } catch (Exception e) {
        }
        C1236a.m8532a("findAppVersionName version=" + str, be.class);
        return str;
    }

    public static boolean m12379d(Context context, String str) {
        if (str == null || C2915a.f14760f.equals(str)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, Opcodes.ACC_ANNOTATION);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean m12380d(String str) {
        boolean z = false;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("ping -c 1 -W 1 " + str);
            if (process.waitFor() == 0) {
                z = true;
            }
            if (process != null) {
                process.destroy();
            }
        } catch (Exception e) {
            if (process != null) {
                process.destroy();
            }
        } catch (Throwable th) {
            if (process != null) {
                process.destroy();
            }
        }
        return z;
    }

    public static String m12382e(Context context) {
        String str = C2915a.f14760f;
        try {
            str = String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (Exception e) {
        }
        C1236a.m8532a("findAppVersionName versionCode=" + str, be.class);
        return str;
    }

    public static boolean m12383e(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    public static String m12385f(Context context) {
        int ipAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getIpAddress();
        return String.format("%d.%d.%d.%d", new Object[]{Integer.valueOf(ipAddress & Util.MASK_8BIT), Integer.valueOf((ipAddress >> 8) & Util.MASK_8BIT), Integer.valueOf((ipAddress >> 16) & Util.MASK_8BIT), Integer.valueOf((ipAddress >> 24) & Util.MASK_8BIT)});
    }

    public static String m12386f(String str) {
        String str2 = null;
        try {
            str2 = new SimpleDateFormat(C1236a.f5614l).format(new SimpleDateFormat("yyyy-MMM-dd_HH:mm:ss", Locale.ENGLISH).parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str2;
    }

    public static String m12388g(Context context) {
        return Build.MODEL + MiPushClient.ACCEPT_TIME_SEPARATOR + ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
    }

    private static String[] m12389g(String str) {
        String[] strArr = new String[3];
        String[] split = str.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        if (split.length <= 9) {
            return split;
        }
        String str2 = C2915a.f14760f;
        int i = 0;
        for (int i2 = 0; i2 < split.length; i2++) {
            if (!C2915a.f14760f.equalsIgnoreCase(split[i2])) {
                i++;
                if (i == 1) {
                    strArr[0] = split[i2];
                } else if (i == 5) {
                    strArr[1] = split[i2];
                } else if (i >= 9) {
                    str2 = C2915a.f14760f.equalsIgnoreCase(str2) ? str2 + split[i2] : str2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + split[i2];
                }
            }
        }
        strArr[2] = str2;
        return strArr;
    }

    public static void m12390h(Context context) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            C1236a.m8532a("error : ", be.class);
        }
        ((AlarmManager) context.getSystemService("alarm")).set(1, System.currentTimeMillis() + 1000, PendingIntent.getActivity(context, 0, new Intent(context.getApplicationContext(), LoginActivity.class), 268435456));
    }

    public static Boolean m12391i(Context context) {
        String[] strArr;
        String[] strArr2;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        Method[] declaredMethods = connectivityManager.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i = 0;
        while (i < length) {
            Method method = declaredMethods[i];
            if (method.getName().equals("getTetheredIfaces")) {
                try {
                    strArr = (String[]) method.invoke(connectivityManager, new Object[0]);
                    break;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                } catch (InvocationTargetException e3) {
                    e3.printStackTrace();
                }
            } else {
                i++;
            }
        }
        strArr = null;
        int length2 = declaredMethods.length;
        length = 0;
        while (length < length2) {
            method = declaredMethods[length];
            if (method.getName().equals("getTetherableUsbRegexs")) {
                try {
                    strArr2 = (String[]) method.invoke(connectivityManager, new Object[0]);
                    break;
                } catch (IllegalAccessException e22) {
                    e22.printStackTrace();
                } catch (InvocationTargetException e32) {
                    e32.printStackTrace();
                }
            } else {
                length++;
            }
        }
        strArr2 = null;
        if (strArr == null) {
            return Boolean.valueOf(true);
        }
        boolean z = false;
        for (String str : strArr) {
            for (String matches : r6) {
                if (str.matches(matches)) {
                    z = true;
                    break;
                }
            }
        }
        return Boolean.valueOf(z);
    }

    public static boolean m12392j(Context context) {
        Object packageName = ((RunningTaskInfo) ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
        return !TextUtils.isEmpty(packageName) && packageName.equals(context.getPackageName());
    }

    public static void m12393k(Context context) {
        f10086c = MediaPlayer.create(context, C1205R.raw.video_record_end);
        f10086c.setVolume(C2020f.f10933c, C2020f.f10933c);
        f10087d = MediaPlayer.create(context, C1205R.raw.video_record_start);
        f10087d.setVolume(C2020f.f10933c, C2020f.f10933c);
        f10088e = MediaPlayer.create(context, C1205R.raw.camera_click);
        f10088e.setVolume(C2020f.f10933c, C2020f.f10933c);
    }

    public static void m12394l(Context context) {
        if (f10086c != null) {
            ah.m8075a(new bf());
        }
    }

    public static void m12395m(Context context) {
        if (f10087d != null) {
            ah.m8075a(new bg());
        }
    }

    public static void m12396n(Context context) {
        if (f10088e != null) {
            ah.m8075a(new bh());
        }
    }

    public static String m12397o(Context context) {
        return "/data/data/" + context.getPackageName() + "/databases/NoFlyZone-V1.0.0-DB.sqlite";
    }

    public static boolean m12398p(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        return locationManager.isProviderEnabled(GeocodeSearch.GPS) || locationManager.isProviderEnabled("network");
    }

    public static boolean m12399q(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }
}
