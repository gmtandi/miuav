package com.fimi.soul.drone.droneconnection.connection;

import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.j */
class C1513j implements Runnable {
    final /* synthetic */ C1498f f7137a;

    C1513j(C1498f c1498f) {
        this.f7137a = c1498f;
    }

    public void run() {
        if (this.f7137a.f7080j.size() < 1) {
            if (!this.f7137a.f7081k.isEmpty()) {
                for (C1514k c1514k : this.f7137a.f7081k.values()) {
                    c1514k.m9990a(false);
                    c1514k.m9992b(false);
                }
                this.f7137a.f7087q.sendEmptyMessageDelayed(1, 1500);
            } else {
                return;
            }
        }
        if (this.f7137a.f7080j.contains(Integer.valueOf(2)) || this.f7137a.f7080j.contains(Integer.valueOf(3)) || this.f7137a.f7080j.contains(Integer.valueOf(4)) || this.f7137a.f7080j.contains(Integer.valueOf(5))) {
            if (!this.f7137a.f7081k.isEmpty()) {
                for (C1514k c1514k2 : this.f7137a.f7081k.values()) {
                    c1514k2.m9990a(true);
                    c1514k2.m9992b(true);
                }
            } else {
                return;
            }
        } else if (!this.f7137a.f7081k.isEmpty()) {
            if (this.f7137a.f7080j.contains(Integer.valueOf(99)) || this.f7137a.f7080j.contains(Integer.valueOf(98)) || this.f7137a.f7080j.contains(Integer.valueOf(100)) || this.f7137a.f7080j.contains(Integer.valueOf(Opcodes.LMUL))) {
                for (C1514k c1514k22 : this.f7137a.f7081k.values()) {
                    c1514k22.m9992b(true);
                    c1514k22.m9990a(false);
                }
            }
        } else {
            return;
        }
        this.f7137a.f7080j.clear();
        this.f7137a.f7087q.postDelayed(this, 1500);
    }
}
