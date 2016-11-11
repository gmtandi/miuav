package com.fimi.soul.biz.camera.p095d;

import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.biz.camera.p088b.C1213e;
import com.fimi.soul.biz.camera.p088b.C1274b;

/* renamed from: com.fimi.soul.biz.camera.d.a */
public class C1294a implements C1213e<X11RespCmd> {
    private C1274b f5780a;

    public void m8797a(C1274b c1274b) {
        if (this.f5780a != null) {
            this.f5780a.m8676a();
        }
    }

    public void m8798a(boolean z, X11RespCmd x11RespCmd) {
        if (z && this.f5780a.m8678c()) {
            m8797a(this.f5780a.m8677b());
        }
    }
}
