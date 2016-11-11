package com.amap.api.maps;

import android.content.Context;
import com.amap.api.mapcore.at;
import com.amap.api.mapcore.util.bm;
import org.p122a.p123a.C2915a;

public final class MapsInitializer {
    private static boolean f2630a;
    public static String sdcardDir;

    static {
        sdcardDir = C2915a.f14760f;
        f2630a = true;
    }

    public static boolean getNetWorkEnable() {
        return f2630a;
    }

    public static String getVersion() {
        return "3.3.1";
    }

    public static void initialize(Context context) {
        at.f1619a = context.getApplicationContext();
    }

    public static void setApiKey(String str) {
        if (str != null && str.trim().length() > 0) {
            bm.m3656a(str);
        }
    }

    public static void setNetWorkEnable(boolean z) {
        f2630a = z;
    }
}
