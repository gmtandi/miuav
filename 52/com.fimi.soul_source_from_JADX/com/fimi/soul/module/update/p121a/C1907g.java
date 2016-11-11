package com.fimi.soul.module.update.p121a;

import android.content.Context;
import com.fimi.kernel.C1189f;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.biz.update.C1423u;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p110d.C1479f;
import com.fimi.soul.drone.p117h.C1575r;
import com.fimi.soul.drone.p117h.ae;
import com.fimi.soul.entity.DroneInfoBean;
import com.tencent.mm.sdk.platformtools.Util;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.module.update.a.g */
public class C1907g implements C1234i {
    Context f9808a;
    C1433a f9809b;
    C1901a f9810c;
    C1313t f9811d;

    public C1907g(Context context, C1433a c1433a) {
        this.f9808a = context;
        this.f9809b = c1433a;
        c1433a.m9590a((C1234i) this);
        this.f9810c = C1901a.m12002a();
        this.f9811d = (C1313t) C1276b.m8680a().m8699d();
        C1189f.m8333c().m7926a(C1423u.f6385j, DroneInfoBean.class);
    }

    private void m12070a(ae aeVar) {
        int c = aeVar.m10247c() & Util.MASK_16BIT;
        char b = aeVar.m10246b();
        byte a = aeVar.m10244a();
        byte d = aeVar.m10248d();
        long e = aeVar.m10249e();
        long f = aeVar.m10250f();
        long g = aeVar.m10251g();
        long h = aeVar.m10252h();
        if (c > 0) {
            C1901a c1901a = this.f9810c;
            c1901a.m12005a(new C1905e(a, d, c, String.valueOf(Long.valueOf((long) b)), e, f, g, h));
            this.f9810c.m12004a(a, c);
        }
    }

    private void m12071d() {
        if (this.f9809b.f6506b.m9849a() == 51) {
            C1575r c1575r = (C1575r) this.f9809b.f6506b;
            this.f9811d.m8853i().setDvVersion(c1575r.m10594c() + C2915a.f14760f);
            this.f9810c.m12004a(4, c1575r.m10594c());
            this.f9810c.m12005a(new C1905e(C2915a.f14760f, 4, c1575r.m10594c()));
        }
    }

    public void m12072a() {
        C1479f.m9869i(this.f9809b);
        this.f9811d.m8876u().m8719i();
    }

    public void m12073b() {
        C1479f.m9868h(this.f9809b);
        this.f9811d.m8876u().m8719i();
    }

    public void m12074c() {
        this.f9809b.m9594b((C1234i) this);
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        switch (C1908h.f9812a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                m12070a(c1433a.m9606i());
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                m12071d();
            default:
        }
    }
}
