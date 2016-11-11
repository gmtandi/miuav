package com.p016a;

import java.net.ServerSocket;

/* renamed from: com.a.du */
class du extends Thread {
    final /* synthetic */ dr f968a;

    du(dr drVar) {
        this.f968a = drVar;
    }

    public void run() {
        try {
            if (!this.f968a.f958v) {
                this.f968a.m1570g();
            }
            if (!this.f968a.f954r) {
                this.f968a.f954r = true;
                this.f968a.f953q = new ServerSocket(43689);
            }
            while (this.f968a.f954r) {
                this.f968a.f955s = this.f968a.f953q.accept();
                this.f968a.m1556a(this.f968a.f955s);
            }
        } catch (Throwable th) {
            ev.m1777a(th, "APSServiceCore", "run");
        }
        super.run();
    }
}
