package com.android.volley;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

/* renamed from: com.android.volley.d */
public class C0556d extends Thread {
    private static final boolean f3513a;
    private final BlockingQueue<C0570r<?>> f3514b;
    private final BlockingQueue<C0570r<?>> f3515c;
    private final C0554b f3516d;
    private final ac f3517e;
    private volatile boolean f3518f;

    static {
        f3513a = ah.f3498b;
    }

    public C0556d(BlockingQueue<C0570r<?>> blockingQueue, BlockingQueue<C0570r<?>> blockingQueue2, C0554b c0554b, ac acVar) {
        this.f3518f = false;
        this.f3514b = blockingQueue;
        this.f3515c = blockingQueue2;
        this.f3516d = c0554b;
        this.f3517e = acVar;
    }

    public void m5075a() {
        this.f3518f = true;
        interrupt();
    }

    public void run() {
        if (f3513a) {
            ah.m5056a("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.f3516d.m5067a();
        while (true) {
            try {
                C0570r c0570r = (C0570r) this.f3514b.take();
                try {
                    c0570r.m5106a("cache-queue-take");
                    if (c0570r.m5122m()) {
                        c0570r.m5110b("cache-discard-canceled");
                    } else {
                        C0555c a = this.f3516d.m5066a(c0570r.m5119j());
                        if (a == null) {
                            c0570r.m5106a("cache-miss");
                            this.f3515c.put(c0570r);
                        } else if (a.m5072a()) {
                            c0570r.m5106a("cache-hit-expired");
                            c0570r.m5101a(a);
                            this.f3515c.put(c0570r);
                        } else {
                            c0570r.m5106a("cache-hit");
                            C0604z a2 = c0570r.m5105a(new C0566n(a.f3506a, a.f3512g));
                            c0570r.m5106a("cache-hit-parsed");
                            if (a.m5073b()) {
                                c0570r.m5106a("cache-hit-refresh-needed");
                                c0570r.m5101a(a);
                                a2.f3690d = true;
                                this.f3517e.m5051a(c0570r, a2, new C0557e(this, c0570r));
                            } else {
                                this.f3517e.m5050a(c0570r, a2);
                            }
                        }
                    }
                } catch (Throwable e) {
                    ah.m5057a(e, "Unhandled exception %s", e.toString());
                }
            } catch (InterruptedException e2) {
                if (this.f3518f) {
                    return;
                }
            }
        }
    }
}
