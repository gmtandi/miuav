package com.fimi.soul.drone.droneconnection.connection.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.fimi.soul.drone.droneconnection.connection.C1499a;
import com.fimi.soul.drone.droneconnection.connection.C1519q;
import com.fimi.soul.drone.droneconnection.connection.p111a.C1497a;
import java.io.IOException;

public class MiLinkService extends Service {
    private static final String f7142a;
    private final C1531j f7143b;
    private C1499a f7144c;

    static {
        f7142a = MiLinkService.class.getSimpleName();
    }

    public MiLinkService() {
        this.f7143b = new C1531j(this);
    }

    private void m10009a() {
        C1519q valueOf = C1519q.valueOf(C1497a.m9893a());
        if (this.f7144c == null || this.f7144c.m9921j() != valueOf.m10002a()) {
            this.f7144c = valueOf.m10003a(this);
        }
        if (this.f7144c != null) {
            try {
                this.f7144c.m9930e();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (this.f7144c.m9920i() != 2) {
            this.f7144c.m9918g();
        }
    }

    private void m10010b() {
        Log.d(f7142a, "Pre disconnect");
        if (this.f7144c != null && this.f7144c.m9920i() != 0) {
            this.f7144c.m9919h();
        }
    }

    public IBinder onBind(Intent intent) {
        return this.f7143b;
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
        m10010b();
    }
}
