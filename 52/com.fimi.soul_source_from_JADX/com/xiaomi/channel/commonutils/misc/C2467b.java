package com.xiaomi.channel.commonutils.misc;

import android.os.Handler;
import android.os.Looper;
import com.xiaomi.channel.commonutils.logger.C2463b;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* renamed from: com.xiaomi.channel.commonutils.misc.b */
public class C2467b {
    private C2465a f12430a;
    private Handler f12431b;
    private volatile boolean f12432c;
    private final boolean f12433d;
    private volatile C2466b f12434e;

    /* renamed from: com.xiaomi.channel.commonutils.misc.b.a */
    class C2465a extends Thread {
        final /* synthetic */ C2467b f12428a;
        private final LinkedBlockingQueue<C2466b> f12429b;

        public C2465a(C2467b c2467b) {
            this.f12428a = c2467b;
            super("PackageProcessor");
            this.f12429b = new LinkedBlockingQueue();
        }

        public void m14133a(C2466b c2466b) {
            this.f12429b.add(c2466b);
        }

        public void run() {
            while (!this.f12428a.f12432c) {
                try {
                    this.f12428a.f12434e = (C2466b) this.f12429b.poll(1, TimeUnit.SECONDS);
                    if (this.f12428a.f12434e != null) {
                        this.f12428a.f12431b.sendMessage(this.f12428a.f12431b.obtainMessage(0, this.f12428a.f12434e));
                        this.f12428a.f12434e.m14135b();
                        this.f12428a.f12431b.sendMessage(this.f12428a.f12431b.obtainMessage(1, this.f12428a.f12434e));
                    }
                } catch (Throwable e) {
                    C2463b.m14125a(e);
                }
            }
        }
    }

    /* renamed from: com.xiaomi.channel.commonutils.misc.b.b */
    public abstract class C2466b {
        public void m14134a() {
        }

        public abstract void m14135b();

        public void m14136c() {
        }
    }

    public C2467b() {
        this(false);
    }

    public C2467b(boolean z) {
        this.f12431b = null;
        this.f12432c = false;
        this.f12431b = new C2468c(this, Looper.getMainLooper());
        this.f12433d = z;
    }

    public synchronized void m14141a(C2466b c2466b) {
        if (this.f12430a == null) {
            this.f12430a = new C2465a(this);
            this.f12430a.setDaemon(this.f12433d);
            this.f12430a.start();
        }
        this.f12430a.m14133a(c2466b);
    }

    public void m14142a(C2466b c2466b, long j) {
        this.f12431b.postDelayed(new C2469d(this, c2466b), j);
    }
}
