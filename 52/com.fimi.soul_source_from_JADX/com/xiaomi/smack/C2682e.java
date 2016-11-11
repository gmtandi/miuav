package com.xiaomi.smack;

import com.xiaomi.kenai.jbosh.C2527z;
import com.xiaomi.kenai.jbosh.ab;

/* renamed from: com.xiaomi.smack.e */
class C2682e implements C2527z {
    final /* synthetic */ C2679b f13281a;

    C2682e(C2679b c2679b) {
        this.f13281a = c2679b;
    }

    public boolean m15198a(ab abVar) {
        if (abVar.m14307a() != null) {
            try {
                this.f13281a.f13275w.write(abVar.m14307a().m14338b());
                this.f13281a.f13275w.flush();
            } catch (Exception e) {
            }
        }
        return false;
    }
}
