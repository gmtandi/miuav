package com.fimi.soul.biz.camera.p094c;

import com.fimi.kernel.C1189f;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1299f;
import com.fimi.soul.biz.camera.C1309p;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.biz.camera.entity.BaseX11Cmd;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import it.p074a.p075a.C2799f;

/* renamed from: com.fimi.soul.biz.camera.c.o */
public class C1291o extends C1277a {
    int f5777a;

    public C1291o(C1299f c1299f) {
        super(c1299f);
        this.f5777a = 0;
    }

    public void m8787a(boolean z) {
        BaseX11Cmd b = m8710b(C1314u.ac, null, null);
        b.setToken(this.f5777a);
        b.setRval(z ? 0 : -1);
        if (z) {
            b.setToken(this.f5777a);
            m8702a().m8827a(this.f5777a);
        }
        m8706a(b);
    }

    public void m8788a(boolean z, X11RespCmd x11RespCmd) {
        super.m8707a(z, x11RespCmd);
        switch (x11RespCmd.getMsg_id()) {
            case C2799f.f14258H /*257*/:
                if (z) {
                    m8702a().m8827a(Integer.parseInt(x11RespCmd.getParam().toString()));
                    this.f5777a = m8702a().m8849e();
                    m8702a().m8836a(C1309p.Normal);
                    m8702a().m8859o();
                    ((C1313t) C1276b.m8680a().m8699d()).m8873r().m8750d();
                    C1189f.m8335e().m8030c(true);
                    C1189f.m8335e().m8033d(true);
                    return;
                }
                m8702a().m8827a(-1);
            case C1314u.ac /*1793*/:
                m8787a(true);
            default:
        }
    }

    public void m8790b() {
        BaseX11Cmd b = m8710b(C2799f.f14258H, null, null);
        b.setToken(0);
        m8706a(b);
    }

    public void m8791c() {
        m8703a((int) C1314u.f5850C);
    }
}
