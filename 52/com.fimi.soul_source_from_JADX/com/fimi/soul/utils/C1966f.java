package com.fimi.soul.utils;

import android.content.Context;

/* renamed from: com.fimi.soul.utils.f */
public class C1966f {
    public static int m12465a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static int m12466b(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
