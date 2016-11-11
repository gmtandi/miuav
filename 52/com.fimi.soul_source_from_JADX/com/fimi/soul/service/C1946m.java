package com.fimi.soul.service;

import android.os.AsyncTask;
import com.fimi.soul.drone.droneconnection.connection.p111a.C1497a;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.service.m */
class C1946m extends AsyncTask<Void, Void, Boolean> {
    final /* synthetic */ DroneConnectService f9985a;

    C1946m(DroneConnectService droneConnectService) {
        this.f9985a = droneConnectService;
    }

    protected Boolean m12181a(Void... voidArr) {
        return Boolean.valueOf(be.m12380d(C1543c.f7237l));
    }

    protected void m12182a(Boolean bool) {
        super.onPostExecute(bool);
        if (bool.booleanValue()) {
            C1497a.m9894a(C1543c.f7229d);
            this.f9985a.f9930a.m9569P().m9996b();
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12181a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12182a((Boolean) obj);
    }
}
