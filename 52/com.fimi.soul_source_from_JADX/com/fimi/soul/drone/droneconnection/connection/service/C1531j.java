package com.fimi.soul.drone.droneconnection.connection.service;

import android.os.Binder;
import com.fimi.soul.drone.droneconnection.connection.C1514k;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import java.lang.ref.WeakReference;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.service.j */
public class C1531j extends Binder {
    private final WeakReference<MiLinkService> f7170a;

    C1531j(MiLinkService miLinkService) {
        this.f7170a = new WeakReference(miLinkService);
    }

    public int m10040a() {
        MiLinkService miLinkService = (MiLinkService) this.f7170a.get();
        return miLinkService == null ? 0 : miLinkService.f7144c.m9920i();
    }

    public void m10041a(C1465c c1465c) {
        MiLinkService miLinkService = (MiLinkService) this.f7170a.get();
        if (miLinkService != null && miLinkService.f7144c != null && miLinkService.f7144c.m9920i() != 0) {
            miLinkService.f7144c.m9913b(c1465c);
        }
    }

    public void m10042a(String str) {
        MiLinkService miLinkService = (MiLinkService) this.f7170a.get();
        if (miLinkService != null && miLinkService.f7144c != null) {
            miLinkService.f7144c.m9910a(str);
        }
    }

    public void m10043a(String str, C1514k c1514k) {
        MiLinkService miLinkService = (MiLinkService) this.f7170a.get();
        if (miLinkService != null && miLinkService.f7144c != null) {
            miLinkService.f7144c.m9911a(str, c1514k);
        }
    }

    public void m10044a(boolean z) {
        if (((MiLinkService) this.f7170a.get()).f7144c != null) {
            ((MiLinkService) this.f7170a.get()).f7144c.m9924a(z);
        }
    }

    public void m10045b() {
        MiLinkService miLinkService = (MiLinkService) this.f7170a.get();
        if (miLinkService != null) {
            miLinkService.m10009a();
        }
    }

    public void m10046b(boolean z) {
        if (((MiLinkService) this.f7170a.get()).f7144c != null) {
            ((MiLinkService) this.f7170a.get()).f7144c.m9927b(z);
        }
    }

    public void m10047c() {
        MiLinkService miLinkService = (MiLinkService) this.f7170a.get();
        if (miLinkService != null) {
            miLinkService.stopSelf();
            miLinkService.m10010b();
        }
    }

    public boolean m10048d() {
        return ((MiLinkService) this.f7170a.get()).f7144c != null ? ((MiLinkService) this.f7170a.get()).f7144c.m9928c() : false;
    }
}
