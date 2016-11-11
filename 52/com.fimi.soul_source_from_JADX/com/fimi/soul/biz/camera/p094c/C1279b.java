package com.fimi.soul.biz.camera.p094c;

import com.fimi.soul.biz.camera.C1299f;
import com.fimi.soul.biz.camera.C1309p;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p107c.p108a.p109a.C1443f;
import com.fimi.soul.drone.p117h.C1564g;
import com.fimi.soul.drone.p117h.C1575r;
import com.fimi.soul.drone.p117h.C1576s;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.biz.camera.c.b */
public class C1279b extends C1278n {
    private C1443f f5739a;
    private boolean f5740b;
    private boolean f5741c;

    public C1279b(C1299f c1299f) {
        super(c1299f);
        this.f5740b = false;
        this.f5741c = false;
    }

    private void m8723a(C1309p c1309p) {
        if (m8702a().m8856l() != null) {
            m8702a().m8856l().m8871a(c1309p, m8702a().m8850f());
        }
    }

    private void m8724a(C1433a c1433a) {
        C1575r c1575r = (C1575r) c1433a.f6506b;
        C1309p f = m8702a().m8850f();
        if (c1575r.m10595d() == C1576s.NormalShot) {
            if (m8702a().m8853i().isContinueCaptureMode()) {
                if (m8702a().m8850f() == C1309p.ContinueCapturing) {
                    m8702a().m8836a(C1309p.Normal);
                } else {
                    m8702a().m8836a(C1309p.ContinueCapturing);
                }
                m8723a(f);
            }
            if (m8702a().m8853i().isPrecisePhotoMode()) {
                m8723a(C1309p.PhotoShoting);
            }
        }
        if (c1575r.m10595d() == C1576s.StartRecord) {
            if (C1309p.Recoding == f) {
                m8702a().m8836a(C1309p.Normal);
                m8723a(f);
            } else {
                m8702a().m8836a(C1309p.Recoding);
                m8723a(f);
            }
        }
        if (c1575r.m10595d() == C1576s.StopRecrod) {
            m8702a().m8836a(C1309p.Normal);
            m8723a(f);
        }
    }

    private void m8725b(C1433a c1433a) {
        C1564g c1564g = (C1564g) c1433a.f6506b;
        C1309p f = m8702a().m8850f();
        switch (C1280c.f5742a[c1564g.m10548f().ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                m8702a().m8836a(C1309p.Recoding);
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                m8702a().m8836a(C1309p.ContinueCapturing);
                break;
            case Type.BYTE /*3*/:
                m8702a().m8836a(C1309p.Normal);
                break;
        }
        m8723a(f);
    }

    public void m8726b() {
        if (this.f5739a != null) {
            this.f5740b = true;
            this.f5739a.m9750f();
        }
    }

    public void m8727c() {
        m8731g();
    }

    public void m8728d() {
        if (this.f5739a != null) {
            this.f5741c = true;
            this.f5739a.m9753i();
        }
    }

    public void m8729e() {
        if (this.f5739a != null) {
            this.f5741c = true;
            this.f5739a.m9744b();
        }
    }

    public void m8730f() {
        if (this.f5739a != null) {
            this.f5741c = true;
            this.f5739a.m9748d();
        }
    }

    public void m8731g() {
        if (this.f5739a != null) {
            this.f5741c = true;
            this.f5739a.m9752h();
        }
    }

    public void m8732h() {
        if (this.f5739a != null) {
            this.f5741c = true;
            this.f5739a.m9736a();
        }
    }

    public void m8733i() {
        if (this.f5739a != null) {
            this.f5741c = true;
            this.f5739a.m9746c();
        }
    }

    public void m8734j() {
        if (this.f5739a != null) {
            this.f5740b = true;
            this.f5739a.m9751g();
        }
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        if (this.f5739a == null && c1433a != null) {
            this.f5739a = C1443f.m9729a(c1433a);
            this.f5739a.m9736a();
        }
        if (c1584h == C1584h.OnRecivedCloudCameraCommandInfo && c1433a.f6506b != null && c1433a.f6506b.m9849a() != 50) {
        }
    }
}
