package com.android.volley;

import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;

class ai {
    public static final boolean f3499a;
    private static final long f3500b = 0;
    private final List<aj> f3501c;
    private boolean f3502d;

    static {
        f3499a = ah.f3498b;
    }

    ai() {
        this.f3501c = new ArrayList();
        this.f3502d = false;
    }

    private long m5063a() {
        if (this.f3501c.size() == 0) {
            return 0;
        }
        return ((aj) this.f3501c.get(this.f3501c.size() - 1)).f3505c - ((aj) this.f3501c.get(0)).f3505c;
    }

    public synchronized void m5064a(String str) {
        this.f3502d = true;
        if (m5063a() > 0) {
            long j = ((aj) this.f3501c.get(0)).f3505c;
            ah.m5058b("(%-4d ms) %s", Long.valueOf(r2), str);
            long j2 = j;
            for (aj ajVar : this.f3501c) {
                ah.m5058b("(+%-4d) [%2d] %s", Long.valueOf(ajVar.f3505c - j2), Long.valueOf(ajVar.f3504b), ajVar.f3503a);
                j2 = ajVar.f3505c;
            }
        }
    }

    public synchronized void m5065a(String str, long j) {
        if (this.f3502d) {
            throw new IllegalStateException("Marker added to finished log");
        }
        this.f3501c.add(new aj(str, j, SystemClock.elapsedRealtime()));
    }

    protected void finalize() {
        if (!this.f3502d) {
            m5064a("Request on the loose");
            ah.m5060c("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
        }
    }
}
