package com.p016a;

import android.content.Context;
import android.text.TextUtils;
import dalvik.system.DexFile;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.a.aq */
class aq extends ClassLoader {
    private static aq f558c;
    private static boolean f559h;
    volatile boolean f560a;
    private final Context f561b;
    private final Map<String, Class<?>> f562d;
    private DexFile f563e;
    private String f564f;
    private gd f565g;

    static {
        f558c = null;
        f559h = true;
    }

    private aq(Context context, ClassLoader classLoader) {
        super(classLoader);
        this.f562d = new HashMap();
        this.f563e = null;
        this.f560a = true;
        this.f561b = context;
    }

    static synchronized aq m1063a(Context context, gd gdVar, String str, String str2, String str3, ClassLoader classLoader) {
        aq aqVar = null;
        synchronized (aq.class) {
            if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
                as.m1075a(context, gdVar);
                File file = new File(str);
                File parentFile = file.getParentFile();
                if (file.exists()) {
                    if (f558c == null) {
                        new Date().getTime();
                        try {
                            f558c = new aq(context.getApplicationContext(), classLoader);
                            f558c.f565g = gdVar;
                            f558c.m1069a(str, str2 + File.separator + an.m1049a(file.getName()));
                        } catch (Throwable th) {
                            C0247g.m1917a(th, "DynamicClassLoader", "getInstance()");
                        }
                        new Date().getTime();
                        new ar(context, str, str2).start();
                    }
                    aqVar = f558c;
                } else if (f559h && parentFile != null && parentFile.exists()) {
                    an.m1059b(context, gdVar.m1938a(), gdVar.m1940b());
                    f559h = false;
                }
            }
        }
        return aqVar;
    }

    static synchronized void m1064a(Context context, gd gdVar, String str, String str2, String str3, ClassLoader classLoader, String str4) {
        synchronized (aq.class) {
            if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
                try {
                    File file = new File(str);
                    File parentFile = file.getParentFile();
                    if (file.exists()) {
                        String str5 = str2 + File.separator + an.m1049a(file.getName());
                        DexFile loadDex = DexFile.loadDex(str, str5, 0);
                        if (loadDex != null) {
                            loadDex.close();
                            aq.m1068a(new File(str5), str5, str4, new C0261v(context, at.m1080c()), gdVar);
                        }
                    } else if (f559h && parentFile != null && parentFile.exists()) {
                        an.m1059b(context, gdVar.m1938a(), gdVar.m1940b());
                        f559h = false;
                    }
                } catch (Throwable th) {
                    C0247g.m1917a(th, "DynamicClassLoader", "getInstanceByThread()");
                }
            }
        }
    }

    private void m1065a(Context context, String str, String str2) {
        new Date().getTime();
        try {
            C0261v c0261v = new C0261v(context, at.m1080c());
            File file = new File(str);
            m1067a(c0261v, file.getName());
            if (!m1070a(c0261v, this.f565g, file.getAbsolutePath())) {
                this.f560a = false;
                an.m1058b(this.f561b, c0261v, file.getName());
                String a = an.m1046a(this.f561b, c0261v, this.f565g);
                if (!TextUtils.isEmpty(a)) {
                    this.f564f = a;
                    aq.m1064a(this.f561b, this.f565g, str, str2, null, this.f561b.getClassLoader(), a);
                }
            }
            if (file.exists()) {
                String str3 = str2 + File.separator + an.m1049a(file.getName());
                File file2 = new File(str3);
                if (file2.exists() && !m1071a(c0261v, an.m1049a(file.getName()), this.f564f)) {
                    m1069a(str, str2 + File.separator + an.m1049a(file.getName()));
                    aq.m1068a(file2, str3, this.f564f, c0261v, this.f565g);
                }
                new Date().getTime();
            }
        } catch (Throwable th) {
            C0247g.m1917a(th, "DynamicClassLoader", "verifyDynamicSDK()");
        }
    }

    private void m1067a(C0261v c0261v, String str) {
        av a = ap.m1061a(c0261v, str);
        if (a != null) {
            this.f564f = a.m1101e();
        }
    }

    private static void m1068a(File file, String str, String str2, C0261v c0261v, gd gdVar) {
        if (!TextUtils.isEmpty(str2)) {
            Object a = fz.m1908a(str);
            if (!TextUtils.isEmpty(a)) {
                String name = file.getName();
                ap.m1062a(c0261v, new aw(name, a, gdVar.m1938a(), gdVar.m1940b(), str2).m1110a("useodex").m1109a(), au.m1088b(name));
            }
        }
    }

    private void m1069a(String str, String str2) {
        try {
            this.f562d.clear();
            m1073c();
            this.f563e = DexFile.loadDex(str, str2, 0);
        } catch (Throwable e) {
            C0247g.m1917a(e, "DynamicClassLoader", "loadDexFile()");
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "DynamicClassLoader", "loadDexFile()");
        }
    }

    private boolean m1070a(C0261v c0261v, gd gdVar, String str) {
        return an.m1055a(c0261v, an.m1057b(gdVar.m1938a(), gdVar.m1940b()), str, gdVar);
    }

    private boolean m1071a(C0261v c0261v, String str, String str2) {
        String a = an.m1047a(this.f561b, str);
        if (an.m1055a(c0261v, str, a, this.f565g)) {
            return true;
        }
        if (ap.m1061a(c0261v, str) != null) {
            return false;
        }
        if (!TextUtils.isEmpty(this.f564f)) {
            ap.m1062a(c0261v, new aw(str, fz.m1908a(a), this.f565g.m1938a(), this.f565g.m1940b(), str2).m1110a("useodex").m1109a(), au.m1088b(str));
        }
        return true;
    }

    private void m1073c() {
        if (this.f563e != null) {
            try {
                this.f563e.close();
            } catch (Throwable e) {
                C0247g.m1917a(e, "DynamicClassLoader", "releaseDexFile()");
            } catch (Throwable e2) {
                C0247g.m1917a(e2, "DynamicClassLoader", "releaseDexFile()");
            }
        }
    }

    boolean m1074a() {
        return this.f563e != null;
    }

    protected Class<?> findClass(String str) {
        try {
            if (this.f563e == null) {
                throw new ClassNotFoundException(str);
            }
            Class<?> cls = (Class) this.f562d.get(str);
            if (cls == null) {
                cls = this.f563e.loadClass(str, this);
                this.f562d.put(str, cls);
                if (cls == null) {
                    throw new ClassNotFoundException(str);
                }
            }
            return cls;
        } catch (Throwable th) {
            C0247g.m1917a(th, "DynamicClassLoader", "findClass()");
            ClassNotFoundException classNotFoundException = new ClassNotFoundException(str);
        }
    }
}
