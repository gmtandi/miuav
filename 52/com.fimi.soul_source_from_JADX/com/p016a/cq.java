package com.p016a;

import android.os.Looper;

/* renamed from: com.a.cq */
class cq extends Thread {
    final /* synthetic */ co f785a;

    cq(co coVar) {
        this.f785a = coVar;
    }

    public void run() {
        try {
            synchronized (this.f785a.f783n) {
                if (!this.f785a.f782m) {
                    Looper.prepare();
                    this.f785a.f770a = Looper.myLooper();
                    this.f785a.m1348q();
                    super.run();
                }
            }
            if (this.f785a.f770a != null) {
                Looper.loop();
            }
            if (!(this.f785a.f776g == null || this.f785a.f780k == null)) {
                try {
                    this.f785a.f776g.listen(this.f785a.f780k, 0);
                    this.f785a.f780k = null;
                } catch (Throwable th) {
                }
            }
            synchronized (this.f785a.f783n) {
                if (this.f785a.f770a != null) {
                    this.f785a.f770a.quit();
                    this.f785a.f770a = null;
                }
            }
        } catch (Throwable th2) {
            try {
                ev.m1777a(th2, "CgiManager", "ListenerThread");
                if (!(this.f785a.f776g == null || this.f785a.f780k == null)) {
                    this.f785a.f776g.listen(this.f785a.f780k, 0);
                    this.f785a.f780k = null;
                }
            } catch (Throwable th3) {
            }
            synchronized (this.f785a.f783n) {
            }
            if (this.f785a.f770a != null) {
                this.f785a.f770a.quit();
                this.f785a.f770a = null;
            }
        }
    }
}
