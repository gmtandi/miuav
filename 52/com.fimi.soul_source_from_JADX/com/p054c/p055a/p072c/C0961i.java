package com.p054c.p055a.p072c;

import android.content.Context;
import android.os.Environment;
import com.facebook.common.util.UriUtil;
import com.fimi.kernel.C1154b;
import java.io.File;
import java.io.IOException;
import org.p122a.p123a.C2915a;

/* renamed from: com.c.a.c.i */
public final class C0961i {
    private static final String f5051a = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final String f5052b = "uil-images";

    private C0961i() {
    }

    public static File m7569a(Context context) {
        return C0961i.m7572a(context, true);
    }

    public static File m7570a(Context context, String str) {
        File a = C0961i.m7569a(context);
        File file = new File(a, str);
        return (file.exists() || file.mkdir()) ? file : a;
    }

    public static File m7571a(Context context, String str, boolean z) {
        File file = null;
        if (z && "mounted".equals(Environment.getExternalStorageState()) && C0961i.m7576d(context)) {
            file = new File(Environment.getExternalStorageDirectory(), str);
        }
        return (file == null || !(file.exists() || file.mkdirs())) ? context.getCacheDir() : file;
    }

    public static File m7572a(Context context, boolean z) {
        Object externalStorageState;
        File file = null;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException e) {
            externalStorageState = C2915a.f14760f;
        } catch (IncompatibleClassChangeError e2) {
            externalStorageState = C2915a.f14760f;
        }
        if (z && "mounted".equals(r1) && C0961i.m7576d(context)) {
            file = C0961i.m7575c(context);
        }
        if (file == null) {
            file = context.getCacheDir();
        }
        if (file != null) {
            return file;
        }
        C0958f.m7561c("Can't define system cache directory! '%s' will be used.", "/data/data/" + context.getPackageName() + "/cache/");
        return new File("/data/data/" + context.getPackageName() + "/cache/");
    }

    public static File m7573b(Context context) {
        return C0961i.m7570a(context, f5052b);
    }

    public static File m7574b(Context context, String str) {
        File file = null;
        if ("mounted".equals(Environment.getExternalStorageState()) && C0961i.m7576d(context)) {
            file = new File(Environment.getExternalStorageDirectory(), str);
        }
        return (file == null || !(file.exists() || file.mkdirs())) ? context.getCacheDir() : file;
    }

    private static File m7575c(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), UriUtil.DATA_SCHEME), context.getPackageName()), C1154b.f5233d);
        if (file.exists()) {
            return file;
        }
        if (file.mkdirs()) {
            try {
                new File(file, ".nomedia").createNewFile();
                return file;
            } catch (IOException e) {
                C0958f.m7559b("Can't create \".nomedia\" file in application external cache directory", new Object[0]);
                return file;
            }
        }
        C0958f.m7561c("Unable to create external cache directory", new Object[0]);
        return null;
    }

    private static boolean m7576d(Context context) {
        return context.checkCallingOrSelfPermission(f5051a) == 0;
    }
}
