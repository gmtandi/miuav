package com.fimi.soul.utils;

import android.content.Intent;

public class ag {
    public static Intent m12240a() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        intent.addCategory("android.intent.category.OPENABLE");
        return intent;
    }

    public static Intent m12241a(String str) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType(str);
        intent.addCategory("android.intent.category.OPENABLE");
        return intent;
    }
}
