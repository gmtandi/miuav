package com.xiaomi.push.service;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.push.service.XMPushService.C2629e;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.service.d */
public class C2648d extends HandlerThread {
    private volatile long f13127a;
    private volatile boolean f13128b;
    private volatile Handler f13129c;
    private List<Pair<C2629e, Long>> f13130d;

    public C2648d(String str) {
        super(str);
        this.f13127a = 0;
        this.f13128b = false;
        this.f13130d = new ArrayList();
    }

    public void m15013a() {
        for (int i = 1; i < 15; i++) {
            m15014a(i);
        }
    }

    public void m15014a(int i) {
        if (this.f13129c != null) {
            this.f13129c.removeMessages(i);
        }
    }

    public void m15015a(int i, Object obj) {
        if (this.f13129c != null) {
            this.f13129c.removeMessages(i, obj);
        }
    }

    public void m15016a(C2629e c2629e, long j) {
        synchronized (this.f13130d) {
            if (this.f13129c != null) {
                Message obtain = Message.obtain();
                obtain.what = c2629e.f13061e;
                obtain.obj = c2629e;
                this.f13129c.sendMessageDelayed(obtain, j);
            } else {
                C2463b.m14123a("the job is pended, the controller is not ready.");
                this.f13130d.add(new Pair(c2629e, Long.valueOf(j)));
            }
        }
    }

    public boolean m15017b() {
        return this.f13128b && System.currentTimeMillis() - this.f13127a > 600000;
    }

    public boolean m15018b(int i) {
        return this.f13129c != null ? this.f13129c.hasMessages(i) : false;
    }

    protected void onLooperPrepared() {
        this.f13129c = new C2649e(this, getLooper());
        synchronized (this.f13130d) {
            for (Pair pair : this.f13130d) {
                C2463b.m14123a("executing the pending job.");
                m15016a((C2629e) pair.first, ((Long) pair.second).longValue());
            }
            this.f13130d.clear();
        }
    }
}
