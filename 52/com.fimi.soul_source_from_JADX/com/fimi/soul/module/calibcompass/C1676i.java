package com.fimi.soul.module.calibcompass;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.fimi.soul.module.calibcompass.i */
class C1676i implements OnClickListener {
    final /* synthetic */ CaliCompassActivity f7934a;

    C1676i(CaliCompassActivity caliCompassActivity) {
        this.f7934a = caliCompassActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.f7934a.f7878c != null) {
            this.f7934a.f7887l = true;
            this.f7934a.f7878c.m10872a();
        }
        this.f7934a.finish();
    }
}
