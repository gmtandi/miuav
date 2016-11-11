package com.fimi.soul.utils;

import android.app.Activity;
import android.os.Vibrator;

public class bi {
    public static void m12400a(Activity activity, long j) {
        ((Vibrator) activity.getSystemService("vibrator")).vibrate(j);
    }

    public static void m12401a(Activity activity, long[] jArr, boolean z) {
        ((Vibrator) activity.getSystemService("vibrator")).vibrate(jArr, z ? 1 : -1);
    }
}
