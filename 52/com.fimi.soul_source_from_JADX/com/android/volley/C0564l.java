package com.android.volley;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

/* renamed from: com.android.volley.l */
public class C0564l extends Thread {
    private final BlockingQueue<C0570r<?>> f3536a;
    private final C0563k f3537b;
    private final C0554b f3538c;
    private final ac f3539d;
    private volatile boolean f3540e;

    public C0564l(BlockingQueue<C0570r<?>> blockingQueue, C0563k c0563k, C0554b c0554b, ac acVar) {
        this.f3540e = false;
        this.f3536a = blockingQueue;
        this.f3537b = c0563k;
        this.f3538c = c0554b;
        this.f3539d = acVar;
    }

    @TargetApi(14)
    private void m5087a(C0570r<?> c0570r) {
        if (VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(c0570r.m5113d());
        }
    }

    private void m5088a(C0570r<?> c0570r, ag agVar) {
        this.f3539d.m5049a((C0570r) c0570r, c0570r.m5098a(agVar));
    }

    public void m5089a() {
        this.f3540e = true;
        interrupt();
    }

    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                C0570r c0570r = (C0570r) this.f3536a.take();
                try {
                    c0570r.m5106a("network-queue-take");
                    if (c0570r.m5122m()) {
                        c0570r.m5110b("network-discard-cancelled");
                    } else {
                        m5087a(c0570r);
                        C0566n a = this.f3537b.m5086a(c0570r);
                        c0570r.m5106a("network-http-complete");
                        if (a.f3544d && c0570r.m5095B()) {
                            c0570r.m5110b("not-modified");
                        } else {
                            C0604z a2 = c0570r.m5105a(a);
                            c0570r.m5106a("network-parse-complete");
                            if (c0570r.m5132w() && a2.f3688b != null) {
                                this.f3538c.m5068a(c0570r.m5119j(), a2.f3688b);
                                c0570r.m5106a("network-cache-written");
                            }
                            c0570r.m5094A();
                            this.f3539d.m5050a(c0570r, a2);
                        }
                    }
                } catch (ag e) {
                    e.m5044a(SystemClock.elapsedRealtime() - elapsedRealtime);
                    m5088a(c0570r, e);
                } catch (Throwable e2) {
                    ah.m5057a(e2, "Unhandled exception %s", e2.toString());
                    ag agVar = new ag(e2);
                    agVar.m5044a(SystemClock.elapsedRealtime() - elapsedRealtime);
                    this.f3539d.m5049a(c0570r, agVar);
                }
            } catch (InterruptedException e3) {
                if (this.f3540e) {
                    return;
                }
            }
        }
    }
}
