package com.fimi.soul.drone.droneconnection.connection;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.d */
class C1508d implements Runnable {
    final /* synthetic */ C1507c f7132a;

    C1508d(C1507c c1507c) {
        this.f7132a = c1507c;
    }

    public void run() {
        this.f7132a.f7129l = false;
        this.f7132a.f7121d.postDelayed(this, 400);
    }
}
