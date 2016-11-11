package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.p107c.p108a.C1466d;

/* renamed from: com.fimi.soul.drone.h.r */
public class C1575r extends C1558a {
    private boolean f7595a;
    private boolean f7596b;
    private C1577t f7597c;
    private C1576s f7598d;
    private int f7599e;

    public C1575r() {
        this.f7595a = false;
        this.f7596b = false;
        this.f7597c = C1577t.Camera;
        this.f7598d = C1576s.None;
        this.f7599e = 0;
    }

    private C1576s m10586a(byte b) {
        return b == 1 ? C1576s.StartRecord : b == 2 ? C1576s.StopRecrod : b == 6 ? C1576s.QuicklyShot : b == 9 ? C1576s.StopQuicklyShot : b == 5 ? C1576s.NormalShot : C1576s.None;
    }

    private C1577t m10587b(byte b) {
        return b == 1 ? C1577t.Camera : b == 2 ? C1577t.Storage : C1577t.Camera;
    }

    public void m10588a(C1466d c1466d) {
        c1466d.m9831c();
        byte d = c1466d.m9833d();
        byte d2 = c1466d.m9833d();
        if (m10173a() == 51) {
            this.f7599e = (d2 * 100) + c1466d.m9833d();
        }
        m10589a(m10586a(d));
        m10590a(m10587b(d2));
        if (c1466d.m9833d() == (byte) 1) {
            m10593b(true);
        }
        if (c1466d.m9833d() == (byte) 1) {
            m10591a(true);
        }
    }

    public void m10589a(C1576s c1576s) {
        this.f7598d = c1576s;
    }

    public void m10590a(C1577t c1577t) {
        this.f7597c = c1577t;
    }

    public void m10591a(boolean z) {
        this.f7596b = z;
    }

    public C1577t m10592b() {
        return this.f7597c;
    }

    public void m10593b(boolean z) {
        this.f7595a = z;
    }

    public int m10594c() {
        return this.f7599e;
    }

    public C1576s m10595d() {
        return this.f7598d;
    }

    public boolean m10596e() {
        return this.f7596b;
    }

    public boolean m10597f() {
        return this.f7595a;
    }

    public String toString() {
        return "CloudCamraActionVariable{isFormatSDCard=" + this.f7595a + ", isResetFactory=" + this.f7596b + ", cameraMode=" + this.f7597c + ", cameraActionType=" + this.f7598d + '}';
    }
}
