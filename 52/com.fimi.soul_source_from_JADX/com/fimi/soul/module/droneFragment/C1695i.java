package com.fimi.soul.module.droneFragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.fimi.soul.module.droneFragment.i */
class C1695i implements OnClickListener {
    final /* synthetic */ FlyActionSettingFragment f8284a;

    C1695i(FlyActionSettingFragment flyActionSettingFragment) {
        this.f8284a = flyActionSettingFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.f8284a.f7959j.m8599a().get() == 3) {
            this.f8284a.f7962m.m11119a();
        }
    }
}
