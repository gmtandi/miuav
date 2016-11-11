package com.fimi.soul.module.setting;

import android.content.Context;
import android.view.View;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.drone.C1433a;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.module.setting.f */
public class C1850f extends C1848d {
    C1433a f9297a;

    public C1850f(Context context, C1433a c1433a) {
        super(context);
        this.f9297a = c1433a;
    }

    private void m11716g() {
        m11694a((int) C1205R.string.follow_mode, C2915a.f14760f, new C1851g(this));
        m11694a((int) C1205R.string.follow_sensitivity, C2915a.f14760f, null);
        m11694a((int) C1205R.string.follow_dead_zone, C2915a.f14760f, null);
        m11694a((int) C1205R.string.work_mode, C2915a.f14760f, null);
        m11694a((int) C1205R.string.angle_limit, C2915a.f14760f, null);
        m11694a((int) C1205R.string.platform_correct, C2915a.f14760f, null);
        m11694a((int) C1205R.string.cloud_platform_upgrade, C2915a.f14760f, null);
    }

    public void m11717a(boolean z, X11RespCmd x11RespCmd) {
    }

    public View m11719c() {
        m11716g();
        return m11704b();
    }
}
