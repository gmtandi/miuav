package com.fimi.soul.utils;

import android.content.res.AssetManager;

/* renamed from: com.fimi.soul.utils.a */
public class C1960a {
    public static boolean m12206a(AssetManager assetManager, String str, String str2) {
        for (String equals : assetManager.list(str)) {
            if (equals.equals(str2)) {
                return true;
            }
        }
        return false;
    }
}
