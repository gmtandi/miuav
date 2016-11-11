package com.fimi.soul.drone;

import android.os.Handler;
import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: com.fimi.soul.drone.d */
public class C1496d extends C1495k {
    private final ConcurrentLinkedQueue<C1584h> f7063b;
    private final Handler f7064c;
    private final Runnable f7065d;
    private final ConcurrentLinkedQueue<C1234i> f7066e;

    public C1496d(C1433a c1433a, Handler handler) {
        super(c1433a);
        this.f7063b = new ConcurrentLinkedQueue();
        this.f7065d = new C1537e(this);
        this.f7066e = new ConcurrentLinkedQueue();
        this.f7064c = handler;
    }

    public void m9890a(C1584h c1584h) {
        this.f7063b.offer(c1584h);
        this.f7064c.post(this.f7065d);
    }

    public void m9891a(C1234i c1234i) {
        int i = 1;
        int i2 = c1234i != null ? 1 : 0;
        if (this.f7066e.contains(c1234i)) {
            i = 0;
        }
        if ((i2 & i) != 0) {
            this.f7066e.add(c1234i);
        }
    }

    public void m9892b(C1234i c1234i) {
        if (c1234i != null && this.f7066e.contains(c1234i)) {
            this.f7066e.remove(c1234i);
        }
    }
}
