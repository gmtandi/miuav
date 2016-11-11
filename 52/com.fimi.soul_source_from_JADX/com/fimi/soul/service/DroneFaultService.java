package com.fimi.soul.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.fimi.kernel.C1189f;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import org.p122a.p123a.C2915a;

public class DroneFaultService extends Service implements C1234i {
    public C1433a f9935a;
    public DroidPlannerApp f9936b;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.f9936b = (DroidPlannerApp) getApplication();
        this.f9935a = this.f9936b.f5570a;
        this.f9935a.m9590a((C1234i) this);
    }

    public void onDestroy() {
        startService(new Intent(this, DroneFaultService.class));
        this.f9935a.m9594b((C1234i) this);
        super.onDestroy();
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        String str = C2915a.f14760f;
        str = C2915a.f14760f;
        if (C1189f.m8334d() == null) {
            stopSelf();
        } else {
            int i = C1949p.f9988a[c1584h.ordinal()];
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }
}
