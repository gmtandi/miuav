package com.fimi.soul.drone.droneconnection.connection.p112b;

import android.content.SharedPreferences;
import com.fimi.soul.drone.p116g.C1543c;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.b.b */
class C1502b extends C1501c {
    final /* synthetic */ SharedPreferences f7100a;
    final /* synthetic */ C1500a f7101h;

    C1502b(C1500a c1500a, SharedPreferences sharedPreferences) {
        this.f7101h = c1500a;
        this.f7100a = sharedPreferences;
    }

    protected int m9944a() {
        return Integer.parseInt(this.f7100a.getString(C1543c.f7234i, C1543c.f7236k));
    }

    protected String m9945b() {
        return this.f7100a.getString(C1543c.f7235j, C1543c.f7237l);
    }
}
