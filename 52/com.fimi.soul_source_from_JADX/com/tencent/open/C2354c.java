package com.tencent.open;

import android.content.Context;
import android.location.Location;
import com.tencent.map.p129a.p130a.C2223a;

/* renamed from: com.tencent.open.c */
public class C2354c {
    private C2355d f12067a;

    /* renamed from: com.tencent.open.c.a */
    public interface C2290a {
        void onLocationUpdate(Location location);
    }

    public void m13808a(Context context, C2290a c2290a) {
        this.f12067a = new C2355d(c2290a);
        C2223a.m13348a().m13349a(context, this.f12067a);
    }

    public boolean m13809a() {
        return C2223a.m13348a().m13350a("OpenSdk", "WQMPF-XMH66-ISQXP-OIGMM-BNL7M");
    }

    public void m13810b() {
        C2223a.m13348a().m13351b();
        this.f12067a = null;
    }
}
