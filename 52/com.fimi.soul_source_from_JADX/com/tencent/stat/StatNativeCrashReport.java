package com.tencent.stat;

import android.content.Context;
import com.tencent.stat.common.C2418k;
import com.tencent.stat.common.C2423p;
import com.tencent.stat.common.StatLogger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashSet;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class StatNativeCrashReport {
    public static final String PRE_TAG_TOMBSTONE_FNAME = "tombstone_";
    static StatNativeCrashReport f12232a;
    private static StatLogger f12233b;
    private static boolean f12234d;
    private static boolean f12235e;
    private static String f12236f;
    private volatile boolean f12237c;

    static {
        f12233b = C2418k.m14018b();
        f12232a = new StatNativeCrashReport();
        f12234d = false;
        f12235e = false;
        f12236f = null;
        try {
            System.loadLibrary("MtaNativeCrash");
        } catch (Throwable th) {
            f12234d = false;
            f12233b.m13981w(th);
        }
    }

    public StatNativeCrashReport() {
        this.f12237c = false;
    }

    static String m13918a(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine);
                stringBuilder.append('\n');
            }
            bufferedReader.close();
        } catch (Exception e) {
            f12233b.m13977e(e);
        }
        return stringBuilder.toString();
    }

    static LinkedHashSet<File> m13919a(Context context) {
        LinkedHashSet<File> linkedHashSet = new LinkedHashSet();
        String tombstonesDir = getTombstonesDir(context);
        if (tombstonesDir != null) {
            File file = new File(tombstonesDir);
            if (file != null && file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File file2 : listFiles) {
                        if (file2.getName().startsWith(PRE_TAG_TOMBSTONE_FNAME) && file2.isFile()) {
                            f12233b.m13976d("get tombstone file:" + file2.getAbsolutePath().toString());
                            linkedHashSet.add(file2.getAbsoluteFile());
                        }
                    }
                }
            }
        }
        return linkedHashSet;
    }

    static long m13920b(File file) {
        long j = 0;
        try {
            j = Long.valueOf(file.getName().replace(PRE_TAG_TOMBSTONE_FNAME, C2915a.f14760f)).longValue();
        } catch (Exception e) {
            f12233b.m13977e(e);
        }
        return j;
    }

    public static void doNativeCrashTest() {
        f12232a.makeJniCrash();
    }

    public static String getTombstonesDir(Context context) {
        if (f12236f == null) {
            f12236f = C2423p.m14062a(context, "__mta_tombstone__", C2915a.f14760f);
        }
        return f12236f;
    }

    public static void initNativeCrash(Context context, String str) {
        if (!f12232a.f12237c) {
            if (str == null) {
                try {
                    str = context.getDir("tombstones", 0).getAbsolutePath();
                } catch (Throwable th) {
                    f12233b.m13981w(th);
                    return;
                }
            }
            if (str.length() > SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
                f12233b.m13978e("The length of tombstones dir: " + str + " can't exceeds 200 bytes.");
                return;
            }
            f12236f = str;
            C2423p.m14065b(context, "__mta_tombstone__", str);
            setNativeCrashEnable(true);
            f12232a.initJNICrash(str);
            f12232a.f12237c = true;
            f12233b.m13976d("initNativeCrash success.");
        }
    }

    public static boolean isNativeCrashDebugEnable() {
        return f12235e;
    }

    public static boolean isNativeCrashEnable() {
        return f12234d;
    }

    public static String onNativeCrashHappened() {
        String str = C2915a.f14760f;
        try {
            new RuntimeException("MTA has caught a native crash, java stack:\n").printStackTrace();
            return str;
        } catch (RuntimeException e) {
            return e.toString();
        }
    }

    public static void setNativeCrashDebugEnable(boolean z) {
        try {
            f12232a.enableNativeCrashDebug(z);
            f12235e = z;
        } catch (Throwable th) {
            f12233b.m13981w(th);
        }
    }

    public static void setNativeCrashEnable(boolean z) {
        try {
            f12232a.enableNativeCrash(z);
            f12234d = z;
        } catch (Throwable th) {
            f12233b.m13981w(th);
        }
    }

    public native void enableNativeCrash(boolean z);

    public native void enableNativeCrashDebug(boolean z);

    public native boolean initJNICrash(String str);

    public native String makeJniCrash();

    public native String stringFromJNI();
}
