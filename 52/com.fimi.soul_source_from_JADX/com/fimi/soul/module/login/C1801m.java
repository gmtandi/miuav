package com.fimi.soul.module.login;

import android.os.Bundle;
import com.fimi.soul.C1205R;

/* renamed from: com.fimi.soul.module.login.m */
class C1801m implements Runnable {
    final /* synthetic */ LoginActivity f8857a;

    C1801m(LoginActivity loginActivity) {
        this.f8857a = loginActivity;
    }

    public void run() {
        if (!this.f8857a.f8799f.isAdded() && !this.f8857a.isFinishing()) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("isEnterLoginFragment", true);
            this.f8857a.f8799f.setArguments(bundle);
            this.f8857a.getFragmentManager().beginTransaction().replace(C1205R.id.main_layout, this.f8857a.f8799f).commitAllowingStateLoss();
        }
    }
}
