package cn.sharesdk.framework;

import android.content.Context;
import android.graphics.Bitmap;
import com.mob.tools.utils.DeviceHelper;
import java.util.HashMap;

public class ShareSDK {
    private static C0194n f188a;
    private static boolean f189b;

    static {
        f189b = true;
    }

    static String m400a(int i, String str) {
        m411c();
        return f188a.m684a(i, str);
    }

    static String m401a(Bitmap bitmap) {
        m411c();
        return f188a.m685a(bitmap);
    }

    static String m402a(String str) {
        m411c();
        return f188a.m707c(str);
    }

    static String m403a(String str, boolean z, int i, String str2) {
        m411c();
        return f188a.m686a(str, z, i, str2);
    }

    static void m404a(int i, int i2) {
        m411c();
        f188a.m688a(i, i2);
    }

    static void m405a(String str, String str2) {
        m411c();
        f188a.m692a(str, str2);
    }

    static boolean m406a() {
        m411c();
        return f188a.m711e();
    }

    static boolean m407a(HashMap<String, Object> hashMap) {
        m411c();
        return f188a.m695a((HashMap) hashMap);
    }

    static String m408b(String str, String str2) {
        m411c();
        return f188a.m699b(str, str2);
    }

    static boolean m409b() {
        m411c();
        return f188a.m712f();
    }

    static boolean m410b(HashMap<String, Object> hashMap) {
        m411c();
        return f188a.m703b((HashMap) hashMap);
    }

    private static void m411c() {
        if (f188a == null) {
            throw new RuntimeException("Please call ShareSDK.initSDK(Context) in the main process before any action.");
        }
    }

    public static void closeDebug() {
        f189b = false;
    }

    public static void deleteCache() {
        m411c();
        f188a.m713g();
    }

    @Deprecated
    public static Platform getPlatform(Context context, String str) {
        m411c();
        return f188a.m683a(str);
    }

    public static Platform getPlatform(String str) {
        m411c();
        return f188a.m683a(str);
    }

    public static synchronized Platform[] getPlatformList() {
        Platform[] a;
        synchronized (ShareSDK.class) {
            m411c();
            a = f188a.m696a();
        }
        return a;
    }

    @Deprecated
    public static synchronized Platform[] getPlatformList(Context context) {
        Platform[] platformList;
        synchronized (ShareSDK.class) {
            platformList = getPlatformList();
        }
        return platformList;
    }

    public static int getSDKVersionCode() {
        m411c();
        return f188a.m704c();
    }

    public static String getSDKVersionName() {
        m411c();
        return f188a.m698b();
    }

    public static <T extends Service> T getService(Class<T> cls) {
        m411c();
        return f188a.m705c((Class) cls);
    }

    public static void initSDK(Context context) {
        initSDK(context, null, true);
    }

    public static void initSDK(Context context, String str) {
        initSDK(context, str, true);
    }

    public static void initSDK(Context context, String str, boolean z) {
        if (DeviceHelper.getInstance(context) == null) {
            throw new RuntimeException("The param of context is null which in ShareSDK.initSDK(context)!");
        } else if (f188a == null) {
            C0194n c0194n = new C0194n(context, str);
            c0194n.m694a(z);
            c0194n.startThread();
            f188a = c0194n;
        }
    }

    public static void initSDK(Context context, boolean z) {
        initSDK(context, null, z);
    }

    public static boolean isDebug() {
        return f189b;
    }

    public static boolean isRemoveCookieOnAuthorize() {
        m411c();
        return f188a.m709d();
    }

    public static void logApiEvent(String str, int i) {
        m411c();
        f188a.m691a(str, i);
    }

    public static void logDemoEvent(int i, Platform platform) {
        m411c();
        f188a.m689a(i, platform);
    }

    public static String platformIdToName(int i) {
        m411c();
        return f188a.m706c(i);
    }

    public static int platformNameToId(String str) {
        m411c();
        return f188a.m697b(str);
    }

    public static void registerPlatform(Class<? extends CustomPlatform> cls) {
        m411c();
        f188a.m708d(cls);
    }

    public static void registerService(Class<? extends Service> cls) {
        m411c();
        f188a.m690a((Class) cls);
    }

    public static void removeCookieOnAuthorize(boolean z) {
        m411c();
        f188a.m702b(z);
    }

    public static void setConnTimeout(int i) {
        m411c();
        f188a.m687a(i);
    }

    public static void setPlatformDevInfo(String str, HashMap<String, Object> hashMap) {
        m411c();
        f188a.m693a(str, (HashMap) hashMap);
    }

    public static void setReadTimeout(int i) {
        m411c();
        f188a.m700b(i);
    }

    public static void stopSDK() {
    }

    public static void stopSDK(Context context) {
        stopSDK();
    }

    public static void unregisterPlatform(Class<? extends CustomPlatform> cls) {
        m411c();
        f188a.m710e(cls);
    }

    public static void unregisterService(Class<? extends Service> cls) {
        m411c();
        f188a.m701b((Class) cls);
    }
}
