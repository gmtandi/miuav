package com.fimi.soul.biz.p103k;

import com.fimi.kernel.C1189f;
import com.fimi.soul.biz.p098j.C1375e;
import com.fimi.soul.drone.p114e.C1532a;
import com.fimi.soul.entity.FdsMsg;
import com.fimi.soul.entity.PlaneMsg;
import com.tencent.connect.common.Constants;
import java.io.File;

/* renamed from: com.fimi.soul.biz.k.au */
class au implements C1375e {
    final /* synthetic */ File f6093a;
    final /* synthetic */ at f6094b;

    au(at atVar, File file) {
        this.f6094b = atVar;
        this.f6093a = file;
    }

    public void m9238a(long j, long j2, String str) {
        if (this.f6094b.f6089a != null && j2 / 100 != 0) {
            this.f6094b.f6089a.m9331a((int) (j / (j2 / 100)), str);
        }
    }

    public void m9239a(PlaneMsg planeMsg) {
        FdsMsg fdsMsg = (FdsMsg) planeMsg.getData();
        if (this.f6094b.f6092e == null) {
            this.f6094b.f6092e = (C1532a) C1189f.m8333c().m7926a(Constants.VIA_RESULT_SUCCESS, C1532a.class);
        }
        this.f6094b.m9231a(fdsMsg, this.f6093a, this.f6094b.f6092e);
    }
}
