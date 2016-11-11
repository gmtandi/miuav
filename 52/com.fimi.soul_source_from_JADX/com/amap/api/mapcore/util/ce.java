package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Looper;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.p122a.p123a.C2915a;

public class ce extends cb implements UncaughtExceptionHandler {
    private static ExecutorService f2327e;
    private Context f2328d;

    /* renamed from: com.amap.api.mapcore.util.ce.1 */
    class C03671 implements Runnable {
        final /* synthetic */ Context f2322a;
        final /* synthetic */ bv f2323b;
        final /* synthetic */ boolean f2324c;
        final /* synthetic */ ce f2325d;

        C03671(ce ceVar, Context context, bv bvVar, boolean z) {
            this.f2325d = ceVar;
            this.f2322a = context;
            this.f2323b = bvVar;
            this.f2324c = z;
        }

        public void run() {
            try {
                synchronized (Looper.getMainLooper()) {
                    new cu(this.f2322a, true).m3928a(this.f2323b);
                }
                if (this.f2324c) {
                    synchronized (Looper.getMainLooper()) {
                        cv cvVar = new cv(this.f2322a);
                        cw cwVar = new cw();
                        cwVar.m3936c(true);
                        cwVar.m3932a(true);
                        cwVar.m3934b(true);
                        cvVar.m3931a(cwVar);
                    }
                    cc.m3815a(this.f2325d.f2328d);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.util.ce.a */
    class C0368a implements dh {
        private Context f2326a;

        C0368a(Context context) {
            this.f2326a = context;
        }

        public void m3825a() {
            try {
                cc.m3818b(this.f2326a);
            } catch (Throwable th) {
                cb.m3809a(th, "LogNetListener", "onNetCompleted");
            }
        }
    }

    private ce(Context context, bv bvVar) {
        this.f2328d = context;
        df.m4012a(new C0368a(context));
        m3832d();
    }

    public static synchronized ce m3827a() {
        ce ceVar;
        synchronized (ce.class) {
            ceVar = (ce) cb.f2306a;
        }
        return ceVar;
    }

    public static synchronized ce m3828a(Context context, bv bvVar) {
        ce ceVar;
        synchronized (ce.class) {
            if (bvVar == null) {
                throw new bk("sdk info is null");
            } else if (bvVar.m3763a() == null || C2915a.f14760f.equals(bvVar.m3763a())) {
                throw new bk("sdk name is invalid");
            } else {
                try {
                    if (cb.f2306a == null) {
                        cb.f2306a = new ce(context, bvVar);
                    } else {
                        cb.f2306a.f2308c = false;
                    }
                    cb.f2306a.m3810a(context, bvVar, cb.f2306a.f2308c);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                ceVar = (ce) cb.f2306a;
            }
        }
        return ceVar;
    }

    public static void m3829a(Throwable th, String str, String str2) {
        if (cb.f2306a != null) {
            cb.f2306a.m3811a(th, 1, str, str2);
        }
    }

    public static synchronized void m3830b() {
        synchronized (ce.class) {
            try {
                if (f2327e != null) {
                    f2327e.shutdown();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                if (!(cb.f2306a == null || Thread.getDefaultUncaughtExceptionHandler() != cb.f2306a || cb.f2306a.f2307b == null)) {
                    Thread.setDefaultUncaughtExceptionHandler(cb.f2306a.f2307b);
                }
                cb.f2306a = null;
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    static synchronized ExecutorService m3831c() {
        ExecutorService executorService;
        synchronized (ce.class) {
            try {
                if (f2327e == null || f2327e.isShutdown()) {
                    f2327e = Executors.newSingleThreadExecutor();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            executorService = f2327e;
        }
        return executorService;
    }

    private void m3832d() {
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

    protected void m3833a(Context context, bv bvVar, boolean z) {
        try {
            ExecutorService c = m3831c();
            if (c != null && !c.isShutdown()) {
                c.submit(new C03671(this, context, bvVar, z));
            }
        } catch (RejectedExecutionException e) {
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void m3834a(Throwable th, int i, String str, String str2) {
        cc.m3816a(this.f2328d, th, i, str, str2);
    }

    public void m3835b(Throwable th, String str, String str2) {
        if (th != null) {
            try {
                m3834a(th, 1, str, str2);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (th != null) {
            m3834a(th, 0, null, null);
            if (this.b != null) {
                this.b.uncaughtException(thread, th);
            }
        }
    }
}
