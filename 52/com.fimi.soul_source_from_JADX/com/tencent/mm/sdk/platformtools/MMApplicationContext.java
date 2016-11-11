package com.tencent.mm.sdk.platformtools;

import android.content.Context;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.PluginIntent;

public final class MMApplicationContext {
    private static Context f11808R;
    private static String aE;

    static {
        f11808R = null;
        aE = PluginIntent.APP_PACKAGE_PATTERN;
    }

    private MMApplicationContext() {
    }

    public static Context getContext() {
        return f11808R;
    }

    public static String getDefaultPreferencePath() {
        return aE + "_preferences";
    }

    public static String getPackageName() {
        return aE;
    }

    public static void setContext(Context context) {
        f11808R = context;
        aE = context.getPackageName();
        Log.m13539d("MicroMsg.MMApplicationContext", "setup application context for package: " + aE);
    }
}
