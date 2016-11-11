package com.fimi.soul.drone.droneconnection.connection;

import com.fimi.soul.drone.p107c.p108a.C1465c;
import java.io.IOException;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.h */
class C1511h implements Runnable {
    final /* synthetic */ C1498f f7135a;

    C1511h(C1498f c1498f) {
        this.f7135a = c1498f;
    }

    public void run() {
        while (this.f7135a.f7083m.get() == 2) {
            try {
                try {
                    this.f7135a.m9914c(((C1465c) this.f7135a.f7082l.take()).m9816d());
                } catch (IOException e) {
                    this.f7135a.m9902b(e.getMessage());
                }
            } catch (InterruptedException e2) {
            } finally {
                this.f7135a.m9919h();
            }
        }
    }
}
