package com.fimi.soul.module.calibcompass;

import android.content.Intent;
import com.fimi.soul.module.login.LoginActivity;

/* renamed from: com.fimi.soul.module.calibcompass.n */
class C1680n implements Runnable {
    final /* synthetic */ CaliCompassFourFragment f7938a;

    C1680n(CaliCompassFourFragment caliCompassFourFragment) {
        this.f7938a = caliCompassFourFragment;
    }

    public void run() {
        if (this.f7938a.f7916j) {
            Intent intent = new Intent(this.f7938a.getActivity(), LoginActivity.class);
            intent.putExtra("login", false);
            this.f7938a.startActivity(intent);
            this.f7938a.getActivity().finish();
            return;
        }
        this.f7938a.getActivity().setResult(0);
        this.f7938a.getActivity().finish();
    }
}
