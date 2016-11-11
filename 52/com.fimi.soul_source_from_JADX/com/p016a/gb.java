package com.p016a;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.io.RandomAccessFile;

/* renamed from: com.a.gb */
public class gb extends Thread implements bm {
    private static String f1252h;
    private static String f1253i;
    private bl f1254a;
    private gc f1255b;
    private RandomAccessFile f1256c;
    private String f1257d;
    private String f1258e;
    private String f1259f;
    private Context f1260g;

    static {
        f1252h = "sodownload";
        f1253i = "sofail";
    }

    public gb(Context context, String str, String str2, String str3) {
        this.f1260g = context;
        this.f1259f = str3;
        this.f1257d = gb.m1927a(context, str + "temp.so");
        this.f1258e = gb.m1927a(context, "libwgs2gcj.so");
        this.f1255b = new gc(str2);
        this.f1254a = new bl(this.f1255b);
    }

    public static String m1927a(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "libso" + File.separator + str;
    }

    private static String m1928b(Context context, String str) {
        return gb.m1927a(context, str);
    }

    private void m1929d() {
        File file = new File(this.f1257d);
        if (file.exists()) {
            file.delete();
        }
    }

    public void m1930a() {
        if (this.f1255b != null && !TextUtils.isEmpty(this.f1255b.m1937c()) && this.f1255b.m1937c().contains("libJni_wgs2gcj.so") && this.f1255b.m1937c().contains(Build.CPU_ABI) && !new File(this.f1258e).exists()) {
            start();
        }
    }

    public void m1931a(Throwable th) {
        try {
            if (this.f1256c != null) {
                this.f1256c.close();
            }
            m1929d();
            File file = new File(gb.m1928b(this.f1260g, "tempfile"));
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdir();
                }
                file.createNewFile();
            }
        } catch (Throwable e) {
            C0247g.m1917a(e, "SDKCoordinatorDownload", "onException");
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "SDKCoordinatorDownload", "onException");
        }
    }

    public void m1932a(byte[] bArr, long j) {
        try {
            if (this.f1256c == null) {
                File file = new File(this.f1257d);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.f1256c = new RandomAccessFile(file, "rw");
            }
        } catch (Throwable e) {
            C0247g.m1917a(e, "SDKCoordinatorDownload", "onDownload");
            m1929d();
        } catch (Throwable e2) {
            m1929d();
            C0247g.m1917a(e2, "SDKCoordinatorDownload", "onDownload");
            return;
        }
        try {
            this.f1256c.seek(j);
            this.f1256c.write(bArr);
        } catch (Throwable e22) {
            m1929d();
            C0247g.m1917a(e22, "SDKCoordinatorDownload", "onDownload");
        }
    }

    public void m1933b() {
        try {
            if (this.f1256c != null) {
                this.f1256c.close();
            }
            if (!fz.m1908a(this.f1257d).equalsIgnoreCase(this.f1259f)) {
                m1929d();
                fo.m1847a(this.f1260g, new ge(f1253i, "1.0.0", "sodownload_1.0.0").m1954a(new String[0]).m1951a());
            } else if (new File(this.f1258e).exists()) {
                m1929d();
            } else {
                new File(this.f1257d).renameTo(new File(this.f1258e));
                fo.m1847a(this.f1260g, new ge(f1252h, "1.0.0", "sodownload_1.0.0").m1954a(new String[0]).m1951a());
            }
        } catch (Throwable th) {
            m1929d();
            File file = new File(this.f1258e);
            if (file.exists()) {
                file.delete();
            }
            try {
                fo.m1847a(this.f1260g, new ge(f1253i, "1.0.0", "sodownload_1.0.0").m1954a(new String[0]).m1951a());
            } catch (fm e) {
                e.printStackTrace();
            }
            C0247g.m1917a(th, "SDKCoordinatorDownload", "onDownload");
        }
    }

    public void m1934c() {
        m1929d();
    }

    public void run() {
        try {
            File file = new File(gb.m1928b(this.f1260g, "tempfile"));
            if (file.exists()) {
                fo.m1847a(this.f1260g, new ge(f1253i, "1.0.0", "sodownload_1.0.0").m1954a(new String[0]).m1951a());
                file.delete();
            }
            this.f1254a.m1175a(this);
        } catch (Throwable th) {
            C0247g.m1917a(th, "SDKCoordinatorDownload", "run");
            m1929d();
        }
    }
}
