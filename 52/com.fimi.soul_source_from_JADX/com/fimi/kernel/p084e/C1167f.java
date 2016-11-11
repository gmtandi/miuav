package com.fimi.kernel.p084e;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.fimi.kernel.p073a.C1090c;
import com.fimi.kernel.p073a.C1091d;
import com.fimi.kernel.p073a.C1093f;
import com.fimi.kernel.p073a.C1094g;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.tencent.connect.common.Constants;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.kernel.e.f */
public class C1167f {
    public static List<String[]> f5317a;

    static {
        f5317a = null;
    }

    public static int m8097a() {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(new C1168g()).length;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static C1093f m8098a(int i) {
        C1093f c1093f = new C1093f();
        if (f5317a == null) {
            f5317a = C1167f.m8111c();
        }
        String str = C2915a.f14760f;
        int size = f5317a.size();
        for (int i2 = 0; i2 < size; i2++) {
            String[] strArr = (String[]) f5317a.get(i2);
            String str2 = strArr[0];
            if (str2 != null && Integer.parseInt(str2) == i) {
                c1093f.f5081c = Integer.parseInt(strArr[0]);
                c1093f.f5083e = strArr[2];
                c1093f.f5084f = strArr[3];
                c1093f.f5085g = strArr[4];
                long j = 0;
                if (strArr[6].indexOf("M") != -1) {
                    j = (Long.parseLong(strArr[6].replace("M", C2915a.f14760f)) * 1000) * FimiMediaMeta.AV_CH_SIDE_RIGHT;
                } else if (strArr[6].indexOf("K") != -1) {
                    j = Long.parseLong(strArr[6].replace("K", C2915a.f14760f)) * 1000;
                } else if (strArr[6].indexOf("G") != -1) {
                    j = ((Long.parseLong(strArr[6].replace("G", C2915a.f14760f)) * 1000) * FimiMediaMeta.AV_CH_SIDE_RIGHT) * FimiMediaMeta.AV_CH_SIDE_RIGHT;
                }
                c1093f.f5082d = j;
                c1093f.f5079a = strArr[8];
                c1093f.f5080b = strArr[9];
                return c1093f;
            }
        }
        return c1093f;
    }

    public static C1094g m8099a(String str) {
        for (C1094g c1094g : C1167f.m8107b()) {
            if (str.equals(c1094g.f5087b)) {
                return c1094g;
            }
        }
        return null;
    }

    public static String m8100a(String[] strArr, String str) {
        String str2;
        Exception e;
        String str3 = C2915a.f14760f;
        C1181t.m8221a(C1167f.class, "#" + strArr);
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(strArr);
            if (str != null) {
                processBuilder.directory(new File(str));
            }
            processBuilder.redirectErrorStream(true);
            InputStream inputStream = processBuilder.start().getInputStream();
            byte[] bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
            str2 = str3;
            while (inputStream.read(bArr) != -1) {
                try {
                    str2 = str2 + new String(bArr);
                } catch (Exception e2) {
                    e = e2;
                }
            }
            inputStream.close();
        } catch (Exception e3) {
            Exception exception = e3;
            str2 = str3;
            e = exception;
            e.printStackTrace();
            return str2;
        }
        return str2;
    }

    public static void m8101a(Context context, int i, String str) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        try {
            if (str.indexOf(":") != -1) {
                str = str.split(":")[0];
            }
            activityManager.killBackgroundProcesses(str);
            Method declaredMethod = activityManager.getClass().getDeclaredMethod("forceStopPackage", new Class[]{String.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(activityManager, new Object[]{str});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void m8102a(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static void m8103a(Context context, String str) {
        Intent intent = new Intent("android.intent.action.DELETE");
        intent.setData(Uri.parse("package:" + str));
        context.startActivity(intent);
    }

    public static boolean m8104a(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getState() == State.CONNECTED) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean m8105a(Context context, String str, int i) {
        InputStream openRawResource;
        Exception e;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2;
            InputStream inputStream;
            File file = new File("/data/data/" + context.getPackageName() + "/databases/" + str);
            if (file.exists()) {
                fileOutputStream2 = null;
            } else {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                openRawResource = context.getResources().openRawResource(i);
                try {
                    fileOutputStream2 = new FileOutputStream(file);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e3) {
                            }
                        }
                        if (openRawResource != null) {
                            return false;
                        }
                        try {
                            openRawResource.close();
                            return false;
                        } catch (Exception e4) {
                            return false;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e5) {
                            }
                        }
                        if (openRawResource != null) {
                            try {
                                openRawResource.close();
                            } catch (Exception e6) {
                            }
                        }
                        throw th;
                    }
                }
                try {
                    byte[] bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
                    while (true) {
                        int read = openRawResource.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream2.write(bArr, 0, read);
                    }
                    fileOutputStream2.flush();
                    inputStream = openRawResource;
                } catch (Exception e7) {
                    Exception exception = e7;
                    fileOutputStream = fileOutputStream2;
                    e = exception;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (openRawResource != null) {
                        return false;
                    }
                    openRawResource.close();
                    return false;
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    fileOutputStream = fileOutputStream2;
                    th = th4;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (openRawResource != null) {
                        openRawResource.close();
                    }
                    throw th;
                }
            }
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Exception e8) {
                }
            }
            if (inputStream == null) {
                return true;
            }
            try {
                inputStream.close();
                return true;
            } catch (Exception e9) {
                return true;
            }
        } catch (Exception e10) {
            e = e10;
            openRawResource = null;
            e.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (openRawResource != null) {
                return false;
            }
            openRawResource.close();
            return false;
        } catch (Throwable th5) {
            th = th5;
            openRawResource = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (openRawResource != null) {
                openRawResource.close();
            }
            throw th;
        }
    }

    public static C1093f m8106b(String str) {
        C1093f c1093f = new C1093f();
        if (f5317a == null) {
            f5317a = C1167f.m8111c();
        }
        String str2 = C2915a.f14760f;
        for (String[] strArr : f5317a) {
            String str3 = strArr[9];
            if (str3 != null && str3.equals(str)) {
                c1093f.f5081c = Integer.parseInt(strArr[0]);
                c1093f.f5083e = strArr[2];
                c1093f.f5084f = strArr[3];
                c1093f.f5085g = strArr[4];
                long j = 0;
                if (strArr[6].indexOf("M") != -1) {
                    j = (Long.parseLong(strArr[6].replace("M", C2915a.f14760f)) * 1000) * FimiMediaMeta.AV_CH_SIDE_RIGHT;
                } else if (strArr[6].indexOf("K") != -1) {
                    j = Long.parseLong(strArr[6].replace("K", C2915a.f14760f)) * 1000;
                } else if (strArr[6].indexOf("G") != -1) {
                    j = ((Long.parseLong(strArr[6].replace("G", C2915a.f14760f)) * 1000) * FimiMediaMeta.AV_CH_SIDE_RIGHT) * FimiMediaMeta.AV_CH_SIDE_RIGHT;
                }
                c1093f.f5082d = j;
                c1093f.f5079a = strArr[8];
                c1093f.f5080b = strArr[9];
                if (c1093f.f5082d == 0) {
                    C1181t.m8221a(C1167f.class, "##" + str + ",top -n 1\u672a\u627e\u5230");
                }
                return c1093f;
            }
        }
        if (c1093f.f5082d == 0) {
            C1181t.m8221a(C1167f.class, "##" + str + ",top -n 1\u672a\u627e\u5230");
        }
        return c1093f;
    }

    public static List<C1094g> m8107b() {
        ArrayList arrayList = new ArrayList();
        String[] split = C1167f.m8110c("ps").split("\n");
        List<C1094g> arrayList2 = new ArrayList();
        for (String c1094g : split) {
            C1094g c1094g2 = new C1094g(c1094g);
            if (c1094g2.f5086a != null) {
                arrayList2.add(c1094g2);
            }
        }
        return arrayList2;
    }

    public static boolean m8108b(Context context) {
        return ((LocationManager) context.getSystemService("location")).isProviderEnabled(GeocodeSearch.GPS);
    }

    public static boolean m8109b(Context context, String str) {
        boolean z = false;
        for (RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
            z = str.equals(runningServiceInfo.service.getClassName()) ? true : z;
        }
        return z;
    }

    public static String m8110c(String str) {
        String str2 = C2915a.f14760f;
        try {
            Process exec = Runtime.getRuntime().exec(str);
            StringBuilder stringBuilder = new StringBuilder();
            Thread thread = new Thread(new C1169h(exec, stringBuilder));
            thread.start();
            StringBuilder stringBuilder2 = new StringBuilder();
            Thread thread2 = new Thread(new C1170i(exec, stringBuilder2));
            thread2.start();
            exec.waitFor();
            while (thread.isAlive()) {
                Thread.sleep(50);
            }
            if (thread2.isAlive()) {
                thread2.interrupt();
            }
            str2 = stringBuilder.toString();
            return str2 + stringBuilder2.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String[]> m8111c() {
        List<String[]> list = null;
        try {
            list = C1167f.m8117d(C1167f.m8116d());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean m8112c(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
    }

    public static boolean m8113c(Context context, String str) {
        Intent intent;
        try {
            intent = new Intent(context, Class.forName(str));
        } catch (Exception e) {
            e.printStackTrace();
            intent = null;
        }
        return intent != null ? context.stopService(intent) : false;
    }

    public static ApplicationInfo m8114d(Context context, String str) {
        if (str == null) {
            return null;
        }
        for (ApplicationInfo applicationInfo : context.getApplicationContext().getPackageManager().getInstalledApplications(Opcodes.ACC_ANNOTATION)) {
            if (str.equals(applicationInfo.processName)) {
                return applicationInfo;
            }
        }
        return null;
    }

    public static DisplayMetrics m8115d(Context context) {
        return (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics();
    }

    public static String m8116d() {
        String str = null;
        try {
            str = C1167f.m8100a(new String[]{"/system/bin/top", "-n", Constants.VIA_TO_TYPE_QQ_GROUP}, "/system/bin/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static List<String[]> m8117d(String str) {
        Object obj = null;
        List<String[]> arrayList = new ArrayList();
        String str2 = C2915a.f14760f;
        String[] split = str.split("[\n]+");
        for (String str3 : split) {
            if (str3.indexOf("PID") != -1) {
                int i = 1;
            } else if (obj == 1) {
                Object split2 = str3.trim().split("[ ]+");
                if (split2.length == 10 && !split2[9].startsWith("/system/bin/")) {
                    arrayList.add(split2);
                }
            }
        }
        return arrayList;
    }

    public static C1091d m8118e() {
        C1091d c1091d = null;
        try {
            c1091d = C1167f.m8119e(C1167f.m8116d());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c1091d;
    }

    public static C1091d m8119e(String str) {
        C1091d c1091d = new C1091d();
        String str2 = C2915a.f14760f;
        String[] split = str.split("[\n]+");
        for (String str22 : split) {
            if (!(str22.indexOf("User") == -1 || str22.indexOf("System") == -1)) {
                String[] split2 = str22.trim().split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                for (int i = 0; i < split2.length; i++) {
                    String[] split3 = split2[i].trim().split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    if (i == 0) {
                        c1091d.f5073a = split3[1];
                    } else if (i == 1) {
                        c1091d.f5074b = split3[1];
                    } else if (i == 2) {
                        c1091d.f5075c = split3[1];
                    } else if (i == 3) {
                        c1091d.f5076d = split3[1];
                    }
                }
            }
        }
        return c1091d;
    }

    public static void m8120e(Context context) {
        ((InputMethodManager) context.getSystemService("input_method")).toggleSoftInput(0, 2);
    }

    public static void m8121f(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null && ((Activity) context).getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 2);
        }
    }

    public static PackageInfo m8122g(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    public static List<C1090c> m8123h(Context context) {
        List<C1090c> list;
        Exception exception;
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            List<C1090c> arrayList = new ArrayList();
            try {
                List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                C1167f.m8122g(context);
                if (f5317a != null) {
                    f5317a.clear();
                }
                f5317a = C1167f.m8111c();
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    C1090c c1090c = new C1090c(runningAppProcessInfo.processName, runningAppProcessInfo.pid, runningAppProcessInfo.uid);
                    ApplicationInfo d = C1167f.m8114d(context, runningAppProcessInfo.processName);
                    if (d != null) {
                        Drawable loadIcon = d.loadIcon(packageManager);
                        String charSequence = d.loadLabel(packageManager).toString();
                        c1090c.f5068e = loadIcon;
                        c1090c.f5064a = charSequence;
                    } else {
                        if (runningAppProcessInfo.processName.indexOf(":") != -1) {
                            c1090c.f5068e = C1167f.m8114d(context, runningAppProcessInfo.processName.split(":")[0]).loadIcon(packageManager);
                        }
                        c1090c.f5064a = runningAppProcessInfo.processName;
                    }
                    C1093f b = C1167f.m8106b(runningAppProcessInfo.processName);
                    c1090c.f5069f = b.f5082d;
                    c1090c.f5070g = b.f5083e;
                    c1090c.f5071h = b.f5084f;
                    c1090c.f5072i = b.f5085g;
                    arrayList.add(c1090c);
                }
                return arrayList;
            } catch (Exception e) {
                Exception exception2 = e;
                list = arrayList;
                exception = exception2;
            }
        } catch (Exception e2) {
            exception = e2;
            list = null;
            exception.printStackTrace();
            return list;
        }
    }

    public static boolean m8124i(Context context) {
        Throwable th;
        DataOutputStream dataOutputStream = null;
        Process exec;
        try {
            String str = "chmod 777 " + context.getPackageCodePath();
            exec = Runtime.getRuntime().exec("su");
            try {
                DataOutputStream dataOutputStream2 = new DataOutputStream(exec.getOutputStream());
                try {
                    dataOutputStream2.writeBytes(str + "\n");
                    dataOutputStream2.writeBytes("exit\n");
                    dataOutputStream2.flush();
                    exec.waitFor();
                    if (dataOutputStream2 != null) {
                        try {
                            dataOutputStream2.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    exec.destroy();
                    return true;
                } catch (Exception e2) {
                    dataOutputStream = dataOutputStream2;
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return false;
                        }
                    }
                    exec.destroy();
                    return false;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    dataOutputStream = dataOutputStream2;
                    th = th3;
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (Exception e32) {
                            e32.printStackTrace();
                            throw th;
                        }
                    }
                    exec.destroy();
                    throw th;
                }
            } catch (Exception e4) {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                exec.destroy();
                return false;
            } catch (Throwable th4) {
                th = th4;
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                exec.destroy();
                throw th;
            }
        } catch (Exception e5) {
            exec = null;
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            exec.destroy();
            return false;
        } catch (Throwable th5) {
            th = th5;
            exec = null;
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            exec.destroy();
            throw th;
        }
    }

    public static long m8125j(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static long m8126k(Context context) {
        long intValue;
        Exception e;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), Opcodes.ACC_ANNOTATION);
            String[] split = bufferedReader.readLine().split("\\s+");
            for (String str : split) {
                C1181t.m8221a(C1167f.class, str + "\t");
            }
            intValue = (long) (Integer.valueOf(split[1]).intValue() * SmileConstants.MAX_SHARED_STRING_VALUES);
            try {
                bufferedReader.close();
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return intValue;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            intValue = 0;
            e = exception;
            e.printStackTrace();
            return intValue;
        }
        return intValue;
    }
}
