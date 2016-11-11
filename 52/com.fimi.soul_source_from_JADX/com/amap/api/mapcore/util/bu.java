package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.amap.api.mapcore.util.bv.C0363a;
import com.amap.api.mapcore.util.de.C0336a;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;

public class bu extends Thread implements C0336a {
    private static String f2267h;
    private static String f2268i;
    private de f2269a;
    private C0362a f2270b;
    private RandomAccessFile f2271c;
    private String f2272d;
    private String f2273e;
    private String f2274f;
    private Context f2275g;

    /* renamed from: com.amap.api.mapcore.util.bu.a */
    class C0362a extends dj {
        private String f2266a;

        C0362a(String str) {
            this.f2266a = str;
        }

        public String m3740a() {
            return this.f2266a;
        }

        public Map<String, String> m3741b() {
            return null;
        }

        public Map<String, String> m3742c() {
            return null;
        }
    }

    static {
        f2267h = "sodownload";
        f2268i = "sofail";
    }

    public bu(Context context, String str, String str2, String str3) {
        this.f2275g = context;
        this.f2274f = str3;
        this.f2272d = m3743a(context, str + "temp.so");
        this.f2273e = m3743a(context, "libwgs2gcj.so");
        this.f2270b = new C0362a(str2);
        this.f2269a = new de(this.f2270b);
    }

    public static String m3743a(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "libso" + File.separator + str;
    }

    private static String m3744b(Context context, String str) {
        return m3743a(context, str);
    }

    private void m3745b() {
        File file = new File(this.f2272d);
        if (file.exists()) {
            file.delete();
        }
    }

    public void m3746a() {
        if (this.f2270b != null && !TextUtils.isEmpty(this.f2270b.m3740a()) && this.f2270b.m3740a().contains("libJni_wgs2gcj.so") && this.f2270b.m3740a().contains(Build.CPU_ABI) && !new File(this.f2273e).exists()) {
            start();
        }
    }

    public void m3747a(Throwable th) {
        try {
            if (this.f2271c != null) {
                this.f2271c.close();
            }
            m3745b();
            File file = new File(m3744b(this.f2275g, "tempfile"));
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdir();
                }
                file.createNewFile();
            }
        } catch (Throwable e) {
            cb.m3809a(e, "SDKCoordinatorDownload", "onException");
        } catch (Throwable e2) {
            cb.m3809a(e2, "SDKCoordinatorDownload", "onException");
        }
    }

    public void m3748a(byte[] bArr, long j) {
        try {
            if (this.f2271c == null) {
                File file = new File(this.f2272d);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.f2271c = new RandomAccessFile(file, "rw");
            }
        } catch (Throwable e) {
            cb.m3809a(e, "SDKCoordinatorDownload", "onDownload");
            m3745b();
        } catch (Throwable e2) {
            m3745b();
            cb.m3809a(e2, "SDKCoordinatorDownload", "onDownload");
            return;
        }
        try {
            this.f2271c.seek(j);
            this.f2271c.write(bArr);
        } catch (Throwable e22) {
            m3745b();
            cb.m3809a(e22, "SDKCoordinatorDownload", "onDownload");
        }
    }

    public void m3749d() {
        m3745b();
    }

    public void m3750e() {
        try {
            if (this.f2271c != null) {
                this.f2271c.close();
            }
            if (!bs.m3725a(this.f2272d).equalsIgnoreCase(this.f2274f)) {
                m3745b();
                bm.m3657a(this.f2275g, new C0363a(f2268i, "1.0.0", "sodownload_1.0.0").m3757a(new String[0]).m3758a());
            } else if (new File(this.f2273e).exists()) {
                m3745b();
            } else {
                new File(this.f2272d).renameTo(new File(this.f2273e));
                bm.m3657a(this.f2275g, new C0363a(f2267h, "1.0.0", "sodownload_1.0.0").m3757a(new String[0]).m3758a());
            }
        } catch (Throwable th) {
            m3745b();
            File file = new File(this.f2273e);
            if (file.exists()) {
                file.delete();
            }
            try {
                bm.m3657a(this.f2275g, new C0363a(f2268i, "1.0.0", "sodownload_1.0.0").m3757a(new String[0]).m3758a());
            } catch (bk e) {
                e.printStackTrace();
            }
            cb.m3809a(th, "SDKCoordinatorDownload", "onDownload");
        }
    }

    public void run() {
        try {
            File file = new File(m3744b(this.f2275g, "tempfile"));
            if (file.exists()) {
                bm.m3657a(this.f2275g, new C0363a(f2268i, "1.0.0", "sodownload_1.0.0").m3757a(new String[0]).m3758a());
                file.delete();
            }
            this.f2269a.m4009a(this);
        } catch (Throwable th) {
            cb.m3809a(th, "SDKCoordinatorDownload", "run");
            m3745b();
        }
    }
}
