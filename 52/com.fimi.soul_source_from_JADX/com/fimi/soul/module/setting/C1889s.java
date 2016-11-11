package com.fimi.soul.module.setting;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.fimi.soul.module.setting.s */
class C1889s implements OnClickListener {
    final /* synthetic */ FlyLogsActivity f9616a;

    C1889s(FlyLogsActivity flyLogsActivity) {
        this.f9616a = flyLogsActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f9616a.f9062k.setVisibility(0);
        this.f9616a.f9064m.setVisibility(0);
        this.f9616a.f9063l.setVisibility(4);
    }
}
