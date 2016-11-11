package com.fimi.soul.biz.camera;

import android.util.Log;
import com.fimi.kernel.C1189f;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.biz.camera.entity.X11FileInfo;
import com.fimi.soul.biz.camera.entity.X11SystemConfig;
import com.fimi.soul.biz.camera.entity.X11SystemConfigOption;
import com.fimi.soul.biz.camera.p088b.C1267c;
import com.fimi.soul.biz.camera.p088b.C1272a;
import com.fimi.soul.biz.camera.p093a.C1270b;
import com.fimi.soul.entity.WifiDistanceFile;
import com.fimi.soul.utils.C1969i;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import java.text.SimpleDateFormat;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.camera.b */
public class C1276b {
    private static C1276b f5732b = null;
    private static final String f5733d = "SP_KEY_IsDownloadAndDelete";
    private static final String f5734e = "SP_KEY_IsTakePhotoByAPI";
    private C1298e f5735a;
    private C1267c f5736c;
    private C1273a[] f5737f;

    static {
        f5732b = new C1276b();
    }

    private C1276b() {
        this.f5735a = C1298e.X11;
        this.f5737f = new C1273a[C1298e.values().length];
        this.f5736c = new C1270b();
    }

    public static synchronized C1276b m8680a() {
        C1276b c1276b;
        synchronized (C1276b.class) {
            if (f5732b == null) {
                f5732b = new C1276b();
            }
            c1276b = f5732b;
        }
        return c1276b;
    }

    public static String m8681a(X11FileInfo x11FileInfo) {
        return String.format("THUMB_%s", new Object[]{x11FileInfo.getName()});
    }

    public static String m8682a(String str) {
        return String.format("http://%s/%s", new Object[]{C1314u.f5874a, str});
    }

    public static String m8683a(String str, String str2) {
        return String.format("%s", new Object[]{str});
    }

    public static void m8684a(DroidPlannerApp droidPlannerApp) {
        droidPlannerApp.f5570a.m9590a(((C1313t) C1276b.m8680a().m8699d()).m8876u());
    }

    private static void m8685a(X11SystemConfig x11SystemConfig, String str, int i, String[] strArr) {
        C1276b.m8686a(x11SystemConfig, str, i, strArr, strArr);
    }

    private static void m8686a(X11SystemConfig x11SystemConfig, String str, int i, String[] strArr, String[] strArr2) {
        X11SystemConfigOption x11SystemConfigOption = new X11SystemConfigOption();
        x11SystemConfigOption.setType(str);
        x11SystemConfigOption.setOptions(strArr2);
        x11SystemConfigOption.setOptionsText(strArr);
        x11SystemConfigOption.setDefaultOption(strArr2[i]);
        x11SystemConfig.getSystemConfigOptions().put(str, x11SystemConfigOption);
    }

    public static boolean m8687a(WifiDistanceFile wifiDistanceFile) {
        return C1276b.m8691c(C1276b.m8688b(wifiDistanceFile));
    }

    public static String m8688b(WifiDistanceFile wifiDistanceFile) {
        try {
            Log.d("Good", "count" + wifiDistanceFile.getName().split("\\.").length + C2915a.f14760f);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMddhhmmss");
            Log.d("Good", "date:" + wifiDistanceFile.getDateString());
            String format = simpleDateFormat2.format(simpleDateFormat.parse(wifiDistanceFile.getDateString()));
            String format2 = String.format("%s_%s.%s", new Object[]{format, r0[0], r0[1]});
            Log.d("Good", "target file download:" + format2);
            return format2;
        } catch (Exception e) {
            return wifiDistanceFile.getName();
        }
    }

    public static boolean m8689b(String str) {
        return new File(C1276b.m8693e(str)).exists();
    }

    public static String m8690c(WifiDistanceFile wifiDistanceFile) {
        return String.format("%s%s", new Object[]{C1969i.m12492o(), C1276b.m8688b(wifiDistanceFile)});
    }

    public static boolean m8691c(String str) {
        File file = new File(C1276b.m8692d(str));
        return file.exists() && file.length() > 0;
    }

    public static String m8692d(String str) {
        return String.format("%s%s", new Object[]{C1969i.m12492o(), str});
    }

    public static String m8693e(String str) {
        return String.format("%s%s", new Object[]{C1969i.m12491n(), str});
    }

    public static C1297d m8694f(String str) {
        return str.toUpperCase().contains("THUMB_") ? C1297d.Thumbnail : str.toLowerCase().contains(".mp4") ? C1297d.Video : str.toLowerCase().contains(Util.PHOTO_DEFAULT_EXT) ? C1297d.Image : null;
    }

    public void m8695a(boolean z) {
        C1189f.m8333c().m7933a(f5734e, z);
    }

    public void m8696b(boolean z) {
        C1189f.m8333c().m7933a(f5733d, z);
    }

    public boolean m8697b() {
        return C1189f.m8333c().m7937d(f5734e);
    }

    public boolean m8698c() {
        return C1189f.m8333c().m7937d(f5733d);
    }

    public C1272a m8699d() {
        int ordinal = this.f5735a.ordinal();
        switch (C1293c.f5779a[this.f5735a.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (this.f5737f[ordinal] == null) {
                    this.f5737f[ordinal] = new C1313t(m8700e());
                    break;
                }
                break;
        }
        return this.f5737f[ordinal];
    }

    public C1267c m8700e() {
        return this.f5736c;
    }

    public C1298e m8701f() {
        return this.f5735a;
    }
}
