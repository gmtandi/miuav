package com.xiaomi.channel.commonutils.file;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import java.io.File;

/* renamed from: com.xiaomi.channel.commonutils.file.a */
public class C2461a {
    public static boolean m14112a() {
        return Environment.getExternalStorageState().equals("removed");
    }

    public static boolean m14113b() {
        return !Environment.getExternalStorageState().equals("mounted");
    }

    public static boolean m14114c() {
        return C2461a.m14116e() <= 102400;
    }

    public static boolean m14115d() {
        return (C2461a.m14113b() || C2461a.m14114c() || C2461a.m14112a()) ? false : true;
    }

    public static long m14116e() {
        if (C2461a.m14113b()) {
            return 0;
        }
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory == null || TextUtils.isEmpty(externalStorageDirectory.getPath())) {
            return 0;
        }
        StatFs statFs = new StatFs(externalStorageDirectory.getPath());
        return (((long) statFs.getAvailableBlocks()) - 4) * ((long) statFs.getBlockSize());
    }
}
