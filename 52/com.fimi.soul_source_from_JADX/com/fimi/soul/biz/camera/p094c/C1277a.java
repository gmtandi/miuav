package com.fimi.soul.biz.camera.p094c;

import android.util.Log;
import com.fimi.soul.biz.camera.C1299f;
import com.fimi.soul.biz.camera.entity.BaseX11Cmd;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.biz.camera.p088b.C1213e;
import com.fimi.soul.utils.ah;

/* renamed from: com.fimi.soul.biz.camera.c.a */
public abstract class C1277a implements C1213e<X11RespCmd> {
    private C1299f f5738a;

    protected C1277a(C1299f c1299f) {
        this.f5738a = c1299f;
    }

    public C1299f m8702a() {
        return this.f5738a;
    }

    protected void m8703a(int i) {
        m8704a(i, null);
    }

    protected void m8704a(int i, String str) {
        m8705a(i, str, null);
    }

    protected void m8705a(int i, String str, String str2) {
        BaseX11Cmd baseX11Cmd = new BaseX11Cmd();
        baseX11Cmd.setMsg_id(i);
        baseX11Cmd.setParam(str);
        baseX11Cmd.setType(str2);
        baseX11Cmd.setToken(m8702a().m8849e());
        m8706a(baseX11Cmd);
    }

    protected void m8706a(BaseX11Cmd baseX11Cmd) {
        String str = ah.m12244a(baseX11Cmd) + "\n";
        Log.d("Good", "send cmd:" + str);
        m8702a().m8840a(str.getBytes());
    }

    public void m8707a(boolean z, X11RespCmd x11RespCmd) {
    }

    protected void m8709a(byte[] bArr, int i, int i2) {
        m8702a().m8841a(bArr, i, i2);
    }

    protected BaseX11Cmd m8710b(int i, String str, String str2) {
        BaseX11Cmd baseX11Cmd = new BaseX11Cmd();
        baseX11Cmd.setMsg_id(i);
        baseX11Cmd.setParam(str);
        baseX11Cmd.setType(str2);
        baseX11Cmd.setToken(m8702a().m8849e());
        return baseX11Cmd;
    }
}
