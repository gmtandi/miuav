package com.fimi.soul.module.droneui;

import android.os.Handler;
import android.os.Message;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1309p;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.biz.p090b.C1253k;
import com.fimi.soul.drone.C1584h;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.module.droneui.k */
class C1749k extends Handler {
    final /* synthetic */ FlightActivity f8614a;

    C1749k(FlightActivity flightActivity) {
        this.f8614a = flightActivity;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 1 && this.f8614a.f8580l) {
            if (C1253k.m8598a(this.f8614a.getApplicationContext()).m8599a().get() != 4) {
                ak.m8083a(this.f8614a, (int) C1205R.string.updataerror, 3000);
            } else if (((C1313t) C1276b.m8680a().m8699d()).m8850f() != C1309p.Recoding) {
                ak.m8083a(this.f8614a, (int) C1205R.string.open_record_error, 3000);
            } else {
                ak.m8083a(this.f8614a, (int) C1205R.string.updateLoadtakephotoerror, 3000);
            }
            this.f8614a.f8581m.setVisibility(8);
        } else if (message.what != Opcodes.LSHR) {
        } else {
            if (this.f8614a.f8587t > 0) {
                sendEmptyMessageDelayed(Opcodes.LSHR, 1000);
                this.f8614a.f8586r.setText(this.f8614a.f8587t + C2915a.f14760f);
                this.f8614a.f8586r.setVisibility(0);
                this.f8614a.f8587t = this.f8614a.f8587t - 1;
                return;
            }
            this.f8614a.f8586r.setVisibility(8);
            this.f8614a.drone.m9589a(C1584h.CHANGETAKEPHOTOMARKERCOLOR);
        }
    }
}
