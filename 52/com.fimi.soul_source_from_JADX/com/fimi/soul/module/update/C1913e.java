package com.fimi.soul.module.update;

import android.content.Intent;
import com.fimi.soul.module.droneui.C1686j;
import com.fimi.soul.module.login.LoginActivity;

/* renamed from: com.fimi.soul.module.update.e */
class C1913e implements C1686j {
    final /* synthetic */ CheckFirmwareActvity f9822a;

    C1913e(CheckFirmwareActvity checkFirmwareActvity) {
        this.f9822a = checkFirmwareActvity;
    }

    public void m12075a() {
    }

    public void m12076b() {
    }

    public void m12077c() {
    }

    public void m12078d() {
        Intent intent = new Intent(this.f9822a, LoginActivity.class);
        intent.putExtra("login", false);
        this.f9822a.startActivity(intent);
        this.f9822a.finish();
    }
}
