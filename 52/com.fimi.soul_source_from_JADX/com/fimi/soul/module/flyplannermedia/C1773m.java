package com.fimi.soul.module.flyplannermedia;

import android.util.Log;
import com.fimi.soul.biz.camera.p088b.C1275d;

/* renamed from: com.fimi.soul.module.flyplannermedia.m */
class C1773m implements C1275d {
    final /* synthetic */ DroneMediaTestActivity f8706a;

    C1773m(DroneMediaTestActivity droneMediaTestActivity) {
        this.f8706a = droneMediaTestActivity;
    }

    public void m11450a(String str, String str2) {
        String str3 = "\u82b1\u8d39:" + (System.currentTimeMillis() - this.f8706a.f8679b) + "\u6beb\u79d2";
        Log.d("Good", str3);
        this.f8706a.getViewManager().m8341a(str3);
    }
}
