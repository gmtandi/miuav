package com.fimi.soul.utils;

import android.os.Environment;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* renamed from: com.fimi.soul.utils.t */
public class C1980t {
    public static String m12511a() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.US).format(new Date());
    }

    public static boolean m12512b() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
}
