package com.fimi.soul.biz.update;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import java.util.TimerTask;

class ab extends TimerTask {
    final /* synthetic */ C1423u f6334a;
    private C1465c f6335b;
    private C1433a f6336c;

    public ab(C1423u c1423u, C1465c c1465c, C1433a c1433a) {
        this.f6334a = c1423u;
        this.f6335b = c1465c;
        this.f6336c = c1433a;
    }

    public void run() {
        C1423u.m9487p();
        this.f6336c.m9569P().m9993a(this.f6335b);
        if (C1423u.f6379H == 20) {
            if (this.f6334a.f6403E != null) {
                this.f6334a.f6403E.cancel();
            }
            if (C1423u.f6382f != null) {
                C1423u.f6382f.m9419a(false, 0, 0, -1);
            }
        }
    }
}
