package com.fimi.soul.module.flyplannermedia;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.fimi.soul.module.flyplannermedia.l */
class C1772l implements OnClickListener {
    final /* synthetic */ DroneMediaTestActivity f8705a;

    C1772l(DroneMediaTestActivity droneMediaTestActivity) {
        this.f8705a = droneMediaTestActivity;
    }

    public void onClick(View view) {
        Log.d("Good", "\u5f00\u59cb\u83b7\u53d6IDR");
        this.f8705a.f8679b = System.currentTimeMillis();
        if (this.f8705a.f8678a.m8855k().getCurDirFileList() == null) {
        }
    }
}
