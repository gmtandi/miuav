package com.fimi.soul.module.update;

import android.os.Message;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.update.ah;
import com.fimi.soul.module.droneui.FlightActivity;

/* renamed from: com.fimi.soul.module.update.g */
class C1915g implements ah {
    final /* synthetic */ DowningActivity f9824a;

    C1915g(DowningActivity downingActivity) {
        this.f9824a = downingActivity;
    }

    public void m12079a(boolean z, long j, long j2, int i) {
        if (!z) {
            Message message = new Message();
            message.what = 100;
            message.arg1 = (int) j;
            message.arg2 = (int) j2;
            this.f9824a.getHandler().sendMessageDelayed(message, 300);
        } else if (j == -2) {
            this.f9824a.m11939a(DownFailedActivity.class);
        } else if (this.f9824a.f9694j < this.f9824a.f9692f) {
            this.f9824a.getHandler().sendEmptyMessageDelayed(0, 300);
        } else if (this.f9824a.f9697n.m9570Q()) {
            this.f9824a.m11939a(FindNewFirmwareAvtivity.class);
        } else {
            ak.m8082a(this.f9824a, (int) C1205R.string.finish_down);
            this.f9824a.m11939a(FlightActivity.class);
        }
    }
}
