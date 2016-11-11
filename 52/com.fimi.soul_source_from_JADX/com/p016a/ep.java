package com.p016a;

import android.os.Looper;

/* renamed from: com.a.ep */
final class ep extends Thread {
    final /* synthetic */ dw f1114a;

    ep(dw dwVar, String str) {
        this.f1114a = dwVar;
        super(str);
    }

    public final void run() {
        dw dwVar;
        try {
            synchronized (this.f1114a.f1014c) {
                if (dw.f992a) {
                    Looper.prepare();
                    this.f1114a.f1029z = Looper.myLooper();
                    try {
                        this.f1114a.f1002A = new er(this.f1114a);
                        this.f1114a.f1020q.addGpsStatusListener(this.f1114a.f1002A);
                        this.f1114a.f1020q.addNmeaListener(this.f1114a.f1002A);
                    } catch (Throwable th) {
                    }
                    this.f1114a.f1003B = new eq(this);
                    try {
                        this.f1114a.f1020q.requestLocationUpdates("passive", 1000, (float) dw.f994d, this.f1114a.f1005D);
                    } catch (Throwable th2) {
                    }
                }
            }
            if (dw.f992a) {
                Looper.loop();
            }
            if (!(this.f1114a.f1002A == null || this.f1114a.f1020q == null)) {
                this.f1114a.f1020q.removeGpsStatusListener(this.f1114a.f1002A);
                this.f1114a.f1020q.removeNmeaListener(this.f1114a.f1002A);
                this.f1114a.f1020q.removeUpdates(this.f1114a.f1005D);
                this.f1114a.f1002A = null;
            }
            if (this.f1114a.f1029z != null) {
                this.f1114a.f1029z.quit();
                dwVar = this.f1114a;
                dwVar.f1029z = null;
            }
        } catch (Throwable th3) {
            if (!(this.f1114a.f1002A == null || this.f1114a.f1020q == null)) {
                this.f1114a.f1020q.removeGpsStatusListener(this.f1114a.f1002A);
                this.f1114a.f1020q.removeNmeaListener(this.f1114a.f1002A);
                this.f1114a.f1020q.removeUpdates(this.f1114a.f1005D);
                this.f1114a.f1002A = null;
            }
            if (this.f1114a.f1029z != null) {
                this.f1114a.f1029z.quit();
                this.f1114a.f1029z = null;
            }
        }
    }
}
