package com.tencent.stat;

import android.content.Context;
import android.os.Environment;
import android.provider.Settings.System;
import com.tencent.stat.common.C2411d;
import com.tencent.stat.common.C2418k;
import com.tencent.stat.common.C2423p;
import com.tencent.stat.common.StatLogger;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/* renamed from: com.tencent.stat.a */
public class C2405a {
    private static C2405a f12293b;
    private StatLogger f12294a;
    private boolean f12295c;
    private boolean f12296d;
    private boolean f12297e;
    private Context f12298f;

    static {
        f12293b = null;
    }

    private C2405a(Context context) {
        this.f12294a = C2418k.m14018b();
        this.f12295c = false;
        this.f12296d = false;
        this.f12297e = false;
        this.f12298f = null;
        this.f12298f = context.getApplicationContext();
        this.f12295c = m13963b(context);
        this.f12296d = m13965d(context);
        this.f12297e = m13964c(context);
    }

    public static synchronized C2405a m13962a(Context context) {
        C2405a c2405a;
        synchronized (C2405a.class) {
            if (f12293b == null) {
                f12293b = new C2405a(context);
            }
            c2405a = f12293b;
        }
        return c2405a;
    }

    private boolean m13963b(Context context) {
        if (C2418k.m14015a(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            return true;
        }
        this.f12294a.m13978e((Object) "Check permission failed: android.permission.WRITE_EXTERNAL_STORAGE");
        return false;
    }

    private boolean m13964c(Context context) {
        if (C2418k.m14015a(context, "android.permission.WRITE_SETTINGS")) {
            return true;
        }
        this.f12294a.m13978e((Object) "Check permission failed: android.permission.WRITE_SETTINGS");
        return false;
    }

    private boolean m13965d(Context context) {
        return C2418k.m14024d() < 14 ? m13963b(context) : true;
    }

    public boolean m13966a(String str, String str2) {
        C2423p.m14065b(this.f12298f, str, str2);
        return true;
    }

    public String m13967b(String str, String str2) {
        return C2423p.m14062a(this.f12298f, str, str2);
    }

    public boolean m13968c(String str, String str2) {
        if (!this.f12295c) {
            return false;
        }
        try {
            C2411d.m13986a(Environment.getExternalStorageDirectory() + "/" + "Tencent/mta");
            File file = new File(Environment.getExternalStorageDirectory(), "Tencent/mta/.mid.txt");
            if (file != null) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(str + MiPushClient.ACCEPT_TIME_SEPARATOR + str2);
                bufferedWriter.write("\n");
                bufferedWriter.close();
            }
            return true;
        } catch (Throwable th) {
            this.f12294a.m13981w(th);
            return false;
        }
    }

    public String m13969d(String str, String str2) {
        if (!this.f12295c) {
            return null;
        }
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "Tencent/mta/.mid.txt");
            if (file != null) {
                for (String split : C2411d.m13987a(file)) {
                    String[] split2 = split.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                    if (split2.length == 2 && split2[0].equals(str)) {
                        return split2[1];
                    }
                }
            }
        } catch (FileNotFoundException e) {
            this.f12294a.m13981w("Tencent/mta/.mid.txt not found.");
        } catch (Throwable th) {
            this.f12294a.m13981w(th);
        }
        return null;
    }

    public boolean m13970e(String str, String str2) {
        if (!this.f12297e) {
            return false;
        }
        System.putString(this.f12298f.getContentResolver(), str, str2);
        return true;
    }

    public String m13971f(String str, String str2) {
        return !this.f12297e ? str2 : System.getString(this.f12298f.getContentResolver(), str);
    }
}
