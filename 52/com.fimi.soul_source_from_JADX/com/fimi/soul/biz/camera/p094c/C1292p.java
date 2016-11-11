package com.fimi.soul.biz.camera.p094c;

import com.fimi.soul.biz.camera.C1299f;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.biz.camera.entity.X11ApWifiConfig;
import com.fimi.soul.biz.camera.entity.X11RespCmd;

/* renamed from: com.fimi.soul.biz.camera.c.p */
public class C1292p extends C1277a {
    private X11ApWifiConfig f5778a;

    public C1292p(C1299f c1299f) {
        super(c1299f);
        this.f5778a = null;
    }

    public void m8792a(X11ApWifiConfig x11ApWifiConfig) {
        String format = String.format("AP_SSID=%s\\nLOCAL_IP=%s\\nESSID=%s\\nAP_CHANNEL=%d", new Object[]{x11ApWifiConfig.getApSSID(), x11ApWifiConfig.getApIP(), x11ApWifiConfig.getApESSID(), Integer.valueOf(x11ApWifiConfig.getApChannel())});
        this.f5778a = x11ApWifiConfig;
        m8704a((int) C1314u.f5871X, format);
    }

    public void m8793a(boolean z, X11RespCmd x11RespCmd) {
        super.m8707a(z, x11RespCmd);
        switch (x11RespCmd.getMsg_id()) {
            case C1314u.f5871X /*1538*/:
                m8702a().m8834a(this.f5778a);
            default:
        }
    }

    public void m8795b() {
        m8703a((int) C1314u.f5870W);
    }

    public void m8796b(X11ApWifiConfig x11ApWifiConfig) {
        m8703a((int) C1314u.f5872Y);
    }
}
