package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.File;

public class bh {
    private static boolean f2200a;

    static {
        f2200a = false;
        f2200a = new File("/system/framework/amap.jar").exists();
    }

    public static AssetManager m3592a(Context context) {
        if (context == null) {
            return null;
        }
        AssetManager assets = context.getAssets();
        if (!f2200a) {
            return assets;
        }
        try {
            assets.getClass().getDeclaredMethod("addAssetPath", new Class[]{String.class}).invoke(assets, new Object[]{"/system/framework/amap.jar"});
            return assets;
        } catch (Throwable th) {
            ce.m3829a(th, "ResourcesUtil", "getSelfAssets");
            return assets;
        }
    }
}
