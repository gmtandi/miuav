package com.fimi.soul.p087b;

import android.view.View;
import com.fimi.kernel.p084e.ak;
import com.fimi.kernel.view.button.C1196c;
import com.fimi.kernel.view.button.SwitchButton;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.camera.C1314u;

/* renamed from: com.fimi.soul.b.e */
class C1216e implements C1196c {
    final /* synthetic */ C1214c f5494a;

    C1216e(C1214c c1214c) {
        this.f5494a = c1214c;
    }

    public void m8461a(View view, boolean z) {
        SwitchButton switchButton = (SwitchButton) view.findViewById(C1205R.id.switch_btn);
        if (!this.f5494a.m8452c()) {
            ak.m8085a(this.f5494a.f5482o, this.f5494a.f5482o.getString(C1205R.string.set_fail_no_connect_camera), ak.f5302b);
            if (this.f5494a.f5481A) {
                switchButton.m8371a(true, false);
            } else {
                switchButton.m8371a(false, false);
            }
        } else if (this.f5494a.m8452c()) {
            if (this.f5494a.f5481A) {
                this.f5494a.f5481A = false;
                switchButton.m8371a(false, true);
                this.f5494a.f5488u = C1314u.ch;
            } else {
                this.f5494a.f5481A = true;
                switchButton.m8371a(true, true);
                this.f5494a.f5488u = C1314u.ci;
            }
            this.f5494a.f5487t = C1314u.bL;
            this.f5494a.f5483p.m8873r().m8742a(this.f5494a.f5487t, this.f5494a.f5488u);
        } else {
            ak.m8085a(this.f5494a.f5482o, this.f5494a.f5482o.getString(C1205R.string.set_fail_no_connect_camera), ak.f5302b);
            if (this.f5494a.f5481A) {
                switchButton.m8371a(true, false);
            } else {
                switchButton.m8371a(false, false);
            }
        }
    }
}
