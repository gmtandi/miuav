package com.fimi.soul.drone.droneconnection.connection;

import android.content.Context;
import com.fimi.soul.drone.droneconnection.connection.p111a.C1497a;
import com.fimi.soul.drone.p116g.C1543c;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.q */
public enum C1519q {
    USB(1),
    TCP(3);
    
    private final int f7141c;

    private C1519q(int i) {
        this.f7141c = i;
        if (i == 3) {
            C1497a.m9894a(C1543c.f7229d);
        } else if (i == 1) {
            C1497a.m9894a(C1543c.f7228c);
        }
    }

    public int m10002a() {
        return this.f7141c;
    }

    public abstract C1499a m10003a(Context context);
}
