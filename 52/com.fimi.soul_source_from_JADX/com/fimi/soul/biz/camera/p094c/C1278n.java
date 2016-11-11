package com.fimi.soul.biz.camera.p094c;

import com.fimi.soul.biz.camera.C1299f;
import com.fimi.soul.biz.camera.C1309p;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.biz.camera.c.n */
public class C1278n extends C1277a implements C1234i {
    public C1278n(C1299f c1299f) {
        super(c1299f);
    }

    public void m8711a(boolean z, X11RespCmd x11RespCmd) {
        super.m8707a(z, x11RespCmd);
        if (z) {
            switch (x11RespCmd.getMsg_id()) {
                case C1314u.f5854G /*513*/:
                    m8702a().m8836a(C1309p.Recoding);
                case C1314u.f5855H /*514*/:
                case C1314u.f5859L /*770*/:
                    m8702a().m8836a(C1309p.Normal);
                case C1314u.f5858K /*769*/:
                    if (m8702a().m8853i().isContinueCaptureMode()) {
                        m8702a().m8836a(C1309p.ContinueCapturing);
                    }
                default:
            }
        }
    }

    public void m8713b() {
        m8703a((int) C1314u.f5858K);
    }

    public void m8714c() {
        m8703a((int) C1314u.f5859L);
    }

    public void m8715d() {
    }

    public void m8716e() {
        m8703a((int) C1314u.f5854G);
    }

    public void m8717f() {
        m8703a((int) C1314u.f5855H);
    }

    public void m8718h() {
    }

    public void m8719i() {
    }

    public void m8720j() {
    }

    public void m8721k() {
        m8703a((int) C1314u.f5856I);
    }

    public void m8722l() {
        m8703a((int) C1314u.f5857J);
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
    }
}
