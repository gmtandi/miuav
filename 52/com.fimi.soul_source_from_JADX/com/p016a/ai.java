package com.p016a;

import android.content.Context;
import android.os.Build.VERSION;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

/* renamed from: com.a.ai */
public class ai extends Thread implements bm {
    private aj f534a;
    private bl f535b;
    private gd f536c;
    private String f537d;
    private RandomAccessFile f538e;
    private String f539f;
    private Context f540g;
    private String f541h;
    private String f542i;
    private String f543j;
    private String f544k;
    private int f545l;
    private int f546m;

    public ai(Context context, aj ajVar, gd gdVar) {
        try {
            this.f540g = context.getApplicationContext();
            this.f536c = gdVar;
            if (ajVar != null) {
                this.f534a = ajVar;
                this.f535b = new bl(new al(this.f534a));
                String[] split = this.f534a.m1029a().split("/");
                this.f539f = split[split.length - 1];
                split = this.f539f.split("_");
                this.f541h = split[0];
                this.f542i = split[2];
                this.f543j = split[1];
                this.f545l = Integer.parseInt(split[3]);
                this.f546m = Integer.parseInt(split[4].split("\\.")[0]);
                this.f544k = ajVar.m1030b();
                this.f537d = an.m1047a(context, this.f539f);
            }
        } catch (Throwable th) {
            C0247g.m1917a(th, "DexDownLoad", "DexDownLoad");
        }
    }

    private void m1017a(String str) {
        C0261v c0261v = new C0261v(this.f540g, at.m1080c());
        List c = c0261v.m2052c(au.m1089b(str, "copy"), new au());
        an.m1053a(c);
        if (c != null && c.size() > 1) {
            int size = c.size();
            for (int i = 1; i < size; i++) {
                an.m1051a(this.f540g, c0261v, ((av) c.get(i)).m1096a());
            }
        }
    }

    private boolean m1018a(Context context) {
        return fw.m1888m(context) == 1;
    }

    private boolean m1019a(C0261v c0261v, av avVar, String str, String str2, String str3, String str4) {
        if ("errorstatus".equals(avVar.m1102f())) {
            if (!new File(an.m1048a(this.f540g, this.f536c.m1938a(), this.f536c.m1940b())).exists()) {
                aq.m1064a(this.f540g, this.f536c, an.m1048a(this.f540g, this.f541h, this.f536c.m1940b()), an.m1045a(this.f540g), null, this.f540g.getClassLoader(), an.m1046a(this.f540g, c0261v, this.f536c));
            }
            return true;
        } else if (!new File(this.f537d).exists()) {
            return false;
        } else {
            List c = c0261v.m2052c(au.m1087a(an.m1057b(str, str2), str, str2, str3), new au());
            if (c != null && c.size() > 0) {
                return true;
            }
            try {
                an.m1050a(this.f540g, c0261v, this.f536c, new aw(an.m1057b(str, this.f536c.m1940b()), str4, str, str2, str3).m1110a("usedex").m1109a(), this.f537d);
                aq.m1064a(this.f540g, this.f536c, an.m1048a(this.f540g, this.f541h, this.f536c.m1940b()), an.m1045a(this.f540g), null, this.f540g.getClassLoader(), str3);
            } catch (Throwable e) {
                C0247g.m1917a(e, "DexDownLoad", "processDownloadedFile()");
            } catch (Throwable e2) {
                C0247g.m1917a(e2, "DexDownLoad", "processDownloadedFile()");
            } catch (Throwable e22) {
                C0247g.m1917a(e22, "DexDownLoad", "processDownloadedFile()");
            }
            return true;
        }
    }

    private boolean m1020a(String str, String str2) {
        return this.f536c != null && this.f536c.m1938a().equals(str) && this.f536c.m1940b().equals(str2);
    }

    private boolean m1021a(String str, String str2, String str3, String str4, String str5) {
        C0261v c0261v = new C0261v(this.f540g, at.m1080c());
        try {
            List c = c0261v.m2052c(au.m1089b(str3, "usedex"), new au());
            if (c != null && c.size() > 0 && ay.m1111a(((av) c.get(0)).m1101e(), this.f543j) > 0) {
                return true;
            }
        } catch (Throwable th) {
            C0247g.m1917a(th, "DexDownLoad", "isDownloaded()");
        }
        av a = ap.m1061a(c0261v, str);
        return a != null ? m1019a(c0261v, a, str3, str4, str2, str5) : false;
    }

    private boolean m1022d() {
        return VERSION.SDK_INT >= this.f546m && VERSION.SDK_INT <= this.f545l;
    }

    private boolean m1023e() {
        try {
            if (!m1020a(this.f541h, this.f542i) || m1021a(this.f539f, this.f543j, this.f541h, this.f542i, this.f544k) || !m1018a(this.f540g) || !m1022d()) {
                return false;
            }
            m1017a(this.f536c.m1938a());
            return true;
        } catch (Throwable th) {
            C0247g.m1917a(th, "DexDownLoad", "isNeedDownload()");
            return false;
        }
    }

    public void m1024a() {
        try {
            start();
        } catch (Throwable th) {
            C0247g.m1917a(th, "DexDownLoad", "startDownload");
        }
    }

    public void m1025a(Throwable th) {
        try {
            if (this.f538e != null) {
                this.f538e.close();
            }
        } catch (Throwable e) {
            C0247g.m1917a(e, "DexDownLoad", "onException()");
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "DexDownLoad", "onException()");
        }
    }

    public void m1026a(byte[] bArr, long j) {
        try {
            if (this.f538e == null) {
                File file = new File(this.f537d);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.f538e = new RandomAccessFile(file, "rw");
            }
        } catch (Throwable e) {
            C0247g.m1917a(e, "DexDownLoad", "onDownload()");
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "DexDownLoad", "onDownload()");
            return;
        }
        try {
            this.f538e.seek(j);
            this.f538e.write(bArr);
        } catch (Throwable e22) {
            C0247g.m1917a(e22, "DexDownLoad", "onDownload()");
        }
    }

    public void m1027b() {
        try {
            if (this.f538e != null) {
                try {
                    this.f538e.close();
                } catch (Throwable e) {
                    C0247g.m1917a(e, "DexDownLoad", "onFinish()");
                }
                String b = this.f534a.m1030b();
                if (an.m1056a(this.f537d, b)) {
                    String c = this.f534a.m1031c();
                    C0261v c0261v = new C0261v(this.f540g, at.m1080c());
                    ap.m1062a(c0261v, new aw(this.f539f, b, this.f541h, c, this.f543j).m1110a("copy").m1109a(), au.m1087a(this.f539f, this.f541h, c, this.f543j));
                    an.m1050a(this.f540g, c0261v, this.f536c, new aw(an.m1057b(this.f541h, this.f536c.m1940b()), b, this.f541h, c, this.f543j).m1110a("usedex").m1109a(), this.f537d);
                    aq.m1064a(this.f540g, this.f536c, an.m1048a(this.f540g, this.f541h, this.f536c.m1940b()), an.m1045a(this.f540g), null, this.f540g.getClassLoader(), this.f543j);
                    return;
                }
                try {
                    new File(this.f537d).delete();
                } catch (Throwable e2) {
                    C0247g.m1917a(e2, "DexDownLoad", "onFinish()");
                }
            }
        } catch (Throwable e22) {
            C0247g.m1917a(e22, "DexDownLoad", "onFinish()");
        } catch (Throwable e222) {
            C0247g.m1917a(e222, "DexDownLoad", "onFinish()");
        } catch (Throwable e2222) {
            C0247g.m1917a(e2222, "DexDownLoad", "onFinish()");
        }
    }

    public void m1028c() {
    }

    public void run() {
        try {
            if (m1023e()) {
                this.f535b.m1175a(this);
            }
        } catch (Throwable th) {
            C0247g.m1917a(th, "DexDownLoad", "run");
        }
    }
}
