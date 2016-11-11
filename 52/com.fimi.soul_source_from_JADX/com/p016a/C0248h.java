package com.p016a;

import android.content.Context;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.h */
public class C0248h extends C0247g implements UncaughtExceptionHandler {
    private static ExecutorService f1279e;
    private Context f1280d;

    private C0248h(Context context, gd gdVar) {
        this.f1280d = context;
        bn.m1178a(new C0250j(context));
        m1981c();
    }

    public static synchronized C0248h m1976a(Context context, gd gdVar) {
        C0248h c0248h;
        synchronized (C0248h.class) {
            if (gdVar == null) {
                throw new fm("sdk info is null");
            } else if (gdVar.m1938a() == null || C2915a.f14760f.equals(gdVar.m1938a())) {
                throw new fm("sdk name is invalid");
            } else {
                try {
                    if (C0247g.f1249a == null) {
                        C0247g.f1249a = new C0248h(context, gdVar);
                    } else {
                        C0247g.f1249a.f1251c = false;
                    }
                    C0247g.f1249a.m1918a(context, gdVar, C0247g.f1249a.f1251c);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                c0248h = (C0248h) C0247g.f1249a;
            }
        }
        return c0248h;
    }

    public static synchronized void m1977a() {
        synchronized (C0248h.class) {
            try {
                if (f1279e != null) {
                    f1279e.shutdown();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                if (!(C0247g.f1249a == null || Thread.getDefaultUncaughtExceptionHandler() != C0247g.f1249a || C0247g.f1249a.f1250b == null)) {
                    Thread.setDefaultUncaughtExceptionHandler(C0247g.f1249a.f1250b);
                }
                C0247g.f1249a = null;
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    static synchronized ExecutorService m1978b() {
        ExecutorService executorService;
        synchronized (C0248h.class) {
            try {
                if (f1279e == null || f1279e.isShutdown()) {
                    f1279e = Executors.newSingleThreadExecutor();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            executorService = f1279e;
        }
        return executorService;
    }

    public static void m1979b(gd gdVar, String str) {
        if (C0247g.f1249a != null) {
            C0247g.f1249a.m1919a(gdVar, str);
        }
    }

    public static void m1980b(Throwable th, String str, String str2) {
        if (C0247g.f1249a != null) {
            C0247g.f1249a.m1920a(th, 1, str, str2);
        }
    }

    private void m1981c() {
        try {
            this.b = Thread.getDefaultUncaughtExceptionHandler();
            if (this.b == null) {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.c = true;
            } else if (this.b.toString().indexOf("com.amap.api") != -1) {
                this.c = false;
            } else {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.c = true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void m1982a(Context context, gd gdVar, boolean z) {
        try {
            ExecutorService b = C0248h.m1978b();
            if (b != null && !b.isShutdown()) {
                b.submit(new C0249i(this, context, gdVar, z));
            }
        } catch (RejectedExecutionException e) {
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void m1983a(gd gdVar, String str) {
        C0255n.m2031a(this.f1280d, gdVar, str);
    }

    protected void m1984a(Throwable th, int i, String str, String str2) {
        C0255n.m2032a(this.f1280d, th, i, str, str2);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (th != null) {
            m1984a(th, 0, null, null);
            if (this.b != null) {
                this.b.uncaughtException(thread, th);
            }
        }
    }
}
