package com.fimi.soul.service;

import android.os.AsyncTask;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.drone.droneconnection.connection.p111a.C1497a;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.service.o */
class C1948o extends AsyncTask<Void, Void, Boolean> {
    final /* synthetic */ C1947n f9987a;

    C1948o(C1947n c1947n) {
        this.f9987a = c1947n;
    }

    protected Boolean m12183a(Void... voidArr) {
        return Boolean.valueOf(be.m12380d(C1543c.f7237l));
    }

    protected void m12184a(Boolean bool) {
        super.onPostExecute(bool);
        C1313t c1313t = (C1313t) C1276b.m8680a().m8699d();
        if (!this.f9987a.f9986a.f9930a.m9569P().m9995a() && bool.booleanValue()) {
            C1497a.m9894a(C1543c.f7229d);
            this.f9987a.f9986a.f9930a.m9569P().m9996b();
            c1313t.m8875t().m8790b();
        } else if (this.f9987a.f9986a.f9930a.m9569P().m9995a() && !bool.booleanValue() && this.f9987a.f9986a.f9930a.m9569P().m10000e().equals(C1543c.f7229d)) {
            this.f9987a.f9986a.f9930a.m9569P().m9999d();
            c1313t.m8827a(-1);
            c1313t.m8857m();
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12183a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12184a((Boolean) obj);
    }
}
