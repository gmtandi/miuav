package com.fimi.soul.utils;

import android.os.Environment;
import com.tencent.mm.sdk.platformtools.Util;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.utils.i */
public class C1969i {
    public static String m12474a() {
        return Environment.getExternalStorageDirectory().getPath() + "/MiDrone/";
    }

    public static String m12475a(String str) {
        return C1969i.m12479b(C1969i.m12491n(), str);
    }

    public static String m12476a(String str, String str2) {
        return C1969i.m12491n() + "THUMB_VIDEO_" + (str2 != null ? str2.replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, C2915a.f14760f) : C2915a.f14760f) + "_" + str.substring(0, str.lastIndexOf(".")) + Util.PHOTO_DEFAULT_EXT;
    }

    public static String m12477b() {
        return C1969i.m12474a() + "/Parameters/";
    }

    public static String m12478b(String str) {
        return C1969i.m12479b(C1969i.m12492o(), str);
    }

    private static String m12479b(String str, String str2) {
        return String.format("%s%s", new Object[]{str, str2});
    }

    public static String m12480c() {
        return C1969i.m12474a() + "/Waypoints/";
    }

    public static String m12481d() {
        return C1969i.m12474a() + "/GCP/";
    }

    public static String m12482e() {
        return C1969i.m12474a() + "/Logs/";
    }

    public static String m12483f() {
        return C1969i.m12474a() + "/Maps/";
    }

    public static String m12484g() {
        return C1969i.m12474a() + "/CameraInfo/";
    }

    public static String m12485h() {
        return C1969i.m12474a() + "/LogCat/";
    }

    public static String m12486i() {
        return C1969i.m12474a() + "/update/";
    }

    public static String m12487j() {
        return C1969i.m12474a() + "/wifi/";
    }

    public static String m12488k() {
        return C1969i.m12474a() + "/ErrorCode/";
    }

    public static String m12489l() {
        return C1969i.m12474a() + "PlaneMedia/";
    }

    public static String m12490m() {
        return C1969i.m12474a() + "LOGDOWN/";
    }

    public static String m12491n() {
        return C1969i.m12489l() + "cache/";
    }

    public static String m12492o() {
        return Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + "MiDrone/";
    }

    public static String m12493p() {
        return C1969i.m12474a() + "/firmware/";
    }

    public static String m12494q() {
        return C1969i.m12474a() + "/CameraCmdLog/log.txt";
    }
}
