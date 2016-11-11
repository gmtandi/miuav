package com.amap.api.services.core;

import android.content.Context;
import android.os.Looper;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.p122a.p123a.C2915a;

public class ay implements UncaughtExceptionHandler {
    private static ay f3042a;
    private static ExecutorService f3043e;
    private UncaughtExceptionHandler f3044b;
    private Context f3045c;
    private boolean f3046d;

    /* renamed from: com.amap.api.services.core.ay.1 */
    class C04541 implements Runnable {
        final /* synthetic */ Context f3037a;
        final /* synthetic */ ad f3038b;
        final /* synthetic */ boolean f3039c;
        final /* synthetic */ ay f3040d;

        C04541(ay ayVar, Context context, ad adVar, boolean z) {
            this.f3040d = ayVar;
            this.f3037a = context;
            this.f3038b = adVar;
            this.f3039c = z;
        }

        public void run() {
            try {
                synchronized (Looper.getMainLooper()) {
                    new an(this.f3037a).m4547a(this.f3038b);
                }
                if (this.f3039c) {
                    synchronized (Looper.getMainLooper()) {
                        aq aqVar = new aq(this.f3037a);
                        as asVar = new as();
                        asVar.m4571c(true);
                        asVar.m4567a(true);
                        asVar.m4569b(true);
                        aqVar.m4560a(asVar);
                    }
                    bf.m4664a(this.f3040d.f3045c);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: com.amap.api.services.core.ay.a */
    class C0455a implements br {
        private Context f3041a;

        C0455a(Context context) {
            this.f3041a = context;
        }

        public void m4584a() {
            try {
                bf.m4666b(this.f3041a);
            } catch (Throwable th) {
                ay.m4590a(th, "LogNetListener", "onNetCompleted");
                th.printStackTrace();
            }
        }
    }

    private ay(Context context, ad adVar) {
        this.f3046d = true;
        this.f3045c = context;
        bq.m4731a(new C0455a(context));
        m4592c();
    }

    public static synchronized ay m4586a(Context context, ad adVar) {
        ay ayVar;
        synchronized (ay.class) {
            if (adVar == null) {
                throw new C0495v("sdk info is null");
            } else if (adVar.m4494a() == null || C2915a.f14760f.equals(adVar.m4494a())) {
                throw new C0495v("sdk name is invalid");
            } else {
                try {
                    if (f3042a == null) {
                        f3042a = new ay(context, adVar);
                    } else {
                        f3042a.f3046d = false;
                    }
                    f3042a.m4588a(context, adVar, f3042a.f3046d);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                ayVar = f3042a;
            }
        }
        return ayVar;
    }

    static synchronized ExecutorService m4587a() {
        ExecutorService executorService;
        synchronized (ay.class) {
            try {
                if (f3043e == null || f3043e.isShutdown()) {
                    f3043e = Executors.newSingleThreadExecutor();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            executorService = f3043e;
        }
        return executorService;
    }

    private void m4588a(Context context, ad adVar, boolean z) {
        try {
            ExecutorService a = m4587a();
            if (a != null && !a.isShutdown()) {
                a.submit(new C04541(this, context, adVar, z));
            }
        } catch (RejectedExecutionException e) {
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void m4589a(Throwable th, int i, String str, String str2) {
        bf.m4665a(this.f3045c, th, i, str, str2);
    }

    public static void m4590a(Throwable th, String str, String str2) {
        if (f3042a != null) {
            f3042a.m4589a(th, 1, str, str2);
        }
    }

    public static synchronized ay m4591b() {
        ay ayVar;
        synchronized (ay.class) {
            ayVar = f3042a;
        }
        return ayVar;
    }

    private void m4592c() {
        try {
            this.f3044b = Thread.getDefaultUncaughtExceptionHandler();
            if (this.f3044b == null) {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.f3046d = true;
            } else if (this.f3044b.toString().indexOf("com.amap.api") != -1) {
                this.f3046d = false;
            } else {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.f3046d = true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void m4593b(Throwable th, String str, String str2) {
        if (th != null) {
            try {
                m4589a(th, 1, str, str2);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (th != null) {
            m4589a(th, 0, null, null);
            if (this.f3044b != null) {
                this.f3044b.uncaughtException(thread, th);
            }
        }
    }
}
