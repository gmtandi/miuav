package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p107c.p108a.C1466d;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;

/* renamed from: com.fimi.soul.drone.h.g */
public class C1564g extends C1558a {
    private C1577t f7519a;
    private C1565h f7520b;
    private C1566i f7521c;
    private C1567j f7522d;
    private byte f7523e;
    private byte f7524f;
    private byte f7525g;
    private byte f7526h;

    public C1564g() {
        this.f7519a = C1577t.Camera;
        this.f7520b = C1565h.NormalReady;
        this.f7521c = C1566i.NormalReady;
        this.f7522d = C1567j.NoWarining;
    }

    private C1577t m10536a(byte b) {
        return b == 1 ? C1577t.Camera : b == 2 ? C1577t.Storage : this.f7519a;
    }

    private C1566i m10537b(byte b) {
        return b == 1 ? C1566i.NormalReady : b == 2 ? C1566i.Formating : b == 3 ? C1566i.NoSDCard : C1566i.Error;
    }

    private C1567j m10538c(byte b) {
        return b == 1 ? C1567j.FullSDCard : C1567j.NoWarining;
    }

    public void m10539a(C1466d c1466d) {
        c1466d.m9831c();
        byte d = c1466d.m9833d();
        this.f7523e = d;
        this.f7524f = c1466d.m9833d();
        this.f7525g = c1466d.m9833d();
        this.f7526h = c1466d.m9833d();
        byte b = (byte) (d & 3);
        d = (byte) ((d >>> 2) & 3);
        m10536a(d);
        d = (byte) ((d >>> 2) & 3);
        m10537b(d);
        m10538c((byte) ((d >>> 2) & 3));
    }

    public void m10540a(C1565h c1565h) {
        this.f7520b = c1565h;
    }

    public void m10541a(C1566i c1566i) {
        this.f7521c = c1566i;
    }

    public void m10542a(C1567j c1567j) {
        this.f7522d = c1567j;
    }

    public void m10543a(C1577t c1577t) {
        this.f7519a = c1577t;
    }

    public int m10544b() {
        return this.f7523e & Util.MASK_8BIT;
    }

    public C1577t m10545c() {
        return this.f7519a;
    }

    public long m10546d() {
        long j = 0;
        if (this.f7523e == 1) {
            j = (long) (this.f7524f & ((this.f7525g * 16) + Util.MASK_8BIT));
        }
        return this.f7523e == 2 ? (long) (((((this.f7524f * 60) * 60) * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) + ((this.f7525g * 60) * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER)) + (this.f7526h * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER)) : j;
    }

    public int m10547e() {
        return this.f7524f;
    }

    public C1565h m10548f() {
        return this.f7523e == null ? C1565h.NormalReady : this.f7523e == 1 ? C1565h.PhotoShoting : this.f7523e == 2 ? C1565h.VideoRecording : this.f7523e == 32 ? C1565h.NoSDCard : this.f7523e == 16 ? C1565h.Formating : this.f7523e == 64 ? C1565h.FullSDCard : this.f7523e == 80 ? C1565h.OUTSDCard : this.f7523e == 81 ? C1565h.CAMERASTART : this.f7523e == 82 ? C1565h.WIFIINIT : this.f7523e == 83 ? C1565h.SAVEPHOTO : this.f7523e == 84 ? C1565h.LOW_SPEED_CARD : (this.f7523e & Util.MASK_8BIT) == Util.MASK_8BIT ? C1565h.UPDATEPRO : (this.f7523e & Util.MASK_8BIT) == C1465c.f6998a ? C1565h.UPDATEFAILE : C1565h.Error;
    }

    public C1566i m10549g() {
        return this.f7521c;
    }

    public C1567j m10550h() {
        return this.f7522d;
    }

    public String toString() {
        return "CloudCameraStatusVariable{cameraMode=" + this.f7519a + ", cameraStatus=" + this.f7520b + ", sdCardStatus=" + this.f7521c + ", waringStatus=" + this.f7522d + ", status = " + this.f7523e + ", time = " + m10546d() + '}';
    }
}
