package com.fimi.soul.module.setting.GimalCalibration;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.fimi.soul.module.setting.GimalCalibration.b */
class C1841b implements OnClickListener {
    final /* synthetic */ GimalCalibrationActivity f9108a;

    C1841b(GimalCalibrationActivity gimalCalibrationActivity) {
        this.f9108a = gimalCalibrationActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.f9108a.f9089f.m11621d();
        this.f9108a.finish();
    }
}
